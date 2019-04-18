package com.supergenius.web.admin.gupage.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
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
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.gupage.helper.GupageDebateHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.gupage.entity.Debate;
import com.supergenius.xo.gupage.service.DebateSO;


/**
 * 顾雏军专栏debate管理
 * 
 * @author
 * @date 2018年1月5日09:57:20
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class GupageDebateAdminer extends BaseController {

	@Autowired
	private DebateSO so;
	
	@Autowired
	private AdminLogSO adminLogSO;
	
	/**
	 * 
	 * 
	 * @author loupengyu
	 * @date 2018年1月10日19:24:12
	 * @return String
	 */
	@RequestMapping(value = "/gupage/gupagedebate", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		List<EStatus> liststatus = new ArrayList<>();
		liststatus.add(EStatus.enable);
		liststatus.add(EStatus.disable);
		map.put(MapperDict.statuslist, liststatus);
		model.put(MapperDict.count, so.getCount(map));
		model.put(ViewKeyDict.channel, EChannel.gupagedebate.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.gupagedebate, Locale.CHINA));
		model.put(ViewKeyDict.site, ESite.gupage.name());
		return "dogupagedebate";
	}
	
	/**
	 * 显示列表
	 * 
	 * @author loupengyu
	 * @date 2018年1月11日19:24:39
	 * @return String
	 */
	@RequestMapping(value = "/gupage/ajax/gupagedebate/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = GupageDebateHP.query(model);
		return json(searchMap, Json.webStrategy);
	}
	
	/**
	 * 添加文章
	 * 
	 * @author loupengyu
	 * @date 2018年1月11日19:25:04
	 */
	@RequestMapping(value = "/gupage/ajax/gupagedebate/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_add(String keywords, String booktime, String title, String[] imgdata, String author, String origin, String type_radio, String summary, String content) {
		Debate article = new Debate();
		String adminUid = AdminHP.getAdminUid();
		if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(type_radio)) {
			article.setTitle(title);
			article.setKind(Integer.parseInt(type_radio));
			article.setContent(content);
		} else {
			return result(MsgKeyDict.addFailed);
		}
		article.setCid(41);
		article.setSummary(summary); 
		article.setCreatetime(new DateTime());
		if (StrUtil.isNotEmpty(imgdata)) {
			article.setImgoriginal(imgdata[0]);
			article.setImgbig(imgdata[1]);
			article.setImgmedium(imgdata[2]);
			article.setImglittle(imgdata[3]);
		}
		if (StrUtil.isNotEmpty(adminUid)) {
			article.setAdminuid(adminUid);
			article.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
		}
		if(StrUtil.isNotEmpty(keywords)){
			article.setKeywords(keywords);
		}
		if (StrUtil.isNotEmpty(booktime)) {
			article.setCreatetime(DateUtil.parse(booktime));
		}
		if (so.add(article)) {
			Debate newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
			newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
			Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
			map.put(MapperSearchDict.table, ESite.gupage.name());
			GupageDebateHP.getEngine().add(map);
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
	@RequestMapping(value = "/gupage/ajax/gupagedebate/delete", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String[] ids) {
		for (String id : ids) {
			Debate article = so.get(id);
			if (article != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(AdminHP.getAdminUid());
				adminLog.setChannel(EChannel.gupagedebate.toInt());
				adminLog.setOperation(EAdminLog.deleteGupageDebate.getName());
				adminLog.setData(EAdminLog.deleteGupageDebate.getName());
				adminLog.setDesc(EAdminLog.deleteGupageDebate.getName());
				adminLog.setDataid(ids[0]);
				adminLogSO.add(adminLog);
				article.setStatus(EStatus.deleted);
				if (so.update(article)) {
					Map<String, Object> map = new HashMap<>();
					map.put(MapperDict.uid, article.getUid());
					GupageDebateHP.getEngine().delete(map);
					return success();
				}
			}
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
	@RequestMapping(value = "/gupage/ajax/gupagedebate/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_delete(String uid, @PathVariable int status) {
			Debate article = so.get(uid);
			String adminuid = AdminHP.getAdminUid();
			if (article != null) {
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminuid(adminuid);
				adminLog.setChannel(EChannel.debate.toInt());//文件里有debate论战 可能要改
				adminLog.setOperation(EAdminLog.deleteGupageDebate.getName());
				adminLog.setData(EAdminLog.deleteGupageDebate.getName());
				adminLog.setDesc(EAdminLog.deleteGupageDebate.getName());
				adminLog.setDataid(uid);
				adminLogSO.add(adminLog);
				article.setStatus(EStatus.get(status));
				article.setAdminuid(adminuid);
				article.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
				if (so.update(article)) {
					if (EStatus.get(status) == EStatus.disable) {
						GupageDebateHP.getEngine().deleteByID(article.getUid());
					}else if (EStatus.get(status) == EStatus.enable) {
						Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
						map.put(MapperSearchDict.table, ESite.finance.name());
						GupageDebateHP.getEngine().add(map);
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
	 * @param summary
	 * @param content
	 * @return
	 * @author loupengyu
	 * @date 2018年1月11日19:25:47
	 */
	@RequestMapping(value = "/gupage/ajax/gupagedebate/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_edit(String keywords, String booktimeStr ,String uid, String[] imgdata, String title, String type_radio, String summary,
			String content, String contact) {
		Debate article = so.get(uid);
		String adminUid = AdminHP.getAdminUid();
		if (article != null) {
			article.setTitle(title);
			article.setKind(Integer.parseInt(type_radio));
			article.setSummary(summary);
			article.setContent(content);
			if (StrUtil.isNotEmpty(imgdata)) {
				article.setImgoriginal(imgdata[0]);
				article.setImgbig(imgdata[1]);
				article.setImgmedium(imgdata[2]);
				article.setImglittle(imgdata[3]);
			}
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminuid(AdminHP.getAdminUid());
			adminLog.setChannel(EChannel.gupagedebate.toInt());
			adminLog.setDataid(article.getUid());
			adminLog.setDesc(EAdminLog.updateGupageDebate.getName());
			adminLog.setData(EAdminLog.updateGupageDebate.getName());
			adminLog.setOperation(EAdminLog.updateGupageDebate.getName());
			if (StrUtil.isNotEmpty(adminUid)) {
				article.setAdminuid(adminUid);
				article.setUpdatetime(new DateTime(DateTimeZone.forOffsetHours(8)));
			}
			if(StrUtil.isNotEmpty(keywords)){
				article.setKeywords(keywords);
			}
			if (StrUtil.isNotEmpty(booktimeStr)) {
				article.setCreatetime(DateUtil.parse(booktimeStr));
			}
			if (so.update(article)) {
				if (article.getStatus().equals(EStatus.enable)) {//删除索引后在添加索引
					GupageDebateHP.getEngine().deleteByID(article.getUid());
					article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());//清除格式
					Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
					map.put(MapperSearchDict.table, ESite.gupage.name());
					GupageDebateHP.getEngine().add(map);
				} else {
					GupageDebateHP.getEngine().deleteByID(article.getUid());
				}
				return success();
			}
			
			
		}
		return result(MsgKeyDict.updateFailed);
	}
	
}
