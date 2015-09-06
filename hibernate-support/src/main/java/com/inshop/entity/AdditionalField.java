package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class store additional parameters from user-defined url in product description.
 *
 * Every product image in instagram must has url to user's shop, e.g:
 * http://<domain>.inshop.com/index.html?category=<category>&size=<size>&param1=value1&...
 * all parameters and values after mandatory parameters like category and size will be stored
 * in {@link AdditionalField}.
 *
 * Also this class contains reference to product object.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
@Table(name = "AdditionalField")
public class AdditionalField implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "AdditionalField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", product=" + product +
                '}';
    }
}
