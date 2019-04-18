package com.supergenius.xo.user.entity;

import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.enums.EScore;

/**
 * 积分详细实体类
 * 
 * @author liubin
 * @date 2016-7-18 下午2:18:39
 */
public class ScoreDetail extends BaseEntity {

	private static final long serialVersionUID = -7971959864894585702L;
	private String useruid;// 用户uid
	private int score;// 变动积分
	private ESite site;// 变动的站点，对应ESite枚举
	private String refuid;// 获得积分与消费积分事件uid
	private EScore type;// 分数类型（写文章获得，转载文章获得，积分晋级）

	public ScoreDetail() {
		super();
	}

	public ScoreDetail(String useruid, int score, ESite site, String refuid, EScore type) {
		super();
		this.useruid = useruid;
		this.score = score;
		this.site = site;
		this.refuid = refuid;
		this.type = type;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ESite getSite() {
		return site;
	}

	public void setSite(ESite site) {
		this.site = site;
	}

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	public EScore getType() {
		return type;
	}

	public void setType(EScore type) {
		this.type = type;
	}

}
