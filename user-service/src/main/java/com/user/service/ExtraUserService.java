package com.user.service;


import com.user.pojo.ExtraUser;
import com.user.pojo.User;

import java.util.List;

/**
 * 用户额外属性服务类
 * createBy：NanMing
 * createTime：2022/8/29
 */
public interface ExtraUserService {

    ExtraUser findById(Long id);

    ExtraUser findByUserId(Long userId);

    List<ExtraUser> queryAll();

    int addExtraUser(ExtraUser user);

    int updateExtraUser(ExtraUser user);

    int deleteExtraUser(Long id);

    int deleteByUserId(Long userId);

}
