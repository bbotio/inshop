package com.inshop.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by savetisyan on 06/09/15.
 */
public interface GenericDao {
    <T> void save(T t);

    <T, ID extends Serializable> T get(Class<T> type, ID id);

    <T> void update(T t);

    <T> void remove(T t);

    <T> List<T> findAll(Class<T> type);
}
