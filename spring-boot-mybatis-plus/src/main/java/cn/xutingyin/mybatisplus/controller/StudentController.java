package cn.xutingyin.mybatisplus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.xutingyin.mybatisplus.entity.Student;
import cn.xutingyin.mybatisplus.mapper.StudentMapper;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/all")
    public  String  queryAll(){
        List<Student> students = studentMapper.selectList(null);
       return JSON.toJSON(students).toString();
    }
}
