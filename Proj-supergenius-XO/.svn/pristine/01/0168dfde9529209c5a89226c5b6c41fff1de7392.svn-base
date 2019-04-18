package com.supergenius.xo.moral.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;

/**
 * 用户上传(userdoc)实体类
 * 
 * @author LiJiacheng
 */
@Maps(strategy = Maps.dbStrategy)
public class Userdoc extends BaseEntity {

	private static final long serialVersionUID = 5025509264284084910L;
	@Json(strategy = Json.webStrategy)
	private String name;// 资料名称
	@Json(strategy = Json.webStrategy)
	private String file;//材料
	@Json(strategy = Json.webStrategy)
	private String desc;//简介
	@Json(strategy = Json.webStrategy)
	private int countdl;// 下载量
	@Json(strategy = Json.webStrategy)
	private String useruid;// 会员uid
	@Json(strategy = Json.webStrategy)
	private String username;// 会员name
	private String md5sum;// md5加密结果

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getCountdl() {
		return countdl;
	}

	public void setCountdl(int countdl) {
		this.countdl = countdl;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getUsername() {
		return username;
	}

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setUsername(String username) {
		this.username = username;
	}

	public String getMd5sum() {
		return md5sum;
	}

	public void setMd5sum(String md5sum) {
		this.md5sum = md5sum;
	}

}
