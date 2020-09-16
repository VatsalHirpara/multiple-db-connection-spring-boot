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
@EnableJpaRepositories(entityManagerFactoryRef = "paymentEntityManagerFactory", transactionManagerRef = "paymentTransactionManager", basePackages = {
		"com.nagarro.multipledbpoc.repository.payment" })
public class PaymentDBConfing {

	@Bean(name = "paymentDataSource")
	@ConfigurationProperties(prefix = "spring.payment.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "paymentEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("paymentDataSource") DataSource dataSource) {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.nagarro.multipledbpoc.domain.payment")
				.persistenceUnit("CategoryModel").build();
	}
	@Bean(name = "paymentTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("paymentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
