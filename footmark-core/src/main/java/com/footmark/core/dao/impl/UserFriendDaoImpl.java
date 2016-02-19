package com.footmark.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.UserFriendDao;
import com.footmark.core.entity.UserFriend;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class UserFriendDaoImpl extends GenericDaoDefault<UserFriend> implements UserFriendDao {

}
