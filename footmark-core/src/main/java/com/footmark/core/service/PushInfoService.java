package com.footmark.core.service;

import com.footmark.core.entity.PushInfo;

/**
 * @Description: 推送信息设备服务
 * @author carlos.chu
 * @date 2015年8月20日
 */
public interface PushInfoService {

    /**
     * 添加pushInfo
     * 
     * @param pushInfo
     */
    void add(PushInfo pushInfo);

    /**
     * 根据userId更新pushInfo
     * 
     * @param pushInfo
     */
    void updateByUserId(PushInfo pushInfo);

    /**
     * 通过uid查询
     * 
     * @param uid
     * @return
     */
    PushInfo queryByUid(Long uid);
}
