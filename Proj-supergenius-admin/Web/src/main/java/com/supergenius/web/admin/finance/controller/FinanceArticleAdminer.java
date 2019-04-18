package com.supergenius.web.admin.finance.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.DateUtil;
import com.genius.core.base.utils.FileUtil;
import com.genius.core.base.utils.JsonUtil;
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.cache.rule.Rule;
import com.genius.core.cache.utils.MemcacheUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.finance.helper.FinanceArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.enums.ECatalogue;
import com.supergenius.xo.finance.rule.FinanceTopArticleRule;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.finance.service.CatalogueSO;
import com.supergenius.xo.user.entity.Score;
import com.supergenius.xo.user.enums.EScore;
import com.supergenius.xo.user.service.ScoreSO;

/**
 * 文章管理
 * 
 * @author ChenQi
 * @date 2018年1月5日15:24:20
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class FinanceArticleAdminer extends BaseController{
	
	@Autowired
	private ArticleSO so;
	
	@Autowired
	private CatalogueSO catalogueSO;

	@Autowired
	private AdminLogSO adminLogSO;

	@Autowired
	private ScoreSO scoreSO;
	
	/**
	 * 文章管理页面
	 * 
	 * @author ChenQi
	 * @date 2018年1月5日15:25:29
	 * @return String
	 */
	@RequestMapping(value = "/finance/{channel:[a-z]+}", method = RequestMethod.GET)
	public String article(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		EChannel eChannel = EChannel.get(channel);
		model.put(ViewKeyDict.channel, eChannel.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(eChannel, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.finance.name());
		model.put(ViewKeyDict.photopath, SysConf.FinancePhotoPath);
		model.put(ViewKeyDict.catelogueList, catalogueSO.getList()); // 获取目录列表
		return "dofinancearticle";
	}

	/**
	 * 显示列表
	 * 
	 * @author ChenQi
	 * @date 2018年1月5日15:25:32
	 * @return String
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = FinanceArticleHP.query(model, channel);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author JiaShitao
	 * @data 2018年1月5日15:25:36
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(Map<String, Object> model, String[] ids) {
		List<String> uidlist = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		for (String id : ids) {
			uidlist.add(id);
		}
		map.put(ViewKeyDict.uidlist, uidlist);
		map.put(ViewKeyDict.status, EStatus.deleted);
		if (so.updateFields(map)) {
			Map<String, Object> tempmap = new HashMap<>();
			List<Article> articleList = new ArrayList<>();
			tempmap.put(ViewKeyDict.uidlist, uidlist);
			articleList = so.getList(tempmap);
			for (Article article : articleList) {
				if (article != null) {
					AdminLog adminLog = new AdminLog();
					adminLog.setAdminuid(AdminHP.getAdminUid());
					adminLog.setChannel(EChannel.article.toInt());
					adminLog.setOperation(EAdminLog.deleteFinanceArticle.getName());
					adminLog.setData(EAdminLog.deleteFinanceArticle.getName());
					adminLog.setDesc(EAdminLog.deleteFinanceArticle.getName());
					adminLog.setDataid(article.getUid());
					adminLogSO.add(adminLog);
					FinanceArticleHP.Cache(article);
					FinanceArticleHP.getEngine().deleteByID(article.getUid());
				}
			}
			// 删除首页序列化文章
			FileUtil.delete(SysConf.FileSiteBasePath + SysConf.FileRecentPath);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 冻结解冻
	 * 
	 * @param ids
	 * @return
	 * @author ChenQi
	 * @data 2018年1月5日15:25:40
	 */
	@RequestMapping(value = "/finance/ajax/financearticle/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
			Article article = so.get(uid);
			String adminuid = AdminHP.getAdminUid();
			if (article != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.article.toInt());
				adminLog.setOperation(EAdminLog.deleteFinanceArticle.getName());
				adminLog.setData(EAdminLog.deleteFinanceArticle.getName());
				adminLog.setDesc(EAdminLog.deleteFinanceArticle.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				article.setStatus(EStatus.get(status));
				article.setAdminuid(adminuid);
				if (so.update(article)) {
					FinanceArticleHP.Cache(article);
					if (EStatus.get(status) == EStatus.disable) {
						FinanceArticleHP.getEngine().deleteByID(article.getUid());
					}else if (EStatus.get(status) == EStatus.enable) {
						Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
						map.put(MapperSearchDict.table, ESite.finance.name());
						FinanceArticleHP.getEngine().add(map);
					}
					return success();
				}
			}
		return result(MsgKeyDict.deleteFailed);
	}
	
	/**
	 * 文章编辑
	 * 
	 * @param uid
	 * @param imgdata
	 * @param title
	 * @param author
	 * @param origin
	 * @param origin_radio
	 * @param type_radio
	 * @param cataloguetype
	 * @param summary
	 * @param content
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日15:25:44
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String uid, String contributeimg, String[] cids, String booktimeStr, String title, String username, String author, String origin, String origin_radio, String type_radio, String summary,
			String content, String contact, int score, String keywords) {
		Article article = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (article != null) {
			int cid = 0;
			for (String item : cids) {
				cid = cid | Integer.valueOf(ECatalogue.get(item).toString());
			}
			article.setCid(cid);
			article.setTitle(title);
			article.setAuthor(author);
			article.setOrigin(origin);
			article.setKeywords(keywords);
			article.setKind(Integer.parseInt(type_radio));
			article.setIsoriginal(Integer.parseInt(origin_radio));
			article.setSummary(summary);
			article.setContent(content);
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				article.setImgoriginal(imgdata[0]);
				article.setImgbig(imgdata[1]);
				article.setImgmedium(imgdata[2]);
				article.setImglittle(imgdata[3]);
			}
			if (StrUtil.isNotEmpty(score)) {
				Map<String, Integer> data = new HashMap<String, Integer>();
				data.put("score", score);
				data.put("scoretype", 0);
				article.setData(JsonUtil.toJson(data));
				Score userscore = scoreSO.getOne(article.getAuthoruid(), EScore.originalFinance);
				if (userscore != null) {
					userscore.setTotal(userscore.getTotal() + score);
					scoreSO.update(userscore);
				} else if(StrUtil.isNotEmpty(article.getAuthoruid())){
					userscore = new Score();
					userscore.setUseruid(article.getAuthoruid());
					userscore.setTotal(score);
					userscore.setType(EScore.originalFinance);
					userscore.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
					scoreSO.add(userscore);
				}
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.article.toInt());
			adminLog.setDataid(article.getUid());
			adminLog.setDesc(EAdminLog.updateFinanceArticle.getName());
			adminLog.setData(EAdminLog.updateFinanceArticle.getName());
			adminLog.setOperation(EAdminLog.updateFinanceArticle.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				article.setAdminuid(adminUid);
			}
			if (StrUtil.isNotEmpty(booktimeStr)) {
				article.setCreatetime(DateUtil.parse(booktimeStr));
			}
			if (so.update(article)) {
				File SEOfile = new File(BaseSysConf.getSEOPath(ESite.finance.name()));
				SEOfile.setLastModified(System.currentTimeMillis());
				FinanceArticleHP.Cache(article);
				if (article.getStatus().equals(EStatus.enable)) {//删除索引后在添加索引
					FinanceArticleHP.getEngine().deleteByID(article.getUid());
					article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());//清除格式
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.finance.name());
					FinanceArticleHP.getEngine().add(map);
				} else {
					FinanceArticleHP.getEngine().deleteByID(article.getUid());
				}
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 设置置顶
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日15:25:49
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_top(String[] ids) {
		Article article;
		Rule rule;
		for(String uid : ids){
			article = so.get(uid);
			article.setIstop(1);
			article.setToptime(new DateTime(DateTimeZone.forOffsetHours(8)));
            if (!so.update(article)) {
            	return result(MsgKeyDict.updateFailed);
            } else {
            	rule = new FinanceTopArticleRule(String.valueOf(article.getCid()));
            	MemcacheUtil.remove(rule);
            }
		}
		return success();
	}

	/**
	 * 取消置顶
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日15:25:53
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		Article article;
		Rule rule;
		for(String uid : ids){
			article = so.get(uid);
			article.setIstop(0);
            if (!so.update(article)) {
            	return result(MsgKeyDict.updateFailed);
            } else {
            	rule = new FinanceTopArticleRule(String.valueOf(article.getCid()));
            	MemcacheUtil.remove(rule);
            }
		}
		return success();
	}
	
	/**
	 * 获取置顶的个数
	 * 
	 * @author ChenQi
	 * @date 2018年1月5日15:25:56
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, @PathVariable String channel,HttpServletRequest request) {
		model.put(MapperDict.count, FinanceArticleHP.getTopArticleCount(FinanceArticleHP.getcid(channel)));
		return json(model, Json.webStrategy);
	}
	
	/**
	 * 设置推荐
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日15:25:49
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/recommend", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_recommend(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			article.setIsrecommend(1);
            if (!so.update(article)) {
            	return result(MsgKeyDict.updateFailed);
            }
		}
		return success();
	}
	
	/**
	 * 取消推荐
	 * 
	 * @param id
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日15:25:49
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/unrecommend", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_unrecommend(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			article.setIsrecommend(0);
			if (!so.update(article)) {
				return result(MsgKeyDict.updateFailed);
			}
		}
		return success();
	}
	
	/**
	 * 获取推荐的个数
	 * 
	 * @author ChenQi
	 * @date 2018年1月5日15:25:56
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/recommendcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_recommendcount(Map<String, Object> model, @PathVariable String channel,HttpServletRequest request) {
		model.put(MapperDict.count, FinanceArticleHP.getRecommendArticleCount(FinanceArticleHP.getcid(channel)));
		return json(model, Json.webStrategy);
	}
	
	/**
	 * 添加文章
	 * 
	 * @param title
	 * @param imgdata
	 * @param author
	 * @param origin
	 * @param origin_radio
	 * @param type_radio
	 * @param cataloguetype
	 * @param summary
	 * @param content
	 * @return
	 * @author ChenQi
	 * @date 2018年1月5日16:48:15
	 */
	@RequestMapping(value = "/finance/ajax/{channel:[a-z]+}/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(@PathVariable String channel, String[] cids, String publishtime, String title, String contributeimg, String author, String origin_radio, String origin, String type_radio, String summary, String content,
			String labelcontent, String SEOkeywords) {
		Article article = new Article();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(origin_radio) && StrUtil.isNotEmpty(type_radio)) {
			article.setTitle(title);
			if (channel.equals(ViewKeyDict.financearticle) && StrUtil.isNotEmpty(cids)) {
				int cid = 0;
				for (String item : cids) {
					cid = cid | Integer.valueOf(ECatalogue.get(item).toString());
				}
				article.setCid(cid);
			}else {
				article.setCid(FinanceArticleHP.getcid(channel));
			}
			article.setKind(Integer.parseInt(type_radio));
			article.setContent(content);
			article.setIsoriginal(Integer.parseInt(origin_radio));
		} else {
			return result(MsgKeyDict.addFailed);
		}
		article.setAuthor(author);
		article.setKeywords(SEOkeywords);
		article.setAuthoruid(AdminHP.getAdminUid());
		article.setOrigin(origin);
		article.setSummary(summary);
		article.setTags(labelcontent);
		article.setType(1);
		article.setCreatetime(new DateTime());
		if (StrUtil.isNotEmpty(contributeimg)) {
			String[] imgdata = contributeimg.split(BaseStrDict.comma);
			article.setImgoriginal(imgdata[0]);
			article.setImgbig(imgdata[1]);
			article.setImgmedium(imgdata[2]);
			article.setImglittle(imgdata[3]);
		}
		if (StrUtil.isNotEmpty(adminUid)) {
			article.setAdminuid(adminUid);
		}
		if (StrUtil.isNotEmpty(publishtime)) {
			article.setCreatetime(DateUtil.parse(publishtime));
		}
		if (so.add(article)) {
			if (!FinanceArticleHP.addTags(article)) {
				return result(MsgKeyDict.addFailed);
			}
			if (StrUtil.isNotEmpty(labelcontent)) {
				FinanceArticleHP.add(article.getUid(), labelcontent);
			}
			FinanceArticleHP.Cache(article);
			Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.finance.name());
			FinanceArticleHP.getEngine().add(map);
			// 删除序列化文件
			return success();
		}
		return result(MsgKeyDict.addFailed);
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
	@RequestMapping(value = { "/finance/ajax/{channel:[a-z]+}/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = FinanceArticleHP.resizeImage(fileimg, SysConf.FinancePhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
		return data;
	}
}