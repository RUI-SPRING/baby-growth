package com.mq.controller;

import com.feign.client.UserFeign;
import com.mq.config.RockerMqProperties;
import com.mq.producer.RockerMqProducer;
import com.mq.service.RockerMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: 测试发送消息
 */
@RestController
@RequestMapping("/rocketMq")
public class SendMessageController {

    @Autowired
    private RockerMqProducer sendMessage;

    @Autowired
    private RockerMqProperties rockerMqProperties;

    @Autowired
    private RockerMqService rockerMqService;

    /**
     * 发送同步消息
     */
    @GetMapping("/syncSend")
    public String syncSend() {
        sendMessage.syncSend("This is a new message1.", "TAG1");
        sendMessage.syncSend("This is a new message2.", "TAG1");
        sendMessage.syncSend("This is a new message3.", "TAG2");
        sendMessage.syncSend("This is a new message4.", "TAG2");
        return "success";
    }

    /**
     * 发送异步消息
     */
    @GetMapping("/asyncSend")
    public String asyncSend() {
        sendMessage.asyncSend("This is a async message1.", "TAG1");
        sendMessage.asyncSend("This is a async message2.", "TAG1");
        sendMessage.asyncSend("This is a async message3.", "TAG2");
        sendMessage.asyncSend("This is a async message4.", "TAG2");
        return "success";
    }

    /**
     * 发送单向消息
     */
    @GetMapping("/onewaySend")
    public String onewaySend() {
        sendMessage.onewaySend("This is a oneway message1.", "TAG1");
        sendMessage.onewaySend("This is a oneway message2.", "TAG1");
        sendMessage.onewaySend("This is a oneway message3.", "TAG2");
        sendMessage.onewaySend("This is a oneway message4.", "TAG2");
        return "success";
    }

    /**
     * 发送延迟消息
     */
    @GetMapping("/delaySend")
    public String delaySend() {
        sendMessage.delaySend("This is a delay message1.", "TAG1", 2);
        sendMessage.delaySend("This is a delay message2.", "TAG1", 2);
        sendMessage.delaySend("This is a delay message3.", "TAG2", 4);
        sendMessage.delaySend("This is a delay message4.", "TAG2", 4);
        return "success";
    }


    /**
     * 批量发送消息
     */
    @GetMapping("/batchSend")
    public String batchSend() {
        sendMessage.batchSend("This is a batchSend message.", "TAG1");
        sendMessage.batchSend("This is a batchSend message.", "TAG2");
        return "success";
    }

    @GetMapping("/now")
    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(rockerMqProperties.getDateformat()));
    }


    @RequestMapping("/hello")
    public String hello(){
        return rockerMqService.hello();
    }

}
