package com.inshop;

import com.inshop.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by savetisyan on 06/09/15.
 */
@Configuration
@ComponentScan("com.inshop")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class HibernateConfig {
    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .addAnnotatedClasses(
                        AdditionalField.class,
                        Cart.class,
                        Category.class,
                        Customer.class,
                        DeliveryType.class,
                        Order.class,
                        OrderDelivery.class,
                        Product.class,
                        ProductPackage.class,
                        Shop.class,
                        ShopDelivery.class,
                        Status.class,
                        Theme.class,
                        User.class)
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                //.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect") TODO: uncomment when use postgres
                .buildSessionFactory();
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
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory());
        return hibernateTransactionManager;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

