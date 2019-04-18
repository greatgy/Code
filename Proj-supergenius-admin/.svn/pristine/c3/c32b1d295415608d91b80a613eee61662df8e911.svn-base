package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseMessageHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.entity.Message;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EFromUserType;
import com.supergenius.xo.tpi.enums.EInboxState;
import com.supergenius.xo.tpi.enums.EMsg;
import com.supergenius.xo.tpi.enums.EMsgState;
import com.supergenius.xo.tpi.service.MessageSO;
import com.supergenius.xo.tpi.service.TpiuserSO;

/**
 * 消息HP
 * @author liushaomin
 */
public class MessageHP extends BaseMessageHP {
	
	private static Logger log = LoggerFactory.getLogger(MessageHP.class);
	
	private static MessageSO so;
	
	private static TpiuserSO tpiuserSO;

	private static MessageSO getSO() {
		if (so == null) {
			so = (MessageSO) spring.getBean(MessageSO.class);
		}
		return so;
	}
	
	private static TpiuserSO getTpiUserSO() {
		if (tpiuserSO == null) {
			tpiuserSO = (TpiuserSO) spring.getBean(TpiuserSO.class);
		}
		return tpiuserSO;
	}
	
	/**
	 * 获取json数据
	 * @param model
	 * @return
	 * @author LiuXiaoke
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.content))) {
			map.put(MapperDict.content, model.get(ViewKeyDict.content));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.fromusername))) {
			map.put(MapperDict.fromusername, model.get(ViewKeyDict.fromusername));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.tousername))) {
			map.put(MapperDict.tousername, model.get(ViewKeyDict.tousername));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.state))) {
			map.put(MapperDict.state, model.get(ViewKeyDict.state));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtime))) {
			map.put(MapperDict.createtime, model.get(ViewKeyDict.createtime));
		}
		map.put(MapperDict.type, EMsg.sysmsg);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
	/**
	 * 发送系统通知
	 * @param ids
	 * @param msg
	 * @author liushaomin
	 */
	public static void SendSysMsg(String[] ids, String msg) {
		for (String id : ids) {
			SendSysMsg(id, msg);
		}
	}
	
	/**
	 * 发送系统通知
	 * @param id
	 * @param mgs
	 * @author liushaomin
	 */
	public static void SendSysMsg(String id, String msg) {
		TpiUser tpiUser = getTpiUserSO().get(id);
		log.info("begin to SendSysMsg, send username" + tpiUser.getUsername());
		if (tpiUser != null) {
			Message message = new Message();
			message.setContent(msg);
			message.setTitle("系统通知");
			message.setFromusername("超天才并购平台");
			message.setTouseruid(tpiUser.getUid());
			message.setTousername(tpiUser.getUsername());
			message.setFromuseruid(SysConf.DefaultSupenGeniusUid);
			message.setFromuseravatar(SysConf.TpiSysMsgImg);
			message.setInstate(EInboxState.normal);
			message.setUsertype(EFromUserType.merger);
			message.setState(EMsgState.init);
			message.setType(EMsg.sysmsg);
			if (getSO().add(message)) {
				log.info("SendSysMsg success");
			}else {
				log.info("SendSysMsg failed");
			}
		}
	}
}
