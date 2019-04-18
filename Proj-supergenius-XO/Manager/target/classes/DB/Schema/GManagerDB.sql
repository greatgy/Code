# 职业经理人表结构
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : GManagerDB


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `GManagerDB`;

CREATE DATABASE `GManagerDB`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `GManagerDB`;

#
# Structure for the `student` table : 学员表
#
CREATE TABLE `student` (
  `uid` varchar(36) NOT NULL COMMENT '用户表相同的uid',
  `sn` varchar(255) DEFAULT NULL COMMENT '学号',
  `majors` int(11) DEFAULT NULL COMMENT '所有专业',
  `workdaytime` varchar(1000) DEFAULT NULL COMMENT '工作日的空闲时间,回车分割',
  `saturdaytime` varchar(1000) DEFAULT NULL COMMENT '周六的空闲时间,回车分割',
  `sundaytime` varchar(1000) DEFAULT NULL COMMENT '周日的空闲时间,回车分割',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='学员表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appstudent` table : 学员申请
#
CREATE TABLE `appstudent` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `useruid` varchar(36) DEFAULT NULL COMMENT '会员uid',
  `usersn` varchar(255) DEFAULT NULL COMMENT '会员号',
  `major` int(11) NOT NULL COMMENT '专业',
  `semester` varchar(20) DEFAULT NULL COMMENT '年度学期',
  `type` tinyint(1) unsigned NOT NULL COMMENT '申请表类别，是职业经理人培训还是企业家培训',
  `money` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  `reason` varchar(500) DEFAULT NULL COMMENT '审批理由',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='学员申请' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `userstatics` table : 用户统计表
#
CREATE TABLE `userstatics` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `useruid` varchar(36) NOT NULL COMMENT '用户uid',
  `pkcount` int(11) DEFAULT NULL COMMENT '我的挑战数',
  `watchpkcount` int(11) DEFAULT NULL COMMENT '我的观战数',
  `replycount` int(11) DEFAULT NULL COMMENT '我的答辩数',
  `judgecount` int(11) DEFAULT NULL COMMENT '作为裁判数',
  `expertcount` int(11) DEFAULT NULL COMMENT '作为专家数',
  `visitorcount` int(11) DEFAULT NULL COMMENT '个人主页被多少人看过',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
 PRIMARY KEY (`uid`),
 UNIQUE KEY `uid` (`uid`)
 )COMMENT='用户统计表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `userlevel` table : 级别明细表
#
CREATE TABLE `userlevel` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户uid',
  `major` int(11) unsigned NOT NULL COMMENT '专业',
  `levelfrom` int(11) DEFAULT NULL COMMENT '之前的级别',
  `levelto` int(11) DEFAULT NULL COMMENT '之后（晋级后）级别',
  `type` int(11) DEFAULT '0' COMMENT '学员or专家or裁判',
  `certificateuid` varchar(36) DEFAULT NULL COMMENT '证书uid',
  `arguments` varchar(1000) DEFAULT NULL COMMENT '相关记录参数',
  `desc` mediumtext COMMENT '备注',
  `channel` tinyint(1) unsigned DEFAULT NULL COMMENT '挑战晋级or积分晋级or答辩晋级or累计失败5场降级or特批学员级别|申请专家or专家晋级or特批专家or特聘专家or专家降级（处罚）or专职专家or退出专家|申请裁判or专职裁判or特聘裁判or被举报取消裁判资格or特批裁判or退出裁判',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用13被更新end)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='级别明细表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `countdetail` table : 计数明细
#
CREATE TABLE `countdetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `useruid` varchar(36) NOT NULL COMMENT '相关人uid',
  `refuid` varchar(36) NOT NULL COMMENT '相关uid',
  `type` tinyint(1) NOT NULL COMMENT '类型（投票）',
  `data` varchar(1000) NOT NULL COMMENT '红方、蓝方(如：{vote:0}),0:红,1:蓝',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='计数明细' ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
#
# Structure for the `major` table : 专业科目
#
CREATE TABLE `major` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `summary` varchar(1000) DEFAULT NULL COMMENT '简介',
  `content` mediumtext DEFAULT NULL COMMENT '详情',
  `type` int(11) unsigned NOT NULL COMMENT '类别对应一个枚举类型EMajor',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='专业科目' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `content` table :内容表
#
CREATE TABLE `content` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `name` varchar(1000) DEFAULT NULL COMMENT '帮助的名字',
  `type` tinyint(1) NOT NULL COMMENT '帮助类型(职业经理人首页|学员培训细则)',
  `data` varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary` varchar(1000) DEFAULT NULL COMMENT '帮助简介',
  `content` mediumtext DEFAULT NULL COMMENT '帮助详情',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `judge` table : 裁判
#
CREATE TABLE `judge` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户Uid',
  `sn` varchar(255) DEFAULT NULL COMMENT '裁判编号',
  `type` int(11) DEFAULT NULL COMMENT '裁判类别（普通裁判，专职裁判，特批裁判，特聘裁判）',
  `major` int(11) DEFAULT NULL COMMENT '专业',
  `level` tinyint(1) DEFAULT NULL COMMENT '裁判级别(暂时保留，以免需求再改)', 
  `certificateuid` varchar(36) DEFAULT NULL COMMENT '裁判证书uid',
  `judgecount` int(11) unsigned DEFAULT '0' COMMENT '评判场数',
  `complaintcount` int(11) unsigned DEFAULT '0' COMMENT '被举报次数',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)', # 退出裁判将改状态改为end 13状态
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='裁判' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appjudgement` table : 裁判申请
#
CREATE TABLE `appjudgement` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '申请人Uid',
  `username` varchar(100) DEFAULT NULL COMMENT '学员姓名',
  `major` int(11) NOT NULL COMMENT '专业',
  `type` int(11) NOT NULL COMMENT '裁判申请的类别',
  `majorlevel` tinyint(1) DEFAULT NULL COMMENT '专业级别',
  `userlevel` int(11) DEFAULT NULL COMMENT '学员级别',
  `judgelevel` tinyint(1) DEFAULT NULL COMMENT '裁判级别',
  `appstudentuid` varchar(36) DEFAULT NULL COMMENT '学员申请Uid',
  `desc` varchar(1000) DEFAULT NULL COMMENT '申请描述',
  `file` varchar(255) DEFAULT NULL COMMENT '学员（裁判）上传题目',
  `file2` varchar(255) DEFAULT NULL COMMENT '反馈材料',
  `topiccount` int(11) DEFAULT NULL COMMENT '通过审核的上传题目数',
  `descto` varchar(255) DEFAULT NULL COMMENT '反馈描述',
  `isonline` tinyint(1) unsigned DEFAULT NULL COMMENT '是否通过在线',
  `providetimes` varchar(1000) DEFAULT NULL COMMENT '提供的空闲时间',
  `providetime` varchar(1000) DEFAULT NULL COMMENT '采纳时间',
  `levelfrom` tinyint(1) unsigned DEFAULT NULL COMMENT '申请前裁判级别(暂时保留)',
  `levelto` tinyint(1) unsigned DEFAULT NULL COMMENT '申请后裁判级别(暂时保留)',
  `stage` tinyint(1) unsigned DEFAULT NULL COMMENT '可申请阶段，未提交申请、裁判申请审核、裁判申请未通过、裁判申请通过，上传资料、裁判资料审核中、材料审核未通过，请上重新传资料、裁判材料审核通过等待面试中、面试进行中、面试结束等待结果、面试为通过、面试通过获得X级裁判资格、专职裁判',
  `confuid` varchar(36) DEFAULT NULL COMMENT '会议室Uid',
  `args` varchar(255) DEFAULT NULL COMMENT '参数，可灵活使用',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='裁判申请' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appjudgementdetail` table : 裁判申请明细
#
CREATE TABLE `appjudgementdetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '申请人Uid',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '管理员Uid',
  `appjudgementuid` varchar(36) NOT NULL COMMENT '裁判申请表Uid',
  `name` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `file` varchar(255) DEFAULT NULL COMMENT '上传材料',
  `stagefrom` tinyint(1) DEFAULT NULL COMMENT '原状态',
  `stageto` tinyint(1) DEFAULT NULL COMMENT '现状态',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='裁判申请明细' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `expert` table : 专家
#
CREATE TABLE `expert` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户Uid',
  `sn` varchar(255) DEFAULT NULL COMMENT '专家编号',
  `major` int(11) DEFAULT NULL COMMENT '专业',
  `level` tinyint(1) DEFAULT '0' COMMENT '专家等级',
  `chaircount` int(11) unsigned DEFAULT '0' COMMENT '参加的答辩场数',
  `complaintcount` int(11) unsigned DEFAULT '0' COMMENT '被举报次数',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `type` int(1) DEFAULT NULL COMMENT '类型：0：普通专家（学员晋级）,1：专职专家,2:特聘专家,3:特批专家',
  `certificateuid` varchar(36) DEFAULT NULL COMMENT '证书uid',
  `file` varchar(255) DEFAULT NULL COMMENT '上传研究报告',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='专家' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appexpert` table : 专家申请
#
CREATE TABLE `appexpert` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '专家的useruid',
  `expertuid` varchar(36) DEFAULT NULL COMMENT '特批专家的专家表uid，否则为空',
  `major` int(11) DEFAULT NULL COMMENT '专业',
  `majorlevel` tinyint(1) DEFAULT NULL COMMENT '专业级别',
  `certificateuid` varchar(36) DEFAULT NULL COMMENT '已获得的证书uid',
  `file` varchar(255) DEFAULT NULL COMMENT '上传研究报告',
  `file2` varchar(255) DEFAULT NULL COMMENT '反馈材料',
  `providetimes` varchar(1000) DEFAULT NULL COMMENT '提供的空闲时间',
  `providetime` varchar(1000) DEFAULT NULL COMMENT '采纳时间',
  `levelfrom` tinyint(1) DEFAULT NULL COMMENT '申请前的等级，如果是会员/学员申请成为专家的话，则为null',
  `levelto` tinyint(1) DEFAULT NULL COMMENT '申请后的等级',
  `type` tinyint(1) DEFAULT NULL COMMENT '申请类别：0：学员或会员申请成为专家，1：专家晋级，2：管理员申请会员成为专家，3:特聘专家,4:特批专家',
  `stage` tinyint(1) unsigned DEFAULT NULL COMMENT '可申请阶段，专家申请审核、专家申请未通过、专家申请通过，上传研究报告、研究报告审核中、研究报告审核未通过，重新上传、研究报告审核通过等待面试中、面试进行中、面试结束等待结果、面试为通过、面试通过获得X级专家资格、退出专家',
  `replycount` int(11) DEFAULT NULL COMMENT '作为专家参加的答辩场数',
  `complaintcount` int(11) unsigned DEFAULT '0' COMMENT '被举报次数',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `auditdesc` varchar(200) DEFAULT NULL COMMENT '管理员审批描述',
  `auditadminuid` varchar(36) DEFAULT NULL COMMENT '审批管理员adminuid',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='专家申请' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appexpertdetail` table : 专家申请明细
#
CREATE TABLE `appexpertdetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '申请人Uid',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '管理员Uid',
  `appexpertuid` varchar(36) NOT NULL COMMENT '专家申请表Uid',
  `name` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `file` varchar(255) DEFAULT NULL COMMENT '上传材料',
  `stagefrom` tinyint(1) DEFAULT NULL COMMENT '原状态',
  `stageto` tinyint(1) DEFAULT NULL COMMENT '现状态',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='裁判申请明细' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `pkstatedetail` table : 挑战状态明细
#
CREATE TABLE `pkstatedetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `pkscheduleuid` varchar(36) NOT NULL COMMENT '挑战表UID',
  `useruid` varchar(36) NOT NULL COMMENT '操作用户UID',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `fromstage` tinyint(1) DEFAULT '1' COMMENT '原状态',
  `tostage` tinyint(1) DEFAULT '1' COMMENT '现状态',
  `action` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `type` tinyint(1) DEFAULT '1' COMMENT '类别',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='挑战状态明细' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `pkdate` table : 挑战时间
#
CREATE TABLE `pkdate` ( 
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `year` smallint(1) DEFAULT NULL COMMENT '年',
  `toyear` smallint(1) DEFAULT NULL COMMENT '到哪年',
  `month` tinyint(1) DEFAULT NULL COMMENT '月',
  `tomonth` tinyint(1) DEFAULT NULL COMMENT '到哪月',
  `day` tinyint(1) DEFAULT NULL COMMENT '日',
  `today` tinyint(1) DEFAULT NULL COMMENT '到哪日',
  `fromhour` tinyint(1) DEFAULT NULL COMMENT '从几时',
  `fromminute` tinyint(1) DEFAULT NULL COMMENT '从几分',
  `tohour` tinyint(1) DEFAULT NULL COMMENT '到几时',
  `tominute` tinyint(1) DEFAULT NULL COMMENT '到几分',
  `datetime` varchar(255) DEFAULT NULL COMMENT '挑战日期',
  `count` tinyint(1) DEFAULT NULL COMMENT '场次',
  `leftcount` tinyint(1) DEFAULT NULL COMMENT '剩余场次',
  `type` tinyint(1) DEFAULT '0' COMMENT '类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改后的时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='挑战时间' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `pkschedule` table : 挑战日程
#
CREATE TABLE `pkschedule` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `sn` varchar(36) NOT NULL COMMENT '挑战编号',
  `confeuid` varchar(36) DEFAULT NULL COMMENT '会议室UID',
  `confesn` varchar(36) DEFAULT NULL COMMENT '会议室编号',
  `name` varchar(255) DEFAULT NULL COMMENT '挑战名称',
  `major` int(11) NOT NULL DEFAULT '1' COMMENT '挑战专业',
  `level` tinyint(1) DEFAULT NULL COMMENT '挑战级别',
  `isonline` tinyint(1) DEFAULT NULL COMMENT '是否在线挑战',
  `pkuseruid` varchar(36) NOT NULL COMMENT '挑战者UID',
  `pkuserlevel` tinyint(1) DEFAULT NULL COMMENT '挑战者级别',
  `judgementuid` varchar(36) DEFAULT NULL COMMENT '挑战者裁判useruid',
  `judgementsn` varchar(255) DEFAULT NULL COMMENT '挑战者裁判编号',
  `pkdates` varchar(1000) DEFAULT NULL COMMENT '挑战者推荐的时间(10个时间用\n分隔)',
  `judgmentfee` decimal(10,2) DEFAULT NULL COMMENT '挑战者裁判最终获得的费用',
  `userscore` decimal(10,2) DEFAULT NULL COMMENT '挑战者分数',
  `isallowpkuser` tinyint(1) DEFAULT '1' COMMENT '挑战者是否加入视频点播系统',
  `isallowjudgement` tinyint(1) DEFAULT '1' COMMENT '挑战者裁判是否加入视频点播系统',
  `pkuseruid2` varchar(36) NOT NULL COMMENT '被挑战者UID',
  `pkuserlevel2` tinyint(1) DEFAULT NULL COMMENT '被挑战者级别',
  `judgementuid2` varchar(36) DEFAULT NULL COMMENT '被挑战者裁判useruid',
  `judgementsn2` varchar(255) DEFAULT NULL COMMENT '被挑战者裁编号',
  `pkdates2` varchar(1000) DEFAULT NULL COMMENT '被挑战者推荐的时间',
  `judgmentfee2` decimal(10,2) DEFAULT NULL COMMENT '被挑战者裁判最终获得的费用',
  `userscore2` decimal(10,2) DEFAULT NULL COMMENT '被挑战者分数',
  `isallowpkuser2` tinyint(1) DEFAULT '1' COMMENT '被挑战者是否加入视频点播系统',
  `isallowjudgement2` tinyint(1) DEFAULT '1' COMMENT '被挑战者挑裁判是否加入视频点播系统',
  `judgementchairsn` varchar(255) DEFAULT NULL COMMENT '主裁判编号 \n分割',
  `pktime` datetime DEFAULT NULL COMMENT '挑战开始时间',
  `pktimeend` datetime DEFAULT NULL COMMENT '挑战结束时间',
  `pkprice` decimal(10,2) DEFAULT NULL COMMENT '挑战费用',
  `judgementchairfee` decimal(10,2) DEFAULT NULL COMMENT '主裁判最终获得的费用',
  `ticketprice` decimal(10,2) DEFAULT NULL COMMENT '门票价格',
  `file` varchar(1000) DEFAULT NULL COMMENT '挑战题目',
  `istop` tinyint(1) unsigned DEFAULT '0' COMMENT '是否置顶',
  `stage` tinyint(1) DEFAULT '0' COMMENT '挑战阶段(0进行中|1即将开始|2等待结果|3已结束|4已取消)',
  `ticketstatus` tinyint(1) DEFAULT NULL COMMENT '门票状态',
  `ticketcount` int(11) DEFAULT NULL COMMENT '门票总数',
  `ticketsalecount` int(11) DEFAULT NULL COMMENT '已出售门票数量',
  `desc` mediumtext DEFAULT NULL COMMENT '挑战详情描述',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `sn` (`sn`)
)COMMENT='挑战日程' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `pkjudgement` table : 挑战的裁判(各选5个)表
#
CREATE TABLE `pkjudgement` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `pkscheduleuid` varchar(36) NOT NULL COMMENT '挑战表UID',
  `useruid` varchar(36) NOT NULL COMMENT '邀请人(挑战者或者被挑战者)UID',
  `judgementuid` varchar(36) DEFAULT NULL COMMENT '裁判UID',
  `pklevel` tinyint(1) DEFAULT NULL COMMENT '挑战级别',
  `desc` mediumtext COMMENT '备注/原因',
  `sortorder` smallint(5) unsigned DEFAULT '0' COMMENT '排序从小到大',
  `isabsent` tinyint(1) DEFAULT '0' COMMENT '是否缺席',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态 init:初始 start：已发送邮件 end:已过期 disable:拒绝 enable:接受 delete:已失效 ',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='挑战裁判' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appreply` table : 答辩
#
CREATE TABLE `appreply` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `sn` varchar(36) NOT NULL COMMENT '答辩号',
  `useruid` varchar(36) NOT NULL COMMENT '用户Uid',
  `major` int(11) NOT NULL COMMENT '专业(二进制)',
  `majorlevel` tinyint(1) NOT NULL COMMENT '用户专业等级(申请时)',
  `appstudentuid` varchar(36) NOT NULL COMMENT '申请表学员的Uid',
  `confuid` varchar(36) DEFAULT NULL COMMENT '会议室Uid',
  `desc` varchar(1000) DEFAULT NULL COMMENT '申请描述',
  `file` varchar(255) DEFAULT NULL COMMENT '学员上传材料',
  `file2` varchar(255) DEFAULT NULL COMMENT '反馈材料',
  `descto` varchar(1000) DEFAULT NULL COMMENT '反馈描述',
  `topic` varchar(1000) DEFAULT NULL COMMENT '题目',
  `certificated` varchar(36) DEFAULT NULL COMMENT '已获得的证书',
  `certificate` varchar(36) DEFAULT NULL COMMENT '申请获得的证书',
  `isvideoreply` tinyint(1) unsigned DEFAULT NULL COMMENT '答辩是否通过视频系统',
  `isvideotopic` tinyint(1) unsigned DEFAULT NULL COMMENT '开题是否通过视频系统',
  `opentimes` varchar(1000) DEFAULT NULL COMMENT '提供的开题论证会的时间，逗号分隔',
  `opentimeok` varchar(1000) DEFAULT NULL COMMENT '确定的开题论证会时间',
  `replytimes` varchar(1000) DEFAULT NULL COMMENT '提供的答辩时间，逗号分隔',
  `replytimeok` varchar(1000) DEFAULT NULL COMMENT '确定的答辩时间',
  `replystage` tinyint(1) unsigned DEFAULT NULL COMMENT '申请状态(开题|预答辩|答辩|获得证书)',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='答辩或开题' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appreplyexpert` table : 答辩的专家(共3个)表
#
CREATE TABLE `appreplyexpert` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `appreplyuid` varchar(36) NOT NULL COMMENT '答辩表UID',
  `expertuid` varchar(36) DEFAULT NULL COMMENT '专家UID',
  `desc` mediumtext COMMENT '备注/原因',
  `isabsent` tinyint(1) DEFAULT '0' COMMENT '是否缺席',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='答辩专家' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appreplydetail` table : 答辩状态明细
#
CREATE TABLE `appreplydetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '学员Uid',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '管理员Uid',
  `appreplyuid` varchar(36) NOT NULL COMMENT '答辩表Uid',
  `applicationuid` varchar(36) default NULL COMMENT '申请表Uid',
  `confuid` varchar(36) DEFAULT NULL COMMENT '会议室Uid',
  `name` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `file` varchar(255) DEFAULT NULL COMMENT '上传材料',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `time` varchar(1000) DEFAULT NULL COMMENT '提供或确定的时间',
  `isvideo` tinyint(1) DEFAULT NULL COMMENT '是否通过视频',
  `replystagefrom` tinyint(1) DEFAULT NULL COMMENT '原状态',
  `replystageto` tinyint(1) DEFAULT NULL COMMENT '现状态',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='答辩状态明细' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `conference` table : 会议室
#
CREATE TABLE `conference` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `sn` varchar(36) NOT NULL COMMENT '会议室编号',
  `typeuid` varchar(36) NOT NULL COMMENT '类型uid',
  `typename` varchar(36) NOT NULL COMMENT '类型名称',
  `cid` varchar(36) NOT NULL COMMENT '会议室ID',
  `name` varchar(255) DEFAULT NULL COMMENT '会议室名称',
  `password` varchar(255) DEFAULT NULL COMMENT '会议室密码',
  `passwordadmin` varchar(255) DEFAULT NULL COMMENT '会议室管理密码',
  `grouptype` varchar(255) DEFAULT NULL COMMENT '集群类型',
  `maxcount` int(11) unsigned DEFAULT '0' COMMENT '最大数量',
  `maxcountuser` int(11) unsigned DEFAULT '0' COMMENT '最大与会人数',
  `maxcounttourist` int(11) unsigned DEFAULT '0' COMMENT '最大观众人数',
  `maxcountspokesman` int(11) unsigned DEFAULT '0' COMMENT '最大主席人数',
  `isopen` tinyint(1) unsigned DEFAULT '1' COMMENT '是否公开,允许任何人参加（1是，0否）,可不填，默认为1',
  `islock` tinyint(1) unsigned DEFAULT '0' COMMENT '锁定会议室（1是，0否）,可不填，默认为0',
  `isautoclear` tinyint(1) unsigned DEFAULT '1' COMMENT '自动清空会议数据（1是，0否），可不填，默认为1',
  `isallvisible` tinyint(1) unsigned DEFAULT '1' COMMENT '所有人可见（0是，1否），可不填，默认为1',
  `args` varchar(255) DEFAULT NULL COMMENT '会议室参数，回车分割',
  `description` varchar(1000) DEFAULT NULL COMMENT '会议描述',
  `type` tinyint(1) NOT NULL  COMMENT '会议室类型',
  `istop` tinyint(1) unsigned DEFAULT '0' COMMENT '是否置顶',
  `ticketstatus` tinyint(1) DEFAULT NULL COMMENT '门票状态',
  `ticketprice` decimal(10,2) DEFAULT '0.00' COMMENT '门票价格',
  `ticketcount` int(11) unsigned DEFAULT '0' COMMENT '门票总数',
  `ticketsaletimefrom` datetime DEFAULT NULL COMMENT '开始卖门票的时间',
  `ticketsaletimeto` datetime DEFAULT NULL COMMENT '结束卖门票的时间',
  `ticketsalecount` int(11) unsigned DEFAULT '0' COMMENT '已出售门票数量',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `sn` (`sn`)
)COMMENT='会议室' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `confemember` table : 会议室成员
#
CREATE TABLE `confemember` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户的UID',
  `confuid` varchar(36) DEFAULT NULL COMMENT '会议室UID',
  `confsn` varchar(36) DEFAULT NULL COMMENT '会议室编号',
  `cid` varchar(36) NOT NULL COMMENT '会议室ID',
  `token` varchar(36) DEFAULT NULL COMMENT '用户token',
  `pkuid` varchar(36) NOT NULL COMMENT '挑战UID',
  `pkname` varchar(255) NOT NULL COMMENT '挑战名称',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型,挑战双方、裁判、观众、主裁判',
  `userid` varchar(36) DEFAULT NULL COMMENT '用户id',
  `username` varchar(100) DEFAULT NULL COMMENT '会员名',
  `useralias` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(36) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮件',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户电话',
  `isjoin` tinyint(1) unsigned DEFAULT '1' COMMENT '是否已加入会议室',
  `args` varchar(255) DEFAULT NULL COMMENT '会议室参数，回车分割',
  `usertype` tinyint(1) NOT NULL DEFAULT '0' COMMENT '视高的用户类型,user/tourist/spokesman/manager',
  `jointimefrom` datetime DEFAULT NULL COMMENT '加入会议室的开始时间',
  `jointimeto` datetime DEFAULT NULL COMMENT '加入会议室的结束时间',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='会议室成员' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `video` table : 视频
#
CREATE TABLE `video` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `sn` varchar(36) NOT NULL COMMENT '编号',
  `videouid` varchar(36) NOT NULL COMMENT '视频的UID，CC视频提供的uid',
  `name` varchar(255) NOT NULL COMMENT '视频名称',
  `code` varchar(1000) NOT NULL COMMENT '视频播放器代码',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `thumblittle` varchar(255) DEFAULT NULL COMMENT '小效果图',
  `thumb` varchar(255) DEFAULT NULL COMMENT '效果图',
  `thumbbig` varchar(255) DEFAULT NULL COMMENT '大效果图',
  `original` varchar(255) DEFAULT NULL COMMENT '原图',
  `refuid` varchar(36) DEFAULT NULL COMMENT '相关Uid',
  `playcount` int(11) unsigned NOT NULL COMMENT '播放次数',
  `endcount` int(11) unsigned NOT NULL COMMENT '播放完成的次数',
  `width` int(11) DEFAULT '0' COMMENT '宽度',
  `height` int(11) DEFAULT '0' COMMENT '高度',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `title` varchar(150) DEFAULT NULL COMMENT '标题',
  `desc` mediumtext COMMENT '详细介绍',  
  `fileurl` varchar(255) DEFAULT NULL COMMENT '文件下载地址',
  `sortorder` smallint(5) unsigned DEFAULT '0' COMMENT '排序从大到小',
  `channelfrom` tinyint(1) DEFAULT NULL COMMENT '来源，包括：挑战视频、答辩视频、开题视频、专家讲解视频',
  `major` int(11) DEFAULT '0' COMMENT '视频所属专业类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `sn` (`sn`)
)COMMENT='视频' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `certificate` table : 证书明细
#
CREATE TABLE `certificate` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT 'UserUID',
  `sn` varchar(36) DEFAULT NULL COMMENT '证书编号',
  `refuid` varchar(36) NOT NULL COMMENT 'refUID相关uid',
  `major` int(11) NOT NULL COMMENT '专业',
  `type` tinyint(1) NOT NULL COMMENT '证书类型（裁判（普通|特批|特聘|专职）、专家（普通|特批|特聘|专职）、高级专家、特级专家、RMBA\SMBA\TMBA）',
  `imglittle` varchar(255) DEFAULT NULL COMMENT '证书小图',
  `img` varchar(255) DEFAULT NULL COMMENT '证书中图',
  `imgbig` varchar(255) DEFAULT NULL COMMENT '证书大图',
  `desc` varchar(1000) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改后的时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='证书明细' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `comments` table : 评论表
#
CREATE TABLE `comments` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `fromuid` varchar(36) NOT NULL COMMENT '所评论文章的UID',
  `touid` varchar(36) DEFAULT NULL COMMENT '回复comment的uid，对应此表的uid',
  `fromuseruid` varchar(36) DEFAULT NULL COMMENT '评论人uid',
  `touseruid` varchar(36) DEFAULT NULL COMMENT '评论给谁的uid',
  `content` varchar(1000) DEFAULT NULL COMMENT '评论，若赞则使用<a>保存标题及连接地址',
  `type` tinyint(1) unsigned DEFAULT '0' COMMENT '类型，评论或者赞',
  `channel` tinyint(1) unsigned DEFAULT '0' COMMENT '频道',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='评论表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `message` table : 消息表
#
CREATE TABLE `message` (
    `uid` VARCHAR(36) NOT NULL COMMENT '唯一ID',
    `fromuid` VARCHAR(36) NOT NULL COMMENT '发送者id',
    `title` VARCHAR(2000) DEFAULT NULL COMMENT '标题',
    `content` VARCHAR(2000) DEFAULT NULL COMMENT '内容',
    `href` varchar(255) DEFAULT NULL COMMENT '链接地址',
    `type` TINYINT(1) UNSIGNED NOT NULL COMMENT '消息类型',
    `typegroup` TINYINT(1) UNSIGNED NOT NULL COMMENT '消息组类型',
    `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`uid`),
    UNIQUE INDEX `uid` (`uid`)
)COMMENT='消息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `inbox` table : 收件箱
#
CREATE TABLE `inbox` (
    `uid` VARCHAR(36) NOT NULL COMMENT '唯一UID',
    `useruid` VARCHAR(36) NOT NULL COMMENT '接收者id',
    `msguid` VARCHAR(36) NOT NULL COMMENT '消息id',
    `isread` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
    `typegroup` TINYINT(1) UNSIGNED NOT NULL COMMENT '消息组类型',
    `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间', 
     PRIMARY KEY (`uid`),
     UNIQUE KEY `uid`(`uid`)
)COMMENT='收件箱' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `complaint` table : 投诉举报
#
CREATE TABLE `complaint` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `fromuseruid` varchar(36) DEFAULT NULL COMMENT '举报人',
  `touseruid` varchar(36) DEFAULT NULL COMMENT '举报谁',
  `refuid` varchar(36) NOT NULL COMMENT '相关uid',
  `refname` varchar(255) DEFAULT NULL COMMENT '相关事件名称',
  `type` tinyint(1) DEFAULT '0' COMMENT '类型（受贿、偏袒、徇私舞弊）',
  `tousertype` int(11) DEFAULT NULL COMMENT '被举报对象的类型',
  `desc` varchar(1000) DEFAULT NULL COMMENT '详细说明',
  `file` varchar(255) DEFAULT NULL COMMENT '上传凭证',
  `result` varchar(1000) DEFAULT NULL COMMENT '处理结果',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态(12为待处理0非有效的举报1有效属实的举报)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='投诉举报' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `dynamic` table : 动态表
#
CREATE TABLE `dynamic` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) DEFAULT NULL COMMENT '用户uid',
  `type` tinyint(1) DEFAULT NULL COMMENT '动态类型',
  `title` varchar(255) DEFAULT NULL COMMENT '动态标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '动态内容',
  `data` varchar(1000) DEFAULT NULL COMMENT '关联数据',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
 PRIMARY KEY (`uid`),
 UNIQUE KEY `uid` (`uid`)
)COMMENT='动态表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `appleave` table : 专家、裁判请假记录表
#
CREATE TABLE `appleave` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) DEFAULT NULL COMMENT 'useruid',
  `usertype` int(11) DEFAULT NULL COMMENT '请假人类型（裁判/专家）',
  `starttime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '请假起始时间',
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '请假结束时间',
  `desc` varchar(1000) DEFAULT NULL COMMENT '请假事由说明',
  `result` varchar(1000) DEFAULT NULL COMMENT '处理结果',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='专家、裁判请假' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `myvideo` table : 我的视频
#
CREATE TABLE `myvideo` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户的uid',
  `videouid` varchar(36) NOT NULL COMMENT '视频的uid',
  `expiretime` datetime NOT NULL COMMENT '过期时间',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='我的视频' ENGINE=InnoDB DEFAULT CHARSET=utf8;
