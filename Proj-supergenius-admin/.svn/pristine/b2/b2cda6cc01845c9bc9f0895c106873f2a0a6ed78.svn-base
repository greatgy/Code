package com.supergenius.web.admin.official.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.official.entity.Banner;
import com.supergenius.xo.official.service.BannerSO;
import com.supergenius.xo.tpi.enums.EContent;

/**
 * 图片轮播管理
 * @author YuYingJie
 */
public class BannerHP extends BaseHP{
	
	private static BannerSO so;
	
	private static BannerSO getSO() {
		if (so == null) {
			so = (BannerSO) spring.getBean(BannerSO.class);
		}
		return so;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @author YuYingJie
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, getSO().getList(map));
		return result;
	}
	
	/**
	 * 得到所有类型
	 * @param
	 * @return
	 * @author YuYingJie
	 */
	public static Map<String, String> getBannerTypeMap() {
		Map<String, String> map = new LinkedHashMap<>();
		for (EContent e : EContent.values()) {
			map.put(e.name(), EContent.getName(e, Locale.CHINA));
		}
		return map;
	}
	
	/**
	 * 轮播图片上移
	 * @param ids
	 * @return
	 * @author YuYingJie
	 */
	public static boolean banner_up(String ids) {
		int i;
		List<Banner> list = getSO().banner_updown();
		for (i = 0 ; i<list.size() ; i++) {
			if(ids.equals(list.get(i).getUid()) && i > 0){
				int temp = list.get(i).getSortorder();
				list.get(i).setSortorder(list.get(i-1).getSortorder());
				list.get(i-1).setSortorder(temp);
				break;
			}
		}
		return getSO().update(list.get(i)) && getSO().update(list.get(i-1));
	}
	
	/**
	 * 轮播图片下移
	 * @param ids
	 * @return
	 * @author YuYingJie
	 */
	public static boolean banner_down(String ids) {
		int i;
		List<Banner> list = getSO().banner_updown();
		for (i = 0; i<list.size() ; i++) {
			if(ids.equals(list.get(i).getUid())){
				int temp = list.get(i).getSortorder();
				list.get(i).setSortorder(list.get(i+1).getSortorder());
				list.get(i+1).setSortorder(temp);
				break;
			}
		}
		return getSO().update(list.get(i)) && getSO().update(list.get(i+1));
	}
}
