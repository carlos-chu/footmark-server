package com.footmark.core.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.core.biz.MultiMediaBiz;
import com.footmark.core.dao.impl.MultiMediaDaoImpl;
import com.footmark.core.dao.impl.SourceMultiDaoImpl;
import com.footmark.core.entity.MultiMedia;
import com.footmark.core.entity.SourceMulti;
import com.footmark.core.enums.SourceMultiTypeEnum;
import com.footmark.core.service.MultiMediaService;

/**
 * @Description:
 * @author carlos.chu
 * @version 1.0 2016年2月1日 下午5:44:12
 */
@Service
public class MultiMediaBizImpl implements MultiMediaBiz {

    @Autowired
    private MultiMediaService multiMediaService;
    @Autowired
    private MultiMediaDaoImpl multiMediaDao;
    @Autowired
    private SourceMultiDaoImpl sourceMultiDao;

    @Override
    @Transactional
    public void create(Long sourceId, MultiMedia multiMedia) {
        multiMediaDao.add(multiMedia);
        if (sourceId == null) {
            throw new BusinessException(ExceptionEnum.DATA_ISEMPTY_ERR);
        }
        SourceMulti sm = new SourceMulti();
        sm.setSourceId(sourceId);
        sm.setMultiId(multiMedia.getId());
        sm.setType(SourceMultiTypeEnum.FOOTMARK);
        sourceMultiDao.add(sm);
    }

}
