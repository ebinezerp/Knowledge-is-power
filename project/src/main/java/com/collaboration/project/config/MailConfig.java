package com.collaboration.project.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration 
public class MailConfig {

    
    private static String host="smtp.gmail.com";

   
    private static Integer port=587;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

       sender.setHost(host);
       sender.setUsername("ebinezer.p87@gmail.com");
       sender.setPassword("M@nojTkumar");
       sender.setJavaMailProperties(getProperties());

        return sender;
    }
    
    public Properties getProperties()
    {
    	Properties props=new Properties();
    	props.put("mail.smtp.auth", true);
    	props.put("mail.smtp.socketFactory.port", "465");
    	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.port", "465");
    	return props;
    }
     
}
