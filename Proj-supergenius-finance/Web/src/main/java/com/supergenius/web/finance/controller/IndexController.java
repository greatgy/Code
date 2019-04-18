package com.supergenius.web.finance.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.finance.helper.ArticleHP;
import com.supergenius.web.finance.helper.IndexHP;
import com.supergenius.xo.finance.entity.Article;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.finance.service.ArticleSO;

/**
 * 首页controller
 * 
 * @author: ChenQi
 * @date 2017年11月14日10:24:34
 */
@Controller
public class IndexController extends BaseController {
	
	@Autowired
	ArticleSO so;
	/**
	 * 跳转到非会员提醒页面
	 * 
	 * @param model
	 * @return
	 * @author:JiaShitao
	 */
	@RequestMapping(value = { "/notmember" }, method = RequestMethod.GET)
	public String notMember() {
		return "notmember";
	}
	
	/**
	 * 跳转到首页并展示数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author: ChenQi 2017年11月14日10:24:45
	 */
	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public String index(Map<String, Object> model, HttpServletRequest request) {
		IndexHP.handleCache();
		model.put(ViewKeyDict.HotArticle, IndexHP.getHotArticles(SysConf.HotArticleSize, 0));
		model.put(ViewKeyDict.HotLabel, IndexHP.getHotLabel(SysConf.HotLabelSize));
		model.put(ViewKeyDict.RecommendArticle, IndexHP.getRecommendArticle());
		model.put(ViewKeyDict.ADContent, IndexHP.getADContent(EContent.get(0)));
		model.put(ViewKeyDict.goldlanguage, IndexHP.getGoldLanguage());
		model.put(ViewKeyDict.FinanceArticle, IndexHP.getRecommendEconomicsArticle());
		model.put(ViewKeyDict.list, IndexHP.getIndexArticle(SysConf.FirstArticleSize));
		model.put(ViewKeyDict.pagesize, SysConf.FirstLoadSize);
		model.put(ViewKeyDict.FirstLoadSize, SysConf.FirstArticleSize);
		IndexHP.SerializeOfficial(model, request);
		IndexHP.SerializeFinanceArticle(model, request);
		return "index";
	}
	
	/**
	 * 首页下拉加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/first/article", method = RequestMethod.GET)
	public String ajax_firstArticle(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Article> list = IndexHP.getFirstMore(pagenum);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	}
	
	/**
	 * 加载文章
	 * 
	 * @param model
	 * @param pagenum
	 * @param pagesize
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/index/article", method = RequestMethod.GET)
	public String ajax_indexArticle(Map<String, Object> model, int pagenum, int pagesize, HttpServletRequest request) {
		List<Article> list = IndexHP.getMoreArticles(pagenum, pagesize);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	}
	
	/**
	 * 官网首页加载
	 * @param models
	 * @param pagenum
	 * @param request
	 * @author YangGuang
	 */
	@RequestMapping(value = "/ajax/official/index", method = RequestMethod.GET)
	public String ajax_firstArticle(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = IndexHP.getOfficial();
		model.put(ViewKeyDict.list, list);
	    return "ajaxofficial";
	}
	
	//prize=0&collect=0&comment=0&domain=localhost&url=http://localhost:9081/article/11/19&title=天财评论文章详情页&referrer=http://localhost:9081/&sh=900&sw=1440&cd=24&lang=zh-CN&username=liubin&islogin=1&useruid=eab2271aa0f74162923d1ef930187c6d&isMember=true&articleuid=f2d11dd899e140288f0c449b031772cb&articlecid=11&time=4.159&strStart=2018-7-26 16:31:13&strEnd=2018-7-26 16:31:17
    @RequestMapping(value = "/log.gif")
    public void analysis(String args, HttpServletResponse response) throws IOException {
        String[] data = args.split("&");
        Map<String, Object> map = new HashMap<String, Object>();
        for (String temp : data) {
			String[] tmp = temp.split("=");
			if (tmp.length > 1) {
				map.put(tmp[0], tmp[1]);
			} else {
				map.put(tmp[0], "");
			}
		}
        String json = JsonUtil.toJson(map);
        File file = new File(SysConf.SerialBasePath + SysConf.SerialFinanceDataPath);
        if (!file.exists()) {
        	file.createNewFile();
        }
        try {
        	BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (SysConf.SerialBasePath + SysConf.SerialFinanceDataPath,true),"UTF-8"));
            bw.write(json + "\r\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * 有偿信息页面
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value = "/classroom", method = RequestMethod.GET)
	public String classroom(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		List<String> uidlist = new ArrayList<String>(){{
			add("f8744185107349b3b2d37bc43fac01ac");
			add("a3fc0726b8ef406abc40287789501431");
			add("4ee07e2725be4d618024645cdd241b4a");
			add("466eadbc458a4a1f85e80f12caa17cca");
			add("4c4591503fb243b9aafdd6b2a3204a51");
		}};
		Map<String, Object> map = new HashMap<>();
		map.put("status", true);
		map.put(BaseMapperDict.ascDesc, BaseMapperDict.desc);
		map.put(BaseMapperDict.startIndex, 0);
		map.put("uidlist", uidlist);
		List<Article> list = so.getList(map);
		list.forEach(article -> {
			IndexHP.setSummary(article);
		});
		model.put("list", list);
		return "ajaxclassroom";
	}
	
	/**
	 * 文章查看详情页面
	 * 
	 * @param model
	 * @param cid
	 * @return
	 * @author YangGuang
	 * @date 2018年1月3日11:43:31
	 */
	@RequestMapping(value = "/classroom/{oid:\\d+}", method = RequestMethod.GET)
	public String article_detial(Map<String, Object> model, @PathVariable int oid, HttpServletRequest request, HttpServletResponse response) {
		Article article = ArticleHP.getArticle(oid);
		IndexHP.setSummary(article);
		model.put(ViewKeyDict.bean, article);
		return "ajaxdetail";
	}
}