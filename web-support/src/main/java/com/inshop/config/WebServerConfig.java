package com.inshop.config;

import com.inshop.web.WebAppInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;


import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {"com.inshop"},
        includeFilters = {@ComponentScan.Filter(WebFilter.class)})
public class WebServerConfig {

    @Value("${web_host:0.0.0.0}")
    private String host;

    @Value("${web_port:8080}")
    private int port;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired(required = false)
    private List<javax.servlet.Filter> filters = new ArrayList<>();

    @Bean
    public WebAppContext jettyWebAppContext() throws IOException {

        WebAppContext ctx = new WebAppContext();
        ctx.setContextPath("/");
        ctx.setResourceBase(new ClassPathResource("webapp").getURI().toString());

        /* Disable directory listings if no index.html is found. */
        ctx.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed",
                "false");


        /* Create the root web application context and set it as a servlet
         * attribute so the dispatcher servlet can find it. */
        GenericWebApplicationContext webApplicationContext =
                new GenericWebApplicationContext();
        webApplicationContext.setParent(applicationContext);
        webApplicationContext.refresh();
        ctx.setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                webApplicationContext);


        ctx.addEventListener(new WebAppInitializer(filters));

        return ctx;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server server() throws IOException {

        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(host);

        server.addConnector(connector);

        server.setHandler(jettyWebAppContext());

        return server;
    }
}
