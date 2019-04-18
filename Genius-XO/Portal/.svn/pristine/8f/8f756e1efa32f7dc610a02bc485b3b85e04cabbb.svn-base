package com.genius.xo.portal.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.portal.entity.Content;
import com.genius.xo.baseadmin.service.BaseSO;

/**
 * @author Architect.bian
 * 
 */
public interface ContentSO extends BaseSO<Content> {

	/**
	 * @param e
	 * @return
	 */
	Content getOneByType(String e);

	/**
	 *
	 * @param valueOf
	 * @return 
	 * @author liushaomin
	 * 2014-6-13 上午11:50:08
	 */
	Content get(Integer oid);

	/**
	 *
	 * @param pager
	 * @param advantageus
	 * @return 
	 * @author liushaomin
	 * 2014-6-13 下午3:11:53
	 */
	List<Content> getListByType(Pager pager, String type);

	/**
	 *
	 * @param pager
	 * @param advantageus
	 * @return 
	 * @author liushaomin
	 * 2014-6-13 下午3:11:53
	 */
	List<Content> getListByPaperMap(Pager pager, Map<String, Object> map);

	/**
	 *
	 * @param advantageus
	 * @return 
	 * @author liushaomin
	 * 2014-6-13 下午3:13:50
	 */
	int getCountByType(String e);
	
	/**
	 *
	 * @param advantageus
	 * @return 
	 * @author liushaomin
	 * 2014-6-13 下午3:13:50
	 */
	int getCountTopByType(String type);
	
	/**
	 * 批量删除数据
	 * @param ids
	 * @author: ShangJianguo
	 * 2014-6-13 下午5:45:21
	 */
	void deleteByIds(String oids);
	
	/**
	 * 根据类别获取置顶的数据
	 * @param type
	 * @return
	 * @author ShangJianguo
	 * @createtime 2014-6-27 上午11:19:57
	 */
	List<Content> getTopListByType(String type);

	/**
	 *
	 * @param pager
	 * @param searchMap
	 * @return 
	 * @author liushaomin
	 * @createtime 2014-7-1 下午1:36:55
	 */
	List<Content> getSearchList(Pager pager, Map<String, Object> searchMap);

	/**
	 *
	 * @param searchMap
	 * @return 
	 * @author liushaomin
	 * @createtime 2014-7-1 下午1:37:01
	 */
	int getSearchCount(Map<String, Object> searchMap);
	
	/**
	 * 设置是否置顶
	 * @param oid
	 * @param istop
	 * @return
	 * @author ShangJianguo
	 * @createtime 2014-7-25 下午4:30:41
	 */
	boolean setTop(String[] ids, boolean istop);

}
