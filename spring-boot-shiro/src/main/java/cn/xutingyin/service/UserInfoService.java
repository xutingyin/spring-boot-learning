package cn.xutingyin.service;

import cn.xutingyin.model.UserInfo;

public interface UserInfoService {

    /**
     * 根据 username查找用户信息
     * @return
     */
    public UserInfo findByUserName(String userName);
}
