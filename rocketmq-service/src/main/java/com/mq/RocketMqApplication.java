package com.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients(basePackages = "com.feign.client")
public class RocketMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RocketMqApplication.class, args);
    }
}
