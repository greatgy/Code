package com.supergenius.web.admin.enterpriser.hellper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.enterpriser.entity.AppCooperation;
import com.supergenius.xo.enterpriser.entity.Participate;
import com.supergenius.xo.enterpriser.service.AppCooperationSO;
import com.supergenius.xo.enterpriser.service.ParticipateSO;

/**
 * 互助合作平台报名HP
 * @author XieMing
 * @date 2016年12月5日 下午2:41:39
 */
public class AppCooperationHP extends BaseHP {

	private static AppCooperationSO so;
	private static ParticipateSO participateSO;
	
	private static AppCooperationSO getSO() {
		if (so == null) {
			so = spring.getBean(AppCooperationSO.class);
		}
		return so;
	}
	
	private static ParticipateSO getParticipateSO() {
		if (participateSO == null) {
			participateSO = spring.getBean(ParticipateSO.class);
		}
		return participateSO;
	}
	
	/**
	 * 查询数据
	 * @param model
	 * @return
	 * @author XieMing
	 * 2016年12月5日 下午2:48:26
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, true);
		if (StrUtil.isNotEmpty(model.get(MapperDict.keyword))) {
			map.put(MapperDict.keyword, model.get(MapperDict.keyword).toString());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		List<AppCooperation> list = getSO().getList(map);
		for (AppCooperation appCooperation : list) {
			Participate participate = getParticipateSO().get(appCooperation.getParticipateuid());
			appCooperation.setUserSn(participate.getUsersn());
		}
		result.put(ViewKeyDict.rows, list);
		return result;
	}
}
