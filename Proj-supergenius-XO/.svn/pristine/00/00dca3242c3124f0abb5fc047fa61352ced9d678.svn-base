package com.supergenius.xo.tpi.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.enums.EMergeCaseType;
import com.supergenius.xo.tpi.enums.EMergeNewsType;

/**
 * 文章类，并购动态和并购案例
 * 
 * @author ShangJianguo
 */
@Maps(strategy=Maps.dbStrategy)
public class Article extends BaseEntity {

	private static final long serialVersionUID = -2679326563934428480L;
	@Json(strategy = Json.webStrategy)
	private String title;// 标题
	@Json(strategy = Json.webStrategy)
	private String origin;// 来源
	@Json(strategy = Json.webStrategy)
	private String author;// 作者
	@Json(strategy = Json.webStrategy)
	private String content;// 内容
	@Json(strategy = Json.webStrategy)
	private EArticleChannel channel;// （并购案例、并购动态）
	@Json(strategy = Json.webStrategy)
	private EMergeCaseType ctype;//（案例分析、传奇人物、并购故事）
	@Json(strategy = Json.webStrategy)
	private EMergeNewsType ntype;//（国外动态、国内动态、并购花絮、并购猜想）
	private String keyword;// 关键字
	private String summary;// 简介
	@Json(strategy = Json.webStrategy)
	private String imglittle;// 小图
	@Json(strategy = Json.webStrategy)
	private String imgmedium;// 中图
	@Json(strategy = Json.webStrategy)
	private String imgbig;// 大图
	@Json(strategy = Json.webStrategy)
	private String imgoriginal;// 原图
	@Json(strategy = Json.webStrategy)
	private boolean ispublic;// 是否公开
	@Json(strategy = Json.webStrategy)
	private boolean istop;// 是否置顶 是、否
	@Json(strategy = Json.webStrategy)
	private boolean isrecommend;// 是否推荐 是、否

	@MapsIgnore(strategy=Maps.dbStrategy)
	public void set(Article article){
		this.setTitle(article.getTitle());
		this.setAuthor(article.getAuthor());
		this.setOrigin(article.getOrigin());
		if (article.getImgoriginal() != null) {
			this.setImgoriginal(article.getImgoriginal());
			this.setImgbig(article.getImgbig());
			this.setImgmedium(article.getImgmedium());
			this.setImglittle(article.getImglittle());
		}
		this.setChannel(article.getChannel());
		this.setECtype(article.getECtype());
		this.setENtype(article.getENtype());
		this.setCtype(article.getCtype());
		this.setNtype(article.getNtype());
		this.setIspublic(article.ispublic);
		this.setContent(article.getContent());
	}
	
	/**
	 * 设置图片
	 * @param article
	 * @param imgs
	 * @return
	 * @author ShangJianguo
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setImgs(String[] imgs){
		this.setImgoriginal(imgs[0]);
		this.setImgbig(imgs[1]);
		this.setImgmedium(imgs[2]);
		this.setImglittle(imgs[3]);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EArticleChannel getChannel() {
		return channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return EArticleChannel.getName(channel, Locale.CHINA);
	}
	
	public void setChannel(EArticleChannel channel) {
		this.channel = channel;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImglittle() {
		return imglittle;
	}

	public void setImglittle(String imglittle) {
		this.imglittle = imglittle;
	}

	public String getImgmedium() {
		return imgmedium;
	}

	public void setImgmedium(String imgmedium) {
		this.imgmedium = imgmedium;
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

	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	public boolean isIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(boolean isrecommend) {
		this.isrecommend = isrecommend;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean isIspublic() {
		return ispublic;
	}

	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public EMergeCaseType getECtype() {
		return ctype;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setECtype(EMergeCaseType ctype) {
		this.ctype = ctype;
	}
	
	public void setCtype(String ctype) {
		this.ctype = EMergeCaseType.getByName(ctype);
	}
	
	public void setNtype(String ntype) {
		this.ntype = EMergeNewsType.getByName(ntype);
	}
	
	public String getCtype() {
		if(ctype != null) {
			return ctype.name();
		} else {
			return null;
		}
	}
	
	public String getNtype() {
		if(ntype != null) {
			return ntype.name();
		} else {
			return null;
		}
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public EMergeNewsType getENtype() {
		return ntype;
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setENtype(EMergeNewsType ntype) {
		this.ntype = ntype;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		String result = null;
		if (this.ctype != null) {
			switch (this.getECtype().ordinal()) {
			case 0:	result = "案例分析"	;	break;
			case 1:	result = "传奇人物"	;	break;
			case 2:	result = "并购故事"	;	break;
			default: result="文章内容";	break;
			}
			return result;
		}
		if (this.ntype != null) {
			switch (this.getENtype().ordinal()) {
			case 0:	result = "国外动态"	;	break;
			case 1:	result = "国内动态"	;	break;
			case 2:	result = "并购花絮"	;	break;
			case 3:	result = "并购猜想"	;	break;
			default: result="文章内容";	break;
			}
			return result;
		}
		return result;
	}

}
