package com.babooin.config.databases;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:database.config.properties")
@EnableJpaRepositories(basePackages = "${employees.jpa.repositories}",
						entityManagerFactoryRef = "employeesEntityManagerFactory",
						transactionManagerRef = "employeesTransactionManger")
public class EmployeesDBConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private HibernateProperties hibernateProperties;
	
	@Bean
	@ConfigurationProperties(prefix = "employees.datasource")
	public DataSource employeesDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean employeesEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		entityManagerFactory.setDataSource(employeesDataSource());
		entityManagerFactory.setPackagesToScan(env.getProperty("employees.jpa.packagesToScan"));		
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(hibernateProperties);
		
		return entityManagerFactory;		
	}
	
	@Bean
	public PlatformTransactionManager employeesTransactionManger() {
		return new JpaTransactionManager(employeesEntityManagerFactory().getObject());
	}

}
