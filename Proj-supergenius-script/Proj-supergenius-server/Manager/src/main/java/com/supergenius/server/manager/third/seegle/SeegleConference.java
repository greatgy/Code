package com.supergenius.server.manager.third.seegle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.supergenius.server.manager.third.seegle.entity.AuthEntity;
import com.supergenius.server.manager.third.seegle.entity.ConfEntity;
import com.supergenius.server.manager.third.seegle.entity.ConfListEntity;
import com.supergenius.server.manager.third.seegle.entity.ConfLogEntity;
import com.supergenius.server.manager.third.seegle.entity.LoginEntity;
import com.supergenius.server.manager.third.seegle.url.ConfUrlBuilder;
import com.supergenius.server.manager.third.seegle.util.HttpClientUtil;

/**
 * @author thomasong
 * @modify chenminchang
 */
public class SeegleConference implements Serializable {
	private static final long serialVersionUID = -6613438030970155715L;

	/**
	 * 添加会议室,成功：返回会议室的cid，失败：返回null
	 * 
	 * @param auth
	 * @param conf
	 * @return
	 */
	public static String add(AuthEntity auth, ConfEntity conf) {
		String addUrl = ConfUrlBuilder.buildAddUrl(auth, conf);
		System.out.println("CreateSeegleUrl:" + addUrl);
		String result = HttpClientUtil.get(addUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = new JSONObject();
		try{
			js = JSONObject.fromObject(substr);
		} catch (JSONException e) {
			if (substr.contains("msg:"))//substr: msg:500  不是{msg:500}则不能转成json
				return substr.substring(substr.indexOf("msg:") + 4);//500
			else
				throw e;
		}
		if ("0".equals(String.valueOf(js.get("msg"))))
			return String.valueOf(js.get("confid"));
		else
			System.out.println("Create Seegle fail， createnum:" + String.valueOf(js.get("msg")));
		return null;
	}

	/**
	 * 获取所有的会议
	 * 
	 * @param token
	 * @return
	 */
	public static List<ConfListEntity> list(String token) {
		String listUrl = ConfUrlBuilder.buildListUrl(token);
		String result = HttpClientUtil.get(listUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONArray js = JSONArray.fromObject(substr);
		int size = js.size();
		List<ConfListEntity> list = new ArrayList<ConfListEntity>();
		for (int i = 0; i < size; i++) {
			ConfListEntity temp = new ConfListEntity();
			JSONObject tjs = js.getJSONObject(i);
			temp.setCid(tjs.getString("cid"));
			temp.setBeginTime(tjs.getString("btime"));
			temp.setConfname(tjs.getString("confname"));
			temp.setEndTime(tjs.getString("etime"));
			temp.setMaxconfuser(tjs.getString("maxconfuser"));
			list.add(temp);
		}
		return list;
	}

	
	/**
	 * 删除会议
	 * 
	 * @param token
	 * @param confId
	 * @return
	 */
	public static String delete(String token, String confId) {
		String deleteUrl = ConfUrlBuilder.buildDeleteUrl(token, confId);
		String result = HttpClientUtil.get(deleteUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = new JSONObject();
		try{
			js = JSONObject.fromObject(substr);
		} catch (JSONException e) {
			if (substr.contains("msg:"))
				return substr.substring(substr.indexOf("msg:") + 4);
			else
				throw e;
		}
		return String.valueOf(js.get("msg"));
	}

	/**
	 * 获取会议
	 * 
	 * @param token
	 * @param confId
	 * @return ConfEntity
	 */
	public static ConfEntity get(String token, String confId) {
		String getUrl = ConfUrlBuilder.buildGetUrl(token, confId);
		String result = HttpClientUtil.get(getUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = new JSONObject();
		try{
			js = JSONObject.fromObject(substr);
		} catch (JSONException e) {
			return null;
		}
		ConfEntity entity = new ConfEntity();
		entity.setAuto_clear_flag(js.getString("confbegintime"));
		entity.setBegintime(js.getString("confbegintime"));
		entity.setEndtime(js.getString("confendtime"));
		entity.setConf_password_md5(js.getString("confpasswordmd5"));
		entity.setConfname(js.getString("confname"));
		entity.setGrouptype(js.getString("confgrouptype"));
		entity.setManage_password_md5(js.getString("managepasswordmd5"));
		entity.setMax_conf_spokesman(js.getString("maxconfspokesman"));
		entity.setMax_conf_tourist(js.getString("maxconftourist"));
		entity.setMax_conf_user(js.getString("maxconfuser"));
		entity.setCid(confId);
		// entity.setOpen_flag(js.getString("open_flag"));
		// entity.setConfsetting(js.getString("confsetting"));
		// entity.setDescription(js.getString("description"));
		// entity.setDownload_mode(js.getString("download_mode"));
		// entity.setLock_flag(js.getString("lock_flag"));
		// entity.setAll_can_visible(js.getString("all_can_visible"));
		return entity;
	}

	/**
	 * 更新会议 update conf
	 * 
	 * @param token
	 * @param conf
	 * @return
	 */
	public static String update(String token, ConfEntity conf) {
		String updateUrl = ConfUrlBuilder.buildUpdateUrl(token, conf);
		String result = HttpClientUtil.get(updateUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = new JSONObject();
		try{
			js = JSONObject.fromObject(substr);
		} catch (JSONException e) {
			if (substr.contains("msg:"))
				return substr.substring(substr.indexOf("msg:") + 4);
			else
				throw e;
		}
		return String.valueOf(js.get("msg"));
	}

	/**
	 * 获取登录会议登录信息
	 * 
	 * @param token
	 * @param cid
	 * @return
	 */
	public static LoginEntity login(String token, String cid) {
		String loginUrl = ConfUrlBuilder.buildLoginUrl(token, cid);
		String result = HttpClientUtil.get(loginUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONObject js = JSONObject.fromObject(substr);
		LoginEntity entity = new LoginEntity();
		entity.setIps(js.getString("ips"));
		// entity.setOrgid(js.getString("orgid"));
		entity.setRid(js.getString("rid"));
		entity.setUserid(js.getString("userid"));
		entity.setUserpass(js.getString("userpass"));
		return entity;
	}

	/**
	 * 获取所有会议室的日志(本地测试不通过)
	 * 
	 * @param token
	 * @return
	 * @author chenminchang
	 * @create 2016-9-9上午9:59:27
	 */
	public static List<ConfLogEntity> allLog(String token) {
		String logUrl = ConfUrlBuilder.buildAllLogUrl(token);
		String result = HttpClientUtil.get(logUrl);
		String substr = result.substring(5, result.length() - 1);
		JSONArray jsArr = JSONArray.fromObject(substr);
		int size = jsArr.size();
		if (JSONUtils.isNull(jsArr) && size > 0)
			return null;
		List<ConfLogEntity> list = new ArrayList<ConfLogEntity>();
		for (int i = 0; i < size; i++) {
			ConfLogEntity entity = new ConfLogEntity();
			JSONObject js = jsArr.getJSONObject(i);
			entity.setCid(js.getString("cid"));
			entity.setCname(js.getString("cname"));
			entity.setConfusernum(js.getString("confusernum"));
			entity.setUsenum(js.getString("usenum"));
			entity.setUsetotaltime(js.getString("usetotaltime"));
			entity.setUseavgtime(js.getString("useavgtime"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 根据名称获取会议
	 * @param token
	 * @return
	 */
	public static ConfListEntity getByName(String token,String name){
		String listUrl=ConfUrlBuilder.buildListUrl(token,name);
		String result= HttpClientUtil.get(listUrl);
		if (result != null) {
			String substr=result.substring(5,result.length()-1);
			JSONArray js=JSONArray.fromObject(substr);
			ConfListEntity temp=new ConfListEntity();
			JSONObject tjs=js.getJSONObject(0);
			temp.setCid(tjs.getString("cid"));
//			temp.setBeginTime(tjs.getString("btime"));
			temp.setConfname(tjs.getString("confname"));
			temp.setEndTime(tjs.getString("etime"));
//			temp.setMaxconfuser(tjs.getString("maxconftourist"));
			return temp;
		}
		return null;
	}

	//@SuppressWarnings("unused")
	public static void main(String[] args) {
		String token = SeegleToken.getAdminToken();
		List<ConfListEntity> list = list(token);
		System.out.println(list.size());
		/*
		//将所有的会议室插入数据库，需要font层调用此main函数
		int sn = 0;
		for (ConfListEntity confL : list) {
			ConfEntity conf = get(token, confL.getCid());
			if (conf != null) {
				Conference conference = new Conference();
				conference.setSn("CON" + ++sn);
				conference.setCid(conf.getCid());
				if (StrUtil.isNotEmpty(conf.getBegintime()))
					conference.setBegintime(DateUtil.parse(conf.getBegintime()));
				if (StrUtil.isNotEmpty(conf.getEndtime()))
					conference.setEndtime(DateUtil.parse(conf.getEndtime()));
				conference.setName(conf.getConfname());
				conference.setType(EConfer.challenge);
				conference.setTypeuid("3541326532351632");
				conference.setTypename(conf.getConfname());
				conference.setMaxcount(Integer.valueOf(conf.getMax_conf_user()));
				conference.setMaxcountuser(Integer.valueOf(conf.getMax_conf_user()));
				conference.setMaxcounttourist(Integer.valueOf(conf.getMax_conf_tourist()));
				conference.setMaxcountspokesman(Integer.valueOf(conf.getMax_conf_spokesman()));
				conference.setPassword(conf.getConf_password_md5());
				conference.setPasswordadmin(conf.getManage_password_md5());
				conference.setDescription(conf.getDescription());
				BaseConferenceHP.add(conference);
			}
		}
		*/
		
	
		//添加会议室测试
		/*
		  AuthEntity auth = new AuthEntity(token); 
		  ConfEntity conf = new ConfEntity();
		  conf.setConfname("超天才之陈敏昌测试03");
		  conf.setBegintime("2016-10-17 11:00:00");
		  conf.setEndtime("2017-10-18 01:00:00");
		  conf.setMax_conf_user("10");
		  conf.setMax_conf_spokesman("10");
		  conf.setMax_conf_tourist("10");
		  conf.setConf_password_md5("123456");
		  conf.setManage_password_md5("123456");
		  conf.setDescription("chenminchangtest");
		  String result = add(auth, conf);
		  System.out.println(result);*/


		//测试获取登录参数
		String tk = SeegleToken.getUserToken("301", "d4580269d1664a1da1329e435fc984b2");
		System.out.println(tk);
		LoginEntity loginentity = SeegleConference.login(token, list.get(0).getCid());
		System.out.println(loginentity);
	}

}
