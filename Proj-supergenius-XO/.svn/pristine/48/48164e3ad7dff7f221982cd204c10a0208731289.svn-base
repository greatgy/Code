package com.supergenius.xo.tpi.service;

import java.util.List;

import com.genius.model.base.entity.Pager;
import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.tpi.entity.Wish;
import com.supergenius.xo.tpi.enums.EWishType;

/**
 * 意愿明细SO
 * @author liushaomin
 */
public interface WishSO extends BaseSO<Wish>{
	
	/**
	 * @param wishtype
	 * @param channel
	 */
	List<Wish> getList(EWishType wishtype, EChannel channel);

	/**
	 * 根据以下条件获取数目
	 * @param touid
	 * @param type
	 * @param project
	 * @return
	 */
	int getCount(String fromuid, String touid, EWishType type, EChannel channel);

	/**
	 * 根据以下条件获取数据
	 * @param fromuid
	 * @param type
	 * @param channel
	 * @return
	 * @author ShangJianguo
	 */
	List<Wish> getList(String fromuid, EWishType type, EChannel channel);

	/**
	 * 根据以下条件获取数据
	 * @param fromuid 可以为空
	 * @param touid 可以为空
	 * @param type
	 * @param channel
	 * @param pager
	 * @return
	 * @author ShangJianguo
	 */
	List<Wish> getList(String fromuid, String touid, EWishType type, EChannel channel, Pager pager);
	
	/**
	 * 根据以下条件获取数据
	 * @param fromuid 可以为空
	 * @param touid 可以为空
	 * @param type
	 * @param channel
	 * @return
	 * @author ShangJianguo
	 */
	List<Wish> getList(String fromuid, String touid, EWishType type, EChannel channel);

	/**
	 * 关联删除wish表中的数据
	 * @param ids Touid
	 * @return
	 * @author liushaomin
	 */
	boolean deleteByTouid(String[] ids);

	/**
	 * 关联删除wish表中的数据
	 * @param ids Fromuid
	 * @author liushaomin
	 */
	boolean deleteByFromuid(String[] ids);

	/**
	 * 是否已支持
	 * @param fromuid
	 * @param touid
	 * @param channel
	 * @author liushaomin
	 */
	boolean isNotAttention(String fromuid, String touid, EChannel channel);

	/**
	 * 删除点击的支持
	 * @param fromuid
	 * @param touid
	 * @param channel
	 * @author liushaomin
	 */
	boolean deleteByAttention(String fromuid, String touid, EChannel channel);

	/**
	 * 删除意愿
	 * @param fromuid
	 * @param type
	 * @author liushaomin
	 */
	boolean deleteByFromuidType(String fromuid, EWishType type);
	
}
