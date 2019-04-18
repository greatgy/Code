package com.supergenius.xo.startup.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.startup.enums.ERuler;

/**
 * 规则实体类
 * 
 * @author ChenQi
 * @date 2017年6月20日 11:47:52
 */
@Json(value = { "uid", "type", "name", "rejectmincount", "rejectmaxcount", "minscore", "maxscore", "content",
		"adminuid", "adminname", "isinit" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Ruler extends BaseEntity {

	private static final long serialVersionUID = 5613847105728617725L;
	private ERuler type;// 评价类型
	private String name;// 规则名称
	private int rejectmincount;// 一票否决数量最小值(包含此值)
	private int rejectmaxcount;// 一票否决数量最大值(包含此值)
	private int minscore;// 分数区间最小值(包含此值)
	private int maxscore;// 分数区间最大值(不包含此值)
	private String content;// 得分区间反馈内容
	private String adminuid;// 操作人uid
	private int isinit;//是否是初始化规则(0不能删除1可以删除)
	
	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名

	public ERuler getType() {
		return type;
	}

	public void setType(ERuler type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRejectmincount() {
		return rejectmincount;
	}

	public void setRejectmincount(int rejectmincount) {
		this.rejectmincount = rejectmincount;
	}

	public int getRejectmaxcount() {
		return rejectmaxcount;
	}

	public void setRejectmaxcount(int rejectmaxcount) {
		this.rejectmaxcount = rejectmaxcount;
	}

	public int getMinscore() {
		return minscore;
	}

	public void setMinscore(int minscore) {
		this.minscore = minscore;
	}

	public int getMaxscore() {
		return maxscore;
	}

	public void setMaxscore(int maxscore) {
		this.maxscore = maxscore;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	public int getIsinit() {
		return isinit;
	}
	
	public void setIsinit(int i) {
		this.isinit = i;
	}
	
	/**
	 * 针对性更新
	 * 
	 * @param t
	 */
	public void set(Ruler t) {
		this.name = t.getName();
		this.type = t.getType();
		this.rejectmincount = t.getRejectmincount();
		this.rejectmaxcount = t.getRejectmaxcount();
		this.minscore = t.getMinscore();
		this.maxscore = t.getMaxscore();
		this.content = t.getContent();
		this.adminuid = t.getAdminuid();
	}
}
