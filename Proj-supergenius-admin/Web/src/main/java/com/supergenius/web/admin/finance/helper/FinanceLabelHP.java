package com.supergenius.web.admin.finance.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.finance.util.FinanceLabelRedisUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.service.LabelSO;

/**
 * 标签管理HP
 * 
 * @author LouPengYu
 */
public class FinanceLabelHP extends BaseHP{
	
	private static LabelSO so;
	private static AdminSO adminSO;

	private static LabelSO getSO() {
		if (so == null) {
			so = (LabelSO) spring.getBean(LabelSO.class);
		}
		return so;
	}

	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = (AdminSO) spring.getBean(AdminSO.class);
		}
		return adminSO;
	}
	
	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.content))) {
			map.put(MapperDict.content + MapperDict.suffix_like_key, model.get(ViewKeyDict.content).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String startTime = model.get(ViewKeyDict.createtimestart).toString().trim() + MapperDict.starttimeformat;
			map.put(MapperDict.createtimestartne, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String endTime = model.get(ViewKeyDict.createtimeend).toString().trim() + MapperDict.endtimeformat;
			map.put(MapperDict.createtimeendne, endTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.isrecommend))) {
			map.put(MapperDict.isrecommend, model.get(ViewKeyDict.isrecommend));
		}
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma
				+ MapperDict.createtime + MapperDict.desc);
		List<Label> list = getSO().getList(map);
		for (Label label : list) {
			label.setNowclick(Long.valueOf(FinanceLabelRedisUtil.getInt(label.getUid())));
			label.setTotalclick(Long.valueOf(FinanceLabelRedisUtil.getInt(label.getUid(), SysConf.LabelClickCount)));
			if (StrUtil.isNotEmpty(label.getAdminuid())) {
				Admin admin = getAdminSO().get(label.getAdminuid());
				if (admin != null) {
					label.setAdminname(admin.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 判断标签是否已经存在
	 * 
	 * @param content
	 * @return
	 * @author
	 */
	public static Label isExist(String content) {
		List<Label> list = getSO().getList();
		if (StrUtil.isEmpty(content)) {
			return null;
		}
		for (Label item : list) {
			if (content.equals(item.getContent())) {
				return item;
			}
		}
		return null;
	}

}
