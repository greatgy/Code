package com.supergenius.xo.moral.service;

import com.genius.xo.base.service.BaseSO;
import com.supergenius.xo.moral.entity.DownloadDetail;
import com.supergenius.xo.moral.enums.EDoc;

/**
 * 下载明细so
 * @author liushaomin
 */
public interface DownloadDetailSO extends BaseSO<DownloadDetail>{

	/**
	 * 通过类型、学员uid、文档uid获取下载明细记录
	 * @param doc
	 * @param fuid
	 * @param suid
	 * @return
	 * @author YuYingJie
	 */
	DownloadDetail getOne(EDoc doc, String fuid, String suid);

	/**
	 * 添加下载明细
	 * @param doc
	 * @param fuid
	 * @param suid
	 * @return
	 * @author YuYingJie
	 */
	boolean add(EDoc doc, String fuid, String suid);

}
