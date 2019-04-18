package com.supergenius.xo.user.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.life.enums.ECatalogueType;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.EComplaint;
import com.supergenius.xo.life.enums.EContent;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.enums.ELabel;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.life.enums.EType;

/**
 * @author Architect.bian
 */
@MappedTypes(value = {ELabel.class,ECatalogueType.class,EGrade.class, EState.class, EType.class, EContent.class,ELifeMsg.class,EComplaint.class,EComment.class, ELifeChannel.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}