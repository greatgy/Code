package com.supergenius.web.admin.life.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.baseadmin.service.AdminSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.user.service.UserSO;

/**
 * 话题管理HP
 * 
 * @author JiaShitao
 * @date 2018年5月10日18:22:16
 */
public class LifeTopicHP extends BaseHP {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(StartupArticleHP.class);

	private static TopicSO so;
	private static AdminSO adminSO;
	private static CatalogueSO catalogueSO;
	private static UserSO userSO;

	private static TopicSO getSO() {
		if (so == null) {
			so = (TopicSO) spring.getBean(TopicSO.class);
		}
		return so;
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

	public static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}

	/**
	 * 组织查询语句
	 * 
	 * @param model
	 * @return map
	 * @author JiaShitao
	 */
	public static Map<String, Object> query(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.name))) {
			map.put(MapperDict.keywords + MapperDict.suffix_like_key, model.get(BaseMapperDict.name).toString().trim());
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.status))) {
			if (Integer.valueOf(model.get(ViewKeyDict.status).toString()) == 10) {
				map.put(MapperDict.createtime_gt, new DateTime(DateTimeZone.forOffsetHours(8)));
			} else if (Integer.valueOf(model.get(ViewKeyDict.status).toString()) == 12) {
				map.put(MapperDict.examine, Integer.valueOf(EStatus.disable.toString()));
			} else {
				map.put(MapperDict.status, model.get(ViewKeyDict.status).toString().trim());
			}
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.cid))) {
			map.put(MapperDict.cid, model.get(ViewKeyDict.cid));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.istop))) {
			map.put(MapperDict.istop, model.get(ViewKeyDict.istop));
		}
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		liststatus.add(EStatus.wait);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.orderBy, MapperDict.sql_order_by + MapperDict.cid + MapperDict.asc + MapperDict.comma + MapperDict.examine + MapperDict.asc + MapperDict.comma + MapperDict.status + MapperDict.desc + MapperDict.comma + MapperDict.createtime + MapperDict.desc);
		List<Topic> list = getSO().getList(map);
		for (Topic topics : list) {
			if (StrUtil.isNotEmpty(topics.getAdminuid())) {
				Admin admin = getAdminSO().get(topics.getAdminuid());
				if (admin != null) {
					topics.setAdminname(admin.getName());
				}
			}
			if (StrUtil.isNotEmpty(topics.getCid())) {
				Catalogue catalogue = getCatalogueSO().getOneByCid(topics.getCid());
				if (catalogue != null) {
					topics.setCataloguename(catalogue.getName());
				}
			}
		}
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, list);
		return result;
	}

	/**
	 * 根据channel获得cid
	 * 
	 * @param channel
	 * @return
	 * @author loupengyu
	 * @date 2018年1月11日19:23:21
	 */
	public static long getcid(String channel) {
		int cid = 1;
		switch (channel) {
		case "upmadeinchina":
			cid = 3;
			break;
		case "industryfund":
			cid = 6;
			break;
		case "crossmerger":
			cid = 4;
			break;
		case "internationalmerger":
			cid = 5;
			break;
		default:
			break;
		}
		return cid;
	}

	/**
	 * 获取置顶的文章数量(排除被冻结的模块文章)
	 * 
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月14日20:00:50
	 */
	public static int getTopTopicCount(Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.istop, 1); // 表示置顶
		map.put(MapperDict.cid, cid);
		List<Topic> list = getSO().getList(map); // 得到置顶的文章
		return getNewList(list).size();
	}

	/**
	 * 对传入的List进行处理，排除属于冻结模块中的文章
	 * 
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月14日20:00:43
	 */
	public static List<Topic> getNewList(List<Topic> list) {
		List<Topic> newList = new ArrayList<>();
		Boolean flag = false; // 定义标志位，用于判断是否存在冻结模块的文章
		for (Topic topic : list) {
			// isMemeber(topic);
			flag = false;
			List<Catalogue> cataloguelist = getStatusList(EStatus.disable);
			for (Catalogue catalogue : cataloguelist) {
				if ((topic.getCid() & catalogue.getCid()) == catalogue.getCid()) { // 当文章在被冻结的模块中~
					flag = true;
					break;
				}
			}
			if (!flag) { // 当文章在未被冻结的模块中~
				if (StrUtil.isNotEmpty(topic.getAdminuid())) {
					Admin admin = getAdminSO().get(topic.getAdminuid());
					if (admin != null) {
						topic.setAdminname(admin.getName());
					}
				}
				newList.add(topic);
			}
		}
		return newList;
	}

	/**
	 * 判断是否是会员
	 * 
	 * @param content
	 * @return
	 * @author ChenQi
	 */
	/*
	 * public static void isMemeber(Article article) { if (article.getUseruid() ==
	 * article.getAdminuid()) { article.setIsmember(1); } else { User user =
	 * getUserSO().get(article.getUseruid()); if (user == null) {
	 * article.setIsmember(0); } else { article.setIsmember(1); } } }
	 */

	/**
	 * 获取catalogue所有指定状态的子模块
	 * 
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月14日20:00:47
	 */
	public static List<Catalogue> getStatusList(EStatus status) {
		List<Catalogue> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, status);
		list = getCatalogueSO().getList(map);
		if (list != null) {
			return list;
		}
		return null;
	}

	/**
	 * 获取catalogue所有指定状态的子模块
	 * 
	 * @return
	 * @author JiaShitao
	 * @date 2018年5月14日19:23:22
	 */
	public static List<Catalogue> getCatalogueList() {
		Map<String, Object> map = getParamMap();
		List<Integer> list = new ArrayList<Integer>();
		list.add(42);
		list.add(51);
		map.put(MapperDict.cids, list);
		List<Catalogue> catelogueList = getCatalogueSO().getList(map);
		return catelogueList;
	}

	/**
	 * 删除序列化的话题
	 * 
	 * @return
	 * @author YangGuang
	 */
	public static void deleteTopic() {
		FileUtil.delete(SysConf.SerialBasePath + SysConf.SerialTopicsPath);
	};
}
