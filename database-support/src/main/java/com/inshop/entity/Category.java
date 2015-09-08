package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base class for product category.
 *
 * Every category has a {@link Category#name} (shoes, t-shirts and etc.) and {@link Category#product}.
 * Each category can has more than one product.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", product=" + product +
                '}';
    }
}