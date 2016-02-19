package com.footmark.gateway.storage;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.footmark.common.util.SystemUtil;

/**
 * @Description: 阿里云token鉴权
 * @author carlos.chu
 * @version 1.0 2016年1月28日 下午1:45:13
 */
@Service("aliyunTokenServer")
public class AliyunTokenServer {

    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    public static final String STS_API_VERSION = "2015-04-01";

    private static String accessKeyId;
    private static String accessKeySecret;
    private static String roleArn;
    private static String tokenExpireTime;
    private static String policyFile = "/policy/all_policy.txt";

    static {
        Properties properties = SystemUtil.loadProperties("/conf/gateway-config.properties");
        accessKeyId = properties.getProperty("AccessKeyID");
        accessKeySecret = properties.getProperty("AccessKeySecret");
        roleArn = properties.getProperty("RoleArn");
        tokenExpireTime = properties.getProperty("TokenExpireTime");
    }

    public Map<String, String> sign() {
        long durationSeconds = Long.valueOf(tokenExpireTime);
        String policy = readJson(policyFile);
        String roleSessionName = "footmark-001";
        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;
        Map<String, String> respMap = null;
        try {
            final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
                    policy, protocolType, durationSeconds);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("status", "200");
            respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            respMap.put("Expiration", stsResponse.getCredentials().getExpiration());
        } catch (ClientException e) {
            respMap = new LinkedHashMap<String, String>();
            respMap.put("status", e.getErrCode());
            respMap.put("AccessKeyId", "");
            respMap.put("AccessKeySecret", "");
            respMap.put("SecurityToken", "");
            respMap.put("Expiration", "");
        }
        return respMap;
    }

    protected AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
            String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds)
            throws ClientException {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);
            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    private String readJson(String path) {
        // 从给定位置获取文件
        InputStream inputStream = AliyunTokenServer.class.getClassLoader().getResourceAsStream(path);
        String asString = SystemUtil.readFileAsString(inputStream);
        return asString;
    }
}
