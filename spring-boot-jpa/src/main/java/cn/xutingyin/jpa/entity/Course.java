package cn.xutingyin.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Course {
    @Id
    private String id;
    private String name;
    private String teacherId;

}
