package com.inshop.dao.impl;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import com.inshop.entity.ShopDelivery;
import com.inshop.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by savetisyan on 16/09/15.
 */
@Repository
@Transactional
public class ShopDaoImpl extends GenericDaoImpl<Shop> implements ShopDao {

    @Override
    public Shop getUserShop(User owner) {
        return (Shop) getCurrentSession()
                .createQuery("from Shop s where s.owner=:owner")
                .setParameter("owner", owner)
                .uniqueResult();
    }

    @Override
    public Shop getShopByDomain(String domain) {
        return (Shop) getCurrentSession()
                .createQuery("from Shop s where s.domain=:domain")
                .setParameter("domain", domain)
                .uniqueResult();
    }
}
