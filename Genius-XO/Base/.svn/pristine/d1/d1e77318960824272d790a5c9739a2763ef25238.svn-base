package com.genius.xo.base.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.genius.core.base.utils.EnumUtil;
import com.genius.model.base.enums.EStatus;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = { EStatus.class})
public class BaseEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

	private Class<E> type;
	
	public BaseEnumTypeHandler() {}
	
	public BaseEnumTypeHandler(Class<E> type) {
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		if (jdbcType == null) {
			ps.setString(i, parameter.toString());
		} else {
			ps.setObject(i, parameter.name(), jdbcType.TYPE_CODE);
		}
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getEnum(rs.getString(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getEnum(rs.getString(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getEnum(cs.getString(columnIndex));
	}

	private E getEnum(String s) {
		return EnumUtil.get(type, s);
	}
}
