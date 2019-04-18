package com.supergenius.xo.manager.entity;

import com.genius.model.base.entity.BaseEntity;

/** 
 * 答辩的专家实体
 * @author chenminchang
 * @date 2016-7-17 上午11:52:18 
 */
public class AppReplyExpert extends BaseEntity {

	private static final long serialVersionUID = 1328359664644494066L;
	private String appreplyuid; //答辩表UID
	private String expertuid; //专家UID
	private boolean isabsent; //是否缺席
	private String desc; //备注/原因
	
	public AppReplyExpert() {
		super();
	}

	public AppReplyExpert(String appreplyuid, String expertuid) {
		super();
		this.appreplyuid = appreplyuid;
		this.expertuid = expertuid;
	}

	public String getAppreplyuid() {
		return appreplyuid;
	}
	
	public void setAppreplyuid(String appreplyuid) {
		this.appreplyuid = appreplyuid;
	}
	
	public String getExpertuid() {
		return expertuid;
	}
	
	public void setExpertuid(String expertuid) {
		this.expertuid = expertuid;
	}
	
	public boolean isIsabsent() {
		return isabsent;
	}
	
	public void setIsabsent(boolean isabsent) {
		this.isabsent = isabsent;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
