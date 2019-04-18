package com.supergenius.web.admin.enterpriser.hellper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.Participate;
import com.supergenius.xo.enterpriser.service.ParticipateSO;
import com.supergenius.xo.user.service.UserSO;

/** 
 * 讲座申请HP
 * @author liubin
 * @date 2016-10-25 下午4:55:47 
 */
public class ParticipateHP extends BaseHP {

	private static ParticipateSO so;
	private static UserSO userSO;
	
	private static ParticipateSO getSO() {
		if (so == null) {
			so = spring.getBean(ParticipateSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	/**
	 * 判断是否已经是学员
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午6:57:42
	 * @return boolean
	 */
	public static boolean getIsUser(String email) {
		if (StrUtil.isNotEmpty(email) && getUserSO().getEnableByEmail(email) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询数据
	 * @param model
	 * @return
	 * @author liubin
	 * @createtime 2016-10-25下午6:23:49
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		if (StrUtil.isNotEmpty(model.get(MapperDict.name))) {
			map.put(MapperDict.name + MapperDict.suffix_like_key, model.get(MapperDict.name).toString());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.email))) {
			map.put(MapperDict.email + MapperDict.suffix_like_key, model.get(MapperDict.email).toString());
		}
		if (StrUtil.isNotEmpty(model.get(MapperDict.status))) {
			map.put(MapperDict.status, model.get(MapperDict.status).toString());
		} else {
			List<EStatus> statusList = new ArrayList<>();
			statusList.add(EStatus.init);
			statusList.add(EStatus.disable);
			statusList.add(EStatus.enable);
			map.put(MapperDict.status + MapperDict.suffix_in_key, statusList);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greater_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_less_key, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCountBySearch(map));
		List<Participate> list = getSO().getListBySearch(map);
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
