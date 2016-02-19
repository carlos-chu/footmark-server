package com.footmark.core.biz.context.pull;

import java.util.List;

import com.footmark.core.biz.context.pull.PullWrapperImpl.UserPull;
import com.footmark.core.dto.FootmarkPullDto;
import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 用户拉取脚印包装器
 * @author carlos.chu
 * @date 2015年9月1日
 */
public interface PullWrapper {

    /**
     * 实现用户拉取脚印数据包装
     * 
     * @param dto
     * @param pullProvider
     *            选择不同的provider产生不同的系统拉取数据
     * @param userPull
     *            new出来，子方法实现具体的用户数据拉取
     * @return E extends EntityVo
     */
    <E extends EntityVo> List<E> pull(FootmarkPullDto dto, PullProvider pullProvider, UserPull<E> userPull);
}
