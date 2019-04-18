package com.supergenius.xo.ai.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.ai.enums.EAiMsg;
import com.supergenius.xo.ai.enums.EComment;
import com.supergenius.xo.ai.enums.EContent;
import com.supergenius.xo.ai.enums.ETop;
import com.supergenius.xo.ai.enums.EType;
import com.supergenius.xo.common.enums.EChannel;

/**
 * @author Architect.bian
 */

@MappedTypes(value = {ETop.class, EType.class, EAiMsg.class, EChannel.class, EComment.class, EContent.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}