package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Base class for user's shop.
 * User can choose subdomain in our domain (inshop.com), e.g.: http://sevakshop.inshop.com,
 * or specify his own domain name, e.g.: myshop.com
 *
 * User can change {@link Shop#title} on main page. Also he can change shop's description.
 * {@link Shop#theme} is UI theme of shop.
 * {@link Shop#shopDelivery} is available delivery type (self-service, EMS and etc.).
 * Every shop can has more than one {@link ShopDelivery}.
 *
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

    @OneToMany
    @JoinColumn(name = "shop_delivery_id", referencedColumnName = "id")
    private Set<ShopDelivery> shopDelivery;

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

        if (domain != null ? !domain.equals(shop.domain) : shop.domain != null) return false;
        if (title != null ? !title.equals(shop.title) : shop.title != null) return false;
        if (description != null ? !description.equals(shop.description) : shop.description != null) return false;
        if (theme != null ? !theme.equals(shop.theme) : shop.theme != null) return false;
        return !(shopDelivery != null ? !shopDelivery.equals(shop.shopDelivery) : shop.shopDelivery != null);

    }

    @Override
    public int hashCode() {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (shopDelivery != null ? shopDelivery.hashCode() : 0);
        return result;
    }
}
