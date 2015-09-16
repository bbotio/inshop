package com.inshop.dao.impl;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by savetisyan on 16/09/15.
 */
@Repository
@Transactional
public class ShopDaoImpl extends GenericDaoImpl<Shop> implements ShopDao {

}
