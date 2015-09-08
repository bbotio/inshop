package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base class for customer.
 * {@link Customer#name} - customer's name
 * {@link Customer#email} - customer's email
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
