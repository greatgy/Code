package com.supergenius.web.admin.moralnews.controller;

import com.genius.core.base.annotation.Json;
import com.genius.core.base.annotation.Maps;
import com.genius.core.base.conf.BaseSysConf;
import com.genius.core.base.constant.BaseStrDict;
import com.genius.core.base.utils.DateUtil;
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
import com.supergenius.global.conf.SysConf;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.moralnews.helper.MoralnewsArticleHP;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.moralnews.rule.MoralnewsTopArticleRule;
import com.supergenius.xo.moralnews.entity.Article;
import com.supergenius.xo.moralnews.enums.ECatalogue;
import com.supergenius.xo.moralnews.service.ArticleSO;
import com.supergenius.xo.moralnews.service.CatalogueSO;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 职业道德帖子管理
 * @author tf
 * @date 2018年9月18日
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralNewsArticleAdminer extends BaseController{


    @Autowired
    private ArticleSO so;

    @Autowired
    private CatalogueSO catalogueSO;

    @Autowired
    private AdminLogSO adminLogSO;

    /**
     * 文章管理页面
     *
     * @author ChenQi
     * @date 2018年1月5日15:25:29
     * @return String
     */
    @RequestMapping(value = "/moralnews/{channel:[a-z]+}", method = RequestMethod.GET)
    public String article(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
        EChannel eChannel = EChannel.get(channel);
        model.put(ViewKeyDict.channel, eChannel.name());
        model.put(ViewKeyDict.channelname, EChannel.getName(eChannel, Locale.CHINA));
        model.put(ViewKeyDict.site, ESite.moralnews.name());
        model.put(ViewKeyDict.photopath, SysConf.MoralNewsPhotoPath);
        model.put(ViewKeyDict.catelogueList, catalogueSO.getList()); // 获取目录列表
        if(channel.equals("moralnewsarticle")){
            return "domoralnewsarticle";
        }
        return "domoralnewssimplearticle";
    }

    /**
     * 显示列表
     *
     * @author ChenQi
     * @date 2018年1月5日15:25:32
     * @return String
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
        cloneParamsToModel(model, request);
        Map<String, Object> searchMap = MoralnewsArticleHP.query(model, channel);
        return json(searchMap, Json.webStrategy);
    }

    /**
     * 删除文章
     *
     * @param ids
     * @return
     * @author ChenQi
     * @data 2018年1月5日15:25:36
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/delete", method = RequestMethod.GET)
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
                if (article != null) {
                    AdminLog adminLog = new AdminLog();
                    adminLog.setAdminuid(AdminHP.getAdminUid());
                    adminLog.setChannel(EChannel.article.toInt());
                    adminLog.setOperation(EAdminLog.deleteMoralnewsArticle.getName());
                    adminLog.setData(EAdminLog.deleteMoralnewsArticle.getName());
                    adminLog.setDesc(EAdminLog.deleteMoralnewsArticle.getName());
                    adminLog.setDataid(article.getUid());
                    adminLogSO.add(adminLog);
                    MoralnewsArticleHP.Cache(article);
                    uidmap.put(MapperDict.uid, article.getUid());
                    MoralnewsArticleHP.getEngine().delete(uidmap);
                }
            }
            return success();
        }
        return result(MsgKeyDict.deleteFailed);
    }

    /**
     * 冻结解冻
     *
     * @param
     * @return
     * @author ChenQi
     * @data 2018年1月5日15:25:40
     */
    @RequestMapping(value = "/moralnews/ajax/moralnewsarticle/status/{status:\\d+}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> article_delete(String uid, @PathVariable int status) {
        Article article = so.get(uid);
        String adminuid = AdminHP.getAdminUid();
        if (article != null) {
            AdminLog adminLog = new AdminLog();
            adminLog.setAdminuid(adminuid);
            adminLog.setChannel(EChannel.article.toInt());
            adminLog.setOperation(EAdminLog.deleteMoralnewsArticle.getName());
            adminLog.setData(EAdminLog.deleteMoralnewsArticle.getName());
            adminLog.setDesc(EAdminLog.deleteMoralnewsArticle.getName());
            adminLog.setDataid(uid);
            adminLogSO.add(adminLog);
            article.setStatus(EStatus.get(status));
            article.setAdminuid(adminuid);
            if (so.update(article)) {
                MoralnewsArticleHP.Cache(article);
                if (EStatus.get(status) == EStatus.disable) {
                    MoralnewsArticleHP.getEngine().deleteByID(article.getUid());
                } else if (EStatus.get(status) == EStatus.enable) {
                    Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
                    map.put(MapperSearchDict.table, ESite.moralnews.name());
                    MoralnewsArticleHP.getEngine().add(map);
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
     * @param title
     * @param author
     * @param origin
     * @param origin_radio
     * @param type_radio
     * @param summary
     * @param content
     * @return
     * @author ChenQi
     * @date 2018年1月5日15:25:44
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> article_edit(String uid, String contributeimg, String[] cids, String booktimeStr, String title, String author, String origin, String origin_radio, String type_radio,
                                            String summary, String content, String keywords) {
        Article article = so.get(uid);
        String adminUid = AdminHP.getAdminUid();
        if (article != null) {
            int cid = 0;
            for (String item : cids) {
                cid = cid | Integer.valueOf(ECatalogue.get(item).toString());
            }
            article.setCid(cid);
            article.setTitle(title);
            article.setAuthor(author);
            article.setOrigin(origin);
            article.setKeywords(keywords);
            article.setKind(Integer.parseInt(type_radio));
            article.setIsoriginal(Integer.parseInt(origin_radio));
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
            adminLog.setChannel(EChannel.article.toInt());
            adminLog.setDataid(article.getUid());
            adminLog.setDesc(EAdminLog.updateMoralnewsArticle.getName());
            adminLog.setData(EAdminLog.updateMoralnewsArticle.getName());
            adminLog.setOperation(EAdminLog.updateMoralnewsArticle.getName());
            if (StrUtil.isNotEmpty(adminUid)) {
                article.setAdminuid(adminUid);
            }
            if (StrUtil.isNotEmpty(booktimeStr)) {
                article.setCreatetime(DateUtil.parse(booktimeStr));
            }
            if (so.update(article)) {
                File SEOfile = new File(BaseSysConf.getSEOPath(ESite.moralnews.name()));
                SEOfile.setLastModified(System.currentTimeMillis());
                MoralnewsArticleHP.Cache(article);
                if (article.getStatus().equals(EStatus.enable)) {// 删除索引后在添加索引
                    MoralnewsArticleHP.getEngine().deleteByID(article.getUid());
                    article.setContent(WebUtil.clearHtmlTag(article.getContent()).toString());// 清除格式
                    Map<String, Object> map = MapsUtil.toMap(article, Maps.searchStrategy);
                    map.put(MapperSearchDict.table, ESite.moralnews.name());
                    MoralnewsArticleHP.getEngine().add(map);
                } else {
                    MoralnewsArticleHP.getEngine().deleteByID(article.getUid());
                }
                return success();
            }
        }
        return result(MsgKeyDict.updateFailed);
    }

    /**
     * 设置置顶
     *
     * @param
     * @return
     * @author ChenQi
     * @date 2018年1月5日15:25:49
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/enable", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> article_top(String[] ids) {
        Article article;
        Rule rule;
        for (String uid : ids) {
            article = so.get(uid);
            article.setIstop(1);
            article.setToptime(new DateTime(DateTimeZone.forOffsetHours(8)));
            if (!so.update(article)) {
                return result(MsgKeyDict.updateFailed);
            } else {
                rule = new MoralnewsTopArticleRule(String.valueOf(article.getCid()));
                MemcacheUtil.remove(rule);
            }
        }
        return success();
    }

    /**
     * 取消置顶
     *
     * @param
     * @return
     * @author ChenQi
     * @date 2018年1月5日15:25:53
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/disable", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> moralnews_untop(String[] ids) {
        Article article;
        Rule rule;
        for (String uid : ids) {
            article = so.get(uid);
            article.setIstop(0);
            if (!so.update(article)) {
                return result(MsgKeyDict.updateFailed);
            } else {
                rule = new MoralnewsTopArticleRule(String.valueOf(article.getCid()));
                MemcacheUtil.remove(rule);
            }
        }
        return success();
    }

    /**
     * 获取置顶的个数
     *
     * @author ChenQi
     * @date 2018年1月5日15:25:56
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/topcount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> article_topcount(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
        model.put(MapperDict.count, MoralnewsArticleHP.getTopArticleCount(MoralnewsArticleHP.getcid(channel)));
        return json(model, Json.webStrategy);
    }

    /**
     * 设置推荐
     *
     * @param
     * @return
     * @author ChenQi
     * @date 2018年1月5日15:25:49
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/recommend", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> article_recommend(String[] ids) {
        Article article;
        for (String uid : ids) {
            article = so.get(uid);
            if (!so.update(article)) {
                return result(MsgKeyDict.updateFailed);
            }
        }
        return success();
    }

    /**
     * 取消推荐
     *
     * @return
     * @author ChenQi
     * @date 2018年1月5日15:25:49
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/unrecommend", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> article_unrecommend(String[] ids) {
        Article article;
        for (String uid : ids) {
            article = so.get(uid);
            if (!so.update(article)) {
                return result(MsgKeyDict.updateFailed);
            }
        }
        return success();
    }

    /**
     * 获取推荐的个数
     *
     * @author ChenQi
     * @date 2018年1月5日15:25:56
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/recommendcount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> article_recommendcount(Map<String, Object> model, @PathVariable String channel, HttpServletRequest request) {
        model.put(MapperDict.count, MoralnewsArticleHP.getRecommendArticleCount(MoralnewsArticleHP.getcid(channel)));
        return json(model, Json.webStrategy);
    }

    /**
     * 添加文章
     *
     * @param title
     * @param author
     * @param origin
     * @param origin_radio
     * @param type_radio
     * @param summary
     * @param content
     * @return
     * @author ChenQi
     * @date 2018年1月5日16:48:15
     */
    @RequestMapping(value = "/moralnews/ajax/{channel:[a-z]+}/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> article_add(@PathVariable String channel, String[] cids, String publishtime, String title, String contributeimg, String author, String origin_radio, String origin,
                                           String type_radio, String summary, String content, String labelcontent, String SEOkeywords) {
        Article article = new Article();
        String adminUid = AdminHP.getAdminUid();
        if (StrUtil.isNotEmpty(title) && StrUtil.isNotEmpty(content) && StrUtil.isNotEmpty(origin_radio) && StrUtil.isNotEmpty(type_radio)) {
            article.setTitle(title);
            if (channel.equals(ViewKeyDict.moralnewsarticle) && StrUtil.isNotEmpty(cids)) {
                int cid = 0;
                for (String item : cids) {
                    cid = cid | Integer.valueOf(ECatalogue.get(item).toString());
                }
                article.setCid(cid);
            } else {
                article.setCid(MoralnewsArticleHP.getcid(channel));
            }
            article.setKind(Integer.parseInt(type_radio));
            article.setContent(content);
            article.setIsoriginal(Integer.parseInt(origin_radio));
        } else {
            return result(MsgKeyDict.addFailed);
        }
        article.setAuthor(author);
        article.setKeywords(SEOkeywords);
        article.setAuthoruid(AdminHP.getAdminUid());
        article.setOrigin(origin);
        article.setSummary(summary);
        article.setType(1);
        article.setCreatetime(new DateTime());
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
        if (StrUtil.isNotEmpty(publishtime)) {
            article.setCreatetime(DateUtil.parse(publishtime));
        }
        if (so.add(article)) {
            MoralnewsArticleHP.Cache(article);
            Article newArticle = so.get(article.getUid());// 获取更新后的文章，因为oid是存入数据库自动生成的
            newArticle.setContent(WebUtil.clearHtmlTag(newArticle.getContent()).toString());// 清除格式
            Map<String, Object> map = MapsUtil.toMap(newArticle, Maps.searchStrategy);// 增加索引
            map.put(MapperSearchDict.table, ESite.moralnews.name());
            MoralnewsArticleHP.getEngine().add(map);
            // 删除序列化文件
            return success();
        }
        return result(MsgKeyDict.addFailed);
    }

    /**
     * 上传图片处理
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = { "/moralnews/ajax/{channel:[a-z]+}/uploadimg" }, method = RequestMethod.POST)
    @ResponseBody
    public String upload(Map<String, Object> model, @RequestParam MultipartFile fileimg, HttpServletRequest request) {
        String data = MoralnewsArticleHP.resizeImage(fileimg, SysConf.MoralNewsPhotoPath, SysConf.TitleImgShowSizes, SysConf.ImgSiteBasePath);
        return data;
    }
    
    /**
     * 贴子置顶
     * 
     * @return
	 * @author tf
	 */
    @RequestMapping(value = { "/moralnews/ajax/moralnewssimplearticle/istop/{istop:\\d+}" }, method = RequestMethod.GET)
	@ResponseBody                              
	public Map<String, Object> article_istop(String ids, @PathVariable int istop) {
		if (0 == istop && so.setTop(ids, istop == 0 ? false : true)) {
			return success();
		} else if (so.checkIsTop(ids) && so.setTop(ids, istop == 0 ? false : true)) {
			return success();
		}
		return result(MsgKeyDict.doFailed);
	}
    
}