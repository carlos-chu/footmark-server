package com.footmark.core.biz.context.pull;

import java.util.List;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 默认拉取脚印的组件提供者
 * @author carlos.chu
 * @date 2015年9月1日
 */
public class DefaultPullProvider implements PullProvider {

    @Override
    public <E extends EntityVo> List<E> pull() {
        // 此处可随机推荐一条系统推荐脚印
        return null;
    }

    @Override
    public <E extends EntityVo> List<E> pull(Long... userId) {
        return null;
    }

}
