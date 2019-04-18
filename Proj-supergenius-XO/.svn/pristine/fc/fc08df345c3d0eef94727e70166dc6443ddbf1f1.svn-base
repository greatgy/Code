package com.supergenius.xo.user.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.user.enums.EComment;
import com.supergenius.xo.user.enums.EContent;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.enums.EOrderState;
import com.supergenius.xo.user.enums.EQuestion;
import com.supergenius.xo.user.enums.EScore;
import com.supergenius.xo.user.enums.ETrade;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * @author Architect.bian
 */

@MappedTypes(value = { EScore.class, EGoods.class, EChannel.class, EComment.class, EOrderState.class, EGender.class, EQuestion.class, ETrade.class, EUserChannel.class, EUser.class, EQuestion.class, EContent.class,EQuestion.class,ESite.class, EScore.class })
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}