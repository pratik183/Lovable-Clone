package com.lovable.com.lovable.controller;

import com.lovable.com.lovable.dto.subscription.*;
import com.lovable.com.lovable.service.PlanService;
import com.lovable.com.lovable.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillingController {

    private final PlanService planService; // Exposes available plans
    private final SubscriptionService subscriptionService; // Manages user subscriptions and Stripe flows

    @GetMapping("/api/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        return ResponseEntity.ok(planService.getAllActivePlans()); // List active/marketed plans
    }

    @GetMapping("/api/me/subscription")
    public ResponseEntity<SubscriptionResponse> getMySubscription(){
        Long userId = 1L; // TODO: obtain from auth context
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId)); // Return current user's subscription info
    }

    @PostMapping("/api/stripe/checkout")
    public ResponseEntity<CheckoutResponse> crateCheckoutResponse(
            @RequestBody CheckoutRequest request
            ){
        Long userId = 1L; // TODO: obtain from auth context
        return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(request,userId)); // Create Stripe checkout session URL
    }

    @PostMapping("/api/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal(){
        Long userId = 1L; // TODO: obtain from auth context
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId)); // Open Stripe customer portal for billing management
    }


}
