package com.exchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * exchange-service对应的Nacos配置文件
 * 实现热更新
 * createBy：NanMing
 * createTime：2022/8/25
 */
@Component
@Data
@ConfigurationProperties(prefix = "exchangeconfig")
public class ExchangeProperties {

    //时间格式
    private String dateformat;

}