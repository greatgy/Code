package com.supergenius.xo.userdel.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.common.enums.EMsg;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.common.enums.ESite;

/**
 * @author liushaomin
 */
@MappedTypes(value = { EStatus.class,EMsg.class,EMsgState.class,ESite.class })
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
