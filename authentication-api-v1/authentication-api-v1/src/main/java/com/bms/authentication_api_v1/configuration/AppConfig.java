package com.bms.authentication_api_v1.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }
    @Bean
    public RestTemplate getRestTemplate(){return new RestTemplate();}
}
