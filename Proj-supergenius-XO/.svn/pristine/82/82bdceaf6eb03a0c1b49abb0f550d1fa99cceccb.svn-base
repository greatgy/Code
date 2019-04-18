package com.supergenius.xo.startup.service;

import java.util.List;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.startup.entity.Content;
import com.supergenius.xo.startup.enums.EContent;

/**
 * 评论so
 * 
 * @author ChenQi
 * @date 2017-8-23 11:13:45
 */
public interface ContentSO extends BaseSO<Content> {
	/**
	 * 添加内容
	 * 
	 * @author ChenQi
	 * @date 2017年8月23日19:47:12
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);

	/**
	 * 更新内容
	 * 
	 * @author ChenQi
	 * @date 2017年8月24日12:12:59
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);

	/**
	 * 冻结或解冻文章
	 * 
	 * @author ChenQi
	 * @date 2017年8月24日18:01:20
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);

	/**
	 * 获得广告位List
	 * 
	 * @author ChenQi
	 * @date 2017年9月7日17:10:40
	 * @return List<Content>
	 */
	List<Content> getADContentList(EStatus eStatus, EContent eContent);
}
