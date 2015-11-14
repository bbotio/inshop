package com.inshop.dao;

import com.inshop.entity.Product;
import com.inshop.entity.Shop;

import java.util.List;

/**
 * Created by savetisyan on 08/11/15
 */
public interface ProductDao extends GenericDao<Product> {
    List<Product> getProductsByShop(Shop shop);

    List<Product> getUniqueProductsByShop(Shop shop);

    Product getByImageUrl(String imageUrl);
}
