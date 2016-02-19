package com.footmark.core.biz.context.pull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footmark.core.dto.FootmarkPullDto;
import com.footmark.core.entity.UserSetting;
import com.footmark.core.enums.UserSettingEnum;
import com.footmark.core.service.impl.UserSettingServiceImpl;
import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 拉取脚印前置接口
 * @author carlos.chu
 * @date 2015年9月1日
 */
@Component("pullWrapper")
public class PullWrapperImpl implements PullWrapper {

    @Autowired
    private UserSettingServiceImpl userSettingService;

    public interface UserPull<E extends EntityVo> {
        /**
         * 拉取用户的脚印
         * 
         * @param dto
         * @return
         */
        List<E> pull(FootmarkPullDto dto, Map<String, Object> settingMap);
    }

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
    public <E extends EntityVo> List<E> pull(FootmarkPullDto dto, PullProvider pullProvider, UserPull<E> userPull) {
        // 1.拉取前置先拉取，可植入一些系统东西，或者朋友优质的脚印，或者特别关注者的脚印
        List<E> providerPulls = pullProvider.pull();
        // 2.获取用户设置
        List<UserSetting> userSettings = userSettingService.query(dto.getUserId());
        // 3.拉取用户需要的脚印
        List<E> userPullList = userPull.pull(dto, settingCoverToMap(userSettings));
        if (providerPulls != null && providerPulls.size() > 0) {
            providerPulls.addAll(userPullList);
            return providerPulls;
        }
        // 4.只返回用户的脚印
        return userPullList;
    }

    // 把用户设置转化为map
    private Map<String, Object> settingCoverToMap(List<UserSetting> settings) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (UserSetting userSetting : settings) {
            UserSettingEnum setting = userSetting.getKey();
            if (setting.getDomain().equals(UserSetting.SCOPE)) {
                int acceptScope = userSetting.getValue();
                resultMap.put(UserSetting.SCOPE, acceptScope);
            }
            if (setting.getDomain().equals(UserSetting.GENDER_CATEGORY)) {
                int acceptCategory = userSetting.getValue();
                resultMap.put(UserSetting.GENDER_CATEGORY, acceptCategory);
            }
        }
        return resultMap;
    }
}
