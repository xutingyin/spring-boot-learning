package cn.xutingyin.springbootnetty.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: ServerSocket 实现网络通信
 * @author: Tingyin.Xu
 * @date: 2020/4/27 16:51
 */

public class GreetingServer extends Thread {

    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        /**
         * 创建 ServerSocket 类来监听指定的 port
         */
        serverSocket = new ServerSocket(port);

        // serverSocket.setSoTimeout(10000);
    }

    @Override
    public void run() {

        System.out.println("等待远程主机访问问，端口号为：" + serverSocket.getLocalPort());
        try {
            // 一直阻塞等待连接
            Socket socket = serverSocket.accept();
            System.out.println("远程主机地址：" + socket.getRemoteSocketAddress());
            // 获取请求的数据
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println(dataInputStream.readUTF() + "-----------");
            String flag = dataInputStream.readUTF();
            while (flag.equals("bye")) {
                // 给客户端回复
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF("谢谢你连接我：" + socket.getRemoteSocketAddress() + "\nGood Bye!");
                dataOutputStream.flush();
                // 断开连接
                socket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new GreetingServer(8888).start();

    }
}
