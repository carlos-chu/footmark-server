package com.footmark.core.service;

import java.util.List;

import com.footmark.core.entity.UserInfo;

/**
 * @Description: 用户信息服务
 * @author carlos.chu
 * @date 2015年8月20日
 */
public interface UserInfoService {

    /**
     * 通过手机号查找用户
     * 
     * @param mobileNo
     * @return
     */
    UserInfo queryByMobileNo(String mobileNo);

    /**
     * 根据手机号和密码查询用户
     * 
     * @param mobileNo
     * @param password
     * @return
     */
    UserInfo queryByMobileNoAndPsw(String mobileNo, String password);

    /**
     * 创建userInfo
     * 
     * @param userInfo
     * @return
     */
    void addUser(UserInfo userInfo);

    /**
     * 根据userId查询用户信息
     * 
     * @param userId
     * @return
     */
    UserInfo queryByUserId(long userId);

    /**
     * 根据ids查询出来多个用户
     * 
     * @param ids
     * @return
     */
    List<UserInfo> queryByIds(List<Long> ids);
}
