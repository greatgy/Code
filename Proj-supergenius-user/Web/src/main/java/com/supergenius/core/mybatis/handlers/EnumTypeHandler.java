package com.supergenius.core.mybatis.handlers;

import com.supergenius.xo.user.enums.*;
import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.ESite;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = {EUserMsg.class,EUserAisle.class, EGoods.class, EOrderState.class, EGender.class, EQuestion.class, ETrade.class, EUserChannel.class, EUser.class, EContent.class, EQuestion.class, ESite.class, EBank.class, ECharge.class, EState.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
