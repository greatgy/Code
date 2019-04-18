package com.supergenius.xo.enterpriser.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息枚举类
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:36:23
 */
public enum EEnterpriserMsg {
	
	sys("0"),//系统消息
	praisearticle("1"),//对文章的点赞
	commentsarticle("2"),//对文章的评论
	praisecomments("3"),//对评论的点赞
	replycomments("4"),//对评论的回复
	collectarticle("5"),//对文章的收藏
	praiseforum("6"),//对帖子的点赞
	commentsforum("7"),//对帖子的评论
	collectforum("8");//对帖子的收藏
	
	private final String value;

	private EEnterpriserMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EEnterpriserMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EEnterpriserMsg get(String str) {
		for (EEnterpriserMsg e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
