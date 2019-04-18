package com.supergenius.xo.official.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.official.entity.Discuss;
import com.supergenius.xo.official.enums.EDiscuss;

/**
 * 评论互动so
 * @author liushaomin
 */
public interface DiscussSO extends BaseSO<Discuss>{

	/**
	 * 修改状态
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean deleteByUids(String[] ids);

	/**
	 * 获取专栏参与人数
	 * @param specialcolumn
	 * @return
	 * @author liushaomin
	 */
	int getCount(EDiscuss specialcolumn);

	/**
	 * 获取专栏评论
	 * @param pager
	 * @param specialcolumn
	 * @return
	 * @author liushaomin
	 */
	List<Discuss> getList(Pager pager, EDiscuss specialcolumn);

}
