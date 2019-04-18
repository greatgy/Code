package com.supergenius.xo.tpi.entity;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.enums.ECountType;

/**
 * 点击明细
 * @author liushaomin
 */
@Maps(strategy = Maps.dbStrategy)
public class TpiCount extends BaseEntity {

	private static final long serialVersionUID = 3127207095370758865L;
	
	private String useruid;//用户uid
	private String refuid;//对应的UID
	private EChannel channel;// 频道
	private ECountType type;//类别（点评论、阅读）
	private int count;//点击几次，默认为1
	
	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public String getRefuid() {
		return refuid;
	}
	
	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}
	
	public EChannel getChannel() {
		return channel;
	}
	
	public void setChannel(EChannel channel) {
		this.channel = channel;
	}
	
	public ECountType getType() {
		return type;
	}
	
	public void setType(ECountType type) {
		this.type = type;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
}
