package com.user.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.user.config.UserProperties;
import com.user.dao.UserMapper;
import com.user.pojo.User;
import com.user.service.UserService;
import com.user.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * User-service业务层实现类
 * createBy：NanMing
 * createTime：2022/8/25
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 根据表id查user
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据用户id查user
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(Long userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.eq("user_name", "userName");// 设置name的条件
        // 这里我们知道只有一个，所以直接用selectOne
        return userMapper.selectOne(wrapper);
    }



    /**
     * 查询全部用户
     * @return
     */
    @Override
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }


    /**
     * 新增用户
     * @return
     */
    @Override
    public int addUser(User user) {
//        User user2=User.builder()
//                .userId(Long.valueOf(1))
//                .userName("userName")
//                .sex(0)
//                .isVip(0)
//                .phone("15670606654").build();
        return userMapper.insert(user);
    }

    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }


    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteById(id);
    }


    /**
     * 测试专用
     * @return
     */
    @Override
    public String hello() {
        logger.info("--------------------------------------------");
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        logger.info("--------------------------------------------");

//        User u=new User();
//        u.setName("hello!World!!");
//        boolean isExist = redisUtil.set("user", u);
//        User data = JSONUtil.toBean(redisUtil.get("user"),User.class);
//        redisUtil.del("user");
//        boolean b = redisUtil.hasKey("user");
//        System.out.println(b);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(userProperties.getDateformat()));
    }
}
