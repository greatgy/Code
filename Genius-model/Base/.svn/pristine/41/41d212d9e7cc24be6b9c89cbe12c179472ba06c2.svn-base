package com.genius.model.base.entity;

import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.GlobalUtil;
import com.genius.core.base.utils.RegexUtil;

/**
 * SEO实体类
 * eg:{uid:"uid111111111111", title:"${title}--网站名"}
 * requestURI=/index:{uri:"index", title:"网站名"}
 * requestURI=/product:{uri:"product", title:"产品列表", parentuid:"uid111111111111"}
 * 产品类别模板：{uid:"uid222222222", title:"${title}--电子", parentuid:"uid111111111111"}
 * requestURI=/product/oid:{uri:"product/.*", title:"${bean.name}", parentuid:"uid222222222"}
 * 产品bean.name为“联想笔记本”，则输出的title为：联想笔记本--电子
 * 应该统一规范页面的主对象要命名为bean，以便seo管理
 * @author architect.bian
 * 2014-6-24 上午11:40:23
 */
@Json
public class SEO {

	private String uid;
	private String uri;//正则
	private String name;
	private String title;
	private String keywords;
	private String desc;
	private String parentuid;
//	private ETmpl tmpltype;//所有的seo都采用freemarker模板
	
	public SEO() {}
	
	public SEO(String title, String keywords, String desc) {
		this.title = title;
		this.keywords = keywords;
		this.desc = desc;
	}
	
	public SEO(String uri, String title, String keywords, String desc) {
		this(uri, title, keywords, desc, null);
	}

	public SEO(String uri, String title, String keywords, String desc, String parentuid) {
		this.uri = uri;
		this.title = title;
		this.keywords = keywords;
		this.desc = desc;
		this.parentuid = parentuid;
	}

	public String getUid() {
		if (StringUtils.isEmpty(this.uid)) {
			this.uid = GlobalUtil.getUUID();
			return this.uid;
		}
		return this.uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * 判断this.uri是否与requestURI匹配，如product匹配http://genius.com/product而不匹配http://genius.com/product/oid
	 * @param requestURI
	 * @return
	 * @author Architect.bian
	 * 2014-6-24 下午2:06:17
	 */
	public boolean isMatchUri(String requestURI) {
		if (this.uri != null) {
			return RegexUtil.isMatch(String.format("^%s$", this.uri), requestURI);
		}
		return false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getParentuid() {
		return parentuid;
	}
	public void setParentuid(String parentuid) {
		this.parentuid = parentuid;
	}
}
