package cn.xutingyin.rabbitmq.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @description: 生产者
 * @author: xuty
 * @date: 2020/6/19 10:11
 */

public class Send {
    private final static String QUEUE_NAME = "love";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            // 关闭频道和连接 -- 这里使用了 try resource 会自动帮我们关闭
//            channel.close();
//            connection.close();
        }
    }
}