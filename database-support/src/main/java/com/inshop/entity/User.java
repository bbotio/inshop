package com.inshop.entity;

import org.jinstagram.auth.model.Token;

import javax.persistence.*;
import java.io.Serializable;

/** Base user entity.
 * Contains user's {@link User#email} and {@link User#instagramToken}.
 * Instagram token is required for fetching {@link Product}'s from user's instagram account.
 * Also user has direct reference to his {@link Shop}. Pay attention, that user can
 * has only one shop.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "instagram_user_id")
    private String instagramUserId;

    @Column(name = "instagram_token")
    private Token instagramToken;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private Shop shop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Token getInstagramToken() {
        return instagramToken;
    }

    public void setInstagramToken(Token instagramToken) {
        this.instagramToken = instagramToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getInstagramUserId() {
        return instagramUserId;
    }

    public void setInstagramUserId(String instagramUserId) {
        this.instagramUserId = instagramUserId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", instagramToken='" + instagramToken + '\'' +
                ", email='" + email + '\'' +
                ", shop=" + shop +
                '}';
    }
}
