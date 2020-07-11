package cn.xutingyin.rocketmq.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.rocketmq.config.JmsConfig;
import cn.xutingyin.rocketmq.server.Producer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private Producer producer;

    private List<String> mesList;

    /**
     * 初始化消息
     */
    public Controller() {
        mesList = new ArrayList<>();
        mesList.add("sunshine");
        mesList.add("dad");
        mesList.add("mom");
        mesList.add("grandfather");
        mesList.add("grandmother");

    }

    @RequestMapping("/text/rocketmq")
    public Object callback() throws Exception {
        // 总共发送五次消息
        for (String s : mesList) {
            // 创建生产信息
            Message message = new Message(JmsConfig.TOPIC, "sunshinetag", ("sunshine一家人的称谓:" + s).getBytes());
            // 发送
            SendResult sendResult = producer.getProducer().send(message);
            log.info("输出生产者信息={}", sendResult);
        }
        return "成功";
    }
}