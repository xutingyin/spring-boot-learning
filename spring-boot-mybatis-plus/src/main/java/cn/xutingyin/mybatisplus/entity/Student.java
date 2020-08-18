package cn.xutingyin.mybatisplus.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("student")
public class Student implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String name;
    private int age;
    private String sex;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss") // 这里如果Controller 层是使用Alibaba 的FastJson 直接返回的话，需要使用此注解，不然返回时间会带 T
    private LocalDateTime createTime;

}
