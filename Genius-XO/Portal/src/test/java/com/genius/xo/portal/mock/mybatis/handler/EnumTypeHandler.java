package com.genius.xo.portal.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = { EStatus.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
