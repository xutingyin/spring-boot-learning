package cn.xutingyin.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.server.WebSocket;

/**
 * @author xuyin
 */
@RestController
@RequestMapping("api")
public class WebSocketController {

    @Autowired
    private WebSocket webSocket;

    private static AtomicInteger count = new AtomicInteger(0);

    @RequestMapping("/sendAllWebSocket")
    public String test() {
        webSocket.sendAllMessage("清晨起来打开窗，心情美美哒~");
        return "websocket群体发送！";
    }

    @GetMapping("/sendOneWebSocket/{userId}")
    public String sendOneWebSocket(@PathVariable("userId") String userId) {
        StringBuilder msg = new StringBuilder();
        int currentCount = count.getAndIncrement();
        msg.append(userId).append(":").append("Stay hungry,stay foolish.").append(String.valueOf(currentCount))
            .toString();
        webSocket.sendOneMessage(userId, msg.toString());
        return msg.toString();
    }
}