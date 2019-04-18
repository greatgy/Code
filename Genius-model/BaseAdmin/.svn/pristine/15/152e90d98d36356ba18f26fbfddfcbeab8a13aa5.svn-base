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
public class WorkOrder extends BaseEntity{

	private static final long serialVersionUID = 3551463138533013969L;
    private String sn;//工单号
    private String adminuid;//操作管理员uid
	private String adminname;//操作管理员姓名
    private String title;//标题
    private String fromuid;//来自哪个对象的uid
    private String name;//对象名称
    private String desc;//操作原因
    private String remark;//评论批复
    private String datafile;//数据文件
    private EWorkOrder type;//工单类型
    private String channel;//频道，备用
    private Object data;//关联对象
    private EWorkStage stageform;//更改前状态
    private EWorkStage stageto;//更改后状态
    
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
   	 * @return the sn
   	 */
   	public String getSn() {
   		return sn;
   	}
   	/**
   	 * @param sn the sn to set
   	 */
   	public void setSn(String sn) {
   		this.sn = sn;
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
   	 * @return the fromuid
   	 */
   	public String getFromuid() {
   		return fromuid;
   	}
   	/**
   	 * @param fromuid the fromuid to set
   	 */
   	public void setFromuid(String fromuid) {
   		this.fromuid = fromuid;
   	}
   	/**
   	 * @return the name
   	 */
   	public String getName() {
   		return name;
   	}
   	/**
   	 * @param name the name to set
   	 */
   	public void setName(String name) {
   		this.name = name;
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
   	 * @return the channel
   	 */
   	public String getChannel() {
   		return channel;
   	}
   	/**
   	 * @param channel the channel to set
   	 */
   	public void setChannel(String channel) {
   		this.channel = channel;
   	}
   	
   	public String getStatusName() {
		return EStatus.getName(getStatus(), Locale.CHINA);
	}

}
