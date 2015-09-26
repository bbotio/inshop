package com.inshop.dao.impl;

import com.inshop.dao.GenericDao;
import org.hibernate.Query;
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
public class GenericDaoImpl<T> implements GenericDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(T t) {
        getCurrentSession().save(t);
    }

    @Override
    public void persist(T t) {
        getCurrentSession().persist(t);
    }

    @Override
    public T get(Class<T> type, Integer id) {
        return getCurrentSession().get(type, id);
    }

    @Override
    public void update(T t) {
        getCurrentSession().update(t);
    }

    @Override
    public void remove(T t) {
        getCurrentSession().delete(t);
    }

    @Override
    public List<T> findAll(Class<T> type) {
        return getCurrentSession().createCriteria(type).list();
    }

    @Override
    public List<T> findMany(Query query) {
        return query.list();
    }

    @Override
    public T findOne(Query query) {
        return (T) query.uniqueResult();
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
