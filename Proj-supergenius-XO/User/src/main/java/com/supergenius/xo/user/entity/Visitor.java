package com.supergenius.xo.user.entity;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseWebConf;
import com.genius.model.base.entity.BaseEntity;

/**
 * Visitor实体类
 * 
 * @author 许志翔
 * @date 2017年8月2日15:27:12
 */
@Json(value = { "uid", "oid", "status", "nickname", "avatar", "createtime",
		"updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Visitor extends BaseEntity {

	private static final long serialVersionUID = 1411041121867975305L;
	private String loginip;// 登录IP
	@Json(strategy = Json.cacheStrategy)
	private String nickname;// 游客昵称
	@Json(strategy = Json.cacheStrategy)
	private String avatar;// 游客头像
	private String useruid;// 用户uid

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Json(strategy = Json.allStrategy)
	public String getUseravatar() {
		return BaseWebConf.UserImgRootPath + avatar;
	}
	
	@Json(strategy = Json.allStrategy)
	public String getUsername() {
		return nickname;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
}