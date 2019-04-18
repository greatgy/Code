package com.supergenius.xo.manager.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genius.core.base.annotation.Json;
import com.genius.core.base.utils.JsonUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.manager.enums.EDynamic;

/**
 * 动态表实体类
 * @author XieMing
 * @date 2016-7-17 上午11:54:47
 */
@Json(value = {"uid", "oid","status", "createtime", "updatetime"}, ignoreTypeStrategy = Json.webStrategy, strategy = Json.webStrategy)
@JsonIgnoreProperties(value={"uid"})
public class Dynamic extends BaseEntity {
	
	private static final long serialVersionUID = 7707941727896672370L;
	private String useruid;//用户uid
	private EDynamic type;//动态类型
	@Json(strategy = Json.webStrategy)
	private String title;//动态标题
	private String content;//动态内容
	private Map<String, Object> data;//关联数据
	private String href;//关联链接
	
	public Dynamic() {
		super();
	}

	public Dynamic(String useruid, EDynamic type, String title, String content, Map<String, Object> data) {
		super();
		this.useruid = useruid;
		this.type = type;
		this.title = title;
		this.content = content;
		this.data = data;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getUseruid() {
		return useruid;
	}
	
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	
	public EDynamic getType() {
		return type;
	}
	
	public void setType(EDynamic type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getData() {
		return JsonUtil.toJson(this.data);
	}
	
	@SuppressWarnings("unchecked")
	public void setData(String data) {
		this.data = JsonUtil.fromJson(data, Map.class);
	}
	
	/**
	 * 获取map结构的数据
	 * @return
	 * @author liubin
	 * @createtime 2016-8-24下午6:53:34
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getDataMap() {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		return this.data;
	}

	/**
	 * 添加href字段
	 * @param href
	 * @author liubin
	 * @createtime 2016-8-24下午6:56:17
	 * @return void
	 */
	public void setDataMap_href(String href) {
		this.getDataMap().put(MapperDict.href, href);
	}

	/**
	 * 获取href字段
	 * @return
	 * @author liubin
	 * @createtime 2016-8-24下午6:57:42
	 * @return String
	 */
	@Json(strategy = Json.webStrategy)
	public String getDataMap_href() {
		if(this.getDataMap().get(MapperDict.href) == null){
			return "";
		}
		return this.getDataMap().get(MapperDict.href).toString();
	}
}
