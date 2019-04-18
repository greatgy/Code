package com.supergenius.web.admin.startup.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.supergenius.core.rule.StartupArticleListRule;
import com.supergenius.core.rule.StartupFirstArticleRule;
import com.supergenius.core.rule.StartupLatestArticleRule;
import com.supergenius.core.rule.StartupRecoomendListRule;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.startup.helper.LabelHP;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.startup.entity.Article;
import com.supergenius.xo.startup.entity.Catalogue;
import com.supergenius.xo.startup.service.ArticleSO;
import com.supergenius.xo.startup.service.CatalogueSO;

/**
 * 文章管理
 * 
 * @author 许志翔
 * @date 2017年8月9日15:34:00
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class StartupArticleAdminer extends BaseController {

	@Autowired
	private ArticleSO so;

	@Autowired
	private AdminLogSO adminLogSO;
	
	@Autowired
	private CatalogueSO catalogueSO;

	/**
	 * 文章管理页面
	 * 
	 * @author 许志翔
	 * @date 2017年8月9日15:38:10
	 * @return String
	 */
	@RequestMapping(value = "/startup/startuparticle", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.startuparticle.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.startuparticle, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.startup.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.photopath, SysConf.StartupPhotoPath);
		model.put(MapperDict.flashcid, SysConf.flashCid);
		model.put(ViewKeyDict.catelogueList, StartupArticleHP.getCatelogueList()); // 获取目录列表
		return "dostartuparticle";
	}

	/**
	 * 显示列表
	 * 
	 * @author 许志翔
	 * @date 2017年8月24日09:34:20
	 * @return String
	 */
	@RequestMapping(value = "/startup/ajax/startuparticle/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = StartupArticleHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author 许志翔
	 * @data 2017年8月9日15:53:05
	 */
	@RequestMapping(value = "/startup/ajax/startuparticle/delete", method = RequestMethod.GET)
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
		if (so.updateFields(map)) {
			Map<String, Object> tempmap = new HashMap<>();
			List<Article> articleList = new ArrayList<>();
			tempmap.put(ViewKeyDict.uidlist, uidlist);
			articleList = so.getList(tempmap);
			for (Article article : articleList) {
				Catalogue catalougue = catalogueSO.get(String.valueOf(article.getCid()));
				Rule allArticlerule = new StartupArticleListRule(ViewKeyDict.all);//清空所有文章缓存
				MemcacheUtil.remove(allArticlerule);
				Rule firstCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getPcid()));//清空一级目录文章缓存
				MemcacheUtil.remove(firstCatalogueRule);
				Rule secondCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getCid()));//清空二级目录文章缓存
				MemcacheUtil.remove(secondCatalogueRule);
				Rule recommendRule = new StartupRecoomendListRule();//清空置顶文章缓存
				MemcacheUtil.remove(recommendRule);
				Rule latestrule = new StartupLatestArticleRule();//清空快讯文章
				MemcacheUtil.remove(latestrule);
				Rule rule = new StartupFirstArticleRule();
				MemcacheUtil.remove(rule);
				if (article != null) {
					AdminLog adminLog = new AdminLog();
					adminLog.setAdminuid(AdminHP.getAdminUid());
					adminLog.setChannel(EChannel.startuparticle.toInt());
					adminLog.setOperation(EAdminLog.deleteStartupArticle.getName());
					adminLog.setData(EAdminLog.deleteStartupArticle.getName());
					adminLog.setDesc(EAdminLog.deleteStartupArticle.getName());
					adminLog.setDataid(article.getUid());
					adminLogSO.add(adminLog);
					uidmap.put(MapperDict.uid, article.getUid());
					StartupArticleHP.getEngine().delete(uidmap);
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
	@RequestMapping(value = "/startup/ajax/startuparticle/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
		Article article = so.get(uid);
		if (article != null) {
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.startuparticle.toInt());
			adminLog.setOperation(EAdminLog.deleteStartupArticle.getName());
			adminLog.setData(EAdminLog.deleteStartupArticle.getName());
			adminLog.setDesc(EAdminLog.deleteStartupArticle.getName());
			adminLog.setDataid(uid);
			adminLogSO.add(adminLog);
			article.setStatus(EStatus.get(status));
			if (so.update(article)) {
				if (EStatus.get(status) == EStatus.disable) {
					StartupArticleHP.getEngine().deleteByID(article.getUid());
				}else if (EStatus.get(status) == EStatus.enable) {
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.startup.name());
					StartupArticleHP.getEngine().add(map);
				}
				Catalogue catalougue = catalogueSO.get(String.valueOf(article.getCid()));
				Rule rule = new StartupArticleListRule(ViewKeyDict.all);
				MemcacheUtil.remove(rule);
				Rule firstCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getPcid()));//清空一级目录文章缓存
				MemcacheUtil.remove(firstCatalogueRule);
				Rule secondCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getCid()));//清空二级目录文章缓存
				MemcacheUtil.remove(secondCatalogueRule);
				Rule recommendRule = new StartupRecoomendListRule();//清空置顶文章缓存
				MemcacheUtil.remove(recommendRule);
				Rule latestrule = new StartupLatestArticleRule();//清空快讯文章
				MemcacheUtil.remove(latestrule);
				Rule firstrule = new StartupFirstArticleRule();
				MemcacheUtil.remove(firstrule);
				return success();
			}
		}
		return result(MsgKeyDict.deleteFailed);
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
	 * @author 许志翔
	 * @date 2017年8月25日09:33:31
	 */
	@RequestMapping(value = "/startup/ajax/startuparticle/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String title, String contributeimg, String booktime, String author, String keywords, String origin, String origin_radio, String type_radio, String cataloguetype, String summary, String content,
			String labelcontent) {
		Article article = new Article();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(cataloguetype) && StrUtil.isNotEmpty(type_radio) && StrUtil.isNotEmpty(origin_radio)) {
			article.setTitle(title);
			if ((Integer.parseInt(cataloguetype) == SysConf.flashCid)) {
				article.setIsflash(1);
			}
			article.setCid(Integer.parseInt(cataloguetype));
			article.setType(Integer.parseInt(type_radio));
			article.setIsoriginal(Integer.parseInt(origin_radio));
			article.setContent(content);
			if (StrUtil.isNotEmpty(keywords)){
				article.setKeywords(keywords);
			}
		} else {
			return result(MsgKeyDict.addFailed);
		}
		article.setAuthor(author);
		article.setOrigin(origin);
		article.setSummary(summary);
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
		if (StrUtil.isNotEmpty(booktime)) {
			article.setCreatetime(DateUtil.parse(booktime));
		}
		if (so.add(article)) {
			if (!StartupArticleHP.addTags(article)) {
				return result(MsgKeyDict.addFailed);
			}
			if (StrUtil.isNotEmpty(labelcontent)) {
				LabelHP.add(article.getUid(), labelcontent);
			}
			Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
			Catalogue catalougue = catalogueSO.get(String.valueOf(newArticle.getCid()));
			Rule rule = new StartupArticleListRule(ViewKeyDict.all);
			List<Article> allArticleList = StartupArticleHP.getArticleListFromMC(rule);
			if (allArticleList != null) {
				newArticle.setContent("");
				allArticleList.add(newArticle);
				MemcacheUtil.set(rule, allArticleList);
			}
			Rule firstrule = new StartupFirstArticleRule();
			MemcacheUtil.remove(firstrule);
			Rule allArticleRule = new StartupArticleListRule(ViewKeyDict.all);//清空左侧文章缓存
			MemcacheUtil.remove(allArticleRule);
			Rule firstCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getPcid()));//清空一级目录文章缓存
			MemcacheUtil.remove(firstCatalogueRule);
			Rule secondCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getCid()));//清空二级目录文章缓存
			MemcacheUtil.remove(secondCatalogueRule);
			Rule recommendRule = new StartupRecoomendListRule();//清空置顶文章缓存
			MemcacheUtil.remove(recommendRule);
			Rule latestrule = new StartupLatestArticleRule();//清空快讯文章
			MemcacheUtil.remove(latestrule);
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.startup.name());
			StartupArticleHP.getEngine().add(map);
			// 删除序列化文件
			String path = SysConf.SerialBasePath + SysConf.SerialArticlePath + SysConf.Separator_Directory;
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
		return result(MsgKeyDict.addFailed);
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
	 * @author 许志翔
	 * @date 2017年8月25日09:33:00
	 */
	@RequestMapping(value = "/startup/ajax/startuparticle/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String uid, String contributeimg, String booktimeStr, String title, String author, String keywords, String origin, String origin_radio, String type_radio, String add_cataloguetype, String summary,
			String content) {
		Article article = so.get(uid);
		Boolean changecid = false;
		int oldcid = 0;
		if (article.getCid() != Integer.parseInt(add_cataloguetype)) {
			changecid = true;
			oldcid = article.getCid();
		}
		String adminUid = AdminHP.getAdminUid();
		if (article != null) {
			article.setTitle(title);
			article.setAuthor(author);
			article.setOrigin(origin);
			article.setIsoriginal(Integer.parseInt(origin_radio));
			article.setType(Integer.parseInt(type_radio));
			if ((Integer.parseInt(add_cataloguetype) == SysConf.flashCid)) {
				article.setIsflash(1);
			}
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
			if (StrUtil.isNotEmpty(keywords)){
				article.setKeywords(keywords);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.startuparticle.toInt());
			adminLog.setDataid(article.getUid());
			adminLog.setDesc(EAdminLog.updateStartupArticle.getName());
			adminLog.setData(EAdminLog.updateStartupArticle.getName());
			adminLog.setOperation(EAdminLog.updateStartupArticle.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				article.setAdminuid(adminUid);
			}
			if (StrUtil.isNotEmpty(booktimeStr)) {
				article.setCreatetime(DateUtil.parse(booktimeStr));
			}
			if (so.update(article)) {
				File SEOfile = new File(BaseSysConf.getSEOPath(ESite.startup.name()));
				SEOfile.setLastModified(System.currentTimeMillis());
				Catalogue catalougue = catalogueSO.get(String.valueOf(article.getCid()));
				Rule allArticlerule = new StartupArticleListRule(ViewKeyDict.all);//清空所有文章缓存
				MemcacheUtil.remove(allArticlerule);
				Rule firstCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getPcid()));//清空一级目录文章缓存
				MemcacheUtil.remove(firstCatalogueRule);
				Rule secondCatalogueRule = new StartupArticleListRule(String.valueOf(catalougue.getCid()));//清空二级目录文章缓存
				MemcacheUtil.remove(secondCatalogueRule);
				Rule firstrule = new StartupFirstArticleRule();
				MemcacheUtil.remove(firstrule);
				if (changecid) {
					Catalogue oldcatalougue = catalogueSO.get(String.valueOf(oldcid));
					firstCatalogueRule = new StartupArticleListRule(String.valueOf(oldcatalougue.getPcid()));//清空一级目录文章缓存
					MemcacheUtil.remove(firstCatalogueRule);
					secondCatalogueRule = new StartupArticleListRule(String.valueOf(oldcatalougue.getCid()));//清空二级目录文章缓存
					MemcacheUtil.remove(secondCatalogueRule);
				}
				Rule allArticleRule = new StartupArticleListRule(ViewKeyDict.all);//清空左侧文章缓存
				MemcacheUtil.remove(allArticleRule);
				Rule recommendRule = new StartupRecoomendListRule();//清空置顶文章缓存
				MemcacheUtil.remove(recommendRule);
				Rule latestrule = new StartupLatestArticleRule();//清空快讯文章
				MemcacheUtil.remove(latestrule);
				if (article.getStatus().equals(EStatus.enable)) {// 删除索引后在添加索引
					StartupArticleHP.getEngine().deleteByID(article.getUid());
					article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.startup.name());
					StartupArticleHP.getEngine().add(map);
				} else {
					StartupArticleHP.getEngine().deleteByID(article.getUid());
				}
				String path = SysConf.SerialBasePath + SysConf.SerialArticlePath + SysConf.Separator_Directory;
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
	 * @author 许志翔
	 * @date 2017年8月24日10:44:55
	 */
	@RequestMapping(value = "/startup/ajax/startuparticle/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_top(String[] ids) {
		Rule recommendRule = new StartupRecoomendListRule();//清空置顶文章缓存
		MemcacheUtil.remove(recommendRule);
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
	 * @author 许志翔
	 * @date 2017年8月24日10:50:07
	 */
	@RequestMapping(value = "/startup/ajax/startuparticle/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		Rule recommendRule = new StartupRecoomendListRule();//清空置顶文章缓存
		MemcacheUtil.remove(recommendRule);
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
	@RequestMapping(value = "/startup/ajax/startuparticle/topcount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, HttpServletRequest request) {
		model.put(MapperDict.count, StartupArticleHP.getTopArticleCount());
		return json(model, Json.webStrategy);
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
	@RequestMapping(value = { "/startup/ajax/startuparticle/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = StartupArticleHP.resizeImage(fileimg, SysConf.StartupPhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
		return data;
	}
}
