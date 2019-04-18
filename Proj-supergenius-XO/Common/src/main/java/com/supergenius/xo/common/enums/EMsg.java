package com.supergenius.xo.common.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息类型
 * @author YuYingJie
 */
public enum EMsg {

	sys("0"),//系统消息
	comment("1"),//评论消息
	basic("2"),//基本消息
	prize("3"),//帖子被赞
	save("4"),//帖子被收藏
	getcertificate("5"),//获得证书
	placetop("6"),//帖子置顶
	delarticle("7"),//帖子被删除
	delcomment("8"),//发言被删除
	deluserdoc("9"),//上传资料被删除
	userdocrmcase("10"),//上传资料移入案例库
	replycomment("11"),//回复评论
	enrollsuccess("12"),//报名成功
	passexam("13"),//通过考试
	nocertificate("14");//未获得证书
	
	private final String value;

	private EMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
	
	public int toInt() {
		return Integer.valueOf(this.value);
	}

	public static EMsg get(int v) {
		String str = String.valueOf(v);
		for (EMsg e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public static EMsg get(String name) {
		for (EMsg e : values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
	
	public static String getName(EMsg e) {
		return I18N.getEnumName(e, Locale.CHINA);
	}
	
	public static String getName(EMsg e, Locale locale) {
		return I18N.getEnumName(e, locale);
	}
}
