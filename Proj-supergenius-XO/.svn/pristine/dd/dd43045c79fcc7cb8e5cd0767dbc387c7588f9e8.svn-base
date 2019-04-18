package com.supergenius.xo.manager.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EAppExpertStage;

/**
 * 申请专家明细
 * 
 * @author chenminchang
 * @date 2016-7-18 下午1:45:57
 */
public class AppExpertDetail extends BaseEntity {

	private static final long serialVersionUID = 5719005353366210698L;
	private String useruid; // 申请人Uid
	private String adminuid; // 管理员Uid
	private String appexpertuid; // 专家申请表Uid
	private String name; // 操作名称
	private String desc; // 描述
	private String file;// 上传材料
	private EAppExpertStage stagefrom; // 原状态
	private EAppExpertStage stageto; // 现状态

	public AppExpertDetail() {
		super();
	}

	public AppExpertDetail(String useruid, String appexpertuid, EAppExpertStage stagefrom, EAppExpertStage stageto, String file) {
		super();
		this.useruid = useruid;
		this.appexpertuid = appexpertuid;
		this.stagefrom = stagefrom;
		this.stageto = stageto;
		this.file = file;
	}

	public AppExpertDetail(String useruid, String appexpertuid, String name, EAppExpertStage stagefrom, EAppExpertStage stageto) {
		super();
		this.useruid = useruid;
		this.appexpertuid = appexpertuid;
		this.name = name;
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

	public String getAppexpertuid() {
		return appexpertuid;
	}

	public void setAppexpertuid(String appexpertuid) {
		this.appexpertuid = appexpertuid;
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

	public EAppExpertStage getStagefrom() {
		return stagefrom;
	}

	public void setStagefrom(EAppExpertStage stagefrom) {
		this.stagefrom = stagefrom;
	}

	public EAppExpertStage getStageto() {
		return stageto;
	}

	public void setStageto(EAppExpertStage stageto) {
		this.stageto = stageto;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}