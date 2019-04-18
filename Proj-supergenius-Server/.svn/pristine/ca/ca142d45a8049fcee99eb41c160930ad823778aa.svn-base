package com.supergenius.server.moral.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.entity.Message;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.common.service.MessageSO;
import com.supergenius.xo.moral.entity.Student;
import com.supergenius.xo.moral.service.StudentSO;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.service.UserSO;

/**
 * 消息HP
 * @author YuYingJie
 */
public class BaseMessageHP extends BaseHP {
	
	private static MessageSO so;
	
	private static UserSO userSO;
	
	private static StudentSO studentSO;
	
	private static MessageSO getSO() {
		if (so == null) {
			so = (MessageSO) spring.getBean(MessageSO.class);
		}
		return so;
	}
	
	private static UserSO getUserSO() {
		if (userSO == null) {
			userSO = (UserSO) spring.getBean(UserSO.class);
		}
		return userSO;
	}
	
	private static StudentSO getStudentSO() {
		if (studentSO == null) {
			studentSO = (StudentSO) spring.getBean(StudentSO.class);
		}
		return studentSO;
	}
	
	/**
	 * 根据系统发消息的用户类别返回用户的Oids
	 * @param userType
	 * @return
	 * @author YuYingJie
	 */
	public static List<Integer> getUserOidsByType(EUser userType) {
		List<Integer> oids = new ArrayList<>();
		if (EUser.all.equals(userType)) {
			List<User> users = getUserSO().getList();
			for (User user : users) {
				oids.add(user.getOid());
			}
		} else if (EUser.user.equals(userType)) {
			List<User> users = getUserSO().getList();
			List<Student> students = getStudentSO().getList();
			boolean isStudent = false;
			for (User user : users) {
				isStudent = false;
				for (Student student : students) {
					if (user.getUid().equals(student.getUseruid())) {
						isStudent = true;
						break;
					}
				}
				if (!isStudent) {
					oids.add(user.getOid());
				}
			}
		} else if (EUser.studentMoral.equals(userType)) {
			List<Student> students = getStudentSO().getList();
			for (Student student : students) {
				User user = getUserSO().get(student.getOid());
				oids.add(user.getOid());
			}
		}
		return oids;
	}
	
	/**
	 * 发送消息
	 * @param fromuseruid
	 * @param fromuseroid
	 * @param fromusername
	 * @param touseruid
	 * @param touseroid
	 * @param tousername
	 * @param useravatar
	 * @param title
	 * @param content
	 * @param href
	 * @param state
	 * @param type
	 * @param site
	 * @param sn
	 * @param data
	 * @return
	 * @author YuYingJie
	 */
	public static boolean sendMessage(String fromuseruid, int fromuseroid, String fromusername, String touseruid, int touseroid, String tousername, String useravatar, String title, String content, String href, EMsgState state, EMsg type, ESite site, String sn, Map<String, String> data) {
		Message message = new Message(fromuseruid, fromuseroid, fromusername, touseruid, touseroid, tousername, useravatar, title, content, href, state, type, site, sn, data);
		return getSO().add(message);
	}
	
}
