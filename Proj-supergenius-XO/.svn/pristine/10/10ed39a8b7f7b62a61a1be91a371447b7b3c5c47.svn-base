package com.supergenius.xo.common.entity;

import com.genius.core.base.annotation.Json;

/**
 * 在此项目中需要用到点击数等，故新增加父类
 * 
 * @author ShangJianguo
 */
public abstract class BaseEntity extends com.genius.model.base.entity.BaseEntity {

	private static final long serialVersionUID = -1923460516866881241L;

	protected long clickcount = 0;// 点击数
	protected long commentcount = 0;// 评论数
	protected long admirecount = 0;// 赞数
	protected long sharecount = 0;// 分享数
	protected boolean isprize = false;// 是否点赞
	protected boolean iscollect = false;// 是否收藏
	@Json(strategy = {Json.appStrategy})
	private String beforetimeStr;//移动端显示的发布时间
	
	public String getBeforetimeStr() {
		return beforetimeStr;
	}
	public void setBeforetimeStr(String beforetimeStr) {
		this.beforetimeStr = beforetimeStr;
	}
	

}
