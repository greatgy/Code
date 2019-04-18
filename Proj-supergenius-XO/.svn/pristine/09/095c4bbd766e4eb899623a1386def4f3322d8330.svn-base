package com.supergenius.xo.user.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.user.enums.EScore;

/**
 * 积分实体类
 * 
 * @author liubin
 * @date 2016-7-18 下午2:01:57
 */
public class Score extends BaseEntity {

	private static final long serialVersionUID = 2905750556408678791L;
	private String useruid;// 用户uid
	private int total;// 总积分
	private EScore type;// 分数类型（写文章获得，转载文章获得，积分晋级） 取用 in

	public Score() {
		super();
	}

	public Score(String useruid, int total, EScore type) {
		super();
		this.useruid = useruid;
		this.total = total;
		this.type = type;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public EScore getType() {
		return type;
	}

	public void setType(EScore type) {
		this.type = type;
	}

}
