package com.supergenius.web.finance.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.SubscribeSO;

/**
 * 文章HP
 * 
 * @author loupengyu
 * @date 2018年1月3日17:33:00
 */
public class UhomeHP extends BaseHP {

	private static ArticleSO so;
	private static SubscribeSO subscribeSO;
	
	public static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}
	
	public static SubscribeSO getSubscribeSO() {
		if (subscribeSO == null) {
			subscribeSO = (SubscribeSO) spring.getBean(SubscribeSO.class);
		}
		return subscribeSO;
	}

	/**
	 * 获取他的主页文章
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return List<Article>
	 * @author loupengyu
	 * @date 2018年1月3日17:33:42
	 */
	public static List<Article> getArticleList(String uid, int pagenum, int pagesize) {
		if (pagenum <= 0) {
			pagenum = 1;
		}
		Map<String, Object> map = getParamMap(Pager.getNewInstance(pagenum, pagesize));
		map.put(MapperDict.authoruid, uid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		for (Article article : list) {
			if (article != null) {
				IndexHP.setSummary(article);
			}
		}
		IndexHP.initArticleList(list);
		return list;
	}
	
	/**
	 * 他的文章数量
	 * 
	 * @param uid
	 * @return
	 * @author loupengyu
	 */
	public static int getHisCount(String uid) {
		Map<String, Object> map = getParamMap();
		map.put(ViewKeyDict.authoruid, uid);
		map.put(ViewKeyDict.type, EStatus.enable);
		return getSO().getCount(map);
	}
	
	/**
	 * 获取他的主页热门文章
	 * 
	 * @param u
	 * @param pagenum
	 * @param pagesize
	 * @param model
	 * @param request
	 * @return
	 * @author loupengyu
	 * @date 2018年1月3日17:58:44
	 */
	public static List<Article> gethisHotArticle(String uid) {
		List<Article> result = new ArrayList<>();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.authoruid, uid);
		map.put(MapperDict.type, EStatus.enable);
		List<Article> list = getSO().getList(map);
		IndexHP.initArticleList(list);
		list.sort(COMPARATOR);
		for (Article article : list) {
			result.add(article);
			if (result.size() >= SysConf.HisArticleSize) {
				break;
			}
		}
		return result;
	}
	
	/**
	 * 获得订阅
	 * 
	 * @param useruid
	 * @param refuseruid
	 * @return
	 * @author loupengyu
	 * @date 2018年1月6日14:10:35
	 */
	public static Subscribe getSubscribe(String useruid, String refuseruid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.refuseruid, refuseruid);
		return getSubscribeSO().getOne(map);
	}

	/**
	 * 比较规则
	 * @param useruid
	 * @param refuseruid
	 * @return
	 * @author loupengyu
	 * @date 2018年1月6日14:10:35
	 */
	private static final Comparator<Article> COMPARATOR = new Comparator<Article>() {
		public int compare(Article o1, Article o2) {
			return o1.compareTo(o2);// 运用Article类的compareTo方法比较两个对象
		}
	};
}
