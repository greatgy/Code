package com.supergenius.xo.manager.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.genius.core.base.utils.I18N;

/** 
 * 裁判申请状态枚举类
 * @author guanshiqian
 * @date 2016-4-28 上午11:14:59 
 */
public enum EAppJudgementStage {

	init("0"), // 已提交申请，审核中
	passInit("1"), // 申请通过，上传题目
	overInit("2"), // 提交申请未通过
	checkQuestion("3"), // 审核题目中
	passCheck("4"), // 审核题目通过,准备参加面试
	overCheck("5"), // 审核题目未通过
	interviewing("6"),//面试进行中
	interview("7"), // 等待面试结果
	passInterview("8"), // 面试通过，获得裁判资格
	overInterview("9"), // 面试未通过
	specialCheck("10"), // 等待后台审批专职裁判
	passSpecialCheck("11"), // 审批通过，获得专职裁判资格
	overSpecialCheck("12"), // 审批未通过
	fulltimeJudgement("13"),//专职裁判
	punishPass("14"), // 裁判处罚通过
	punishRefuse("15"),// 裁判处罚未通过
	quit("16");//退出裁判

	private final String value;

	private EAppJudgementStage(String v) {
		this.value = v;
	}

	public String toString() {
		return this.value;
	}

	public static EAppJudgementStage get(int v) {
		String str = String.valueOf(v);
		return get(str);
	}

	public static EAppJudgementStage get(String str) {
		for (EAppJudgementStage e : values()) {
			if (e.toString().equals(str)) {
				return e;
			}
		}
		return null;
	}

	public String getName() {
		return I18N.getEnumName(this, Locale.CHINA);
	}
	
	/**
	 * 获取所有裁判申请阶段的值与中文名称
	 * @return
	 * @author XieMing
	 * 2016-10-21 下午4:56:29
	 */
    public static Map<String, String> getValueAndChinese() {
    	Map<String, String> stages = new HashMap<String, String>();
		for (EAppJudgementStage stage : EAppJudgementStage.values()) {
			stages.put(stage.toString(), stage.getName());
		}
		return stages;
    }
    
    /**
     * 获取用户需要上传文件的阶段
     * @return
     * @author XieMing
     * 2016-10-24 下午5:16:14
     */
    public static List<EAppJudgementStage> getNeedFileList() {
    	List<EAppJudgementStage> list = new ArrayList<EAppJudgementStage>();
    	list.add(EAppJudgementStage.passInit);
    	list.add(EAppJudgementStage.overCheck);
    	list.add(EAppJudgementStage.overInterview);
    	return list;
    }
    
    /**
     * 获取后台会反馈资料的阶段
     * @return
     * @author XieMing
     * 2016-10-24 下午5:16:14
     */
    public static List<EAppJudgementStage> getNeedFileList2() {
    	List<EAppJudgementStage> list = new ArrayList<EAppJudgementStage>();
    	list.add(EAppJudgementStage.checkQuestion);
    	list.add(EAppJudgementStage.interview);
    	list.add(EAppJudgementStage.passCheck);
    	list.add(EAppJudgementStage.interviewing);
    	return list;
    }
}