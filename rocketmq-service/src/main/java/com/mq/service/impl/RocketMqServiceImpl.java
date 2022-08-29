package com.mq.service.impl;

import com.feign.client.UserFeign;
import com.mq.service.RockerMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMqServiceImpl implements RockerMqService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public String hello() {
        return userFeign.hello();
    }
}
