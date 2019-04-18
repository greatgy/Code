package com.supergenius.xo.enterpriser.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.enterpriser.enums.ECatalogue;

/**
 * 社区公告实体类
 * 
 * @author XueZhenYong
 * @date 2018年2月9日上午11:35:21
 */
@Json(value = { "uid","status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Announcement extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Json(strategy = Json.webStrategy)
	private int sn;// 编号
	@Json(strategy = Json.webStrategy)
	private int cid;// 论坛页面cid
	@Json(strategy = Json.webStrategy)
	private String title;// 标题
	@Json(strategy = Json.webStrategy)
	private String content;// 内容
	@Json(strategy = Json.webStrategy)
	private DateTime addtime;// 添加时间
	@Json(strategy = Json.webStrategy)
	private DateTime endtime;// 到期时间
	@Json(strategy = Json.webStrategy)
	private int istop;// 是否置顶

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DateTime getAddtime() {
		return addtime;
	}

	public void setAddtime(DateTime addtime) {
		this.addtime = addtime;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getAddtimeStr() {
		return addtime.toString(DateUtil.FORMAT_DATETIME_CHINA);
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getEndtimeStr() {
		return endtime.toString(DateUtil.FORMAT_DATETIME_CHINA);
	}

	public DateTime getEndtime() {
		return endtime;
	}

	public void setEndtime(DateTime endtime) {
		this.endtime = endtime;
	}

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getIstopName() {
		return istop == 1 ? "是" : "否";
	}
	
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getCatalogueName() {
		return ECatalogue.get(this.cid).getName();
	}

}
