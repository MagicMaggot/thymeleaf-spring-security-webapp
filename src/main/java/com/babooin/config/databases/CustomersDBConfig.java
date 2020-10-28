package com.babooin.config.databases;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:database.config.properties")
@EnableJpaRepositories(basePackages = "${customers.jpa.repositories}", 
						entityManagerFactoryRef = "customersEntityManagerFactory",
						transactionManagerRef = "customersTransactionManager")
public class CustomersDBConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private HibernateProperties hibernateProperties;
	
	@Bean
	@ConfigurationProperties(prefix = "customers.datasource")
	@Primary
	public DataSource customersDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean customersEntityManagerFactory(DataSource dataSource,
								EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = entityManagerFactoryBuilder.dataSource(dataSource).build();
		
		entityManagerFactory.setPackagesToScan(env.getProperty("customers.jpa.packagesToScan"));
		
		entityManagerFactory.setJpaProperties(hibernateProperties);
		
		return entityManagerFactory;
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager customersTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		
		return new JpaTransactionManager(entityManagerFactory.getObject());
		
	}

}
