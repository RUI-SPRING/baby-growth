package com.feign.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * Feign配置类
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }

    @Bean
    public Request.Options options(){
        return new Request.Options(5000,5000);
    }

}
