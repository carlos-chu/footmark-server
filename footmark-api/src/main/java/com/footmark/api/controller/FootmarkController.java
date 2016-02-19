package com.footmark.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.CreateFootmarkReqDto;
import com.footmark.api.dto.FootmarkReqDto;
import com.footmark.core.biz.impl.FootmarkBizImpl;
import com.footmark.core.dto.CreateFootmarkDto;
import com.footmark.core.dto.FootmarkPullDto;
import com.footmark.core.vo.FootmarkVo;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 脚印的api
 * @author carlos.chu
 * @date 2015年8月24日
 */
@Controller
@RequestMapping("/api/footmark")
public class FootmarkController extends BaseController {

    @Autowired
    private FootmarkBizImpl footmarkBiz;

    /**
     * 创建脚印
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDto<Long> createFootmark(final CreateFootmarkReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Long>() {
            @Override
            public Long doInTransaction(RequestDto request) throws Exception {
                CreateFootmarkDto targetDto = new CreateFootmarkDto();
                BeanCopierUtil.copy(dto, targetDto, true);
                return footmarkBiz.create(targetDto);
            }
        });
    }

    /**
     * 得到普通脚印
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pull", method = RequestMethod.POST)
    public ResponseDto<List<FootmarkVo>> queryNormalFootmark(final FootmarkReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, List<FootmarkVo>>() {
            @Override
            public List<FootmarkVo> doInTransaction(RequestDto request) throws Exception {
                FootmarkPullDto targetDto = new FootmarkPullDto();
                BeanCopierUtil.copy(dto, targetDto, false);
                return footmarkBiz.pull(targetDto);
            }
        });
    }

    /**
     * 得到某用户脚印
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryByUid", method = RequestMethod.GET)
    public ResponseDto<List<FootmarkVo>> queryByUserId(
            @RequestParam(required = true, value = "userId") final Long userId,
            @RequestParam(required = true, value = "page") final Integer page,
            @RequestParam(required = true, value = "size") final Integer size) {
        return handleGet(new BizCallback<RequestDto, List<FootmarkVo>>() {
            @Override
            public List<FootmarkVo> doInTransaction(RequestDto request) throws Exception {
                return footmarkBiz.queryByUid(userId, page, size);
            }
        });
    }

    /**
     * 得到某一脚印
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public ResponseDto<FootmarkVo> queryById(@RequestParam(required = true, value = "id") final Long id) {
        return handleGet(new BizCallback<RequestDto, FootmarkVo>() {
            @Override
            public FootmarkVo doInTransaction(RequestDto request) throws Exception {
                if (id == null || id == 0) {
                    return null;
                }
                return footmarkBiz.queryById(id);
            }
        });
    }
}
