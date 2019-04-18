package com.supergenius.xo.moralnews.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.moralnews.enums.EComment;
import com.supergenius.xo.moralnews.enums.EContent;
import com.supergenius.xo.moralnews.enums.EMoralnewsChannel;
import com.supergenius.xo.moralnews.enums.EMoralnewsMsg;

/**
 * @author Architect.bian
 */

@MappedTypes(value = {EComment.class, EContent.class, EMoralnewsChannel.class, EMoralnewsMsg.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}