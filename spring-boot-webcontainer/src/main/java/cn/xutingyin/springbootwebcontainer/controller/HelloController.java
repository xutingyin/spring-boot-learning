package cn.xutingyin.springbootwebcontainer.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @RequestMapping("hello")
    public String sayHello() {
        StringBuilder msg = new StringBuilder();
        // String msg = "";
        for (int i = 0; i < 10000; i++) {

            msg.append(" hello " + LocalDateTime.now() + "==" + i);
            // msg = msg + "" + i;
        }
        return msg.toString();
    }
}
