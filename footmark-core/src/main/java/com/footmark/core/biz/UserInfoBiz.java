package com.footmark.core.biz;

import com.footmark.core.entity.UserInfo;
import com.footmark.core.vo.FriendInfoVo;

/**
 * @Description: 用户业务
 * @author carlos.chu
 * @date 2015年8月19日
 */
public interface UserInfoBiz {

    /**
     * 发送短信验证码
     * 
     * @param mobileNo
     * @return
     */
    Void validateTelNo(String mobileNo);

    /**
     * 校验短信验证码
     * 
     * @param telCode
     * @return userId
     */
    Void validateIdentifyingCode(String mobileNo, String telCode);

    /**
     * 用户注册
     * 
     * @param userInfo
     * @return
     */
    Long register(UserInfo userInfo);

    /**
     * 更新用户信息
     * 
     * @param user
     * @return
     */
    UserInfo updateUserInfo(UserInfo user);

    /**
     * 用户登录
     * 
     * @param user
     * @return
     */
    UserInfo login(UserInfo user);

    /**
     * 通过fid查询用户,并查询出来和uid之间的关系
     * 
     * @param userId
     * @param friendId
     * @return
     */
    FriendInfoVo queryFriendById(Long userId, Long friendId);

}
