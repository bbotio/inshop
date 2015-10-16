package com.inshop.dao.impl;

import com.inshop.dao.DeliveryTypeDao;
import com.inshop.entity.DeliveryType;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by savetisyan on 16/10/15.
 */
@Repository
@Transactional
public class DeliveryTypeDaoImpl extends GenericDaoImpl<DeliveryType> implements DeliveryTypeDao {

    @Override
    public DeliveryType getByName(String name) {
        final Query query = getCurrentSession().createQuery("from DeliveryType d where d.name=:name");
        query.setParameter("name", name);

        return (DeliveryType) query.uniqueResult();
    }
}
