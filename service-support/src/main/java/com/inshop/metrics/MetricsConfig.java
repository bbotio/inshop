package com.inshop.metrics;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aleksei Kornev
 */
@Configuration
public class MetricsConfig {

	@Bean
	public MetricRegistry metricsRegistry() {
		return new MetricRegistry();
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public JmxReporter jmxReporter() {
		return JmxReporter.forRegistry(metricsRegistry()).build();
	}

}
