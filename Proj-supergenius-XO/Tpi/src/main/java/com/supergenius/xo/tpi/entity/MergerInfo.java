package com.supergenius.xo.tpi.entity;

import java.io.Serializable;
import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.annotation.MapsIgnore;
import com.genius.core.base.utils.WebUtil;
import com.supergenius.xo.tpi.enums.EAssetScale;
import com.supergenius.xo.tpi.enums.EMergeType;
import com.supergenius.xo.tpi.enums.EStaffScale;

/**
 * 并购机构
 * 
 * @author ShangJianguo
 */
@Maps(strategy = Maps.dbStrategy)
public class MergerInfo implements Serializable{
	
	private static final long serialVersionUID = 8269369992809556990L;
	@Json(strategy = Json.webStrategy)
	private String introduce;// 投资（并购）机构简介
	@Json(strategy = Json.webStrategy)
	private EAssetScale scale;//并购机构规模
	@Json(strategy = Json.webStrategy)
	private EStaffScale staffnum;//员工数量
	@Json(strategy = Json.webStrategy)
	private String address;//并购地域
	@Json(strategy = Json.webStrategy)
	private String superiority;//并购优势
	@Json(strategy = Json.webStrategy)
	private String mergercase;//并购案例
	@Json(strategy = Json.webStrategy)
	private String industry;//并购行业
	@Json(strategy = Json.webStrategy)
	private String standard;//并购标准
	@Json(strategy = Json.webStrategy)
	private String limit;//并购限制
	@Json(strategy = Json.webStrategy)
	private String other;//其他说明
	@Json(strategy = Json.webStrategy)
	private EMergeType type;// 类别 跨国并购机构、国内VIP并购机构、国内大型并购机构 国内中型并购机构、国内小型并购机构

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public EAssetScale getScale() {
		return scale;
	}

	@MapsIgnore(value = Maps.dbStrategy)
	public String getScaleEName() {
		return scale.name();
	}

	public void setScale(EAssetScale scale) {
		this.scale = scale;
	}
	
	@Json(strategy = Json.webStrategy)
	@MapsIgnore(value = Maps.dbStrategy)
	public String getScaleName() {
		return EAssetScale.getName(scale);
	}

	public EStaffScale getStaffnum() {
		return staffnum;
	}

	public String getStaffnumEName() {
		return staffnum.name();
	}

	public void setStaffnum(EStaffScale staffnum) {
		this.staffnum = staffnum;
	}
	
	@MapsIgnore(value = Maps.dbStrategy)
	public String getStuffnumName() {
		return EStaffScale.getName(staffnum);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSuperiority() {
		return superiority;
	}

	public void setSuperiority(String superiority) {
		this.superiority = superiority;
	}

	public String getMergercase() {
		return mergercase;
	}

	public void setMergercase(String mergercase) {
		this.mergercase = mergercase;
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

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public EMergeType getType() {
		return type;
	}

	public void setType(EMergeType type) {
		this.type = type;
	}
	
	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return EMergeType.getName(type, Locale.CHINA);
	}
	
	@MapsIgnore(strategy=Maps.dbStrategy)
	public void clearXSS() {
		if (this.introduce != null) {
			this.introduce = WebUtil.clearXSS(introduce);
		}
		if (this.address != null) {
			this.address = WebUtil.clearXSS(address);
		}
		if (this.superiority != null) {
			this.superiority = WebUtil.clearXSS(superiority);
		}
		if (this.mergercase != null) {
			this.mergercase = WebUtil.clearXSS(mergercase);
		}
		if (this.industry != null) {
			this.industry = WebUtil.clearXSS(industry);
		}
		if (this.standard != null) {
			this.standard = WebUtil.clearXSS(standard);
		}
		if (this.limit != null) {
			this.limit = WebUtil.clearXSS(limit);
		}
		if (this.other != null) {
			this.other = WebUtil.clearXSS(other);
		}
	}

}
