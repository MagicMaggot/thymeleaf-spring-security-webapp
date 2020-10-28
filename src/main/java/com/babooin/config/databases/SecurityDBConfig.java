package com.babooin.config.databases;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:database.config.properties")
@EnableJpaRepositories(basePackages = "${sec.jpa.repositories}",
						entityManagerFactoryRef = "securityEntityManagerFactory",
						transactionManagerRef = "securityTransactionManager")
public class SecurityDBConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private HibernateProperties hibernateProperties;
	
	@Bean
	@ConfigurationProperties(prefix = "sec.datasource")
	public DataSource secDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(EntityManagerFactoryBuilder builder, 
					@Qualifier("secDataSource") DataSource dataSource) {
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = 
				builder.dataSource(dataSource).packages(env.getProperty("sec.jpa.packagesToScan")).properties(hibernateProperties.getMap()).build();
		
		return entityManagerFactory;
	}
	
	@Bean
	public PlatformTransactionManager securityTransactionManager(
			@Qualifier("securityEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
		return new JpaTransactionManager(factory.getObject());
	}

}
