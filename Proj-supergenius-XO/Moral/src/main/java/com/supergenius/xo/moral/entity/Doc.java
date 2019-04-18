package com.supergenius.xo.moral.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EChapter;

/**
 * 讲义(doc)实体类
 * 
 * @author LiJiacheng
 */
@Maps(strategy = Maps.dbStrategy)
public class Doc extends BaseEntity {

	private static final long serialVersionUID = 4822428414337591422L;
	@Json(strategy = Json.webStrategy)
	private String name;// 名称
	@Json(strategy = Json.webStrategy)
	private String file;//材料
	@Json(strategy = Json.webStrategy)
	private String docintro;// 讲义简介
	@Json(strategy = Json.webStrategy)
	private EChapter chapter;// 章节
	@Json(strategy = Json.webStrategy)
	private int countdl;// 下载量
	@Json(strategy = Json.webStrategy)
	private String sn;// 编号
	@Json(strategy = Json.webStrategy)
	private int sortorder;// 上移下移

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getDocintro() {
		return docintro;
	}

	public void setDocintro(String docintro) {
		this.docintro = docintro;
	}

	public EChapter getChapter() {
		return chapter;
	}

	public void setChapter(EChapter chapter) {
		this.chapter = chapter;
	}

	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getChapterName() {
		return EChapter.getName(chapter, Locale.CHINA);
	}

	public int getCountdl() {
		return countdl;
	}

	public void setCountdl(int countdl) {
		this.countdl = countdl;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getSortorder() {
		return sortorder;
	}

	public void setSortorder(int updown) {
		this.sortorder = updown;
	}

}
