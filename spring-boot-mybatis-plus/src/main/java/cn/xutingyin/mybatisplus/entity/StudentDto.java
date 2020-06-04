package cn.xutingyin.mybatisplus.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private BigDecimal score;

}
