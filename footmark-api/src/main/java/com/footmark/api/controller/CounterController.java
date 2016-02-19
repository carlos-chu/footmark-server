package com.footmark.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.CounterCancelReqDto;
import com.footmark.api.dto.CounterInReqDto;
import com.footmark.core.biz.impl.CounterBizImpl;
import com.footmark.core.entity.Counter;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 计数api
 * @author carlos.chu
 * @date 2015年8月28日
 */
@Controller
@RequestMapping("/api/counter")
public class CounterController extends BaseController {

    @Autowired
    private CounterBizImpl counterBiz;

    /**
     * 创建计数
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDto<Long> countIn(final CounterInReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Long>() {
            @Override
            public Long doInTransaction(RequestDto request) throws Exception {
                Counter counter = new Counter();
                BeanCopierUtil.copy(dto, counter, true);
                return counterBiz.countIn(counter);
            }
        });
    }

    /**
     * 取消计数
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResponseDto<Void> countCancel(final CounterCancelReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) throws Exception {
                counterBiz.countCancel(dto.getCounterId());
                return null;
            }
        });
    }
}
