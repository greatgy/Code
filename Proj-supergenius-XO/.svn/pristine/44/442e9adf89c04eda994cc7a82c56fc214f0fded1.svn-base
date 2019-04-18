package com.supergenius.xo.tpi.entity;

import java.io.Serializable;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.supergenius.xo.tpi.enums.ERecommendType;;

/**
 * 推荐机构
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class RecommendInfo implements Serializable{
	
	private static final long serialVersionUID = -4605814045635423647L;
	private ERecommendType type;// 类别 政府机构、公司
	@Json(strategy = Json.webStrategy)
	private String introduce;// 推荐机构介绍
	@Json(strategy = Json.webStrategy)
	private String businesslicense;// 业执照（公司）
	@Json(strategy = Json.webStrategy)
	private String legalpersoncard;// 法人身份证照（公司）
	@Json(strategy = Json.webStrategy)
	private String sealpersoncard;// 盖有机构公章的联系人身份证明（政府机构）
	@Json(strategy = Json.webStrategy)
	private String personcard;// 联系人身份证清晰照片(政府机构)

	public ERecommendType getType() {
		return type;
	}

	public void setType(ERecommendType type) {
		this.type = type;
	}

	public String getBusinesslicense() {
		return businesslicense;
	}

	public void setBusinesslicense(String businesslicense) {
		this.businesslicense = businesslicense;
	}

	public String getLegalpersoncard() {
		return legalpersoncard;
	}

	public void setLegalpersoncard(String legalpersoncard) {
		this.legalpersoncard = legalpersoncard;
	}

	public String getSealpersoncard() {
		return sealpersoncard;
	}

	public void setSealpersoncard(String sealpersoncard) {
		this.sealpersoncard = sealpersoncard;
	}

	public String getPersoncard() {
		return personcard;
	}

	public void setPersoncard(String personcard) {
		this.personcard = personcard;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy = Maps.dbStrategy)
	public String getTypeName() {
		return ERecommendType.getName(type, Locale.CHINA);
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.introduce != null) {
			this.introduce = WebUtil.clearXSS(this.introduce);
		}
	}
	
}