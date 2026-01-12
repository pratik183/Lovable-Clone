package com.lovable.com.lovable.dto.subscription;

public record PlanResponse(
        Long id,
        String name,
        Integer newProjects,
        Integer maxTokensPerDay,
        Boolean unlimitedAi,
        String price
) {
}
