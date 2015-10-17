package com.inshop.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by savetisyan on 15/10/15.
 */
@Embeddable
public class Address implements Serializable {
    private String country;
    private String city;
    private String stateOrProvince;
    private String address;
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
}
