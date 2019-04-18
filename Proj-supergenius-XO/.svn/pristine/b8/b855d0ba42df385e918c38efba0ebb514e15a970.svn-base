package com.supergenius.xo.startup.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 背景音乐实体类
 * 
 * @author 许志翔
 * @date 2017年8月9日14:42:59
 */
@Json(value = {"uid", "status", "createtime", "name", "url", "temp", "order", "adminuid", "adminname"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Music extends BaseEntity {

	private static final long serialVersionUID = 5887121247428549673L;
	private String name;// 歌曲名字
	private String url;// 歌曲路径
	private int temp;// 判断本地歌曲，还是网络歌曲  0 表示本地   1 表示网络
	private int order;// 显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示
	private String adminuid;// 操作人uid
	
	// 下面属性不存在数据库中，封装为了使用
	private String adminname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

}
