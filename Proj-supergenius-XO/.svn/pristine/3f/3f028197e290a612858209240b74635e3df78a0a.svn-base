package com.supergenius.xo.entrepreneur.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * @author tf
 * @date 2018年7月4日
 */
public enum EEntrepreneurMsg {
	
	sys("0"),//系统消息
	praisearticle("1"),//对文章的点赞
	commentsarticle("2"),//对文章的评论
	praisecomments("3"),//对评论的点赞
	replycomments("4");//对评论的回复
	
	private final String value;

	private EEntrepreneurMsg(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EEntrepreneurMsg get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EEntrepreneurMsg get(String str) {
		for (EEntrepreneurMsg e : values()) {
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
