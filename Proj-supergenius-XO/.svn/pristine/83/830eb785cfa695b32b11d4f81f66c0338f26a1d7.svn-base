package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/** 
 * 
 * @author chenminchang
 * @date 2016-7-18 上午11:37:21 
 */
public enum EAppExpertStage {
	
	applyCheck("0"),//专家申请审核
	applyNoPass("1"),//专家申请未通过
	applyPass("2"),//专家申请通过
	reportCheck("3"),//研究报告审核中
	reportNoPass("4"),//研究报告审核未通过，重新上传
	reportPass("5"),//研究报告审核通过等待面试中
	interviewing("6"),//面试进行中
	interviewed("7"),//面试结束
	interviewNoPass("8"),//面试不通过
	interviewPass("9"),//面试通过，获得专家资格
	specialCheck("10"), // 等待后台审批专职专家
	specialPass("11"), // 审批通过
	specialNoPass("12"), // 审批未通过
	exit("13");//退出专家,同时修改Expert的status为end

    private final String value;

    private EAppExpertStage(String v) {
        this.value = v;
    }

    public String toString() {
        return this.value;
    }

    public static EAppExpertStage get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static EAppExpertStage get(String str) {
		for (EAppExpertStage e : values()) {
		   if (e.value.equals(str)) {
			return e;
		   }
		}
		return null;
	}
    
    public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
    
    /**
	 * 获取所有专家申请阶段的值与中文名称
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:56:29
	 */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> stages = new HashMap<String, String>();
		for (EAppExpertStage stage : EAppExpertStage.values()) {
			stages.put(stage.toString(), stage.getName());
		}
		return stages;
    }
    
    /**
     * 获取用户需要上传文件的阶段
     * @return
     * @author XieMing
     * 2016-10-24 下午5:32:38
     */
    public static List<EAppExpertStage> getFileList() {
    	List<EAppExpertStage> list = new ArrayList<EAppExpertStage>();
    	list.add(EAppExpertStage.applyPass);
    	list.add(EAppExpertStage.reportNoPass);
    	list.add(EAppExpertStage.interviewNoPass);
    	return list;
    }
    
    /**
     * 获取后台会反馈材料的阶段
     * @return
     * @author XieMing
     * 2016-10-24 下午5:33:01
     */
    public static List<EAppExpertStage> getFileList2() {
    	List<EAppExpertStage> list = new ArrayList<EAppExpertStage>();
    	list.add(EAppExpertStage.reportCheck);
    	list.add(EAppExpertStage.interviewed);
    	list.add(EAppExpertStage.interviewing);
    	list.add(EAppExpertStage.reportPass);
    	return list;
    }
}
