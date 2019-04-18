package com.supergenius.xo.startup.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Collect;

/**
 * 收藏SO
 * 
 * @author 杨光
 * @date 2017年8月23日14:19:09
 */
public interface CollectSO extends BaseSO<Collect> {
	
	/**
	 * 获取收藏
	 * @param useruid
	 * @return
	 * @author 许志翔
	 * @date 2017年8月28日16:12:17
	 */
	List<Collect> getList(String useruid, Pager pager);
	
	/**
	 * 获取收藏的人数
	 * @return
	 * @author ChenQi
	 * @date 2017年9月5日18:18:27
	 */
	int getCount();

}
