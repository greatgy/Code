package com.supergenius.xo.moral.entity;

import java.util.Locale;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.moral.enums.ECase;

/**
 * 案例库(case)实体类
 * 
 * @author LiJiacheng
 */
@Maps(strategy = Maps.dbStrategy)
public class Case extends BaseEntity {

	private static final long serialVersionUID = 5458520552578902812L;
	@Json(strategy = Json.webStrategy)
	private String name;// 名称
	@Json(strategy = Json.webStrategy)
	private ECase type;// 文档或链接
	@Json(strategy = Json.webStrategy)
	private String href;// 链接地址
	@Json(strategy = Json.webStrategy)
	private int countdl;// 下载量

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ECase getType() {
		return type;
	}

	public void setType(ECase type) {
		this.type = type;
	}

	@Json(strategy = Json.webStrategy)
	public String getTypeName() {
		return ECase.getName(type, Locale.CHINA);
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getCountdl() {
		return countdl;
	}

	public void setCountdl(int countdl) {
		this.countdl = countdl;
	}

	/**
	 * @param cases
	 * @author liushaomin
	 */
	public void set(Case cases) {
		this.countdl = cases.getCountdl();
	}

}
