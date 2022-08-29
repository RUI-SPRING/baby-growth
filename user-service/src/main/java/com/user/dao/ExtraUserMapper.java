package com.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.ExtraUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * ExtraUser-service数据交互层
 * createBy：NanMing
 * createTime：2022/8/29
 */
@Mapper
public interface ExtraUserMapper extends BaseMapper<ExtraUser> {

}