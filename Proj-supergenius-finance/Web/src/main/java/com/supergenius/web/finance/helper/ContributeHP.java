package com.supergenius.web.finance.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.CookieUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.NetUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.search.engine.SearchEngine;
import com.genius.model.base.enums.EStatus;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Label;
import com.supergenius.xo.finance.rule.FinanceArticleRlue;
import com.supergenius.xo.finance.rule.FinanceFirstArticleRule;
import com.supergenius.xo.finance.rule.FinanceTopArticleRule;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.LabelSO;
import com.supergenius.xo.user.entity.Visitor;
import com.supergenius.xo.user.service.VisitorSO;

/**
 * 投稿HP
 * 
 * @author liushaomin
 */
public class ContributeHP extends BaseHP {

	private static ArticleSO so;

	private static VisitorSO visitorSO;

	private static SearchEngine engine;
	
	private static LabelSO labelso;

	private static LabelSO getLabelSO() {
		if (labelso == null) {
			labelso = (LabelSO) spring.getBean(LabelSO.class);
		}
		return labelso;
	}

	public static ArticleSO getSO() {
		if (so == null) {
			so = (ArticleSO) spring.getBean(ArticleSO.class);
		}
		return so;
	}

	private static VisitorSO getVisitorSO() {
		if (visitorSO == null) {
			visitorSO = (VisitorSO) spring.getBean(VisitorSO.class);
		}
		return visitorSO;
	}

	public static SearchEngine getEngine() {
		if (engine == null) {
			engine = (SearchEngine) spring.getBean("financeEngine");
		}
		return engine;
	}

	/**
	 * 处理上传的图片
	 * 
	 * @param fileimg
	 * @param string
	 * @param imgshowsizes
	 * @param imgUploadBasePath
	 * @author liushaomin
	 * @return
	 */
	public static String resizeImage(MultipartFile fileimg, String path, int[][] imgsizes, String imgUploadBasePath) {
		String[] paths = FileUtil.resizeImage(fileimg, path, imgsizes, imgUploadBasePath);
		String data = null;
		if (path.length() != 0) {
			data = paths[0] + BaseStrDict.comma + paths[1] + BaseStrDict.comma + paths[2] + BaseStrDict.comma + paths[3];
		}
		return data;
	}

	/**
	 * 获取游客
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author yangguang
	 */
	public static Visitor getVisitor(HttpServletRequest request, HttpServletResponse response) {
		String visitorUid = CookieUtil.get(request, ViewKeyDict.visitors);
		Visitor visitor;
		if (StringUtils.isNotEmpty(visitorUid)) {
			visitor = getVisitorSO().get(visitorUid);
			if (visitor != null) {
				return visitor;
			}
		}
		String ip = NetUtil.getIPAddr(request);
		visitor = new Visitor();
		visitor.setLoginip(ip);
		visitor.setCreatetime(new DateTime());
		Boolean bool = getVisitorSO().add(visitor);
		if (bool) {
			CookieUtil.addCookie(response, ViewKeyDict.visitors, visitor.getUid(), Integer.MAX_VALUE);
		}
		return visitor;
	}

	/**
	 * 添加标签
	 * 
	 * @param uid
	 * @param content
	 * @return
	 * @author yangguang
	 */
	public static void add(String uid, String content) {
		String[] labels = content.split("\\s+");
		for (String labeli : labels) {
			if (StrUtil.isEmpty(labeli)) {
				continue;
			}
			Label labeltmp = isExist(labeli);
			if (labeltmp != null) {
				labeltmp.setRefuid(labeltmp.getRefuid() + ViewKeyDict.comma + uid);
				labeltmp.setCount(labeltmp.getCount() + SysConf.RelateArticle);
				getLabelSO().update(labeltmp);
				continue;
			}
			Label label = new Label();
			label.setContent(labeli);
			label.setCount(SysConf.RelateArticle);
			label.setRefuid(uid);
			getLabelSO().add(label);
			addTags(label);
		}
	}
	
	/**
	 * 判断标签是否已经存在
	 * 
	 * @param content
	 * @return
	 * @author yangguang
	 */
	public static Label isExist(String content) {
		List<Label> list = getLabelSO().getList();
		if (StrUtil.isEmpty(content)) {
			return null;
		}
		for (Label item : list) {
			if (content.equals(item.getContent())) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 给文章添加标签
	 * 
	 * @param article
	 * @return
	 * @author yangguang
	 * @date 2017年8月31日17:56:23
	 */
	public static Boolean addTags(Article article) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.status, EStatus.enable);
		List<Label> list = getLabelSO().getList(map); // 得到所有标签
		if (list != null) {
			String articleContent = article.getContent() + article.getTitle();
			String labelContent;
			for (Label label : list) {
				labelContent = label.getContent();
				if (StrUtil.isNotEmpty(labelContent) & StrUtil.isNotEmpty(articleContent) & articleContent.indexOf(label.getContent()) != -1) {
					label.setRefuid(label.getRefuid() + ViewKeyDict.comma + article.getUid());
					label.setCount(label.getCount() + SysConf.RelateArticle);
					if (!getLabelSO().update(label)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 添加或解冻标签时对已有文章进行匹配
	 * 
	 * @param label
	 * @return
	 * @author yangguang
	 * @date 2017年9月6日11:26:59
	 */
	public static void addTags(Label label) {
		Map<String, Object> map = getParamMap();
		List<Article> list = getSO().getList(map);
		String labelContent;
		for (Article item : list) {
			String articleContent = item.getContent() + item.getTitle();
			if (articleContent.indexOf(label.getContent()) != -1) {
				labelContent = label.getContent();
				if (StrUtil.isNotEmpty(label.getRefuid())) {
					if (StrUtil.isNotEmpty(labelContent) & StrUtil.isNotEmpty(articleContent) & label.getRefuid().indexOf(item.getUid()) == -1) {
						label.setRefuid(label.getRefuid() + ViewKeyDict.comma + item.getUid());
					}
				} else {
					label.setRefuid(label.getRefuid() + ViewKeyDict.comma + item.getUid());
				}
				label.setCount(label.getCount() + SysConf.RelateArticle);
				getLabelSO().update(label);
			}
		}
	}

	/**
	 * 发布文章时处理缓存
	 * 
	 * @param article
	 * @author yangguang
	 * @return
	 */
	public static void Cache(Article article) {
		Rule bannerrule = new FinanceTopArticleRule(String.valueOf(article.getCid()));// 模块的轮播缓存
		MemcacheUtil.remove(bannerrule);
		Rule Firstrule = new FinanceFirstArticleRule();// 首页左侧缓存
		MemcacheUtil.remove(Firstrule);
		Rule cidrule = new FinanceArticleRlue(String.valueOf(article.getCid()));// 模块下的所有文章
		MemcacheUtil.remove(cidrule);
		Rule allrule = new FinanceArticleRlue(String.valueOf(0));// 所有文章的缓存
		MemcacheUtil.remove(allrule);
	}

}
