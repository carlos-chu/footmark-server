package com.footmark.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.core.dao.impl.LetterDaoImpl;
import com.footmark.core.enums.ReadStatusEnum;
import com.footmark.core.service.LetterService;
import com.footmark.core.vo.LetterVo;

/**
 * @Description: 私信服务实现
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午4:17:27
 */
@Service
public class LetterServiceImpl implements LetterService {

    @Autowired
    private LetterDaoImpl letterDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<LetterVo> query(Long uid, ReadStatusEnum readStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("readStatus", readStatus);
        return (List<LetterVo>) letterDao.query("queryByUid", params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LetterVo> queryDetail(Long letterId) {
        Map<String, Object> params = new HashMap<>();
        params.put("letterId", letterId);
        return letterDao.query("queryDetail", params);
    }
}
