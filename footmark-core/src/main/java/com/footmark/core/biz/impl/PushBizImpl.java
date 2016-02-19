package com.footmark.core.biz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.footmark.core.biz.PushBiz;
import com.footmark.core.entity.PushInfo;
import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.core.service.PushInfoService;
import com.footmark.gateway.push.AndroidPushMsg;
import com.footmark.gateway.push.PushConstant;
import com.footmark.gateway.push.entity.AndroidNotification;
import com.footmark.gateway.push.entity.PushMessage;

/**
 * @Description: 推送业务实现
 * @author carlos.chu
 * @version 1.0 2015年12月31日 下午2:52:13
 */
@Service
public class PushBizImpl implements PushBiz {

    private static final Logger logger = LoggerFactory.getLogger(PushBizImpl.class);
    @Autowired
    private PushInfoService pushInfoService;

    @Override
    public void pushPassMsgToSingle(Long fromUid, Long toUid, PromptTypeEnum pushType, String msg) {
        PushInfo pushInfo = pushInfoService.queryByUid(toUid);
        if (pushInfo != null) {
            logger.info("推送给单设备，pushInfo:{}", pushInfo);
            AndroidPushMsg pushMsg = new AndroidPushMsg();
            PushMessage message = new PushMessage();
            JSONObject json = new JSONObject();
            json.put(PushConstant.FROM_UID, fromUid);
            json.put(PushConstant.PROMPT_TYPE, pushType.getCode());
            if (msg != null) {
                json.put(PushConstant.MESSAGE, msg);
            }
            message.setMessage(json.toJSONString());
            try {
                pushMsg.pushToSingleDevice(AndroidPushMsg.MessageType.PASS_MESSAGE.code, pushInfo.getBdChannelId(),
                        message);
            } catch (Exception e) {
                logger.error("推送给单设备出错", e);
            }
        }
    }

    @Override
    public void pushNotificationToSingle(Long toUid, AndroidNotification message) {
        PushInfo pushInfo = pushInfoService.queryByUid(toUid);
        if (pushInfo != null) {
            logger.info("推送给单设备，pushInfo:{}", pushInfo);
            AndroidPushMsg pushMsg = new AndroidPushMsg();
            try {
                pushMsg.pushToSingleDevice(AndroidPushMsg.MessageType.NOTIFICATION.code, pushInfo.getBdChannelId(),
                        message);
            } catch (Exception e) {
                logger.error("推送给单设备出错", e);
            }
        }
    }

}
