/**
 * 视高HP
 * ============================================================================
 * 声明：© 2013-2014 天才纵横版权所有
 * ----------------------------------------------------------------------------
 * Official Website: http://www.grandgeniusgroup.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2013 GrandGeniusGroup All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: liushaomin
 * ----------------------------------------------------------------------------
 * Create at: 2013-7-24 下午6:22:53
 * ============================================================================
 */
package com.supergenius.server.manager.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.ListUtil;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.manager.third.seegle.SeegleConfUser;
import com.supergenius.server.manager.third.seegle.SeegleConference;
import com.supergenius.server.manager.third.seegle.SeegleToken;
import com.supergenius.server.manager.third.seegle.entity.AuthEntity;
import com.supergenius.server.manager.third.seegle.entity.ConfEntity;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.manager.entity.AppReply;
import com.supergenius.xo.manager.entity.ConfeMember;
import com.supergenius.xo.manager.entity.Conference;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EConfer;
import com.supergenius.xo.user.entity.User;

/**
 * @author liushaomin
 * @modify chenminchang
 */
public class SeegleHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(SeegleHP.class);
	
	/**
	 * 创建会议室 挑战的
	 * 
	 * @param pkschedule
	 * @param conf
	 * @return
	 */
	public static boolean create(PKSchedule pkschedule, ConfEntity conf) {
		log.info(String.format("begin to invoke Create(pkschedule:%s, pkuser:%s, pkuser2:%s, judgementuid:%s, judgementuid2:%s, chairjudgesn:%s, conf:%s)", JsonUtil.toJson(pkschedule),
				pkschedule.getPkuseruid(), pkschedule.getPkuseruid(), pkschedule.getJudgementuid(), pkschedule.getJudgementuid2(), pkschedule.getJudgementchairsn(), JsonUtil.toJson(conf)));
		String addtoken = SeegleToken.getAdminToken();
		log.info(String.format("Create createtoken:%s", addtoken));
		AuthEntity auth = new AuthEntity(addtoken);
		String cid = SeegleConference.add(auth, conf);
		if (cid != null) {// 创建会议室成功
			log.info(String.format("Create Seegle succeed and the conference cid:%s", cid));
			// 将挑战双方 裁判双方和主裁判加入到会议室
			String adminList = String.valueOf(BaseMyPKHP.getPKChairJudge(pkschedule).getOid());
			String commonList = ListUtil.toString(",", String.valueOf(BaseMyPKHP.getPKUser1(pkschedule).getOid()), String.valueOf(BaseMyPKHP.getPKUser2(pkschedule).getOid()),
					String.valueOf(BaseMyPKHP.getPKJudge1(pkschedule).getOid()), String.valueOf(BaseMyPKHP.getPKJudge2(pkschedule).getOid()));
			String addnum = SeegleConfUser.addConfUser(addtoken, cid, adminList, commonList);
			if (addnum.equals("0")) {// 加入到会议室成功
				User pkuser1 = BaseMyPKHP.getPKUser1(pkschedule);
				User pkuser2 = BaseMyPKHP.getPKUser2(pkschedule);
				User pkjudge1 = BaseMyPKHP.getPKJudge1(pkschedule);
				User pkjudge2 = BaseMyPKHP.getPKJudge2(pkschedule);
				User pkchairjudge = BaseMyPKHP.getPKChairJudge(pkschedule);
				log.info(String.format("add user succeed，addnum:%s", addnum));
				log.info("zhucaipan oid:" + pkchairjudge.getOid());
				log.info("zhucaipan name:" + pkchairjudge.getShowname());
				log.info("pkuser showname:" + pkuser1.getShowname());
				log.info("pkjudgement showname:" + pkjudge1.getShowname());
				log.info("pkuser2 showname:" + pkuser2.getShowname());
				log.info("pkjudgement2 showname:" + pkjudge2.getShowname());
				Conference conference = new Conference();// 插入到conference表中
				conference.setSn(AutoIncrHP.getConferencesn());
				conference.setTypeuid(pkschedule.getUid());
				conference.setTypename(pkschedule.getName());
				conference.setCid(cid);
				conference.setName(conf.getConfname());
				conference.setType(EConfer.challenge);
				conference.setPassword(conf.getConf_password_md5());
				conference.setPasswordadmin(conf.getManage_password_md5());
				conference.setGrouptype(conf.getGrouptype());
				conference.setMaxcount(Integer.parseInt(conf.getMax_conf_user()));
				conference.setMaxcountuser(Integer.parseInt(conf.getMax_conf_user()));
				conference.setMaxcounttourist(Integer.parseInt(conf.getMax_conf_tourist()));
				conference.setMaxcountspokesman(Integer.parseInt(conf.getMax_conf_spokesman()));
				conference.setIsopen(true);
				conference.setIslock(false);
				conference.setIsautoclear(true);
				conference.setIsallvisible(true);
				conference.setIstop(false);
				conference.setDescription(conf.getDescription());
				conference.setStatus(EStatus.init); //未开始
				conference.setExpectJoinCount(5); //设置参加人数
				BaseConferenceHP.add(conference);
				// 往会议成员表中插入5条数据 confemember
				String[] number = commonList.split(",");
				List<User> userList = new ArrayList<>();
				for (int i = 0; i < number.length; i++) {
					userList.add(BaseUserHP.get(Integer.valueOf(number[i])));
				}
				for (int j = 0; j < userList.size(); j++) {
					ConfeMember confemember = new ConfeMember();
					confemember.setUseruid(userList.get(j).getUid());
					confemember.setConfuid(conference.getUid());
					confemember.setConfsn(conference.getSn());
					confemember.setCid(conference.getCid());
					confemember.setToken(addtoken);// ---------------
					confemember.setPkuid(pkschedule.getUid());
					confemember.setPkname(pkschedule.getName());
					if (userList.get(j).getUid().equals(pkschedule.getPkuseruid())) {
						confemember.setUsertype(EConfemember.challenger);
					} else if (userList.get(j).getUid().equals(pkschedule.getPkuseruid2())) {
						confemember.setUsertype(EConfemember.negativeSide);
					} else if (userList.get(j).getUid().equals(pkschedule.getJudgementuid())) {
						confemember.setUsertype(EConfemember.challengerJudgment);
					} else if (userList.get(j).getUid().equals(pkschedule.getJudgementuid2())) {
						confemember.setUsertype(EConfemember.negativeSideJudgment);
					}
					confemember.setUserid(conference.getCid());
					confemember.setUsername(userList.get(j).getShowname());
					confemember.setUseralias(userList.get(j).getShowname());
					confemember.setEmail(userList.get(j).getEmail());
					confemember.setPhone(userList.get(j).getPhone());
					confemember.setType(EConfer.challenge);
					BaseConfeMemberHP.add(confemember);
				}
				// 将主裁判加入到会议成员表
				ConfeMember confemember = new ConfeMember();
				confemember.setUseruid(BaseMyPKHP.getPKChairJudge(pkschedule).getUid());
				confemember.setConfuid(conference.getUid());
				confemember.setConfsn(conference.getSn());
				confemember.setCid(conference.getCid());
				confemember.setToken(addtoken);// ---------------
				confemember.setPkuid(pkschedule.getUid());
				confemember.setPkname(pkschedule.getName());
				confemember.setUsertype(EConfemember.chiefUmpire);
				confemember.setUserid(conference.getCid());
				confemember.setUsername(BaseMyPKHP.getPKChairJudge(pkschedule).getShowname());
				confemember.setUseralias(BaseMyPKHP.getPKChairJudge(pkschedule).getShowname());
				confemember.setType(EConfer.challenge);
				BaseConfeMemberHP.add(confemember);
				return true;
			}
			log.info(String.format("add  user fail， addnum:%s", addnum));
			return false;
		}
		log.info(String.format("Create Seegle fail， pk uid:%s", pkschedule.getUid()));
		return false;
	}

	/**
	 * 学员购买门票 添加到会议室
	 * 
	 * @param user
	 * @param typeuid
	 */
	public static boolean addConfUser(User user, PKSchedule pkschedule) {
		log.info(String.format("begin to invoke addConfUser(user:%s, pkschedule:%s)", JsonUtil.toJson(user), JsonUtil.toJson(pkschedule)));
		String addtoken = SeegleToken.getAdminToken();
		log.info(String.format("add tourist addtoken:%s", addtoken));
		Conference conference = BaseConferenceHP.getByTypeuid(pkschedule.getUid());
		if (conference != null) {// 加入到会议室
			if (SeegleConference.get(addtoken, conference.getCid()) != null) {
				String addnumber = SeegleConfUser.addConfUser(addtoken, conference.getCid(), null, String.valueOf(user.getOid()));
				if (addnumber.equals("0")) {
					log.info(String.format("add tourist succeed:%s", addnumber));
					log.info("add tourist name:" + user.getShowname());
					log.info("add tourist uid:" + user.getUid());
					log.info("add tourist oid:" + user.getOid());
					log.info("add tourist time:" + DateUtil.getNow());
					// 插入到会议成员表中
					ConfeMember confemember = new ConfeMember();
					confemember.setUseruid(user.getUid());
					confemember.setConfuid(conference.getUid());
					confemember.setConfsn(conference.getSn());
					confemember.setCid(conference.getCid());
					confemember.setPkuid(pkschedule.getUid());
					confemember.setPkname(pkschedule.getName());
					confemember.setUsertype(EConfemember.visitor);
					confemember.setUserid(conference.getCid());
					confemember.setUsername(user.getShowname());
					confemember.setUseralias(user.getShowname());
					confemember.setEmail(user.getEmail());
					confemember.setPhone(user.getPhone());
					confemember.setType(EConfer.challenge);
					BaseConfeMemberHP.add(confemember);
					BaseConferenceHP.incrExpectJoinCount(conference);//参加会议人数+1
					return true;
				}
				log.info(String.format("add tourist fail:%s", addnumber));
			}
			log.info(String.format("根据会议室名称获取会议室", "获取失败"));
		}
		return false;
	}

	/**
	 * 管理后台 往会议室中添加会议成员（角色可根据EConfemember）
	 * 观众，挑战裁判，答辩开题专家,(区分成员和管理员)
	 * @param user
	 * @param conference
	 * @param confemembertype
	 * @param isadmin
	 * @return
	 * @author chenminchang
	 * @create 2016-11-14上午11:58:25
	 */
	public static boolean addConfUser(User user, Conference conference, EConfemember confemembertype, boolean isadmin) {
		String usertype = "";
		String addAdminList = null;
		String addUserList = null;
		if (isadmin) {
			usertype = "Admin";
			addAdminList = String.valueOf(user.getOid());
		} else {
			usertype = "User";
			addUserList = String.valueOf(user.getOid());
		}
		log.info(String.format("begin to invoke addConf" + usertype + "(user:%s, confuid:%s)", JsonUtil.toJson(user), JsonUtil.toJson(conference)));
		String addtoken = SeegleToken.getAdminToken();
		log.info(String.format("add " + usertype + " " + confemembertype.name() + " addtoken:%s", addtoken));
		if (SeegleConference.get(addtoken, conference.getCid()) != null) {
			String addnumber = SeegleConfUser.addConfUser(addtoken, conference.getCid(), addAdminList, addUserList);
			if (addnumber.equals("0")) {
				log.info(String.format("add " + usertype + " " + confemembertype.name() + " succeed:%s", addnumber));
				log.info("add " + usertype + " " + confemembertype.name() + " name:" + user.getShowname());
				log.info("add " + usertype + " " + confemembertype.name() + " uid:" + user.getUid());
				log.info("add " + usertype + " " + confemembertype.name() + " oid:" + user.getOid());
				log.info("add " + usertype + " " + confemembertype.name() + " time:" + DateUtil.getNow());
				// 插入到会议成员表中
				ConfeMember con = BaseConfeMemberHP.getByUseruidConfuid(user.getUid(), conference.getUid());
				if (con != null) {
					if (!EStatus.enable.equals(con.getStatus()))
						BaseConfeMemberHP.updateStatus(con.getUid(), EStatus.enable);
				} else {
					ConfeMember confemember = new ConfeMember();
					confemember.setUseruid(user.getUid());
					confemember.setConfuid(conference.getUid());
					confemember.setConfsn(conference.getSn());
					confemember.setCid(conference.getCid());
					confemember.setPkuid(conference.getTypeuid());
					confemember.setPkname(conference.getTypename());
					confemember.setUsertype(confemembertype);
					confemember.setUserid(conference.getCid());
					confemember.setUsername(user.getShowname());
					confemember.setUseralias(user.getShowname());
					confemember.setEmail(user.getEmail());
					confemember.setPhone(user.getPhone());
					confemember.setType(conference.getType());
					BaseConfeMemberHP.add(confemember);
					BaseConferenceHP.incrExpectJoinCount(conference);//预期参加会议人数+1
				}
				return true;
			}
			log.info(String.format("add " + usertype + " " + confemembertype.name() + " fail:%s", addnumber));
		}
		log.info(String.format("根据会议室名称获取会议室", "获取失败"));
		return false;
	}

	
	/**
	 * 删除会议室成员(视高删除) 本地数据库中修改状态为deleted，没有删除 不涉及订单修改 只是删除成员
	 * 
	 * @param user
	 * @param conference
	 * @return
	 */
	public static boolean delConfUser(User user, Conference conference) {
		log.info(String.format("begin to invoke delConfUser(user:%s, conference:%s)", JsonUtil.toJson(user), JsonUtil.toJson(conference)));
		String deltoken = SeegleToken.getAdminToken();
		log.info(String.format("del member deltoken:%s", deltoken));
		if (SeegleConference.get(deltoken, conference.getCid()) != null) {
			String delnum = SeegleConfUser.deleteConfUser(deltoken, conference.getCid(), null, String.valueOf(user.getOid()));
			if (delnum.equals("0")) {
				log.info(String.format("del member succeed:%s", delnum));
				log.info("del member name:" + user.getShowname());
				log.info("del member uid:" + user.getUid());
				log.info("del member oid:" + user.getOid());
				log.info("del member time:" + DateUtil.getNow());
				ConfeMember confemember = BaseConfeMemberHP.getByUseruidConfuid(user.getUid(), conference.getUid());
				if (BaseConfeMemberHP.updateStatus(confemember.getUid(), EStatus.deleted)) {
					BaseConferenceHP.decrExpectJoinCount(conference);//预期参加会议人数-1
					return true;
				}
			}
			log.info(String.format("del member fail:%s", delnum));
		}
		return false;
	}

	/**
	 * 删除视高中游客，只删除视高中的
	 * 
	 * @param user
	 * @param conference
	 * @return
	 */
	public static boolean ConfUserStatus_del(User user, Conference conference) {
		log.info(String.format("begin to invoke ConfUserStatus_del(user:%s, conference:%s)", JsonUtil.toJson(user), JsonUtil.toJson(conference)));
		String deltoken = SeegleToken.getAdminToken();
		log.info(String.format("del member deltoken:%s", deltoken));
		if (SeegleConference.get(deltoken, conference.getCid()) != null) {
			String delnum = SeegleConfUser.deleteConfUser(deltoken, conference.getCid(), null, String.valueOf(user.getOid()));
			if (delnum.equals("0")) {
				log.info(String.format("del member succeed:%s", delnum));
				log.info("del member name:" + user.getShowname());
				log.info("del member uid:" + user.getUid());
				log.info("del member oid:" + user.getOid());
				log.info("del member time:" + DateUtil.getNow());
				return true;
			}
			log.info(String.format("del member fail:%s", delnum));
		}
		return false;
	}

	/**
	 * 添加会议室成员（在本地confemember表中已存在的数据）
	 * 
	 * @param user
	 * @param conference
	 * @return
	 */
	public static boolean ConfUserStatus_add(User user, Conference conference) {
		log.info(String.format("begin to invoke ConfUserStatus_add(user:%s, conference:%s)", JsonUtil.toJson(user), JsonUtil.toJson(conference)));
		String addtoken = SeegleToken.getAdminToken();
		log.info(String.format("add member token:%s", addtoken));
		if (SeegleConference.get(addtoken, conference.getCid()) != null) {
			String addnum = SeegleConfUser.addConfUser(addtoken, conference.getCid(), null, String.valueOf(user.getOid()));
			if (addnum.equals("0")) {
				log.info(String.format("add member succeed:%s", addnum));
				log.info("add member showname:" + user.getShowname());
				log.info("add member uid:" + user.getUid());
				log.info("add member oid:" + user.getOid());
				log.info("add member time:" + DateUtil.getNow());
				return true;
			}
			log.info(String.format("add member fail:%s", addnum));
		}
		return false;
	}

	/**
	 * 创建会议室 答辩过程中用到的seegle
	 * 
	 * @param application
	 * @param conf
	 * @param admin
	 * @return
	 */
	@SuppressWarnings("null")
	public static boolean creatOpenRoom(AppReply appReply, ConfEntity conf) {
		log.info(String.format("begin to invoke CreateReplySeegle (appReply:%s, conf:%s)", JsonUtil.toJson(appReply), JsonUtil.toJson(conf)));
		User user = BaseUserHP.get(appReply.getUseruid());
		String addtoken = SeegleToken.getAdminToken();
		AuthEntity auth = new AuthEntity(addtoken);
		String cid = SeegleConference.add(auth, conf);
		if (cid != null) {
			log.info(String.format("Create Seegle succeed and the conference cid:%s", cid));
			String[] expertoid = null;
			List<User> list = BaseReplyHP.getReplyExperts(appReply.getUid());
			for (int i = 0; i < list.size(); i++) {
				expertoid[i] = String.valueOf(list.get(i).getOid());
			}
			String adminList = ListUtil.toString(",", expertoid);
			String commonList = String.valueOf(user.getOid());
			String addnum = SeegleConfUser.addConfUser(addtoken, cid, adminList, commonList);
			if (addnum.equals("0")) {
				log.info(String.format("add user succeed，addnum:%s", addnum));
				Conference conference = new Conference();
				conference.setSn(AutoIncrHP.getConferencesn());
				conference.setTypename(appReply.getName());
				conference.setCid(cid);
				conference.setName(conf.getConfname());
				conference.setType(EConfer.reply);
				conference.setPassword(conf.getConf_password_md5());
				conference.setPasswordadmin(conf.getManage_password_md5());
				conference.setGrouptype(conf.getGrouptype());
				conference.setMaxcount(Integer.parseInt(conf.getMax_conf_user()));
				conference.setMaxcountuser(Integer.parseInt(conf.getMax_conf_user()));
				conference.setMaxcounttourist(Integer.parseInt(conf.getMax_conf_tourist()));
				conference.setMaxcountspokesman(Integer.parseInt(conf.getMax_conf_spokesman()));
				conference.setIsopen(true);
				conference.setIslock(false);
				conference.setIsautoclear(true);
				conference.setIsallvisible(true);
				conference.setIstop(false);
				conference.setDescription(conf.getDescription());
				conference.setStatus(EStatus.init); // 未开始
				conference.setExpectJoinCount(4); // 设置参加人数
				BaseConferenceHP.add(conference);
				for (int i = 0; i < list.size(); i++) {
					ConfeMember confemember = new ConfeMember();
					confemember.setUseruid(list.get(i).getUid());
					confemember.setConfuid(conference.getUid());
					confemember.setConfsn(conference.getSn());
					confemember.setCid(conference.getCid());
					confemember.setToken(addtoken);// ---------------
					confemember.setPkuid(appReply.getUid());
					confemember.setPkname(appReply.getName());
					confemember.setUsertype(EConfemember.appReply);
					confemember.setUserid(list.get(i).getUid());
					confemember.setUsername(list.get(i).getShowname());
					confemember.setUseralias(list.get(i).getShowname());
					confemember.setEmail(list.get(i).getEmail());
					confemember.setPhone(list.get(i).getPhone());
					confemember.setType(EConfer.reply);
					confemember.setUsertype(EConfemember.appReply);
					BaseConfeMemberHP.add(confemember);
				}
				ConfeMember confemember = new ConfeMember();
				confemember.setUseruid(user.getUid());
				confemember.setConfuid(conference.getUid());
				confemember.setConfsn(conference.getSn());
				confemember.setCid(conference.getCid());
				confemember.setToken(addtoken);// ---------------
				confemember.setPkuid(appReply.getUid());
				confemember.setPkname(appReply.getName());
				confemember.setUsertype(EConfemember.appReply);
				confemember.setUserid(user.getUid());
				confemember.setUsername(user.getShowname());
				confemember.setUseralias(user.getShowname());
				confemember.setEmail(user.getEmail());
				confemember.setPhone(user.getPhone());
				confemember.setType(EConfer.reply);
				confemember.setUsertype(EConfemember.student);
				BaseConfeMemberHP.add(confemember);
				return true;
			} else {
				log.info(String.format("add  user fail， addnum:%s", addnum));
				return false;
			}
		}
		log.info(String.format("Create Seegle fail， appreply uid:%s, appreply name:%s", appReply.getUid(), appReply.getName()));
		return false;
	}	
}
