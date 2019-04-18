package com.supergenius.conf.manager.message;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 消息配置 
 * @author chenminchang
 * @createtime 2016-8-1下午12:30:56
 */
@Component("managerMessageConf")
public class MessageConf {

	public static String ManagerMsgTmplPath;
	public static String MsgContentTmpl;
	public static String MsgTitleTmpl;

	@Value("#{sys}")
	public void setSysConf(Properties sys) {
		MessageConf.ManagerMsgTmplPath = sys.getProperty("ManagerMsgTmplPath");
		MessageConf.MsgContentTmpl = sys.getProperty("MsgContentTmpl");
		MessageConf.MsgTitleTmpl = sys.getProperty("MsgTitleTmpl");
	}

}
