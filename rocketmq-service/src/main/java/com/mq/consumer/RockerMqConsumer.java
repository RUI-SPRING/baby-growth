package com.mq.consumer;
import com.mq.producer.RockerMqProducer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description: 消费者1
 */
@Service
@RocketMQMessageListener(
        consumerGroup = "${rocketmq.namespace}%${rocketmq.consumer1.group}",  // 消费组，格式：namespace全称%group名称
        // 需要使用topic全称，所以进行topic名称的拼接，也可以自己设置  格式：namespace全称%topic名称
        topic = "${rocketmq.namespace}%${rocketmq.consumer1.topic}",
        selectorExpression = "${rocketmq.consumer1.subExpression}" // 订阅表达式, 不配置表示订阅所有消息
)
public class RockerMqConsumer implements RocketMQListener<String> {

    private final static Logger logger = LoggerFactory.getLogger(RockerMqProducer.class);

    @Override
    public void onMessage(String message) {
        logger.info("消费者1 消费消息：" + message);
    }
}
