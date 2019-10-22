package cn.xutingyin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @Description: Hello 前端控制器
* @Author: xuty 
* @Date: 2019/10/22 17:19
*/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        String message = "Hello , Spring boot 2.0 !";
        System.out.println(message);
        return message;
    }

}
