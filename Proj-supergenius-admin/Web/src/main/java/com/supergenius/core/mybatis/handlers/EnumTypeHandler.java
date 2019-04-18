package com.supergenius.core.mybatis.handlers;

import org.apache.ibatis.type.MappedTypes;

import com.genius.model.base.enums.EStatus;
import com.genius.model.baseadmin.enums.EWorkOrder;
import com.genius.model.baseadmin.enums.EWorkStage;
import com.genius.xo.base.mybatis.handler.BaseEnumTypeHandler;
import com.supergenius.xo.account.enums.EBank;
import com.supergenius.xo.account.enums.EState;
import com.supergenius.xo.admin.enums.EAdminLog;
import com.supergenius.xo.ai.enums.ETop;
import com.supergenius.xo.ai.enums.EType;
import com.supergenius.xo.base.enums.EUser;
import com.supergenius.xo.career.enums.ECatalogue;
import com.supergenius.xo.common.enums.EChannel;
import com.supergenius.xo.common.enums.ECharge;
import com.supergenius.xo.common.enums.EContent;
import com.supergenius.xo.common.enums.EMsgState;
import com.supergenius.xo.common.enums.EQuotes;
import com.supergenius.xo.common.enums.ESite;
import com.supergenius.xo.gupage.enums.EPatent;
import com.supergenius.xo.life.enums.ECatalogueType;
import com.supergenius.xo.life.enums.EGrade;
import com.supergenius.xo.life.enums.ELabel;
import com.supergenius.xo.life.enums.ELifeChannel;
import com.supergenius.xo.life.enums.ELifeMsg;
import com.supergenius.xo.manager.enums.EAppExpert;
import com.supergenius.xo.manager.enums.EAppExpertStage;
import com.supergenius.xo.manager.enums.EAppJudgementStage;
import com.supergenius.xo.manager.enums.EAppStudent;
import com.supergenius.xo.manager.enums.ECertificate;
import com.supergenius.xo.manager.enums.EComplaint;
import com.supergenius.xo.manager.enums.EConfemember;
import com.supergenius.xo.manager.enums.EConfer;
import com.supergenius.xo.manager.enums.EDynamic;
import com.supergenius.xo.manager.enums.EExpert;
import com.supergenius.xo.manager.enums.EExpertLevel;
import com.supergenius.xo.manager.enums.EJudge;
import com.supergenius.xo.manager.enums.EJudgementLevel;
import com.supergenius.xo.manager.enums.ELevelChannel;
import com.supergenius.xo.manager.enums.EMajor;
import com.supergenius.xo.manager.enums.EMsg;
import com.supergenius.xo.manager.enums.EMsgGroup;
import com.supergenius.xo.manager.enums.EPKLog;
import com.supergenius.xo.manager.enums.EPKStage;
import com.supergenius.xo.manager.enums.EReplyStage;
import com.supergenius.xo.manager.enums.EStudentLevel;
import com.supergenius.xo.manager.enums.ETicket;
import com.supergenius.xo.manager.enums.EVideoFrom;
import com.supergenius.xo.startup.enums.ERuler;
import com.supergenius.xo.tpi.enums.ESysEmailType;
import com.supergenius.xo.user.enums.EComment;
import com.supergenius.xo.user.enums.EGender;
import com.supergenius.xo.user.enums.EGoods;
import com.supergenius.xo.user.enums.EScore;
import com.supergenius.xo.user.enums.ETrade;
import com.supergenius.xo.user.enums.EUserAisle;
import com.supergenius.xo.user.enums.EUserChannel;
import com.supergenius.xo.user.enums.EUserMsg;

/**
 * @author Architect.bian
 * 
 */
@MappedTypes(value = { EUserMsg.class, com.supergenius.xo.finance.enums.ECatalogue.class, ECharge.class, EPatent.class, com.supergenius.xo.gupage.enums.EComment.class,
		com.supergenius.xo.common.enums.EMsg.class, com.supergenius.xo.gupage.enums.ETop.class, com.supergenius.xo.finance.enums.EComment.class, com.supergenius.xo.finance.enums.EContent.class,
		com.supergenius.xo.career.enums.EType.class, com.supergenius.xo.career.enums.EContent.class, ECatalogue.class, ETop.class, EType.class, EConfemember.class, EReplyStage.class, EComplaint.class,
		EPKLog.class, EDynamic.class, ETicket.class, EConfer.class, com.supergenius.xo.manager.enums.EContent.class, com.supergenius.xo.enterpriser.enums.EContent.class, ELevelChannel.class,
		EAppExpertStage.class, EAppExpert.class, EExpert.class, EExpertLevel.class, ECertificate.class, EAppJudgementStage.class, EJudgementLevel.class, EJudge.class, EVideoFrom.class,
		EAppStudent.class, EVideoFrom.class, EStudentLevel.class, EMajor.class, EPKStage.class, EScore.class, EMsgGroup.class, EMsg.class, EQuotes.class, EStatus.class, EWorkOrder.class,
		EWorkStage.class, EContent.class, EComment.class, com.supergenius.xo.enterpriser.enums.EComment.class, EChannel.class, EUserChannel.class, EGender.class, ESite.class, EBank.class,
		EState.class, EMsgState.class, EMsg.class, ESite.class, EUser.class, EUserAisle.class, ESysEmailType.class, EAdminLog.class, EGoods.class, com.supergenius.xo.user.enums.EContent.class,
		ETrade.class, com.supergenius.xo.startup.enums.EGender.class, ERuler.class, com.supergenius.xo.startup.enums.EComment.class, com.supergenius.xo.startup.enums.EContent.class,
		com.supergenius.xo.ai.enums.EComment.class, com.supergenius.xo.ai.enums.EContent.class, com.supergenius.xo.solr.enums.EContent.class, com.supergenius.xo.career.enums.EComment.class,
		com.supergenius.xo.career.enums.ETop.class, com.supergenius.xo.life.enums.EComment.class, com.supergenius.xo.life.enums.EContent.class, ECatalogueType.class, ELifeChannel.class, EGrade.class,
		com.supergenius.xo.life.enums.EState.class, ELabel.class, com.supergenius.xo.life.enums.EComplaint.class, ELifeMsg.class, com.supergenius.xo.managernews.enums.EContent.class,
		com.supergenius.xo.entrepreneur.enums.EEntrepreneurChannel.class, com.supergenius.xo.entrepreneur.enums.EEntrepreneurChannel.class, com.supergenius.xo.entrepreneur.enums.EComment.class,
		com.supergenius.xo.entrepreneur.enums.EContent.class, com.supergenius.xo.moral.enums.EScoreDetail.class, com.supergenius.xo.moralnews.enums.EComment.class,
		com.supergenius.xo.moralnews.enums.EMoralnewsChannel.class, com.supergenius.xo.moralnews.enums.EContent.class })
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {
	public EnumTypeHandler(Class<E> type) {
		super(type);
	}
}