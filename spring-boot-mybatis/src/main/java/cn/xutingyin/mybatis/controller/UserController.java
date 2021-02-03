package cn.xutingyin.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.mybatis.dto.UserDto;
import cn.xutingyin.mybatis.entity.Page;
import cn.xutingyin.mybatis.entity.User;
import cn.xutingyin.mybatis.mapper.UserMapper;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("test")
    public void test() {
        UserDto userDto = new UserDto();
        Page page = new Page();
        page.setCurrentPage(1);
        page.setShowCount(3);
        userDto.setPage(page);
        // 这里进行查询后，UserDto 中的Page 对象会被重新赋值，就可以拿到总记录数
        List<User> users = userMapper.listPageUser(userDto);
        // List<User> users2 = userMapper.listUserByPage(1, 3);
        // List<User> users = userMapper.listUserByPage(1, 3);
        users.forEach(System.out::println);
        // users2.forEach(System.out::println);
        Integer i = 0;
        int j = 0;
    }
}
