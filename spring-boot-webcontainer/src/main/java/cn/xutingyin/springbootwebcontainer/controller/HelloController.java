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
        for (int i = 0; i < 10; i++) {
            msg.append(" dddd " + LocalDateTime.now() + "==" + i);
        }
        return msg.toString();
    }
}
