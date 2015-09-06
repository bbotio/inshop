package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by savetisyan on 05/09/15.
 */
@Entity
@Table(name = "DeliveryType")
public class DeliveryType implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "shop_delivery_id", referencedColumnName = "id")
    private ShopDelivery shopDelivery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopDelivery getShopDelivery() {
        return shopDelivery;
    }

    public void setShopDelivery(ShopDelivery shopDelivery) {
        this.shopDelivery = shopDelivery;
    }

    @Override
    public String toString() {
        return "DeliveryType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", shopDelivery=" + shopDelivery +
                '}';
    }
}
