package cn.xutingyin.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    @Autowired // 注解注入
    private B b;

    public A() {}

    // 构造器注入
    public A(B b) {
        this.b = b;
    }

    // setter 注入
    public void setB(B b) {
        this.b = b;
    }

}
