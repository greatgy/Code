package com.supergenius.xo.finance.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.dao.ArticleDao;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.service.ArticleSO;

/**
 * 文章SO实现
 * 
 * @author XueZhenYong
 * @date 2017年12月29日下午3:26:31
 */
@Service("financeArticleSOImpl")
public class ArticleSOImpl extends BaseSOImpl<Article> implements ArticleSO {

	@Autowired
	ArticleDao dao;

	@Override
	protected BaseDao<Article> getDao() {
		return dao;
	}

	@Override
	public List<Article> getBannerList(Pager pager, boolean istop, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.istop, istop);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}

	@Override
	public List<Article> getRecommendList(Pager pager, boolean isrecommend, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.isrecommend, isrecommend);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}

	@Override
	public List<Article> getListByCid(Integer cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EStatus.enable);
		if (cid == 0) { // 0表示首页
			return dao.getList(map);
		}
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getRelatecarticleList(Pager pager, String useruid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.useruid, useruid);
		map.put(MapperDict.type, true);
		return dao.getList(map);
	}

	@Override
	public List<Article> getFirstArticle(Pager pager, int cid) {
		Map<String, Object> map = getParamMap(pager);
		map.put(MapperDict.type, EStatus.enable);
		map.put(MapperDict.cid, cid);
		return dao.getList(map);
	}
	
	@Override
	public List<Article> getCollectList(String useruid, Pager pager) {
		Map<String, Object> map;
		if (pager != null) {
			map = getParamMap(pager);
		} else {
			map = getParamMap();
		}
		map.put(MapperDict.useruid, useruid);
		return dao.getCollectList(map);
	}
	
	@Override
	public DateTime getCacheTime() {
		Map<String, Object> map = getParamMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Date date = dao.getCacheTime(map);
		if (date != null) {
			String dateTime = df.format(date);
			if (StrUtil.isNotEmpty(dateTime)) {
				return DateUtil.parse(dateTime);
			}
		}
		return null;
	}
	
	@Override
	public boolean updateFields(Map<String, Object> map){
		return dao.updateFields(map);
	}
}
