package com.supergenius.xo.finance.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.DateUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.finance.enums.ECatalogue;

/**
 * 文章实体类
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午12:13:29
 */
@Json(value = { "uid", "status", "updatetime", "createtime", "cid", "adminuid", "useruid", "author", "content", "title", "imglittle", "imgmedium", "imgbig", "imgoriginal", "origin",
		"summary", "istop", "isrecommend", "toptime", "type", "adminname", "keywords", "tags", "isoriginal", "kind",
		"cataloguename", "data", "score", "booktime", "publisher"}, ignoreTypeStrategy = { Json.webStrategy, Json.cacheStrategy }, strategy = { Json.webStrategy, Json.cacheStrategy })
@Maps(strategy = { Maps.searchStrategy })
@MapsIgnore(strategy = Maps.searchStrategy, value = {"cids", "listcid", "adminuid", "useruid", "prizecount", "clickcount", "commentscount", "collectcount", "weight" })
public class Article extends BaseEntity implements Comparable<Article>{

	private static final long serialVersionUID = 1L;
	private int cid; // 模块id
	private List<ECatalogue> listcid = new ArrayList<>();
	private String contributeuid; // 投稿表id
	private String adminuid; // 操作管理员UID
	private String authoruid; // 用户uid
	private String author; // 作者
	private String content; // 内容
	private String title; // 标题
	private String shorttitle; // 短标题
	private int grade; // 文章等级(备用)
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	private String origin; // 来源
	private String originurl; // 来源地址
	private String summary; // 摘要内容
	private String tags;// 关键字
	private String keywords;// seo关键字
	private int sortorder; // 排序
	private int config; // 备用
	private String data; // 备用
	private int type; // 是否发布 0 不发布 1 发布 默认0
	private int isoriginal; // 是否原创(0非原创1原创) 默认0
	private int kind; // 类型,(0文章1视频2图片) 默认0
	private int istop; // 置顶(0非置顶1置顶) 默认0 (1表示设为焦点文章)
	private int isrecommend; // 是否推荐为首页显示
	private int ispublic; // 是否公开(备用)
	private DateTime toptime; // 置顶时间
	private DateTime publishtime; // 文章发布时间
	private DateTime booktime; // 文章预定发布时间

	// 下面属性不存在数据库中，封装为了使用
	private String adminname; // 管理员姓名
	private String cataloguename; // 模块名称
	private int clickcount;
	private int prizecount;
	private int commentscount;
	private int collectcount;
	private int score;
	private float weight;// 权重值
	private int ismember;// 权重值
	private String publisher; // 作者

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getContributeuid() {
		return contributeuid;
	}

	public void setContributeuid(String contributeuid) {
		this.contributeuid = contributeuid;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public int getIsoriginal() {
		return isoriginal;
	}

	public void setIsoriginal(int isoriginal) {
		this.isoriginal = isoriginal;
	}

	public String getAuthoruid() {
		return authoruid;
	}

	public void setAuthoruid(String authoruid) {
		this.authoruid = authoruid;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginurl() {
		return originurl;
	}

	public void setOriginurl(String originurl) {
		this.originurl = originurl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getSortorder() {
		return sortorder;
	}

	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}

	public int getConfig() {
		return config;
	}

	public void setConfig(int config) {
		this.config = config;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void minusCid(ECatalogue e) {
		this.cid = this.cid ^ Integer.valueOf(e.toString());
		this.listcid.remove(e);
	}

	public void plusCid(ECatalogue e) {
		if (e != null) {
			this.cid = this.cid | Integer.valueOf(e.toString());
			this.listcid.add(e);
		}
	}
	
	public List<ECatalogue> getCids() {
		if (listcid.size() == 0) {
			listcid = ECatalogue.getMatch(this.cid);
		}
		return listcid;
	}
	
	/**
	 * 显示所属的所有版块
	 * 
	 * @return
	 */
	@Json(strategy = {Json.webStrategy, Json.appStrategy})
	public String getCidName() {
		StringBuffer cidname = new StringBuffer();
		for (ECatalogue item : getCids()) {
			cidname.append(ECatalogue.getName(ECatalogue.get(item.toString()), Locale.CHINA).toString()).append(" ");
		}
		return cidname.toString();
	}
	
	public int getFirstCid() {
		List<ECatalogue> list = ECatalogue.getMatch(this.cid);
		if (list.size() != 0) {
			return Integer.valueOf(list.get(0).toString());
		}
		return this.cid;
	}
	
	/**
	 * 添加cid类型
	 * 
	 * @param catalogue
	 */
	public void setECatalogue(String[] catalogue) {
		for (String item : catalogue) {
			plusCid(ECatalogue.get(item));
		}
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

	public int getIspublic() {
		return ispublic;
	}

	public void setIspublic(int ispublic) {
		this.ispublic = ispublic;
	}

	public DateTime getToptime() {
		return toptime;
	}

	public void setToptime(DateTime toptime) {
		this.toptime = toptime;
	}

	public DateTime getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(DateTime publishtime) {
		this.publishtime = publishtime;
	}

	public DateTime getBooktime() {
		return booktime;
	}

	public void setBooktime(DateTime booktime) {
		this.booktime = booktime;
	}
	
	@Json(strategy = Json.allStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getBooktimeStr() {
		return getBooktime().toString(DateUtil.FORMAT_DATETIME_CHINA);
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
	}

	public int getClickcount() {
		return clickcount;
	}

	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}

	public int getPrizecount() {
		return prizecount;
	}

	public void setPrizecount(int prizecount) {
		this.prizecount = prizecount;
	}

	public int getCommentscount() {
		return commentscount;
	}

	public void setCommentscount(int commentscount) {
		this.commentscount = commentscount;
	}

	public int getCollectcount() {
		return collectcount;
	}

	public void setCollectcount(int collectcount) {
		this.collectcount = collectcount;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsprize() {
		return this.isprize;
	}

	public void setIsprize(Boolean isprize) {
		this.isprize = isprize;
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIscollect() {
		return this.iscollect;
	}

	public void setIscollect(Boolean iscollect) {
		this.iscollect = iscollect;
	}
	
	@Json(strategy = Json.webStrategy)
	public int getIsmember() {
		return this.ismember;
	}

	public void setIsmember(int ismember) {
		this.ismember = ismember;
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsEconomics() {
		return ECatalogue.ismatch(cid, ECatalogue.economics);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsInvest() {
		return ECatalogue.ismatch(cid, ECatalogue.invest);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsMerger() {
		return ECatalogue.ismatch(cid, ECatalogue.merger);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsRisk() {
		return ECatalogue.ismatch(cid, ECatalogue.risk);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsTechnology() {
		return ECatalogue.ismatch(cid, ECatalogue.technology);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsGold() {
		return ECatalogue.ismatch(cid, ECatalogue.gold);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsEntrepreneur() {
		return ECatalogue.ismatch(cid, ECatalogue.entrepreneur);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsProfession() {
		return ECatalogue.ismatch(cid, ECatalogue.profession);
	}
	
	@Json(strategy = Json.webStrategy)
	public boolean getIsNightwords() {
		return ECatalogue.ismatch(cid, ECatalogue.nightwords);
	}
	
	public List<ECatalogue> getListcid() {
		return listcid;
	}

	public void setListcid(List<ECatalogue> listcid) {
		this.listcid = listcid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public int compareTo(Article o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Article) {
			Article article = (Article) o;
			if (weight > article.getWeight()) {
				return -1;
			} else if (weight == article.getWeight()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
}