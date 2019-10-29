package cn.xutingyin.dao;

import cn.xutingyin.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
/** 
* @Description: 用户信息Dao
* @Author: xuty 
* @Date: 2019/10/23 10:05
*/
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**
     * 通过username查找用户信息;
     * @param userName 用户名
     * @return 用户信息
     */
    public UserInfo findByUsername(String userName);
}
