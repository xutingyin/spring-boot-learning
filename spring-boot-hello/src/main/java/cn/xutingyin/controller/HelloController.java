package cn.xutingyin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.config.PropertyConfig;

/** 
* @Description: Hello 前端控制器
* @Author: xuty 
* @Date: 2019/10/22 17:19
*/
@RestController
//@Scope("prototype")　每次都返回一个新的实例
public class HelloController {
    @Autowired
    private PropertyConfig propertyConfig;

    private long num;
    @GetMapping("/hello")
    public String sayHello(){
        String message = "Hello , Spring boot 2.0 !";
        String name = propertyConfig.getName();
        num = num +1;
        return message.concat("--").concat(name).concat("--").concat(String.valueOf(num));
    }

}
