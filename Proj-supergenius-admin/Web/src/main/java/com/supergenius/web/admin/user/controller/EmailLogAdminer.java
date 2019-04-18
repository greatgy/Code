package com.supergenius.web.admin.user.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genius.core.base.annotation.Json;
import com.genius.server.base.controller.BaseController;
import com.genius.server.baseadmin.helper.AdminHP;
import com.supergenius.global.conf.UriConf;
import com.supergenius.server.common.constants.MsgKeyDict;
import com.supergenius.server.common.constants.ViewKeyDict;
import com.supergenius.web.admin.user.helper.EmailLogHP;
import com.supergenius.web.admin.user.helper.NewsHP;
import com.supergenius.web.admin.user.helper.UserHP;
import com.supergenius.xo.admin.entity.EmailLog;
import com.supergenius.xo.admin.service.EmailLogSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.user.service.UserSO;

/**
 * 群发邮件管理
 *
 * @author XieMing
 * @date 2016-5-23 上午11:05:44
 */
@Controller
@RequestMapping(value = UriConf.baseAdminPath)
public class EmailLogAdminer extends BaseController {

    @Autowired
    EmailLogSO so;

    @Autowired
    UserSO userSO;

    /**
     * 跳转到群发邮件管理页面
     *
     * @param model
     * @param request
     * @return
     * @author XieMing 2016-5-23 上午11:15:48
     */
    @RequestMapping(value = "/user/emaillog", method = RequestMethod.GET)
    public String emaillog(Map<String, Object> model, HttpServletRequest request) {
        model.put(ViewKeyDict.channel, EChannel.emaillog.name());
        model.put(ViewKeyDict.channelname, EChannel.getName(EChannel.emaillog, Locale.CHINA));
        model.put(ViewKeyDict.site, EChannel.user.name());
        return "doemaillog";
    }

    /**
     * 加载数据
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = {"/user/ajax/emaillog/list"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> emaillog_list(Map<String, Object> model, HttpServletRequest request) {
        cloneParamsToModel(model, request);
        Map<String, Object> searchMap = EmailLogHP.query(model);
        return json(searchMap, Json.webStrategy);
    }

    /**
     * 群发邮件
     *
     * @param useruids
     * @param title
     * @param content
     * @param request
     * @return
     */
    @RequestMapping(value = {"/user/ajax/emaillog/add"}, method = RequestMethod.POST)
    public Map<String, Object> emaillog_add(String useruids, String useruidsname, String title, String content, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        String[] uidsStrings = useruids.split(",");
        if (uidsStrings.length > 0) {
            for (int uid = 0; uid < uidsStrings.length; uid++) {
                UserHP.sentInfoEmail(userSO.get(uidsStrings[uid]), title, content);
                NewsHP.sendNews(userSO.get(uidsStrings[uid]), title, content, AdminHP.getAdminid());
                map.put(userSO.get(uidsStrings[uid]).getUid(), userSO.get(uidsStrings[uid]).getName());
            }
        }
        EmailLog emailLog = new EmailLog();
        emailLog.setAdminid(AdminHP.getAdminid());
        emailLog.setContent(content);
        emailLog.setTitle(title);
        emailLog.setDataMap(map);
        emailLog.setSender(AdminHP.getAdminid());
        emailLog.setCreatetime(DateTime.now());
        emailLog.setUpdatetime(DateTime.now());
        if (so.add(emailLog)) {
            return result(MsgKeyDict.submitSuccess);
        } else {
            return result(MsgKeyDict.addFailed);
        }
    }
}