package cn.xutingyin.springdatajpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

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
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private Long id;

    private String name;

}
