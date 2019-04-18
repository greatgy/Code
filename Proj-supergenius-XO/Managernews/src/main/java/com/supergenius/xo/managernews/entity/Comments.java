package com.supergenius.xo.managernews.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.managernews.enums.EComment;
import com.supergenius.xo.managernews.enums.EManagernewsChannel;
import com.supergenius.xo.managernews.rule.ManagernewsSecondCommentCountRule;
import com.supergenius.xo.managernews.rule.PrizeCountCommentsManagernewsRule;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 评论实体类
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午1:54:50
 */
@Json(value = { "uid", "createtime", "to", "touseruid", "fromusername", "fromuseruid", "tousername", "status", "type", "content", "ip", "title", "adminname", "createtime" , "fromVisitorAvatar", "cataloguename", "fromVisitorName" }, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Comments extends BaseEntity implements Comparable<Comments> {

	private static final long serialVersionUID = 1L;
	private String fromuid;// 所评论文章的UID
	private String touid;// 回复comment的uid，对应此表的uid
	private String fromuseruid;// 评论人uid
	private int fromuseroid;// 评论人oid
	private String fromusername;// 评论人name
	private String touseruid;// 评论给谁的uid
	private int touseroid;// 评论给谁的oid
	private String tousername;// 评论给谁的name
	private int cid;// 评论模块cid
	private String cataloguename;// 评论模块名称
	private String content;// 评论
	private EManagernewsChannel channel;// 频道
	private String data;// 其他数据json格式
	private EComment type;// 类型，评论或者赞
	private String adminuid;// 操作人UID

	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名
	private String title;// 评论文章标题
	private User fromUser;
	private User toUser;
	private String ip;
	private Visitor fromVisitor;
	private String fromVisitorAvatar;
	private String fromVisitorName;
	private String useruid;

	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
	}

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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EManagernewsChannel getChannel() {
		return channel;
	}

	public void setChannel(EManagernewsChannel channel) {
		this.channel = channel;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return EManagernewsChannel.getName(channel, Locale.CHINA);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public EComment getType() {
		return type;
	}

	public void setType(EComment type) {
		this.type = type;
	}

	public String getAdminuid() {
		return adminuid;
	}

	public void setAdminuid(String adminuid) {
		this.adminuid = adminuid;
	}

	@Json(strategy = Json.webStrategy)
	public boolean getIsprize() {
		return this.isprize;
	}

	public void setIsprize(Boolean isprize) {
		this.isprize = isprize;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
	

	/**
	 * 得到评论的赞数
	 * 
	 * @return
	 */
	@Json(strategy = Json.webStrategy)
	public long getPrizecount() {
		Rule rule = new PrizeCountCommentsManagernewsRule(this.uid);
		admirecount = RedisUtil.getIncr(rule);
		return admirecount > 0 ? admirecount : 0;
	}
	
	public Long getCommentcount() {
		Rule rule = new ManagernewsSecondCommentCountRule(this.uid);
		return RedisUtil.getIncr(rule) > 0 ? RedisUtil.getIncr(rule) : 0;
	}

	@Override
	public int compareTo(Comments o) {
		if (this == o) {
			return 0;
		} else if (o != null && o instanceof Comments) {
			Comments comments = (Comments) o;
			if (this.getCommentcount() > comments.getCommentcount()) {
			return -1;
			} else if (this.getCommentcount() == comments.getCommentcount()) {
				if(this.getPrizecount() > comments.getPrizecount()) {
					return -1;
				}
				if(this.getPrizecount() < comments.getPrizecount()) {
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
	
	
}