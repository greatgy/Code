package com.supergenius.xo.moral.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.EChapter;

/**
 * 视频(video)实体类
 * @author LiJiacheng
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Video extends BaseEntity {

	private static final long serialVersionUID = 4289337009655235877L;
	@Json(strategy = Json.webStrategy)
	private String name;// 名称
	@Json(strategy = Json.webStrategy)
	private EChapter chapter;// 章节
	@Json(strategy = Json.webStrategy)
	private String code;// 视频代码
	@Json(strategy = Json.webStrategy)
	private String imglittle;// 小图
	private String img;// 中图
	private String imgbig;// 大图
	private String imgoriginal;// 原图
	@Json(strategy = Json.webStrategy)
	private int countpl;// 播放量

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EChapter getChapter() {
		return chapter;
	}

	public void setChapter(EChapter chapter) {
		this.chapter = chapter;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChapterName() {
		return EChapter.getName(chapter, Locale.CHINA);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImglittle() {
		return imglittle;
	}

	public void setImglittle(String imglittle) {
		this.imglittle = imglittle;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImgbig() {
		return imgbig;
	}

	public void setImgbig(String imgbig) {
		this.imgbig = imgbig;
	}

	public String getImgoriginal() {
		return imgoriginal;
	}

	public void setImgoriginal(String imgoriginal) {
		this.imgoriginal = imgoriginal;
	}
	
	/**
	 * 设置图片
	 * @param imgs
	 * @author liushaomin
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setImgs(String[] imgs){
		this.setImgoriginal(imgs[0]);
		this.setImgbig(imgs[1]);
		this.setImg(imgs[2]);
		this.setImglittle(imgs[3]);
	}

	public int getCountpl() {
		return countpl;
	}

	public void setCountpl(int countpl) {
		this.countpl = countpl;
	}

}
