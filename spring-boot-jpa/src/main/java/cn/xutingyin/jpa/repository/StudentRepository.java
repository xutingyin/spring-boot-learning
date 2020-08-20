package cn.xutingyin.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.xutingyin.jpa.dto.StudentDto2;
import cn.xutingyin.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Transactional(timeout = 10) // update|delete 方法需要加上事务注解
    @Modifying
    @Query("delete from Student u where u.name = ?1")
    void deleteByName(String name);

    @Query("SELECT stu.id AS studentId,stu.name AS studentName,sc.courseId AS courseId,c.name AS courseName,sc.score AS score FROM Student  stu LEFT JOIN Score sc ON stu.id=sc.studentId LEFT JOIN Course c ON sc.courseId=c.id")
    List<Object[]> multiQuery();

    @Query("SELECT stu.id AS studentId,stu.name AS studentName,sc.courseId AS courseId,c.name AS courseName,sc.score AS score FROM Student  stu LEFT JOIN Score sc ON stu.id=sc.studentId LEFT JOIN Course c ON sc.courseId=c.id")
    List<StudentDto2> multiQuery2();

}
