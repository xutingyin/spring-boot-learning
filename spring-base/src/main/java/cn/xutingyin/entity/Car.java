package cn.xutingyin.entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Car {
    public Car() {
        System.out.println("Car 实例完成...");
    }
}
