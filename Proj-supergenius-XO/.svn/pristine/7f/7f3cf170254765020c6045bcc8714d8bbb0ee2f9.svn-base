package com.supergenius.xo.moral.entity;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EDoc;

/**
 * 下载明细表
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class DownloadDetail extends BaseEntity{

	private static final long serialVersionUID = -8623893526517846907L;
	private String useruid;//会员uid
	private EDoc type;//下载文档类型（讲义，学员上传）
	private String fromuid;//对应uid
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public EDoc getType() {
		return type;
	}
	
	public void setType(EDoc type) {
		this.type = type;
	}
	
	public String getFromuid() {
		return fromuid;
	}
	
	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
	}
}
