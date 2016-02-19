package com.footmark.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.LetterReqDto;
import com.footmark.api.dto.LetterUpdateReqDto;
import com.footmark.core.biz.LetterBiz;
import com.footmark.core.entity.Letter;
import com.footmark.core.enums.ReadStatusEnum;
import com.footmark.core.vo.LetterVo;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 私信api
 * @author carlos.chu
 * @date 2015年8月28日
 */
@Controller
@RequestMapping("/api/letter")
public class LetterController extends BaseController {

    @Autowired
    private LetterBiz letterBiz;

    /**
     * 发送私信
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseDto<LetterVo> send(final LetterReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, LetterVo>() {
            @Override
            public LetterVo doInTransaction(RequestDto request) throws Exception {
                Letter letter = new Letter();
                BeanCopierUtil.copy(dto, letter, false);
                return letterBiz.send(letter);
            }
        });
    }

    /**
     * 更新阅读状态私信
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseDto<Void> updateReadStatus(final LetterUpdateReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) throws Exception {
                letterBiz.updateReadStatus(dto.getLetterId(), dto.getFlag());
                return null;
            }
        });
    }

    /**
     * 查询用户所有的私信
     * 
     * @param uid
     * @param readStatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseDto<List<LetterVo>> query(@RequestParam(value = "uid", required = true) final Long uid,
            @RequestParam(value = "readStatus", required = false) final Integer readStatus) {
        return handleGet(new BizCallback<Long, List<LetterVo>>() {
            @Override
            public List<LetterVo> doInTransaction(Long request) throws Exception {
                ReadStatusEnum readStatusEnum = null;
                if (readStatus != null) {
                    readStatusEnum = ReadStatusEnum.toEnum(readStatus);
                }
                return letterBiz.query(uid, readStatusEnum);
            }
        });
    }

    /**
     * 查询私信详细
     * 
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDetail", method = RequestMethod.GET)
    public ResponseDto<List<LetterVo>> queryByParentId(
            @RequestParam(value = "parentId", required = true) final Long parentId) {
        return handleGet(new BizCallback<Long, List<LetterVo>>() {
            @Override
            public List<LetterVo> doInTransaction(Long request) throws Exception {
                return letterBiz.queryDetail(parentId);
            }
        });
    }
}
