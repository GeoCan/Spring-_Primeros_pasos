package com.plenumsoft.vuzee.config;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "mysqlEntityManager", 
		transactionManagerRef = "mysqlTransactionManager", 
  basePackages = { "com.plenumsoft.vuzee.mysql.repositories" }
)
public class MySqlConfig {
	
	/**
	 * MySQL datasource definition.
	 * 
	 * @return datasource.
	 */
	@Bean
	@ConfigurationProperties(prefix = "mysql.spring.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder
					.create()
					.build();
	}

	/**
	 * Entity manager definition. 
	 *  
	 * @param builder an EntityManagerFactoryBuilder.
	 * @return LocalContainerEntityManagerFactoryBean.
	 */
	@Bean(name = "mysqlEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(mysqlDataSource())
					.properties(hibernateProperties())
					.packages("com.plenumsoft.vuzee.mysql.entities")
					.persistenceUnit("mysqlPU")
					.build();
	}

	/**
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
											.collect(Collectors.toMap(
														e -> e.getKey().toString(),
														e -> e.getValue())
													);
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
	}
	
	
	
	
	
	
	
//	@Bean(name = "mysqlDataSource")
//	  @ConfigurationProperties(prefix = "mysql-local")
//	  public DataSource dataSource() {
//	    return DataSourceBuilder.create().build();
//	  }
//	  
//	  @Bean(name = "mysqlEntityManagerFactory")
//	  public LocalContainerEntityManagerFactoryBean 
//	  mysqlEntityManagerFactory(
//	    EntityManagerFactoryBuilder builder,
//	    @Qualifier("mysqlDataSource") DataSource dataSource
//	  ) {
//	    return
//	      builder
//	        .dataSource(dataSource)
//	        .packages("com.plenumsoft.vuzee.mysql.entities")
//	        .persistenceUnit("mysql")
//	        .build();
//	  }
//	  @Bean(name = "mysqlTransactionManager")
//	  public PlatformTransactionManager mysqlTransactionManager(
//	    @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory
//	    mysqlEntityManagerFactory
//	  ) {
//	    return new JpaTransactionManager(mysqlEntityManagerFactory);
//	  }
}
