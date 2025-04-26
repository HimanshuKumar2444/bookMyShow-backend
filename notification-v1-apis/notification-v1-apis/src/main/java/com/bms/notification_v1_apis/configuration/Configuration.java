package com.bms.notification_v1_apis.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class Configuration {
//    @Bean
//    public JavaMailSender getJavaMailSender(){
//        return new JavaMailSenderImpl();
//    }

    @Bean
public JavaMailSender getJavaMailSender(){
JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
mailSender.setHost("smtp.gmail.com");
mailSender.setPort(587);
mailSender.setUsername("himanshukumar290071@gmail.com");
mailSender.setPassword("jcrj piix jzrs oumg");
Properties props = mailSender.getJavaMailProperties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
return mailSender;
}

    @Bean
    public TemplateEngine getTemplateEngine(){
        return  new TemplateEngine();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
