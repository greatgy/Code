package com.supergenius.xo.moral.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;

/**
 * 证书 
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class Certificate {
	
	@Json(strategy = Json.webStrategy)
	private String sn;//证书编号
	@Json(strategy = Json.webStrategy)
	private String img;//证书图片
	@Json(strategy = Json.webStrategy)
	private DateTime createtime;//获得证书时间
	
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public DateTime getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}

}
