package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.MessageDao;
import com.supergenius.xo.tpi.entity.Message;

/**
 * MessageDaoImpl
 * @author liushaomin
 */
@Component
public class MessageDaoImpl extends BaseMongoDaoImpl<Message> implements MessageDao{

}
