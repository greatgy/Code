package com.supergenius.web.front.life.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Maps;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.front.life.helper.ContributeHP;
import com.supergenius.web.front.life.helper.IndexHP;
import com.supergenius.web.front.life.helper.TopicHP;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Catalogue;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.service.ArticleSO;
import com.supergenius.xo.life.service.TopicSO;
import com.supergenius.xo.user.entity.User;

/**
 * 投稿controller
 * 
 * @author YangGuang
 * @date 2018年5月16日15:23:10
 */
@Controller
public class ContributeController extends BaseController {

	@Autowired
	private ArticleSO so;
	@Autowired
	private TopicSO topicSO;

	/**
	 * 打开投稿页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/my/member/contribute" }, method = RequestMethod.GET)
	public String contribute(Map<String, Object> model, String cid, HttpServletRequest request) {
		model.put(ViewKeyDict.catalogueList, ContributeHP.getCatalogues());
		if (StrUtil.isNotEmpty(cid)) {
			Catalogue catalogue = IndexHP.getOneCatalogue(Long.parseLong(cid));
			long pcid=catalogue.getPcid();
			model.put(ViewKeyDict.pcid, pcid);
			model.put(ViewKeyDict.cid,  Long.parseLong(cid));
		}
		return "contribute";
	}

	/**
	 * 提交投稿
	 * 
	 * @param model
	 * @param request
	 * @param topic
	 * @return
	 */
	@RequestMapping(value = { "/my/contribute" }, method = RequestMethod.POST)
	public String contribute(Map<String, Object> model, Article article, String cid, String contributeimg, String[] cids, String city, String code, String province, HttpServletRequest request,
			HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		model.put(ViewKeyDict.user, user);
		boolean flag = true;
		if (StrUtil.isEmpty(article.getTitle()) || StrUtil.isEmpty(article.getContent())) {
			flag = false;
		}
		if (flag) {
			article.setUseruid(user.getUid());
			if (cids != null && cids.length >0) {
				long tempcid = 0;
				for (String item : cids) {
					tempcid = tempcid | Long.parseLong(ECatalogue.get(item).toString());
				}
				article.setCid(tempcid);
			}
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				article.setImgoriginal(imgdata[0]);
				article.setImgbig(imgdata[1]);
				article.setImgmedium(imgdata[2]);
				article.setImglittle(imgdata[3]);
			}
			// 行万里路
			if (ECatalogue.get(cid) == ECatalogue.travel) {
				Map<String, Object> placemap = new HashMap<String, Object>();
				placemap.put("name", city);
				placemap.put("code", code);
				if (StrUtil.isNotEmpty(province)) {
					placemap.put("province", province.replace("省", ""));
				}
				if (StrUtil.isEmpty(code) || Integer.valueOf(code.substring(0, 3)) < 350) {
					placemap.put("country", "中国");
					if (StrUtil.isEmpty(province)) {
						placemap.put("province", city.replace("市", ""));
					}
				} else {
					placemap.put("country", city);
				}
				article.setOrigin(JsonUtil.toJson(placemap));
			}
			if (so.add(article)) {
				Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
				if (article.getType() == 1) {
					ContributeHP.Cache(article.getCid());
					newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
					Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
					map.put(MapperSearchDict.table, ESite.life.name());
					ContributeHP.getEngine().add(map);
					return redirectPrefix + "/article" + BaseStrDict.slash + newArticle.getFirstCid() + BaseStrDict.slash + newArticle.getOid();
				} else if (article.getType() == 0) {
					return "contribute";
				}
			}
			return "failed";
		}
		return "failed";
	}
	
	/**
	 * 打开话题投稿页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/my/topic/contribute" }, method = RequestMethod.GET)
	public String topic_contribute(Map<String, Object> model, Long cid, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(cid)) {
			Catalogue catalogue = IndexHP.getOneCatalogue(cid);
			long pcid=catalogue.getPcid();
			model.put(ViewKeyDict.pcid, pcid);
			model.put(ViewKeyDict.cid, cid);
		}
		return "topiccontribute";
	}
	
	/**
	 * 提交话题投稿
	 * 
	 * @param model
	 * @param request
	 * @param topic
	 * @return
	 */
	@RequestMapping(value = { "/my/topic/contribute" }, method = RequestMethod.POST)
	public String topic_contribute(Map<String, Object> model, Topic topic, String contributeimg, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		model.put(ViewKeyDict.user, user);
		boolean flag = true;
		if (StrUtil.isEmpty(topic.getTitle()) || StrUtil.isEmpty(topic.getContent())) {
			flag = false;
		}
		if (flag) {
			topic.setUseruid(user.getUid());
			topic.setAuthor(user.getUsername());
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				topic.setImgoriginal(imgdata[0]);
				topic.setImgbig(imgdata[1]);
				topic.setImgmedium(imgdata[2]);
				topic.setImglittle(imgdata[3]);
			}
			if (ECatalogue.get(topic.getCid()) == ECatalogue.insight) {
				TopicHP.deleteTopic();
				topic.setExamine(1);
			} else {
				topic.setExamine(0);
			}
			if (topicSO.add(topic)) {
				Topic newTopic = topicSO.get(topic.getUid());
				if (ECatalogue.get(topic.getCid()) == ECatalogue.insight) {
					return redirectPrefix + "/topic/" + newTopic.getCid() + BaseStrDict.slash + newTopic.getOid();
				} else {
					return redirectPrefix + "/topic/review/" + newTopic.getCid() + BaseStrDict.slash + newTopic.getOid();
				}
				
			}
			return "failed";
		}
		return "failed";
	}

	/**
	 * 上传图片处理
	 * 
	 * @param model
	 * @param type
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = ContributeHP.resizeImage(fileimg, SysConf.UploadImgPath, SysConf.ImgShowSizes, SysConf.ImgUploadBasePath);
		return data;
	}
}