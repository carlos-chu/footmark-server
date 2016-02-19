package com.footmark.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.FriendReqDto;
import com.footmark.core.biz.PushBiz;
import com.footmark.core.biz.UserFriendBiz;
import com.footmark.core.entity.UserFriend;
import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.vo.FriendInfoVo;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 用户朋友关系
 * @author carlos.chu
 * @version 1.0 2015年12月16日 下午4:20:05
 */
@Controller
@RequestMapping("/api/friend")
public class UserFriendController extends BaseController {

    @Autowired
    private UserFriendBiz userFriendBiz;
    @Autowired
    private PushBiz pushBiz;

    /**
     * 好友申请
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseDto<Void> apply(FriendReqDto request) {
        return handlePost(request, new BizCallback<FriendReqDto, Void>() {
            @Override
            public Void doInTransaction(FriendReqDto request) throws Exception {
                UserFriend userFriend = new UserFriend();
                BeanCopierUtil.copy(request, userFriend, false);
                boolean applyFlag = userFriendBiz.apply(userFriend);
                if (applyFlag) {
                    pushBiz.pushPassMsgToSingle(userFriend.getUserId(), userFriend.getFriendId(),
                            PromptTypeEnum.APPLY_FRIEND, null);
                }
                return null;
            }
        });
    }

    /**
     * 同意添加
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    public ResponseDto<Void> agree(FriendReqDto request) {
        return handlePost(request, new BizCallback<FriendReqDto, Void>() {
            @Override
            public Void doInTransaction(FriendReqDto request) throws Exception {
                UserFriend userFriend = new UserFriend();
                BeanCopierUtil.copy(request, userFriend, false);
                boolean agreeFlag = userFriendBiz.agree(userFriend);
                if (agreeFlag) {
                    pushBiz.pushPassMsgToSingle(userFriend.getUserId(), userFriend.getFriendId(),
                            PromptTypeEnum.AGTEE_FRIEND, null);
                }
                return null;
            }
        });
    }

    /**
     * 拒绝添加
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    public ResponseDto<Void> refuse(FriendReqDto request) {
        return handlePost(request, new BizCallback<FriendReqDto, Void>() {
            @Override
            public Void doInTransaction(FriendReqDto request) throws Exception {
                UserFriend userFriend = new UserFriend();
                BeanCopierUtil.copy(request, userFriend, false);
                userFriendBiz.refuse(userFriend);
                return null;
            }
        });
    }

    /**
     * 得到某个人的好友及关系
     * 
     * @param userId
     * @param acceptStatus
     *            nullable
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseDto<List<FriendInfoVo>> queryByUid(
            @RequestParam(required = true, value = "userId") final Long userId,
            @RequestParam(required = false, value = "acceptStatus") final Integer acceptStatus) {
        return handleGet(new BaseController.BizCallback<RequestDto, List<FriendInfoVo>>() {
            @Override
            public List<FriendInfoVo> doInTransaction(RequestDto request) throws Exception {
                RelationAcceptStatusEnum acceptStatusEnum = null;
                if (acceptStatus != null) {
                    acceptStatusEnum = RelationAcceptStatusEnum.toEnum(acceptStatus);
                }
                return userFriendBiz.queryFriendInfoByUserId(userId, acceptStatusEnum);
            }
        });
    }
}
