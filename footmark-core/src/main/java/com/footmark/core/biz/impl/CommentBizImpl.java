package com.footmark.core.biz.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.enums.StatusEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.common.util.DateUtil;
import com.footmark.common.util.StringUtil;
import com.footmark.core.biz.CommentBiz;
import com.footmark.core.biz.PushBiz;
import com.footmark.core.dao.impl.CommentDaoImpl;
import com.footmark.core.dao.impl.UserFootmarkDaoImpl;
import com.footmark.core.entity.Comment;
import com.footmark.core.entity.Footmark;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.enums.CommentSourceTypeEnum;
import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.core.service.UserInfoService;
import com.footmark.core.vo.CommentVo;
import com.footmark.gateway.push.PushConstant;
import com.footmark.gateway.push.entity.AndroidNotification;

/**
 * @Description: 评论业务实现
 * @author carlos.chu
 * @version 1.0 2016年1月18日 下午5:39:20
 */
@Service
public class CommentBizImpl implements CommentBiz {

    @Autowired
    private CommentDaoImpl commentDao;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PushBiz pushBiz;
    @Autowired
    private UserFootmarkDaoImpl footmarkDao;

    @Override
    public CommentVo create(Comment comment) {
        UserInfo user = userInfoService.queryByUserId(comment.getUserId());
        Footmark footmark = footmarkDao.get(comment.getSourceId());
        if (user == null || footmark == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND_ERR);
        }
        commentDao.add(comment);
        CommentVo commentVo = populateCommentVo(comment, footmark, user);
        // 发送推送消息
        AndroidNotification notification = new AndroidNotification();
        notification.setTitle(user.getName() + "评论了你的脚印");
        notification.setDescription(comment.getContent());
        notification.setOpen_type(2);
        notification.setPkg_content("#Intent;component=com.cwq.footmark/.ui.activity.UserFootmarkActivity;end");
        JSONObject json = new JSONObject();
        json.put(PushConstant.FROM_UID, comment.getUserId());
        json.put(PushConstant.PROMPT_TYPE, PromptTypeEnum.COMMENT.getCode());
        json.put(PushConstant.OBJECT, commentVo.toString());
        notification.setCustom_content(json);
        pushBiz.pushNotificationToSingle(comment.getTargetUserId(), notification);
        return commentVo;
    }

    private CommentVo populateCommentVo(Comment comment, Footmark footmark, UserInfo user) {
        CommentVo commentVo = new CommentVo();
        commentVo.setId(comment.getId());
        commentVo.setContent(comment.getContent());
        commentVo.setCreateTime(DateUtil.DateTimeToString(comment.getCreateTime()));
        commentVo.setUserAvatar(user.getAvatar());
        commentVo.setUserName(user.getName());
        commentVo.setUserId(user.getId());
        if (comment.getTargetUserId() != null) {
            UserInfo targetUser = userInfoService.queryByUserId(comment.getTargetUserId());
            commentVo.setReplyUserId(targetUser.getId());
            commentVo.setReplyUserName(targetUser.getName());
        }
        commentVo.setUniqId(comment.getSourceId().toString());
        commentVo.setSource(StringUtil.getString(footmark.getContent(), 20));
        return commentVo;
    }

    @Override
    public void update(Long id, StatusEnum statusEnum) {
        int result = commentDao.updateStatus(id, statusEnum);
        if (result != 1) {
            throw new BusinessException(ExceptionEnum.DATA_ISEMPTY_ERR);
        }
    }

    @Override
    public List<CommentVo> query(Long sourceId, CommentSourceTypeEnum sourceType) {
        return commentDao.query(sourceId, sourceType);
    }

}
