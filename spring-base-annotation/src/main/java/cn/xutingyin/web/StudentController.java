package cn.xutingyin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.xutingyin.entity.Student;

@RestController
public class StudentController {

    @GetMapping("all")
    public String all() {
        Student student = new Student("susan", 22);
        return JSON.toJSONString(student);
    }
}
