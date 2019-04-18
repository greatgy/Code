package com.genius.server.portal.helper;

import com.genius.core.base.constant.BaseMapperDict;
import com.genius.model.portal.enums.EContent;
import com.genius.server.base.helper.BaseHP;
import com.genius.xo.portal.service.ContentSO;

public class ContentHP extends BaseHP {

//	private static Logger log = LoggerFactory.getLogger(ContentHP.class);

	private static ContentSO so;

	private static ContentSO getSO() {
		if (so == null) {
			so = (ContentSO) spring.getBean(ContentSO.class);
		}
		return so;
	}

	public static Object getOne(EContent type) {
		return getSO().getOneByField(BaseMapperDict.type, EContent.friendLink_txt);
	}
	
	
}
