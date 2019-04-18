package com.supergenius.third.boc.util;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.supergenius.global.conf.SysConf;

/**
 *  
 * @author liushaomin
 */
public class BocOrderBuilder {
	
//	商户订单号|订单时间|订单币种|订单金额|商户号
//	1|20131007000601|001|1|104110059475555|
//	orderNo|orderTime|curCode|orderAmount|merchantNo
//	1|1970-01-01 08:00:00.0|001|1.00|1
	private static  PKCS7Tool tool=null;
	
	//private static final String JKS_PATH;
	//private static final String CMB_KEY;
	private static final String PFX_PATH;
	private static final String PASSWD;
	private static final String CER_PATH;
	
	static {
		String basePath = SysConf.BankBocBasePath;
		PFX_PATH = basePath + SysConf.BankBocPfxFilePath;
		PASSWD = SysConf.BankBocPasswd;
		CER_PATH = basePath + SysConf.BankBocCerFilePath;
		try {
			tool = PKCS7Tool.getSigner(PFX_PATH, PASSWD, PASSWD);
			PKCS7Tool.getVerifier(CER_PATH);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String orderNo;
	private String orderTime;
	private String curCode="001";
	private String orderAmout="1";
	private String merchantNo="104110059475555"; 
	
	private StringBuilder orderSb=new StringBuilder();
	private String split="|";
	
	public BocOrderBuilder() {
		super();
		
	}
	
	
	/**
	 * 
	 * @param orderNo
	 * @param orderTime
	 * @param orderAmout
	 */
	public BocOrderBuilder(String orderNo, String orderTime, String orderAmout) {
		super();
		this.orderNo = orderNo;
		this.orderTime = orderTime;
		this.orderAmout = orderAmout;
	}
	
	public String getOrderBuilderStr(){
		orderSb.append(orderNo).append(split).append(orderTime).
		append(split).append(curCode).append(split).append(orderAmout).append(split)
		.append(merchantNo);
		return orderSb.toString();
	}
	
	public String getSignatureData() throws Exception{
		return tool.sign(this.getOrderBuilderStr().getBytes());
	}
	
	public boolean verifyData(String signature,String data,String dn) throws Exception{
		try{
		 tool.verify(signature, data.getBytes(), dn);
		 return true;
		}catch(Exception e){
			e.printStackTrace();
			//todo log and 业务逻辑
			return false;
		}
	}
	
	public static String signOrderStrForQuery(String queryStr){
		try {
			return tool.sign(queryStr.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getCurCode() {
		return curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	public String getOrderAmout() {
		return orderAmout;
	}
	public void setOrderAmout(String orderAmout) {
		this.orderAmout = orderAmout;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}
