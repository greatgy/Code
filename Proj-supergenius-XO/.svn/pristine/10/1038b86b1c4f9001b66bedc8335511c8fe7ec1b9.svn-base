package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EReplyStage;

/**
 * 答辩状态明细实体类
 * @author XieMing
 * @date 2016-7-17 下午12:05:32
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class AppReplyDetail extends BaseEntity {

	private static final long serialVersionUID = -1614984142247020429L;
	private String useruid;//学员Uid
	private String adminuid;//管理员Uid
	private String appreplyuid;//答辩表Uid
	private String applicationuid;//申请表Uid
	private String confuid;//会议室Uid
	private String name;//操作名称
	@Json(strategy = Json.webStrategy)
	private String file;//上传材料
	private String desc;//描述
	private String time;//提供或确定的时间
	private boolean isvideo;//是否通过视频
	@Json(strategy = Json.webStrategy)
	private EReplyStage replystagefrom;//原状态
	private EReplyStage replystageto;//现状态
	//以下字段不存入数据库
	private String adminname;// 管理员姓名
	
	public AppReplyDetail() {
		super();
	}
	
	public AppReplyDetail(String useruid, String adminuid, String appreplyuid, String confuid, String name, String file, String desc, String time, boolean isvideo, EReplyStage replystagefrom, EReplyStage replystageto) {
		super();
		this.useruid = useruid;
		this.adminuid = adminuid;
		this.appreplyuid = appreplyuid;
		this.confuid = confuid;
		this.name = name;
		this.file = file;
		this.desc = desc;
		this.time = time;
		this.isvideo = isvideo;
		this.replystagefrom = replystagefrom;
		this.replystageto = replystageto;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminuidname) {
		this.adminname = adminuidname;
	}

	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getAdminuid() {
		return adminuid;
	}
	
	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	
	public String getAppreplyuid() {
		return appreplyuid;
	}
	
	public void setAppreplyuid(String appreplyuid) {
		this.appreplyuid = appreplyuid;
	}
	
	public String getApplicationuid() {
		return applicationuid;
	}
	
	public void setApplicationuid(String applicationuid) {
		this.applicationuid = applicationuid;
	}
	
	public String getConfuid() {
		return confuid;
	}
	
	public void setConfuid(String confuid) {
		this.confuid = confuid;
	}
	
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
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public boolean isIsvideo() {
		return isvideo;
	}
	
	public void setIsvideo(boolean isvideo) {
		this.isvideo = isvideo;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getReplystagefromName() {
		return this.replystagefrom == null ? "" : this.replystagefrom.getName();
	}
	
	public EReplyStage getReplystagefrom() {
		return replystagefrom;
	}
	
	public void setReplystagefrom(EReplyStage replystagefrom) {
		this.replystagefrom = replystagefrom;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getReplystagetoName() {
		return this.replystageto.getName();
	}
	
	public EReplyStage getReplystageto() {
		return replystageto;
	}
	
	public void setReplystageto(EReplyStage replystageto) {
		this.replystageto = replystageto;
	}
	
}
