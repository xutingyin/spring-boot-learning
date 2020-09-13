package cn.xutingyin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PropertyConfig {
    @Value("${cn.xutingyin.value}")
    private  String name;

}
