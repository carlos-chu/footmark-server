package com.footmark.core.biz;

import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.gateway.push.entity.AndroidNotification;

/**
 * @Description: 推送业务
 * @author carlos.chu
 * @date 2015年8月19日
 */
public interface PushBiz {

    /**
     * 推送给单个设备
     * 
     * @param fromUid
     *            发起者
     * @param toUid
     *            接受者
     * @param pushType
     * @param msg
     */
    void pushPassMsgToSingle(Long fromUid, Long toUid, PromptTypeEnum pushType, String msg);

    /**
     * 推送通知给单个用户
     * 
     * @param toUid
     *            接受者
     * @param notification
     */
    void pushNotificationToSingle(Long toUid, AndroidNotification message);
}
