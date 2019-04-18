package com.supergenius.xo.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EVideoFrom;

/**
 * 视频实体类
 * @author XieMing
 * @date 2016-7-17 下午12:04:44
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Video extends BaseEntity {

	private static final long serialVersionUID = 8867509606697189996L;
	@Json(strategy = Json.webStrategy)
	private String sn;//编号
	private String videouid;//视频的UID，CC视频提供的uid
	@Json(strategy = Json.webStrategy)
	private String name;//视频名称
	@Json(strategy = Json.webStrategy)
	private String code;//视频播放器代码
	@Json(strategy = Json.webStrategy)
	private double price;//价格
	private String thumblittle;//小效果图
	private String thumb;//效果图
	private String thumbbig;//大效果图
	@Json(strategy = Json.webStrategy)
	private String original;//原图
	private String refuid;//相关Uid
	private int playcount;//播放次数
	private int endcount;//播放完成的次数
	private int width;//宽度
	private int height;//高度
	@Json(strategy = Json.webStrategy)
	private String summary;//摘要
	@Json(strategy = Json.webStrategy)
	private String title;//标题
	@Json(strategy = Json.webStrategy)
	private String desc;//详细介绍
	private String fileurl;//文件下载地址
	private int sortorder;//排序从大到小
	@Json(strategy = Json.webStrategy)
	private boolean isrecommend;//推荐视频
	@Json(strategy = Json.webStrategy)
	private EVideoFrom channelfrom;//来源，包括：挑战视频、答辩视频、开题视频、专家讲解视频、范例视频
	@Json(strategy = Json.webStrategy)
	private EMajor major;//视频所属专业类型
	private int buyUserCount;
	private String userNames;

	@Json(strategy = Json.webStrategy)
	public String getMajorName() {
		return this.major.getName();
	}

	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public String getVideouid() {
		return videouid;
	}
	
	public void setVideouid(String videouid) {
		this.videouid = videouid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getThumblittle() {
		return thumblittle;
	}
	
	public void setThumblittle(String thumblittle) {
		this.thumblittle = thumblittle;
	}
	
	public String getThumb() {
		return thumb;
	}
	
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public String getThumbbig() {
		return thumbbig;
	}
	
	public void setThumbbig(String thumbbig) {
		this.thumbbig = thumbbig;
	}
	
	public String getOriginal() {
		return original;
	}
	
	public void setOriginal(String original) {
		this.original = original;
	}
	
	public String getRefuid() {
		return refuid;
	}
	
	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}
	
	public int getPlaycount() {
		return playcount;
	}
	
	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}
	
	public int getEndcount() {
		return endcount;
	}
	
	public void setEndcount(int endcount) {
		this.endcount = endcount;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getFileurl() {
		return fileurl;
	}
	
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	
	public int getSortorder() {
		return sortorder;
	}
	
	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}
	
	public EVideoFrom getChannelfrom() {
		return channelfrom;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelfromName() {
		return this.channelfrom.getName();
	}
	
	public boolean isIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(boolean isrecommend) {
		this.isrecommend = isrecommend;
	}
	
	public void setChannelfrom(EVideoFrom channelfrom) {
		this.channelfrom = channelfrom;
	}
	
	public EMajor getMajor() {
		return major;
	}
	
	public void setMajor(EMajor major) {
		this.major = major;
	}

	public void setBuyUserCount(int buyUserCount) {
		this.buyUserCount = buyUserCount;
	}
	
	@Json(strategy = Json.webStrategy)
	public int getBuyUserCount() {
		return this.buyUserCount;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getUserNames() {
		return this.userNames;
	}
	
	public void setImgs(String[] imgs) {
		this.setOriginal(imgs[0]);
		this.setThumblittle(imgs[1]);
		this.setThumb(imgs[2]);
		this.setThumbbig(imgs[3]);
	}
}
