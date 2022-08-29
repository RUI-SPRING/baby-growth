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


    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){
        //这里要加上分页
        return Result.success(userService.queryAll());
    }

    /**
     * 根据用户表id查询用户
     * @return
     */
    @GetMapping("/findById")
    public Result findById(@RequestParam Long id){
        return Result.success(userService.findById(id));
    }

    /**
     * 根据用户唯一id查询用户
     * @return
     */
    @GetMapping("/findByUserId")
    public Result findByUserId(@RequestParam Long userId){
        return Result.success(userService.findByUserId(userId));
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        return Result.success(userService.addUser(user));
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        return Result.success(userService.updateUser(user));
    }

    /**
     * 根据用户表id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id){
        return Result.success(userService.deleteUser(id));
    }

    /**
     * 根据用户唯一id删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteByUserId")
    public Result deleteByUserId(@RequestParam Long userId){
        return Result.success(userService.deleteByUserId(userId));
    }


}
