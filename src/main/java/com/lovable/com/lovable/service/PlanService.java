package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
