package cn.xutingyin.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * @Description: 设置访问的URL
 * @Author: xuty
 * @CreateDate: 2021/5/23 11:46
 * 
 */
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    /**
     * 建立连接成功时触发
     * 
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(userId, session);
        System.out.println("【websocket消息】有新的连接，总数为:" + webSockets.size());
    }

    /**
     * 关闭连接时触发
     */
    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("【websocket消息】连接断开，总数为:" + webSockets.size());
    }

    /**
     * 通信发生错误时触发
     * 
     * @param session
     * @param error
     */

    @OnError
    public void onError(Session session, Throwable error) {

        System.out.println("发生错误！");
        error.printStackTrace();
    }

    /**
     * 
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:" + message);
    }

    /**
     * 广播消息
     */

    public void sendAllMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单点消息
     */

    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}