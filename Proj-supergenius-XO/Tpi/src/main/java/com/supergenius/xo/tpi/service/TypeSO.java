package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.tpi.entity.Type;
import com.supergenius.xo.tpi.enums.EType;

/**
 * 类型so
 * @author liushaomin
 */
public interface TypeSO extends BaseSO<Type>{

	/**
	 * @param team
	 * @return
	 */
	List<Type> getListByType(EType team);

	/**
	 * @param ids
	 * @return
	 */
	boolean deleteByUids(String[] ids);
	
}
