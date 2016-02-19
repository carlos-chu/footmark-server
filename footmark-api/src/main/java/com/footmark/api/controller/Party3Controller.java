package com.footmark.api.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.footmark.api.dto.OSSCallbackReqDto;
import com.footmark.core.biz.MultiMediaBiz;
import com.footmark.core.dao.impl.UserInfoDaoImpl;
import com.footmark.core.entity.MultiMedia;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.enums.MultiMediaTypeEnum;
import com.footmark.framework.controller.BaseController;
import com.footmark.framework.dto.RequestDto;
import com.footmark.framework.dto.ResponseDto;
import com.footmark.gateway.storage.AliyunTokenServer;

/**
 * @Description: 第三方相关的接口
 * @author carlos.chu
 * @version 1.0 2015年12月31日 上午10:31:57
 */
@Controller
@RequestMapping("/api/party3")
public class Party3Controller extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String outsideUrl = "footmark-img.oss-cn-beijing.aliyuncs.com/";
    private final static String bucket = "footmark-img";
    @Autowired
    private AliyunTokenServer aliyunTokenServer;
    @Autowired
    private UserInfoDaoImpl userInfoDao;
    @Autowired
    private MultiMediaBiz multiMediaBiz;

    /**
     * 阿里云OSS鉴权
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/aliyun/sign", method = RequestMethod.GET)
    public ResponseDto<Map<String, String>> createPushInfo() {
        return handleGet(new BizCallback<RequestDto, Map<String, String>>() {
            @Override
            public Map<String, String> doInTransaction(RequestDto request) throws Exception {
                return aliyunTokenServer.sign();
            }
        });
    }

    /**
     * 创建pushInfo
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/aliyun/callback", method = RequestMethod.POST)
    public void callback(@RequestBody final OSSCallbackReqDto requestParams, final HttpServletResponse response) {
        ResponseDto<Void> result = handlePost(requestParams, new BizCallback<OSSCallbackReqDto, Void>() {
            @Override
            public Void doInTransaction(OSSCallbackReqDto request) throws Exception {
                if (requestParams.getBelongType() == 1) { // headUrl
                    UserInfo user = new UserInfo();
                    user.setId(requestParams.getUserId());
                    user.setAvatar(outsideUrl + requestParams.getObject());
                    userInfoDao.update(user);
                } else if (requestParams.getBelongType() == 2) { // multiMedia
                    MultiMedia multiMedia = new MultiMedia();
                    multiMedia.setBucket(bucket);
                    multiMedia.setFileName(requestParams.getObject());
                    multiMedia.setOutsideUrl(outsideUrl + requestParams.getObject());
                    multiMedia.setType(MultiMediaTypeEnum.PICTURE);
                    multiMedia.setExtName("jpg");
                    multiMediaBiz.create(requestParams.getSourceId(), multiMedia);
                }
                return null;
            }
        });
        String resultJson = "{\"Status\":\"500\"}";
        if (result.isRet()) {
            resultJson = "{\"Status\":\"200\"}";
        }
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader("Content-Length", String.valueOf(resultJson.length()));
            response.addHeader("Content-Type", "application/json");
            response.getWriter().println(resultJson);
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("aliyun callback error cause by", e);
        }
    }
}
