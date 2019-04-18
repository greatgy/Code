package com.supergenius.web.finance.controller;

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
import com.genius.core.base.utils.MapsUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.core.base.utils.WebUtil;
import com.genius.core.search.constant.MapperSearchDict;
import com.genius.server.base.controller.BaseController;
import com.genius.server.base.helper.BaseHP;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.finance.helper.CommentsHP;
import com.supergenius.web.finance.helper.ContributeHP;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.enums.ECatalogue;
import com.supergenius.xo.finance.service.ArticleSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.entity.Visitor;

/**
 * 投稿controller
 * 
 * @author ChenQi
 * @date 2017年9月26日17:53:43
 */
@Controller
public class ContributeController extends BaseController {

	@Autowired
	private ArticleSO so;
	
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
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			model.put(ViewKeyDict.user, user);
		}
		if (StrUtil.isNotEmpty(cid)) {
			model.put(ViewKeyDict._id, Integer.parseInt(cid));
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
	@RequestMapping(value = { "/my/member/contribute" }, method = RequestMethod.POST)
	public String contribute(Map<String, Object> model, Article article, String[] cids, String contributeimg, HttpServletRequest request, HttpServletResponse response) {
		User user = BaseUserHP.getCurrUser(request);
		if (user != null) {
			model.put(ViewKeyDict.user, user);
		}
		boolean flag = true;
		if (StrUtil.isEmpty(article.getTitle()) || StrUtil.isEmpty(article.getContent())) {
			flag = false;
		}
		if (flag) {
			if (user != null) {
				article.setAuthoruid(user.getUid());// session中信息不全，考虑直接取数据库，没必要在redis中增加信息
				article.setAuthor(user.getShowname());
			} else {// 游客投稿, Contributor add
				Visitor visitor = CommentsHP.getVisitor(request, response);
				article.setAuthoruid(visitor.getUid());
				article.setAuthor(visitor.getNickname());
			}
			if (StrUtil.isNotEmpty(contributeimg)) {
				String[] imgdata = contributeimg.split(BaseStrDict.comma);
				article.setImgoriginal(imgdata[0]);
				article.setImgbig(imgdata[1]);
				article.setImgmedium(imgdata[2]);
				article.setImglittle(imgdata[3]);
			}
			int cid = 0;
			for (String item : cids) {
				cid = cid | Integer.valueOf(ECatalogue.get(item).toString());
			}
			article.setCid(cid);
			if (so.add(article)) {
				Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
				if (article.getType() == 1) {
					ContributeHP.Cache(article);
					if (StrUtil.isNotEmpty(article.getTags())) {
						ContributeHP.add(article.getUid(), article.getTags());
					}
					newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
					Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
					map.put(MapperSearchDict.table, ESite.finance.name());
					ContributeHP.getEngine().add(map);
					return redirectPrefix + "/article" + BaseStrDict.slash + newArticle.getFirstCid() + BaseStrDict.slash
							+ newArticle.getOid();
				} else if (article.getType() == 0) {
					return "contribute";
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

	/**
	 * 验证验证码
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/ajax/checkCode" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> upload(Map<String, Object> model, String checkCode, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StrUtil.isEmpty(checkCode) || !BaseHP.getCaptcha(request).equals(checkCode.trim())) {
			result.put(ViewKeyDict.result, false);// 验证码错误
		} else {
			result.put(ViewKeyDict.result, true);
		}
		return result;
	}
}