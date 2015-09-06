package com.inshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * Created by savetisyan on 06/09/15.
 */
@Configuration
@PropertySource("classpath:config.properties")
public class HibernateProperties {
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dll;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${test.hibernate.hbm2ddl.auto}")
    private String hbm2dllTest;

    @Value("${test.hibernate.dialect}")
    private String dialectTest;


    @Bean(name = "hibernateProperty")
    @Profile("dev")
    public Properties devHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2dll);
        properties.setProperty("hibernate.dialect", dialect);
        return properties;
    }

    @Bean(name = "hibernateProperty")
    @Profile("test")
    public Properties testHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2dllTest);
        properties.setProperty("hibernate.dialect", dialectTest);
        return properties;
    }
}
