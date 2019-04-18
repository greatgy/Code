package com.supergenius.web.front.life.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genius.core.base.conf.BaseWebConf;
import com.genius.core.base.utils.FreemarkerUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.life.util.ArticleRedisUtil;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.entity.Photo;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EContent;
import com.supergenius.xo.life.service.ContentSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

public class ContentHP extends BaseHP {
	
	private static ContentSO so;

	public static ContentSO getSO() {
		if (so == null) {
			so = (ContentSO) spring.getBean(ContentSO.class);
		}
		return so;
	}

	/**
	 * 处理进入静态页面
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月18日15:26:16
	 */
	public static void organized(Content content, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {// 设置是否赞过
			if (CommentsHP.isPrise(content.getUid(), user.getUid())) {
				content.setIsprize(true);
			}
		} else {
			Visitor visitor = CommentsHP.getVisitor(request, response);
			if (CommentsHP.isPrise(content.getUid(), visitor.getUid())) {
				content.setIsprize(true);
			}
		}
		ArticleHP.incrClickCount(request, response, content.getUid());// 增加计数
		content.setClickcount(ArticleRedisUtil.getInt(content.getUid(), ViewKeyDict.clickcount));
		content.setPrizecount(ArticleRedisUtil.getInt(content.getUid(), ViewKeyDict.prizecount));
		content.setCommentscount(ArticleRedisUtil.getInt(content.getUid(), ViewKeyDict.commentscount));
	}
	
	/**
	 * 得到广告位
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月14日16:59:42
	 * @return List<Content>
	 */
	public static List<Content> getContentList(EContent content, ECatalogue cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, content);
		map.put(MapperDict.cid, cid);
		return getSO().getList(map);
	}
	
	/**
	 * 得到banner
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月17日14:48:35
	 * @return Content
	 */
	@SuppressWarnings("unchecked")
	public static List<Photo> getbannerList(ECatalogue cid) {
		List<Photo> result = new ArrayList<>();
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, EContent.banner);
		map.put(MapperDict.cid, cid);
		List<Content> list = getSO().getList(map);
		for (Content content : list) {
			Photo photo = null;
			Map<String, Map<String, String>> dataMap = JsonUtil.fromJson(content.getData(), Map.class);
			Iterator<String> iter = dataMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				Map<String, String> data = dataMap.get(key);
				photo = createPhoto(key, data);
				photo.setCid(content.getCid());
				result.add(photo);
			}
		}
		return result;
	}
	
	/**
	 * 组织数据
	 * 
	 * @return
	 * @author ChenQi
	 */
	public static Photo createPhoto(String oid, Map<String, String> map) {
		Photo photo = new Photo();
		photo.setOid(Integer.parseInt(oid));
		photo.setTitle(map.get(ViewKeyDict.title));
		photo.setContent(map.get(ViewKeyDict.content));
		photo.setUrl(map.get(ViewKeyDict.url));
		return photo;
	}
	
	/**
	 * 通过cid获取页面content
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日11:13:16
	 * @return List<Content>
	 */
	public static Content getOneContent(EContent content, Long cid) {
		Map<String, Object> map = getParamMap();
		map.put(MapperDict.type, content);
		map.put(MapperDict.cid, cid);
		return getSO().getOne(map);
	}
	
	/**
	 * 通过uid获取页面content
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月21日14:01:40
	 * @return Content
	 */
	public static Content getOneContent(String uid) {
		return getSO().get(uid);
	}
	
	/**
	 * 获取页面content
	 * 
	 * @return
	 * @author YangGuang
	 * @date 2018年5月15日11:13:16
	 * @return List<Content>
	 */
	public static void freemarked(Content content, Map<String, Object> model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String basePath = request.getContextPath();
		map.putAll(BaseWebConf.getBasePath(basePath));
		if (model != null && model.size() != 0) {
			map.putAll(model);
		}
		String code = content.getContent();
		if (StrUtil.isNotEmpty(code)) {
			String contents = FreemarkerUtil.processStr(code, map);
			content.setContent(contents);
		}
	}

}