package com.supergenius.xo.finance.entity;

import com.genius.core.base.annotation.Json;
import com.supergenius.xo.common.entity.BaseEntity;

/**
 * 标签实体类
 * 
 * @author LouPengYu
 * @date 2018年1月2日09:41:43
 */
@Json(value = { "uid", "content", "adminname", "nowclick", "totalclick", "status", "createtime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Label extends BaseEntity {

	private static final long serialVersionUID = 677694943583462047L;
	private String content; // 内容
	private int count; // 点击或搜索数量
	private String refuid; // 关键字uid
	private String adminuid;// 操作人uid
	
	// 不存入数据库
	private String adminname;
	private Long nowclick;// 今日点击量
	private Long totalclick;// 总点击量
	private int isrecommend;//是否推荐1（是）0（否）

	    public int getIsrecommend() {
		return isrecommend;
	}

	    public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

		public String getAdminname() {
			return adminname;
		}

		public void setAdminname(String adminname) {
			this.adminname = adminname;
		}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}


}
