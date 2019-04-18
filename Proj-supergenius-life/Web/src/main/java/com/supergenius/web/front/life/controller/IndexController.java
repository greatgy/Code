package com.supergenius.web.front.life.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.genius.core.base.utils.JsonUtil;
import com.genius.server.base.controller.BaseController;
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.constants.ViewKeyDict;
import com.supergenius.web.front.life.helper.ArticleHP;
import com.supergenius.web.front.life.helper.ContentHP;
import com.supergenius.web.front.life.helper.IndexHP;
import com.supergenius.xo.life.entity.Article;
import com.supergenius.xo.life.entity.Problem;
import com.supergenius.xo.life.entity.Topic;
import com.supergenius.xo.life.entity.Video;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.EContent;

/**
 * 首页controller
 * 
 * @author: ChenQi
 * @date 2017年11月14日10:24:34
 */
@Controller
public class IndexController extends BaseController {
	
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
		IndexHP.handleCache(Long.parseLong(ECatalogue.index.toString()));
		model.put(ViewKeyDict.list, ArticleHP.getFirstArticles(SysConf.FirstArticleSize));
		IndexHP.SerializeArticle(model, request);
		model.put(ViewKeyDict.problemList, IndexHP.getIndexProblem(0, SysConf.FirstProblemSize));
		model.put(ViewKeyDict.topicList, IndexHP.getIndexTopic(0, SysConf.FirstTopicSize));
		model.put(ViewKeyDict.videoList, IndexHP.getIndexVideo(0, SysConf.FirstVideoSize));
		model.put(ViewKeyDict.contentList, ContentHP.getContentList(EContent.ad, ECatalogue.index));
		model.put(ViewKeyDict.bannerList, ContentHP.getbannerList(ECatalogue.index));
		model.put(ViewKeyDict.pagesize, SysConf.LoadSize);
		model.put(ViewKeyDict.FirstLoadSize, SysConf.FirstArticleSize);
		model.put(ViewKeyDict.cid, 1);
		IndexHP.SerializeLifeArticle(model, request);
		return "index";
	}

	/**
	 * 按照文章原创加载
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author JiaShitao
	 */
	@RequestMapping(value = "/ajax/article/originalarticle", method = RequestMethod.GET)
	public String ajax_moreOriginalArticle(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Article> list = ArticleHP.getOriginalArticle(pagenum, SysConf.FirstArticleSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	}
	
	/**
	 * 首页下拉加载文章
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/first/article", method = RequestMethod.GET)
	public String ajax_firstArticle(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Article> list = ArticleHP.getFirstMore(pagenum, SysConf.FirstArticleSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxarticle";
	}
	
	/**
	 * 首页话题换一批
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/first/topic", method = RequestMethod.GET)
	public String ajax_firstTopic(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Topic> list = IndexHP.getIndexTopic(pagenum, SysConf.FirstTopicSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxindextopic";
	}
	
	/**
	 * 首页问答换一批
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/first/problems", method = RequestMethod.GET)
	public String ajax_firstProblem(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Problem> list = IndexHP.getIndexProblem(pagenum, SysConf.FirstProblemSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxindexproblem";
	}
	
	/**
	 * 首页视频换一批
	 * 
	 * @param model
	 * @param pagenum
	 * @param request
	 * @return
	 * @author 杨光
	 */
	@RequestMapping(value = "/ajax/first/video", method = RequestMethod.GET)
	public String ajax_firstVideo(Map<String, Object> model, int pagenum, HttpServletRequest request) {
		List<Video> list = IndexHP.getIndexVideo(pagenum, SysConf.FirstVideoSize);
		model.put(ViewKeyDict.list, list);
		return "ajaxindexvideo";
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
	 * 非会员用户温馨提示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notmember", method = RequestMethod.GET)
	public String notmember() {
		return "notmember";
	}
	
	/**
	 * 数据收集
	 * 
	 * @return
	 */
	//prize=0&collect=0&comment=0&domain=localhost&url=http://localhost:9093/article/11/19&title=天才AI文章详情页&referrer=http://localhost:9093/&sh=900&sw=1440&cd=24&lang=zh-CN&username=liubin&islogin=1&useruid=eab2271aa0f74162923d1ef930187c6d&isMember=true&articleuid=f2d11dd899e140288f0c449b031772cb&articlecid=11&time=4.159&strStart=2018-7-26 16:31:13&strEnd=2018-7-26 16:31:17
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
        File file = new File(SysConf.SerialBasePath + SysConf.SerialLifeDataPath);
        if (!file.exists()) {
        	file.createNewFile();
        }
        try {
        	BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (SysConf.SerialBasePath + SysConf.SerialLifeDataPath,true),"UTF-8"));
            bw.write(json + "\r\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
