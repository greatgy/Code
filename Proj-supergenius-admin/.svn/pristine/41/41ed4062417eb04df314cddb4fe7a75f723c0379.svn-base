package com.supergenius.web.admin.manger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.manager.helper.BaseConferenceHP;
import com.supergenius.server.manager.helper.SeegleHP;
import com.supergenius.server.manager.third.seegle.SeegleConference;
import com.supergenius.server.manager.third.seegle.SeegleToken;
import com.supergenius.server.manager.third.seegle.entity.ConfEntity;
import com.supergenius.web.admin.manager.helper.ConferenceHP;
import com.supergenius.web.admin.manager.helper.EmailHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EConfer;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 会议室管理
 * @author chenminchang
 * @date 2016-11-9 下午3:57:21 
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ConferenceAdminer extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ConferenceAdminer.class);
	@Autowired
	ConferenceSO so;
	@Autowired
	UserSO userSO;
	@Autowired
	PkScheduleSO pkscheduleSO;
	@Autowired
	AppReplySO appReplySO;
	
	/**
	 * 进入会议室管理
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-11-9下午4:00:05
	 */
	@RequestMapping( value = "/manager/conference", method = RequestMethod.GET)
	public String manager_conference(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.conference.name());
		model.put(ViewKeyDict.channelname, EChannel.conference.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.total, so.getCount());
		model.put(ViewKeyDict.pk, so.getCountByType(EConfer.challenge));
		model.put(ViewKeyDict.reply, so.getCountByType(EConfer.reply));
		model.put(ViewKeyDict.judge, so.getCountByType(EConfer.judgment));
		model.put(ViewKeyDict.expert, so.getCountByType(EConfer.expert));
		model.put(ViewKeyDict.conferences, EConfer.getValueAndChinese());
		BaseConferenceHP.updateComferenceStatus();
		return "doconference";
	}

	/**
	 *  查询会议室
	 * @param model
	 * @param request
	 * @return
	 * @author chenminchang
	 * @create 2016-11-10下午6:11:40
	 */
	@RequestMapping(value = { "/manager/ajax/conference/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> conference_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ConferenceHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 修改会议室
	 * @param conference
	 * @return
	 * @author chenminchang
	 * @create 2016-11-11上午10:42:38
	 */
	@RequestMapping(value = { "/manager/ajax/conference/edit" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> conference_edit(Conference conference) {
		Conference oldconference = so.get(conference.getUid());
		if (oldconference	 != null) {
			String token = SeegleToken.getAdminToken();
			if (SeegleConference.get(token, oldconference.getCid()) != null) {
				ConfEntity confEntity = new ConfEntity();
				confEntity.setCid(oldconference.getCid());
				confEntity.setConfname(conference.getName());
				confEntity.setBegintime(conference.getBegintimeStr());
				confEntity.setEndtime(conference.getEndtimeStr());
				confEntity.setGrouptype(oldconference.getGrouptype());
				confEntity.setMax_conf_user(String.valueOf(conference.getMaxcountuser()));
				confEntity.setMax_conf_tourist(String.valueOf(conference.getMaxcounttourist()));
				confEntity.setMax_conf_spokesman(String.valueOf(conference.getMaxcountspokesman()));
				confEntity.setConf_password_md5(conference.getPassword());
				confEntity.setManage_password_md5(conference.getPasswordadmin());
				confEntity.setDescription(conference.getDescription());
				String updatenum = SeegleConference.update(token, confEntity);
				if (updatenum.equals("0")) {
					oldconference.setName(conference.getName());
					oldconference.setBegintime(conference.getBegintime());
					oldconference.setEndtime(conference.getEndtime());
					oldconference.setMaxcountuser(conference.getMaxcountuser());
					oldconference.setMaxcounttourist(conference.getMaxcounttourist());
					oldconference.setMaxcountspokesman(conference.getMaxcountspokesman());
					oldconference.setPassword(conference.getPassword());
					oldconference.setPasswordadmin(conference.getPasswordadmin());
					oldconference.setDescription(conference.getDescription());
					so.updateFields(oldconference);
					return success();
				} else {
					log.info("update conference failed, the conference uid:" + conference.getUid() + " and udpate result number:" + updatenum);
					return result(MsgKeyDict.doFailed);
				}
			} 
			log.info("you want to update conference does not exist in seegle, the conference uid:" + conference.getUid());
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 删除会议室
	 * @param uids
	 * @return
	 * @author chenminchang
	 * @create 2016-11-11下午1:56:42
	 */
	@RequestMapping(value = { "/manager/ajax/conference/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> conference_delete(String ids) {
		Conference conference = so.get(ids);
		if (conference != null && !EStatus.disable.equals(conference.getStatus())) {
			if (so.updateStatus(conference.getUid(), EStatus.disable)) {
				String token = SeegleToken.getAdminToken();
				if (SeegleConference.get(token, conference.getCid()) != null) {
					String deletenum = SeegleConference.delete(token, conference.getCid());
					if (deletenum.equals("0")) 
						log.info("delete conference success, the conference uid:" + conference.getUid());
					else
						log.info("delete conference failed, the conference uid:" + conference.getUid() + " and udpate result number:" + deletenum);
				} else {
					log.info("you want to delete conference does not exist in seegle, the conference uid:" + conference.getUid());
				}
			}
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 开启或关闭会议室
	 * @param id
	 * @param option
	 * @return
	 * @author chenminchang
	 * @create 2016-11-11下午3:08:21
	 */
	@RequestMapping(value = { "/manager/ajax/conference/time/{option:\\d+}" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> conference_time(String uid, @PathVariable int option) {
		Conference conference = so.get(uid);
		if (conference != null) {
			//判断会议室是否已经关闭或开启
			if (EStatus.disable.toString().equals(String.valueOf(option))) {//关闭会议室
				if (DateUtil.NowTime().isAfter(conference.getEndtime())) {
					so.updateStatus(conference.getUid(), EStatus.end);
					return success();
				}
			} else {//开启会议室
				if (DateUtil.NowTime().isBefore(conference.getEndtime()) && DateUtil.NowTime().isAfter(conference.getBegintime())) {
					so.updateStatus(conference.getUid(), EStatus.enable);
					return success();
				}
			}
			//走到这说明需要修改去seegle的会议室
			String token = SeegleToken.getAdminToken();
			if (SeegleConference.get(token, conference.getCid()) != null) {
				ConfEntity confEntity = new ConfEntity();
				confEntity.setCid(conference.getCid());
				confEntity.setConfname(conference.getName());
				if (EStatus.enable.toString().equals(String.valueOf(option))) {//开启会议室
					confEntity.setBegintime(DateUtil.getNow());
					confEntity.setEndtime(DateUtil.NowTime().plusHours(SysConf.DefaultConferenceTime).toString(DateUtil.FORMAT_DATETIME_CHINA));
				} else {//关闭会议室
					confEntity.setBegintime(conference.getBegintimeStr());
					confEntity.setEndtime(DateUtil.getNow());
				}
				confEntity.setGrouptype(conference.getGrouptype());
				confEntity.setMax_conf_user(String.valueOf(conference.getMaxcountuser()));
				confEntity.setMax_conf_tourist(String.valueOf(conference.getMaxcounttourist()));
				confEntity.setMax_conf_spokesman(String.valueOf(conference.getMaxcountspokesman()));
				confEntity.setConf_password_md5(conference.getPassword());
				confEntity.setManage_password_md5(conference.getPasswordadmin());
				confEntity.setDescription(conference.getDescription());
				String updatenum = SeegleConference.update(token, confEntity);
				if (updatenum.equals("0")) {
					return success();
				} else {
					log.info("open or close conference failed, the conference uid:" + conference.getUid() + " and udpate result number:" + updatenum);
				}
			} else {
				log.info("you want to open or close conference does not exist in seegle, the conference uid:" + conference.getUid());
			}
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 添加会议室成员
	 * @param type
	 * @param uids
	 * @return
	 * @author chenminchang
	 * @create 2016-11-14上午10:34:06
	 */
	@RequestMapping(value = { "/manager/ajax/conference/adduser" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> conference_adduser(String type, String useruids, String uid) {
		Conference conference = so.get(uid);
		if (conference != null && StrUtil.isNotEmpty(useruids)) {
			boolean result = true;
			boolean isadmin = false;
			EContent emailtype = EContent.email_inviteconfvisitor;
			EConfemember confetype = EConfemember.specialVisitor;
			String datatime = null;
			String refname = conference.getTypename();
			String refurl = null;
			if (EConfer.challenge.equals(conference.getType())) {//目前只有挑战和答辩线上
				datatime = pkscheduleSO.get(conference.getTypeuid()).getPktimeStr();
				refurl = WebConf.baseManagerPath + String.format(SysConf.PkConfHref, conference.getTypeuid());
			} else if (EConfer.reply.equals(conference.getType())) {
				datatime = appReplySO.get(conference.getTypeuid()).getOpentimeok();
				refurl = WebConf.baseManagerPath + String.format(SysConf.AppReplyConfHref, conference.getTypeuid());
			}
			if ("1".equals(type)) {
				isadmin = true;
				confetype = EConfemember.admin;
				emailtype = EContent.email_inviteconfadmin;
			}
			String[] useruidArr = useruids.split(ViewKeyDict.comma);
			for (String useruid : useruidArr) {
				User user = userSO.get(useruid);
				if (SeegleHP.addConfUser(user, conference, confetype, isadmin)) {
					if (!EmailHP.sendInviteUser(user.getShowname(), datatime, refname, refurl, emailtype, user.getEmail()))
						log.info(String.format("add invite user success in seegle, but send email failed. the conference uid:/% , and the user name:/% ,uid: /%", conference.getUid(), user.getName(), user.getUid()));
				} else {
					result = false;
				}
			}
			if (result)
				return success();
		}
		return result(MsgKeyDict.doFailed);
	}
}
