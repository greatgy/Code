package com.supergenius.xo.manager.entity;

import java.util.HashMap;
import java.util.Map;

import com.genius.core.base.utils.JsonUtil;
import com.genius.model.base.entity.BaseEntity;
import com.supergenius.xo.manager.enums.ECount;

/**
 * 计数明细表
 * 
 * @author liubin
 * @date 2016-7-17 下午2:37:14
 */
public class CountDetail extends BaseEntity {

	private static final long serialVersionUID = -1633061153598203251L;
	private String useruid;// 相关人uid
	private String refuid;// 相关uid
	private ECount type;// 类型（投票）
	private Map<String, Object> data;// 红方、蓝方(如：{vote:0}),0:红,1:蓝

	public CountDetail() {
		super();
	}

	public CountDetail(String useruid, String refuid, ECount type) {
		super();
		this.useruid = useruid;
		this.refuid = refuid;
		this.type = type;
	}

	public String getUseruid() {
		return useruid;
	}

	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}

	public String getRefuid() {
		return refuid;
	}

	public void setRefuid(String refuid) {
		this.refuid = refuid;
	}

	public ECount getType() {
		return type;
	}

	public void setType(ECount type) {
		this.type = type;
	}

	public String getData() {
		return JsonUtil.toJson(data);
	}

	@SuppressWarnings("unchecked")
	public void setData(String json) {
		this.data = JsonUtil.fromJson(json, Map.class);
	}

	/**
	 * 以Map的形式返回data
	 * 
	 * @author liubin
	 * @createtime 2016-8-16下午12:29:27
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getDataMap() {
		if(this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		return this.data;
	}

	/**
	 * 设置data的值，以{uid:true}的方式来记录给某人点赞
	 * 
	 * @param isred
	 * @author liubin
	 * @createtime 2016-8-16下午12:30:07
	 * @return void
	 */
	public void setDataMap_prize(String prizeUid) {
		this.getDataMap().put(prizeUid, true);
	}
}
