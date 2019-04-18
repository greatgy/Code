package com.genius.model.baseadmin.entity;

import java.util.Locale;

import com.genius.model.base.entity.BaseEntity;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.enums.EWorkOrder;
import com.genius.model.baseadmin.enums.EWorkStage;

/**
 * @author liushaomin
 *
 */
public class WorkFlow extends BaseEntity{

	private static final long serialVersionUID = -7421436594802620276L;
	private String workorderuid;//工单UID
	private String adminuid;//操作管理员uid
	private String adminname;//操作管理员姓名
	private String title;//标题
	private String desc;//操作原因
	private String remark;//评论批复
	private String datafile;//数据文件，备用
	private int statusfrom;//原状态
	private int statusto;//现状态
	private EWorkStage stageform;//更改前状态
    private EWorkStage stageto;//更改后状态
    private EWorkOrder type;//工单类型
    private Object data;//关联对象
    
    /**
	 * @return the conobj
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the type
	 */
	public EWorkOrder getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EWorkOrder type) {
		this.type = type;
	}

	/**
	 * @return the stageform
	 */
	public EWorkStage getStageform() {
		return stageform;
	}

	/**
	 * @param stageform the stageform to set
	 */
	public void setStageform(EWorkStage stageform) {
		this.stageform = stageform;
	}
	
	public String getStageFromName() {
		return EWorkStage.getName(getStageform(), Locale.CHINA);
	}
	
	/**
	 * @return the stageto
	 */
	public EWorkStage getStageto() {
		return stageto;
	}

	/**
	 * @param stageto the stageto to set
	 */
	public void setStageto(EWorkStage stageto) {
		this.stageto = stageto;
	}

	public String getStageToName() {
		return EWorkStage.getName(getStageto(), Locale.CHINA);
	}
	
	/**
	 * @return the workorderuid
	 */
	public String getWorkorderuid() {
		return workorderuid;
	}
	/**
	 * @param workorderuid the workorderuid to set
	 */
	public void setWorkorderuid(String workorderuid) {
		this.workorderuid = workorderuid;
	}
	/**
	 * @return the adminuid
	 */
	public String getAdminuid() {
		return adminuid;
	}
	/**
	 * @param adminuid the adminuid to set
	 */
	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}
	/**
	 * @return the adminname
	 */
	public String getAdminname() {
		return adminname;
	}
	/**
	 * @param adminname the adminname to set
	 */
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the datafile
	 */
	public String getDatafile() {
		return datafile;
	}
	/**
	 * @param datafile the datafile to set
	 */
	public void setDatafile(String datafile) {
		this.datafile = datafile;
	}
	/**
	 * @return the statusfrom
	 */
	public int getStatusfrom() {
		return statusfrom;
	}
	/**
	 * @param statusfrom the statusfrom to set
	 */
	public void setStatusfrom(int statusfrom) {
		this.statusfrom = statusfrom;
	}
	
	public String getStatusFromName() {
		return EStatus.getName(EStatus.get(getStatusfrom()), Locale.CHINA);
	}
	
	/**
	 * @return the statusto
	 */
	public int getStatusto() {
		return statusto;
	}
	/**
	 * @param statusto the statusto to set
	 */
	public void setStatusto(int statusto) {
		this.statusto = statusto;
	}
	
	public String getStatusToName() {
		return EStatus.getName(EStatus.get(getStatusto()), Locale.CHINA);
	}

}
