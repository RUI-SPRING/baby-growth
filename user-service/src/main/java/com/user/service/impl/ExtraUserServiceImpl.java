package com.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.user.dao.ExtraUserMapper;
import com.user.pojo.ExtraUser;
import com.user.pojo.User;
import com.user.service.ExtraUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraUserServiceImpl implements ExtraUserService {

    @Autowired
    private ExtraUserMapper extraUserMapper;

    @Override
    public ExtraUser findById(Long id) {
        return extraUserMapper.selectById(id);
    }

    @Override
    public ExtraUser findByUserId(Long userId) {
        QueryWrapper<ExtraUser> wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.eq("user_id", userId);// 设置name的条件
        return extraUserMapper.selectOne(wrapper);
    }

    @Override
    public List<ExtraUser> queryAll() {
        return extraUserMapper.selectList(null);
    }

    @Override
    public int addExtraUser(ExtraUser extraUser) {
        return extraUserMapper.insert(extraUser);
    }

    @Override
    public int updateExtraUser(ExtraUser extraUser) {
        return extraUserMapper.updateById(extraUser);
    }

    @Override
    public int deleteExtraUser(Long id) {
        return extraUserMapper.deleteById(id);
    }

    @Override
    public int deleteByUserId(Long userId) {
        QueryWrapper<ExtraUser> wrapper = new QueryWrapper<>();// 构建一个查询的wrapper
        wrapper.eq("user_id", userId);// 设置name的条件
        return extraUserMapper.delete(wrapper);
    }
}
