package com.supergenius.xo.moral.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Userdoc;

/**
 * 用户上传SO
 * 
 * @author LiJiacheng
 */
public interface UserdocSO extends BaseSO<Userdoc> {
	
	/**
	 * 修改状态
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 增加下载量
	 * @param file
	 * @return
	 * @author YuYingJie
	 */
	boolean updateCount(Userdoc file);

	/**
	 * 用户上传文档分组排序
	 * @param pager
	 * @author YuYingJie
	 */
	List<Map<String, Object>> group(Pager pager);

	/**
	 * 通过useruid获取list
	 * @param useruid
	 * @author YuYingJie
	 */
	List<Userdoc> getList(String useruid);
	
	/**
	 * 获取List,自定义排序字段
	 * @param pager
	 * @param orderBy
	 * @return
	 * @author YuYingJie
	 */
	List<Userdoc> getList(Pager pager, String orderBy);

}
