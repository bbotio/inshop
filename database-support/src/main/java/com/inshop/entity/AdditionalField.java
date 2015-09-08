package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class store additional parameters from user-defined url in product description.
 *
 * Every product image in instagram must have an url to the user's shop, e.g:
 * http://shopname.inshop.com/index.html?category=tshirt&size=xxl&param1=value1&...
 * all parameters and values after mandatory parameters like category and size will be stored
 * in {@link AdditionalField}.
 *
 * Also this class contains reference to product object.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class AdditionalField implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

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

    @Override
    public String toString() {
        return "AdditionalField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '}';
    }
}
