package com.supergenius.xo.account.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.enums.EChannel;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.ERefundState;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.ESite;

/**
 * @author liushaomin
 *
 */
@MappedTypes(value = { EStatus.class, EChannel.class, ESite.class, EState.class, EBank.class, ECharge.class, ERefundState.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
