package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by savetisyan on 05/09/15.
 */
@Entity
@Table(name = "OrderDelivery")
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
}
