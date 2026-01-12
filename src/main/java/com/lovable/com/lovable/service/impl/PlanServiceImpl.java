package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.subscription.PlanResponse;
import com.lovable.com.lovable.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
