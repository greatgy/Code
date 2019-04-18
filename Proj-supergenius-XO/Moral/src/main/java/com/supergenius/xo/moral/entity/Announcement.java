package com.supergenius.xo.moral.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.genius.model.base.entity.BaseEntity;

/**
 * 社区公告实体类
 * 
 * @author LiJiacheng
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Announcement extends BaseEntity {

	private static final long serialVersionUID = -4146763362878350336L;
	@Json(strategy = Json.webStrategy)
	public int sn;// 编号
	@Json(strategy = Json.webStrategy)
	public String title;// 标题
	@Json(strategy = Json.webStrategy)
	public String content;// 内容
	@Json(strategy = Json.webStrategy)
	public DateTime addtime;// 添加时间
	@Json(strategy = Json.webStrategy)
	public DateTime endtime;// 到期时间
	@Json(strategy = Json.webStrategy)
	public boolean istop;// 是否置顶

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

	public DateTime getEndtime() {
		return endtime;
	}

	public void setEndtime(DateTime endtime) {
		this.endtime = endtime;
	}

	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getEndtimeStr() {
		return endtime.toString(DateUtil.FORMAT_DATETIME_CHINA);
	}

	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getIstopName() {
		return istop ? "是" : "否";
	}

}
