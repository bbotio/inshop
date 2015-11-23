package com.inshop.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Avetisyan Sevak
 * Date: 20.09.2015
 * Time: 23:53
 */
@Entity
public class Price implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Price() {
    }

    public Price(double price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Currency {
        USD, EUR, RUB
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", price, currency.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (Double.compare(price1.price, price) != 0) return false;
        return currency == price1.currency;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
