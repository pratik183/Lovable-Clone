package com.lovable.com.lovable.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Value("${stripe.api.secret}")
    private String stripeSecretKey;

    // This method is for connecting to stripe from our application
    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeSecretKey;
    }
}
