package com.inshop.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Wrapper for PayPal TokenAuthorization class
 * Created by savetisyan on 10/10/15.
 */
@Embeddable
public class Token implements Serializable {
    private String token;
    private String secret;

    public Token() {
    }

    public Token(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
