package com.supergenius.xo.tpi.entity;

import java.io.Serializable;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.supergenius.xo.tpi.enums.EAssetScale;
import com.supergenius.xo.tpi.enums.EInvestType;

/**
 * 投资机构
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class InvestInfo implements Serializable{

	private static final long serialVersionUID = -6844631833756910213L;
	@Json(strategy = Json.webStrategy)
	private String introduce;// 投资（并购）机构介绍
	@Json(strategy = Json.webStrategy)
	private String group;// 投资（并购）机构团队
	@Json(strategy = Json.webStrategy)
	private EAssetScale scale;// 投资（并购）机构规模
	@Json(strategy = Json.webStrategy)
	private String industry;// 投资（并购）行业
	@Json(strategy = Json.webStrategy)
	private String standard;// 投资（并购）标准
	@Json(strategy = Json.webStrategy)
	private String limit;// 投资（并购）限制
	@Json(strategy = Json.webStrategy)
	private String riskmeasure;// 风险措施
	@Json(strategy = Json.webStrategy)
	private EInvestType type;// 类别 VIP投资机构、专业投资机构、法人投资机构、私人投资机构

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public EAssetScale getScale() {
		return scale;
	}

	public void setScale(EAssetScale scale) {
		this.scale = scale;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(value = Maps.dbStrategy)
	public String getScaleName() {
		return EAssetScale.getName(this.scale);
	}
	
	@MapsIgnore(value = Maps.dbStrategy)
	public String getScaleStr() {
		return EAssetScale.getByValues(this.scale);
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getRiskmeasure() {
		return riskmeasure;
	}

	public void setRiskmeasure(String riskmeasure) {
		this.riskmeasure = riskmeasure;
	}

	public EInvestType getType() {
		return type;
	}

	public void setType(EInvestType type) {
		this.type = type;
	}

	@Json(strategy = Json.webStrategy)
	@MapsIgnore(strategy=Maps.dbStrategy)
	public String getTypeName() {
		return EInvestType.getName(this.type, Locale.CHINA);
	}
	
	@MapsIgnore(value = Maps.dbStrategy)
	public String getScaleEName() {
		return scale.name();
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.introduce != null) {
			this.introduce = WebUtil.clearXSS(this.introduce);
		}
		if (this.group != null) {
			this.group = WebUtil.clearXSS(this.group);
		}
		if (this.industry != null) {
			this.industry = WebUtil.clearXSS(this.industry);
		}
		if (this.standard != null) {
			this.standard = WebUtil.clearXSS(this.standard);
		}
		if (this.limit != null) {
			this.limit = WebUtil.clearXSS(this.limit);
		}
		if (this.riskmeasure != null) {
			this.riskmeasure = WebUtil.clearXSS(this.riskmeasure);
		}
	}
	
}
