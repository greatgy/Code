package com.supergenius.web.admin.career.controller;

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
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.genius.xo.baseadmin.service.AdminLogSO;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.career.util.ArticleRedisUtil;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.CareerArticleHP;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.career.entity.Article;
import com.supergenius.xo.career.enums.ECatalogue;
import com.supergenius.xo.career.enums.ETop;
import com.supergenius.xo.career.service.ArticleSO;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;

/**
 * 应聘指南文章管理
 * 
 * @author yangguang
 * @date 2017年11月14日14:29:24
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class GuideArticleAdminer extends BaseController{
	
	@Autowired
	private ArticleSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	/**
	 * 应聘指南页面
	 * 
	 * @author 杨光
	 * @date 2017年11月14日12:21:35
	 * @return String
	 */
	@RequestMapping(value = "/career/guidearticle", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.careerarticle.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.careerarticle, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.career.name());
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		Integer[] cids = new Integer[]{21,22,23};
		List<Integer> listcid = java.util.Arrays.asList(cids);
		map.put(MapperDict.cidlist, listcid);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.specialList, CareerArticleHP.getGuideList());
		model.put(ViewKeyDict.photopath, SysConf.CareerPhotoPath);
		return "doguidearticle";
	}

	/**
	 * 显示列表
	 * 
	 * @author 杨光
	 * @date 2017年9月19日11:50:19
	 * @return String
	 */
	@RequestMapping(value = "/career/ajax/guidearticle/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = CareerArticleHP.query(model, ECatalogue.guide);
		return json(searchMap, Json.webStrategy);
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
	@RequestMapping(value = "/career/ajax/careerarticle/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String title, String contributeimg,Integer prizecount,Integer clickcount, String booktime, String author, String origin_radio, String origin, String type_radio, String cataloguetype, String summary, String content,
			String labelcontent,String keywordscontent) {
		Article article = new Article();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(origin_radio) && StrUtil.isNotEmpty(cataloguetype) && StrUtil.isNotEmpty(type_radio)) {
			article.setTitle(title);
			article.setCid(Integer.parseInt(cataloguetype));
			article.setKind(Integer.parseInt(type_radio));
			article.setContent(content);
			article.setIsoriginal(Integer.parseInt(origin_radio));
		} else {
			return result(MsgKeyDict.addFailed);
		}
		article.setAuthor(author);
		article.setOrigin(origin);
		article.setSummary(summary);
		article.setTags(labelcontent);
		article.setKeywords(keywordscontent);
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
		if (StrUtil.isNotEmpty(booktime)) {
			article.setCreatetime(DateUtil.parse(booktime));
		}
		if (so.add(article)) {
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put(MapperDict.clickcount, clickcount);
			map2.put(MapperDict.prizecount, prizecount);
			ArticleRedisUtil.set(article.getUid(), map2);
			CareerArticleHP.handleCache(article.getCid());
			Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.career.name());
			CareerArticleHP.getEngine().add(map);
			// 删除序列化文件
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	
	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author 杨光
	 * @data 2017年9月19日12:30:21
	 */
	@RequestMapping(value = "/career/ajax/careerarticle/delete", method = RequestMethod.GET)
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
				if (article != null) {
					AdminLog adminLog = new AdminLog();
					adminLog.setAdminuid(AdminHP.getAdminUid());
					adminLog.setChannel(EChannel.careerarticle.toInt());
					adminLog.setOperation(EAdminLog.deleteAiArticle.getName());
					adminLog.setData(EAdminLog.deleteAiArticle.getName());
					adminLog.setDesc(EAdminLog.deleteAiArticle.getName());
					adminLog.setDataid(ids[0]);
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
	 * @data 2017年11月14日12:35:14
	 */
	@RequestMapping(value = "/career/ajax/careerarticle/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
			Article article = so.get(uid);
			String adminuid = AdminHP.getAdminUid();
			if (article != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.careerarticle.toInt());
				adminLog.setOperation(EAdminLog.deleteAiArticle.getName());
				adminLog.setData(EAdminLog.deleteAiArticle.getName());
				adminLog.setDesc(EAdminLog.deleteAiArticle.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				article.setStatus(EStatus.get(status));
				article.setAdminuid(adminuid);
				if (so.update(article)) {
					CareerArticleHP.handleCache(article.getCid());
					if (EStatus.get(status) == EStatus.disable) {
						CareerArticleHP.getEngine().deleteByID(article.getUid());
					}else if (EStatus.get(status) == EStatus.enable) {
						Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
						map.put(MapperSearchDict.table, ESite.startup.name());
						CareerArticleHP.getEngine().add(map);
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
	 * @author 杨光
	 * @date 2017年9月19日12:40:05
	 */
	@RequestMapping(value = "/career/ajax/careerarticle/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String uid, String contributeimg, String booktimeStr,Integer prizecount,Integer clickcount, String title, String username, String origin_radio, String author, String origin, String type_radio, String add_cataloguetype, String summary,
			String content, String contact, String keywords) {
		Article article = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (article != null) {
			article.setTitle(title);
			article.setAuthor(author);
			article.setOrigin(origin);
			HashMap<String, Object> map2 = new HashMap<>();
			map2.put(MapperDict.clickcount, clickcount);
			map2.put(MapperDict.prizecount, prizecount);
			ArticleRedisUtil.set(uid, map2);
			article.setKind(Integer.parseInt(type_radio));  
			article.setCid(Integer.parseInt(add_cataloguetype));
			article.setSummary(summary);
			article.setKeywords(keywords);
			article.setContent(content);
			article.setIsoriginal(Integer.parseInt(origin_radio));
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				article.setImgoriginal(imgdata[0]);
				article.setImgbig(imgdata[1]);
				article.setImgmedium(imgdata[2]);
				article.setImglittle(imgdata[3]);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.careerarticle.toInt());
			adminLog.setDataid(article.getUid());
			adminLog.setDesc(EAdminLog.updateCareerArticle.getName());
			adminLog.setData(EAdminLog.updateCareerArticle.getName());
			adminLog.setOperation(EAdminLog.updateCareerArticle.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				article.setAdminuid(adminUid);
			}
			if (StrUtil.isNotEmpty(booktimeStr)) {
				article.setCreatetime(DateUtil.parse(booktimeStr));
			}
			if (so.update(article)) {
				File SEOfile = new File(BaseSysConf.getSEOPath(ESite.career.name()));
				SEOfile.setLastModified(System.currentTimeMillis());
				CareerArticleHP.handleCache(article.getCid());
				if (article.getStatus().equals(EStatus.enable)) {//删除索引后在添加索引
					CareerArticleHP.getEngine().deleteByID(article.getUid());
					article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());//清除格式
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.career.name());
					CareerArticleHP.getEngine().add(map);
				} else {
					CareerArticleHP.getEngine().deleteByID(article.getUid());
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
	@RequestMapping(value = "/career/ajax/careerarticle/enable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_top(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			CareerArticleHP.handleCache(article.getCid());
		}
		if (so.update(ids, ETop.istop)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}
	/**
	 * 设置置底
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日13:53:03
	 */
	@RequestMapping(value = "/career/ajax/careerarticle/2", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_bottom(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			CareerArticleHP.handleCache(article.getCid());
		}
		if (so.update(ids, ETop.isbottom)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 取消置顶，取消置底
	 * 
	 * @param id
	 * @return
	 * @author 杨光
	 * @date 2017年9月19日13:57:23
	 */
	@RequestMapping(value = "/career/ajax/careerarticle/disable", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> finance_untop(String[] ids) {
		Article article;
		for(String uid : ids){
			article = so.get(uid);
			CareerArticleHP.handleCache(article.getCid());
		}
		if (so.update(ids, ETop.nottop)) {
			return success();
		}
		return result(MsgKeyDict.updateFailed);
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
	@RequestMapping(value = { "/career/ajax/careerarticle/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = CareerArticleHP.resizeImage(fileimg, SysConf.CareerPhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
		return data;
	}
}
