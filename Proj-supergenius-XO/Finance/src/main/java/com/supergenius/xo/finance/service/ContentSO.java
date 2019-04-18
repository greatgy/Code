package com.supergenius.xo.finance.service;

import java.util.List;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.finance.entity.Content;
import com.supergenius.xo.finance.enums.EContent;

/**
 * 内容SO
 * @author XueZhenYong
 * @date 2017年12月29日下午3:23:49
 */
public interface ContentSO extends BaseSO<Content>{

	/**
	 * 获得广告位List
	 * 
	 * @author yangguang
	 * @date 2017年11月15日09:38:23
	 * @return List<Content>
	 */
	List<Content> getADContentList(EContent eContent);
	
	/**
	 * 添加内容
	 * 
	 * @author yangguang
	 * @date 2017年11月15日09:38:45
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);

	/**
	 * 更新内容
	 * 
	 * @author yangguang
	 * @date 2017年11月15日09:38:35
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);

	/**
	 * 冻结或解冻
	 * 
	 * @author yangguang
	 * @date 2017年11月15日09:38:09
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);
}
