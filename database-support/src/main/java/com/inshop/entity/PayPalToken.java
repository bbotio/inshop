package com.inshop.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Wrapper for PayPal TokenAuthorization class
 * Created by savetisyan on 10/10/15.
 */
@Embeddable
public class PayPalToken implements Serializable {
    private String accessToken;
    private String tokenSecret;

    public PayPalToken() {
    }

    public PayPalToken(String accessToken, String tokenSecret) {
        this.accessToken = accessToken;
        this.tokenSecret = tokenSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
