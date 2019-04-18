package com.supergenius.xo.ai.dao;

import org.springframework.stereotype.Component;

import com.genius.xo.base.dao.BaseDao;
import com.supergenius.xo.ai.entity.Label;

/**
 * 标签Dao
 * 
 * @author ChenQi
 * @date 2017年9月19日10:07:37
 */
@Component("AiLabelDao")
public interface LabelDao extends BaseDao<Label> {

}
