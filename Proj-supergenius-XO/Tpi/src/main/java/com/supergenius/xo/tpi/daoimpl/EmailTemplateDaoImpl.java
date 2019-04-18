package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.EmailTemplateDao;
import com.supergenius.xo.tpi.entity.EmailTemplate;

/**
 * EmailTemplateDaoImpl
 * @author liushaomin
 */
@Component
public class EmailTemplateDaoImpl extends BaseMongoDaoImpl<EmailTemplate> implements EmailTemplateDao{

}
