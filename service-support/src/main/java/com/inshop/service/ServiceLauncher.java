package com.inshop.service;


import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * @author Aleksei Kornev
 */
public class ServiceLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLauncher.class);

    public static final String CONFIG_PACKAGE = "com.inshop.config";
    public static final String DEFAULT_CONFIG_PATH = "/etc/inshop/";
    public static final String DEFAULT_PROPERTY_SOURCE = "serviceDefaults";
    public static final String RUNTIME_PROPERTY_SOURCE = "serviceRuntime";
    public static final String EXTERNAL_PROPERTY_SOURCE = "externalConfig";

    protected static AnnotationConfigApplicationContext ctx;

    public static void main(String[] args) {
        setupExceptionsHandler();
        setupLoggers();
        try {
            ctx = new AnnotationConfigApplicationContext();
            ctx.register(CommonConfig.class);
            ctx.scan(CONFIG_PACKAGE);

            configure(ctx);
            daemonize(new Runnable() {
                @Override
                public void run() {
                    ctx.close();
                }
            });

            ctx.refresh();

        } catch (Throwable e) {
            LOGGER.error("Startup failed", e);
        }
    }

    public static void setupLoggers() {
        // Optionally remove existing handlers attached to j.u.l root logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();

        // add SLF4JBridgeHandler to j.u.l's root logger
        SLF4JBridgeHandler.install();
    }

    public static void configure(ConfigurableApplicationContext context) throws IOException {
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();

        // Add config.properties to property sources
        PropertySource<Map<String, Object>> serviceDefaults = new ResourcePropertySource(
                DEFAULT_PROPERTY_SOURCE, context.getResource("classpath:config.properties"));
        propertySources.addLast(serviceDefaults);

        // Calculate file name for external configuration
        Resource externalResource;
        if (context.getEnvironment().containsProperty("service.config_file")) {
            externalResource = context.getResource("file:" + context.getEnvironment().getProperty("service.config_file"));
        } else {
            String serviceName = context.getEnvironment().getProperty("service_name");
            externalResource = context.getResource("file:" + DEFAULT_CONFIG_PATH + serviceName + ".properties");
            LOGGER.warn("Configuration file not specified, using default of \"{}\"",
                    externalResource.getFile().getPath());
        }

        // Add external configuration file to property sources
        if (externalResource.exists() && externalResource.isReadable()) {
            PropertySource<Map<String, Object>> externalConfig = new ResourcePropertySource(
                    EXTERNAL_PROPERTY_SOURCE, externalResource);
            propertySources.addBefore(DEFAULT_PROPERTY_SOURCE, externalConfig);
        } else {
            LOGGER.warn("Configuration file \"{}\" not found, running with default settings",
                    externalResource.getFile().getPath());
        }

        // Add runtime service properties to property sources
        HashMap<String, Object> runtimeConfig = new HashMap<String, Object>() {{
            put("service.startup_time", String.valueOf(ManagementFactory.getRuntimeMXBean().getStartTime()));
        }};
        propertySources.addFirst(new MapPropertySource(RUNTIME_PROPERTY_SOURCE, runtimeConfig));
    }

    public static void daemonize(Runnable shutdownHook) {
        final File pid = getPidFile();
        if (pid != null) {
            pid.deleteOnExit();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook));
    }

    private static File getPidFile() {
        final String pidFileName = System.getProperty("daemon.pidfile");
        return pidFileName == null ? null : new File(pidFileName);
    }

    private static void setupExceptionsHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.error("Uncaught exception in {}", t.getName(), e);
            }
        });
    }

}
