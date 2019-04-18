package com.genius.core.base.mock.testentity;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Maps;

/**
 * msg的基类，单元测试用
 * 
 * @author architect.bian
 * @createtime 2015-9-25 下午2:29:40
 */
@Maps
public class Msg implements Serializable {

	private static final long serialVersionUID = 8363016237491162171L;
	
	private User from;
	private User to;
	
	private DateTime createtime;
	private DateTime updatetime;
	
	@Maps(isRaw = true)
	public User getFrom() {
		return from;
	}
	@Maps(isRaw = true)
	public void setFrom(User from) {
		this.from = from;
	}
	
	@Maps(isRaw = true)
	public User getTo() {
		return to;
	}
	@Maps(isRaw = true)
	public void setTo(User to) {
		this.to = to;
	}
	
	@Maps(isRaw = true)
	public DateTime getCreatetime() {
		return createtime;
	}
	@Maps(isRaw = true)
	public void setCreatetime(DateTime createtime) {
		this.createtime = createtime;
	}
	
	public DateTime getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(DateTime updatetime) {
		this.updatetime = updatetime;
	}

	
}
