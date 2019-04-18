package com.supergenius.xo.startup.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.startup.enums.EComment;
import com.supergenius.xo.startup.enums.EContent;
import com.supergenius.xo.startup.enums.EGender;
import com.supergenius.xo.startup.enums.ERuler;

/**
 * @author Architect.bian
 */

@MappedTypes(value = {EMsg.class, EChannel.class, EGender.class, ERuler.class, EComment.class, EContent.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}