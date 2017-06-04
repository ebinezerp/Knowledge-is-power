package com.collaboration.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;;
@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"com.collaboration.project"})
public class MvcConfig extends WebMvcConfigurerAdapter {

    // Configuration for view resolver
    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/views/");
        viewResolve.setSuffix(".jsp");

        return viewResolve;
    }
    
    @Bean
    public WebContentInterceptor webContentInterceptor() {
      WebContentInterceptor interceptor = new WebContentInterceptor();
      interceptor.setCacheSeconds(0);
      interceptor.setUseExpiresHeader(true);
      interceptor.setUseCacheControlHeader(true);
      interceptor.setUseCacheControlNoStore(true);

      return interceptor;
    }
    // Use the DefaultServletHandlerConfigurer 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
      configurer.enable();
    }  
	
}
