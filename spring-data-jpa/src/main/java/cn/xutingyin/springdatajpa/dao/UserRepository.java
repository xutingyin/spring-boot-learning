package cn.xutingyin.springdatajpa.dao;

import cn.xutingyin.springdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    /***
     * nativeQuery = true 则使用原生SQL进行查询，即使用原始的SQL语句查询
     * @param name
     * @return
     */
    @Query(value = "select id,name,age,create_time from user where name like %?1% ",nativeQuery = true)
    List<User> findByNameLike(String name);

    // 这样是用的对象来进行查询
    @Query(value = "select a.id,a.name,a.age,a.createTime from User as a where a.name like %:name%")
    List<User> findByNameLike2(@Param("name") String name);

    //分页查询
}
