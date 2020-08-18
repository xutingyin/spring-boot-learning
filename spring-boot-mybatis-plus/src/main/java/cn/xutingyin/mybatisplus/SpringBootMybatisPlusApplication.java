package cn.xutingyin.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 启动类 如果在启动类中添加了mapper包下的扫描，那么对应的Mapper接口上就不需要再添加@Mapper 或者@Repository 注解
 * @author: xuty
 * @date: 2020/8/18 9:56
 */

@SpringBootApplication
@MapperScan("cn.xutingyin.mybatisplus.mapper")
public class SpringBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusApplication.class, args);
    }

}
