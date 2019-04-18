package com.supergenius.xo.user.enums;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/** 
 * 内容枚举类
 * 增加邮件模板的话必须以email_开头
 * @author guanshiqian
 * @date 2016-4-15 下午4:18:01 
 */
public enum EContent {
	
	email_validate(0),//邮箱验证
	email_registsuccess(1),//注册成功
	email_rebinding(2),//重新绑定
	email_pwdreset(3),//找回密码
	userrule(4),//用户协议
	legal(5),//法律声明
	managerule(6),//网络平台管理规定
	email_remindpay(7),//提醒交会费(注册7天后没交会费)
	email_remindpay1(8),//提醒交会费(注册14天后没交会费)
	email_disable(9),//账号被冻结邮件
	email_toenable(10),//账号解冻
	email_special(11),//特批会员
	email_invite(12),//特邀会员
	email_tepiaccount(13),//特批余额
	email_paysuccess(14),//充值成功
	email_findpaypwd(15),//找回支付密码
	
	//manager
	email_lacnuhpk(40),//发起挑战
	email_refusepk(41),//被挑战者拒绝挑战
	email_choosetime(42),//被挑战者重选时间
	email_tennothandle1(43),//被挑战10日内未处理
	email_tennothandle2(44),//被挑战10日内未处理（发给自己）
	email_refusebacktime(45),//挑战者拒绝被挑战者的10个时间
	email_fivenothandle1(46),//挑战5日内未处理（发给自己）
	email_fivenothandle2(47),//挑战5日内未处理
	email_judgeinvite(48),//裁判邀请
	email_createpk1(49),//主裁确定，生成挑战 》挑战者
	email_createpk2(50),//主裁确定，生成挑战 》裁判
	email_buypk(51),//购买门票
	email_willstarttouser(52),//挑战进入24小时倒计时，发送邮件给挑战双方
	email_willstarttojudge(53),//挑战进入24小时倒计时，发送邮件给裁判
	email_willstarttoaudience(54),//挑战进入24小时倒计时，发送邮件给观众
	email_willexpirevideo(55),//视频还有三天过期，给购买者发送邮件
	email_awardcertificatesuccess(56),//证书颁发成功，给获得证书的发送邮件
	email_checkopenreplyfilesuccess(57),//审核开题材料成功，给申请人发信息
	email_checkreplyfilesuccess(58),//审核答辩材料成功，给申请人发信息
	email_specifyreplyexpert(59),//被指定为答辩专家，给专家发送邮件
	email_changespecifyreplyexpert(60),//取消本次答辩专家资格
	email_canappreply(61),//满足申请答辩条件通知
	email_inviteconfvisitor(62),//邀请会议室观众
	email_inviteconfadmin(63),//邀请会议室管理员
	email_pkqqgroupoktouser(64),//挑战QQ群确定(挑战双方、裁判)
	email_pkqqgroupoktowatcher(65),//挑战QQ群确定(购票/被邀请)
	email_openreplyqqgroupok(66),//答辩开题论证会QQ群确定
	email_pkgetresult(67),//挑战结果已定
	email_rewardjudgement(68),//发放裁判费用
	email_rewardpkwinuser(69),//发放挑战获胜方奖励
	//enterpriser
	email_sendgift(80),// 发送赠品邮件通知
	email_participatesuccess(81),// 报名成功邮件通知
	email_lecturestart(82),// 讲座确定邮件通知
	email_lecturetimechange(83),// 讲座信息修改邮件通知
	email_participatesuccessnouser(84);// 报名成功不赠送会员邮件通知
	
	private final int value;

	private EContent(int v) {
		this.value = v;
	}

	public int getValue() {
		return value;
	}

	public static EContent get(int v) {
		for (EContent e : values()) {
			if (e.getValue() == v) {
				return e;
			}
		}
		return null;
	}
	
	public static EContent get(long v) {
		for (EContent e : values()) {
			if (e.getValue() == v) {
				return e;
			}
		}
		return null;
	}
	
	@Override
 	public String toString() {
 		return String.valueOf(value);
 	}
	
	public String getName(){
	    return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获取不是以emai开头的枚举的值用逗号拼接成字符串
	 * @return
	 * @author XieMing
	 * 2016-5-21 下午1:28:30
	 */
	public static String getNotEmail() {
		String str = "";
		for (EContent content : EContent.values()) {
			if(!content.name().startsWith("email_")) {
				str += content.value + ",";
			}
		}
		str = str.substring(0,str.length() - 1);
		return str;
	}
	
	/**
	 * 获取所有以email_开头的枚举的map，key为值的字符串形式，value为对应的中文名
	 * @return
	 * @author chenminchang
	 */
	public static Map<String, String> getEmailEnum() {
		Map<String, String> map = new HashMap<>();
		for (EContent content : EContent.values()) {
			if(content.name().startsWith("email_")) {
				map.put(content.toString(), content.getName());
			} 
		}
		return map;
	}
}
