package cn.xutingyin.rocketmq.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.rocketmq.config.JmsConfig;
import cn.xutingyin.rocketmq.producer.Producer;
import cn.xutingyin.rocketmq.result.JsonResult;
import cn.xutingyin.rocketmq.service.TransactionListenerImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: RocketMQ 生产者使用示例控制器下面例子，均参考官网例子，详情见：http://rocketmq.apache.org/docs/quick-start/
 * @author: xuty
 * @date: 2020/7/15 16:06
 */

@Slf4j
@RestController
@RequestMapping("/producer")
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

    @RequestMapping("/transactionMsg")
    public JsonResult transactionMessage() {
        try {
            TransactionListener transactionListener = new TransactionListenerImpl();
            TransactionMQProducer producer = new TransactionMQProducer("transaction_producer_group");
            producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
            ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transaction-msg-check-thread");
                        return thread;
                    }
                });

            producer.setExecutorService(executorService);
            producer.setTransactionListener(transactionListener);
            producer.start();

            String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
            for (int i = 0; i < 10; i++) {
                try {
                    Message msg = new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                    System.out.printf("%s%n", sendResult);

                    Thread.sleep(10);
                } catch (MQClientException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < 100000; i++) {
                Thread.sleep(1000);
            }
            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.success("成功");
    }

    @RequestMapping("/oneWay")
    public JsonResult oneWay() {
        try {
            DefaultMQProducer producer = new DefaultMQProducer();
            producer.setProducerGroup(JmsConfig.PRODUCER_GROUP);
            producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
            producer.start();
            for (int i = 0; i < 100; i++) {
                Message msg = new Message(JmsConfig.TOPIC_ONEWAY, "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.sendOneway(msg);
            }
            Thread.sleep(5000);
            producer.shutdown();
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success("成功");
    }

    @RequestMapping("/asyncSendMessage")
    public JsonResult asyncSendMessage() {
        try {
            DefaultMQProducer producer = new DefaultMQProducer(JmsConfig.PRODUCER_GROUP);
            producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(0);
            int messageCount = 100;
            final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
            for (int i = 0; i < messageCount; i++) {
                try {
                    final int index = i;
                    Message msg = new Message(JmsConfig.TOPIC_ASYNC, "TagA", "OrderID188",
                        "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                    producer.send(msg, new SendCallback() {
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
            producer.shutdown();
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success("成功");
    }

    @RequestMapping("/syncSendMessage")
    public JsonResult syncSendMessage() {
        try {
            // 总共发送五次消息
            for (String s : mesList) {
                // 创建生产信息
                Message message = new Message(JmsConfig.TOPIC, "sunshinetag", ("sunshine一家人的称谓:" + s).getBytes());
                // 发送
                SendResult sendResult = producer.getProducer().send(message);
                log.info("输出生产者信息={}", sendResult);
            }
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success("成功");
    }
}