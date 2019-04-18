package com.supergenius.xo.tpi.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.EmailTemplate;
import com.supergenius.xo.tpi.enums.ESysEmailType;

/**
 * 邮件模板so
 * 
 * @author LiuXiaoke
 */
public interface EmailTemplateSO extends BaseSO<EmailTemplate>{
	
	public EmailTemplate getOneByType(ESysEmailType type);
	
}
