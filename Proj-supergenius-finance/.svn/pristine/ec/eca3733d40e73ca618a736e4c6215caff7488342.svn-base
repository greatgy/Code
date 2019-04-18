package com.supergenius.___.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.RedisUtil;
import com.supergenius.core.rule.ClickCountArticleRule;
import com.supergenius.core.rule.CommentCountArticleRule;
import com.supergenius.core.rule.PrizeCountArticleRule;
import com.supergenius.core.rule.PrizeCountCommentRule;
import com.supergenius.server.finance.util.ArticleRedisUtil;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Comments;
import com.supergenius.xo.finance.rule.FinanceSecondCommentCountRule;
import com.supergenius.xo.finance.rule.PrizeCountCommentsFinanceRule;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CollectSO;
import com.supergenius.xo.finance.service.CommentsSO;

/**
 * 目录模块测试类
 * 
 * @author 杨光
 * @date 2017年9月19日10:08:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/applicationContext**.xml" })
@Ignore
public class InsertDataTest {

	@Autowired
	public ArticleSO so;
	@Autowired
	public CommentsSO commentsso;
	@Autowired
	public CollectSO collectSO;


	/**
	 * Test Get()
	 */
	@Test
	@Ignore
	public void testGet() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		int clickcount = 0;
		int commentcount = 0;
		int prizecount = 0;
		int collectcount= 0;
		List<Article> list = so.getList(map);
		for (Article item : list) {
			Rule clickrule = new ClickCountArticleRule(item.getUid());
			Rule commentrule = new CommentCountArticleRule(item.getUid());
			Rule prizerule = new PrizeCountArticleRule(item.getUid());
			clickcount = (int) (RedisUtil.getIncr(clickrule) > 0 ? RedisUtil.getIncr(clickrule) : 0);
			commentcount = (int) (RedisUtil.getIncr(commentrule) > 0 ? RedisUtil.getIncr(commentrule) : 0);
			prizecount = (int) (RedisUtil.getIncr(prizerule) > 0 ? RedisUtil.getIncr(prizerule) : 0);
			ArticleRedisUtil.incr(item.getUid(), "clickcount", clickcount);
			ArticleRedisUtil.incr(item.getUid(), "commentscount", commentcount);
			ArticleRedisUtil.incr(item.getUid(), "prizecount", prizecount);
			
			map1.put(MapperDict.refuid, item.getUid());
			collectcount = collectSO.getCount(map1);
			ArticleRedisUtil.incr(item.getUid(), "collectcount", collectcount);
		}
	}

	/**
	 * Test Get()
	 */
	@Test
	@Ignore
	public void testInitComments() {
		Map<String, Object> map = new HashMap<>();
		int prizecount = 0;
		List<Comments> list = commentsso.getList(map);
		for (Comments item : list) {
			Rule prizerule = new PrizeCountCommentRule(item.getUid());
			prizecount = (int) (RedisUtil.getIncr(prizerule) > 0 ? RedisUtil.getIncr(prizerule) : 0);
			Rule newprizerule = new PrizeCountCommentsFinanceRule(item.getUid());// 指被赞的评论uid
			RedisUtil.setIncr(newprizerule, prizecount);
			if (StrUtil.isEmpty(item.getTouid())) {
				Map<String, Object> map1 = new HashMap<>();
				map1.put("status", 1);
				map1.put("touid", item.getUid());
				int commentcount = commentsso.getCount(map1);
				Rule newcommentrule = new FinanceSecondCommentCountRule(item.getUid());// 指被赞的评论uid
				RedisUtil.setIncr(newcommentrule, commentcount);
			}
		}
	}
}
