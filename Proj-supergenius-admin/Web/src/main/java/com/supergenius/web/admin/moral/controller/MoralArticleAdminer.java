package com.supergenius.web.admin.moral.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.entity.AdminLog;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.moral.helper.BaseStudentHP;
import com.supergenius.server.user.helper.BaseUserHP;
import com.supergenius.web.admin.moral.helper.ArticleHP;
import com.supergenius.web.admin.moral.helper.MessageHP;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.moral.entity.Article;
import com.supergenius.xo.moral.entity.UserStatistics;
import com.supergenius.xo.moral.enums.EScoreDetail;
import com.supergenius.xo.moral.service.ArticleSO;
import com.supergenius.xo.moral.service.CommentSO;
import com.supergenius.xo.moral.service.CountDetailSO;
import com.supergenius.xo.moral.service.UserStatisticsSO;

/**
 * 用户发帖
 * 
 * @author liushaomin
 * @modifier Lijiacheng
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class MoralArticleAdminer extends BaseController {

	@Autowired
	ArticleSO so;

	@Autowired
	CommentSO commentSO;

	@Autowired
	CountDetailSO countDetailSO;

	@Autowired
	UserStatisticsSO userStatisticsSO;

	/**
	 * 打开用户发帖管理
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/moralarticle", method = RequestMethod.GET)
	public String article(Map<String, Object> model, HttpServletRequest request) {
		model.put(ViewKeyDict.channel, EChannel.moralarticle.name());
		model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.moralarticle, Locale.CHINA));
		model.put(ViewKeyDict.site, EChannel.moral.name());
		return "domoralarticle";
	}

	/**
	 * 查询组织数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/moralarticle/list" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> article_list(Map<String, Object> model, HttpServletRequest request) {
		cloneParamsToModel(model, request);
		Map<String, Object> searchMap = ArticleHP.query(model);
		return json(searchMap, Json.webStrategy);
	}

	/**
	 * 更新状态
	 * 
	 * @param ids
	 * @param status
	 * @param adminLog
	 * @param dopwd
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = "/moral/ajax/moralarticle/status/{status:\\d+}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> article_status(String[] ids, @PathVariable int status, AdminLog adminLog, String dopwd) {
		if (AdminHP.isDopwd(dopwd)) {
			if (so.update(EStatus.get(status), ids)) {
				return success();
			}
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 帖子置顶
	 * 
	 * @param ids
	 * @param istop
	 * @param adminLog
	 * @param dopwd
	 * @return
	 * @author LiJiacheng
	 */
	@RequestMapping(value = "/moral/ajax/moralarticle/istop/{istop:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_top(String ids, @PathVariable int istop) {
		Article article = so.get(ids);
		if (so.setTop(ids, istop == 0 ? false : true)) {
			if (1 == istop) {
				MessageHP.sendNoticeMsg(BaseUserHP.get(article.getUseruid()), article.getTitle(), WebConf.baseMoralPath + WebConf.MoralArticleUrlPath + article.getUid(), EMsg.placetop);
				if (!countDetailSO.articleIsTop(ids)) {
					countDetailSO.articleTop(ids);
					UserStatistics userStatistics = userStatisticsSO.getOneByUseruid(article.getUseruid());
					BaseStudentHP.updateScore(article.getUseruid(), userStatistics.getScore() + 10, userStatistics.getScore(), EScoreDetail.istop, AdminHP.getAdmin(), null);
				}
			}
			return success();
		}
		return result(MsgKeyDict.updateFailed);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @param request
	 * @return
	 * @author liushaomin
	 */
	@RequestMapping(value = { "/moral/ajax/moralarticle/delete" }, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> article_delete(String ids) {
		Article article = so.get(ids);
		if (so.deleteByUids(ids) && commentSO.deleteArticleComment(ids) && countDetailSO.deleteArticleCountDetail(ids)) {
			UserStatistics userStatistics = userStatisticsSO.getOneByUseruid(article.getUseruid());
			BaseStudentHP.updateScore(article.getUseruid(), userStatistics.getScore() - 5, userStatistics.getScore(), EScoreDetail.delArticle, AdminHP.getAdmin(), null);
			MessageHP.sendNoticeMsg(BaseUserHP.get(article.getUseruid()), article.getTitle(), null, EMsg.delarticle);
			return success();
		}
		return result(MsgKeyDict.deleteFailed);
	}

}
