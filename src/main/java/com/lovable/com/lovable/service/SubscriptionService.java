package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.subscription.CheckoutRequest;
import com.lovable.com.lovable.dto.subscription.CheckoutResponse;
import com.lovable.com.lovable.dto.subscription.PortalResponse;
import com.lovable.com.lovable.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);
}
