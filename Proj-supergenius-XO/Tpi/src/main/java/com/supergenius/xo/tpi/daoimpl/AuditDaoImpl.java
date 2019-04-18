package com.supergenius.xo.tpi.daoimpl;

import org.springframework.stereotype.Component;

import com.genius.xo.mongodb.daoimpl.BaseMongoDaoImpl;
import com.supergenius.xo.tpi.dao.AuditDao;
import com.supergenius.xo.tpi.entity.Audit;

/**
 * 用户Dao实现
 * 
 * @author ShangJianguo
 */
@Component
public class AuditDaoImpl extends BaseMongoDaoImpl<Audit> implements AuditDao {

}
