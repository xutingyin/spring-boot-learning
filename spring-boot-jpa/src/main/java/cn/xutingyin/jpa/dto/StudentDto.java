package cn.xutingyin.jpa.dto;

import lombok.Data;

@Data
public class StudentDto {
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private Integer score;

    public StudentDto() {}

    public StudentDto(String studentId, String studentName, String courseId, String courseName, Integer score) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.score = score;
    }
}
