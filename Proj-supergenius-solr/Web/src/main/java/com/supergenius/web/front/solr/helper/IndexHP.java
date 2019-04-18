package com.supergenius.web.front.solr.helper;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.solr.entity.Content;
import com.supergenius.xo.solr.entity.Terms;
import com.supergenius.xo.solr.enums.EContent;
import com.supergenius.xo.solr.service.ContentSO;
import com.supergenius.xo.solr.service.TermsSO;

/**
 * 搜索HP
 * 
 * @author yangguang
 *
 */
public class IndexHP extends BaseHP {

	private static TermsSO termso;
	private static ContentSO contentso;

	public static ContentSO getContentSO() {
		if (contentso == null) {
			contentso = (ContentSO) spring.getBean(ContentSO.class);
		}
		return contentso;
	}
	
	public static TermsSO getTermsSO() {
		if (termso == null) {
			termso = (TermsSO) spring.getBean(TermsSO.class);
		}
		return termso;
	}

	/**
	 * 得到热门词
	 * 
	 * @return
	 * @author yangguang
	 * @date 2017年11月2日18:44:02
	 * @return List<Terms>
	 */
	public static List<Terms> getHotLabel(int pagerSize) {
		Pager pager = Pager.getNewInstance(0, pagerSize);
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.count + MapperDict.desc);
		List<Terms> hotLabelList = getTermsSO().getList(map);
		return hotLabelList;
	}

	/**
	 * 根据内容得到搜索词
	 * 
	 * @return
	 * @author yangguang
	 * @date 2017年11月2日18:44:02
	 * @return Terms
	 */
	public static Terms getTermByContent(String keyword) {
		List<Terms> list = getTermsSO().getList();
		for(Terms item : list){
			if (keyword.equals(item.getContent())) {
				return item;
			}
		} 
		Terms terms = new Terms();
		terms.setContent(keyword);
		terms.setCount(0L);
		getTermsSO().add(terms);
		return terms;
	}

	/**
	 * 得到广告位内容
	 * 
	 * @return
	 * @author yangguang
	 * @date 2017年11月2日19:25:44
	 * @return List<Content>
	 */
	public static List<Content> getADContent() {
		List<Content> list = getContentSO().getADContentList(EStatus.enable, EContent.ad);
		// 单独封装成方法为以后扩展会员通道模块时使用。
		return list;
	}
}
