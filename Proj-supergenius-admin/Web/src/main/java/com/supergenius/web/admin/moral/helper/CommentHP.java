package com.supergenius.web.admin.moral.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.moral.entity.Article;
import com.supergenius.xo.moral.enums.EComment;
import com.supergenius.xo.moral.service.ArticleSO;
import com.supergenius.xo.moral.service.CommentSO;

/**
 * 评论hp
 * 
 * @author liushaomin
 */
public class CommentHP extends BaseHP {

	private static CommentSO so;

	private static CommentSO getSO() {
		if (so == null) {
			so = (CommentSO) spring.getBean(CommentSO.class);
		}
		return so;
	}

	private static ArticleSO articleSO;

	private static ArticleSO getArticleSO() {
		if (articleSO == null) {
			articleSO = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return articleSO;
	}

	/**
	 * 组织数据
	 * 
	 * @param model
	 * @return
	 * @author liushaomin
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		map.put(MapperDict.type, EComment.comment);
		map.put(MapperDict.channel + MapperDict.suffix_no_key, EChannel.moraltalk);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.fromtitle))) {
			Map<String, Object> articleMap = getParamMap();
			articleMap.put(MapperDict.fromtitle + MapperDict.suffix_like_key, model.get(ViewKeyDict.fromtitle));
			List<Article> articles = getArticleSO().getList(articleMap);
			String[] useruids = new String[articles.size()];
			for (int i = 0; i < articles.size(); i++) {
				useruids[i] = articles.get(i).getUid();
			}
			map.put(MapperDict.fromuid + MapperDict.suffix_in_key, useruids);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.fromusername))) {
			map.put(MapperDict.fromusername + MapperDict.suffix_like_key, model.get(ViewKeyDict.fromusername));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.content))) {
			map.put(MapperDict.content + MapperDict.suffix_like_key, model.get(ViewKeyDict.content));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String start = model.get(ViewKeyDict.createtimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.createtime + MapperDict.suffix_greaterOrEqual_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String end = model.get(ViewKeyDict.createtimeend).toString() + MapperDict.endtimeformat;
			DateTime endTime = DateTime.parse(end, DateTimeFormat.forPattern(DateUtil.FORMAT_DATETIME_CHINA));
			map.put(MapperDict.createtime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.channel))) {
			map.put(MapperDict.channel, model.get(ViewKeyDict.channel));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, model.get(ViewKeyDict.status));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.fromtitle))) {
			map.put(MapperDict.fromtitle, model.get(ViewKeyDict.fromtitle));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}

}
