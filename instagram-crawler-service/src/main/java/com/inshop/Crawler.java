package com.inshop;

import java.util.List;

/**
 * Created by savetisyan on 16/09/15.
 */
public interface Crawler<T> {
    List<T> getItems();

    List<ParsedImage<T>> getShopItems(Filter<T> filter);
}
