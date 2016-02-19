package com.footmark.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.RegisterReqDto;
import com.footmark.api.dto.UserLoginReqDto;
import com.footmark.api.dto.ValidateTelIdentityingCodeReqDto;
import com.footmark.api.dto.ValidateTelNoReqDto;
import com.footmark.core.biz.impl.UserInfoBizImpl;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.vo.FriendInfoVo;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 用户信息相关
 * @author carlos.chu
 * @date 2015年8月19日
 */
@Controller
@RequestMapping("/api/user")
public class UserInfoController extends BaseController {

    @Autowired
    UserInfoBizImpl userInfoBiz;

    /**
     * 给用户下发短信验证码
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validTelNo", method = RequestMethod.POST)
    public ResponseDto<Void> validateTelNo(final ValidateTelNoReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) {
                return userInfoBiz.validateTelNo(dto.getMobileNo());
            }
        });
    }

    /**
     * 校验短信验证码
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validIdentifyingCode", method = RequestMethod.POST)
    public ResponseDto<Void> validateIdentifyingCode(final ValidateTelIdentityingCodeReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) {
                return userInfoBiz.validateIdentifyingCode(dto.getMobileNo(), dto.getIdentityingCode());
            }
        });
    }

    /**
     * 注册用户
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseDto<Long> register(final RegisterReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Long>() {
            @Override
            public Long doInTransaction(RequestDto request) throws Exception {
                UserInfo user = new UserInfo();
                BeanCopierUtil.copy(dto, user, true);
                return userInfoBiz.register(user);
            }
        });
    }

    /**
     * 登录
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDto<UserInfo> login(final UserLoginReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, UserInfo>() {
            @Override
            public UserInfo doInTransaction(RequestDto request) throws Exception {
                UserInfo user = new UserInfo();
                BeanCopierUtil.copy(dto, user, true);
                return userInfoBiz.login(user);
            }
        });
    }

    /**
     * 通过id查找用户详细信息
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseDto<FriendInfoVo> queryById(@RequestParam(value = "uid", required = true) final Long uid,
            @RequestParam(value = "fid", required = true) final Long fid) {
        return handleGet(new BizCallback<RequestDto, FriendInfoVo>() {
            @Override
            public FriendInfoVo doInTransaction(RequestDto request) throws Exception {
                return userInfoBiz.queryFriendById(uid, fid);
            }
        });
    }
}
