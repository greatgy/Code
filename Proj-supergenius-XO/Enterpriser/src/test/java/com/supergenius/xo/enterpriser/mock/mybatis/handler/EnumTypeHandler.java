package com.supergenius.xo.enterpriser.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.enterpriser.enums.EComment;
import com.supergenius.xo.enterpriser.enums.EContent;
import com.supergenius.xo.enterpriser.enums.EEnterpriserMsg;

/**
 * @author Architect.bian
 */
@MappedTypes(value = {EComment.class, EEnterpriserMsg.class, EContent.class })
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}