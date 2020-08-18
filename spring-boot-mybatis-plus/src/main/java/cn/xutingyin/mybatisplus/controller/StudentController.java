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
    public List<Student> queryAll() {
        List<Student> students = studentMapper.selectList(null);
        return students;
    }

    /**
     * 这个方法的目的就是用于测试 LocalDateTime 类型的字段看返回是否正常，是否格式中带有 T
     * 
     * @return
     */
    @GetMapping("/all2")
    public String queryAll2() {
        List<Student> students = studentMapper.selectList(null);
        return JSON.toJSONString(students);
    }
}
