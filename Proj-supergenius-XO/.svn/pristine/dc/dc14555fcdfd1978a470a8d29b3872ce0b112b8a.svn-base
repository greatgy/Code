package com.supergenius.xo.official.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.official.enums.EArticleChannel;
import com.supergenius.xo.official.enums.EArticleType;

/**
 * 文章和新闻实体
 * @author liushaomin
 */
@Json(value = {"uid", "status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy=Maps.dbStrategy)
public class Article extends BaseEntity{

	private static final long serialVersionUID = -7609085594415142008L;
	@Json(strategy = Json.webStrategy)
    private String title;// 标题
	@Json(strategy = Json.webStrategy)
    private String origin;// 来源
	@Json(strategy = Json.webStrategy)
    private String author;// 作者
	@Json(strategy = Json.webStrategy)
    private String content;// 内容
	@Json(strategy = Json.webStrategy)
	private EArticleChannel channel;//学员培训细则、培训专业介绍、智慧产业等
	@Json(strategy = Json.webStrategy)
	private EArticleType type;//新闻、文章、PC视频、移动端视频
    @Json(strategy = Json.webStrategy)
    private String keyword;// 关键字
    @Json(strategy = Json.webStrategy)
    private String imglittle;// 小图
    private String imgmedium;// 中图
    private String imgbig;// 大图
    private String imgoriginal;// 原图
    @Json(strategy = Json.webStrategy)
    private boolean ispublic;// 是否公开
    @Json(strategy = Json.webStrategy)
    private boolean istop;// 是否置顶 是、否
    @Json(strategy = Json.webStrategy)
    private boolean isrecommend;// 是否推荐 是、否
    
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
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

	public void setChannel(EArticleChannel channel) {
		this.channel = channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return EArticleChannel.getName(channel, Locale.CHINA);
	}
	
	public EArticleType getType() {
		return type;
	}
	
	public void setType(EArticleType type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return EArticleType.getName(type, Locale.CHINA);
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	/**
	 * 设置图片
	 * @param article
	 * @param imgs
	 * @return
	 * @author ShangJianguo
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setImgs(String[] imgs){
		if (imgs.length >= 4) {
			this.setImgoriginal(imgs[0]);
			this.setImgbig(imgs[1]);
			this.setImgmedium(imgs[2]);
			this.setImglittle(imgs[3]);
		}
	}
	
	public boolean isIspublic() {
		return ispublic;
	}
	
	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
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
}
