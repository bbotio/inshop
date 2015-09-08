package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base class for order delivery type.
 *
 * Contains departure date, {@link OrderDelivery#trackId} in some delivery service (or null, if user has selected
 * self-service) and delivery address.
 * Also this class contains reference to current shop's delivery types.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class OrderDelivery implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "track_id")
    private String trackId;

    @Column(name = "addess")
    private String address;

    @OneToOne
    @JoinColumn(name = "shop_delivery_id", referencedColumnName = "id")
    private ShopDelivery shopDelivery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShopDelivery getShopDelivery() {
        return shopDelivery;
    }

    public void setShopDelivery(ShopDelivery shopDelivery) {
        this.shopDelivery = shopDelivery;
    }

    @Override
    public String toString() {
        return "OrderDelivery{" +
                "id=" + id +
                ", date=" + date +
                ", trackId='" + trackId + '\'' +
                ", address='" + address + '\'' +
                ", shopDelivery=" + shopDelivery +
                '}';
    }
}
