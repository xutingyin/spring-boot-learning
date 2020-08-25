package cn.xutingyin.entity;

import org.springframework.stereotype.Component;

@Component
public class C {

    private A a;

    public C() {}

    public C(A a) {
        this.a = a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
