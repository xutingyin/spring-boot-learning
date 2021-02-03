package cn.xutingyin.springdatajpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 *
 * 功能描述：用户实体类
 * 
 * @author TingYin.Xu
 * @date 2021/1/27 19:04
 *
 */
@Entity
@Table(name = "user")
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String name;

    private Integer age;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
