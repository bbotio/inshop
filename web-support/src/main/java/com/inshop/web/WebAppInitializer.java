package com.inshop.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.List;

/**
 * Created by akornev on 06/09/15.
 */
public class WebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer implements
        ServletContextListener {

    private List<Filter> filters;

    public WebAppInitializer(final List<Filter> filters) {
        this.filters = filters;
    }

    /**
     * See {@link AbstractAnnotationConfigDispatcherServletInitializer}.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Set the application context for the Spring MVC web tier.
     *
     * @See {@link AbstractAnnotationConfigDispatcherServletInitializer}
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfiguration.class};
    }

    /**
     * Map the Spring MVC servlet as the root.
     *
     * @See {@link AbstractAnnotationConfigDispatcherServletInitializer}
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(
            ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            onStartup(servletContextEvent.getServletContext());
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    /**
     * Overrided to squelch a meaningless log message when embedded.
     */
    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
    }

    @Override
    protected Filter[] getServletFilters() {
        return filters.toArray(new Filter[]{});
    }
}
