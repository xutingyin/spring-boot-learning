package cn.xutingyin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SpringBootHelloApplicationTests {

    @Test
    void contextLoads() {
        ClassPathXmlApplicationContext applicationtext = new ClassPathXmlApplicationContext("application.properties");
        applicationtext.getBean("userService");

    }

}
