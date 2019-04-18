package com.supergenius.xo.tpi.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.model.base.entity.Pager;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.dao.ArticleDao;
import com.supergenius.xo.tpi.dao.ProjectDao;
import com.supergenius.xo.tpi.dao.TeamDao;
import com.supergenius.xo.tpi.dao.TpiUserDao;
import com.supergenius.xo.tpi.entity.Article;
import com.supergenius.xo.tpi.entity.Project;
import com.supergenius.xo.tpi.entity.Team;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EArticleChannel;
import com.supergenius.xo.tpi.enums.EWishType;
import com.supergenius.xo.tpi.service.SearchSO;
import com.supergenius.xo.tpi.service.TypeSO;
import com.supergenius.xo.tpi.service.WishSO;

/**
 * 搜索的实现类
 * @author ShangJianguo
 */
@Service(value = "tpisearchso")
public class SearchSOImpl implements SearchSO{

	@Autowired
	WishSO wishSO;
	@Autowired
	TypeSO typeSO;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	TpiUserDao tpiUserDao;
	@Autowired
	TeamDao teamDao;
	@Autowired
	ArticleDao articleDao;
	
	/* (non-Javadoc)
	 * @see com.supergenius.xo.tpi.service.SearchSO#search(java.lang.String, java.lang.String)
	 * @author: ShangJianguo
	 * 2015-2-8 下午2:46:10
	 */
	@Override
	public List<Object> search(String keyword, EChannel channel, Pager pager) {
		List<Object> list = new ArrayList<>();
		if (EChannel.project == channel) {
			List<Project> data = projectDao.search(keyword, pager);
			for (Project item : data) {
				item.setWantinum(wishSO.getCount(null, item.getUid(), EWishType.wantInvestment, EChannel.project));
				item.setWantmnum(wishSO.getCount(null, item.getUid(), EWishType.wantMergers, EChannel.project));
				item.setCountnum(wishSO.getCount(null, item.getUid(), EWishType.attention, EChannel.project));
				item.setTypeName(typeSO.get(item.getTypeuid()).getName());
				list.add(item);
			}
		} else if (EChannel.tpiuser == channel) {
			List<TpiUser> data = tpiUserDao.search(keyword, pager);
			for (TpiUser item : data) {
				list.add(item);
			}
		} else if (EChannel.team == channel) {
			List<Team> data = teamDao.search(keyword, pager);
			for (Team item : data) {
				list.add(item);
			}
		} else if (EChannel.mergecase == channel) {
			List<Article> data = articleDao.search(keyword, pager, EArticleChannel.mergecase);
			for (Article item : data) {
				list.add(item);
			}
		} else if (EChannel.mergenews == channel) {
			List<Article> data = articleDao.search(keyword, pager, EArticleChannel.mergenews);
			for (Article item : data) {
				list.add(item);
			}
		}
		return list;
	}

}
