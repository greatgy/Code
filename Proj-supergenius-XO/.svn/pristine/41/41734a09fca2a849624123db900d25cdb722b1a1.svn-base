package com.supergenius.xo.moral.entity;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;

/**
 * 用户发帖
 * 
 * @author liushaomin
 */
@Json(value = { "uid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@Maps(strategy = Maps.dbStrategy)
public class Article extends BaseEntity {

	private static final long serialVersionUID = 7409205671636018258L;
	@Json(strategy = Json.webStrategy)
	private String useruid;// 会员uid
	@Json(strategy = Json.webStrategy)
	private int useroid;// 会员oid
	@Json(strategy = Json.webStrategy)
	private String username;// 会员名称
	@Json(strategy = Json.webStrategy)
	private String title;// 帖子标题
	@Json(strategy = Json.webStrategy)
	private String content;// 内容
	@Json(strategy = Json.webStrategy)
	private String keywords;// 关键词
	@Json(strategy = Json.webStrategy)
	private String imglittle;// 小图
	private String img;// 中图
	private String imgbig;// 大图
	private String imgoriginal;// 原图
	@Json(strategy = Json.webStrategy)
	private boolean istop;// 是否置顶
	@Json(strategy = Json.webStrategy)
	private int countclick;// 点击数
	@Json(strategy = Json.webStrategy)
	private int countcomment;// 评论数
	@Json(strategy = Json.webStrategy)
	private int countpraise;// 赞数
	
	private String isvisitor;//匿名评论
	
	public String getIsvisitor() {
		return isvisitor;
	}

	public void setIsvisitor(String isvisitor) {
		this.isvisitor = isvisitor;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public int getUseroid() {
		return useroid;
	}

	public void setUseroid(int useroid) {
		this.useroid = useroid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public boolean isIstop() {
		return istop;
	}

	public void setIstop(boolean istop) {
		this.istop = istop;
	}

	public int getCountclick() {
		return countclick;
	}

	public void setCountclick(int countclick) {
		this.countclick = countclick;
	}

	public int getCountcomment() {
		return countcomment;
	}

	public void setCountcomment(int countcomment) {
		this.countcomment = countcomment;
	}

	public int getCountpraise() {
		return countpraise;
	}

	public void setCountpraise(int countpraise) {
		this.countpraise = countpraise;
	}

	/**
	 * @param useruid
	 * @param useroid
	 * @param username
	 * @param title
	 * @param content
	 * @param keywords
	 * @param imglittle
	 * @param img
	 * @param imgbig
	 * @param imgoriginal
	 * @param istop
	 * @param countclick
	 * @param countcomment
	 * @param countpraise
	 * @author: LiJiacheng 2015-9-10 下午6:11:03
	 */
	public Article(String useruid, int useroid, String username, String title, String content, String keywords, String imglittle, String img, String imgbig, String imgoriginal, boolean istop,
			int countclick, int countcomment, int countpraise) {
		super();
		this.useruid = useruid;
		this.useroid = useroid;
		this.username = username;
		this.title = title;
		this.content = content;
		this.keywords = keywords;
		this.imglittle = imglittle;
		this.img = img;
		this.imgbig = imgbig;
		this.imgoriginal = imgoriginal;
		this.istop = istop;
		this.countclick = countclick;
		this.countcomment = countcomment;
		this.countpraise = countpraise;
	}

	/**
	 * 
	 * @author: LiJiacheng 2015-9-1 下午12:19:52
	 */
	public Article() {
		super();
	}

	/**
	 * @param oid
	 * @param createtime
	 * @param updatetime
	 * @author: LiJiacheng 2015-9-1 下午12:19:52
	 */
	public Article(int oid, DateTime createtime, DateTime updatetime) {
		super(oid, createtime, updatetime);
	}

	/**
	 * @param uid
	 * @param createtime
	 * @param updatetime
	 * @author: LiJiacheng 2015-9-1 下午12:19:52
	 */
	public Article(String uid, DateTime createtime, DateTime updatetime) {
		super(uid, createtime, updatetime);
	}

}
