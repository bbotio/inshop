package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base class for delivery type (e.g.: self-service, EMS, DHL and etc.)
 * Every delivery type has the name and description, which includes delivery's conditions (cost,
 * delivery area and etc.)
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class DeliveryType implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

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

    @Override
    public String toString() {
        return "DeliveryType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '}';
    }
}
