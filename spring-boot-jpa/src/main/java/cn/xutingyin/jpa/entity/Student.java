package cn.xutingyin.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
@Entity
public class Student implements Serializable {
    @Id
    private String id;
    private String name;
    private int age;
    private String sex;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

}
