package com.footmark.core.service;

import java.util.List;

import com.footmark.core.dto.FootmarkDto;
import com.footmark.core.enums.AccepterCategoryEnum;
import com.footmark.core.enums.GenderEnum;

/**
 * @Description: 用户脚印服务
 * @author carlos.chu
 * @date 2015年8月20日
 */
public interface UserFootmarkService {

    /**
     * 根据设置获取一定范围内推荐给用户的脚印
     * 
     * @param selfGender
     *            用户自己的性别--用于匹配脚印发表者的设置接受者性别
     * @param settingGender
     *            用户设置接受发表脚印者的性别--用于匹配脚印接受者的设置发表者性别
     * @param coordinates
     *            位置坐标的id
     * @return
     */
    List<FootmarkDto> queryByScope(GenderEnum selfGender, AccepterCategoryEnum settingGender, List<Long> coordinates,
            int from, int to);

    /**
     * 得到用户的脚印
     * 
     * @param uid
     * @return
     */
    List<FootmarkDto> queryByUid(Long uid, int page, int size);

}
