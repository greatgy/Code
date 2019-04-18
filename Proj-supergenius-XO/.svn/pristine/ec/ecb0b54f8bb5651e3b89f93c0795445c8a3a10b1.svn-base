package com.supergenius.xo.tpi.service;

import java.util.List;
import java.util.Map;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.TpiCount;
import com.supergenius.xo.tpi.enums.EArticleChannel;

/**
 * 点击明细
 * @author liushaomin
 */
public interface TpiCountSO extends BaseSO<TpiCount>{
	
	/**
	 * 获取文章排行榜
	 */
	public List<Map<String, Object>> getRankList(int pagenum, EArticleChannel articlechannel);
	
	/**
	 * 关联删除tpicount表中的数据
	 * @param ids refuid
	 * @return
	 * @author liuxiaoke
	 */
	public boolean deleteByRefuid(String[] ids);
	
}
