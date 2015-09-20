package com.inshop.entity;


/**
 * Created by Avetisyan Sevak
 * Date: 20.09.2015
 * Time: 23:53
 */
public class Price {
    private double price;
    private Currency currency;

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

    public enum Currency {
        EU("Euro"), RU("Rubles"), USD("dollars");

        String name;

        Currency(String name) {
            this.name = name;
        }
    }
}
