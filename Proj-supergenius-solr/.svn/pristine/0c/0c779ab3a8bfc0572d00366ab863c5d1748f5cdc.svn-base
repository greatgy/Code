package com.supergenius.core.mybatis.handlers;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.solr.enums.EContent;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.enums.EUserChannel;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = {EStatus.class,EContent.class,EUserChannel.class,EGender.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}
