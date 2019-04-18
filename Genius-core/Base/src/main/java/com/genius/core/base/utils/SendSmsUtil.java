package com.genius.core.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.genius.core.base.conf.BaseSysConf;

/**
 * 发送短信
 * 
 * @author YangGuang
 */
public class SendSmsUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(SendSmsUtil.class);
	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	/**
	 * 发送短信验证码
	 * 
	 * @param phoneNumber
	 * @param templateCode 模板id
	 * @param checkCode 验证码
	 * @return code 状态返回码 OK为发送成功
	 * @author: YangGuang 2018年3月1日09:54:42
	 */
	public static String sendVerificationCode(String phoneNumber, String templateCode, String checkCode) {
		log.info(String.format("begin to invoke recharge (phoneNumber:%s, templateCode:%s, checkCode:%s)", phoneNumber, templateCode, checkCode));
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", BaseSysConf.aliyunAccessKeyId, BaseSysConf.aliyunAccessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(phoneNumber);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(BaseSysConf.aliyunSignName);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam("{\"code\":\"" + checkCode + "\"}");

			// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");

			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			if (StrUtil.isNotEmpty(null)) {
				request.setOutId("");
			}
			// hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			log.info("send sms message is " + sendSmsResponse.getMessage());
			log.info("send sms code is " + sendSmsResponse.getCode());

			return sendSmsResponse.getCode();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 批量发送短信
	 * 
	 * @param phoneNumber  [“15000000000”,”15000000001”]
	 * @param templateCode 模板id
	 * @param 短信内容
	 * @return code 状态返回码 OK为发送成功
	 * @author: YangGuang 2018年7月26日16:59:35
	 */
	public static String SendBatchSms(String phoneNumberJson, String templateCode, String content) {
		log.info(String.format("begin to invoke recharge (phoneNumber:%s, templateCode:%s, checkCode:%s)", phoneNumberJson, templateCode, content));
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", BaseSysConf.aliyunAccessKeyId, BaseSysConf.aliyunAccessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendBatchSmsRequest request = new SendBatchSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumberJson(phoneNumberJson);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignNameJson(BaseSysConf.aliyunSignName);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParamJson("{\"content\":\"" + content + "\"}");

			// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");

			// hint 此处可能会抛出异常，注意catch
			SendBatchSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			log.info("send sms message is " + sendSmsResponse.getMessage());
			log.info("send sms code is " + sendSmsResponse.getCode());

			return sendSmsResponse.getCode();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
