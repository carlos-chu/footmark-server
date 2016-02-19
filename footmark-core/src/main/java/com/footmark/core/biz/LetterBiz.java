package com.footmark.core.biz;

import java.util.List;

import com.footmark.core.entity.Letter;
import com.footmark.core.enums.ReadStatusEnum;
import com.footmark.core.vo.LetterVo;

/**
 * @Description: 私信业务
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午3:16:11
 */
public interface LetterBiz {

    /**
     * 发送私信，这是本系统内处理逻辑
     * 
     * @param letter
     * @return
     */
    LetterVo send(Letter letter);

    /**
     * 更新私信阅读状态为已读
     * 
     * @param letterId
     * @param flag
     */
    void updateReadStatus(Long letterId, int flag);

    /**
     * 查询用户的私信
     * 
     * @param userId
     * @param readStatus
     *            可为空
     * @return
     */
    List<LetterVo> query(Long userId, ReadStatusEnum readStatus);

    /**
     * 查询私信详情
     * 
     * @param letterId
     * @return
     */
    List<LetterVo> queryDetail(Long letterId);

}
