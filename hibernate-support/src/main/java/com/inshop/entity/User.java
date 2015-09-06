package com.inshop.entity;

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
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "instagram_token")
    private String instagramToken;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstagramToken() {
        return instagramToken;
    }

    public void setInstagramToken(String instagramToken) {
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
