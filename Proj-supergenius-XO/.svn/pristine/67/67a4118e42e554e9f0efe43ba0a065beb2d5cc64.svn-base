package com.supergenius.xo.startup.entity;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;

/**
 * 标签实体类
 * 
 * @author 杨光
 * @date 2017年8月23日14:12:11
 */
@Json(value = {"uid", "status", "createtime", "content", "count", "isrecommend", "nowclick", "totalclick", "adminuid", "adminname"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Label extends BaseEntity {

	private static final long serialVersionUID = 8053337083988723415L;
	private String content;// 标签的内容
	private Long count;// 点击和搜索数量
	private int isrecommend;// 是(1)否(0)推荐
	private String refuid;// 字符串格式保存文章的uid
	private String adminuid;// 操作者uid
	// 不存入数据库
	private String adminname;
	private Long nowclick;// 今日点击量
	private Long totalclick;// 总点击量

	public Long getNowclick() {
		return nowclick;
	}

	public void setNowclick(Long nowclick) {
		this.nowclick = nowclick;
	}

	public Long getTotalclick() {
		return totalclick;
	}

	public void setTotalclick(Long totalclick) {
		this.totalclick = totalclick;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int isIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
}
