package com.footmark.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.dao.impl.UserFriendDaoImpl;
import com.footmark.core.entity.UserFriend;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.enums.RelationTypeEnum;
import com.footmark.core.service.UserFriendService;
import com.footmark.core.vo.FriendInfoVo;

/**
 * @Description: 用户好友关系服务实现
 * @author carlos.chu
 * @date 2015年9月11日
 */
@Service
public class UserFriendServiceImpl implements UserFriendService {

    private static final String UPDATE_ACCEPT_STATUS = "updateAcceptStatus";
    private static final String UPDATE_RELATION_TYPE = "updateRelationType";
    private static final String QUERY_BY_ACCEPT_STATUS = "queryByAcceptStatus";
    private static final String QUERY_BY_UID_FID = "queryByUidAndFid";
    private static final String QUERY_BY_UID = "queryByUid";
    private static final String QUERY_FRIEND_BY_UID = "queryFriendByUid";
    @Autowired
    private UserFriendDaoImpl userFriendDao;

    @Override
    public int updateUserFriendAcceptStatus(Long userId, Long friendId, RelationAcceptStatusEnum acceptStatus) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("friendId", friendId);
        paramMap.put("acceptStatus", acceptStatus);
        return userFriendDao.update(UPDATE_ACCEPT_STATUS, paramMap);
    }

    @Override
    public int updateUserFriendAcceptStatus(Long userFriendId, RelationAcceptStatusEnum acceptStatus) {
        UserFriend userFriend = new UserFriend();
        userFriend.setId(userFriendId);
        userFriend.setAcceptStatus(acceptStatus);
        return userFriendDao.update(userFriend);
    }

    @Override
    public void updateUserFriendRelationType(Long userId, Long friendId, RelationTypeEnum relationType,
            StatusEnum status) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("friendId", friendId);
        paramMap.put("relationType", relationType);
        paramMap.put("status", status);
        userFriendDao.update(UPDATE_RELATION_TYPE, paramMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserFriend> queryByAcceptStatus(Long userId, RelationAcceptStatusEnum acceptStatus) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("acceptStatus", acceptStatus);
        paramMap.put("status", StatusEnum.NORMAL);
        return userFriendDao.query(QUERY_BY_ACCEPT_STATUS, paramMap);
    }

    @Override
    public UserFriend queryByUidAndFid(Long userId, Long friendId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("friendId", friendId);
        return (UserFriend) userFriendDao.queryOne(QUERY_BY_UID_FID, paramMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserFriend> queryByUid(Long userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", StatusEnum.NORMAL);
        return userFriendDao.query(QUERY_BY_UID, paramMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FriendInfoVo> queryFriendByUid(Long userId, RelationAcceptStatusEnum acceptStatus) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", StatusEnum.NORMAL);
        paramMap.put("acceptStatus", acceptStatus);
        return userFriendDao.query(QUERY_FRIEND_BY_UID, paramMap);
    }

}
