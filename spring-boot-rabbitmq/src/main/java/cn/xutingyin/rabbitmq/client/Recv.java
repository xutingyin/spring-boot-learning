package cn.xutingyin.rabbitmq.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @description: 消费者-当启动多个消费者时，会平均消费生产者生产的数据
 * @author: xuty
 * @date: 2020/6/19 10:11
 */

public class Recv {

    private final static String QUEUE_NAME = "love";

    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 通过连接工厂创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个队列
        // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object>
        // arguments)
        // 参数1 queue ：队列名
        // 参数2 durable ：是否持久化
        // 参数3 exclusive ：仅创建者可以使用的私有队列，断开后自动删除
        // 参数4 autoDelete : 当所有消费客户端连接断开后，是否自动删除队列
        // 参数5 arguments
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            try {
                dowrk(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 每次处理完成一个消息后，手动发送一次应答。
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        // basicConsume(String queue, boolean autoAck, Consumer callback)
        // 参数1 queue ：队列名
        // 参数2 autoAck ： 是否自动ACK -- 消费者消费了消息后会向生产者发送一个ACK,生产者收到这个ACK，才将内存中的消息删除。
        // 参数3 callback ： 消费者对象的一个接口，用来配置回调
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
        });
    }

    private static void dowrk(String message) throws InterruptedException {
        String[] taskArr = message.split(":");
        // TimeUnit.SECONDS.sleep(Long.valueOf(taskArr[1]));
    }

}