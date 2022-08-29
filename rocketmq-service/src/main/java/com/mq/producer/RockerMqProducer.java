package com.mq.producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RocketMQ 生产者
 * createBy:NanMing
 * createName: 2022/8/25
 */

@Service
public class RockerMqProducer {

    private final static Logger logger = LoggerFactory.getLogger(RockerMqProducer.class);

    // topic名称 (需要使用topic全称，所以在这里对topic名称进行拼接，格式：namespace全称%topic名称)
    @Value("${rocketmq.namespace}%${rocketmq.producer1.topic}")
    private String topic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 同步发送
     *
     * @param message 消息内容
     * @param tags    订阅tags
     */
    public void syncSend(String message, String tags) {
        // springboot不支持使用header传递tags，根据要求，需要在topic后进行拼接 formats: `topicName:tags`，不拼接标识无tag
        String destination = StringUtils.isBlank(tags) ? topic : topic + ":" + tags;
        SendResult sendResult = rocketMQTemplate.syncSend(destination,
                MessageBuilder.withPayload(message)
                        .setHeader(MessageConst.PROPERTY_KEYS, "jieZiShuKey")   // 指定业务key
                        .build());
        logger.info("同步发送消息，topic为：", topic, sendResult);
    }

    /**
     * 异步发送消息
     *
     * @param message 消息内容
     * @param tags    订阅tags
     */
    public void asyncSend(String message, String tags) {
        // springboot不支持使用header传递tags，根据要求，需要在topic后进行拼接 formats: `topicName:tags`，不拼接标识无tag
        String destination = StringUtils.isBlank(tags) ? topic : topic + ":" + tags;
        rocketMQTemplate.asyncSend(destination, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                logger.info("async onSuccess SendResult= "+ var1);
            }

            @Override
            public void onException(Throwable var1) {
                logger.info("async onException Throwable= "+ var1);
            }

        });
    }


    /**
     * 单选发送消息
     *
     * @param message 消息内容
     * @param tags    订阅tags
     */
    public void onewaySend(String message, String tags) {
        // springboot不支持使用header传递tags，根据要求，需要在topic后进行拼接 formats: `topicName:tags`，不拼接标识无tag
        String destination = StringUtils.isBlank(tags) ? topic : topic + ":" + tags;
        rocketMQTemplate.sendOneWay(destination, message);
    }


    /**
     * 发送延迟消息
     *
     * @param message    消息内容
     * @param tags       订阅tags
     * @param delayLevel 消息延迟级别
     *                   延迟等级与时间对应关系：
     *                   1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h；
     *                   1    2    3     4     5    6   7    8   9    10   11   12  13   14    15    16   17   18
     */
    public void delaySend(String message, String tags, int delayLevel) {
        // springboot不支持使用header传递tags，根据要求，需要在topic后进行拼接 formats: `topicName:tags`，不拼接标识无tag
        String destination = StringUtils.isBlank(tags) ? topic : topic + ":" + tags;
        SendResult sendResult = rocketMQTemplate.syncSend(
                destination,
                MessageBuilder.withPayload(message).build(),
                5000,
                delayLevel);
        logger.info("DelaySend to topic %s sendResult=%s %n", topic, sendResult);
    }


    /**
     * 批量发送消息
     *
     * @param message 消息内容
     * @param tags    订阅tags
     */
    public void batchSend(String message, String tags) {
        String destination = StringUtils.isBlank(tags) ? topic : topic + ":" + tags;
        List<Message<String>> messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messageList.add(MessageBuilder.withPayload(message + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }
        SendResult sendResult = rocketMQTemplate.syncSend(destination, messageList, 60000);
        logger.info("DelaySend to topic %s sendResult=%s %n", topic, sendResult);
    }


}
