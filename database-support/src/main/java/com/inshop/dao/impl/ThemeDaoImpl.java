package com.inshop.dao.impl;

import com.inshop.dao.ThemeDao;
import com.inshop.entity.Theme;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by savetisyan on 16/09/15.
 */
@Repository
@Transactional
public class ThemeDaoImpl extends GenericDaoImpl<Theme> implements ThemeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Theme getThemeByName(final String name) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Theme t where t.name=:name")
                .setParameter("name", name);
        return (Theme) query.uniqueResult();
    }
}