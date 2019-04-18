package com.genius.core.base.constant;


/**
 * @author Architect.bian
 *
 */
public abstract class BaseMapperDict extends BaseStrDict {

	/**
	 * special asc desc section
	 */
	public static final String asc = " asc";
	public static final String desc = " desc";
	
	/**
	 * map key
	 */
	public static final String _id = "_id";
	public static final String ids = "ids";
	public static final String uids = "uids";
	public static final String oids = "oids";
	public static final String ascDesc = "ascDesc";
	public static final String orderBy = "orderBy";
	public static final String startIndex = "startIndex";
	public static final String pageSize = "pageSize";
	public static final String createtimestart = "createtimestart";
	public static final String createtimeend = "createtimeend";
	
	/**
	 * condition key
	 */
	@Deprecated
	public static final String greaterOrEqual_key_suffix = "_ge";
	@Deprecated
	public static final String greater_key_suffix = "_gt";
	@Deprecated
	public static final String lessOrEqual_key_suffix = "_le";
	@Deprecated
	public static final String less_key_suffix = "_lt";
	@Deprecated
	public static final String like_key_suffix = "_lk";
	@Deprecated
	public static final String no_key_prefix = "no_";
	@Deprecated
	public static final String isnull_key_suffix = "_isnull";
	@Deprecated
	public static final String in_key_suffix = "_in";
	
	public static final String suffix_greaterOrEqual_key = "_ge";
	public static final String suffix_greater_key = "_gt";
	public static final String suffix_lessOrEqual_key = "_le";
	public static final String suffix_notEqual_key = "_ne";
	public static final String suffix_less_key = "_lt";
	public static final String suffix_like_key = "_lk";
	public static final String suffix_no_key = "_no";
	public static final String suffix_isnull_key = "_isnull";
	public static final String suffix_in_key = "_in";
	public static final String suffix_inall_key = "_inall";
	public static final String suffix_nin_key = "_nin";
	public static final String suffix_exist_key = "_exist";
	public static final String suffix_nexist_key = "_nexist";
	public static final String suffix_mod_key = "_mod";
	public static final String suffix_arrsize_key = "_arrsize";

	public static final String prefix_no_key = "no_";
	public static final String no = "no_";
	public static final String where = "where";
	
	/**
	 * sql section
	 */
	public static final String sql_order_by = " order by ";
	
	/**
	 * 字段名称
	 */
	public static final String createtime = "createtime";
	public static final String updatetime = "updatetime";
	public static final String tid = "tid";
	public static final String useruid = "useruid";
	public static final String userid = "userid";
	public static final String fromuid = "fromuid";
	public static final String status = "status";
	public static final String istop = "istop";
	public static final String groupuid = "groupuid";
	public static final String sn = "sn";
	public static final String address = "address";
	public static final String name = "name";
	public static final String type = "type";
	public static final String logo = "logo";
	public static final String headimg = "headimg";
	public static final String avatar = "avatar";
	public static final String img = "img";
	public static final String thumb = "thumb";
	public static final String original = "original";
	public static final String distinct = "distinct";
	public static final String gender = "gender";
	public static final String realname = "realname";
	public static final String birthday = "birthday";
	public static final String email = "email";
	public static final String phone = "phone";
	public static final String mobile = "mobile";
	public static final String price = "price";
	public static final String level = "level";
	public static final String sortorder = "sortorder";
	public static final String parentuid = "parentuid";
	public static final String parentid = "parentid";
	public static final String ispublic = "ispublic";
	public static final String isbest = "isbest";
	public static final String ishot = "ishot";
	public static final String num = "num";
	public static final String search = "search";
	public static final String content = "content";
	public static final String title = "title";
	public static final String username = "username";
	public static final String account = "account";
	public static final String clickcount = "clickcount";
	public static final String date = "date";
	public static final String pagenum = "pagenum";
	public static final String resetpwd = "resetpwd";
	public static final String lastlogintime = "lastlogintime";
	public static final String lastloginip = "lastloginip";
	public static final String commentcount = "commentcount";
	public static final String fromuseruid = "fromuseruid";
	public static final String touid = "touid";
	public static final String touseruid = "touseruid";
	public static final String adminuid = "adminuid";
	public static final String adminid = "adminid";
	public static final String roleuid = "roleuid";
	public static final String roleid = "roleid";
	public static final String stage = "stage";
	public static final String year = "year";
	public static final String month = "month";
	public static final String workorderuid = "workorderuid";
	public static final String authority = "authority";
	public static final String pwd = "pwd";
	public static final String dopwd = "dopwd";
	public static final String urlregx = "urlregx";
	public static final String author = "author";
}
