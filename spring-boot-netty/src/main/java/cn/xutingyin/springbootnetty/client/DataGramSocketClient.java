package cn.xutingyin.springbootnetty.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @description: UDP DataGramSocket 实现客户端
 * @author: Tingyin.Xu
 * @date: 2020/4/28 15:25
 */

public class DataGramSocketClient {
    public static void main(String[] args) throws Exception {
        String msg = "Hello Server 服务端";
        DatagramSocket client = new DatagramSocket(8889);
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length);
        packet.setAddress(InetAddress.getLocalHost());
        packet.setPort(8888);
        packet.setData(msg.getBytes());
        client.send(packet);
        client.receive(packet);
        System.out.println(packet.getAddress().getHostName() + ":" + packet.getPort() + new String(packet.getData()));
        client.close();

    }
}
