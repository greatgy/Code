package com.supergenius.xo.gupage.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.gupage.entity.Debate;

/**
 * 文章so
 * 
 * @author 杨光
 * @date 2017年9月19日09:54:02
 */
public interface DebateSO extends BaseSO<Debate> {

	/**
	 * 根据cid获取文章
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2017年9月19日18:00:35
	 * @return List<Debate>
	 */
	List<Debate> getListByCid(Pager pager, int cid);
	
	/**
	 * 根据cid获取文章，当cid为0时，获取所有的
	 * 
	 * @param cids
	 * @author 许志翔
	 * @createtime 2017年9月19日18:00:35
	 * @return List<Debate>
	 */
	List<Debate> getListByCid(Integer cid);

}
