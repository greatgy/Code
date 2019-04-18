package com.supergenius.xo.life.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息枚举类
 * 
 * @author YangGuang
 * @date 2018年5月9日15:57:27
 */
public enum ELifeMsg {
	
	sys("0"),//系统消息
	praisearticle("1"),//对文章的点赞
	commentsarticle("2"),//对文章的评论
	praisecomments("3"),//对评论的点赞
	replycomments("4"),//对评论的回复
	praisetopic("5"),//对话题的点赞
	commentstopic("6"),//对话题的评论
	evaluatevideo("7"),//点评视频
	evaluateproblem("8"),//点评问题
	report("9");//举报
	
	private final String value;

	private ELifeMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ELifeMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ELifeMsg get(String str) {
		for (ELifeMsg e : values()) {
			if (e.toString().equals(str) || e.name().equals(str)) {
				return e;
			}
		}
		return null;
	}
	
	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
