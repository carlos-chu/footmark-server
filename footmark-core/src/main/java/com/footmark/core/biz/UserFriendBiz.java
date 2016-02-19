package com.footmark.core.biz;

import java.util.List;
import java.util.Map;

import com.footmark.core.entity.UserFriend;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.vo.FriendInfoVo;

/**
 * @Description: 用户好友业务
 * @author carlos.chu
 * @date 2015年9月11日
 */
public interface UserFriendBiz {

    /**
     * 申请添加好友
     * 
     * @param userFriend
     * @return
     */
    boolean apply(UserFriend userFriend);

    /**
     * 同意添加好友
     * 
     * @param userFriend
     */
    boolean agree(UserFriend userFriend);

    /**
     * 拒绝添加好友
     * 
     * @param userFriend
     */
    void refuse(UserFriend userFriend);

    /**
     * 删除好友
     * 
     * @param userFriendId
     */
    void delete(Long userFriendId);

    /**
     * 添加进黑名单
     * 
     * @param userFriend
     */
    void addToBlackList(UserFriend userFriend);

    /**
     * 通过userId和接受状态查询出好友
     * 
     * @param userId
     * @param acceptStatus
     * @return
     */
    List<UserFriend> queryByUserId(Long userId, RelationAcceptStatusEnum acceptStatus);

    /**
     * 查询用户的好友并且按首字母排序
     * 
     * @param userId
     * @param acceptStatus
     * @return
     */
    Map<String, Object> queryFriendInfoByUserIdSortByName(Long userId, RelationAcceptStatusEnum acceptStatus);

    /**
     * 查询用户的好友按时间排序
     * 
     * @param userId
     * @param acceptStatus
     * @return
     */
    List<FriendInfoVo> queryFriendInfoByUserId(Long userId, RelationAcceptStatusEnum acceptStatus);

}
