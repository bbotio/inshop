package com.inshop.dao.impl;

import com.inshop.dao.ProductDao;
import com.inshop.entity.Product;
import com.inshop.entity.Shop;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Product> getUniqueProductsByShop(Shop shop) {
        List<Integer> packageIds = getCurrentSession()
                .createQuery("select p.productPackage.id from Product p where p.shop = :shop group by p.productPackage")
                .setParameter("shop", shop)
                .list();

        return packageIds.stream().map(this::getByPackageId).collect(Collectors.toList());
    }

    @Override
    public Product getByImageUrl(String imageUrl) {
        return (Product) getCurrentSession().createQuery("from Product p where p.imageUrl = :imageUrl")
                .setParameter("imageUrl", imageUrl)
                .uniqueResult();
    }

    @Override
    public Product getByPackageId(Integer packageId) {
        return findAny(getCurrentSession()
                .createQuery("from Product where productPackage.id = :id")
                .setParameter("id", packageId));
    }
}
