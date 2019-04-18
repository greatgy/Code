package com.supergenius.web.admin.manager.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.AutoIncrHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.manager.entity.Dynamic;
import com.supergenius.xo.manager.entity.Judge;
import com.supergenius.xo.manager.entity.PKJudgement;
import com.supergenius.xo.manager.entity.PKSchedule;
import com.supergenius.xo.manager.entity.UserLevel;
import com.supergenius.xo.manager.enums.EDynamic;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.service.AppReplySO;
import com.supergenius.xo.manager.service.ConferenceSO;
import com.supergenius.xo.manager.service.DynamicSO;
import com.supergenius.xo.manager.service.JudgeSO;
import com.supergenius.xo.manager.service.PkJudgementSO;
import com.supergenius.xo.manager.service.PkScheduleSO;
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
 * 挑战管理HP
 * 
 * @author liubin
 * @date 2016-10-20 下午2:10:45
 */
public class ChallengeHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(ChallengeHP.class);
	private static PkScheduleSO so;
	private static UserSO userSO;
	private static JudgeSO judgeSO;
	private static ConferenceSO conferenceSO;
	private static AppReplySO appReplySO;
	private static PkJudgementSO pkJudgementSO;
	private static OrderGoodsSO orderGoodsSO;
	private static UserLevelSO userLevelSO;
	private static DynamicSO dynamicSO;

	private static PkScheduleSO getSO() {
		if (so == null) {
			so = spring.getBean(PkScheduleSO.class);
		}
		return so;
	}

	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static JudgeSO getJudgeSO() {
		if (judgeSO == null) {
			judgeSO = spring.getBean(JudgeSO.class);
		}
		return judgeSO;
	}
	
	private static ConferenceSO getConferenceSO() {
		if (conferenceSO == null) {
			conferenceSO = spring.getBean(ConferenceSO.class);
		}
		return conferenceSO;
	}
	
	private static AppReplySO getAppReplySO() {
		if (appReplySO == null) {
			appReplySO = spring.getBean(AppReplySO.class);
		}
		return appReplySO;
	}
	
	private static PkJudgementSO getPkJudgementSO() {
		if (pkJudgementSO == null) {
			pkJudgementSO = spring.getBean(PkJudgementSO.class);
		}
		return pkJudgementSO;
	}
	
	private static OrderGoodsSO getOrderGoodsSO() {
		if (orderGoodsSO == null) {
			orderGoodsSO = spring.getBean(OrderGoodsSO.class);
		}
		return orderGoodsSO;
	}
	
	private static UserLevelSO getUserLevelSO() {
		if (userLevelSO == null) {
			userLevelSO = spring.getBean(UserLevelSO.class);
		}
		return userLevelSO;
	}
	
	private static DynamicSO getDynamicSO() {
		if (dynamicSO == null) {
			dynamicSO = spring.getBean(DynamicSO.class);
		}
		return dynamicSO;
	}

	/**
	 * 组织查询语句查询数据
	 * 
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-10-20下午7:21:25
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(MapperDict.search))) {
			map.put(MapperDict.search, model.get(MapperDict.search).toString());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.major))) {
			map.put(MapperDict.major, model.get(MapperDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.level))) {
			map.put(MapperDict.level, model.get(MapperDict.level).toString());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.stage))) {
			if (model.get(MapperDict.stage).toString().equals("1")) {
				map.put(MapperDict.stage + MapperDict.suffix_in_key, EPKStage.getAppings());
			}
			if (model.get(MapperDict.stage).toString().equals("2")) {
				List<EPKStage> list = new ArrayList<EPKStage>();
				list.add(EPKStage.challenging);
				map.put(MapperDict.stage + MapperDict.suffix_in_key, list);
			}
			if (model.get(MapperDict.stage).toString().equals("3")) {
				map.put(MapperDict.stage + MapperDict.suffix_in_key, EPKStage.getPKOver());
			}
			if (model.get(MapperDict.stage).toString().equals("4")) {
				map.put(MapperDict.stage + MapperDict.suffix_in_key, EPKStage.getCancels());
			}
			if (model.get(MapperDict.stage).toString().equals("5")) {
				map.put(MapperDict.stage + MapperDict.suffix_in_key, EPKStage.waitingForChallenge);
			}
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.status))) {
			map.put(MapperDict.status, model.get(MapperDict.status).toString());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCountBySearch(map));
		List<PKSchedule> list = getSO().getListBySearch(map);
		if (list.size() > 0 && list != null) {
			for (PKSchedule pkSchedule : list) {
				if (StrUtil.isNotEmpty(pkSchedule.getPkuseruid())) {
					pkSchedule.setPkusername(getUserSO().get(pkSchedule.getPkuseruid()).getName());
				}
				if (StrUtil.isNotEmpty(pkSchedule.getPkuseruid2())) {
					pkSchedule.setPkusername2(getUserSO().get(pkSchedule.getPkuseruid2()).getName());
				}
				if (StrUtil.isNotEmpty(pkSchedule.getJudgementuid())) {
					pkSchedule.setJudgementname(getUserSO().get(pkSchedule.getJudgementuid()).getName());
				}
				if (StrUtil.isNotEmpty(pkSchedule.getJudgementuid2())) {
					pkSchedule.setJudgementname2(getUserSO().get(pkSchedule.getJudgementuid2()).getName());
				}
				if (StrUtil.isNotEmpty(pkSchedule.getJudgementchairuid())) {
					pkSchedule.setJudgementchairname(getUserSO().get(pkSchedule.getJudgementchairuid()).getName());
				}
				if (StrUtil.isNotEmpty(pkSchedule.getConfeuid())) {
					pkSchedule.setConferencename(getConferenceSO().get(pkSchedule.getConfeuid()).getName());
				}
				if (pkSchedule.isApping()) {
					pkSchedule.setPkstagename(SysConf.AppChallengingName);
				} else if (pkSchedule.getStage() == EPKStage.challenging) {
					pkSchedule.setPkstagename(SysConf.ChallengingName);
				} else if (pkSchedule.isPkOver()) {
					pkSchedule.setPkstagename(SysConf.ChallengeOverName);
				} else if (pkSchedule.getStage() == EPKStage.challengSeegleOver) {
					pkSchedule.setPkstagename(SysConf.ChallengMettingOver);
				} else if (pkSchedule.getStage() == EPKStage.waitingForChallenge) {
					pkSchedule.setPkstagename(SysConf.WaitingForChalleng);
				} else {
					pkSchedule.setPkstagename(SysConf.ChallengeCancelName);
				}
				if (pkSchedule.isPkOver() && StrUtil.isNotEmpty(pkSchedule.getUserscore()) && StrUtil.isNotEmpty(pkSchedule.getUserscore2())) {
					int pkResult = getResult(pkSchedule.getUserscore(), pkSchedule.getUserscore2());
					if (pkResult == -1) {
						pkSchedule.setPkresult(SysConf.PKUser2Win);
					} else if (pkResult == 1) {
						pkSchedule.setPkresult(SysConf.PKUserWin);
					} else {
						pkSchedule.setPkresult(SysConf.PKDeuce);
					}
				} else {
					pkSchedule.setPkresult(SysConf.PKNoResult);
				}
				if (StrUtil.isNotEmpty(pkSchedule.getUid())) {
					List<PKJudgement> pkJudgements = getPkJudgementSO().getListByPkuid(pkSchedule.getUid());
					if (pkJudgements != null && pkJudgements.size() > 0) {
						Iterator<PKJudgement> it = pkJudgements.iterator();
						while(it.hasNext()){
						    PKJudgement pkJudgement = it.next();
//						    if (EStatus.enable == pkJudgement.getStatus()) {
//								it.remove();
//							} else {
								if (StrUtil.isNotEmpty(pkJudgement.getJudgementuid())) {
									User user = getUserSO().get(pkJudgement.getJudgementuid());
									if (user != null) {
										pkJudgement.setUsername(user.getName());
									}
								}  
//							}
						}
						pkSchedule.setPkjudgements(pkJudgements);
					}
				}
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 根据分数判断挑战结果,0分代表缺席{-1 ：挑战者失败， 1：挑战者胜利，0：平局}
	 * @param score1
	 * @param score2
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午5:01:26
	 * @return int
	 */
	public static int getResult(Double score1, Double score2) {
		if (score1 != 0 && score2 != 0) {
		    if (score2 > score1 && (score2 - score1) >= score1 * SysConf.PercentJudgePkResult) {// 挑战失败
			    return -1;
		    } else if (score2 < score1 && (score1 - score2) >= score1 * SysConf.PercentJudgePkResult) {// 挑战成功
			    return 1;
		    } else {
		    	return 0;
		    }
		} else if (score1 == 0 && score2 != 0) {
			return -1;
		} else if (score1 != 0 && score2 ==0) {
			return 1;
		}
		return -1;
	}
	
	/**
	 * 挑战结束给裁判奖励
	 * @param pkSchedule
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午1:53:21
	 * @return boolean
	 */
	public static boolean rewardJudgement(PKSchedule pkSchedule, String judgementuid) {
		double money = 0;
		if (pkSchedule.getLevel() == EStudentLevel.manager) {
			money = SysConf.PKJudgementRewardBasic;
		} else {
			money =SysConf.PKJudgementRewardManager;
		}
		if (getPkJudgementSO().getCount(judgementuid, pkSchedule.getLevel(), false) >=  getPkJudgementSO().getCount(judgementuid, pkSchedule.getLevel(), true)) {
			User user = getUserSO().get(judgementuid);
			if (user != null) {
				user.plusAccount(money);
				user.plusTotalincome(money);
			}
			Judge judge = getJudgeSO().getOne(judgementuid, pkSchedule.getMajor());
			if (judge != null) {
				judge.setJudgecount(judge.getJudgecount() + 1);
			}
			TradeDetail tradeDetail = new TradeDetail(judgementuid, pkSchedule.getUid(), money, user.getAccount(), AutoIncrHP.getTradeDetailsn(), null, ETrade.judgement, ETrade.judgement.getName(), ESite.manager);
			if (judgementuid.equals(pkSchedule.getJudgementuid())) {
				pkSchedule.setJudgmentfee(money);
			} else if (judgementuid.equals(pkSchedule.getJudgementuid2())) {
				pkSchedule.setJudgmentfee2(money);
			} else if (judgementuid.equals(pkSchedule.getJudgementchairuid())) {
				pkSchedule.setJudgementchairfee(money);
			}
			PKJudgement pkJudgement = getPkJudgementSO().getOne(judgementuid, pkSchedule.getUid());
			if (pkJudgement != null) {
				pkJudgement.setIsabsent(false);
			}
			getSO().update(pkSchedule, user, judge, tradeDetail, pkJudgement);
			if (MsgHP.sendPKJudgementGetRewardMsg(AdminHP.getAdminUid(), judgementuid, pkSchedule.getName(), pkSchedule.getUid()) && EmailHP.sendPKGetResult(user.getShowname(), pkSchedule.getName(), user.getEmail(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), EContent.email_rewardjudgement)) {
				log.error("the email is ---------------------------------------------------------------------" + user.getName() + ":" + user.getEmail());
				return true;
			} else {
				log.error("in " + pkSchedule.getName() + "send to judgement" + judgementuid +"get reward msg or Email is false! errmethod ChallengeHP.java line:289!");
			}
	    }
		return false;
	}
	
	/**
	 * 判断该学员是否符合降级条件,符合的话对其进行降级处理
	 * @param uid
	 * @param major
	 * @return
	 * @author liubin
	 * @createtime 2016-11-4下午8:06:04
	 * @return boolean
	 */
	public static boolean isDownLevel(String uid, PKSchedule pkSchedule, EStudentLevel level) {
		if (getFailNumber(uid, pkSchedule, level) >= SysConf.PKDownLevelNumber - 1) {
			if (level == EStudentLevel.majordomo && getAppReplySO().getOneByMajor(uid, pkSchedule.getMajor()) == null && level != EStudentLevel.basic) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 得到某一学员在同一级别同一专业上失败的次数
	 * @param uid
	 * @param major
	 * @param level
	 * @return
	 * @author liubin
	 * @createtime 2016-11-5下午2:32:15
	 * @return int
	 */
	public static int getFailNumber(String uid, PKSchedule pkSchedule, EStudentLevel level) {
		int count = 0;
		List<PKSchedule> list = null;
		if (uid.equals(pkSchedule.getPkuseruid())) {
			list = getSO().getList(uid, pkSchedule.getMajor(), level, EPKStage.getPKOver());
		} else {
			list = getSO().getListByPKUser2(uid, pkSchedule.getMajor(), level, EPKStage.getPKOver());
		}
		if (list != null && list.size() > 0) {
			for (PKSchedule pkSchedule2 : list) {
				if (pkSchedule2.getStage() == EPKStage.bothAbsent || pkSchedule2.getStage() == EPKStage.pkuserAbsent) {
					count ++;
				} else if (pkSchedule2.getStage() == EPKStage.challengeSuccess && getResult(pkSchedule2.getUserscore(), pkSchedule2.getUserscore2()) == -1) {
					count ++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 对学员进行降级处理
	 * @param pkSchedule
	 * @param studentLevel
	 * @author liubin
	 * @createtime 2016年12月7日下午7:29:18
	 * @return void
	 */
	public static void downLevel(String uid, PKSchedule pkSchedule, EStudentLevel studentLevel) {
		Dynamic dynamic = new Dynamic();
		dynamic.setUseruid(uid);
		dynamic.setType(EDynamic.pkFail5);
		dynamic.setContent(pkSchedule.getName());
		dynamic.setDataMap_href(WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()));
		UserLevel userLevel = getUserLevelSO().getOne(uid, pkSchedule.getMajor(), studentLevel.toInt());
		if (userLevel != null) {
			userLevel.setStatus(EStatus.disable);
		}
		UserLevel userLevel2 = null;
		if (pkSchedule.getLevel() == EStudentLevel.manager) {
			userLevel2 = new UserLevel(uid, pkSchedule.getMajor(), EStudentLevel.manager.toInt(), EStudentLevel.basic.toInt(), EUser.student,
					ELevelChannel.totallFailLevel);
			dynamic.setTitle(String.format(EDynamic.pkFail5.getName(), getUserSO().get(pkSchedule.getPkuseruid()).getShowname(), EStudentLevel.manager.getName()));
		} else if (pkSchedule.getLevel() == EStudentLevel.majordomo) {
			userLevel2 = new UserLevel(uid, pkSchedule.getMajor(), EStudentLevel.majordomo.toInt(), EStudentLevel.manager.toInt(), EUser.student,
					ELevelChannel.totallFailLevel);
			dynamic.setTitle(String.format(EDynamic.pkFail5.getName(), getUserSO().get(uid).getShowname(), EStudentLevel.majordomo.getName()));
		}
		getUserLevelSO().update(userLevel, userLevel2);
		getDynamicSO().add(dynamic);
		if (studentLevel == EStudentLevel.manager) {
			if (!MsgHP.sendPKUserGetPunishMsg(AdminHP.getAdminUid(), uid, pkSchedule.getName(), EStudentLevel.basic.getName(), pkSchedule.getUid())) {
				log.error("in " + pkSchedule.getName() + "send Msg to user" + pkSchedule.getUid() + " is false! errmethod in ChallengeAdminer.java line:604!");
			}
		} else {
			if (!MsgHP.sendPKUserGetPunishMsg(AdminHP.getAdminUid(), uid, pkSchedule.getName(), EStudentLevel.manager.getName(), pkSchedule.getUid())) {
				log.error("in " + pkSchedule.getName() + "send Msg to user" + pkSchedule.getUid() + " is false! errmethod in ChallengeAdminer.java line:608!");
			}
		}
	}
	
	/**
	 * 挑战者获胜，进行晋级
	 * @param pkSchedule
	 * @param studentLevel
	 * @author liubin
	 * @createtime 2016年12月7日下午7:54:20
	 * @return void
	 */
	public static void pkuserWinGetLeveUp(PKSchedule pkSchedule, EStudentLevel studentLevel) {
		Dynamic dynamic = new Dynamic();
		dynamic.setUseruid(pkSchedule.getPkuseruid());
		dynamic.setType(EDynamic.pkSuccess);
		dynamic.setContent(pkSchedule.getName());
		dynamic.setDataMap_href(WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()));
		UserLevel userLevel = getUserLevelSO().getOne(pkSchedule.getPkuseruid(), pkSchedule.getMajor(), studentLevel.toInt());
		if (userLevel != null) {
			userLevel.setStatus(EStatus.disable);
		}
		UserLevel userLevel2 = null;
		if (pkSchedule.getLevel() == EStudentLevel.manager) {
			userLevel2 = new UserLevel(pkSchedule.getPkuseruid(), pkSchedule.getMajor(), EStudentLevel.basic.toInt(), EStudentLevel.manager.toInt(), EUser.student,
					ELevelChannel.challenge);
			dynamic.setTitle(String.format(EDynamic.pkSuccess.getName(), getUserSO().get(pkSchedule.getPkuseruid()).getShowname(), EStudentLevel.manager.getName()));
		} else if (pkSchedule.getLevel() == EStudentLevel.majordomo) {
			userLevel2 = new UserLevel(pkSchedule.getPkuseruid(), pkSchedule.getMajor(), EStudentLevel.manager.toInt(), EStudentLevel.majordomo.toInt(), EUser.student,
					ELevelChannel.challenge);
			dynamic.setTitle(String.format(EDynamic.pkSuccess.getName(), getUserSO().get(pkSchedule.getPkuseruid()).getShowname(), EStudentLevel.majordomo.getName()));
		}
		getUserLevelSO().update(userLevel, userLevel2);
		getDynamicSO().add(dynamic);
	}
	
	/**
	 * 给所有参加挑战的人发送消息
	 * @param pkSchedule
	 * @return
	 * @author liubin
	 * @createtime 2016年11月29日下午5:05:47
	 * @return boolean
	 */
	public static boolean sendPKAllMemberMsg(PKSchedule pkSchedule) {
		List<User> users = new ArrayList<>();
		if (pkSchedule != null) {
			User pkUser = getUserSO().get(pkSchedule.getPkuseruid());
			User pkUser2 = getUserSO().get(pkSchedule.getPkuseruid2());
			User judgement = getUserSO().get(pkSchedule.getJudgementuid());
			User judgement2 = getUserSO().get(pkSchedule.getJudgementuid2());
			User judgement3 = getUserSO().get(pkSchedule.getJudgementchairuid());
			if (pkUser != null) {
				users.add(pkUser);
			}
			if (pkUser2 != null) {
				users.add(pkUser2);
			}
			if (judgement != null) {
				users.add(judgement);
			}
			if (judgement2 != null) {
				users.add(judgement2);
			}
			if (judgement3 != null) {
				users.add(judgement3);
			}
			List<OrderGoods> orderGoods = getOrderGoodsSO().getListByRefuid(pkSchedule.getUid(), EGoods.ticket);
			if (orderGoods != null && orderGoods.size() > 0) {
				for (OrderGoods orderGoods2 : orderGoods) {
					User user = getUserSO().get(orderGoods2.getUseruid());
					if (user != null) {
						users.add(user);
					}
				}
			}
		   boolean flag = true;
		   if (users != null && users.size() > 0) {
			   for (User user : users) {
				   if (!MsgHP.sendPKAllMemberMsg(AdminHP.getAdminUid(), user.getUid(), pkSchedule.getName(), pkSchedule.getUid()) || !EmailHP.sendPKGetResult(user.getShowname(), pkSchedule.getName(), user.getEmail(), WebConf.baseManagerPath + String.format(SysConf.PKDetailUrl, pkSchedule.getUid()), EContent.email_pkgetresult)) {
					   flag = false;
					   log.error("send msg to " + user.getName() + "is false in " + pkSchedule.getName() + "method in ChallengeHP.java line:354!");
				   }
			   }
		   }
		   if (flag) {
			   return true;
		   }
		}
		return false;
	}
	
	/**
	 * 查询指定裁判
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-11-2下午4:39:12
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> queryjudge(Map<String, Object> model){
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.sn))) {
			map.put(MapperDict.sn + MapperDict.suffix_like_key, model.get(ViewKeyDict.sn).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, model.get(ViewKeyDict.type).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.major))) {
			map.put(MapperDict.major, model.get(ViewKeyDict.major).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.showname + MapperDict.suffix_like_key, model.get(ViewKeyDict.name).toString());
		}
		result.put(ViewKeyDict.total, getJudgeSO().getQueryCount(map));
		List<Judge> list = getJudgeSO().getQueryList(map);
		for (Judge judge : list) {
			User user = getUserSO().get(judge.getUseruid());
			if(user != null) {
				judge.setUser(user);
				judge.setUsername(user.getShowname());
			}
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
