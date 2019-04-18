package com.supergenius.xo.moral.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.moral.moral.dao.CountDetailDao;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.moral.entity.CountDetail;
import com.supergenius.xo.moral.enums.ECountDetail;
import com.supergenius.xo.moral.service.ArticleSO;
import com.supergenius.xo.moral.service.CountDetailSO;
import com.supergenius.xo.user.entity.User;

/**
 * 点赞、收藏、点击SO实现
 * 
 * @author LiJiacheng
 */
@Service("moralCountDetailSOImpl")
public class CountDetailSOImpl extends BaseSOImpl<CountDetail> implements CountDetailSO {

	@Autowired
	CountDetailDao dao;

	@Autowired
	ArticleSO articleSO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 * 
	 * @author: LiJiacheng 2015-7-23 下午6:06:40
	 */
	@Override
	protected BaseDao<CountDetail> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#updateCount(java.lang.String, java.lang.String, int)
	 * 
	 * @author: LiJiacheng 2015-7-24 下午7:17:13
	 */
	@Override
	public boolean updateCount(String userUid, String articleUid, int count, ECountDetail detail) {
		Map<String, Object> where = new HashMap<>();
		Map<String, Object> fields = new HashMap<>();
		where.put(MapperDict.fromuseruid, userUid);
		where.put(MapperDict.articleuid, articleUid);
		where.put(MapperDict.type, detail);
		fields.put(MapperDict.count, count);
		fields.put(MapperDict.updatetime, DateTime.now().toString());
		return dao.update(where, fields);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#updatePraise(java.lang.String, int, com.supergenius.xo.user.entity.User)
	 * 
	 * @author: LiJiacheng 2015-7-31 下午2:25:24
	 */
	@Override
	public boolean updateArticelPraise(String articleUid, int count, User user) {
		if (-1 == count) {
			CountDetail countDetail = new CountDetail(user.getUid(), articleUid, 1, ECountDetail.praise);
			countDetail.setCreatetime(DateTime.now());
			return dao.insert(countDetail) && articleSO.updateArticlePraise(articleUid, 1);
		} else if (0 == count) {
			return updateCount(user.getUid(), articleUid, 1, ECountDetail.praise) && articleSO.updateArticlePraise(articleUid, 1);
		} else if (1 == count) {
			return updateCount(user.getUid(), articleUid, 0, ECountDetail.praise) && articleSO.updateArticlePraise(articleUid, -1);
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#updateCollect(java.lang.String, int, com.supergenius.xo.user.entity.User)
	 * 
	 * @author: LiJiacheng 2015-7-31 下午2:25:24
	 */
	@Override
	public boolean updateArticelCollect(String articleUid, int count, User user) {
		Map<String, Object> updateMap = new HashMap<>();
		updateMap.put(MapperDict._id, articleUid);
		updateMap.put(MapperDict.updatetime, DateTime.now().toString());
		if (-1 == count) {
			CountDetail countDetail = new CountDetail(user.getUid(), articleUid, 1, ECountDetail.collect);
			countDetail.setCreatetime(DateTime.now());
			return dao.insert(countDetail) && articleSO.updateFields(updateMap);
		} else if (0 == count) {
			return updateCount(user.getUid(), articleUid, 1, ECountDetail.collect) && articleSO.updateFields(updateMap);
		} else if (1 == count) {
			return updateCount(user.getUid(), articleUid, 0, ECountDetail.collect) && articleSO.updateFields(updateMap);
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#getMyCollectedCount(com.supergenius.xo.user.entity.User)
	 * 
	 * @author: LiJiacheng 2015-8-10 下午12:19:21
	 */
	@Override
	public int getMyCollectedCount(User user) {
		Map<String, Object> countMap = new HashMap<>();
		countMap.put(MapperDict.fromuseruid, user.getUid());
		countMap.put(MapperDict.type, ECountDetail.collect);
		countMap.put(MapperDict.count, 1);
		return dao.getCount(countMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#deleteArticleCountDetail(java.lang.String[])
	 * 
	 * @author: LiJiacheng 2015-8-18 下午4:06:50
	 */
	@Override
	public boolean deleteArticleCountDetail(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.articleuid, id);
		dao.deleteByMap(map);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#articleIsTop(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-6 下午2:22:03
	 */
	@Override
	public boolean articleIsTop(String articleUid) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.articleuid, articleUid);
		map.put(MapperDict.type, ECountDetail.top);
		return dao.getOne(map) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.supergenius.xo.moral.service.CountDetailSO#articleTop(java.lang.String)
	 * 
	 * @author: LiJiacheng 2015-9-6 下午2:34:53
	 */
	@Override
	public boolean articleTop(String articleUid) {
		CountDetail countDetail = new CountDetail(null, articleUid, 1, ECountDetail.top);
		countDetail.setCreatetime(DateTime.now());
		return dao.insert(countDetail);
	}

}
