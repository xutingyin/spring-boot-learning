package cn.xutingyin.springbootwebcontainer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class HelloController {

    @RequestMapping("hello")
    public String sayHello(){
        String msg =" hello " + LocalDateTime.now();
        return msg;
    }
}
