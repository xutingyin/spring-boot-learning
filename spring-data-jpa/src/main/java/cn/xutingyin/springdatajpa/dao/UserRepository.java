package cn.xutingyin.springdatajpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.xutingyin.springdatajpa.entity.User;

/**
 * 自定义的Repository继承PagingAndSortingRepository，PagingAndSortingRepository继承了CRUDRepository
 * 
 * @author xuyin
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /***
     * nativeQuery = true 则使用原生SQL进行查询，即使用原始的SQL语句查询
     * 
     * @param name
     * @return
     */
    @Query(value = "select id,name,age,create_time,update_time from user where name like %?1% ", nativeQuery = true)
    List<User> findByNameLike(String name);

    /**
     * 这样是用的对象来进行查询 这里不能查询指定的属性，暂且也不清楚啥原因 select a.id,a.name,a.age,a.createTime,a.updateTime from User as a where a.name
     * like %?1%
     * 
     * @param name
     * @return
     */
    @Query("select u from User as u where u.name like %?1%")
    List<User> findByNameLike2(String name);

}
