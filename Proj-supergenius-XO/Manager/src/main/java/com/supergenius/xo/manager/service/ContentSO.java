package com.supergenius.xo.manager.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.manager.entity.Content;
import com.supergenius.xo.manager.enums.EContent;

/** 
* 内容SO
* @author chenzhixing
*/
public interface ContentSO extends BaseSO<Content> {

	/**
	 * 根据帮助类型得到一个Content对象
	 * @param type
	 * @return
	 */
	Content getOneByType(EContent type);
	
	/**
	 * 更新
	 * @param uid
	 * @param status
	 * @return
	 * @author chenminchang
	 * @create 2016-11-2下午7:52:47
	 */
	boolean updateContent(String uid, String content);
}
