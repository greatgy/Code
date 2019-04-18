package com.supergenius.core.mybatis.handlers;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.life.enums.ECatalogue;
import com.supergenius.xo.life.enums.ECatalogueType;
import com.supergenius.xo.life.enums.ECollectType;
import com.supergenius.xo.life.enums.EComment;
import com.supergenius.xo.life.enums.EComplaint;
import com.supergenius.xo.life.enums.EContent;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.enums.ELabel;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.life.enums.EState;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = {EComplaint.class, EStatus.class, EGrade.class,EContent.class, ECatalogue.class, ELifeChannel.class, ECollectType.class, EState.class, ECatalogueType.class, EUserChannel.class, EGender.class, EComment.class, ELifeMsg.class, ELabel.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
