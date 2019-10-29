package cn.xutingyin.service.impl;

import cn.xutingyin.dao.UserInfoDao;
import cn.xutingyin.model.UserInfo;
import cn.xutingyin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
* @Description: 用户信息业务层实现
* @Author: xuty 
* @Date: 2019/10/23 10:07
*/

@Service
public class UserInfoServiceImpl implements UserInfoService {

//    @Autowired
    @Resource
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo findByUserName(String userName) {
        return userInfoDao.findByUsername(userName);
    }
}
