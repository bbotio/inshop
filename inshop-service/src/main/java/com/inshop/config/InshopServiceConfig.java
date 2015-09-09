package com.inshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import({CommonConfig.class, WebServerConfig.class, HibernateConfig.class})
public class InshopServiceConfig {
}
