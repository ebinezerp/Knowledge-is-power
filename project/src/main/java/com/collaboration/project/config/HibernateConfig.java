package com.collaboration.project.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan("com.collaboration.project")
@EnableTransactionManagement
public class HibernateConfig {
	static private String dialect ="org.hibernate.dialect.Oracle10gDialect";

	
	@Bean
	public DataSource getDataSource()
	{
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("second");
		dataSource.setPassword("system");
		return dataSource;
	}

	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(dataSource);
		localSessionFactoryBuilder.scanPackages("com.collaboration.project.model");
		System.out.println("hiiii");
		localSessionFactoryBuilder.addProperties(getProperties());
		System.out.println("hello");
		return localSessionFactoryBuilder.buildSessionFactory();
		
	}
	
	public Properties getProperties()
	{
		Properties properties=new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("transaction");
		System.out.println("Session factory"+sessionFactory);
		HibernateTransactionManager txmgr=null;
	
		 txmgr=new HibernateTransactionManager(sessionFactory);
		System.out.println("transaction M:::::"+txmgr);
		
		
		return txmgr;
		
	}
	  @Bean(name="multipartResolver") 
	    public CommonsMultipartResolver getResolver() throws IOException{
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	         
	        //Set the maximum allowed size (in bytes) for each individual file.
	        resolver.setMaxUploadSizePerFile(5242880);//5MB
	         
	        //You may also set other available properties.
	         
	        return resolver;
	    }
}
