package com.supergenius.server.user.helper;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.AutoIncrRule;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.enums.ESerialID;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EGoods;

/**
 * @author architect.bian
 *
 */
public class AutoIncrHP extends BaseHP {

	private static Logger log = LoggerFactory.getLogger(AutoIncrHP.class);
	private static String [] number  = {"1","2","3","6","8","9"};
	
	private static long getID(ESerialID type) {
		Rule rule = new AutoIncrRule(type.name());
		log.info("getID rule:" + rule.toString());
		return RedisUtil.incr(rule);
	}

	/**
	 * 重置为0，下次自增时返回1
	 * @param type
	 * @return
	 */
	private static boolean resetID(ESerialID type) {
		Rule rule = new AutoIncrRule(type.name());
		return RedisUtil.setIncr(rule, 0);
	}
	
	/**
	 * 格式：1311251500270050004
	 * @return
	 */
	public synchronized static String getAccountsn() {
		String prefix = DateUtil.getNowForID();
		long id = getID(ESerialID.accountsn);
		if (id >= 9999) {
			resetID(ESerialID.accountsn);
		}
		String sn = prefix + StrUtil.format("0000", id);
		log.info("getAccountsn:" + sn);
		return sn;
	}
	
	public synchronized static String getTradeDetailsn() {
		String prefix = DateUtil.getNowForID();
		long id = getID(ESerialID.accountsn);
		if (id >= 9999) {
			resetID(ESerialID.accountsn);
		}
		String sn = prefix + StrUtil.format("0000", id);
		log.info("getAccountsn:" + sn);
		return sn;
	}
	
	public static String getAccountlogid() {
		String prefix = DateUtil.getNowForID();
		long id = getID(ESerialID.accountlog);
		if (id >= 9999) {
			resetID(ESerialID.accountlog);
		}
		String sn = prefix + StrUtil.format("0000", id);
		log.info("getAccountlog:" + sn);
		return sn;
	}
	
	public synchronized static String getOrdersn(EGoods type) {
		String prefix = DateUtil.getNowForID();
		long id = getID(ESerialID.ordersn);
		if (id >= 9999) {
			resetID(ESerialID.ordersn);
		}
		String sn = prefix + StrUtil.format("0000", id);
		log.info("getOrdersn:" + sn);
		return sn;
	}
	
	public synchronized static String getVideosn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.videosn);
		if (id >= 9999) {
			resetID(ESerialID.videosn);
		}
		String sn = prefix + StrUtil.format("0000", id);
		log.info("getVideosn:" + sn);
		return sn;
	}
	
	public synchronized static String getChallengesn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.challengesn);
		if (id >= 9999) {
			resetID(ESerialID.challengesn);
		}
		String sn = prefix + StrUtil.format("0000", id);
		log.info("getChallengesn:" + sn);
		return sn;
	}
	
	/**
	 * 普通会员号生成规则
	 * 三个字符HRH-普通会员
	 * @return
	 */
	public synchronized static String getHRHUsersn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.usersn);
		if (id >= 9999) {
			resetID(ESerialID.usersn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String usersn = "HRH" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getHRHUsersn:" + usersn);
		return usersn;
	}
	
	/**
	 * 特邀嘉宾的会员号
	 * @return
	 */
	public synchronized static String getVONUsersn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.usersn);
		if (id >= 9999) {
			resetID(ESerialID.usersn);
		}
		int index = (int)(Math.random() * number.length);
		String rand = number[index];
		String usersn = "VON" + prefix + StrUtil.format("0000", id) + rand;
		log.info("getVONUsersn:" + usersn);
		return usersn;
	}
	
	/**
	 * 工单号号生成规则
	 * 三个字符FLO-工单
	 * @return
	 */
	public synchronized static String getWorkordersn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.workordersn);
		if (id >= 9999) {
			resetID(ESerialID.workordersn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String workordersn = "WO" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getFlowordersn:" + workordersn);
		return workordersn;
	}
	
	/**
	 * 答辩号生成规则
	 * APPR
	 * @return
	 */
	public synchronized static String getAppreplysn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.appreplysn);
		if (id >= 9999) {
			resetID(ESerialID.appreplysn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String appreplysn = "APPR" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getAppreplysn:" + appreplysn);
		return appreplysn;
	}
	
	/**
	 * 申请裁判号生成规则
	 * APPR
	 * @return
	 */
	public synchronized static String getAppjudgementsn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.appjudgement);
		if (id >= 9999) {
			resetID(ESerialID.appjudgement);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String appjudgementsn = "APPJ" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getAppjudgementsn:" + appjudgementsn);
		return appjudgementsn;
	}
	
	/**
	 * 会议室编号生成规则
	 * LEV
	 * @return
	 */
	public synchronized static String getConferencesn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.conferencesn);
		if (id >= 9999) {
			resetID(ESerialID.conferencesn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String conferencesn = "CONF" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getConferencesn:" + conferencesn);
		return conferencesn;
	}
	
	public synchronized static String getAskleavelsn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.askleavesn);
		if (id >= 9999) {
			resetID(ESerialID.askleavesn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String askleavesn = "LEV" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getAskleavesn:" + askleavesn);
		return askleavesn;
	}
	
	/**
	 * 生成学员sn
	 * @return
	 * @author liubin
	 * @createtime 2016-9-12下午7:25:58
	 * @return String
	 */
	public synchronized static String getStudentsn(User user) {
		return user.getUsersn() + ViewKeyDict.LM;
	}
	
	/**
	 * 普通裁判SN
	 * @param user
	 * @return
	 * @author liubin
	 * @createtime 2016-10-11下午4:44:06
	 * @return String
	 */
	public synchronized static String getJudgeSn(User user) {
		return ViewKeyDict.TC + user.getUsersn() + ViewKeyDict.LM;
	}
	
	/**
	 * 特邀裁判SN
	 * @param user
	 * @return
	 * @author liubin
	 * @createtime 2016-10-11下午4:43:53
	 * @return String
	 */
	public synchronized static String getTCJudgeSn(User user) {
		return ViewKeyDict.TC + user.getUsersn();
	}
	
	/**
	 * 普通专家SN
	 * @param user
	 * @return
	 * @author liubin
	 * @createtime 2016-10-11下午4:45:56
	 * @return String
	 */
	public synchronized static String getExpert(User user) {
		return ViewKeyDict.ZT + user.getUsersn() + ViewKeyDict.LM;
	}
	
	/**
	 * 特邀专家SN
	 * @param user
	 * @return
	 * @author liubin
	 * @createtime 2016-10-11下午4:46:15
	 * @return String
	 */
	public synchronized static String getZTExpert(User user) {
		return ViewKeyDict.ZT + user.getUsersn();
	}
	
	/**
	 * 答辩号生成规则
	 * APPR
	 * @return
	 */
	public synchronized static String getLecturesn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.lecturesn);
		if (id >= 9999) {
			resetID(ESerialID.lecturesn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String lecturesn = "LEC" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getAppreplysn:" + lecturesn);
		return lecturesn;
	}
	
	/**
	 * 证书编号生成规则
	 * APPR
	 * @return
	 */
	public synchronized static String getCertificatesn() {
		String prefix = DateUtil.NowTime().toString(DateUtil.FORMAT_DATE_SHORT);
		long id = getID(ESerialID.certificatesn);
		if (id >= 9999) {
			resetID(ESerialID.certificatesn);
		}
		int index = (int)(Math.random()*number.length);
		String rand = number[index];
		String certificatesn = "CER" +prefix + StrUtil.format("0000", id) + rand;
		log.info("getCertificatesn:" + certificatesn);
		return certificatesn;
	}
	
	/**
	 * 报名企业家培训讲座赠送会员的初始密码
	 * @return
	 * @author XieMing
	 * 2016-10-26 下午6:00:44
	 */
	public synchronized static String getUserInitPwd() {
		Random random = new Random();
		String result="";
		for(int i=0;i<6;i++){
			result += random.nextInt(10);
		}
		return result;
	}
	
}
