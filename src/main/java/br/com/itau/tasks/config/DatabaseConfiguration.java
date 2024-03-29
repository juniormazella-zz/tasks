package br.com.itau.tasks.config;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.domain.task.TaskRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class configures the application's connection to the database and also makes other settings more specific to the JPA
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@Configuration
@EntityScan(basePackageClasses = {Task.class})
@EnableJpaRepositories(basePackageClasses = {TaskRepository.class})
public class DatabaseConfiguration extends JpaBaseConfiguration {
	
	/**
	 * Constructor for {@link DatabaseConfiguration}
	 *
	 * @param dataSource
	 * 		object containing the database connection
	 * @param properties
	 * 		the properties of connection
	 * @param jtaTransactionManager
	 * 		is a transaction manager of Spring
	 * @param transactionManagerCustomizers
	 * 		is the transaction customizer of Spring
	 */
	public DatabaseConfiguration(final DataSource dataSource, final JpaProperties properties,
								 final ObjectProvider<JtaTransactionManager> jtaTransactionManager,
								 final ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
	}
	
	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}
	
	@Override
	protected Map<String, Object> getVendorProperties() {
		final Map<String, Object> vendorProperties = new HashMap<>();
		vendorProperties.put("eclipselink.cache.shared.default", Boolean.TRUE.toString());
		vendorProperties.put("eclipselink.cache-usage", "CheckCacheThenDatabase");
		vendorProperties.put("eclipselink.jdbc.cache-statements", Boolean.FALSE.toString());
		vendorProperties.put("eclipselink.orm.throw.exceptions", Boolean.TRUE.toString());
		vendorProperties.put("eclipselink.weaving", "static");
		vendorProperties.put("eclipselink.query-results-cache", Boolean.TRUE.toString());
		vendorProperties.put("eclipselink.persistence-context.close-on-commit", Boolean.TRUE.toString());
		vendorProperties.put("eclipselink.persistence-context.flush-mode", "commit");
		vendorProperties.put("eclipselink.persistence-context.persist-on-commit", Boolean.FALSE.toString());
		vendorProperties.put("eclipselink.ddl-generation", "none");
		return Collections.unmodifiableMap(vendorProperties);
	}
	
}
