package com.supergenius.xo.moral.entity;

import java.util.ArrayList;
import java.util.List;

import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EChapter;
import com.supergenius.xo.moral.enums.EQst;

/**
 * 考试结果统计(examstatistics)实体类
 * 
 * @author LiJiacheng
 */
@Maps(strategy = Maps.dbStrategy)
public class Examstatistics extends BaseEntity {

	private static final long serialVersionUID = -7239198510768926839L;
	private String useruid;// 会员uid
	private EChapter chapter;// 章节
	private EQst type;// 题型
	private List<String> erroruids = new ArrayList<>();// 答错的题的uids
	private List<String> correctuids = new ArrayList<>();// 正确题的uids

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public EChapter getChapter() {
		return chapter;
	}

	public void setChapter(EChapter chapter) {
		this.chapter = chapter;
	}

	public EQst getType() {
		return type;
	}

	public void setType(EQst type) {
		this.type = type;
	}
	
	public List<String> getErroruids() {
		return erroruids;
	}

	public void setErroruids(List<String> erroruids) {
		this.erroruids = erroruids;
	}

	public List<String> getCorrectuids() {
		return correctuids;
	}

	public void setCorrectuids(List<String> correctuids) {
		this.correctuids = correctuids;
	}

}
