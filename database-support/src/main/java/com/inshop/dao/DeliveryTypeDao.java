package com.inshop.dao;

import com.inshop.entity.DeliveryType;

/**
 * Created by savetisyan on 16/10/15.
 */
public interface DeliveryTypeDao extends GenericDao<DeliveryType> {
    DeliveryType getByName(String name);
}
