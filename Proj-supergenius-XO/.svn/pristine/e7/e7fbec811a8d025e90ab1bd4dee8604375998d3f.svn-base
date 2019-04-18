package com.supergenius.xo.tpi.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.genius.model.base.enums.EStatus;

/**
 * 审批表
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class Audit extends BaseEntity {
	
	private static final long serialVersionUID = -3554833713889469656L;
	
	private String refuid; // 审批的数据的uid
	private String type; // 审批类别，是哪一模块的审批
	private EStatus status;// 审批结果，0不通过，1通过
	private String desc; // 原因，理由，描述等
	private String adminuid; // 审批人uid
	private String adminname; // 审批人

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public DateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}
}
