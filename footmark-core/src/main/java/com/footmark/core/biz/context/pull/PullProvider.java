package com.footmark.core.biz.context.pull;

import java.util.List;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 拉取脚印的组件提供接口
 * @author carlos.chu
 * @date 2015年9月1日
 */
public interface PullProvider {

    /**
     * 拉取系统推荐的脚印
     * 
     * @return
     */
    <E extends EntityVo> List<E> pull();

    /**
     * 根据用户拉取系统推荐的个性化的脚印
     * 
     * @param userId
     * @return
     */
    <E extends EntityVo> List<E> pull(Long... userId);
}
