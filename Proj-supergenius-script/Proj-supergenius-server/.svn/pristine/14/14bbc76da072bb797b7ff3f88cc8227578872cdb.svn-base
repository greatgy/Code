package com.supergenius.server.manager.third.seegle.entity;

import java.io.Serializable;

/**
 * 
 * 会议室实体
 * 
 * @author chenminchang
 * @createtime 2016-9-9上午10:48:14
 */
public class ConfEntity implements Serializable {
	
	private static final long serialVersionUID = -4017481758665598718L;

	/**
	 * 会议ID
	 */
	private String cid;
	/**
	 * 会议室名称
	 */
	private String confname;
	/**
	 * 会议室开始时间
	 */
	private String begintime;
	/**
	 * 会议室结束时间
	 */
	private String endtime;
	/**
	 * 集群类型
	 */
	private String grouptype;
	/**
	 * 最大与会人数
	 */
	private String max_conf_user;
	/**
	 * 最大游客人数
	 */
	private String max_conf_tourist;
	/**
	 * 最大主席人数
	 * 
	 */
	private String max_conf_spokesman;
	/**
	 * 会议室密码
	 */
	private String conf_password_md5;
	/**
	 * 会议室管理密码
	 */
	private String manage_password_md5;
	/**
	 * 允许任何人参加（1是，0否）,可不填，默认为1
	 */
	private String open_flag = "0";
	/**
	 * 锁定会议室（1是，0否）,可不填，默认为0
	 */
	private String lock_flag = "0";
	/**
	 * 自动清空会议数据（1是，0否），可不填，默认为1
	 */
	private String auto_clear_flag = "1";
	/**
	 * 所有人可见（0是，1否），可不填，默认为1
	 */
	private String all_can_visible = "1";
	/**
	 * 白板文档下载方式（1全部下载，0按需下载）,可不填，默认为0
	 */
	private String download_mode = "0";
	/**
	 * 会议描述
	 */
	private String description;
	/**
	 * 视频设置+会议录制参数 可不填，默认为：<H264QP>=3;<VRenderer>=1;<AutoRecord>=0;
	 */
	private String confsetting;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getConfname() {
		return confname;
	}

	public void setConfname(String confname) {
		this.confname = confname;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getMax_conf_user() {
		return max_conf_user;
	}

	public void setMax_conf_user(String max_conf_user) {
		this.max_conf_user = max_conf_user;
	}

	public String getMax_conf_tourist() {
		return max_conf_tourist;
	}

	public void setMax_conf_tourist(String max_conf_tourist) {
		this.max_conf_tourist = max_conf_tourist;
	}

	public String getMax_conf_spokesman() {
		return max_conf_spokesman;
	}

	public void setMax_conf_spokesman(String max_conf_spokesman) {
		this.max_conf_spokesman = max_conf_spokesman;
	}

	public String getConf_password_md5() {
		return conf_password_md5;
	}

	public void setConf_password_md5(String conf_password_md5) {
		this.conf_password_md5 = conf_password_md5;
	}

	public String getManage_password_md5() {
		return manage_password_md5;
	}

	public void setManage_password_md5(String manage_password_md5) {
		this.manage_password_md5 = manage_password_md5;
	}

	public String getOpen_flag() {
		return open_flag;
	}

	public void setOpen_flag(String open_flag) {
		this.open_flag = open_flag;
	}

	public String getLock_flag() {
		return lock_flag;
	}

	public void setLock_flag(String lock_flag) {
		this.lock_flag = lock_flag;
	}

	public String getAuto_clear_flag() {
		return auto_clear_flag;
	}

	public void setAuto_clear_flag(String auto_clear_flag) {
		this.auto_clear_flag = auto_clear_flag;
	}

	public String getAll_can_visible() {
		return all_can_visible;
	}

	public void setAll_can_visible(String all_can_visible) {
		this.all_can_visible = all_can_visible;
	}

	public String getDownload_mode() {
		return download_mode;
	}

	public void setDownload_mode(String download_mode) {
		this.download_mode = download_mode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConfsetting() {
		return confsetting;
	}

	public void setConfsetting(String confsetting) {
		this.confsetting = confsetting;
	}

	@Override
	public String toString() {
		return "ConfEntity [cid=" + cid + ", confname=" + confname + ", begintime=" + begintime + ", endtime=" + endtime + ", grouptype=" + grouptype + ", max_conf_user=" + max_conf_user
				+ ", max_conf_tourist=" + max_conf_tourist + ", max_conf_spokesman=" + max_conf_spokesman + ", conf_password_md5=" + conf_password_md5 + ", manage_password_md5=" + manage_password_md5
				+ ", description=" + description + "]";
	}

}
