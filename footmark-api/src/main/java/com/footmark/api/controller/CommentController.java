package com.footmark.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.CommentReqDto;
import com.footmark.api.dto.CommentUpdateReqDto;
import com.footmark.common.enums.StatusEnum;
import com.footmark.core.biz.CommentBiz;
import com.footmark.core.entity.Comment;
import com.footmark.core.enums.CommentSourceTypeEnum;
import com.footmark.core.vo.CommentVo;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.framework.util.BeanCopierUtil;

/**
 * @Description: 评论api
 * @author carlos.chu
 * @date 2015年8月28日
 */
@Controller
@RequestMapping("/api/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentBiz commentBiz;

    /**
     * 创建评论
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDto<CommentVo> create(final CommentReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, CommentVo>() {
            @Override
            public CommentVo doInTransaction(RequestDto request) throws Exception {
                Comment comment = new Comment();
                BeanCopierUtil.copy(dto, comment, true);
                return commentBiz.create(comment);
            }
        });
    }

    /**
     * 更新评论状态
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseDto<Void> updateReadStatus(final CommentUpdateReqDto dto) {
        return handlePost(dto, new BizCallback<RequestDto, Void>() {
            @Override
            public Void doInTransaction(RequestDto request) throws Exception {
                commentBiz.update(dto.getCommentId(), StatusEnum.toEnum(dto.getStatus()));
                return null;
            }
        });
    }

    /**
     * 查询某一主题的评论
     * 
     * @param uid
     * @param readStatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseDto<List<CommentVo>> query(@RequestParam(value = "sourceId", required = true) final Long sourceId,
            @RequestParam(value = "sourceType", required = true) final Integer sourceType) {
        return handleGet(new BizCallback<Long, List<CommentVo>>() {
            @Override
            public List<CommentVo> doInTransaction(Long request) throws Exception {
                CommentSourceTypeEnum sourceTypeEnum = null;
                if (sourceType != null) {
                    sourceTypeEnum = CommentSourceTypeEnum.toEnum(sourceType);
                }
                return commentBiz.query(sourceId, sourceTypeEnum);
            }
        });
    }

}
