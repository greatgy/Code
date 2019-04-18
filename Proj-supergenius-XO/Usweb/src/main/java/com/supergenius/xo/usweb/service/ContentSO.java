package com.supergenius.xo.usweb.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.usweb.entity.Content;

/**
 * 内容SO
 *
 * @author Yong
 * @date 2018年12月07日11:04:55
 */
public interface ContentSO extends BaseSO<Content>{
	/**
	 * 添加内容
	 * 
	 * @author tf
	 * @date 2017年9月19日
	 * @return boolean
	 */
	boolean add(Content content);
	
	/**
	 * 更新内容
	 * 
	 * @author tf
	 * @date 2017年9月19日
	 * @return boolean
	 */
	boolean update(Content content);
	/**
	 * 通过内容名称来获取内容
	 * @author guoning
	 * @date 2018年12月11日
	 * @return Content
	 */
	Content getContentByName(String name);
}
