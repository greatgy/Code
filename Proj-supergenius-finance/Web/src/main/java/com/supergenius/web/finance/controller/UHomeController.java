package com.supergenius.web.finance.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.finance.helper.UhomeHP;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.entity.Subscribe;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 他的主页
 * 
 * @author LouPengYu
 * @date 2018年1月7日14:50:17
 *
 */
@Controller
public class UHomeController extends BaseController {
	
	@Autowired
	private UserSO userso;
	
	/**
	 * 进入他的主页
	 * 
	 * @author LouPengYu
	 * @date 2018年1月7日14:50:20
	 *
	 */
	@RequestMapping(value = {"finance/uhome/{authoruid:.{32}}"}, method = RequestMethod.GET)
	public String index(Map<String, Object> model, @PathVariable String authoruid, HttpServletRequest request) {
		
		List<Article> list = UhomeHP.getArticleList(authoruid, 1, SysConf.HisArticleSize);
		User user = BaseUserHP.getCurrUser(request);
		User author =userso.get(authoruid);
		List<Article> hisHotArticle = UhomeHP.gethisHotArticle(authoruid);
		if (author != null) {
			if (user != null) {// 是否订阅过
				Subscribe subscribe = UhomeHP.getSubscribe(user.getUid(), author.getUid());
				if (subscribe != null) {
					model.put(ViewKeyDict.isSubscribe, true);
			    } else {
					model.put(ViewKeyDict.isSubscribe, false);
			      }
			}
		}
		model.put(ViewKeyDict.hotarticles, hisHotArticle);
		model.put(ViewKeyDict.user, user);
		model.put(ViewKeyDict.author, author);
		model.put(ViewKeyDict.count, UhomeHP.getHisCount(authoruid));
		model.put(ViewKeyDict.list,list);
		return "uhome" ;
	}
	/**
	 * 他的文章更多
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @author loupengyu
	 * @date  2018年1月7日14:51:37
	 */
	@RequestMapping(value = "/ajax/his/article/{uid:.{32}}", method = RequestMethod.GET)
	public String ajax_msg(Map<String, Object> model, @PathVariable String uid, int pagenum, HttpServletRequest request) {
		List<Article> list  = UhomeHP.getArticleList(uid, pagenum, SysConf.HisArticleSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	
	}
}
