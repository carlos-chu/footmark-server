package com.footmark.core.biz;

import java.util.List;

import com.footmark.core.entity.UserSetting;
import com.footmark.core.vo.SettingVo;

/**
 * @Description: 用户设置接口
 * @author carlos.chu
 * @version 1.0 2015年12月16日 下午2:54:42
 */
public interface UserSettingBiz {

    /**
     * 用户设置
     * 
     * @param userSetting
     */
    void setSetting(UserSetting userSetting);

    /**
     * 根据uid得到设置
     * 
     * @param userId
     * @return
     */
    List<SettingVo> queryByUid(Long userId);
}
