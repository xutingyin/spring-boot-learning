package cn.xutingyin.springbootmybatis;

import cn.xutingyin.springbootmybatis.entity.User;
import cn.xutingyin.springbootmybatis.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void listUser(){
        PageHelper.startPage(1,10);
        Page<User> users = userMapper.listUser();
    }
}
