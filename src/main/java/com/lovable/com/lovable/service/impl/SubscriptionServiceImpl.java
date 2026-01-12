package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.subscription.CheckoutRequest;
import com.lovable.com.lovable.dto.subscription.CheckoutResponse;
import com.lovable.com.lovable.dto.subscription.PortalResponse;
import com.lovable.com.lovable.dto.subscription.SubscriptionResponse;
import com.lovable.com.lovable.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
