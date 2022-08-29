package com.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User-service数据交互层
 * createBy：NanMing
 * createTime：2022/8/25
 */

//表明这是一个Mapper，也可以在启动类上加上包扫描
//Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
@Mapper
public interface UserMapper extends BaseMapper<User> {

}