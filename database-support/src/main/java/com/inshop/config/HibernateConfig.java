package com.inshop.config;

import com.inshop.dao.GenericDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by savetisyan on 06/09/15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = GenericDao.class)
public class HibernateConfig {
    @Value("${db.driver:org.postgresql.Driver}")
    private String dbDriver;

    @Value("${db.url:jdbc:postgresql://localhost:15432/inshop}")
    private String url;

    @Value("${db.username:admin}")
    private String username;

    @Value("${db.password:admin}")
    private String password;

    @Value("${hibernate.hbm2ddl.auto:create-drop}")
    private String hbm2dll;

    @Value("${hibernate.dialect:org.hibernate.dialect.PostgreSQL94Dialect}")
    private String dialect;

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2dll);
        properties.setProperty("hibernate.dialect", dialect);
        return properties;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.inshop.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setImplicitNamingStrategy(new PrefixTableNameNamingStrategy("inshop_"));
        sessionFactory.afterPropertiesSet();

        return sessionFactory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory());
        return hibernateTransactionManager;
    }
}

