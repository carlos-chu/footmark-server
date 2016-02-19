package com.footmark.gateway.push;

import java.util.Properties;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.footmark.common.util.SystemUtil;
import com.footmark.gateway.push.entity.PushMessage;

/**
 * @Description: 使用百度推送服务推送给安卓客户端
 * @author carlos.chu
 * @version 1.0 2015年12月30日 下午3:17:26
 */
@Component("androidPushMsg")
public class AndroidPushMsg {

    private static final Logger logger = LoggerFactory.getLogger(AndroidPushMsg.class);

    private static String apiKey;
    private static String secretKey;
    static {
        Properties properties = SystemUtil.loadProperties("/conf/gateway-config.properties");
        apiKey = properties.getProperty("baidu_apiKey");
        secretKey = properties.getProperty("baidu_secretKey");
    }

    public enum MessageType {
        PASS_MESSAGE(0, "透传消息"), NOTIFICATION(1, "通知台消息");
        public int code;

        MessageType(int code, String name) {
            this.code = code;
        }
    }

    public enum DeviceType {
        ANDROID(3, "安卓"), IOS(4, "苹果");
        private int code;

        DeviceType(int code, String name) {
            this.code = code;
        }

    }

    private BaiduPushClient initBaiduClient() {
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                logger.info(event.getMessage());
            }
        });
        return pushClient;
    }

    /**
     * 推送给所有设备
     * 
     * @param message
     * @throws PushClientException
     * @throws PushServerException
     */
    public void pushToAll(PushMessage message) throws PushClientException, PushServerException {
        BaiduPushClient pushClient = initBaiduClient();
        try {
            JSONObject json = JSONObject.fromObject(message);
            PushMsgToAllRequest request = new PushMsgToAllRequest().addMessageType(MessageType.NOTIFICATION.code)
                    .addMessage(json.toString()).addDeviceType(DeviceType.ANDROID.code);
            pushClient.pushMsgToAll(request);
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
                        e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

    /**
     * 推送给单个设备
     * 
     * @param msgType
     * @param channelId
     * @param message
     * @throws PushServerException
     * @throws PushClientException
     */
    public void pushToSingleDevice(int msgType, String channelId, PushMessage message) throws Exception {
        BaiduPushClient pushClient = initBaiduClient();
        String messageStr = null;
        if (msgType == MessageType.PASS_MESSAGE.code) {
            messageStr = message.getMessage();
        } else {
            messageStr = JSONObject.fromObject(message).toString();
        }
        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
                    .addMessageType(msgType).addMessage(messageStr).addDeviceType(3);
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
        } catch (PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
                        e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }
}
