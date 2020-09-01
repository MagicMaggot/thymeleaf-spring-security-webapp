package com.babooin.config.databases;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:database.config.properties")
public class HibernateProperties extends Properties {
	
	private String showSql = "hibernate.show_sql";
	private String hbm2ddl = "hibernate.hbm2ddl.auto";
	private String dialect = "hibernate.dialect";
	
	private static Map<String, String> propMap;
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void setProps() {
		super.setProperty(showSql, env.getProperty(showSql));
		super.setProperty(hbm2ddl, env.getProperty(hbm2ddl));
		super.setProperty(dialect, env.getProperty(dialect));
	}
	
	public Map<String, String> getMap() {
		if (propMap == null) {
			populateMap();
		}
		
		return propMap;
		
	}
	
	private void populateMap() {
		propMap = new HashMap<>();
		propMap.put(showSql, getProperty(showSql));
		propMap.put(hbm2ddl, getProperty(hbm2ddl));
		propMap.put(dialect, getProperty(dialect));
	}
}
