package com.user.controller;

import com.feign.common.Result;
import com.user.config.UserProperties;
import com.user.pojo.User;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * User-service表现层
 * createBy：NanMing
 * createTime：2022/8/25
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/findAll")
    public Result findAll(){
        return Result.success(userService.queryAll());
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user){
        return Result.success(userService.addUser(user));
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        return Result.success(userService.updateUser(user));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id){
        return Result.success(userService.deleteUser(id));
    }

}
