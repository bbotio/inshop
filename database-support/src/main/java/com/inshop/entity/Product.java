package com.inshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Base class for product.
 * <p/>
 * Each product has a name & description, price (in RUB, USD, EUR), seller-defined tags (#t-shirt, #xxl and etc),
 * url to product image in seller's instagram and product creation date.
 * <p/>
 * Pay attention, that our application will fetch only products with tag #instore.
 * <p/>
 * Also class has references to shop, in which product is sold, and product package. (@see {@link ProductPackage})
 * <p/>
 * Created by savetisyan on 05/09/15.
 */
@Entity
public class Product implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price price;

    @Column(name = "tags")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> tags;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_package_id")
    private ProductPackage productPackage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "additional_field_id")
    private Set<AdditionalField> additionalFields;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Category> categories;

    /**
     * This field is used only for UI purposes
     */
    @Transient
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", shop=" + shop +
                ", productPackage=" + productPackage +
                ", additionalFields=" + additionalFields +
                '}';
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Product clone() throws CloneNotSupportedException {
        Product product = (Product) super.clone();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setTags(tags);
        product.setImageUrl(imageUrl);
        product.setDate(date);
        product.setShop(shop);
        product.setProductPackage(productPackage);
        product.setAdditionalFields(additionalFields);
        product.setCategories(categories);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (tags != null ? !tags.equals(product.tags) : product.tags != null) return false;
        if (imageUrl != null ? !imageUrl.equals(product.imageUrl) : product.imageUrl != null) return false;
        if (date != null ? !date.equals(product.date) : product.date != null) return false;
        if (shop != null ? !shop.equals(product.shop) : product.shop != null) return false;
        if (additionalFields != null ? !additionalFields.equals(product.additionalFields) : product.additionalFields != null)
            return false;
        return !(categories != null ? !categories.equals(product.categories) : product.categories != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (shop != null ? shop.hashCode() : 0);
        result = 31 * result + (productPackage != null ? productPackage.hashCode() : 0);
        result = 31 * result + (additionalFields != null ? additionalFields.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }
}
