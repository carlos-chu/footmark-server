package com.footmark.core.service;

import java.util.List;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.entity.UserFriend;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.enums.RelationTypeEnum;
import com.footmark.core.vo.FriendInfoVo;

/**
 * @Description: 好友关系服务接口
 * @author carlos.chu
 * @date 2015年9月11日
 */
public interface UserFriendService {

    /**
     * 更新好友关系接受状态
     * 
     * @param userId
     * @param friendId
     * @param acceptStatus
     * @return int
     */
    int updateUserFriendAcceptStatus(Long userId, Long friendId, RelationAcceptStatusEnum acceptStatus);

    /**
     * 更新好友关系接受状态
     * 
     * @param userFriendId
     * @param acceptStatus
     * @return int
     */
    int updateUserFriendAcceptStatus(Long id, RelationAcceptStatusEnum acceptStatus);

    /**
     * 更新好友关系类型
     * 
     * @param userId
     * @param friendId
     * @param relationType
     * @param status
     */
    void updateUserFriendRelationType(Long userId, Long friendId, RelationTypeEnum relationType, StatusEnum status);

    /**
     * 根据接受状态得到用户的好友
     * 
     * @param userId
     * @param acceptStatus
     * @return
     */
    List<UserFriend> queryByAcceptStatus(Long userId, RelationAcceptStatusEnum acceptStatus);

    /**
     * 得到用户的好友
     * 
     * @param userId
     * @return
     */
    List<UserFriend> queryByUid(Long userId);

    /**
     * 通过uid和fid查询UserFriend
     */
    UserFriend queryByUidAndFid(Long userId, Long friendId);

    /**
     * 根据userId查询出好友关系
     * 
     * @param userId
     * @param acceptStatus
     * @return
     */
    List<FriendInfoVo> queryFriendByUid(Long userId, RelationAcceptStatusEnum acceptStatus);
}
