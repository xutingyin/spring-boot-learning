package cn.xutingyin.redis.web;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.redis.entity.User;
import cn.xutingyin.redis.util.SpringRedisTool;

@RestController
public class StudentController {
    @Autowired
    private SpringRedisTool springRedisTool;

    @RequestMapping("/getUser")
    @Cacheable(value = "cn.xutingtyin") // 先从缓存中取，如果没有，则返回新建的对象
    public User getUser() {
        User user = new User(2L, "aa", "aa@126.com", "aa123456", "aa", "123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        System.out.println(springRedisTool.getByKey("cn.xutingyin"));
        springRedisTool.deleteByKey("cn.xutingyin");
        UUID uid = (UUID)session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
