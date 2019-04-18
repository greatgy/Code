package com.supergenius.web.admin.official.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.supergenius.web.admin.moralnews.helper.MoralNewsCatalogueHP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genius.model.baseadmin.entity.Admin;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.admin.career.helper.CareerArticleHP;
import com.supergenius.web.admin.enterpriser.hellper.EnterpriserArticleHP;
import com.supergenius.web.admin.entrepreneur.hellper.EntrepreneurCatalogueHP;
import com.supergenius.web.admin.finance.helper.FinanceCatalogueHP;
import com.supergenius.web.admin.managernews.helper.ManagerNewsCatalogueHP;
import com.supergenius.web.admin.official.helper.OfficialArticleHP;
import com.supergenius.web.admin.startup.helper.StartupArticleHP;
import com.supergenius.xo.common.enums.EChannel;


/**
 * 全站文章管理
 * @author loupengyu
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class AllArticleAdminer extends BaseController{
 
	/**
	 * 打开页面
	 * @param model
	 * @return String
	 * @author loupengyu
	 * @date 2018年2月25日10:23:33
	 */
	@RequestMapping(value = {"/official/allarticle"}, method = RequestMethod.GET)
	public String article(Map<String, Object> model) {
		Admin admin= AdminHP.getAdmin();
		model.put(ViewKeyDict.enterprisercatalog, EnterpriserArticleHP.getCatalogueList());
		model.put(ViewKeyDict.financecataloglist, FinanceCatalogueHP.getlist());
		model.put(ViewKeyDict.managernewscataloglist, ManagerNewsCatalogueHP.getlist());
		model.put(ViewKeyDict.entrepreneurcataloglist, EntrepreneurCatalogueHP.getlist());
		model.put(ViewKeyDict.moralnewscataloglist, MoralNewsCatalogueHP.getlist());
		model.put(ViewKeyDict.channel, EChannel.article.name());
		model.put(ViewKeyDict.careercataloglist, CareerArticleHP.getCatalogueList());
		model.put(ViewKeyDict.startupcataloglist, StartupArticleHP.getCatelogueList());
		model.put(ViewKeyDict.photopath, SysConf.AllarticlePhotoPath);
		model.put(ViewKeyDict.admin, admin);
		return "doallarticle";
	}
	
	/**
	 * 打开页面 post
	 * @param model
	 * @return String
	 * @author loupengyu
	 * @date 2018年2月25日10:23:33
	 */
	@RequestMapping(value = {"/official/allarticle"}, method = RequestMethod.POST)
	public String article_post(Map<String, Object> model) {
		Admin admin= AdminHP.getAdmin();
		model.put(ViewKeyDict.enterprisercatalog, EnterpriserArticleHP.getCatalogueList());
		model.put(ViewKeyDict.financecataloglist, FinanceCatalogueHP.getlist());
		model.put(ViewKeyDict.channel, EChannel.article.name());
		model.put(ViewKeyDict.careercataloglist, CareerArticleHP.getCatalogueList());
		model.put(ViewKeyDict.startupcataloglist, StartupArticleHP.getCatelogueList());
		model.put(ViewKeyDict.photopath, SysConf.AllarticlePhotoPath);
		model.put(ViewKeyDict.admin, admin);
		return "doallarticle";
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
	@RequestMapping(value = { "/official/allarticle/uploadimg" }, method = RequestMethod.POST)
	@ResponseBody
	public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
		String data = OfficialArticleHP.resizeImage(fileimg, SysConf.AllarticlePhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
		return data;
	}

}