package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.subscription.PlanLimitsResponse;
import com.lovable.com.lovable.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
