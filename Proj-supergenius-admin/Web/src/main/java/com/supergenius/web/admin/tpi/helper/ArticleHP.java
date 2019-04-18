package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseArticleHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.service.ArticleSO;

/**
 * 文章HP
 * 
 * @author ShangJianguo
 */
public class ArticleHP extends BaseArticleHP {
	private static ArticleSO so;

	private static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @author ShangJianguo
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, EArticleChannel.get(model.get(ViewKeyDict.channel).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.ctype))) {
			map.put(MapperDict.ctype, model.get(ViewKeyDict.ctype).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.ntype))) {
			map.put(MapperDict.ntype, model.get(ViewKeyDict.ntype).toString());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.author))) {
			map.put(MapperDict.author, model.get(ViewKeyDict.author));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.origin))) {
			map.put(MapperDict.origin + MapperDict.suffix_like_key, model.get(ViewKeyDict.origin));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			String istop = model.get(ViewKeyDict.istop).toString();
			if ("0".equals(istop)) {
				map.put(MapperDict.istop, false);
			}else {
				map.put(MapperDict.istop, true);
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.ispublic))) {
			String ispublic = model.get(ViewKeyDict.ispublic).toString();
			if ("0".equals(ispublic)) {
				map.put(MapperDict.ispublic, false);
			}else {
				map.put(MapperDict.ispublic, true);
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.title))) {
			map.put(MapperDict.title + MapperDict.suffix_like_key, model.get(ViewKeyDict.title));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
				map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
}
