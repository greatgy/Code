package com.genius.xo.base.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 * Mybatis的Joda date类型转换处理
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:05:28
 */
@MappedTypes(LocalDate.class)
//@MappedJdbcTypes(value = {JdbcType.DATE,JdbcType.TIME,JdbcType.TIMESTAMP})
public class JodaDateTypeHandler extends BaseTypeHandler<LocalDate> {

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setDate(i, new Date(parameter.toDateTimeAtStartOfDay().toDate().getTime()));
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getJodaTime(rs.getDate(columnName));
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
	 */
	@Override
	public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getJodaTime(rs.getDate(columnIndex));
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
	 */
	@Override
	public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getJodaTime(cs.getDate(columnIndex));
	}

	private LocalDate getJodaTime(Date time) {
	    if (time != null) {
	      return new LocalDate(time.getTime(), DateTimeZone.forOffsetHours(8));
	    }
	    return null;
	}
}
