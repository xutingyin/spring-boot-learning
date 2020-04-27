package cn.xutingyin.springbootmybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.xutingyin.springbootmybatis.entity.User;
import cn.xutingyin.springbootmybatis.mapper.UserMapper;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void listUser() {
        PageHelper.startPage(1, 10);
        Page<User> users = userMapper.listUser();
    }

    @Test
    public void testAssert() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前值：" + i);
        }
    }
}
