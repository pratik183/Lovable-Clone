package com.lovable.com.lovable.controller;


import com.lovable.com.lovable.dto.subscription.PlanLimitsResponse;
import com.lovable.com.lovable.dto.subscription.UsageTodayResponse;
import com.lovable.com.lovable.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
public class UsageController {

    private final UsageService usageService; // Exposes usage metrics and limits for the current user

    @GetMapping("/check")
    public String justCheck(){
        return "Usage Controller is working"; // Simple health check for this controller
    }

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getTodayUsage(){
        Long userId = 1L; // TODO: fetch from authenticated principal
        return ResponseEntity.ok(usageService.getTodayUsageOfUser(userId)); // Return today's usage counters
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponse> getPlanLimits(){
        Long userId = 1L; // TODO: fetch from authenticated principal
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId)); // Return plan limits for current user
    }
}
