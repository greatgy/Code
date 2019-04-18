package com.supergenius.xo.user.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.entrepreneur.enums.EEntrepreneurChannel;
import com.supergenius.xo.entrepreneur.enums.ECatalogueType;
import com.supergenius.xo.entrepreneur.enums.EComment;
import com.supergenius.xo.entrepreneur.enums.EContent;
import com.supergenius.xo.entrepreneur.enums.EEntrepreneurMsg;

/**
 * @author Architect.bian
 */
@MappedTypes(value = {EComment.class,EContent.class,EEntrepreneurMsg.class,ECatalogueType.class,EEntrepreneurChannel.class,com.genius.model.base.enums.EStatus.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}