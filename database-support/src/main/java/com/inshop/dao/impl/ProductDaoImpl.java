package com.inshop.dao.impl;

import com.inshop.dao.ProductDao;
import com.inshop.entity.Product;
import com.inshop.entity.Shop;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by savetisyan on 08/11/15
 */
@Repository
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    @Override
    public List<Product> getProductsByShop(Shop shop) {
        return (List<Product>) getCurrentSession().createQuery("from Product p where p.shop = :shop")
                .setParameter("shop", shop)
                .list();
    }

    @Override
    public Product getByImageUrl(String imageUrl) {
        return (Product) getCurrentSession().createQuery("from Product p where p.imageUrl = :imageUrl")
                .setParameter("imageUrl", imageUrl)
                .uniqueResult();
    }
}
