package com.inshop.config;

import com.inshop.dao.ThemeDao;
import com.inshop.entity.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import({CommonConfig.class, WebServerConfig.class, HibernateConfig.class,
        InstagramConfig.class, CrawlerConfig.class, PayPalConfig.class})
public class InshopServiceConfig {

    @Autowired
    private ThemeDao themeDao;

    @Bean
    public Theme initThemes() {
        final Theme theme = new Theme();
        theme.setName("fashion-shop");
        themeDao.save(theme);
        return theme;
    }

}
