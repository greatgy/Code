/**
 * 
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
 * Create at: 2013-12-20 下午2:14:20
 * ============================================================================
 */
package com.supergenius.third.icbcB2C.pay;

import icbcB2C.common.IcbcB2CUtil;
import icbcB2C.model.B2cConfig;
import icbcB2C.model.NotifyData;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.infosec.icbc.ReturnValue;

/**
 * @author architect.bian
 * 
 */
public class IcbcB2CNotifyImpl extends icbcB2C.notify.IcbcB2CNotifyImpl {

	private static String charset = "GBK";

	private static Logger logger = LoggerFactory.getLogger(IcbcB2CNotifyImpl.class.getName());

	public NotifyData createNotifyData(String notifyData, String signMsg, String xmlPath) {
		NotifyData nd = null;
		try {
			B2cConfig C = IcbcB2CUtil.loadXML(xmlPath);
			if (C != null) {
				String publicCrtPath = C.getPublicCrtPath();
				logger.info("银行证书存放路径：" + publicCrtPath);
				FileInputStream in1 = new FileInputStream(publicCrtPath);
				byte[] bcert = new byte[in1.available()];
				in1.read(bcert);
				in1.close();

				byte[] byteSM = signMsg.getBytes(charset);
				byte[] DecSignMsg = ReturnValue.base64dec(byteSM);
				if (DecSignMsg == null) {
					System.out.println("错误：签名信息BASE64解码失败。");
					logger.error("错误：签名信息BASE64解码失败。");
					return null;
				}
				byte[] byteNd = notifyData.getBytes();
				byte[] DesNd = ReturnValue.base64dec(byteNd);
				if (DesNd == null) {
					System.out.println("错误：通知信息BASE64解码失败。");
					logger.error("错误：通知信息BASE64解码失败。");
					return null;
				}

				logger.info("银行对通知结果的签名数据：" + signMsg);
				int result = ReturnValue.verifySign(DesNd, DesNd.length, bcert, DecSignMsg);

				if (result == 0) {
					String notifyDataStr = new String(DesNd, charset).toString();
					logger.info("通知结果数据：" + notifyDataStr);
					nd = xmlElements(notifyDataStr);
					logger.info("验签成功！");
				} else {
					logger.error("验签失败！");
					return null;
				}

			} else {
				System.out.println("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
				logger.error("错误：配置文件有误，请检查配置文件存放路径和配置文件格式是否正确。");
				return null;
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (SignatureException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return null;
		}
		return nd;
	}
}
