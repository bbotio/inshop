package com.inshop.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by akornev on 06/09/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(useDefaultFilters = false, basePackages = {"com.inshop"},
        includeFilters = {@ComponentScan.Filter(Controller.class)})
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**").addResourceLocations("images/");
        registry.addResourceHandler("css/**").addResourceLocations("css/");
        registry.addResourceHandler("scripts/**").addResourceLocations("scripts/");
    }

    @Bean()
    public FreeMarkerConfig getFreeMarkerConfig() {
        FreeMarkerConfigurer conf = new FreeMarkerConfigurer();
        conf.setTemplateLoaderPaths("templates");
        conf.setDefaultEncoding("UTF-8");
        return conf;
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        freeMarkerViewResolver.setExposeSpringMacroHelpers(true);
        return freeMarkerViewResolver;
    }
}
