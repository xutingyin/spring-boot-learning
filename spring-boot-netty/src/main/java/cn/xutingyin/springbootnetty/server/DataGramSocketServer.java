package cn.xutingyin.springbootnetty.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @description: UDP DataGramSocket 实现服务端
 * @author: Tingyin.Xu
 * @date: 2020/4/28 15:25
 */

public class DataGramSocketServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(8888);
        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            server.receive(packet);
            // 接收客户端数据
            byte[] data = packet.getData();
            System.out.println(
                packet.getAddress().getHostName() + ":" + (packet.getPort()) + new String(data, 0, data.length));
            packet.setData("Hello Client-你好，客户端".getBytes());
            packet.setPort(8889);
            packet.setAddress(InetAddress.getLocalHost());
            // 向客户端发送数据
            server.send(packet);
            // 关闭链接
            server.close();
        }
    }
}
