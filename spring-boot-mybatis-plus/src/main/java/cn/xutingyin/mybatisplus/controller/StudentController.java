package cn.xutingyin.mybatisplus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.xutingyin.mybatisplus.entity.Student;
import cn.xutingyin.mybatisplus.mapper.StudentMapper;
import cn.xutingyin.mybatisplus.result.JsonResult;
import cn.xutingyin.mybatisplus.vo.StudentVo;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/all/{current}/{pageSize}")
    public JsonResult queryPageAll(@PathVariable("current") long current, @PathVariable("pageSize") long pageSize) {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(pageSize);
        IPage iPage = studentMapper.listStudentScore(page);
        StudentVo studentVo = new StudentVo();
        studentVo.setCurrentPage(current);
        studentVo.setTotalSize(iPage.getTotal());
        studentVo.setStudents(iPage.getRecords());
        return JsonResult.success(studentVo);
    }

    @GetMapping("/all")
    public String queryAll() {
        List<Student> students = studentMapper.selectList(null);
        return JSON.toJSON(students).toString();
    }
}
