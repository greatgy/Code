package com.supergenius.xo.career.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.career.enums.ECareerMsg;
import com.supergenius.xo.career.enums.EComment;
import com.supergenius.xo.career.enums.EContent;
import com.supergenius.xo.common.enums.EChannel;
@MappedTypes(value = {EContent.class, ECareerMsg.class, EComment.class, EChannel.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}