package cn.xutingyin.springdatajpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
 *
 * 功能描述：用户实体类
 * @author TingYin.Xu
 * @date 2021/1/27 19:04
 *
 */
@Entity
@Table(name= "user")
@Data
@Proxy(lazy = false)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 这种策略是ID为整型时，自增的情况
    @Column(length = 32)
    private String id;

    private String name;

    private Integer age;

    @Column(name="create_time")
    private LocalDateTime createTime;
}
