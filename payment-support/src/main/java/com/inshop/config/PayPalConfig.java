package com.inshop.config;

import com.inshop.Converter;
import com.inshop.PayPalFactory;
import com.inshop.entity.Token;
import com.paypal.core.credential.SignatureCredential;
import com.paypal.core.credential.TokenAuthorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.*;

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

    @Bean
    public Converter converter() {
        return new Converter();
    }
}