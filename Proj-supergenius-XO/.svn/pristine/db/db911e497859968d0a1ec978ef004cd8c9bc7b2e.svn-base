package com.supergenius.xo.manager.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EAppJudgementStage;

/**
 * 申请裁判明细
 * 
 * @author liubin
 * @date 2016-7-17 下午3:37:07
 */
public class AppJudgementDetail extends BaseEntity {

	private static final long serialVersionUID = 6552370913167257282L;
	private String useruid;// 申请人Uid
	private String adminuid;// 管理员Uid
	private String appjudgementuid;// 裁判申请表Uid
	private String name;// 操作名称
	private String desc;// 描述
	private String file;// 上传材料
	private EAppJudgementStage stagefrom;// 原状态
	private EAppJudgementStage stageto;// 现状态

	public AppJudgementDetail() {
		super();
	}
	
	public AppJudgementDetail(String useruid, String appjudgementuid, String file, EAppJudgementStage stagefrom, EAppJudgementStage stageto) {
		super();
		this.useruid = useruid;
		this.appjudgementuid = appjudgementuid;
		this.file = file;
		this.stagefrom = stagefrom;
		this.stageto = stageto;
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

	public String getAppjudgementuid() {
		return appjudgementuid;
	}

	public void setAppjudgementuid(String appjudgementuid) {
		this.appjudgementuid = appjudgementuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public EAppJudgementStage getStagefrom() {
		return stagefrom;
	}

	public void setStagefrom(EAppJudgementStage stagefrom) {
		this.stagefrom = stagefrom;
	}

	public EAppJudgementStage getStageto() {
		return stageto;
	}

	public void setStageto(EAppJudgementStage stageto) {
		this.stageto = stageto;
	}
}
