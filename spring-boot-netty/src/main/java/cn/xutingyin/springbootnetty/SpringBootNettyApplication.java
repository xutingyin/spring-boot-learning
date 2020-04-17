package cn.xutingyin.springbootnetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.xutingyin.springbootnetty.server.BootNettyServer;

@SpringBootApplication
public class SpringBootNettyApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpringBootNettyApplication.class, args);
        SpringApplication boot = new SpringApplication(SpringBootNettyApplication.class);
        boot.setWebApplicationType(WebApplicationType.NONE);//不启动web服务
        boot.run(args);
        new BootNettyServer().bind(8866);
    }

}
