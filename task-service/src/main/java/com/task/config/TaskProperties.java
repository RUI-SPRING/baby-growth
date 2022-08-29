package com.task.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * record-service对应的Nacos配置文件
 * 实现热更新
 * createBy：NanMing
 * createTime：2022/8/25
 */
@Component
@Data
@ConfigurationProperties(prefix = "taskconfig")
public class TaskProperties {

    //时间格式
    private String dateformat;

}