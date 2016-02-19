package com.footmark.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.PushInfoReqDto;
import com.footmark.api.dto.PushMsgReqDto;
import com.footmark.core.biz.PushBiz;
import com.footmark.core.entity.PushInfo;
import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.core.service.PushInfoService;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 第三方相关的接口
 * @author carlos.chu
 * @version 1.0 2015年12月31日 上午10:31:57
 */
@Controller
@RequestMapping("/api/push")
public class PushController extends BaseController {

    @Autowired
    private PushInfoService pushInfoService;
    @Autowired
    private PushBiz pushBiz;

    /**
     * 创建pushInfo
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/createPushInfo", method = RequestMethod.POST)
    public ResponseDto<Void> createPushInfo(final PushInfoReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) throws Exception {
                PushInfo target = new PushInfo();
                BeanCopierUtil.copy(request, target, false);
                pushInfoService.add(target);
                return null;
            }
        });
    }

    /**
     * 更新pushInfo
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePushInfo", method = RequestMethod.POST)
    public ResponseDto<Void> updatePushInfo(final PushInfoReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) throws Exception {
                PushInfo target = new PushInfo();
                BeanCopierUtil.copy(request, target, false);
                pushInfoService.updateByUserId(target);
                return null;
            }
        });
    }

    /**
     * 向单设备推送透传消息
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/passMsg/pushToSingle", method = RequestMethod.POST)
    public ResponseDto<Void> pushToSinglePassMsg(final PushMsgReqDto dto) {
        return handlePost(dto, new BizCallback<PushMsgReqDto, Void>() {
            @Override
            public Void doInTransaction(PushMsgReqDto request) throws Exception {
                pushBiz.pushPassMsgToSingle(request.getFromUid(), request.getToUid(),
                        PromptTypeEnum.toEnum(request.getPushType()), null);
                return null;
            }
        });
    }
}
