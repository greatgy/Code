package com.supergenius.xo.gupage.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.xo.common.entity.BaseEntity;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.gupage.enums.EComment;
import com.supergenius.xo.gupage.rule.GupageSecondCommentCountRule;
import com.supergenius.xo.gupage.rule.PrizeCountCommentsGupageRule;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 评论实体类
 * 
 * @author yangguang
 * @date 2017年11月13日15:41:12
 */
@Json(value = { "uid", "fromuid", "to", "touseruid", "fromusername", "fromuseruid", "tousername", "status", "type", "content", "ip", "title", "adminname", "createtime" , "fromVisitorAvatar"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
public class Comments extends BaseEntity {

	private static final long serialVersionUID = -3494645693550486383L;
	private String fromuid;// 所评论的UID
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
	private EChannel channel;// 频道
	// 下面属性不存在数据库中，封装为了使用
	private String adminname;// 操作人姓名
	private String cataloguename;//模块名称
	private String title;// 评论文章标题
	private User fromUser;
	private User toUser;
	private String ip;
	private Visitor fromVisitor;
	private String fromVisitorAvatar;
	
	@Json(strategy = Json.webStrategy)
	public String getCataloguename() {
		return cataloguename;
	}

	public void setCataloguename(String cataloguename) {
		this.cataloguename = cataloguename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFromuid() {
		return fromuid;
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

	public EComment getType() {
		return type;
	}

	public void setType(EComment type) {
		this.type = type;
	}

	public String getTypeName() {
		return this.type.getName();
	}

	public EChannel getChannel() {
		return channel;
	}

	public void setChannel(EChannel channel) {
		this.channel = channel;
	}

	@Json(strategy = Json.webStrategy)
	public String getChannelName() {
		return EChannel.getName(channel, Locale.CHINA);
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
		Rule rule = new PrizeCountCommentsGupageRule(this.uid);
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
		Rule rule = new GupageSecondCommentCountRule(this.uid);
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
	
}