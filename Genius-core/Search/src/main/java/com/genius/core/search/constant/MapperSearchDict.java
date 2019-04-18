package com.genius.core.search.constant;

import org.apache.solr.common.params.CommonParams;

/**
 * 搜索用到的常量
 * 
 * @author architect.bian
 * @createtime 2015-12-30 下午3:26:53
 */
public class MapperSearchDict {

	public static final String rows = CommonParams.ROWS;
	public static final String start = CommonParams.START;
	public static final String sort = CommonParams.SORT;
	public static final String wt = CommonParams.WT;
	public static final String timeAllowed = CommonParams.TIME_ALLOWED;
	public static final String omitHeader = CommonParams.OMIT_HEADER;
	public static final String debug = CommonParams.DEBUG; // timing results query
	
	public static final String asc = "asc";
	public static final String desc = "desc";
	public static final String operOR = " OR ";
	public static final String operAND = " AND ";
	
	public static final String id = "id";
	public static final String route = "_route_";
	public static final String table = "_table_";

}
