package com.feign.controller;

import com.feign.client.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/hello")
    public String hello(){
        return userFeign.hello();
    }

}
