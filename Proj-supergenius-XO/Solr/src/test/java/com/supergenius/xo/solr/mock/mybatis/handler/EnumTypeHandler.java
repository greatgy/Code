package com.supergenius.xo.solr.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.solr.enums.EContent;

/**
 * @author Architect.bian
 */

@MappedTypes(value = {EContent.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}