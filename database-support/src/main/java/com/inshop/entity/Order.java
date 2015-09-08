package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base class for order.
 * Each order has creation {@link Order#date}, {@link Order#status} (e.g. IN_PROCESSING, PROCESSED and etc,
 * for more info @see {@link Status}. Also order has a user selected product. Each order can has only product of
 * one type. {@link Order#orderDelivery} shows user selected delivery type. {@link Order#cart} is a user's current cart.
 * {@link Order#customer} - reference to the customer, whose order it is.
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
@Table(name="order_table")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private Status status;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_delivery_id", referencedColumnName = "id")
    private OrderDelivery orderDelivery;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDelivery getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(OrderDelivery orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", product=" + product +
                ", orderDelivery=" + orderDelivery +
                ", cart=" + cart +
                ", customer=" + customer +
                '}';
    }
}
