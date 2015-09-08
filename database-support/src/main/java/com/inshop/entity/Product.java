package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Base class for product.
 *
 * Each product has a name & description, price (in RUB, USD, EUR), seller-defined tags (#t-shirt, #xxl and etc),
 * url to product image in seller's instagram and product creation date.
 *
 * Pay attention, that our application will fetch only products with tag #instore.
 *
 * Also class has references to shop, in which product is sold, and product package. (@see {@link ProductPackage})
 *
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "tags")
    private String tags;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "product_package_id", referencedColumnName = "id")
    private ProductPackage productPackage;

    @OneToMany
    @JoinColumn(name = "additional_field_id", referencedColumnName = "id")
    private Set<AdditionalField> additionalFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ProductPackage getProductPackage() {
        return productPackage;
    }

    public void setProductPackage(ProductPackage productPackage) {
        this.productPackage = productPackage;
    }

    public Set<AdditionalField> getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(Set<AdditionalField> additionalFields) {
        this.additionalFields = additionalFields;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags='" + tags + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", shop=" + shop +
                ", productPackage=" + productPackage +
                ", additionalFields=" + additionalFields +
                '}';
    }
}
