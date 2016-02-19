package com.footmark.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.SettingReqDto;
import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.core.biz.UserSettingBiz;
import com.footmark.core.entity.UserSetting;
import com.footmark.core.enums.UserSettingEnum;
import com.footmark.core.vo.SettingVo;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;

/**
 * @Description: 用户设置
 * @author carlos.chu
 * @version 1.0 2015年12月16日 下午2:40:57
 */
@Controller
@RequestMapping("/api/setting")
public class UserSettingController extends BaseController {

    @Autowired
    private UserSettingBiz userSettingBiz;

    /**
     * 设置
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ResponseDto<Void> setting(SettingReqDto request) {
        return handlePost(request, new BizCallback<SettingReqDto, Void>() {
            @Override
            public Void doInTransaction(SettingReqDto request) throws Exception {
                for (int key : request.getKeys()) {
                    UserSetting userSetting = new UserSetting(request.getUserId(), UserSettingEnum.toEnum(key));
                    userSettingBiz.setSetting(userSetting);
                }
                return null;
            }
        });
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseDto<List<SettingVo>> query(@RequestParam(required = true, value = "userId") final Long userId) {
        return handleGet(new BizCallback<RequestDto, List<SettingVo>>() {
            @Override
            public List<SettingVo> doInTransaction(RequestDto request) throws Exception {
                if (userId == null) {
                    throw new BusinessException(ExceptionEnum.PARAM_ERR);
                }
                return userSettingBiz.queryByUid(userId);
            }
        });
    }
}
