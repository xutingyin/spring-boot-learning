package cn.xutingyin.rocketmq;

import java.util.ArrayList;
import java.util.List;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.xutingyin.rocketmq.config.JmsConfig;
import cn.xutingyin.rocketmq.server.Producer;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringBootRocketmqApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    void contextLoads() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        List<String> mesList;
        mesList = new ArrayList<>();
        mesList.add("小小");
        mesList.add("爸爸");
        mesList.add("妈妈");
        mesList.add("爷爷");
        mesList.add("奶奶");
        // 总共发送五次消息
        for (String s : mesList) {
            // 创建生产信息
            Message message = new Message(JmsConfig.TOPIC, "testtag", ("小小一家人的称谓:" + s).getBytes());
            // 发送
            SendResult sendResult = producer.getProducer().send(message);
            log.info("输出生产者信息={}", sendResult);
        }

    }
}
