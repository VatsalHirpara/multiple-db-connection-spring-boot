package com.nagarro.multipledbpoc.configuration;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "subscribeVehicleManagementEntityManagerFactory", transactionManagerRef = "subscribeVehicleManagementTransactionManager", basePackages = {
		"com.nagarro.multipledbpoc.repository.subscribevehiclemanagement" })
public class SubscribeVehicleManagementConfig {

	@Bean(name = "subscribeVehicleManagementDataSource")
	@ConfigurationProperties(prefix = "spring.subscribevehiclemanagement.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "subscribeVehicleManagementEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("subscribeVehicleManagementDataSource") DataSource dataSource) {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.nagarro.multipledbpoc.domain.category")
				.persistenceUnit("CategoryMaster").build();
	}

	@Bean(name = "subscribeVehicleManagementTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("subscribeVehicleManagementEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
