package com.supergenius.xo.official.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.official.entity.Article;
import com.supergenius.xo.official.enums.EArticleType;

/**
 * 文章SO
 * @author liushaomin
 */
public interface ArticleSO extends BaseSO<Article>{

	/**
	 * 设置置顶
	 * @param ids
	 * @param istop
	 * @return
	 * @author liushaomin
	 */
	boolean setTop(String[] ids, boolean istop);

	/**
	 * 修改状态
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 删除
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean deleteByUids(String[] ids);

	/**
	 * 取置顶
	 * @param bool
	 * @return
	 * @author YuYingJie
	 */
	Article getTop(boolean bool);

	/**
	 * 获取上一条，当前，下一条数据
	 * @param uid
	 * @return
	 * @author YuYingJie
	 */
	List<Article> getLastNext(String uid);
	
	/**
	 * 获取新闻列表
	 * @param e
	 * @param pager
	 * @return
	 * @author YuYingJie
	 */
	List<Article> getList(EArticleType e, Pager pager);

}
