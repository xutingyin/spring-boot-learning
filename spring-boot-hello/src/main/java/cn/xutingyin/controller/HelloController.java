package cn.xutingyin.controller;

import cn.xutingyin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.config.PropertyConfig;

import java.util.ArrayList;
import java.util.List;

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
    public List<User> sayHello(){
        User u = new User();
        u.setAge(20);
        u.setName("susan");
        List<User> list= new ArrayList<>();
        list.add(u);
        list.add(u);
        list.add(u);
        return list;
    }
}
