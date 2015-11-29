package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.ALL;

/**
 * Base user entity.
 * Contains user's {@link User#email} and {@link User#instagramToken}.
 * Instagram token is required for fetching {@link Product}'s from user's instagram account.
 * Also user has direct reference to his {@link Shop}. Pay attention, that user can
 * has only one shop.
 * <p/>
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "token", column = @Column(name = "instagram_token")),
            @AttributeOverride(name = "secret", column = @Column(name = "instagram_secret"))
    })
    @Column(name = "instagram_token")
    private Token instagramToken;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "token", column = @Column(name = "paypal_token")),
            @AttributeOverride(name = "secret", column = @Column(name = "paypal_secret"))
    })
    @Column(name = "paypal_token")
    private Token paypalToken;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private Shop shop;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Token getInstagramToken() {
        return instagramToken;
    }

    public void setInstagramToken(Token instagramToken) {
        this.instagramToken = instagramToken;
    }

    public Token getPaypalToken() {
        return paypalToken;
    }

    public void setPaypalToken(Token paypalToken) {
        this.paypalToken = paypalToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", instagramUserId='" + instagramUserId + '\'' +
                ", shop=" + shop +
                ", address=" + address +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
