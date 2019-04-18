package com.genius.xo.base.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Mybatis的Joda datetime类型转换处理
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:05:12
 */
@MappedTypes(DateTime.class)
//@MappedJdbcTypes(value = {JdbcType.DATE,JdbcType.TIME,JdbcType.TIMESTAMP})
public class JodaDateTimeTypeHandler extends BaseTypeHandler<DateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
	    return getJodaTime(rs.getTimestamp(columnName));
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getJodaTime(rs.getTimestamp(columnIndex));
	}

	@Override
	public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getJodaTime(cs.getTimestamp(columnIndex));
	}
	
	private DateTime getJodaTime(Timestamp stamp) {
	    if (stamp != null) {
	      return new DateTime(stamp.getTime(), DateTimeZone.forOffsetHours(8));
	    }
	    return null;
	}

}
