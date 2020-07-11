package cn.xutingyin.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import cn.xutingyin.rocketmq.config.JmsConfig;

/**
 * @description: 同步消息生产者 --TODO:这里不知道为什么直接启动会报超时的错误，放到Junit单元测试执行就可以正常执行
 * @author: xuty
 * @date: 2020/7/11 16:31
 * 
 */

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("test_producer");
        // Specify name server addresses.
        producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        producer.setVipChannelEnabled(false);
        // Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(JmsConfig.TOPIC /* Topic */, "TagA" /* Tag */,
                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        // Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
