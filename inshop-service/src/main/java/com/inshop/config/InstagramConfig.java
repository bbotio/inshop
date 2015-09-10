package com.inshop.config;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by savetisyan on 09/09/15.
 */
@Configuration
public class InstagramConfig {
    @Value("${client_id}")
    private String clientId;

    @Value("${client_secret}")
    private String clientSecret;

    @Value("${url:http://localhost:8080/login/handleToken}")
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
