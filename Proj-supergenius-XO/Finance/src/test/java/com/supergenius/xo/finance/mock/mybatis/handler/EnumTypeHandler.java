package com.supergenius.xo.finance.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.finance.enums.EComment;
import com.supergenius.xo.finance.enums.EContent;
import com.supergenius.xo.finance.enums.EFinanceMsg;
import com.supergenius.xo.finance.enums.EFollow;
import com.supergenius.xo.finance.enums.EType;
/**
 * @author liushaomin
 * 
 */
@MappedTypes(value = {EFinanceMsg.class , EFollow.class,EComment.class,EContent.class, EType.class, EChannel.class})public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}