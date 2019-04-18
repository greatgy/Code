package com.supergenius.web.admin.manger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.ListUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.web.admin.manager.helper.ChallengeHP;
import com.supergenius.web.admin.manager.helper.EmailHP;
import com.supergenius.web.admin.manager.helper.MsgHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.manager.entity.ConfeMember;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.entity.Dynamic;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.PKJudgement;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.entity.PKStateDetail;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EConfer;
import com.supergenius.xo.manager.enums.EDynamic;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.manager.enums.EPKLog;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.enums.ETicket;
import com.supergenius.xo.manager.service.ConfeMemberSO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.manager.service.DynamicSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
import com.supergenius.xo.manager.service.PkStateDetailSO;
import com.supergenius.xo.manager.service.UserLevelSO;
import com.supergenius.xo.user.entity.OrderGoods;
import com.supergenius.xo.user.entity.TradeDetail;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.enums.ETrade;
import com.supergenius.xo.user.service.OrderGoodsSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 挑战管理控制器
 * 
 * @author liubin
 * @date 2016-10-20 下午12:32:53
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class ChallengeAdminer extends BaseController {

	private static Logger log = LoggerFactory.getLogger(ChallengeAdminer.class);
	
	@Autowired
	PkScheduleSO so;

	@Autowired
	PkStateDetailSO pkStateDetailSO;

	@Autowired
	UserSO userSO;

	@Autowired
	JudgeSO judgeSO;

	@Autowired
	ConferenceSO conferenceSO;

	@Autowired
	UserLevelSO userLevelSO;

	@Autowired
	DynamicSO dynamicSO;
	
	@Autowired
	OrderGoodsSO orderGoodsSO;
	
	@Autowired
    ConfeMemberSO confeMemberSO;
	
	/**
	 * 跳转挑战管理页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-20下午2:09:27
	 * @return String
	 */
	@RequestMapping(value = "/manager/challenge", method = RequestMethod.GET)
	public String challenge(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.challenge.name());
		model.put(ViewKeyDict.channelname, EChannel.challenge.getName());
		model.put(ViewKeyDict.site, EChannel.manager.name());
		model.put(ViewKeyDict.totalpkcount, so.getCount());
		model.put(ViewKeyDict.successpkcount, so.getCount(EPKStage.getSuccess()));
		model.put(ViewKeyDict.failpkcount, so.getCount(EPKStage.getAppFaileds()));
		double totalincome = 0;
		List<PKSchedule> list = so.getList(null, EPKStage.getEndApps());
		if (list.size() > 0 && list != null) {
			for (PKSchedule pkSchedule : list) {
				totalincome += pkSchedule.getTicketsalecount() * pkSchedule.getTicketprice();
			}
		}
		model.put(ViewKeyDict.totalincome, totalincome);
		model.put(ViewKeyDict.majors, EMajor.getValueAndChinese());
		model.put(ViewKeyDict.map, EStudentLevel.getEsudentsLevelMap());
		return "dochallenge";
	}

	/**
	 * 查询得到数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25上午11:30:47
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ajax_challenge(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ChallengeHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 获得查询条件数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2上午11:26:07
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/judge/search_condition", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajax_search_condition(Map<String, Object> model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> typeMap = new HashMap<>();
		for (EJudge judge : EJudge.values()) {
			typeMap.put(judge.toString(), judge.getName());
		}
		map.put(ViewKeyDict.type, typeMap);
		return map;
	}

	/**
	 * 获得挑战明细
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-15下午7:48:37
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/getpkdetail", method = RequestMethod.GET)
	@ResponseBody
	public String ajax_getpkdetail(Map<String, Object> model, String uid, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid)) {
			List<PKStateDetail> list = pkStateDetailSO.getListByEnable(uid);
			for (PKStateDetail pkStateDetail : list) {
				User user = userSO.get(pkStateDetail.getUseruid());
				if (user != null) {
					pkStateDetail.setUsername(user.getShowname());
				}
			}
			return JsonUtil.toJson(list, Json.webStrategy);
		}
		return null;
	}
	
	/**
	 * 获得挑战观众
	 * @param model
	 * @param uid
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-15下午8:41:31
	 * @return List<PKStateDetail>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/getpkview", method = RequestMethod.GET)
	@ResponseBody
	public String ajax_getpkview(Map<String, Object> model, String uid, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(uid)) {
			List<User> users = new ArrayList<User>();
			List<OrderGoods> list = orderGoodsSO.getListByRefuid(uid, EGoods.ticket);
			for (OrderGoods orderGoods : list) {
				User user = userSO.get(orderGoods.getUseruid());
				if (user != null) {
					users.add(user);
				}
			}
			return JsonUtil.toJson(users, Json.webStrategy);
		}
		return null;
	}
	
	/**
	 * 按条件得到查询指定裁判
	 * 
	 * @param model
	 * @param major
	 * @param request
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午3:07:15
	 * @return ResponseEntity<Map<String,Object>>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/specifyjudge", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ajax_getjudgelist(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ChallengeHP.queryjudge(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 指定主裁判并创建会议室,生成挑战
	 * 
	 * @param uid
	 * @param judgementuid
	 * @param judgementuid2
	 * @param judgementchairuid
	 * @param setjudgement1
	 * @param setjudgement2
	 * @param setjudgement3
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午5:10:52
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/setjudgement", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_specify_judge(String uid, String judgementuid, String judgementuid2, String judgementchairuid, String setjudgement1, String setjudgement2, String setjudgement3) {
		if (StrUtil.isEmpty(uid)) {
			return result(MsgKeyDict.editFailed);
		}
		PKSchedule pkSchedule = so.get(uid);
		if (pkSchedule != null) {
			List<PKJudgement> list = new ArrayList<>();
			if (StrUtil.isNotEmpty(setjudgement1)) {
				Judge judge = judgeSO.getOne(setjudgement1);
				if (judge != null) {
					pkSchedule.setJudgementuid(judge.getUseruid());
					pkSchedule.setJudgementsn(judge.getSn());
					PKJudgement judgement = new PKJudgement(pkSchedule.getUid(), AdminHP.getAdminUid(), judge.getUseruid(), pkSchedule.getLevel(), EStatus.enable);
					list.add(judgement);
				}
			} else if (StrUtil.isEmpty(judgementuid)) {
				return result(MsgKeyDict.editFailed);
			}
			if (StrUtil.isNotEmpty(setjudgement2)) {
				Judge judge2 = judgeSO.getOne(setjudgement2);
				if (judge2 != null) {
					pkSchedule.setJudgementuid2(judge2.getUseruid());
					pkSchedule.setJudgementsn2(judge2.getSn());
					PKJudgement judgement = new PKJudgement(pkSchedule.getUid(), AdminHP.getAdminUid(), judge2.getUseruid(), pkSchedule.getLevel(), EStatus.enable);
					list.add(judgement);
				}
			} else if (StrUtil.isEmpty(judgementuid2)) {
				return result(MsgKeyDict.editFailed);
			}
			if (StrUtil.isNotEmpty(setjudgement3)) {
				Judge judge3 = judgeSO.getOne(setjudgement3);
				if (judge3 != null) {
					pkSchedule.setJudgementchairuid(judge3.getUseruid());
					pkSchedule.setJudgementchairsn(judge3.getSn());
					pkSchedule.setStage(EPKStage.waitingCreateSeegle);
			        pkSchedule.setETicket(ETicket.waiting);
			        PKJudgement judgement = new PKJudgement(pkSchedule.getUid(), AdminHP.getAdminUid(), judge3.getUseruid(), pkSchedule.getLevel(), EStatus.enable);
					list.add(judgement);
				}
			} else {
				return result(MsgKeyDict.editFailed);
			}
			PKStateDetail pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.setJudgementChair, EPKStage.waitingCreateSeegle, EPKLog.sysAction);
			so.update(pkSchedule, pkStateDetail, list);
			if (!(MsgHP.sendPKSpecifyChairJudgementMsg(EMsgGroup.system, EMsg.createPK, pkSchedule.getName(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()),
					pkSchedule.getPkuseruid(), AdminHP.getAdminUid(), pkSchedule.getName())
					&& MsgHP.sendPKSpecifyChairJudgementMsg(EMsgGroup.system, EMsg.createPK, pkSchedule.getName(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()),
							pkSchedule.getPkuseruid2(), AdminHP.getAdminUid(), pkSchedule.getName())
					&& MsgHP.sendPKSpecifyChairJudgementMsg(EMsgGroup.system, EMsg.createPKToJudge, pkSchedule.getName(),
							WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), pkSchedule.getJudgementuid(), AdminHP.getAdminUid(), pkSchedule.getName())
					&& MsgHP.sendPKSpecifyChairJudgementMsg(EMsgGroup.system, EMsg.createPKToJudge, pkSchedule.getName(),
							WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), pkSchedule.getJudgementuid2(), AdminHP.getAdminUid(), pkSchedule.getName()) && MsgHP
						.sendPKSpecifyChairJudgementMsg(EMsgGroup.system, EMsg.createPKToJudge, pkSchedule.getName(),
								WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), pkSchedule.getJudgementchairuid(), AdminHP.getAdminUid(), pkSchedule.getName()))) {
				return result(MsgKeyDict.sendFailed);
			}  
			return success();
//			ConfEntity confEntity = new ConfEntity();//去掉会议室创建功能
//			confEntity.setConfname(pkSchedule.getName());
//			confEntity.setBegintime(pkSchedule.getPktime().toString());
//			confEntity.setConf_password_md5(DigestUtils.md5Hex(SysConf.password));
//			confEntity.setManage_password_md5(DigestUtils.md5Hex(SysConf.password));
//			confEntity.setGrouptype(SeegleConstant.DEfAULT_GROUP);
//			confEntity.setMax_conf_user(SysConf.Max_conf_user);
//			confEntity.setMax_conf_spokesman(SysConf.Max_conf_spokesman);
//			confEntity.setMax_conf_tourist(SysConf.Max_conf_tourist);
//			if (SeegleHP.create(pkSchedule, confEntity)) {
//				pkSchedule.setStage(EPKStage.waitingForChallenge);
//				PKStateDetail pkStateDetail2 = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.waitingCreateSeegle, EPKStage.waitingForChallenge, EPKLog.sysAction);
//				so.updateStage(pkSchedule, pkStateDetail2);
//				return success();
//			}
		}
		return result(MsgKeyDict.editFailed);
	}

	/**
	 * 添加qq群会议室
	 * @param uid
	 * @param qq
	 * @return
	 * @author liubin
	 * @createtime 2016-11-16下午3:11:44
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/addqqmetting", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_add_qqgroupmeeting(String uid, String qq) {
		if (StrUtil.isNotEmpty(uid)) {
			PKSchedule pkSchedule = so.get(uid);
		    if (pkSchedule != null) {
		    	pkSchedule.setQqgroup(qq);
		    	Conference conference = new Conference();
		    	conference.setSn(AutoIncrHP.getConferencesn());
		    	conference.setName(pkSchedule.getName());
		    	conference.setTypeuid(pkSchedule.getUid());
		    	conference.setTypename(pkSchedule.getName());
		    	conference.setCid("");//使用qq，此字段为空
		    	conference.setType(EConfer.challenge);
		    	conference.setStatus(EStatus.init);
		    	pkSchedule.setStage(EPKStage.waitingForChallenge);
				pkSchedule.setETicket(ETicket.start);
				pkSchedule.setConfeuid(conference.getUid());
				pkSchedule.setConfesn(conference.getSn());
				PKStateDetail pkStateDetail2 = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.waitingCreateSeegle, EPKStage.waitingForChallenge, EPKLog.sysAction);
				so.update(pkSchedule, pkStateDetail2, conference);
		    	String pkusers = ListUtil.toString(BaseStrDict.comma, pkSchedule.getPkuseruid(), pkSchedule.getPkuseruid2(), pkSchedule.getJudgementuid(), pkSchedule.getJudgementuid2(), pkSchedule.getJudgementchairuid());
		    	String [] pkuserarr = pkusers.split(BaseStrDict.comma);
		    	List<User> userList = new ArrayList<>();
				for (int i = 0; i < pkuserarr.length; i++) {
					userList.add(userSO.get(pkuserarr[i]));
				}
				for (int j = 0; j < userList.size(); j++) {
					ConfeMember confemember = new ConfeMember();
					confemember.setUseruid(userList.get(j).getUid());
					confemember.setConfuid(conference.getUid());
					confemember.setConfsn(conference.getSn());
					confemember.setCid(conference.getCid());
					confemember.setPkuid(pkSchedule.getUid());
					confemember.setPkname(pkSchedule.getName());
					if (userList.get(j).getUid().equals(pkSchedule.getPkuseruid())) {
						confemember.setUsertype(EConfemember.challenger);
					} else if (userList.get(j).getUid().equals(pkSchedule.getPkuseruid2())) {
						confemember.setUsertype(EConfemember.negativeSide);
					} else if (userList.get(j).getUid().equals(pkSchedule.getJudgementuid())) {
						confemember.setUsertype(EConfemember.challengerJudgment);
					} else if (userList.get(j).getUid().equals(pkSchedule.getJudgementuid2())) {
						confemember.setUsertype(EConfemember.negativeSideJudgment);
					} else if (userList.get(j).getUid().equals(pkSchedule.getJudgementchairuid())) {
						confemember.setUsertype(EConfemember.chiefUmpire);
					}
					confemember.setUsername(userList.get(j).getShowname());
					confemember.setUseralias(userList.get(j).getNickname());
					confemember.setEmail(userList.get(j).getEmail());
					confemember.setPhone(userList.get(j).getPhone());
					confemember.setType(EConfer.challenge);
					confeMemberSO.add(confemember);
				}
				boolean flag = true;
				for (User user : userList) {
					if (user != null) {
						if (!EmailHP.sendCreatPKConferenceSuccess(user.getShowname(), pkSchedule.getName(), user.getEmail(), qq, WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()))) {
							flag = false;
						}
					}
				}
				List<OrderGoods> orderGoods = orderGoodsSO.getListByRefuid(pkSchedule.getUid(), EGoods.ticket);
				if (orderGoods != null && orderGoods.size() > 0) {
					for (OrderGoods orderGoods2 : orderGoods) {
						User user = userSO.get(orderGoods2.getUseruid());
						if (user != null) {
							if (!MsgHP.sendCreatQQGroupmettingForPKToViewMsg(AdminHP.getAdminUid(), user.getUid(), pkSchedule.getName(), qq, pkSchedule.getUid())) {
								flag = false;
							}
						}
					}
				}
		    	if (!flag || !(MsgHP.sendCreatQQGroupmettingForPKMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid(), pkSchedule.getName(), qq, pkSchedule.getUid())
						&& MsgHP.sendCreatQQGroupmettingForPKMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid2(), pkSchedule.getName(), qq, pkSchedule.getUid())
						&& MsgHP.sendCreatQQGroupmettingForPKMsg(AdminHP.getAdminUid(), pkSchedule.getJudgementuid(), pkSchedule.getName(), qq, pkSchedule.getUid())
						&& MsgHP.sendCreatQQGroupmettingForPKMsg(AdminHP.getAdminUid(), pkSchedule.getJudgementuid2(), pkSchedule.getName(), qq, pkSchedule.getUid())
						&& MsgHP.sendCreatQQGroupmettingForPKMsg(AdminHP.getAdminUid(), pkSchedule.getJudgementchairuid(), pkSchedule.getName(), qq, pkSchedule.getUid()))) {
					return result(MsgKeyDict.sendFailed);
				}
		    	return success();
		    }
		}
		return result(MsgKeyDict.addFailed);
	}
	
	/**
	 * 编辑门票信息和修改会议室密码
	 * 
	 * @param uid
	 * @param confeuid
	 * @param ticketprice
	 * @param ticketcount
	 * @param password
	 * @param repassword
	 * @return
	 * @author liubin
	 * @createtime 2016-11-3下午3:16:22
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/editticket", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_edit_tickit(String uid, String confeuid, String ticketprice, String ticketcount, String password, String repassword) {
//		if (StrUtil.isNotEmpty(confeuid) && StrUtil.isNotEmpty(uid)) {
			if (StrUtil.isNotEmpty(uid)) {	
//			Conference conference = conferenceSO.get(confeuid);// 去掉会议室密码修改功能
			PKSchedule pkSchedule = so.get(uid);
//			if (conference != null && pkSchedule != null) {
//				if (StrUtil.isNotEmpty(password) && StrUtil.isNotEmpty(repassword) && password.equals(repassword)) {
//					conference.setPassword(password);
//					AdminLog adminLog = new AdminLog();
//					adminLog.setAdminuid(AdminHP.getAdminUid());
//					adminLog.setDataid(conference.getUid());
//					adminLog.setChannel(EChannel.challenge.toInt());
//					adminLog.setOperation(EAdminLog.updateConferencePassword.getName());
//					adminLog.setData(EAdminLog.updateConferencePassword.getName());
//					conferenceSO.update(conference, adminLog);
//				} else {
//					return result(MsgKeyDict.pwdIsWrong);
//				}
//			}
			if (StrUtil.isNotEmpty(ticketcount)) {
				pkSchedule.setTicketcount(Integer.parseInt(ticketcount));
			} else {
				return result(MsgKeyDict.editFailed);
			}
			if (StrUtil.isNotEmpty(ticketprice)) {
				pkSchedule.setTicketprice(Double.parseDouble(ticketprice));
				pkSchedule.setETicket(ETicket.start);
			} else {
				return result(MsgKeyDict.editFailed);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setDataid(pkSchedule.getUid());
			adminLog.setChannel(EChannel.challenge.toInt());
			adminLog.setDesc(EAdminLog.updateTicket.getName());
			adminLog.setOperation(EAdminLog.updateTicket.getName());
			adminLog.setData(EAdminLog.updateTicket.getName());
			so.update(pkSchedule, adminLog);
			return success();
		}
		return result(MsgKeyDict.editFailed);
	}

	/**
	 * 更新挑战结束状态
	 * @param uid
	 * @return
	 * @author liubin
	 * @createtime 2016年11月23日下午3:41:16
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/setover", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_setpkover(String uid) {
		if (StrUtil.isNotEmpty(uid)) {
			PKSchedule pkSchedule = so.get(uid);
			if (pkSchedule != null) {
				pkSchedule.setStage(EPKStage.challengSeegleOver);
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setDataid(pkSchedule.getUid());
				adminLog.setChannel(EChannel.challenge.toInt());
				adminLog.setDesc(EAdminLog.updatePKScheduleStatus.getName());
				adminLog.setOperation(EAdminLog.updatePKScheduleStatus.getName());
				adminLog.setData(EAdminLog.updatePKScheduleStatus.getName());
				PKStateDetail pkStateDetail = new PKStateDetail(uid, AdminHP.getAdminUid(), EPKStage.challenging, EPKStage.challengSeegleOver, EPKLog.sysAction);
				so.update(pkSchedule, pkStateDetail, adminLog);
				return success();
			}
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 设置挑战结果
	 * 
	 * @param uid
	 * @param userscore
	 * @param userscore2
	 * @param desc
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午4:50:37
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/setresult", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_setresult(String uid, String userscore, String userscore2, String desc) {
		double score = 0;
		double score2 = 0;
		if (StrUtil.isNotEmpty(uid)) {
			PKSchedule pkSchedule = so.get(uid);
			boolean flag = true;
			if (pkSchedule != null && pkSchedule.getStage() == EPKStage.challengSeegleOver) {
				EStudentLevel studentLevel = EStudentLevel.basic;
			    if (pkSchedule.getLevel() == EStudentLevel.majordomo) {
			    	studentLevel = EStudentLevel.manager;
			    }
				if (StrUtil.isNotEmpty(userscore)) {
					score = Double.parseDouble(userscore);
				}
				if (StrUtil.isNotEmpty(userscore2)) {
					score2 = Double.parseDouble(userscore2);
				}
				pkSchedule.setUserscore(score);
				pkSchedule.setUserscore2(score2);
				if (StrUtil.isNotEmpty(desc)) {
					pkSchedule.setDesc(desc);
				}
				if (ChallengeHP.getResult(score, score2) == -1) {
					PKStateDetail pkStateDetail = null;
					if (ChallengeHP.isDownLevel(pkSchedule.getPkuseruid(), pkSchedule, studentLevel)) {
						ChallengeHP.downLevel(uid, pkSchedule, studentLevel);
					} else {
						Dynamic dynamic = new Dynamic();
						dynamic.setUseruid(pkSchedule.getPkuseruid());
						dynamic.setType(EDynamic.pkFailLess5);
						dynamic.setTitle(String.format(EDynamic.pkFailLess5.getName(), userSO.get(pkSchedule.getPkuseruid()).getShowname(),
								ChallengeHP.getFailNumber(pkSchedule.getPkuseruid(), pkSchedule, pkSchedule.getLevel())));
						dynamic.setContent(pkSchedule.getName());
						dynamic.setDataMap_href(WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()));
						dynamicSO.add(dynamic);
					}
					if (score == 0 && score2 == 0) {// 挑战者失败，如果是双方均缺席的情况下，需要对被挑战着也进行惩罚
						if (ChallengeHP.isDownLevel(pkSchedule.getPkuseruid2(), pkSchedule, studentLevel)) {
							ChallengeHP.downLevel(uid, pkSchedule, studentLevel);
						}
						pkSchedule.setStage(EPKStage.bothAbsent);
						pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.challengSeegleOver, EPKStage.bothAbsent, EPKLog.sysAction);
						pkSchedule.setETicket(ETicket.end);
						so.update(pkSchedule, pkStateDetail);
						if (!ChallengeHP.sendPKAllMemberMsg(pkSchedule)) {
							flag = false;
						}
						if (!MsgHP.sendPKUseraAbsentMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid(), pkSchedule.getName(), pkSchedule.getUid()) || !MsgHP.sendPKUserbAbsentMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid2(), pkSchedule.getName(), pkSchedule.getUid())) {
							flag = false;
						}
					} else if (score == 0 && score2 != 0) {//挑战者失败，是因为其缺席
						pkSchedule.setStage(EPKStage.pkuserAbsent);
						pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.challengSeegleOver, EPKStage.pkuserAbsent, EPKLog.sysAction);
						pkSchedule.setETicket(ETicket.end);
						so.update(pkSchedule, pkStateDetail);
						if (!ChallengeHP.sendPKAllMemberMsg(pkSchedule)) {
							flag = false;
						}
						if (!MsgHP.sendPKUserWinGetRewardMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid2(), pkSchedule.getName(), pkSchedule.getUid()) || !MsgHP.sendPKUseraAbsentMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid(), pkSchedule.getName(), pkSchedule.getUid())) {
							flag = false;
						}
						User user = userSO.get(pkSchedule.getPkuseruid2());
						if (user != null) {
							if (!EmailHP.sendPKGetResult(user.getShowname(), pkSchedule.getName(), user.getEmail(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), EContent.email_rewardpkwinuser)) {
								flag = false;
							}
						}
					} else {//双方均未缺席
						// 挑战者失败，给被挑战着钱，判断裁判应该得到多少钱
						User user = userSO.get(pkSchedule.getPkuseruid2());
						if (user != null && score2 != 0) {
							user.plusAccount(SysConf.PKWinRewardMoney);
							user.plusTotalincome(SysConf.PKWinRewardMoney);
						}
						TradeDetail tradeDetail = new TradeDetail(user.getUid(), pkSchedule.getUid(), SysConf.PKWinRewardMoney, user.getAccount(), AutoIncrHP.getTradeDetailsn(), null,
								ETrade.challengetowin, ETrade.challengetowin.getName(), ESite.manager);
						pkSchedule.setStage(EPKStage.challengeSuccess);
						pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.challengSeegleOver, EPKStage.challengeSuccess, EPKLog.sysAction);
						pkSchedule.setETicket(ETicket.end);
						so.update(pkSchedule, pkStateDetail, user, tradeDetail);
						if (!ChallengeHP.sendPKAllMemberMsg(pkSchedule)) {
							flag = false;
						}
						if (!MsgHP.sendPKUserWinGetRewardMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid2(), pkSchedule.getName(), pkSchedule.getUid()) || !EmailHP.sendPKGetResult(user.getShowname(), pkSchedule.getName(), user.getEmail(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), EContent.email_rewardpkwinuser)) {
							flag = false;
						}
					}
				} else { // 挑战者获胜，包括双方平手，给三位裁判奖励
					PKStateDetail pkStateDetail = null;
					ChallengeHP.pkuserWinGetLeveUp(pkSchedule, studentLevel);
					if (ChallengeHP.getResult(score, score2) == 1) {// 当挑战者获胜时，需要对被挑战者进行惩罚，并对挑战者进行奖励
						if (ChallengeHP.isDownLevel(pkSchedule.getPkuseruid2(), pkSchedule, studentLevel)) {
							ChallengeHP.downLevel(uid, pkSchedule, studentLevel);
						}
						User user = userSO.get(pkSchedule.getPkuseruid());
						if (user != null) {
							user.plusAccount(SysConf.PKWinRewardMoney);
							user.plusTotalincome(SysConf.PKWinRewardMoney);
						}
						TradeDetail tradeDetail = new TradeDetail(user.getUid(), pkSchedule.getUid(), SysConf.PKWinRewardMoney, user.getAccount(), AutoIncrHP.getTradeDetailsn(), null,
								ETrade.challengetowin, ETrade.challengetowin.getName(), ESite.manager);
						if (score2 == 0) {
							pkSchedule.setStage(EPKStage.pkuser2Absent);
							pkSchedule.setETicket(ETicket.end);
							pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.challengSeegleOver, EPKStage.pkuser2Absent, EPKLog.sysAction);
							so.update(pkSchedule, pkStateDetail, user, tradeDetail);
							if (!ChallengeHP.sendPKAllMemberMsg(pkSchedule)) {
								flag = false;
							}
							if (!MsgHP.sendPKUserbAbsentMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid2(), pkSchedule.getName(), pkSchedule.getUid()) || !MsgHP.sendPKUserWinGetRewardMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid(), pkSchedule.getName(), pkSchedule.getUid()) || !EmailHP.sendPKGetResult(user.getShowname(), pkSchedule.getName(), user.getEmail(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), EContent.email_rewardpkwinuser)) {
								flag = false;
							}
						} else {
							pkSchedule.setStage(EPKStage.challengeSuccess);
							pkSchedule.setETicket(ETicket.end);
							pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.challengSeegleOver, EPKStage.challengeSuccess, EPKLog.sysAction);
							so.update(pkSchedule, pkStateDetail, user, tradeDetail);
							if (!ChallengeHP.sendPKAllMemberMsg(pkSchedule)) {
								flag = false;
							}
							if (!MsgHP.sendPKUserWinGetRewardMsg(AdminHP.getAdminUid(), pkSchedule.getPkuseruid(), pkSchedule.getName(), pkSchedule.getUid()) || !EmailHP.sendPKGetResult(user.getShowname(), pkSchedule.getName(), user.getEmail(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), EContent.email_rewardpkwinuser)) {
								flag = false;
							}
						}
					} else {
					    //双方平手
					    pkSchedule.setStage(EPKStage.challengeSuccess);
					    pkStateDetail = new PKStateDetail(pkSchedule.getUid(), AdminHP.getAdminUid(), EPKStage.challengSeegleOver, EPKStage.challengeSuccess, EPKLog.sysAction);
					    pkSchedule.setETicket(ETicket.end);
					    so.update(pkSchedule, pkStateDetail);
					    if (!ChallengeHP.sendPKAllMemberMsg(pkSchedule)) {
							flag = false;
						}
					}
				}
			}
			ChallengeHP.rewardJudgement(pkSchedule, pkSchedule.getJudgementuid());
			ChallengeHP.rewardJudgement(pkSchedule, pkSchedule.getJudgementuid2());
			ChallengeHP.rewardJudgement(pkSchedule, pkSchedule.getJudgementchairuid());
			if (flag) {
				return success();
			}
		}
		log.error("the pkschedule uid is null!!!");
		return result(MsgKeyDict.doFailed);
	}

	/**
	 * 冻结或者解冻挑战的状态
	 * @param status
	 * @param uid
	 * @param dopwd
	 * @param desc
	 * @param adminuid
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午6:40:26
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/manager/ajax/challenge/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_setstatus(@PathVariable int status, String[] ids, String dopwd, String desc, String adminuid) {
		EStatus status2 = EStatus.get(status);
		if (AdminHP.isDopwd(dopwd)) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(adminuid);
			adminLog.setChannel(EChannel.challenge.toInt());
			adminLog.setOperation(EAdminLog.updatePKScheduleStatus.getName());
			adminLog.setData(EAdminLog.updatePKScheduleStatus.getName());
			adminLog.setDataid(ids[0]);
			adminLog.setDesc(desc);
			PKSchedule pkSchedule = so.get(ids[0]);
			if (pkSchedule != null) {
				pkSchedule.setStatus(status2);
			}
			so.update(pkSchedule, adminLog);
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 上传挑战题目
	 * @param uid
	 * @param filedata
	 * @return
	 * @author chenminchang
	 * @create 2016年11月24日下午12:52:08
	 */
	@RequestMapping(value = "/manager/ajax/challenge/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajax_uploadfile(String uid, @RequestParam MultipartFile filedata) {
		PKSchedule pk = so.get(uid);
		if (pk != null && filedata != null && filedata.getSize() > 0) {
			String path = FileUtil.uploadFile(filedata, SysConf.PKFilePath);
			so.updateFile(pk.getUid(), path);
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
	
	/**
	 * 下载文件
	 * @return
	 * @author chenminchang
	 * @throws Exception 
	 * @create 2016-10-27上午11:54:22
	 */
	@RequestMapping(value = "/manager/challenge/download", method = RequestMethod.GET)
	public String lecture_download(HttpServletResponse response, HttpServletRequest request, String uid) throws Exception {
		if (StrUtil.isNotEmpty(uid)) {
			PKSchedule pk = so.get(uid);
			if (pk != null)
				FileUtil.download(pk.getFilename(), pk.getFile(), request, response);
		}
		return null;
	}
}
