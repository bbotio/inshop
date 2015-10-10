package com.inshop.config;

import com.inshop.PayPalFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by savetisyan on 09/10/15.
 */
@Configuration
public class PayPalConfig {
    @Value("${paypal.grant_permissions_url}")
    private String grantPermissionsUrl;

    @Bean
    public String grantPermissionsUrl() {
        return grantPermissionsUrl;
    }

    @Bean(initMethod = "init")
    public PayPalFactory payPalFactory() {
        return new PayPalFactory();
    }
}