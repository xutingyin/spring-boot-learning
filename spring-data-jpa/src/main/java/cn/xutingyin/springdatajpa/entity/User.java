package cn.xutingyin.springdatajpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(length = 32)
    private String id;

    private String name;

}
