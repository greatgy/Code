package com.supergenius.xo.managernews.mock.mybatis.handler;

import org.apache.ibatis.type.MappedTypes;

import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.managernews.enums.EComment;
import com.supergenius.xo.managernews.enums.EContent;
import com.supergenius.xo.managernews.enums.EManagernewsMsg;


/**
 * @author Architect.bian
 */

@MappedTypes(value = {EComment.class,EContent.class,EManagernewsMsg.class})
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}