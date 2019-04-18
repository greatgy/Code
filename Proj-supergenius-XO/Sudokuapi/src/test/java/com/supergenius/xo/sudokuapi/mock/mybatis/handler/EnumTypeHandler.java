package com.supergenius.xo.sudokuapi.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.sudokuapi.enums.EGameStatus;
import com.supergenius.xo.sudokuapi.enums.EGrade;
import com.supergenius.xo.sudokuapi.enums.ERecharge;

/**
 * @author Architect.bian
 */

@MappedTypes(value = {EGameStatus.class, EGrade.class, ERecharge.class })
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}