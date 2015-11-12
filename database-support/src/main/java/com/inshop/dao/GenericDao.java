package com.inshop.dao;

import org.hibernate.Query;
import java.util.List;

/**
 * Created by savetisyan on 06/09/15.
 */
public interface GenericDao<T> {
    void save(T t);

    void persist(T t);

    T get(Class<T> type, Integer id);

    void update(T t);

    void remove(T t);

    List<T> findAll(Class<T> type);

    List<T> findMany(Query query);

    T findOne(Query query);

    T findAny(Query query);

    void refresh(T t);
}
