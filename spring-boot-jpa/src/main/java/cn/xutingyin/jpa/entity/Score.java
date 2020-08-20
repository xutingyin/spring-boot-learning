package cn.xutingyin.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Score {
    @Id
    private String id;
    private String studentId;
    private String courseId;
    private int score;

}
