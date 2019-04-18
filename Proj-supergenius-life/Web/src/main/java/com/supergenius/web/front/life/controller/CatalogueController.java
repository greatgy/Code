package com.supergenius.web.front.life.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.ArticleHP;
import com.supergenius.web.front.life.helper.ContentHP;
import com.supergenius.web.front.life.helper.EssayHP;
import com.supergenius.web.front.life.helper.IndexHP;
import com.supergenius.web.front.life.helper.ProblemHP;
import com.supergenius.web.front.life.helper.TopicHP;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Content;
import com.supergenius.xo.life.entity.Essay;
import com.supergenius.xo.life.entity.Photo;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EContent;
import com.supergenius.xo.life.service.CatalogueSO;
import com.supergenius.xo.user.entity.User;

/**
 * 目录controller
 * 
 * @author YangGuang
 * @date 2018年5月14日18:31:35
 */
@Controller
public class CatalogueController extends BaseController {

	@Autowired
	CatalogueSO so;

	/**
	 * 跳转到相应的页面并对相应的页面展示相应的数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author YangGuang
	 * @Datetime 2018年5月15日10:58:39
	 */
	@RequestMapping(value = "/catalogue/{pcid:\\w+}/{cid:\\w+}", method = RequestMethod.GET)
	private String index(Map<String, Object> model, @PathVariable Long cid, @PathVariable Long pcid, HttpServletRequest request, HttpServletResponse response) {
		model.put(ViewKeyDict.cid, cid);
		model.put(ViewKeyDict.pcid, pcid);
		model.put(ViewKeyDict.catalogue, so.getOneByCid(cid));
		// 会员通道
		if (ECatalogue.get(cid) == ECatalogue.member) {
			return redirectPrefix + "/member";
		}
		// 首页
		if (ECatalogue.get(cid) == ECatalogue.index) {
			return redirectPrefix + "/index";
		}
		// 效果检验
		if (ECatalogue.get(cid) == ECatalogue.test || ECatalogue.get(cid) == ECatalogue.stage) {
			return redirectPrefix + "/video/" + cid;
		}
		// 课程推荐
		if (ECatalogue.get(cid) == ECatalogue.course) {
			return redirectPrefix + "/course/" + cid;
		}
		// 资料交流 人生问答
		if (ECatalogue.get(cid) == ECatalogue.material || ECatalogue.get(cid) == ECatalogue.answer) {
			return redirectPrefix + "/problem/" + cid + "_0";
		}

		// 交流对话 守望初心 我身边的偶像 我崇拜的偶像 我幻想的偶像 人生推荐
		if (ECatalogue.get(cid) == ECatalogue.communication || ECatalogue.get(cid) == ECatalogue.watching || ECatalogue.get(cid) == ECatalogue.side || ECatalogue.get(cid) == ECatalogue.recommendation
				|| ECatalogue.get(cid) == ECatalogue.worship || ECatalogue.get(cid) == ECatalogue.fantasy) {
			model.put(ViewKeyDict.userlist, EssayHP.getUserForToday(cid));
			return "conversation";
		}

		// 我的理想
		if (ECatalogue.get(cid) == ECatalogue.ideal) {
			List<Essay> list = new ArrayList<>();
			User user = BaseUserHP.getCurrUser(request);
			list = EssayHP.getEssayHot(1, SysConf.EssaySize, cid);
			EssayHP.organized(list);
			EssayHP.setprise(list, user, request, response);
			model.put(ViewKeyDict.list, list);
			return "mydream";
		}

		// 火眼金睛
		if (ECatalogue.get(cid) == ECatalogue.insight) {
			return "insight";
		}

		// 思维拓展
		if (ECatalogue.get(cid) == ECatalogue.thinking) {
			Topic bean = TopicHP.getlastTopic(cid);
			model.put(ViewKeyDict.bean, bean);
			return redirectPrefix + "/topic/" + bean.getCid() + BaseStrDict.slash + bean.getOid();
		}

		// 人生宗旨、人生定位、人生路径
		if (ECatalogue.get(cid) == ECatalogue.purpose || ECatalogue.get(cid) == ECatalogue.Location || ECatalogue.get(cid) == ECatalogue.path) {
			Content content = ContentHP.getOneContent(EContent.html, cid);
			ContentHP.organized(content, model, request, response);
			ContentHP.freemarked(content, model, request);
			model.put(ViewKeyDict.bean, content);
			return "position";
		}

		// 行万里路
		if (ECatalogue.get(cid) == ECatalogue.travel) {
			ArticleHP.OrganizedCity(model,cid);
			return "travel";
		}

		IndexHP.handleCache(cid);
		// 家长责任
		if (ECatalogue.get(cid) == ECatalogue.duty) {
			Content studycontent = ContentHP.getOneContent(ViewKeyDict.studyid);
			Content content = ContentHP.getOneContent(ViewKeyDict.dutyid);
			List<Photo> bannerList = ContentHP.getbannerList(ECatalogue.get(cid));
			List<Article> articleList = ArticleHP.getOtherArticles(SysConf.FirstArticleSize, cid);
			ContentHP.organized(content, model, request, response);
			ContentHP.freemarked(content, model, request);
			model.put(ViewKeyDict.bean, content);
			model.put(ViewKeyDict.self_study, studycontent);
			model.put(ViewKeyDict.bannerList, bannerList);
			model.put(ViewKeyDict.list, articleList);
			return "duty";
		}

		List<Article> articleList = ArticleHP.getOtherArticles(SysConf.FirstArticleSize, cid);// 文章
		List<Content> adList = ContentHP.getContentList(EContent.ad, ECatalogue.get(cid));// 广告位
		model.put(ViewKeyDict.list, articleList);
		model.put(ViewKeyDict.adList, adList);
		model.put(ViewKeyDict.pagesize, SysConf.LoadSize);
		model.put(ViewKeyDict.FirstLoadSize, SysConf.FirstArticleSize);
		// 留学指南、留学风向、留学天地、专业匹配、大学优选
		if (ECatalogue.get(cid) == ECatalogue.abroadguide || ECatalogue.get(cid) == ECatalogue.abroadtrend || ECatalogue.get(cid) == ECatalogue.abroadworld || ECatalogue.get(cid) == ECatalogue.major
				|| ECatalogue.get(cid) == ECatalogue.university) {
			return "abroad";
		}

		List<Article> hotArticleList = ArticleHP.getHotArticles(SysConf.HotArticleSize, cid);
		model.put(ViewKeyDict.hotArticleList, hotArticleList);
		// 课程见解、水煮历史、去伪存真、梦想成真、理想变迁、携手前行
		if (ECatalogue.get(cid) == ECatalogue.perspectives || ECatalogue.get(cid) == ECatalogue.history || ECatalogue.get(cid) == ECatalogue.technology || ECatalogue.get(cid) == ECatalogue.dream
				|| ECatalogue.get(cid) == ECatalogue.changing || ECatalogue.get(cid) == ECatalogue.partner || ECatalogue.get(cid) == ECatalogue.dreaming) {
			return "article";
		}
		// 别出心裁、识破骗局
		if (ECatalogue.get(cid) == ECatalogue.difference || ECatalogue.get(cid) == ECatalogue.fraud) {
			Content content = ContentHP.getOneContent(EContent.html, cid);
			ContentHP.freemarked(content, model, request);
			model.put(ViewKeyDict.bean, content);
			return "article";
		}

		// 人生设计
		if (ECatalogue.get(cid) == ECatalogue.design) {
			List<Problem> problemList = ProblemHP.getProblemList(0, SysConf.DesignProblemSize, cid, SysConf.ismember);
			model.put(ViewKeyDict.problemList, problemList);
			return "design";
		}
		return "";
	}

	/**
	 * 除首页的页面的下拉加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @param cid
	 * @return
	 * @author YangGuang
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajax/otherpage/catalogue/{cid:\\w+}", method = RequestMethod.GET)
	public String ajax_firstArticle(Map<String, Object> model, int pagenum, @PathVariable Long cid, HttpServletRequest request) {
		List<Article> list = ArticleHP.getMoreArticle(pagenum, SysConf.LoadSize, cid);
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		if (ECatalogue.get(cid) == ECatalogue.travel) {
			Map<String, Object> map = null;
			for (Article article : list) {
				String json = article.getOrigin();
				map = JsonUtil.fromJson(json, Map.class);
				article.setPlacename((String) map.get("name"));
			}
			return "ajaxtravel";
		}
		return "ajaxarticle";
	}

	/**
	 * 除首页的页面的加载文章
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @param cid
	 * @return
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/article/catalogue/{cid:\\w+}", method = RequestMethod.GET)
	public String ajax_Article(Map<String, Object> model, int pagesize, int pagenum, @PathVariable Long cid, HttpServletRequest request) {
		List<Article> list = IndexHP.getPagesMore(pagenum, cid, pagesize);
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		return "ajaxarticle";
	}

	/**
	 * 行万里路按照文章热度加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajax/article/hottravel", method = RequestMethod.GET)
	public String ajax_hotArticle(Map<String, Object> model, int pagenum, Long cid, HttpServletRequest request) {
		List<Article> list = ArticleHP.getArticleHot(pagenum, SysConf.LoadSize, cid);
		Map<String, Object> map = null;
		for (Article article : list) {
			String json = article.getOrigin();
			map = JsonUtil.fromJson(json, Map.class);
			article.setPlacename((String) map.get("name"));
		}
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		return "ajaxtravel";
	}
	
	/**
	 * 行万里路按地区加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author tf
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ajax/article/area", method = RequestMethod.GET)
	public String ajax_area(Map<String, Object> model, int pagenum, Long cid,String province,String city, HttpServletRequest request) {
		List<Article> list = new ArrayList<>();
		Map<String, Object> map = null;
		//点击更多  根据城市进行搜索
		if (StrUtil.isNotEmpty(province)) {
			List<Article> areaList = new ArrayList<>();
			list = ArticleHP.getProvinceArticle(pagenum, SysConf.LoadSize, province);
			for (Article article : list) {
				String json = article.getOrigin();
				map = JsonUtil.fromJson(json, Map.class);
				String cities = (String) map.get("name");
				article.setPlacename(cities);
				if (StrUtil.isNotEmpty(city)) {
					if (city.equals(cities)) {
						areaList.add(article);
					}
				}
			}
			if (StrUtil.isNotEmpty(city)) {
				list = areaList;
			}
		} else {
			list = ArticleHP.getArticleHot(pagenum, SysConf.LoadSize, cid);
			for (Article article : list) {
				String json = article.getOrigin();
				map = JsonUtil.fromJson(json, Map.class);
				article.setPlacename((String) map.get("name"));
			}
		}
		model.put(ViewKeyDict.list, list);
		model.put(ViewKeyDict.cid, cid);
		return "ajaxtravel";
	}

	/**
	 * 自学流程图页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/duty/selftaught", method = RequestMethod.GET)
	public String taught(Map<String, Object> model, int pcid, int cid, HttpServletRequest request) {
		model.put(ViewKeyDict.pcid, pcid);
		model.put(ViewKeyDict.cid, cid);
		return "selftaught";
	}
}