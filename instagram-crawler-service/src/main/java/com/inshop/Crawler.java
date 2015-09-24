package com.inshop;

import com.inshop.entity.Product;

import java.util.List;

/**
 * Created by savetisyan on 16/09/15.
 */
public interface Crawler<T> {
    List<T> getItems();

    List<List<Product>> getShopItems(Filter<T> filter);
}
