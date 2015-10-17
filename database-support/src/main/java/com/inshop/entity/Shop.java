package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * Base class for owner's shop.
 * User can choose subdomain in our domain (inshop.com), e.g.: http://sevakshop.inshop.com,
 * or specify his own domain name, e.g.: myshop.com
 * <p>
 * User can change {@link Shop#title} on main page. Also he can change shop's description.
 * {@link Shop#theme} is UI theme of shop.
 * {@link Shop#shopDelivery} is available delivery type (self-service, EMS and etc.).
 * Every shop can has more than one {@link ShopDelivery}.
 * <p>
 * Created by savetisyan on 05/09/15.
 */

@Entity
public class Shop implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "domain")
    private String domain;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    private Theme theme;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "shop_delivery_id", referencedColumnName = "id")
    private Set<ShopDelivery> shopDelivery;

    @OneToOne(cascade = ALL, mappedBy = "shop")
    private ShopAnalytics shopAnalytics;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Set<ShopDelivery> getShopDelivery() {
        return shopDelivery;
    }

    public void setShopDelivery(Set<ShopDelivery> shopDelivery) {
        this.shopDelivery = shopDelivery;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }

    public ShopAnalytics getShopAnalytics() {
        return shopAnalytics;
    }

    public void setShopAnalytics(ShopAnalytics shopAnalytics) {
        this.shopAnalytics = shopAnalytics;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", theme=" + theme +
                ", shopDelivery=" + shopDelivery +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        return id == shop.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
