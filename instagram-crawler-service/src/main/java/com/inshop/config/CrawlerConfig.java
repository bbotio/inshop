package com.inshop.config;

import com.inshop.InstagramScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by savetisyan on 23/09/15.
 */
@Configuration
public class CrawlerConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public InstagramScheduler endpoint() {
        return new InstagramScheduler();
    }
}
