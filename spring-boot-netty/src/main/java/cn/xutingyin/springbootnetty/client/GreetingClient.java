package cn.xutingyin.springbootnetty.client;

import java.io.*;
import java.net.Socket;

public class GreetingClient {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = Integer.parseInt("8888");
        try {

            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("你好");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            out.flush();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}