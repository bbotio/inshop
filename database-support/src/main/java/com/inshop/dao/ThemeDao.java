package com.inshop.dao;

import com.inshop.entity.Theme;

/**
 * Created by savetisyan on 16/09/15.
 */
public interface ThemeDao extends GenericDao<Theme> {

    Theme getThemeByName(final String name);
}
