package com.supergenius.xo.moral.service;

import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.Article;
import com.supergenius.xo.user.entity.User;

/**
 * 用户发帖SO
 * 
 * @author liushaomin
 */
public interface ArticleSO extends BaseSO<Article> {

	/**
	 * 修改状态
	 * 
	 * @param eStatus
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean update(EStatus eStatus, String[] ids);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @author liushaomin
	 */
	boolean deleteByUids(String ids);

	/**
	 * 置顶
	 * 
	 * @param ids
	 * @return
	 * @author LiJiacheng
	 */
	boolean setTop(String ids, boolean istop);

	/**
	 * 获取我发布的帖子
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<Article> getMyArticles(String uid, Pager pager);

	/**
	 * 获取我收藏的所有的帖子
	 * 
	 * @param list
	 * @return
	 * @author LiJiacheng
	 */
	List<Article> getMyCollectArticle(User user, Pager pager);

	/**
	 * 获取所有帖子的数量，进行分页
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	int getCount();

	/**
	 * 获取我发布的帖子的总数
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	int getMyArticleCount(User user);

	/**
	 * 分组获取热门帖子
	 * 
	 * @return
	 * @author LiJiacheng
	 */
	List<Map<String, Object>> groupArticles(Pager pager);

	/**
	 * 增加(或减少)帖子的点赞总数
	 * 
	 * @param updown
	 * @return
	 * @author LiJiacheng
	 */
	boolean updateArticlePraise(String articleUid, int updown);

	/**
	 * 添加新的帖子
	 * 
	 * @param article
	 * @param user
	 * @return
	 * @author LiJiacheng
	 */
	boolean addArticle(Article article, User user);

	/**
	 * 得到所有的帖子，并将置顶的帖子和未置顶的帖子分开。
	 * 
	 * @param pager
	 * @return
	 * @author LiJiacheng
	 */
	List<Article> getAllArticles(Pager pager);

	/**
	 * 编辑帖子
	 * 
	 * @param article
	 * @return
	 * @author LiJiacheng
	 */
	boolean editArticle(Article article);

	/**
	 * 获取今天的发帖数量
	 * 
	 * @param userUid
	 * @return
	 * @author LiJiacheng
	 */
	int getTodayArticle(String userUid);

}
