package cn.xutingyin.springbootmybatis.mapper;

import cn.xutingyin.springbootmybatis.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
* @Description: 用户DAO 
* @Author: xuty 
* @Date: 2019/11/11 16:32
*/
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM tb_user")
    Page<User> listUser();

}
