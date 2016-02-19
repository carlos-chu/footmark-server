package com.footmark.core.biz;

import java.util.List;

import com.footmark.core.dto.CreateFootmarkDto;
import com.footmark.core.dto.FootmarkPullDto;
import com.footmark.core.vo.FootmarkVo;

/**
 * @Description: 脚印业务接口
 * @author carlos.chu
 * @date 2015年8月21日
 */
public interface FootmarkBiz {

    /**
     * 创建脚印
     * 
     * @param dto
     * @return footmarkId
     */
    Long create(CreateFootmarkDto dto);

    /**
     * 用户根据用户设置及系统设置拉取脚印
     * 
     * @param FootmarkPullDto
     * @return
     */
    List<FootmarkVo> pull(FootmarkPullDto dto);

    /**
     * 得到用户的脚印
     * 
     * @param uid
     * @param page
     * @param size
     * @return
     */
    List<FootmarkVo> queryByUid(Long uid, int page, int size);

    /**
     * 得到脚印
     * 
     * @param id
     * @return
     */
    FootmarkVo queryById(Long id);
}
