package com.inshop;

import com.inshop.entity.Product;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created by savetisyan on 16/09/15.
 */
public interface Crawler<T> {
    List<T> getItems();

    List<Pair<Product, Integer>> getShopItems(Filter<T> filter);
}
