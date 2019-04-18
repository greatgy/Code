package com.supergenius.xo.tpi.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.enums.EArticleChannel;

/**
 * 文章SO
 * 
 * @author ShangJianguo
 */
public interface ArticleSO extends BaseSO<Article>{
	
	/**
	 * 修改状态
	 * @param uids
	 * @param status
	 * @return
	 * @author ShangJianguo
	 */
	public boolean setStatus(String[] uids, EStatus status);
	
	/**
	 * 修改是否置顶
	 * @param uids
	 * @param state
	 * @return
	 * @author ShangJianguo
	 */
	public boolean setTop(String[] uids, boolean state);
	
	/**
	 * 是否公开
	 * @param uids
	 * @param ispublic
	 * @return
	 * @author ShangJianguo
	 */
	public boolean setPublic(String[] uids, boolean ispublic);
	
	/**
	 * 根据类别获取分页数据
	 * @param type
	 * @param pager
	 * @return
	 * @author LiuXiaoke
	 */
	public List<Article> getList(String typename, Pager pager);
	
	/**
	 * 根据类别获取文章数量
	 * @param type
	 * @return
	 * @author LiuXiaoke
	 */
	public int getCount(String typename);
	
	/**
	 * 根据置顶获取置顶的文章
	 * @param pager
	 * @return
	 * @author LiuXiaoke
	 */
	public List<Article> getListByTop(EArticleChannel channel, Pager pager);
	
	/**
	 * 根据类别获取文章列表
	 * @param pager
	 * @return
	 * @author LiuXiaoke
	 */
	public List<Article> getListByType(String typename, Pager pager);
	
	/**
	 * 获取上一篇下一篇数据
	 * @param uid
	 * @return
	 * @author LiuXiaoke
	 */
	public Map<String, Object> getLastNext(String uid);

	/**
	 * channel不同
	 * @param mergecase
	 * @param pager
	 * @return
	 * @author liushaomin
	 */
	public List<Article> getList(EArticleChannel channel, Pager pager);
}
