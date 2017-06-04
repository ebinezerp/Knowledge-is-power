package com.collaboration.project.intializer;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.collaboration.project.config.AppConfig;
import com.collaboration.project.config.MvcConfig;
import com.collaboration.project.config.WebSocketConfig;
import com.collaboration.project.filter.CORSFilter;

public class MvcIntializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class< ?>[] { AppConfig.class, WebSocketConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		return new Filter[] {new CORSFilter(),characterEncodingFilter};
	}
	
	
	 @Override
	  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	    registration.setInitParameter("dispatchOptionsRequest", "true");
	    registration.setAsyncSupported(true);
	  }

}
