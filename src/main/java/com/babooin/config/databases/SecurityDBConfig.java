package com.babooin.config.databases;

import java.util.HashMap;
import java.util.Map;

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
	
	@Bean
	@ConfigurationProperties(prefix = "sec.datasource")
	public DataSource secDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory(EntityManagerFactoryBuilder builder, 
					@Qualifier("secDataSource") DataSource dataSource) {
		
		Map<String, String> properties = new HashMap<>();
		
		String showSql = "hibernate.show_sql";
		String hbm2ddl = "hibernate.hbm2ddl.auto";
		
		properties.put(showSql, env.getProperty(showSql));
		properties.put(hbm2ddl, env.getProperty(hbm2ddl));
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = 
				builder.dataSource(dataSource).packages(env.getProperty("sec.jpa.packagesToScan")).properties(properties).build();
		
		return entityManagerFactory;
	}
	
	@Bean
	public PlatformTransactionManager securityTransactionManager(
			@Qualifier("securityEntityManagerFactory") LocalContainerEntityManagerFactoryBean factory) {
		return new JpaTransactionManager(factory.getObject());
	}

}
