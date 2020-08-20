package cn.xutingyin.jpa;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import cn.xutingyin.jpa.dto.StudentDto;
import cn.xutingyin.jpa.dto.StudentDto2;
import cn.xutingyin.jpa.entity.Student;
import cn.xutingyin.jpa.repository.StudentRepository;

@SpringBootTest
public class StudentTest {

    @Resource
    private StudentRepository studentRepository;

    @Test
    public void save() {
        Student student = new Student();
        student.setId(UUID.randomUUID().toString().replace("-", ""));
        student.setAge(22);
        student.setName("马云");
        student.setSex("男");
        student.setCreateTime(LocalDateTime.now());
        studentRepository.save(student);
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setName("孙风");
        studentRepository.deleteByName(student.getName());
    }

    @Test
    public void query() {
        List<Student> students = studentRepository.findAll();
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void multiQuery() throws Exception {
        // JPA 不支持返回自定义实体类，只能返回Map
        List<Object[]> studentDtos = studentRepository.multiQuery();
        List<StudentDto> studentDtoList = castEntity(studentDtos, StudentDto.class);
        studentDtoList.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * 跟着纯洁的微笑学习的技巧：List 里面装的是接口，第一次见这种骚操作
     * 
     * @throws Exception
     */
    @Test
    public void multiQuery2() throws Exception {
        List<StudentDto2> studentDtos = studentRepository.multiQuery2();
        studentDtos.forEach(student -> {
            System.out.println(student.getCourseId() + "--" + student.getStudentName());
        });
    }

    /**
     * 转换实体类--这里自定义实体类的属性字段顺序还必须和SQL中返回的字段顺序一致
     */
    public <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        if (CollectionUtils.isEmpty(list)) {
            return returnList;
        }
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        // 确定构造方法
        for (int i = 0; i < co.length; i++) {
            if (co[i] != null) {
                c2[i] = co[i].getClass();
            } else {
                c2[i] = String.class;
            }
        }
        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }

}
