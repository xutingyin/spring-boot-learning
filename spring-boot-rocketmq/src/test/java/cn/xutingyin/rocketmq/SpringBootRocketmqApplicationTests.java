package cn.xutingyin.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.xutingyin.rocketmq.config.JmsConfig;
import cn.xutingyin.rocketmq.producer.Producer;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringBootRocketmqApplicationTests {

    @Autowired
    private Producer producer;

    /**
     * 单向发送消息
     * 
     * @throws RemotingException
     * @throws MQClientException
     * @throws InterruptedException
     * @throws UnsupportedEncodingException
     */
    @Test
    void sendOneWay() throws RemotingException, MQClientException, InterruptedException, UnsupportedEncodingException {
        for (int i = 0; i < 100; i++) {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */, "TagA" /* Tag */,
                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // Call send message to deliver message to one of brokers.
            producer.getProducer().sendOneway(msg);
        }
        // Wait for sending to complete
        Thread.sleep(5000);
    }

    /**
     * 可靠异步发送消息
     * 
     * @throws InterruptedException
     */
    @Test
    void asyncProducer() throws InterruptedException {
        int messageCount = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                final int index = i;
                Message msg =
                    new Message("Hello_Topic", "TagA", null, "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.getProducer().send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
    }

    /**
     * 可靠同步发送消息 原理：同步发送是指消息发送方发出数据后，会在收到接收方发回响应之后才发下一个数据包的通讯方式。
     * 
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     * @throws UnsupportedEncodingException
     */
    @Test
    void syncProducer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException,
        UnsupportedEncodingException {
        List<String> mesList;
        mesList = new ArrayList<>();
        mesList.add("sunshine");
        mesList.add("爸爸");
        mesList.add("妈妈");
        mesList.add("爷爷");
        mesList.add("奶奶");
        // 总共发送五次消息
        // for (String s : mesList) {
        // // 创建生产信息
        // Message message = new Message(JmsConfig.TOPIC, "TAG_SUNSHINE",
        // ("sunshine一家人的称谓:" + s).getBytes(RemotingHelper.DEFAULT_CHARSET));
        // // 发送
        // SendResult sendResult = producer.getProducer().send(message);
        // log.info("输出生产者信息={}", sendResult);
        // }
        for (int i = 0; i < 100; i++) {
            // Create a message instance, specifying topic, tag and message body.
            Message msg = new Message(JmsConfig.TOPIC /* Topic */, "TagA" /* Tag */,
                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.getProducer().send(msg);

        }
    }
}
