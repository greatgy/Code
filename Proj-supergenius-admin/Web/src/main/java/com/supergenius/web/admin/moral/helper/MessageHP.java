package com.supergenius.web.admin.moral.helper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.moral.helper.BaseMessageHP;
import com.supergenius.server.user.helper.BaseUserConfigHP;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.entity.Message;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.common.service.MessageSO;
import com.supergenius.xo.common.serviceimpl.MessageSOImpl;
import com.supergenius.xo.moral.constants.MapperDict;
import com.supergenius.xo.moral.entity.Userdoc;
import com.supergenius.xo.user.entity.User;

/**
 * 系统消息管理hp
 * @author YuYingJie
 */
public class MessageHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(MessageSOImpl.class);
	
	private static MessageSO so;
	
	private static MessageSO getSO() {
		if (so == null) {
			so = (MessageSO) spring.getBean(MessageSO.class);
		}
		return so;
	}
	
	/**
	 * 组织查询语句
	 * @param model
	 * @return
	 * @author YuYingJie
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		map.put(MapperDict.type, EMsg.sys);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
	/**
	 * 发送系统消息
	 * @param message
	 * @param who
	 * @author YuYingJie
	 */
	public static boolean sendSysMsg(Message message, EUser who) {
		Admin admin = AdminHP.getAdmin();
		Map<String, String> map = new HashMap<>();
		map.put(MapperDict.utype, who.name());
		List<Integer> oids = BaseMessageHP.getUserOidsByType(who);
		return sendMessage(oids, ViewKeyDict.systemmsgcount, admin.getUid(), admin.getOid(), admin.getName(), null, 0, null, SysConf.MoralSysMsgImg, message.getTitle(), message.getContent(), null, EMsgState.init, EMsg.sys, ESite.moral, getMsgNumber(), map);
		
	}
	
	

	/**
	 * 发送通知消息
	 * @param userdoc
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	public static boolean sendNoticeMsg(Userdoc userdoc, EMsg type) {
		return sendNoticeMsg(BaseUserHP.get(userdoc.getUseruid()), userdoc.getName(), null, type);
	}
	
	/**
	 * 发送通知消息6,7,8,9,10
	 * @param userdoc
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	public static boolean sendNoticeMsg(User user, String title, String url, EMsg type) {
		if (user == null) {
			return false;
		}
		Admin admin = AdminHP.getAdmin();
		String content = getContent(title, url, type);
		return sendMessage(ViewKeyDict.noticemsgcount, admin.getUid(), admin.getOid(), admin.getName(), user.getUid(), user.getOid(), user.getShowname(), SysConf.MoralSysMsgImg, null, content, null, EMsgState.init, type, ESite.moral, null, null);
	}
	
	/**
	 * 发送通知消息 5,12,13,14
	 * @param useruid
	 * @param url
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	public static boolean sendNoticeMsg(User user, String url, EMsg type) {
		if (user == null) {
			return false;
		}
		Admin admin = AdminHP.getAdmin();
		String content = getContent(url, type);
		return sendMessage(ViewKeyDict.noticemsgcount, admin.getUid(), admin.getOid(), admin.getName(), user.getUid(), user.getOid(), user.getShowname(), SysConf.MoralSysMsgImg, null, content, null, EMsgState.init, type, ESite.moral, null, null);
	}

	/**
	 * 发送消息
	 * @return
	 * @author YuYingJie
	 */
	private static boolean sendMessage(String msgtype, String fromuseruid, int fromuseroid, String fromusername, String touseruid, int touseroid, String tousername, String useravatar, String title, String content, String href, EMsgState state, EMsg type, ESite site, String sn, Map<String, String> data) {
		boolean bool = BaseMessageHP.sendMessage(fromuseruid, fromuseroid, fromusername, touseruid, touseroid, tousername, useravatar, title, content, href, state, type, site, sn, data);
		if (bool) {
			String filePath = SysConf.SerialBasePath + SysConf.UserIgnoreMsgFilePath;
			BaseUserConfigHP.updateUserIgnoreMsgFile(touseroid, filePath, msgtype, EChannel.moral.name());
			return true;
		} else {
			log.info("Failed to send message");
			return false;
		}
	}
	
	/**
	 * 发送消息
	 * @return
	 * @author YuYingJie
	 */
	private static boolean sendMessage(List<Integer> oids, String msgtype, String fromuseruid, int fromuseroid, String fromusername, String touseruid, int touseroid, String tousername, String useravatar, String title, String content, String href, EMsgState state, EMsg type, ESite site, String sn, Map<String, String> data) {
		boolean bool = BaseMessageHP.sendMessage(fromuseruid, fromuseroid, fromusername, touseruid, touseroid, tousername, useravatar, title, content, href, state, type, site, sn, data);
		if (bool) {
			String filePath = SysConf.SerialBasePath + SysConf.UserIgnoreMsgFilePath;
			BaseUserConfigHP.updateUserIgnoreMsgFile(oids, filePath, msgtype, EChannel.moral.name());
			return true;
		} else {
			log.info("Failed to send message");
			return false;
		}
	}
	
	/**
	 * 获得编号
	 * @return
	 * @author YuYingJie
	 */
	private static String getMsgNumber() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(DateUtil.getToday());
		String today = DateUtil.getNow();
		today = today.split(" ")[0];
		today = today + MapperDict.starttimeformat;
		Map<String, Object> map1 = getParamMap();
		map1.put(MapperDict.type, EMsg.sys);
		map1.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, DateUtil.parse(today));
		DecimalFormat nf = new DecimalFormat("00");
		List<Message> list = getSO().getList(map1);
		int sn = 0;
		String str;
		if (list.size() == 0) {
			str = buffer.append(nf.format(sn + 1)).toString();
		} else {
			sn = Integer.parseInt(list.get(0).getSn()) + 1;
			str = String.valueOf(sn);
		}
		return str;
	}
	
	/**
	 * 返回使用某个文件的模板的转换结果
	 * @param url
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	private static String getContent(String url, EMsg type) {
		Map<String, Object> map = new HashMap<>();
		if (url != null) {
			map.put(ViewKeyDict.url, url);
		}
		return getContent(map, type);
	}
	
	/**
	 * 返回使用某个文件的模板的转换结果
	 * @param title
	 * @param url
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	private static String getContent(String title, String url, EMsg type) {
		Map<String, Object> map = new HashMap<>();
		if (type.equals(EMsg.placetop)) {
			map.put(ViewKeyDict.url, url);
		}
		if (title != null) {
			map.put(ViewKeyDict.title, title);
		}
		return getContent(map, type);
	}
	
	/**
	 * 返回使用某个文件的模板的转换结果
	 * 
	 * @param map
	 * @param type
	 * @return
	 * @author YuYingJie
	 */
	private static String getContent(Map<String, Object> map, EMsg type) {
		if (type == null) {
			return null;
		}
		if (map.containsKey(MapperDict.type)) {
			map.remove(MapperDict.type);
			map.put(MapperDict.type, type);
		} else {
			map.put(MapperDict.type, type);
		}
		try {
			return FreemarkerUtil.process(SysConf.MoralMsgTemplPath, SysConf.MoralMsgTempName, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
