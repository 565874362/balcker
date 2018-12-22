package com.baihua.baihuamedical.modules.basic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title SmsService.java
 * @Package com.baihua.baihuamedical.modules.basic.service.impl
 * @date 2018年12月22日 13:30:43
 */
@Service
@ConditionalOnBean(AliSmsServiceImpl.AliSmsProperties.class)
@Slf4j
public class AliSmsServiceImpl extends AbstractSmsService {

	//短信服务
	static final String MSG_PRODUCT = "Dysmsapi";
	static final String MSG_DOMAIN = "dysmsapi.aliyuncs.com";

	@Autowired
	private AliSmsProperties aliSmsProperties;

	@Override
	public boolean sendSms(String phone,String code) throws ClientException {
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsProperties.getKeyId(), aliSmsProperties.getKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", MSG_PRODUCT, MSG_DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号
		request.setPhoneNumbers(phone);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(aliSmsProperties.getMsgSignName());
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(aliSmsProperties.getMsgCaptchaTelCode());
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\'name\':\'Tom\', \'code\':\'"+code+"\'}");
		request.setTemplateParam("{\'code\':\'"+getCaptcha()+"\'}");
		//选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		//request.setSmsUpExtendCode("90997");

		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("");
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode().equalsIgnoreCase("OK")){
			return true;
		}
		log.error("短信发送失败>>>>>>>>>>>>>>>>>>>> {}",sendSmsResponse.getMessage());
		return false;
	}

	public QuerySendDetailsResponse querySendDetails(String phone) throws ClientException, com.aliyuncs.exceptions.ClientException {

		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsProperties.getKeyId(), aliSmsProperties.getKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", MSG_PRODUCT,MSG_DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		//组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		//必填-号码
		request.setPhoneNumber(phone);
		//必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(new Date()));
		//必填-页大小
		request.setPageSize(10L);
		//必填-当前页码从1开始计数
		request.setCurrentPage(1L);

		//hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

		return querySendDetailsResponse;
	}

	@Data
	public static class AliSmsProperties {
		private String keyId;
		private String keySecret;
		private String msgSignName ;
		private String msgCaptchaTelCode ;
	}
}

