package cn.xutingyin.springbootmybatis.entity;

import lombok.Data;

/** 
* @Description: 用户实体类
* @Author: xuty 
* @Date: 2019/11/11 16:32
*/
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String salt;
}
