package com.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name="userservice",path = "/user")
public interface UserFeign {


//    @GetMapping("/user/{id}")
//    JSObject findById(@PathVariable("id") Long id);

    @GetMapping("/hello")
    String hello();


}