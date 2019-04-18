package com.supergenius.web.admin.gupage.controller;

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
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.gupage.helper.GupageArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.gupage.entity.Article;
import com.supergenius.xo.gupage.service.ArticleSO;
import com.supergenius.xo.gupage.service.CatalogueSO;

/**
 * 顾雏军专栏文章管理
 * 
 * @author loupengyu
 * @date 2018年1月5日09:57:20
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class GupageArticleAdminer extends BaseController{
	
	@Autowired
	private ArticleSO so;

	@Autowired
	private AdminLogSO adminLogSO;

	@Autowired
	private CatalogueSO catalogueSO;
	
	/**
	 * 个人文章
	 * 
	 * @author loupengyu
	 * @date 2018年1月10日19:24:12
	 * @return String
	 */
	@RequestMapping(value = "/gupagearticle/{channel:[a-z]+}", method = RequestMethod.GET)
	public String article(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		EChannel eChannel = EChannel.get(channel);
		model.put(ViewKeyDict.channel, eChannel.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(eChannel, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.gupagearticle.name());
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.cid, GupageArticleHP.getcid(channel));
		model.put(ViewKeyDict.catelogueList, catalogueSO.getList());
		model.put(ViewKeyDict.photopath, SysConf.GupagePhotoPath);
		return "dogupagearticle";
	}

	/**
	 * 显示列表
	 * 
	 * @author loupengyu
	 * @date 2018年1月11日18:55:26
	 * @return String
	 */
	@RequestMapping(value = "/gupagearticle/ajax/{channel:[a-z]+}/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = GupageArticleHP.query(model, channel);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加列表
	 * 
	 * @author loupengyu
	 * @date 2018年1月11日18:55:05
	 * @return String
	 */
	@RequestMapping(value = "/gupagearticle/ajax/{channel:[a-z]+}/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(@PathVariable String channel, String booktime, String keywords, String title, String contributeimg, String author, String origin, String type_radio, String summary, String content) {
		Article article = new Article();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(type_radio)) {
			article.setTitle(title);
			article.setKind(Integer.parseInt(type_radio));
			article.setContent(content);
		} else {
			return result(MsgKeyDict.addFailed);
		}
		article.setSummary(summary); 
		article.setCid(GupageArticleHP.getcid(channel));
		article.setCreatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
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
		if(StrUtil.isNotEmpty(keywords)){
			article.setKeywords(keywords);
		}
		if (StrUtil.isNotEmpty(booktime)) {
			article.setCreatetime(DateUtil.parse(booktime));
		}
		if (so.add(article)) {
			Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.gupage.name());
			GupageArticleHP.getEngine().add(map);
			return success();
		}
		return result(MsgKeyDict.addFailed);
	}

	
	/**
	 * 删除文章
	 * 
	 * @param ids
	 * @return
	 * @author loupengyu
	 * @data 2018年1月5日15:25:36
	 */
	@RequestMapping(value = "/gupagearticle/ajax/{channel:[a-z]+}/delete", method = RequestMethod.GET)
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
					adminLog.setChannel(EChannel.article.toInt());
					adminLog.setOperation(EAdminLog.deleteGupageArticle.getName());
					adminLog.setData(EAdminLog.deleteGupageArticle.getName());
					adminLog.setDesc(EAdminLog.deleteGupageArticle.getName());
					adminLog.setDataid(ids[0]);
					adminLogSO.add(adminLog);
					
					uidmap.put(MapperDict.uid, article.getUid());
					GupageArticleHP.getEngine().delete(uidmap);
					
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
	 * @author loupengyu
	 * @data 2018年1月5日15:25:40
	 */
	@RequestMapping(value = "/gupagearticle/ajax/gupagearticle/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
			Article article = so.get(uid);
			String adminuid = AdminHP.getAdminUid();
			if (article != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.article.toInt());
				adminLog.setOperation(EAdminLog.deleteGupageArticle.getName());
				adminLog.setData(EAdminLog.deleteGupageArticle.getName());
				adminLog.setDesc(EAdminLog.deleteGupageArticle.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				article.setStatus(EStatus.get(status));
				article.setAdminuid(adminuid);
				if (so.update(article)) {
					if (EStatus.get(status) == EStatus.disable) {
						GupageArticleHP.getEngine().deleteByID(article.getUid());
					}else if (EStatus.get(status) == EStatus.enable) {
						Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
						map.put(MapperSearchDict.table, ESite.gupage.name());
						GupageArticleHP.getEngine().add(map);
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
	 * @param origin
	 * @param origin_radio
	 * @param type_radio
	 * @param cataloguetype
	 * @param summary
	 * @param content
	 * @return
	 * @author loupengyu
	 * @date 2018年1月11日18:56:15
	 */
	@RequestMapping(value = "/gupagearticle/ajax/{channel:[a-z]+}/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String uid, String contributeimg, String booktimeStr, String keywords, String title, String type_radio, String summary,
			String content,int cid) {
		Article article = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (article != null) {
			article.setTitle(title);
			article.setKind(Integer.parseInt(type_radio));
			article.setCid(cid);
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
			adminLog.setAdminuid(adminUid);
			adminLog.setChannel(EChannel.gupagearticle.toInt());
			adminLog.setDataid(article.getUid());
			adminLog.setDesc(EAdminLog.updateGupageArticle.getName());
			adminLog.setData(EAdminLog.updateGupageArticle.getName());
			adminLog.setOperation(EAdminLog.updateGupageArticle.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				article.setAdminuid(adminUid);
			}
			if(StrUtil.isNotEmpty(keywords)){
				article.setKeywords(keywords);
			}
			if (StrUtil.isNotEmpty(booktimeStr)) {
				article.setCreatetime(DateUtil.parse(booktimeStr));
			}
			if (so.update(article)) {
				File SEOfile = new File(BaseSysConf.getSEOPath(ESite.gupage.name()));
				SEOfile.setLastModified(System.currentTimeMillis());
				if (article.getStatus().equals(EStatus.enable)) {//删除索引后在添加索引
					GupageArticleHP.getEngine().deleteByID(article.getUid());
					article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());//清除格式
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.gupage.name());
					GupageArticleHP.getEngine().add(map);
				} else {
					GupageArticleHP.getEngine().deleteByID(article.getUid());
				}
				return success();
			}
			
			
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
	@RequestMapping(value = { "/gupagearticle/ajax/{channel:[a-z]+}/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = GupageArticleHP.resizeImage(fileimg, SysConf.GupagePhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
		return data;
	}
}