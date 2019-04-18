package com.supergenius.web.front.user.helper;

import java.util.Random;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.EmailUtil;
import com.genius.core.base.utils.SendSmsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.utils.ValidUtil;
import com.supergenius.xo.user.rule.SmsCodeRule;

/**
 * 短信验证相关HP （6位随机数字）
 * 
 * @author YangGuang
 * @date 2018年3月14日16:22:22
 */
public class SmsHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(SmsHP.class);
	private static final String smsCode = "_smsCode";

	/**
	 * 生成短信验证码
	 * 
	 * @param phoneNumber
	 * @return
	 * @author YangGuang
	 */
	public static String createSmsCode(String phoneNumber) {
		Rule rule = new SmsCodeRule(phoneNumber + smsCode);
		String codeStr = null;
		boolean flag = true;
		try {
			codeStr = (String) MemcacheUtil.get(rule);
		} catch (Exception e) {
			logException(log, e);
		}
		if (StrUtil.isNotEmpty(codeStr) && StrUtil.isNotEmpty(codeStr.substring(7)) && StrUtil.isNotEmpty(codeStr.substring(0, 6))) {
			if ((new DateTime().getMillis() - DateUtil.parse(codeStr.substring(7)).getMillis()) / 60000 < SysConf.smsAliveTime) {
				flag = false;
			}
		} 
		if (flag) {
			MemcacheUtil.remove(rule);
			codeStr = getSix() + "_" + DateUtil.getNow();
			MemcacheUtil.set(rule, codeStr);
		}
		return codeStr.substring(0, 6);
	}

	/**
	 * 验证短信验证码
	 * 
	 * @param phoneNumber
	 * @param code
	 * @return
	 * @author YangGuang
	 */
	public static boolean validateSmsCode(String phoneNumber, String code) {
		Rule rule = new SmsCodeRule(phoneNumber + smsCode);
		String codeStr = null;
		try {
			codeStr = (String) MemcacheUtil.get(rule); 
		} catch (Exception e) {
			logException(log, e);
		}
		if (StrUtil.isNotEmpty(codeStr) && StrUtil.isNotEmpty(codeStr.substring(7)) && StrUtil.isNotEmpty(codeStr.substring(0, 6))) {
			if ((new DateTime().getMillis() - DateUtil.parse(codeStr.substring(7)).getMillis()) / 60000 < SysConf.smsAliveTime) {
				codeStr = codeStr.substring(0, 6);
				if (code.equals(codeStr)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 清除短信验证码缓存
	 * 
	 * @param phoneNumber
	 * @return
	 * @author YangGuang
	 */
	public static void removeSmsCode(String phoneNumber) {
		Rule rule = new SmsCodeRule(phoneNumber + smsCode);
		MemcacheUtil.remove(rule);
	}

	/**
	 * 产生随机的六位数
	 * 
	 * @return
	 */
	public static String getSix() {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != 6) {
			return getSix();
		}
		return result;
	}
	
	/**
	 * 发送短信验证码
	 */
	public static boolean sendSms(String token) {
		if (ValidUtil.isEmail(token)) {
			String createSmsCode = createSmsCode(token);
			EmailUtil.send(token, "邮箱验证码", "【超天才】：" + createSmsCode+"(动态验证码，有效期5分钟)");
			return true;
		}
		if (ValidUtil.isMobile(token)) {
			String smsCode = createSmsCode(token);
			String code = SendSmsUtil.sendSms(token, SysConf.aliyunReTempId, smsCode);
			log.info("发送短信返回码为--------------------------------：" + code);
			return true;
		}
		return false;
	}
}
