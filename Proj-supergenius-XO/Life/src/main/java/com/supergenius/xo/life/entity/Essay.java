package com.supergenius.xo.life.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.rule.LifeEssaySecondCommentCountRule;
import com.supergenius.xo.life.rule.PrizeCountEssayLifeRule;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 评论实体类
 * 
 * @author ChenQi
 * @date 2018年5月9日17:13:34
 */
@Json(value = { "uid", "cid", "touid", "touseruid", "fromusername", "fromuseruid", "tousername", "status", "type", "content", "fromUserType", "adminname", "createtime",
		"fromVisitorAvatar","catalogueName", "fromVisitorName"}, ignoreTypeStrategy = Json.webStrategy, strategy = { Json.webStrategy, Json.cacheStrategy })
public class Essay extends BaseEntity implements Comparable<Essay> {

	private static final long serialVersionUID = -8507452943623730323L;
	
	private long cid;// 评论模块cid
	private String touid;// 回复comment的uid，对应此表的uid
	private String fromuseruid;// 评论人uid
	private int fromuseroid;// 评论人oid
	private String fromusername;// 评论人name
	private String touseruid;// 评论给谁的uid
	private int touseroid;// 评论给谁的oid
	private String tousername;// 评论给谁的name
	private String content;// 评论
	private EComment type;// 类型，评论或者赞
	private String data;// 其他数据json格式
	private String adminuid;// 操作人UID
	private ELifeChannel channel;// 频道
	private String imglittle; // 小图
	private String imgmedium; // 中图
	private String imgbig; // 大图
	private String imgoriginal; // 原图
	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名
	private User fromUser;
	private User toUser;
	private Visitor fromVisitor;
	private String fromVisitorAvatar;
	private String fromVisitorName;
	private String useruid;
	private String catalogueName;
	private int fromUserType;// 游客为0会员为1
	private List<String> imgList;// 游客为0会员为1

	
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getTouid() {
		return touid;
	}

	public void setTouid(String touid) {
		this.touid = touid;
	}

	public String getFromuseruid() {
		return fromuseruid;
	}

	public void setFromuseruid(String fromuseruid) {
		this.fromuseruid = fromuseruid;
	}

	public int getFromuseroid() {
		return fromuseroid;
	}

	public void setFromuseroid(int fromuseroid) {
		this.fromuseroid = fromuseroid;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getTouseruid() {
		return touseruid;
	}

	public void setTouseruid(String touseruid) {
		this.touseruid = touseruid;
	}

	public int getTouseroid() {
		return touseroid;
	}

	public void setTouseroid(int touseroid) {
		this.touseroid = touseroid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EComment getType() {
		return type;
	}

	public void setType(EComment type) {
		this.type = type;
	}

	public String getTypeName() {
		return this.type.getName();
	}

	public ELifeChannel getChannel() {
		return channel;
	}

	public void setChannel(ELifeChannel channel) {
		this.channel = channel;
	}

	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return ELifeChannel.getName(channel, Locale.CHINA);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIsprize() {
		return this.isprize;
	}

	public void setIsprize(Boolean isprize) {
		this.isprize = isprize;
	}

	public Visitor getFromVisitor() {
		return fromVisitor;
	}

	public void setFromVisitor(Visitor fromVisitor) {
		this.fromVisitor = fromVisitor;
	}

	public String getFromVisitorAvatar() {
		return fromVisitorAvatar;
	}

	public void setFromVisitorAvatar(String fromVisitorAvatar) {
		this.fromVisitorAvatar = fromVisitorAvatar;
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

	public int getFromUserType() {
		return fromUserType;
	}

	public void setFromUserType(int fromUserType) {
		this.fromUserType = fromUserType;
	}

	/**
	 * 得到评论的赞数
	 * 
	 * @return
	 */
	@Json(strategy = Json.webStrategy)
	public long getPrizecount() {
		Rule rule = new PrizeCountEssayLifeRule(this.uid);
		admirecount = RedisUtil.getIncr(rule);
		return admirecount > 0 ? admirecount : 0;
	}

	public Long getCommentcount() {
		Rule rule = new LifeEssaySecondCommentCountRule(this.uid);
		return RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
	}

	public void setCommentcount(long commentcount) {
		this.commentcount = commentcount;
	}

	public List<String> getImgList() {
		List<String> list = new ArrayList<String>();
		if (StrUtil.isNotEmpty(this.imgmedium)) {
			String[] temp = this.imgmedium.split(",");
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i]);
			}
		}
		this.imgList = list;
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	@Override
	public int compareTo(Essay o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Essay) {
			Essay essay = (Essay) o;
			if (this.getCommentcount() > essay.getCommentcount()) {
				return -1;
			} else if (this.getCommentcount() == essay.getCommentcount()) {
				if(this.getPrizecount() > essay.getPrizecount()) {
					return -1;
				}
				if(this.getPrizecount() < essay.getPrizecount()) {
					return 1;
				}
				return 0;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}

	public String getFromVisitorName() {
		return fromVisitorName;
	}

	public void setFromVisitorName(String fromVisitorName) {
		this.fromVisitorName = fromVisitorName;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getCatalogueName() {
		return catalogueName;
	}

	public void setCatalogueName(String catalogueName) {
		this.catalogueName = catalogueName;
	}
	
}
