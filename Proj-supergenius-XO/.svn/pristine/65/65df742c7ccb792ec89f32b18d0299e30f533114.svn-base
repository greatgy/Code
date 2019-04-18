package com.supergenius.xo.gupage.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.gupage.enums.EComment;
import com.supergenius.xo.gupage.enums.EContent;
import com.supergenius.xo.gupage.enums.EPatent;

/**
 * @author Architect.bian
 */

@MappedTypes(value = { EComment.class,EContent.class,EPatent.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}