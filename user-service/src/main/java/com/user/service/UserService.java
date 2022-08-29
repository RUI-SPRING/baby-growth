package com.user.service;

import com.user.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User-service业务层
 * createBy：NanMing
 * createTime：2022/8/25
 */
public interface UserService {

    User findById(Long id);

    User findByUserId(Long userId);

    List<User> queryAll();

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);

    int deleteByUserId(Long userId);

    String hello();
}
