package cn.xutingyin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.xutingyin.entity.Human;

@Configuration // 此注解不加也可以，区别在于采用什么代理方式，加了@Configuration后的配置类会由cglib动态代理。
@ComponentScan("cn.xutingyin")
public class MyConfig {

    @Bean(name = "human")
    public Human getStudent() {
        return new Human();
    }
}
