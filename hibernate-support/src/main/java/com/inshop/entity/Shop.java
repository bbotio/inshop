package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by savetisyan on 05/09/15.
 */

@Entity
@Table(name = "Shop")
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

    @ManyToOne
    @JoinColumn(name = "shop_delivery_id", referencedColumnName = "id")
    private ShopDelivery shopDelivery;

    public Shop() {
    }

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

    public ShopDelivery getShopDelivery() {
        return shopDelivery;
    }

    public void setShopDelivery(ShopDelivery shopDelivery) {
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
}
