package cn.xutingyin.rocketmq.consumer;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import cn.xutingyin.rocketmq.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;
    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP = "test_consumer";

    /**
     * 通过构造函数 实例化对象
     */
    public Consumer() throws MQClientException {

        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        // 消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 订阅主题和 标签（ * 代表所有标签)下信息,如果需要使用多个标签，使用 || 分隔开，例如 TagA || TagB || TagC
        consumer.subscribe(JmsConfig.TOPIC, "TagB");
        // 事务消息需要使用的状态
        // LocalTransactionState.COMMIT_MESSAGE

        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently)(msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            try {
                for (Message msg : msgs) {

                    // 消费者获取消息 这里只输出 不做后面逻辑处理
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", msg.getTopic(), body);
                    System.out.printf("%s : %s", msg.getTopic(), body);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 等价于上面的lambda表达式的写法
        /* consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext conetxt) {
                for (Message msg : msgs) {
        
                    // 消费者获取消息 这里只输出 不做后面逻辑处理
                    String body = null;
                    try {
                        body = new String(msg.getBody(), "utf-8");
                        log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", msg.getTopic(), body);
                        System.out.printf("%s : %s", msg.getTopic(), body);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });*/

        consumer.start();
        System.out.println("消费者 启动成功=======");
    }
}