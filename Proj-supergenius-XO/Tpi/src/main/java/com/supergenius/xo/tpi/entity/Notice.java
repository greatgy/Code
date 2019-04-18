package com.supergenius.xo.tpi.entity;


import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.ENoticeChannel;
import com.supergenius.xo.tpi.enums.ENoticeType;

/**
 * 通知、通告、招聘等
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class Notice extends BaseEntity {

	private static final long serialVersionUID = -5177098099225592985L;
	@Json(strategy = Json.webStrategy)
	private String title;//标题
	@Json(strategy = Json.webStrategy)
	private ENoticeType type;//类型 信息来源
	@Json(strategy = Json.webStrategy)
	private ENoticeChannel channel;//类型（招聘、团队中的资金需求等）
	private String fromuid;//来自uid
	@Json(strategy = Json.webStrategy)
	private String fromname;//来自标题
	@Json(strategy = Json.webStrategy)
	private String content;//内容
	
	@Maps(strategy = "fundneed")
	public String getTitle() {
		return title;
	}
	
	@Maps(strategy = "fundneed")
	public void setTitle(String title) {
		this.title = title;
	}
	
	public ENoticeType getType() {
		return type;
	}
	
	public void setType(ENoticeType type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return ENoticeType.getName(type, Locale.CHINA);
	}
	
	public ENoticeChannel getChannel() {
		return channel;
	}

	public void setChannel(ENoticeChannel channel) {
		this.channel = channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return ENoticeChannel.getName(channel, Locale.CHINA);
	}
	
	public String getFromuid() {
		return fromuid;
	}
	
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}
	
	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	
	@Maps(strategy = "fundneed")
	public String getContent() {
		return content;
	}
	
	@Maps(strategy = "fundneed")
	public void setContent(String content) {
		this.content = content;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.title != null) {
			this.title = WebUtil.clearXSS(this.title);
		}
		if (this.content != null) {
			this.content = WebUtil.clearXSS(this.content);
		}
	}
	
}
