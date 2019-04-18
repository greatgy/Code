/**
 * 工行支付参数签名类，重写了icbcB2C.pay.IcbcB2CPayImpl
 * ============================================================================
 * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 GrandGeniusGroup All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: Architect.bian
 * ----------------------------------------------------------------------------
 * Create at: 2013-10-10 下午2:54:57
 * ============================================================================
 */
package com.supergenius.third.icbcB2C.pay;

import icbcB2C.common.IcbcB2CUtil;
import icbcB2C.model.B2cConfig;
import icbcB2C.model.FormData;
import icbcB2C.model.TranData;

import java.io.FileInputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.infosec.icbc.ReturnValue;

/**
 * @author architect.bian
 * 
 */
public class IcbcB2CPayImpl extends icbcB2C.pay.IcbcB2CPayImpl {
	
	private static String charset = "GBK";

	private static Logger logger = LoggerFactory.getLogger(IcbcB2CPayImpl.class.getName());

	public String packTranData(B2cConfig b2cConfig, TranData tranData) {
		if (StringUtils.isNotEmpty(tranData.getMerURL())) {
			b2cConfig.setMerURL(tranData.getMerURL());// 修改b2cConfig.merURL参数为tranData.merURL，tranData.merURL为在BankPX设置的值
		}
		return super.packTranData(b2cConfig, tranData);
	}

	public FormData createFormData(String xmlPath, TranData tranData) {
		FormData formdata = new FormData();
		try {
			B2cConfig b2cConfig = IcbcB2CUtil.loadXML(xmlPath);
			if (b2cConfig != null) {
				String tranDataXmlStr = packTranData(b2cConfig, tranData);
				System.out.println("tranData明文串：" + tranDataXmlStr);
				logger.info("tranData明文串：" + tranDataXmlStr);
				if (tranDataXmlStr == null) {
					return null;
				}

				String pwd = b2cConfig.getPassword();
				String userCrtPath = b2cConfig.getUserCrtPath();
				String userKeyPath = b2cConfig.getUserKeyPath();
				byte[] byteTranData = tranDataXmlStr.getBytes(charset);

				byte[] Base64TranData = ReturnValue.base64enc(byteTranData);
				String StrBase64TranData = new String(Base64TranData, charset).toString();
				System.out.println("tranData明文串的base64编码：" + StrBase64TranData);
				logger.info("tranData明文串的base64编码：" + StrBase64TranData);

				char[] keyPass = pwd.toCharArray();

				FileInputStream in1 = new FileInputStream(userCrtPath);
				byte[] bcert = new byte[in1.available()];
				in1.read(bcert);
				in1.close();

				FileInputStream in2 = new FileInputStream(userKeyPath);
				byte[] bkey = new byte[in2.available()];
				in2.read(bkey);
				in2.close();

				byte[] sign = ReturnValue.sign(byteTranData, byteTranData.length, bkey, keyPass);
				if (sign == null) {
					System.out.println("错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
					logger.error("错误：签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
					return null;
				}

				System.out.println("签名成功。");

				byte[] EncSign = ReturnValue.base64enc(sign);
				String SignMsgBase64 = new String(EncSign, charset).toString();
				System.out.println("签名信息BASE64编码：" + SignMsgBase64);
				logger.info("签名信息BASE64编码：" + SignMsgBase64);

				byte[] EncCert = ReturnValue.base64enc(bcert);
				String CertBase64 = new String(EncCert, charset).toString();
				System.out.println("证书公钥BASE64编码：" + CertBase64);
				logger.info("证书公钥BASE64编码：" + CertBase64);

				formdata.setInterfaceName("ICBC_PERBANK_B2C");

				formdata.setInterfaceVersion("1.0.0.11");

				formdata.setTranData(StrBase64TranData);

				formdata.setMerSignMsg(SignMsgBase64);

				formdata.setMerCert(CertBase64);
			} else {
				System.out.println("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
				logger.error("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		return formdata;
	}
}
