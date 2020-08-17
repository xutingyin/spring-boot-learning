package cn.xutingyin.mybatisplus;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.xutingyin.mybatisplus.entity.Student;
import cn.xutingyin.mybatisplus.entity.StudentDto;
import cn.xutingyin.mybatisplus.mapper.StudentMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringBootMybatisPlusApplicationTests {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void queryStudents() {
        Page<StudentDto> page = new Page<StudentDto>(1, 10);
        IPage<StudentDto> studentDtoIPage = studentMapper.listStudentScore(page);
        studentDtoIPage.getRecords().forEach(System.out::println);
    }

    @Test
    void addUser() {
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setAge(22);
        student.setName("Linda");
        student.setSex("女");
        student.setCreateTime(LocalDateTime.now());
//        studentMapper.addUser(student);
        studentMapper.insert(student);
    }

}
