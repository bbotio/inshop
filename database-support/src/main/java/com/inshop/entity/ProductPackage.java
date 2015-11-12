package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Every product has count. After fetching all data we populate (product x count) to (product, product, ...)
 * {@link ProductPackage} is used to store all products of the same type.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class ProductPackage implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductPackage{" +
                "id=" + id +
                '}';
    }
}
