package com.supergenius.xo.career.service;

import java.util.List;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.career.entity.Content;
import com.supergenius.xo.career.enums.EContent;

/**
 * 广告位so
 * 
 * @author ChenQi
 * @date 2017年11月13日16:23:10
 */
public interface ContentSO extends BaseSO<Content> {
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

	/**
	 * 获得广告位List
	 * 
	 * @author yangguang
	 * @date 2017年11月15日09:38:23
	 * @return List<Content>
	 */
	List<Content> getADContentList(EStatus eStatus, EContent eContent);
}
