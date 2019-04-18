package com.supergenius.xo.managernews.service;

import java.util.List;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.managernews.entity.Content;
import com.supergenius.xo.managernews.enums.EContent;

/**
 * 内容SO
 * 
 * @author tf
 * @date 2018年7月5日
 */
public interface ContentSO extends BaseSO<Content>{
	/**
	 * 添加内容
	 * 
	 * @author xuzhixiang
	 * @date 2017年8月23日19:47:12
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);
	
	/**
	 * 更新内容
	 * 
	 * @author xuzhixiang
	 * @date 2017年8月24日12:12:59
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);
	
	/**
	 * 冻结或解冻文章
	 * 
	 * @author xuzhixiang
	 * @date 2017年8月24日18:01:20
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);

	/** 
	 * @author 雍雪振
	 * @time 2018年7月9日下午4:19:13
	 * @description:获得广告位List
	 */
	List<Content> getADContentList(EContent content,int cid);
}
