package br.com.itau.tasks.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class sets the context for Prometheus in the application, so you can see the application metrics on Prometheus
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
@Configuration
public class PrometheusConfiguration {
	
	private static final String APPLICATION_LABEL = "application";
	private static final String HOSTNAME_LABEL = "hostname";
	
	private final String applicationName;
	private final String hostname;
	
	/**
	 * Constructor for {@link PrometheusConfiguration}
	 *
	 * @param applicationName
	 * 		the spring application name
	 * @param hostname
	 * 		the value of name of current host
	 */
	public PrometheusConfiguration(@Value("${spring.application.name}") final String applicationName, @Value("${hostname}") final String hostname) {
		this.applicationName = applicationName;
		this.hostname = hostname;
	}
	
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags(APPLICATION_LABEL, applicationName)
				.commonTags(HOSTNAME_LABEL, hostname);
	}
	
}
