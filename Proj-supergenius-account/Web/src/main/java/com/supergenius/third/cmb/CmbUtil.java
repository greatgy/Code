/**
 * 招行
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
 * Create at: 2013-11-25 下午3:53:02
 * ============================================================================
 */
package com.supergenius.third.cmb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmb.netpayment.Security;

import com.genius.core.base.utils.BaseUtil;
import com.supergenius.global.conf.SysConf;

/**
 * @author architect.bian
 *
 */
public class CmbUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(CmbUtil.class);
	
	public static boolean checkFromBank(String result) {
        byte[] baSig = result.getBytes();
		return getPaySecurity().checkInfoFromBank(baSig);
	}
	
	private static Security getPaySecurity() {
		try{
			return new cmb.netpayment.Security(SysConf.BankCmbPulicKeyFile);
		} catch(Exception e){
			e.printStackTrace();
			logException(log, e);
		}
		return null;
	}
	
	/**
	 * 结账
	 * @param strBillNo 定单号
	 * @param strRefNo 定单流水号
	 * @return
	 */
	public static int SettleOrder(String strBillNo, String strRefNo){
		
		return 0;
	}
}
