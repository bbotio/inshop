package com.inshop.dao;

import com.inshop.entity.User;

/**
 * Created by savetisyan on 16/09/15.
 */
public interface UserDao extends GenericDao<User> {
    User getById(String userId);
}