package com.supergenius.xo.moral.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.moral.moral.dao.SignDao;
import com.supergenius.xo.moral.entity.Sign;

/**
 * 签到实现
 * @author liushaomin
 */
@Component
public class SignDaoImpl extends BaseMongoDaoImpl<Sign> implements SignDao{

}
