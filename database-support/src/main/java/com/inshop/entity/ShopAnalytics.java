package com.inshop.entity;

import javax.persistence.*;

/**
 * Created by akornev on 14/10/15.
 */
@Entity
public class ShopAnalytics {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "google_analytics_id")
    private String googleAnalyticsId;

    @Column(name = "tags")
    private String tags;

    @OneToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoogleAnalyticsId() {
        return googleAnalyticsId;
    }

    public void setGoogleAnalyticsId(String googleAnalyticsId) {
        this.googleAnalyticsId = googleAnalyticsId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopAnalytics that = (ShopAnalytics) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
