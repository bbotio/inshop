package com.inshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import({CommonConfig.class, WebServerConfig.class, HibernateConfig.class,
        InstagramConfig.class, CrawlerConfig.class, PayPalConfig.class})
public class InshopServiceConfig {

}
