package com.inshop.config;

import com.inshop.controllers.LoginController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 */
@Configuration
@Import({CommonConfig.class, WebServerConfig.class, HibernateConfig.class, InstagramConfig.class})
public class InshopServiceConfig {

}
