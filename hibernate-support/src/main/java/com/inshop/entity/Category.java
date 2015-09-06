package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by savetisyan on 05/09/15.
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
