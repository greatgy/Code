package com.supergenius.xo.moral.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Doc;

/**
 * 讲义SO
 * 
 * @author LiJiacheng
 */
public interface DocSO extends BaseSO<Doc> {

	/**
	 * 更新状态
	 * 
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 讲义上移
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean doc_up(String ids);

	/**
	 * 讲义下移
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean doc_down(String ids);

	/**
	 * 讲义下载量加一
	 * @param file
	 * @return
	 * @author YuYingJie
	 */
	boolean updateCount(Doc file);

	/**
	 * 获取List,自定义排序字段
	 * @param pager
	 * @param orderBy
	 * @return
	 * @author YuYingJie
	 */
	List<Doc> getList(Pager pager, String orderBy);
	
}
