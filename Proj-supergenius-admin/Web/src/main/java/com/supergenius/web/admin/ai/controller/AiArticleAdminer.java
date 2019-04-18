package com.supergenius.web.admin.ai.controller;

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
import com.genius.core.base.utils.FileUtil;
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
import com.supergenius.core.rule.AiTopArticleRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.ai.helper.AiArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.ai.entity.Article;
import com.supergenius.xo.ai.service.ArticleSO;
import com.supergenius.xo.ai.service.CatalogueSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 文章管理
 * 
 * @author 杨光
 * @date 2017年9月19日11:35:16
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AiArticleAdminer extends BaseController{
	
	@Autowired
	private ArticleSO so;
	
	@Autowired
	private CatalogueSO catalogueSO;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 文章管理页面
	 * 
	 * @author 杨光
	 * @date 2017年9月19日11:36:30
	 * @return String
	 */
	@RequestMapping(value = "/ai/aiarticle", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.aiarticle.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.aiarticle, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.ai.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		map.put(MapperDict.catagory, EStatus.enable);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.photopath, SysConf.AiPhotoPath);
		model.put(ViewKeyDict.catelogueList, catalogueSO.getList()); // 获取目录列表
		return "doaiarticle";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2017年9月19日11:50:19
	 * @return String
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = AiArticleHP.query(model, EStatus.enable);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author 杨光
	 * @data 2017年9月19日12:30:21
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String[] ids) {
		List<String> uidlist = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> uidmap = new HashMap<>();
		map.put(ViewKeyDict.status, EStatus.deleted);
		for (String id : ids) {
			uidlist.add(id);
		}
		map.put(ViewKeyDict.uidlist, uidlist);
		if (so.updateToDeleted(map)) {
			Map<String, Object> tempmap = new HashMap<>();
			List<Article> articleList = new ArrayList<>();
			tempmap.put(ViewKeyDict.uidlist, uidlist);
			articleList = so.getList(tempmap);
			for (Article article : articleList) {
				AiArticleHP.handleDeleteCache(article.getCid());
				if (article != null) {
					AdminLog adminLog = new AdminLog();
					adminLog.setAdminuid(AdminHP.getAdminUid());
					adminLog.setChannel(EChannel.aiarticle.toInt());
					adminLog.setOperation(EAdminLog.deleteAiArticle.getName());
					adminLog.setData(EAdminLog.deleteAiArticle.getName());
					adminLog.setDesc(EAdminLog.deleteAiArticle.getName());
					adminLog.setDataid(article.getUid());
					adminLogSO.add(adminLog);
					
					uidmap.put(MapperDict.uid, article.getUid());
					AiArticleHP.getEngine().delete(map);
					String path = SysConf.SerialBasePath + SysConf.SerialAiArticlePath + SysConf.Separator_Directory;
					File file = new File(path);
					if (file.exists()) {
						File f = new File(path);
						String[] files = f.list();
						for (int a = 0; a < files.length; a++) {
							files[a] = path + files[a];
						}
						if (!FileUtil.delete(files)) {
							return result(MsgKeyDict.updateFailed);
						}
					} else {
						file.mkdirs();
					}
				}
			}
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
		
		
	}
	
	/**
	 * 冻结解冻
	 * 
	 * @param ids
	 * @return
	 * @author 杨光
	 * @data 2017年9月22日16:44:54
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
			Article article = so.get(uid);
			String adminuid = AdminHP.getAdminUid();
			if (article != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.aiarticle.toInt());
				adminLog.setOperation(EAdminLog.deleteAiArticle.getName());
				adminLog.setData(EAdminLog.deleteAiArticle.getName());
				adminLog.setDesc(EAdminLog.deleteAiArticle.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				article.setStatus(EStatus.get(status));
				article.setAdminuid(adminuid);
				article.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				if (so.update(article)) {
					if (EStatus.get(status) == EStatus.disable) {
						AiArticleHP.getEngine().deleteByID(article.getUid());
					}else if (EStatus.get(status) == EStatus.enable) {
						Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
						map.put(MapperSearchDict.table, ESite.ai.name());
						AiArticleHP.getEngine().add(map);
					}
					AiArticleHP.handleStatusCache(article, status);
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
	 * @author 杨光
	 * @date 2017年9月19日12:40:05
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String uid, String contributeimg, String title, String username, String author, String origin, String origin_radio, String type_radio, String add_cataloguetype, String summary,
			String content, String contact, String keywords) {
		Article article = so.get(uid);
		int oldcid = 0;
		if (article.getCid() != Integer.parseInt(add_cataloguetype)) {
			oldcid = article.getCid();
		}
		String adminUid = AdminHP.getAdminUid();
		if (article != null) {
			if (StrUtil.isNotEmpty(username)) {
				article.setUsername(username);
			}
			if (StrUtil.isNotEmpty(contact)) {
				article.setContact(contact);
			}
			article.setKeywords(keywords);
			article.setTitle(title);
			article.setAuthor(author);
			article.setOrigin(origin);
			article.setKind(Integer.parseInt(type_radio));
			article.setIsoriginal(Integer.parseInt(origin_radio));
			article.setCid(Integer.parseInt(add_cataloguetype));
			article.setSummary(summary);
			article.setContent(content);
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				article.setImgoriginal(imgdata[0]);
				article.setImgbig(imgdata[1]);
				article.setImgmedium(imgdata[2]);
				article.setImglittle(imgdata[3]);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.aiarticle.toInt());
			adminLog.setDataid(article.getUid());
			adminLog.setDesc(EAdminLog.updateAiArticle.getName());
			adminLog.setData(EAdminLog.updateAiArticle.getName());
			adminLog.setOperation(EAdminLog.updateAiArticle.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				article.setAdminuid(adminUid);
				article.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if (so.update(article)) {
				File SEOfile = new File(BaseSysConf.getSEOPath(ESite.ai.name()));
				SEOfile.setLastModified(System.currentTimeMillis());
				AiArticleHP.handleEditCache(article,oldcid);
				if (article.getStatus().equals(EStatus.enable)) {//删除索引后在添加索引
					AiArticleHP.getEngine().deleteByID(article.getUid());
					article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());//清除格式
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.ai.name());
					AiArticleHP.getEngine().add(map);
				} else {
					AiArticleHP.getEngine().deleteByID(article.getUid());
				}
				String path = SysConf.SerialBasePath + SysConf.SerialAiArticlePath;
				File file = new File(path);
				if (file.exists()) {
					File f = new File(path);
					String[] files = f.list();
					for (int a = 0; a < files.length; a++) {
						files[a] = path + files[a];
					}
					if (!FileUtil.delete(files)) {
						return result(MsgKeyDict.updateFailed);
					}
				} else {
					file.mkdirs();
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
	 * @author 杨光
	 * @date 2017年9月19日13:53:03
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_top(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			Rule rule = new AiTopArticleRule(String.valueOf(article.getCid()));
			MemcacheUtil.remove(rule);
		}
		if (so.update(ids, true)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 取消置顶
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日13:57:23
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			Rule rule = new AiTopArticleRule(String.valueOf(article.getCid()));
			MemcacheUtil.remove(rule);
		}
		if (so.update(ids, false)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	
	/**
	 * 获取置顶的个数
	 * 
	 * @author 许志翔
	 * @date 2017年8月28日09:33:22
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, AiArticleHP.getTopArticleCount());
		return json(model, Json.webStrategy);
	}
	
	/**
	 * 文章添加
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
	 * @author 娄鹏宇
	 * @date 2018年3月1日12:07:10
	 */
	@RequestMapping(value = "/ai/ajax/aiarticle/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String keywords, String title, String contributeimg, String author, String cid, String origin, String origin_radio, String type_radio, String summary,
			String content) {
		Article article = new Article();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(type_radio)) {
			article.setTitle(title);
			article.setKind(Integer.parseInt(type_radio));
			article.setContent(content);
			article.setCatagory(1);
		} else {
			return result(MsgKeyDict.addFailed);
		}
		article.setTitle(title);
		article.setAuthor(author);
		article.setOrigin(origin);
		article.setKind(Integer.parseInt(type_radio));
		article.setIsoriginal(Integer.parseInt(origin_radio));
		article.setCid(Integer.parseInt(cid));
		article.setSummary(summary);
		article.setContent(content);
		article.setType(1);
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
		if (StrUtil.isNotEmpty(keywords)) {
			article.setKeywords(keywords);
		}
		if (so.add(article)) {
			AiArticleHP.handleEditCache(article,0);
			Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.ai.name());
			AiArticleHP.getEngine().add(map);
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
	@RequestMapping(value = { "/ai/ajax/aiarticle/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = AiArticleHP.resizeImage(fileimg, SysConf.AiPhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
		return data;
	}
}