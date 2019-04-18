package com.supergenius.web.admin.tpi.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genius.core.base.utils.EmailUtil;
import com.genius.core.base.utils.StrUtil;
import com.genius.model.base.entity.Pager;
import com.supergenius.global.conf.WebConf;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.server.tpi.helper.BaseTpiuserHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.tpi.entity.EmailTemplate;
import com.supergenius.xo.tpi.entity.TpiUser;
import com.supergenius.xo.tpi.enums.EInvestType;
import com.supergenius.xo.tpi.enums.EPayType;
import com.supergenius.xo.tpi.enums.ESysEmailType;
import com.supergenius.xo.tpi.enums.ETpiUserType;
import com.supergenius.xo.tpi.enums.ETpiuserState;
import com.supergenius.xo.tpi.service.TpiuserSO;

/**
 * 并购平台会员HP
 * 
 * @author ShangJianguo
 */
public class TpiuserHP extends BaseTpiuserHP {
	private static Logger log = LoggerFactory.getLogger(TpiuserHP.class);
	private static TpiuserSO so;

	private static TpiuserSO getSO() {
		if (so == null) {
			so = (TpiuserSO) spring.getBean(TpiuserSO.class);
		}
		return so;
	}
	
	/**
	 * 发送审核结果信息给用户
	 * @param uids 要发送消息的用户uid
	 * @param result 审核结果，true表示通过，false为不通过
	 * @param content 管理员审核时填写的审核内容
	 * @author ShangJianguo
	 */
	public static void sendAuditMsg2User(final String[] uids, final boolean result, final String content) {
		Map<String, String> map = new HashMap<>();
		if (result) {
			map.put(ViewKeyDict.result, WebConf.RegisterCheckPass);
		} else {
			map.put(ViewKeyDict.result, WebConf.RegisterCheckFail);
		}
		final EmailTemplate email = EmailTemplateHP.getProcessedEmail(ESysEmailType.registercheckresult, map);
		new Thread(new Runnable() {
			@Override
			public void run() {
				MessageHP.SendSysMsg(uids, email.getContent());
				for (String uid : uids) {
					TpiUser user = getSO().get(uid);
					log.info(email.getTitle() + user.getUsername() + ViewKeyDict.result + email.getContent());
					EmailUtil.send(user.getEmail(), email.getTitle(), email.getContent());
					log.info(email.getTitle() + user.getUsername() + ViewKeyDict.success);
				}
			}
		}).start();
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @author ShangJianguo
	 */
	public static Map<String, Object> query(Map<?, ?> model) {
		Pager pager = Pager.getNewInstance(model.get("page"), model.get("rows"));
		Map<String, Object> map = getParamMap(pager, model);
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.email))) {
			map.put(MapperDict.email + MapperDict.suffix_like_key, model.get(ViewKeyDict.email));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.username))) {
			map.put(MapperDict.username + MapperDict.suffix_like_key, model.get(ViewKeyDict.username));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.usersn))) {
			map.put(MapperDict.usersn + MapperDict.suffix_like_key, model.get(ViewKeyDict.usersn));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.type))) {
			map.put(MapperDict.type, ETpiUserType.getByEName(model.get(ViewKeyDict.type).toString()));
		}
		String investtype = ViewKeyDict.investInfo + ViewKeyDict.dot + ViewKeyDict.type;
		if (StrUtil.isNotEmpty(model.get(investtype))) {
			map.put(investtype, EInvestType.getByName(model.get(investtype).toString()));
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.state))) {
			map.put(MapperDict.state, ETpiuserState.getByName(model.get(ViewKeyDict.state).toString()));;
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.paytype))) {
			map.put(MapperDict.paytype, EPayType.getByName(model.get(ViewKeyDict.paytype).toString()));;
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.committimestart))) {
			String start = model.get(ViewKeyDict.committimestart).toString();
			DateTime startTime = DateTime.parse(start);
			map.put(MapperDict.committime + MapperDict.suffix_greaterOrEqual_key, startTime);
		}
		if (StrUtil.isNotEmpty(model.get(ViewKeyDict.committimeend))) {
			String end = model.get(ViewKeyDict.committimeend).toString();
			DateTime endTime = DateTime.parse(end);
			map.put(MapperDict.committime + MapperDict.suffix_lessOrEqual_key, endTime);
		}
		map.put(MapperDict.state + MapperDict.suffix_greater_key, ETpiuserState.emailvalid);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(ViewKeyDict.total, getSO().getCount(map));
		result.put(ViewKeyDict.rows, TpiuserHP.dealAddress(getSO().getList(map)));
		return result;
	}
	
}
