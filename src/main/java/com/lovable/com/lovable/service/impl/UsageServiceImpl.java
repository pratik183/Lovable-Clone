package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.subscription.PlanLimitsResponse;
import com.lovable.com.lovable.dto.subscription.UsageTodayResponse;
import com.lovable.com.lovable.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null; // TODO: compute usage for the current day (e.g., API calls, credits) for user
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null; // TODO: return plan limits based on user's current subscription
    }
}
