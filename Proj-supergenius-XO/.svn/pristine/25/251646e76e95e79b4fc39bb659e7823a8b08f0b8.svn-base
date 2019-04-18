package com.supergenius.xo.ai.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 评论枚举类
 * 
 * @author ChenQi
 * @date 2017年8月23日10:51:26
 */
public enum EAiMsg {
	
	sys("0"),//系统消息
	praisearticle("1"),//对文章的点赞
	commentsarticle("2"),//对文章的评论
	praisecomments("3"),//对评论的点赞
	replycomments("4"),//对评论的回复
	collectarticle("5");//对文章的收藏
	
	private final String value;

	private EAiMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EAiMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAiMsg get(String str) {
		for (EAiMsg e : values()) {
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
