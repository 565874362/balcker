package com.baihua.baihuamedical.modules.service.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.alibaba.fastjson.JSON;
import com.baihua.baihuamedical.common.exception.BaihuaException;
import com.baihua.baihuamedical.common.utils.CodeUtil;
import com.baihua.baihuamedical.modules.basic.service.ChatSupportService;

import lombok.Data;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title RongRunChatServiceImpl.java
 * @Package com.baihua.baihuamedical.modules.service.service.impl
 * @date 2018年12月22日 09:49:43
 */
@Service("rongRunChatServiceImpl")
@ConditionalOnBean(RongRunChatServiceImpl.RongYunProperties.class)
public class RongRunChatServiceImpl implements ChatSupportService {

	private static final String REMOTE_API_URL = "http://api.cn.ronghub.com/user/getToken.json";

	private static final String APPKEY = "RC-App-Key";
	private static final String NONCE = "RC-Nonce";
	private static final String TIMESTAMP = "RC-Timestamp";
	private static final String SIGNATURE = "RC-Signature";

	@Autowired
	private RestOperations restOperations;

	@Autowired
	private RongYunProperties rongYunProperties;

	@Override
	public String getToken(Long accountId) {
		HttpHeaders headers = new HttpHeaders();

		String nonce = String.valueOf(Math.random() * 1000000);
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		StringBuilder toSign = new StringBuilder(rongYunProperties.getAppSecret()).append(nonce).append(timestamp);
		String sign = CodeUtil.hexSHA1(toSign.toString());

		headers.add(APPKEY, rongYunProperties.getAppKey());
		headers.add(NONCE, nonce);
		headers.add(TIMESTAMP, timestamp);
		headers.add(SIGNATURE, sign);
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		String requestParam = "portraitUri=&name=&userId=" + accountId;
		HttpEntity<String> requestBody = new HttpEntity<>(requestParam, headers);
		ResponseEntity<String> response = null;
		try {
			response = restOperations.postForEntity(new URI(REMOTE_API_URL), requestBody, String.class);
		} catch (URISyntaxException e) {
		}
		if (response != null) {
			if (response.getStatusCode() == HttpStatus.OK) {
				return JSON.parseObject(response.getBody(),RongRunResponse.class).getToken();
			}
		}
		throw new BaihuaException("聊天服务器异常");
	}


	@Data
	private static class RongRunResponse {
		private int code;
		private String userId;
		private String token;
	}

	@Data
	public static class RongYunProperties {
		private String appKey;
		private String appSecret;
	}

}

