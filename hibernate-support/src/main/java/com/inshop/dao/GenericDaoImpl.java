package com.inshop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by savetisyan on 06/09/15.
 */
@Repository
@Transactional
public class GenericDaoImpl implements GenericDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public <T> void save(T t) {
        getCurrentSession().save(t);
    }

    public <T, ID extends Serializable> T get(Class<T> type, ID id) {
        return getCurrentSession().get(type, id);
    }

    public <T> void update(T t) {
        getCurrentSession().update(t);
    }

    public <T> void remove(T t) {
        getCurrentSession().delete(t);
    }

    public <T> List<T> findAll(Class<T> type) {
        return getCurrentSession().createCriteria(type).list();
    }
}
