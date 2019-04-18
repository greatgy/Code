package com.supergenius.web.admin.entrepreneur.hellper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.entrepreneur.entity.Article;
import com.supergenius.xo.entrepreneur.entity.Comments;
import com.supergenius.xo.entrepreneur.enums.EComment;
import com.supergenius.xo.entrepreneur.service.ArticleSO;
import com.supergenius.xo.entrepreneur.service.CatalogueSO;
import com.supergenius.xo.entrepreneur.service.CollectSO;
import com.supergenius.xo.entrepreneur.service.CommentsSO;
import com.supergenius.xo.entrepreneur.entity.Catalogue;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 评论管理HP
 * 
 * @author xuzhixiang
 * @date 2017年9月19日15:07:24
 */
public class EntrepreneurCommentsHP extends BaseHP {

	private static CommentsSO so;
	private static CollectSO collectSO;
	private static ArticleSO articleso;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static VisitorSO visitorSO;

	private static CommentsSO getSO() {
		if (so == null) {
			so = (CommentsSO) spring.getBean(CommentsSO.class);
		}
		return so;
	}

	private static ArticleSO getArticleSO() {
		if (articleso == null) {
			articleso = spring.getBean(ArticleSO.class);
		}
		return articleso;
	}
	
	private static VisitorSO getVisitorSO() {
		if (visitorSO == null) {
			visitorSO = spring.getBean(VisitorSO.class);
		}
		return visitorSO;
	}

	private static CollectSO getCollectSO() {
		if (collectSO == null) {
			collectSO = spring.getBean(CollectSO.class);
		}
		return collectSO;
	}
	
	private static AdminSO getAdminSO() {
		if (adminSO == null) {
			adminSO = spring.getBean(AdminSO.class);
		}
		return adminSO;
	}
	
	private static CatalogueSO getCatalogueSO() {
		if (catalogueSO == null) {
			catalogueSO = spring.getBean(CatalogueSO.class);
		}
		return catalogueSO;
	}
	
	/**
	 * 查询评论时组织数据
	 * 
	 * @return
	 * @author xuzhixiang
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.search))) {
			map.put(ViewKeyDict.fromusername + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			map.put(BaseMapperDict.content + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			Article article = articleso.getOneByField(BaseMapperDict.title + MapperDict.suffix_like_key,
					model.get(ViewKeyDict.search).toString().trim());
			if (article != null) {
				map.put(BaseMapperDict.fromuid, article.getUid());
			} else {
				map.put(BaseMapperDict.fromuid, "");
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			map.put(MapperDict.status, EStatus.get(model.get(ViewKeyDict.status).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimestart))) {
			String startTime = model.get(ViewKeyDict.createtimestart).toString().trim() + MapperDict.starttimeformat;
			map.put(MapperDict.createtimestart, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.createtimeend))) {
			String endTime = model.get(ViewKeyDict.createtimeend).toString().trim() + MapperDict.endtimeformat;
			map.put(MapperDict.createtimeend, endTime);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.type + MapperDict.asc + MapperDict.comma+ MapperDict.createtime + MapperDict.desc);
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.type, EComment.comment);
		List<Comments> list = getSO().search(map);
		for (Comments comments : list) {
			if (StrUtil.isNotEmpty(comments.getAdminuid())) {
				Admin admin = getAdminSO().get(comments.getAdminuid());
				if (admin != null) {
					comments.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(comments.getFromuid())) {
				Article article = getArticleSO().get(comments.getFromuid());
				if (article != null) {
					comments.setTitle(article.getTitle());
				}
			}
			if (StrUtil.isNotEmpty(comments.getCid())) {  
				Catalogue catalogue=getCatalogueSO().get(comments.getCid());
				if (catalogue != null) {
					comments.setCataloguename(catalogue.getName());
				}
			}
			if (comments.getFromuseroid() == 0) {
				comments.setIp(getVisitorSO().get(comments.getFromuseruid()).getLoginip());
			}
		}
		result.put(ViewKeyDict.total, getSO().searchCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}
	
	/**
	 * 获取参与人数的数量
	 * 
	 * @return
	 * @author xuzhixiang
	 */
	public static String getUserCount() {
		int commentsCount = getSO().getCountByfromuseruid();
		int collectCount = getCollectSO().getCount();
		return String.valueOf(commentsCount + collectCount);
	}

}
