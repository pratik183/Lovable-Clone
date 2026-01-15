package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.subscription.CheckoutRequest;
import com.lovable.com.lovable.dto.subscription.CheckoutResponse;
import com.lovable.com.lovable.dto.subscription.PortalResponse;
import com.lovable.com.lovable.entity.Plan;
import com.lovable.com.lovable.entity.User;
import com.lovable.com.lovable.error.ResourceNotFoundException;
import com.lovable.com.lovable.repository.PlanRepository;
import com.lovable.com.lovable.repository.UserRepository;
import com.lovable.com.lovable.security.AuthUtil;
import com.lovable.com.lovable.service.PaymentProcessor;
import com.stripe.exception.StripeException;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripePaymentProcessor implements PaymentProcessor {

    private final AuthUtil authUtil; // Provides current authenticated user id
    private final PlanRepository planRepository; // Fetches plan/pricing data from DB
    private final UserRepository userRepository;

    @Value("${client.url}")
    private String frontendUrl; // Base URL for success/cancel redirects

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        Plan plan = planRepository.findById(request.planId()).orElseThrow(() ->
                new ResourceNotFoundException("Plan", request.planId().toString())); // Ensure plan exists
        Long userId = authUtil.getCurrentUserId(); // Associate checkout with current user
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", userId.toString()));

        // This params code mostly taken from docs and modified somethings
        var params = SessionCreateParams.builder()
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setPrice(plan.getStripePriceId()).setQuantity(1L).build()) // setting price from our db and setting quantity one as this is subscription so only one line item
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION) // mode is subscription
                .setSubscriptionData(
                        new SessionCreateParams.SubscriptionData.Builder()
                                .setBillingMode(SessionCreateParams.SubscriptionData.BillingMode.builder()
                                    .setType(SessionCreateParams.SubscriptionData.BillingMode.Type.FLEXIBLE) // Here we are setting billing mode as flexible so that new way of payments start using services today and pay at same day after month will happen
                                    .build())
                        .build()
                )
                .setSuccessUrl(frontendUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}") // Redirect on success
                .setCancelUrl(frontendUrl + "/cancel.html") // Redirect on cancel
                .putMetadata("userId", user.toString()) // Persist user for webhook handling
                .putMetadata("planId", plan.getId().toString()); // Persist plan for fulfillment


        try {
            String stripeCustomerId = user.getStripeCustomerId();
            if (stripeCustomerId == null || stripeCustomerId.isBlank()) {
                params.setCustomerEmail(user.getUsername()); // here we are checking if user is existing then setting their email so for same email/customer new stripe customer will not be created
            } else {
                params.setCustomer(stripeCustomerId); // if customer/email is new then creating new customer with stripe
            }
            Session session = Session.create(params.build()); // Call Stripe to create checkout session
            return new CheckoutResponse(session.getUrl()); // Return URL for client redirection
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }

    @Override
    public void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata) {

        log.info("type");
    }
}
