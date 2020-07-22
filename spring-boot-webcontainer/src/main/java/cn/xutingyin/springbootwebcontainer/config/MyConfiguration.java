package cn.xutingyin.springbootwebcontainer.config;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MyConfiguration {

    public MyConfiguration() {
        log.info("MyConfiguration started...");
    }

}
