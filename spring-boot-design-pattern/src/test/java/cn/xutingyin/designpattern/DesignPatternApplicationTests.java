package cn.xutingyin.designpattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cn.xutingyin.designpattern.facade.MoYanClient;

@SpringBootTest
class DesignPatternApplicationTests {

    @Test
    void contextLoads() {
        MoYanClient moYanClient = new MoYanClient();
        moYanClient.wantHouse();
    }

}
