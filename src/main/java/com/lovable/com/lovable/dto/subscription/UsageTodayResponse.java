package com.lovable.com.lovable.dto.subscription;

public record UsageTodayResponse(
        Integer tokensUsed,
        Integer tokensLimit,
        Integer previewRunning,
        Integer previewLimit
) {
}
