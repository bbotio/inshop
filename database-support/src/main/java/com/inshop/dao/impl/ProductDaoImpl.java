package com.inshop.dao.impl;

import com.inshop.dao.ProductDao;
import com.inshop.entity.Product;
import com.inshop.entity.Shop;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
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
    public List<Product> getUniqueProductsByShop(Shop shop) {
        ProjectionList properties = Projections.projectionList()
                .add(Projections.groupProperty("productPackage"))
                .add(Projections.min("id"), "id");

        DetachedCriteria msgFromCriteria = DetachedCriteria.forClass(Product.class)
                .setProjection(properties);

        return getCurrentSession().createCriteria(Product.class)
                .add(Subqueries.propertiesIn(new String[]{"productPackage", "id"}, msgFromCriteria))
                .setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE)
                .list();
    }

    @Override
    public Product getByImageUrl(String imageUrl) {
        return (Product) getCurrentSession().createQuery("from Product p where p.imageUrl = :imageUrl")
                .setParameter("imageUrl", imageUrl)
                .uniqueResult();
    }
}
