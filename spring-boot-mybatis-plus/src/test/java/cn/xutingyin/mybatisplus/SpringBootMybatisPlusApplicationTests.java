package cn.xutingyin.mybatisplus;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.xutingyin.mybatisplus.dto.StudentDto;
import cn.xutingyin.mybatisplus.entity.Course;
import cn.xutingyin.mybatisplus.entity.Score;
import cn.xutingyin.mybatisplus.entity.Student;
import cn.xutingyin.mybatisplus.mapper.CourseMapper;
import cn.xutingyin.mybatisplus.mapper.ScoreMapper;
import cn.xutingyin.mybatisplus.mapper.StudentMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringBootMybatisPlusApplicationTests {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Test
    void queryStudents() {
        Page<StudentDto> page = new Page<StudentDto>(1, 2);
        IPage<StudentDto> studentDtoIPage = studentMapper.listStudentScore(page);
        studentDtoIPage.getRecords().forEach(System.out::println);
    }

    @Test
    void addUser() {
        Student student = new Student();
        student.setAge(22);
        student.setName("Linda");
        student.setSex("女");
        student.setCreateTime(LocalDateTime.now());
        int insert = studentMapper.insert(student);
        // 模拟插入返回主键ID
        System.out.println(student.getId());
    }

    @Test
    void addCourse() {
        Course course = new Course();
        course.setCourseName("语文");
        course.setCreateTime(LocalDateTime.now());
        courseMapper.insert(course);
    }

    @Test
    // @Transactional
    void addScore() {
        Course course = new Course();
        course.setCourseName("语文");
        course.setCreateTime(LocalDateTime.now());
        courseMapper.insert(course);
        Score score = new Score();
        score.setCourseId(course.getId());
        score.setScore(96);
        score.setStudentId("864a4db77d27d881a7830cdc4a2ac28d");
        score.setCreateTime(LocalDateTime.now());
        scoreMapper.insert(score);
    }

}
