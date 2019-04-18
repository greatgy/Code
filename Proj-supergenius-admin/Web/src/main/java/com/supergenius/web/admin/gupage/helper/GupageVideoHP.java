package com.supergenius.web.admin.gupage.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.gupage.entity.Video;
import com.supergenius.xo.gupage.enums.ETop;
import com.supergenius.xo.gupage.rule.GupageFirstVideoRule;
import com.supergenius.xo.gupage.service.VideoSO;

/**
 * 图片管理HP
 * 
 * @author 杨光
 * @date 2018年1月10日13:54:52
 */
public class GupageVideoHP extends BaseHP {

	private static VideoSO so;

	private static VideoSO getSO() {
		if (so == null) {
			so = (VideoSO) spring.getBean(VideoSO.class);
		}
		return so;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return
	 * @author 杨光
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, model.get(BaseMapperDict.title).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		} else {
			map.put(MapperDict.istop, null);
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Video> list = getSO().getList(map);
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日14:12:33
	 */
	public static int getTopVideoCount() {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, ETop.istop); // 表示置顶
		List<Video> list = getSO().getList(map); // 得到置顶的文章
		return list.size();
	}
	
	/**
	 * 图片有变更时处理缓存
	 * 
	 * @return
	 * @author 杨光
	 * @date 2018年1月12日11:33:59
	 */
	public static void handleCache() {
		Rule rule = new GupageFirstVideoRule();
		MemcacheUtil.remove(rule);
	}

}
