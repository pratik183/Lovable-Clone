package com.lovable.com.lovable.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil; // Verifies JWT and builds principal
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
        log.info("incoming request: {} {}", request.getMethod(), request.getRequestURI());

        final String requestHeaderToken = request.getHeader("Authorization"); // Expecting "Bearer <token>"
        log.info("Authorization header: {}", requestHeaderToken);

        if (requestHeaderToken == null || !requestHeaderToken.startsWith("Bearer")) {
            filterChain.doFilter(request, response); // No JWT: continue without authentication
            log.warn("No Authorization header present in the request");
            return;
        }

        // Extract JWT token Authorization: "Bearer <token>"
        String jwtToken = requestHeaderToken.split("Bearer ")[1];

        JwtUserPrincipal user = authUtil.verifyAccessToken(jwtToken); // Validate and parse JWT

        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.authorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Populate security context
        }

        filterChain.doFilter(request, response); // Continue filter chain
    } catch (Exception e) {
        // Handled exceptions releated to JWT and authentication
        handlerExceptionResolver.resolveException(request, response, null, e);
    }


    }
}
