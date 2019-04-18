package com.supergenius.web.admin.user.helper;

import com.genius.server.base.helper.BaseHP;
import com.supergenius.xo.common.constants.MapperDict;
import com.supergenius.xo.user.entity.User;
import com.supergenius.xo.user.enums.EUserMsg;
import com.supergenius.xo.user.service.NewsSO;

import java.util.HashMap;
import java.util.Map;

public class NewsHP extends BaseHP {
    private static NewsSO so;

    private static NewsSO getSO() {
        if (so == null) {
            so = spring.getBean(NewsSO.class);
        }
        return so;
    }

    public static void sendNews(User user, String title, String content,String fromuseruid){
        Map<String, Object> map = new HashMap<>();
        map.put(MapperDict.title, title);
        map.put(MapperDict.type, EUserMsg.sys);
        map.put(MapperDict.touid, user.getUid());
        map.put(MapperDict.content, content);
        map.put(MapperDict.fromuseruid, fromuseruid);
        map.put(MapperDict.href,"");
        getSO().add(map);
    }

}
