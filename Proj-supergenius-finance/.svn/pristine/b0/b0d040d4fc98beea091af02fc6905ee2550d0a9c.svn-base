package com.supergenius.core.mybatis.handlers;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.finance.enums.ECatalogue;
import com.supergenius.xo.finance.enums.EComment;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.finance.enums.EFinanceMsg;
import com.supergenius.xo.finance.enums.EFollow;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = {EUserChannel.class, EChannel.class, EContent.class, ECatalogue.class, EComment.class, EFinanceMsg.class, EFollow.class, EUserChannel.class, com.supergenius.xo.user.enums.EGender.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
