package com.supergenius.xo.career.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 消息枚举类
 * 
 * @author ChenQi
 * @date 2017年11月13日16:49:35
 */
public enum ECareerMsg {
	
	sys("0"),//系统消息
	praisearticle("1"),//对文章的点赞
	commentsarticle("2"),//对文章的评论
	praisecomments("3"),//对评论的点赞
	replycomments("4"),//对评论的回复
	collectarticle("5");//对文章的收藏
	
	private final String value;

	private ECareerMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static ECareerMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static ECareerMsg get(String str) {
		for (ECareerMsg e : values()) {
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
