package com.supergenius.xo.career.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.Problem;

/**
 * 问题SO
 * 
 * @author ChenQi
 * @date 2017年11月13日17:06:02
 */
public interface ProblemSO extends BaseSO<Problem> {

	/**
	 * 设置是否置顶
	 * 
	 * @param oid
	 * @param istop
	 * @return
	 * @author 杨光
	 */
	boolean update(String[] ids, boolean istop);
	
	/**
	 * 得到指定数量的推荐文章
	 * 
	 * @param isrecommend
	 * @author yangguang
	 * @createtime 2017年11月15日10:45:06
	 * @return List<Article>
	 */
	List<Problem> getRecommendList(Pager pager, boolean isrecommend);
}
