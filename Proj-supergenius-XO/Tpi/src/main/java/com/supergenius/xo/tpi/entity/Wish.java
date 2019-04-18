package com.supergenius.xo.tpi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.EWishFromType;
import com.supergenius.xo.tpi.enums.EWishType;

/**
 * 意愿明细
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class Wish extends BaseEntity {
	
	private static final long serialVersionUID = -1330188799364389184L;
	
	private String fromuid;//意愿人UID
	private EWishFromType fromtype;
	@Json(strategy = Json.webStrategy)
	private String fromname;//意愿人姓名
	private String touid;//目标uid
	@Json(strategy = Json.webStrategy)
	private String title;//目标标题
	private String img;//目标头像
	@Json(strategy = Json.webStrategy)
	private String content;//并购方案、投资计划
	private EWishType type;//类型（支持[关注]、想并购、想投资）
	private EChannel channel;//频道（项目、团队、投资、并购机构）
	
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
	
	public String getTouid() {
		return touid;
	}
	
	public void setTouid(String touid) {
		this.touid = touid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public EWishType getType() {
		return type;
	}
	
	public void setType(EWishType type) {
		this.type = type;
	}
	
	public EChannel getChannel() {
		return channel;
	}
	
	public void setChannel(EChannel channel) {
		this.channel = channel;
	}

	public EWishFromType getFromtype() {
		return fromtype;
	}

	public void setFromtype(EWishFromType fromtype) {
		this.fromtype = fromtype;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public boolean getIsSuperGenius(){
		return this.fromtype == EWishFromType.supergenius;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.title != null) {
		}
		this.title = WebUtil.clearXSS(this.title);
		if (this.content != null) {
			this.content = WebUtil.clearXSS(this.content);
		}
	}
	
}
