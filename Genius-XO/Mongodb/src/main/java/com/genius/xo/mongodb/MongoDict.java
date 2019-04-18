package com.genius.xo.mongodb;

/**
 * MongoDB对应的常量列表
 * 
 * @author architect.bian
 * @createtime 2015-1-28 下午5:41:13
 */
public class MongoDict {

	public static final String id = "_id";
	public static final String _id = "_id";
	public static final String $ = "$";
	
	public static final String $gt = "$gt";
	public static final String $gte = "$gte";
	public static final String $lt = "$lt";
	public static final String $lte = "$lte";
	public static final String $in = "$in";
	public static final String $all = "$all";
	public static final String $exists = "$exists";
	public static final String $mod = "$mod";
	public static final String $size = "$size";
	public static final String $type = "$type";
	public static final String $elemMatch = "$elemMatch";
	public static final String $not = "$not";
	public static final String $or = "$or";
	public static final String $and = "$and";
	
	// operator
	public static final String $where = "$where";
	public static final String $push = "$push";
	public static final String $pushAll = "$pushAll";
	public static final String $addToSet = "$addToSet";
	public static final String $pull = "$pull";
	public static final String $pop = "$pop";
	public static final String $pullAll = "$pullAll";
	public static final String $inc = "$inc";
	public static final String $set = "$set";
	public static final String $unset = "$unset";
	public static final String $text = "$text";
	
	// modifier
	public static final String $sum = "$sum";
	public static final String $avg = "$avg";
	public static final String $match = "$match";
	public static final String $group = "$group";
	public static final String $sort = "$sort";
	public static final String $project = "$project";
	public static final String $limit = "$limit";
	public static final String $skip = "$skip";
	public static final String $each = "$push";
	public static final String $slice = "$slice";
	
}
