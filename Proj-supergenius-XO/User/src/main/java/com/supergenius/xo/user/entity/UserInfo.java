package com.supergenius.xo.user.entity;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.enums.EUserAisle;

/**
 * 用户信息类实体
 *
 * @author LiuBin
 * @date 2016-3-23 下午4:53:47
 */
@Json(value = { "uid", "oid", "status", "createtime", "updatetime" }, ignoreTypeStrategy = { Json.webStrategy, Json.appStrategy }, strategy = Json.webStrategy)
public class UserInfo extends BaseEntity {

	private static final long serialVersionUID = -5724027970509303827L;
	private String name;// 真实姓名，2-4个汉字
	private String nickname;// 用户英文名
	private String showname;// 会员名
	private String identityid;// 身份证号
	@Json(strategy = { Json.appStrategy, Json.allStrategy })
	private EGender gender;// 性别(1:男,0:女)
	private DateTime birthday;// 出生年月日
	private String college;// 毕业院校
	private String address;// 现居地址
	private String qq;// QQ
	private String wx;// 微信
	private String sina;// 新浪微博
	private int aisle;// 注册通道
	private String othernicks;// 第三方登录昵称
	private String otheravatar;// 第三方登录头像
	private String country;// 护照国家
	private String soldier;// 士兵证
	private String passport;// 护照
	private String msn;// MSN
	private int freeze;// 用户冻结【0:正常，1:未填写信息，冻结，2:填写信息，冻结】
	private String avatarbig;// 个人头像180x180
	@Json(strategy = Json.appStrategy, alias = ("icon"))
	private String avatar;// 中头像90x90
	private String avatarlittle;// 小头像36x36
	private String original;// 原图
	@Json(strategy = Json.appStrategy, alias = ("desc"))
	private String summary;// 自我简介最多150字
	@Json(strategy = Json.appStrategy, alias = ("position"))
	private String job;// 职位
	private String department;// 部门
	@Json(strategy = Json.appStrategy, alias = ("org"))
	private String company;// 供职单位
	private String domain;// 二级域名
	private String honor;// 个人荣誉使用 ，以json格式存储(list(map))
	private Map<String, Map<String, Object>> education;// 教育经历使用，以json格式存储(map(map))
	private Map<String, Map<String, Object>> work;// 工作经历使用，以json格式存储(map(map))

	protected UserInfo getThis() {
		return this;
	}

	@Json(strategy = Json.allStrategy)
	public int getAisle() {
		return getThis().aisle;
	}

	@Json(strategy = Json.allStrategy)
	public int getFreeze() {
		return freeze;
	}

	public void setFreeze(int freeze) {
		this.freeze = freeze;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setAisle(int aisle) {
		getThis().aisle = aisle;
	}

	@Json(strategy = Json.allStrategy)
	public String getAisleName() {
		if (StrUtil.isEmpty(this.aisle)) {
			return null;
		} else {
			return EUserAisle.get(getThis().aisle).getName();
		}
	}

	@Json(strategy = Json.allStrategy)
	public String getName() {
		return getThis().name;
	}

	public void setName(String name) {
		getThis().name = name;
	}

	@Json(strategy = Json.allStrategy)
	public String getNickname() {
		return getThis().nickname;
	}

	public void setNickname(String nickname) {
		getThis().nickname = nickname;
	}

	@Json(strategy = Json.allStrategy)
	public String getShowname() {
		return getThis().showname;
	}

	public void setShowname(String showname) {
		getThis().showname = showname;
	}
	@Json(strategy = Json.allStrategy)
	public String getUsername() {
		if (StrUtil.isNotEmpty(getThis().nickname)) {
			return getThis().nickname;
		} else if (StrUtil.isNotEmpty(getThis().othernicks)) {
			return getThis().othernicks;
		} else {
			return getThis().showname;
		}
	}

	@Json(strategy = Json.allStrategy)
	public String getIdentityid() {
		return getThis().identityid;
	}

	public void setIdentityid(String identityid) {
		getThis().identityid = identityid;
	}

	public EGender getGender() {
		return getThis().gender;
	}

	public String getGenderName() {
		return getThis().gender.getName();
	}

	public void setGender(EGender gender) {
		getThis().gender = gender;
	}

	public DateTime getBirthday() {
		return getThis().birthday;
	}

	public void setBirthday(DateTime birthday) {
		getThis().birthday = birthday;
	}

	public String getCollege() {
		return getThis().college;
	}

	public void setCollege(String college) {
		getThis().college = college;
	}

	public String getAddress() {
		return getThis().address;
	}

	public void setAddress(String address) {
		getThis().address = address;
	}

	public String getQq() {
		return getThis().qq;
	}

	public void setQq(String qq) {
		getThis().qq = qq;
	}

	public String getWx() {
		return getThis().wx;
	}

	public void setWx(String wx) {
		getThis().wx = wx;
	}

	public String getSina() {
		return getThis().sina;
	}

	public void setSina(String sina) {
		getThis().sina = sina;
	}

	@Json(strategy = Json.allStrategy)
	public String getOthernicks() {
		return getThis().othernicks;
	}

	public void setOthernicks(String othernicks) {
		getThis().othernicks = othernicks;
	}

	public String getOtheravatar() {
		return getThis().otheravatar;
	}

	public void setOtheravatar(String otheravatar) {
		getThis().otheravatar = otheravatar;
	}



	public String getSoldier() {
		return getThis().soldier;
	}

	public void setSoldier(String soldier) {
		getThis().soldier = soldier;
	}

	public String getPassport() {
		return getThis().passport;
	}

	public void setPassport(String passport) {
		getThis().passport = passport;
	}

	public String getMsn() {
		return getThis().msn;
	}

	public void setMsn(String msn) {
		getThis().msn = msn;
	}

	@Json(strategy = Json.allStrategy)
	public String getAvatarbig() {
		return getThis().avatarbig;
	}

	public void setAvatarbig(String avatarbig) {
		getThis().avatarbig = avatarbig;
	}

	@Json(strategy = Json.allStrategy)
	public String getAvatar() {
		return getThis().avatar;
	}

	public String getUseravatar() {
		if (StrUtil.isNotEmpty(getThis().avatar)) {
			return BaseWebConf.UserImgRootPath + getThis().avatar;
		} else {
			return getThis().otheravatar;
		}
	}

	public void setAvatar(String avatar) {
		getThis().avatar = avatar;
	}

	@Json(strategy = Json.allStrategy)
	public String getAvatarlittle() {
		return getThis().avatarlittle;
	}

	public void setAvatarlittle(String avatarlittle) {
		getThis().avatarlittle = avatarlittle;
	}

	public String getOriginal() {
		return getThis().original;
	}

	public void setOriginal(String original) {
		getThis().original = original;
	}

	@Json(strategy = Json.allStrategy)
	public String getSummary() {
		return getThis().summary;
	}

	@Json(strategy = Json.allStrategy)
	public void setSummary(String summary) {
		getThis().summary = summary;
	}

	@Json(strategy = Json.allStrategy)
	public String getJob() {
		return getThis().job;
	}

	public void setJob(String job) {
		getThis().job = job;
	}

	@Json(strategy = Json.allStrategy)
	public String getDepartment() {
		return getThis().department;
	}

	public void setDepartment(String department) {
		getThis().department = department;
	}

	@Json(strategy = Json.allStrategy)
	public String getCompany() {
		return getThis().company;
	}

	public void setCompany(String company) {
		getThis().company = company;
	}

	public String getDomain() {
		return getThis().domain;
	}

	public void setDomain(String domain) {
		getThis().domain = domain;
	}

	public String getHonor() {
		return getThis().honor;
	}

	public void setHonor(String honor) {
		getThis().honor = honor;
	}

	public String getEducation() {
		return JsonUtil.toJson(getThis().education);
	}

	@SuppressWarnings("unchecked")
	public void setEducation(String education) {
		getThis().education = JsonUtil.fromJson(education, Map.class);
	}

	public String getWork() {
		return JsonUtil.toJson(getThis().work);
	}

	@SuppressWarnings("unchecked")
	public void setWork(String work) {
		getThis().work = JsonUtil.fromJson(work, Map.class);
	}

	/**
	 * 得到Map<String, Map<String, Object>>结构的education
	 *
	 * @author liubin
	 * @createtime 2016-8-19上午9:35:59
	 * @return Map<String, Map<String, Object>>
	 */
	public Map<String, Map<String, Object>> getEducationMap() {
		if (getThis().education == null) {
			getThis().education = new HashMap<String, Map<String, Object>>();
		}
		return getThis().education;
	}

	/**
	 * 获得指定教育经历map
	 *
	 * @param education
	 * @author liubin
	 * @createtime 2016-8-25下午2:16:28
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getEducationMap(String key) {
		return getThis().getEducationMap().get(key);
	}

	/**
	 * 添加教育经历
	 *
	 * @param education
	 * @author liubin
	 * @createtime 2016-8-19上午9:44:23
	 * @return void
	 */
	public void setEducationMap(Map<String, Object> education) {
		getThis().getEducationMap().put(String.valueOf(System.currentTimeMillis()), education);
	}

	/**
	 * 删除教育经历
	 *
	 * @param keys
	 * @author liubin
	 * @createtime 2016-8-19上午11:10:01
	 * @return void
	 */
	public void delEducationMap(String key) {
		getThis().getEducationMap().remove(key);
	}

	/**
	 * 得到Map<String, Map<String, Object>>结构的work
	 *
	 * @author liubin
	 * @createtime 2016-8-19上午11:19:16
	 * @return Map<String,Map<String,Object>>
	 */
	public Map<String, Map<String, Object>> getWorkMap() {
		if (getThis().work == null) {
			getThis().work = new HashMap<String, Map<String, Object>>();
		}
		return getThis().work;
	}

	/**
	 * 得到指定key值得WorkMap
	 *
	 * @param key
	 * @return
	 * @author liubin
	 * @createtime 2016-8-25下午7:15:13
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getWorkMap(String key) {
		return getThis().getWorkMap().get(key);
	}

	/**
	 * 添加个人工作经历
	 *
	 * @param honor
	 * @author liubin
	 * @createtime 2016-8-19上午11:20:15
	 * @return void
	 */
	public void setWorkMap(Map<String, Object> work) {
		getThis().getWorkMap().put(String.valueOf(System.currentTimeMillis()), work);
	}

	/**
	 * 删除个人工作经历
	 *
	 * @param key
	 * @author liubin
	 * @createtime 2016-8-19上午11:22:11
	 * @return void
	 */
	public void delWorkMap(String key) {
		getThis().getWorkMap().remove(key);
	}

}