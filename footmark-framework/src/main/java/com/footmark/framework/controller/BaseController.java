package com.footmark.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.footmark.common.exception.BusinessException;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BaseInfoValidater;

/**
 * @Description: 公共的controller，处理数据的进出
 * @author carlos.chu
 * @date 2015年8月17日
 */
public abstract class BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 内部接口，实现该接口的内部类进行具体的业务,返回值
     * 
     * @param <R>
     * @param <T>
     */
    public interface BizCallback<R, T> {

        T doInTransaction(R request) throws Exception;
    }

    public <R extends RequestDto, T> ResponseDto<T> handlePost(R request, BizCallback<R, T> callback) {
        logger.info("biz start at {}, param is : {}", System.currentTimeMillis(), request);
        ResponseDto<T> response = new ResponseDto<T>();
        try {
            // 1.check params
            BaseInfoValidater.checkParam(request);
            // 2.do biz
            T resultData = callback.doInTransaction(request);
            // 3.build result
            response.succ(resultData);
        } catch (BusinessException e) {
            response.err(e);
            logger.error("ServiceException errCode:【{}】,errMsg:【{}】,cause by", e.getErrCode(), e.getErrChineseMsg(), e);
        } catch (Throwable e) {
            response.err(e);
            logger.error("Exception has happen, cause by", e);
        } finally {
            logger.info("biz end at {}, response is {}", System.currentTimeMillis(), response);
        }
        return response;
    }

    public <R, T> ResponseDto<T> handleGet(BizCallback<R, T> callback) {
        logger.info("biz start at {}", System.currentTimeMillis());
        ResponseDto<T> response = new ResponseDto<T>();
        try {
            // 1.do biz
            T resultData = callback.doInTransaction(null);
            // 2.build result
            response.succ(resultData);
        } catch (BusinessException e) {
            response.err(e);
            logger.error("ServiceException errCode:【{}】,errMsg:【{}】,cause by", e.getErrCode(), e.getErrChineseMsg(), e);
        } catch (Throwable e) {
            response.err(e);
            logger.error("Exception has happen, cause by", e);
        } finally {
            logger.info("biz end at {}, response is {}", System.currentTimeMillis(), response);
        }
        return response;
    }
}
