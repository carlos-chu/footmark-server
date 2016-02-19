package com.footmark.core.biz.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.common.util.DateUtil;
import com.footmark.core.biz.LetterBiz;
import com.footmark.core.biz.PushBiz;
import com.footmark.core.dao.impl.LetterDaoImpl;
import com.footmark.core.entity.Letter;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.core.enums.ReadStatusEnum;
import com.footmark.core.service.LetterService;
import com.footmark.core.service.UserInfoService;
import com.footmark.core.vo.LetterVo;
import com.footmark.gateway.push.PushConstant;
import com.footmark.gateway.push.entity.AndroidNotification;

/**
 * @Description: 私信业务实现
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午3:30:54
 */
@Service
public class LetterBizImpl implements LetterBiz {

    @Autowired
    private LetterDaoImpl letterDao;
    @Autowired
    private PushBiz pushBiz;
    @Autowired
    private LetterService letterService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public LetterVo send(Letter letter) {
        UserInfo fromUser = userInfoService.queryByUserId(letter.getFromUid());
        if (fromUser == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND_ERR);
        }
        letter.setReadStatus(ReadStatusEnum.UNREAD);
        letterDao.add(letter);
        LetterVo letterVo = populateLetterVo(letter, fromUser);
        // 发送通知给接收方---随后另起线程
        AndroidNotification notification = new AndroidNotification();
        notification.setTitle(fromUser.getName() + "给你发送一条私信");
        notification.setDescription(letter.getContent());
        notification.setOpen_type(2);
        notification.setPkg_content("#Intent;component=com.cwq.footmark/.ui.activity.LetterListActivity;end");
        JSONObject json = new JSONObject();
        json.put(PushConstant.FROM_UID, letter.getFromUid());
        json.put(PushConstant.PROMPT_TYPE, PromptTypeEnum.PRIVATE_LETTER.getCode());
        json.put(PushConstant.OBJECT, letterVo.toString());
        notification.setCustom_content(json);
        pushBiz.pushNotificationToSingle(letter.getToUid(), notification);
        return letterVo;
    }

    @Override
    public void updateReadStatus(Long letterId, int flag) {
        if (flag == 1) {
            letterDao.updateReadStatus(letterId);
        } else {
            letterDao.updateToDelete(letterId);
        }
    }

    @Override
    public List<LetterVo> query(Long userId, ReadStatusEnum readStatus) {
        List<LetterVo> letters = letterService.query(userId, readStatus);
        return letters;
    }

    private LetterVo populateLetterVo(Letter letter, UserInfo fromUser) {
        LetterVo letterVo = new LetterVo();
        letterVo.setId(letter.getId());
        letterVo.setFromUid(letter.getFromUid());
        letterVo.setFromName(fromUser.getName());
        letterVo.setFromAvatar(fromUser.getAvatar());
        letterVo.setContent(letter.getContent());
        letterVo.setCreateTime(DateUtil.DateTimeToString(letter.getCreateTime()));
        letterVo.setReadStatus(letter.getReadStatus().getCode());
        letterVo.setParentId(letter.getParentId());
        return letterVo;
    }

    @Override
    public List<LetterVo> queryDetail(Long letterId) {
        return letterService.queryDetail(letterId);
    }
}
