package com.supergenius.xo.tpi.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.dao.BaseDao;
import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.genius.xo.mongodb.MongoDict;
import com.mongodb.BasicDBObject;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.TpiCountDao;
import com.supergenius.xo.tpi.entity.TpiCount;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.service.TpiCountSO;

/**
 * 点击明细so实现
 * @author liushaomin
 */
@Service
public class TpiCountSOImpl extends BaseSOImpl<TpiCount> implements TpiCountSO{

	@Autowired
	TpiCountDao dao;
	
	/* (non-Javadoc)
	 * @see com.genius.xo.base.serviceimpl.BaseSOImpl#getDao()
	 * @author: ShangJianguo
	 * 2015-1-6 下午5:38:15
	 */
	@Override
	protected BaseDao<TpiCount> getDao() {
		
		return dao;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.TpiCountSO#getRankList
	 * @author: LiuXiaoke
	 */
	public List<Map<String, Object>> getRankList(int pagenum, EArticleChannel articlechannel) {
		 Map<String, Object> where = new HashMap<>();
		 where.put(MapperDict.createtime + MapperDict.suffix_greater_key, DateTime.now().minusDays(30));
		 where.put(MapperDict.updatetime + MapperDict.suffix_greater_key, DateTime.now().minusDays(90));
		 if(articlechannel == EArticleChannel.mergecase) {
			 where.put(MapperDict.channel, EChannel.mergecase);
		 }else if(articlechannel == EArticleChannel.mergenews) {
			 where.put(MapperDict.channel, EChannel.mergenews);
		 }
		 Map<String, Object> group = new HashMap<>();
		 group.put(MapperDict.count, new BasicDBObject(MongoDict.$sum, 1));
		 List<String> fields = new ArrayList<>();
		 fields.add(MapperDict.refuid);
		 Map<String, Object> sort = new HashMap<>();
		 sort.put(MapperDict.count, -1);
		 List<Map<String, Object>> list = dao.group(where, fields, group, sort, new Pager(pagenum, 10));
		 return list;
	}
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.WishSO#deletebyrefuid(java.lang.String[])
	 */
	public boolean deleteByRefuid(String[] ids) {
		Map<String, Object> map = getParamMap();
		for (String refuid : ids) {
			map.put(MapperDict.refuid, refuid);
			dao.deleteByMap(map);
		}
		return true;
	}
	
}
