package com.supergenius.web.finance.helper;

import java.util.HashMap;
import java.util.Map;

import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Collect;
import com.supergenius.xo.finance.service.CollectSO;

/**
 * 收藏HP
 * 
 * @author yangguang
 * @date 2017年8月29日14:29:57
 */
public class CollectHP extends BaseHP {

	private static CollectSO so;

	public static CollectSO getSO() {
		if (so == null) {
			so = (CollectSO) spring.getBean(CollectSO.class);
		}
		return so;
	}

	/**
	 * 是否收藏
	 * 
	 * @param useruid
	 * @param refuid
	 * @return
	 * @author yangguang
	 * @date 2017年8月29日14:29:57
	 */
	public static boolean isCollect(String useruid, String refuid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.refuid, refuid);
		map.put(MapperDict.status, EStatus.enable);
		if (getSO().getOne(map) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 添加收藏
	 * 
	 * @param user
	 * @param id
	 * @author yangguang
	 * @date 2017年8月29日16:58:23
	 */
	public static boolean add(String useruid, String refuid) {
		Collect collect = new Collect();
		collect.setUseruid(useruid);
		collect.setRefuid(refuid);
		if (getSO().add(collect)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 取消收藏
	 * @param uid
	 * @param id
	 * @author yangguang
	 * @date 2017年8月29日14:29:57
	 */
	public static boolean cancleCollect(String useruid, String refuid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.refuid, refuid);
		if (getSO().deleteByMap(map)) {
			return true;
		}
		return false;
	}
}
