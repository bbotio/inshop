package com.inshop.dao;

import com.inshop.entity.Shop;
import com.inshop.entity.User;

/**
 * Created by savetisyan on 16/09/15.
 */
public interface ShopDao extends GenericDao<Shop> {

    Shop getUserShop(User user);

    Shop getShopByDomain(String domain);
}
