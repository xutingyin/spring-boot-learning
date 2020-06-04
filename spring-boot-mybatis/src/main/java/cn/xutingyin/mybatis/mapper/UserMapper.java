package cn.xutingyin.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;

import cn.xutingyin.mybatis.dto.UserDto;
import cn.xutingyin.mybatis.entity.User;

/**
 * @Description: 用户DAO
 * @Author: xuty
 * @Date: 2019/11/11 16:32
 */
@Mapper
@Repository
public interface UserMapper {

    // 利用Pagehelper 插件实现分页，这种方式在实现分页的同时，总条数也可以在Page 对象中获取
    Page<User> listUser();

    // 利用拦截器实现分页
    List<User> listPageUser(UserDto userDto);

    List<User> listUserByPage(@Param("currPage") Integer currPage, @Param("pageSize") Integer pageSize);
}
