import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RocketMqTest {



    /**
     *  创建消息生产者,并发送消息
     */
    @Test
    public void test(){
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer(
                "MQ_INST_rocketmqvv9engbbppnm_JieZiShuSpace",
                "JieZiShuGroup",
                new AclClientRPCHook(new SessionCredentials(
                        "eyJrZXlJZCI6InJvY2tldG1xLXZ2OWVuZ2JicHBubSIsImFsZyI6IkhTMjU2In0.eyJzdWIiOiJyb2NrZXRtcS12djllbmdiYnBwbm1fYWRtaW4ifQ.ql1tzejGpcjfRvALSWx7g0cjqd25GePcy2vP38wKpHE","admin")) // ACL权限
        );

       // 设置NameServer的地址
        producer.setNamesrvAddr("MQ_INST_rocketmqvv9engbbppnm_JieZiShuSpace.tdmq-rocketmq.ap-gz.public.tencenttdmq.com:9876");
       // 启动Producer实例
        try {
            producer.start();
            // 创建消息实例，设置topic和消息内容
            Message msg = new Message("JieZiShuTopic", "TAG", ("Hello  RocketMQ~").getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 消费者消费消息
     */
    @Test
    public void test02(){
        // 实例化消费者
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(
                "MQ_INST_rocketmqvv9engbbppnm_JieZiShuSpace",
                "JieZiShuGroup",
                new AclClientRPCHook(new SessionCredentials(
                        "eyJrZXlJZCI6InJvY2tldG1xLXZ2OWVuZ2JicHBubSIsImFsZyI6IkhTMjU2In0.eyJzdWIi" +
                                "OiJyb2NrZXRtcS12djllbmdiYnBwbm1fYWRtaW4ifQ.ql1tzejGpcjfRvALSWx7g0cjqd25GePcy2vP38wKpHE","admin"))); //ACL权限
        // 设置NameServer的地址
        pushConsumer.setNamesrvAddr("MQ_INST_rocketmqvv9engbbppnm_JieZiShuSpace.tdmq-rocketmq.ap-gz.public.tencenttdmq.com:9876");


        // 订阅topic
        try {
            pushConsumer.subscribe("JieZiShuTopic", "*");
         // 注册回调实现类来处理从broker拉取回来的消息
            pushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                // 消息处理逻辑
                String a= String.valueOf(context);
                String b= String.valueOf(msgs);
                System.out.println(context);
                System.out.println(msgs);
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 标记该消息已经被成功消费, 根据消费情况，返回处理状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
             // 启动消费者实例
            pushConsumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Test
    public void test03(){
        // 实例化消费者
        DefaultLitePullConsumer pullConsumer = new DefaultLitePullConsumer(
                "MQ_INST_rocketmqvv9engbbppnm_JieZiShuSpace",
                "JieZiShuGroup",
                new AclClientRPCHook(new SessionCredentials("eyJrZXlJZCI6InJvY2tldG1xLXZ2OWVuZ2JicHBubSIsImFsZyI6IkhTMjU2In0.eyJzdWIi" +
                        "OiJyb2NrZXRtcS12djllbmdiYnBwbm1fYWRtaW4ifQ.ql1tzejGpcjfRvALSWx7g0cjqd25GePcy2vP38wKpHE","admin")));
// 设置NameServer的地址
        pullConsumer.setNamesrvAddr("MQ_INST_rocketmqvv9engbbppnm_JieZiShuSpace.tdmq-rocketmq.ap-gz.public.tencenttdmq.com:9876");
// 设置从第一个偏移量开始消费
        pullConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

           try{
        // 订阅topic
        pullConsumer.subscribe("JieZiShuTopic", "*");
// 启动消费者实例
        pullConsumer.start();

            System.out.printf("Consumer Started.%n");
            while (true) {
                // 拉取消息
                List<MessageExt> messageExts = pullConsumer.poll();
                messageExts.forEach(e->{
                    Charset charset = StandardCharsets.UTF_8;
                    String string1 = charset.decode(ByteBuffer.wrap(e.getBody())).toString();
                    System.out.println(string1);
                });

            }
        }catch (Exception e){
               e.printStackTrace();
           }  finally{
            pullConsumer.shutdown();
        }




    }


}
