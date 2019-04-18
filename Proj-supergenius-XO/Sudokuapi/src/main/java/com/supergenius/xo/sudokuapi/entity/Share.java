package com.supergenius.xo.sudokuapi.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.supergenius.xo.base.BaseEntity;
import com.supergenius.xo.sudokuapi.enums.EAddress;

/**
 * 分享实体
 * 
 * @CreateTime 2018年5月30日--下午5:43:48
 * @author LiuBin
 */
@Json(value = { "uid", "status", "createtime", "updatetime", "version", "title", "text", "type", "imageurl", "url", "address" }, ignoreTypeStrategy = Json.appStrategy, strategy = Json.appStrategy)
@Maps(strategy = { Maps.dbStrategy })
public class Share extends BaseEntity {

	private static final long serialVersionUID = -48786267775002860L;

	private int version; // 版本
	private String appversion; // app版本
	private String title; // 分享标题
	private String text; // 分享内容
	private String imageurl; // 分享图片路径
	private String url; // 分享路径
	private int type; // 分享的类型(0:自动（IOS为自动，安卓仅为Text；1：文字；2：图文；4：连接；5：音乐；6：视频；7：应用；8：附件；9：表情）)
	private EAddress address; //根据版本号相对应位置; (1:首页; 2：单机游戏结束; 3：人机游戏结束)

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public EAddress getAddress() {
		return address;
	}

	public void setAddress(EAddress address) {
		this.address = address;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}