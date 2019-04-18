package com.genius.xo.base.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

/**
 * Mybatis的Joda datetime类型转换处理
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:05:45
 */
@MappedTypes(LocalTime.class)
//@MappedJdbcTypes(value = {JdbcType.DATE,JdbcType.TIME,JdbcType.TIMESTAMP})
public class JodaTimeTypeHandler extends BaseTypeHandler<LocalTime> {

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setTime(i, new Time(parameter.getMillisOfDay()));
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getJodaTime(rs.getTime(columnName));
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
	 */
	@Override
	public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getJodaTime(rs.getTime(columnIndex));
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
	 */
	@Override
	public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getJodaTime(cs.getTime(columnIndex));
	}

	private LocalTime getJodaTime(Time time) {
	    if (time != null) {
	      return new LocalTime(time.getTime(), DateTimeZone.forOffsetHours(8));
	    }
	    return null;
	}
}
