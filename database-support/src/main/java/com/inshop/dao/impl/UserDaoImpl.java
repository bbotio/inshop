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
    @Override
    public User getByInstagramUserId(final String instagramUserId) {
        Query query = getCurrentSession()
                .createQuery("from User u where u.instagramUserId=:instagramUserId")
                .setParameter("instagramUserId", instagramUserId);
        return (User) query.uniqueResult();
    }

    @Override
    public User getByEmail(String email) {
        Query query = getCurrentSession()
                .createQuery("from User u where u.email=:email")
                .setParameter("email", email);
        return (User) query.uniqueResult();
    }
}