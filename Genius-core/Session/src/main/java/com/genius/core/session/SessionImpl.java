/**
 * Session的实现类，提供session操作的静态方法
 * ============================================================================
 * 声明：© 2014 genius.com, All Rights Reserved
 * ----------------------------------------------------------------------------
 * Official Website: http://www.genius.com
 * ----------------------------------------------------------------------------
 * Copyright: © 2014 genius All Rights Reserved.
 * ----------------------------------------------------------------------------
 * @version: 1.0
 * ----------------------------------------------------------------------------
 * @author: Architect.bian
 * ----------------------------------------------------------------------------
 * Create at: 2014-1-11 下午12:10:26
 * ============================================================================
 */
package com.genius.core.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionContext;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.genius.core.session.utils.SessionUtil;

/**
 * @author Architect.bian
 *
 */
@SuppressWarnings("deprecation")
@Component
@Lazy
@Scope("singleton")
public class SessionImpl implements Session {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private DateTime dateTime;
	
	/* 
	 * 通过name获得对应的值
	 */
	@Override
	public Object getAttribute(String name) {
		try {
			return SessionUtil.get(name);
		}
		catch(NullPointerException e) {
			return null;
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/* 
	 * 通过name设置value
	 */
	@Override
	public void setAttribute(String name, Object value) {
		SessionUtil.set(name, value);
	}
	
	/* 
	 * 通过name删除对应的键值对
	 */
	@Override
	public void removeAttribute(String name) {
		SessionUtil.remove(name);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getAttributeNames()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Deprecated
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getCreationTime()
	 */
	@Override
	@Deprecated
	public long getCreationTime() {
		if(dateTime == null) {
			dateTime = new DateTime();
		}
		return dateTime.getMillis();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getId()
	 */
	@Override
	@Deprecated
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getLastAccessedTime()
	 */
	@Override
	@Deprecated
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getServletContext()
	 */
	@Override
	@Deprecated
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#setMaxInactiveInterval(int)
	 */
	@Override
	@Deprecated
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getMaxInactiveInterval()
	 */
	@Override
	@Deprecated
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getSessionContext()
	 */
	@Override
	@Deprecated
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getValue(java.lang.String)
	 */
	@Override
	@Deprecated
	public Object getValue(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#getValueNames()
	 */
	@Override
	@Deprecated
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#putValue(java.lang.String, java.lang.Object)
	 */
	@Override
	@Deprecated
	public void putValue(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#removeValue(java.lang.String)
	 */
	@Override
	@Deprecated
	public void removeValue(String name) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#invalidate()
	 */
	@Override
	@Deprecated
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSession#isNew()
	 */
	@Override
	@Deprecated
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

}
