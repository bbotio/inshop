package com.inshop.dao.impl;

import com.inshop.dao.UserDao;
import com.inshop.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by savetisyan on 16/09/15.
 */
@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getById(String userId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User u where u.userId=:userId")
                .setParameter("userId", userId);

        List result = query.list();
        if (!result.isEmpty()) {
            return (User) result.get(0);
        }

        return null;
    }
}