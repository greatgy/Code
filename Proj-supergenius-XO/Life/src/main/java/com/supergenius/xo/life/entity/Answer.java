package com.supergenius.xo.life.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.rule.LifePbmSecondCmtCountRlue;
import com.supergenius.xo.life.rule.PrizeCountAnswerLifeRule;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 回答实体类
 * 
 * @author YangGuang
 * @date 2018年5月9日16:02:43
 */
@Json(value = { "uid", "updatetime","fromuid", "touseruid", "cataloguename", "cid", "fromusername", "fromuseruid", "tousername", "status", "type", "content", "ip", "title", "adminname", "createtime" , "fromVisitorAvatar", "fromVisitorName", "ismajor"}, ignoreTypeStrategy = Json.webStrategy, strategy = { Json.webStrategy, Json.cacheStrategy })
public class Answer extends BaseEntity implements Comparable<Answer>{

	private static final long serialVersionUID = -8682811314575792169L;
	private String fromuid;// 所评论的UID
	private String title;// 所回答问题的标题
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
	private long cid;
	private String cataloguename;// 频道
	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名
	private User fromUser;
	private User toUser;
	private Visitor fromVisitor;
	private String fromVisitorAvatar;
	private String fromVisitorName;
	private String useruid;
	private int ismajor;

	public String getFromuid() {
		return fromuid;
	}

	public void setFromuid(String fromuid) {
		this.fromuid = fromuid;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 得到评论的赞数
	 * 
	 * @return
	 */
	@Json(strategy = Json.webStrategy)
	public long getPrizecount() {
		Rule rule = new PrizeCountAnswerLifeRule(this.uid);
		admirecount = RedisUtil.getIncr(rule);
		return admirecount > 0 ? admirecount : 0;
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIsprize() {
		return this.isprize;
	}

	public void setIsprize(Boolean isprize) {
		this.isprize = isprize;
	}

	public Long getCommentcount() {
		Rule rule = new LifePbmSecondCmtCountRlue(this.uid);
		return RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
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

	public EComment getType() {
		return type;
	}

	public void setType(EComment type) {
		this.type = type;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
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
	
	public int getIsmajor() {
		return ismajor;
	}

	public void setIsmajor(int ismajor) {
		this.ismajor = ismajor;
	}

	@Override
	public int compareTo(Answer o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Answer) {
			Answer answer = (Answer) o;
			if (answer.getIsmajor() == 1) {
				if(this.getCreatetime().isAfter(answer.getCreatetime())) {
					return -1;
				}
				if(this.getCreatetime().isBefore(answer.getCreatetime())) {
					return 1;
				}
				return 0;
			} else {
				if (this.getCommentcount() > answer.getCommentcount()) {
					return -1;
				} else if (this.getCommentcount() == answer.getCommentcount()) {
					if(this.getPrizecount() > answer.getPrizecount()) {
						return -1;
					}
					if(this.getPrizecount() < answer.getPrizecount()) {
						return 1;
					}
					return 0;
				} else {
					return 1;
				}
			}
		} else {
			return -1;
		}
	}
}