package com.inshop.dao.impl;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by savetisyan on 16/09/15.
 */
@Repository
@Transactional
public class ShopDaoImpl extends GenericDaoImpl<Shop> implements ShopDao {

    @Override
    public Shop getUserShop(final User owner) {
        final Query query = getCurrentSession().createQuery("from Shop s where s.owner=:owner");
        query.setParameter("owner", owner);

        return (Shop) query.uniqueResult();
    }

    @Override
    public Shop getShopByDomain(final String domain) {
        final Query query = getCurrentSession().createQuery("from Shop s where s.domain=:domain");
        query.setParameter("domain", domain);

        return (Shop) query.uniqueResult();
    }
}
