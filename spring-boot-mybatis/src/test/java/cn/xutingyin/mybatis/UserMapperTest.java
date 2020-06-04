package cn.xutingyin.mybatis;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.xutingyin.mybatis.dto.UserDto;
import cn.xutingyin.mybatis.entity.Page;
import cn.xutingyin.mybatis.entity.User;
import cn.xutingyin.mybatis.mapper.UserMapper;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void listUser() {
        // 分页插件PageHelper
        // PageHelper.startPage(1, 10);
        // Page<User> users = userMapper.listUser();
        // List<User> result = users.getResult();
        UserDto userDto = new UserDto();
        Page page = new Page();
        page.setCurrentPage(1);
        page.setShowCount(3);
        userDto.setPage(page);
        List<User> users1 = userMapper.listPageUser(userDto);

        users1.forEach(System.out::println);
    }

}
