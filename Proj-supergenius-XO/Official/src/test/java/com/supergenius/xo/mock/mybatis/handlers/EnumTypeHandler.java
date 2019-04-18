package com.supergenius.xo.mock.mybatis.handlers;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.common.enums.EChannel;

/**
 * Mybatis枚举处理类
 *
 * @param <E>
 * @author ShangJianguo
 */
@MappedTypes(value = { EStatus.class, EChannel.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
