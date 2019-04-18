package com.supergenius.xo.enterpriser.service;

import java.util.List;

import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.enterpriser.entity.Content;
import com.supergenius.xo.enterpriser.enums.ECatalogue;
import com.supergenius.xo.enterpriser.enums.EContent;

/**
 * contentSO
 * @author XieMing
 * @date 2016-10-24 下午4:43:29
 */
public interface ContentSO extends BaseSO<Content>{
	
	/**
	 * 获得广告位List
	 * 
	 * @author yangguang
	 * @date 2017年11月15日09:38:23
	 * @return List<Content>
	 */
	List<Content> getADContentList(ECatalogue catalogue);

	/**
	 * 根据类型获取内容
	 * @param type
	 * @return
	 * @author XieMing
	 * 2016-10-25 上午11:57:51
	 */
	Content getOneByType(EContent type);
	
	/**
	 * 根据类型获取数量
	 * @param type
	 * @return
	 * @author liubin
	 * @createtime 2016-10-28下午4:46:35
	 * @return int
	 */
	int getCount(EContent type);

	/**
	 * 添加内容
	 * 
	 * @author loupengyu
	 * @date 2018年1月30日11:20:13
	 * @return boolean
	 */
	boolean add(Content content, AdminLog adminLog);

	/**
	 * 更新内容
	 * 
	 * @author loupengyu
	 * @date 2018年1月30日11:20:14
	 * @return boolean
	 */
	boolean update(Content content, AdminLog adminLog);

	/**
	 * 冻结或解冻
	 * 
	 * @author loupengyu
	 * @date 2018年1月30日11:20:20
	 * @return boolean
	 */
	boolean updateStatusByUid(String uid);
	
}
