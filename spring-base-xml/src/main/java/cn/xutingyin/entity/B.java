package cn.xutingyin.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

    @Autowired
    private C c;

    public B() {}

    // 构造器注入
    public B(C c) {
        this.c = c;
    }

    // setter 方式注入
    public void setC(C c) {
        this.c = c;
    }
}
