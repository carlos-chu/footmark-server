package com.footmark.core.biz.context.relation;

import com.footmark.core.enums.RelationTypeEnum;

/**
 * @Description: 用户关系过滤接口
 * @author carlos.chu
 * @version 1.0 2015年12月16日 下午4:57:33
 */
public interface RelationFilter {

    /**
     * 过滤关系，例如拉黑，删除，特别关心，暗恋，喜欢等
     * 
     * @param userId
     * @param friendId
     * @return RelationTypeEnum
     */
    RelationTypeEnum filterRelation(Long userId, Long friendId);
}
