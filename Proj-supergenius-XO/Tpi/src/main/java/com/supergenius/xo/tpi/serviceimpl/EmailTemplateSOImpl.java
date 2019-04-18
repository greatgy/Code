package com.supergenius.xo.tpi.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.xo.base.serviceimpl.BaseSOImpl;
import com.supergenius.xo.tpi.constants.MapperDict;
import com.supergenius.xo.tpi.dao.EmailTemplateDao;
import com.supergenius.xo.tpi.entity.EmailTemplate;
import com.supergenius.xo.tpi.enums.ESysEmailType;
import com.supergenius.xo.tpi.service.EmailTemplateSO;

/**
 * 消息SO实现
 * @author LiuXiaoke
 *
 */
@Service
public class EmailTemplateSOImpl extends BaseSOImpl<EmailTemplate> implements EmailTemplateSO{

	@Autowired
	private EmailTemplateDao dao;
	
	@Override
	protected EmailTemplateDao getDao() {
		return dao;
	}
	
	public EmailTemplate getOneByType(ESysEmailType type) {
		Map<String, Object> map = new HashMap<>();
		map.put(MapperDict.type, type);
		return dao.getOne(map);
	}

}
