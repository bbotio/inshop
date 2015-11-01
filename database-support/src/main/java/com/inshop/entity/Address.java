package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by savetisyan on 15/10/15.
 */
@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "state_or_province")
    private String stateOrProvince;

    @Column(name = "address")
    private String address;

    @Column(name = "zip")
    private String zip;

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id == address.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
