package com.supergenius.xo.manager.serviceimpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.conf.manager.message.MessageConf;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.dao.MessageDao;
import com.supergenius.xo.manager.entity.Message;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.manager.service.MessageSO;
import com.supergenius.xo.user.dao.UserDao;
import com.supergenius.xo.user.entity.User;

/**
 * 消息Impl
 * @author XieMing
 * @date 2016-7-18 下午2:31:52
 */
@Service("managerMessageSOImpl")
public class MessageSOImpl extends BaseSOImpl<Message> implements MessageSO {

	private static Logger log = LoggerFactory.getLogger(MessageSOImpl.class);
	
	@Autowired
	MessageDao dao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	protected BaseDao<Message> getDao() {
		return dao;
	}

	@Override
	public Message addMsg(Map<String, Object> map) {
		if (!map.containsKey(MapperDict.title) || !map.containsKey(MapperDict.type) || !map.containsKey(MapperDict.typegroup) || (!map.containsKey(MapperDict.adminuid) && !map.containsKey(MapperDict.fromuseruid))) {
			return null;
		}
		//判断map中对应type是否含有对应字段，没有则返回null
		EMsg[] nothavecontent = {EMsg.feeSuccess, EMsg.getPK, EMsg.bNoPK, EMsg.bNoPKToSelf, EMsg.bMonthNoCount, EMsg.bYesTime, EMsg.bReselectTime, EMsg.bNotHandle, EMsg.bNotHandleToslfe, EMsg.aNoAlltime, EMsg.aNoAlltimeToslfe, EMsg.aNotHandle, EMsg.waitabJudge, EMsg.openAppNotPass,
				EMsg.openAppPass, EMsg.openFileNotPass, EMsg.openFilePass, EMsg.openNotPass, EMsg.openPass, EMsg.prepfileNotPass, EMsg.prepfilePass, EMsg.repFileNotPass, EMsg.repFilePass, EMsg.financeScore, EMsg.scoreCanPK};
		EMsg[] havelevel = {EMsg.demotion, EMsg.appRepHint, EMsg.repCountDown, EMsg.repPass, EMsg.repNotPass, EMsg.secondNotPass, EMsg.secondPass, EMsg.repReportNotPass, EMsg.getCertificate, EMsg.expertAbsent, EMsg.appExpHint, EMsg.expAppNotPass,
				EMsg.expAppPass, EMsg.expReportNotPass, EMsg.expReportPass, EMsg.expInterviewNotPass, EMsg.becomeExp, EMsg.scoreUpGrade};
		EMsg[] havefromuseruid = {EMsg.comment, EMsg.replyComment, EMsg.getPK, EMsg.bNoPK, EMsg.bReselectTime, EMsg.bNotHandle, EMsg.bNotHandleToslfe, EMsg.aNoAlltime, EMsg.aNotHandle, EMsg.waitabJudge};
		EMsg[] havefromuseruidandadminuid ={EMsg.bNoPKToSelf, EMsg.bYesTime, EMsg.aNoAlltimeToslfe, EMsg.aNotHandle2};
		EMsg[] havetime = {EMsg.bYesTime, EMsg.openFilePass, EMsg.repFilePass, EMsg.expReportPass, EMsg.filePass};
		EMsg[] haveresult = {EMsg.compExpertSuccess, EMsg.compExpertSuccessToExpert};
		EMsg[] havecount = {EMsg.bMonthNoCount, EMsg.fileNotPass};
		EMsg[] havecount2 = {EMsg.bMonthNoCount};
		EMsg[] havecause = {EMsg.cancelPK};
		EMsg[] havescore = {EMsg.financeScore};
		EMsg[] haveyear = {EMsg.bMonthNoCount};
		EMsg[] havemonth = {EMsg.bMonthNoCount};
		//EMsg[] nothavehref = {EMsg.system, EMsg.compfailed};
		EMsg type = (EMsg)map.get(MapperDict.type);
		if (! Arrays.asList(nothavecontent).contains(type)) {
			if (! map.containsKey(MapperDict.content)) 
				return null;
		}
		if (Arrays.asList(havelevel).contains(type)) {
			if (! map.containsKey(MapperDict.level)) 
				return null;
		}
		if (Arrays.asList(havefromuseruid).contains(type)) {
			if (! map.containsKey(MapperDict.fromuseruid)) 
				return null;
		} else {
			if (! map.containsKey(MapperDict.adminuid)) 
				return null;
		}
		//判断map中是否同时包含adminuid和fromuseruid两个字段
		if (Arrays.asList(havefromuseruidandadminuid).contains(type)) {
			if (map.containsKey(MapperDict.fromuseruid) && map.containsKey(MapperDict.adminuid)) {
				User fromuser = userDao.get((String)map.get(MapperDict.fromuseruid));
				if (fromuser != null) {
					map.put(MapperDict.fromuser, fromuser);
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		if (Arrays.asList(havetime).contains(type)) {
			if (! map.containsKey(MapperDict.time)) 
				return null;
		}
		if (Arrays.asList(haveresult).contains(type)) {
			if (! map.containsKey(MapperDict.result)) 
				return null;
		}
		if (Arrays.asList(havecount).contains(type)) {
			if (! map.containsKey(MapperDict.count)) 
				return null;
		}
		if (Arrays.asList(havecount2).contains(type)) {
			if (! map.containsKey(MapperDict.count2)) 
				return null;
		}
		if (Arrays.asList(havecause).contains(type)) {
			if (! map.containsKey(MapperDict.cause)) 
				return null;
		}
		if (Arrays.asList(havescore).contains(type)) {
			if (! map.containsKey(MapperDict.score)) 
				return null;
		}
		if (Arrays.asList(haveyear).contains(type)) {
			if (! map.containsKey(MapperDict.year)) 
				return null;
		}
		if (Arrays.asList(havemonth).contains(type)) {
			if (! map.containsKey(MapperDict.month)) 
				return null;
		}
		Message message = new Message();
		if (map.containsKey(MapperDict.adminuid)) {
			message.setFromuid(map.get(MapperDict.adminuid).toString());
		} else {
			message.setFromuid((String)map.get(MapperDict.fromuseruid));
		}
		message.setTitle(getTitle(map));
		message.setContent(getContent(map));
		message.setHref((String)map.get(MapperDict.href));
		message.setType(type);
		message.setTypegroup((EMsgGroup)map.get(MapperDict.typegroup));
		if (dao.insert(message)) {
			return message;
		}
		log.info("Failed to send message");
		return null;
	}
	
	/**
	 * 得到消息的题目，将map中的href和title进行匹配，将a链接作为String返回
	 * @param map
	 * @return
	 * @author chenminchang
	 */
	private String getTitle(Map<String, Object> map) {
		try {
			return FreemarkerUtil.process(MessageConf.ManagerMsgTmplPath, MessageConf.MsgTitleTmpl, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到消息的内容，将通知类型的消息，根据type转换为String返回
	 * @param map
	 * @return
	 * @author chenminchang
	 */
	private String getContent(Map<String, Object> map) {
		try {
			return FreemarkerUtil.process(MessageConf.ManagerMsgTmplPath, MessageConf.MsgContentTmpl, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
