package com.supergenius.xo.official.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.official.enums.EBanner;

/**
 * 网站内容
 * @author LiuXiaoke
 */
@Maps(strategy=Maps.dbStrategy)
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Banner extends BaseEntity {
	
	private static final long serialVersionUID = -2679326563934428480L;
	@Json(strategy = Json.webStrategy)
    private String title;//标题
	//@Json(strategy = Json.webStrategy)
    //private String img;//路径
	@Json(strategy = Json.webStrategy)
    private String href;//链接
	@Json(strategy = Json.webStrategy)
    private int sortorder;//顺序
	@Json(strategy = Json.webStrategy)
	private String imglittle;// 小图
	@Json(strategy = Json.webStrategy)
	private String imgmedium;// 中图
	@Json(strategy = Json.webStrategy)
	private String imgbig;// 大图
	@Json(strategy = Json.webStrategy)
	private String imgoriginal;// 原图
	@Json(strategy = Json.webStrategy)
	private String background;//填充色
	@Json(strategy = Json.webStrategy)
	private EBanner bannertype;//banner 类别

	/**
	 * @param banner
	 * author yuyingjie
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void set(Banner banner){
		this.setTitle(banner.getTitle());
		this.setBackground(banner.getBackground());
		this.setBannertype(banner.getBannertype());
		this.setHref(banner.getHref());
		this.setSortorder(banner.getSortorder());
		if (banner.getImgoriginal() != null) {
			this.setImgoriginal(banner.getImgoriginal());
			this.setImgbig(banner.getImgbig());
			this.setImgmedium(banner.getImgmedium());
			this.setImglittle(banner.getImglittle());
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public int getSortorder() {
		return sortorder;
	}
	
	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}
	
	/**
	 * 设置图片
	 * @param article
	 * @param imgs
	 * @return
	 * @author yuyingjie
	 */
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void setImgs(String[] imgs){
		this.setImgoriginal(imgs[0]);
		this.setImgbig(imgs[1]);
		this.setImgmedium(imgs[2]);
		this.setImglittle(imgs[3]);
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}
	
	public EBanner getBannertype() {
		return bannertype;
	}

	public void setBannertype(EBanner bannertype) {
		this.bannertype = bannertype;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getBannertypeName() {
		return EBanner.getName(bannertype, Locale.CHINA);
	}
	
}
