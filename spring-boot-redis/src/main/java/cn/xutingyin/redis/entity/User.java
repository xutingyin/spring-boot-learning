package cn.xutingyin.redis.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String nickname;
    private String regTime;

}
