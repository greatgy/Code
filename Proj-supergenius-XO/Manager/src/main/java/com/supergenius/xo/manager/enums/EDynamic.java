package com.supergenius.xo.manager.enums;

import java.util.Locale;

import com.genius.core.base.utils.I18N;

/**
 * 动态实体枚举
 * @author XieMing
 * @date 2016-5-3 上午11:22:01
 */
public enum EDynamic {
	
	risePK(1),//发起挑战
	acceptPK(2),//接受挑战
	pkFailLess5(3),//挑战失败低于5次
	pkFail5(4),//挑战失败5次
	pkSuccess(5),//挑战成功
	scoreRiseUpSuccess(19),//积分晋级成功
	appReply(6),//申请答辩
	reAppReply(7),//申请二次答辩
	passReply(8),//答辩通过
	appJudge(9),//申请裁判
	passAppJudge(10),//申请裁判成功
	firstAppExpert(11),//初次申请专家
	passAppExpert(12),//申请专家成功
	appSeniorExpert(13),//申请高级专家
	passAppSeniorExpert(14),//申请高级专家成功
	appSpecialExpert(15),//申请特级专家
	passAppSpecialExpert(16),//申请特级专家成功
	buyTicket(17),//购买挑战门票
	buyVideo(18);//购买视频
     
     private final int value;
     
     private EDynamic(int v) {
    	 this.value=v;
     }
     
     private int getValue() {
    	 return value;
     }
     
     public static EDynamic get(int v) {
    	 for(EDynamic e : values()) {
    		 if(e.getValue()==v) {
    			 return e;
    		 }
    	 }
    	 return null;
     }
     
    public String toString() {
    	return String.valueOf(value);
    }
    
    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
}
