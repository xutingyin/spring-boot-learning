package cn.xutingyin.springbootwebcontainer.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Student {
    private String name;
    private Integer age;
    private BigDecimal money;
}
