package cn.xutingyin.mybatis.dto;

import cn.xutingyin.mybatis.entity.Page;
import cn.xutingyin.mybatis.entity.User;
import lombok.Data;

@Data
public class UserDto extends User {
    private Page page;
}
