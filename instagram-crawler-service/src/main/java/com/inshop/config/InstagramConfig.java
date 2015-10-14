package com.inshop.config;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstagramConfig {
    @Value("${instagram.client_id}")
    private String clientId;

    @Value("${instagram.client_secret}")
    private String clientSecret;

    @Value("${instagram.handle_token:http://localhost:8080/login/handleToken}")
    private String url;

    @Bean
    public InstagramService instagramService() {
        return new InstagramAuthService()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(url)
                .build();
    }
}