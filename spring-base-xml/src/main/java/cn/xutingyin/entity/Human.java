package cn.xutingyin.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Human implements Serializable {
    private String name;
    private String sex;
    private int age;
    @Autowired
    private Car audi;
    @Autowired
    private Car bmw;

    private Car benz;

    @Autowired
    public void SetBenz(Car benz) {
        this.benz = benz;
    }

    public Human() {
        System.out.println("Human 实例完成...");
    }
}
