﻿# 原enterprise项目表结构
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : GEnterpriserDB2

drop table if exists `lecture`;
#drop table if exists `content`;
drop table if exists `register`;
drop table if exists `participate`;

#
# Structure for the `content` table :内容表(新项目不使用此表)
#
#CREATE TABLE `content` (
#  `uid` varchar(36) NOT NULL COMMENT 'uid',
#  `name` varchar(255) DEFAULT NULL COMMENT '内容名称',
#  `type` tinyint(1) NOT NULL COMMENT '内容类型(专题讲座|企业家培训)',
#  `data` varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
#  `summary` varchar(1000) DEFAULT NULL COMMENT '内容简介',
#  `content` mediumtext DEFAULT NULL COMMENT '内容详情',
#  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
#  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
#  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
#  PRIMARY KEY (`uid`),
#  UNIQUE KEY `uid` (`uid`)
#)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `lecture` table :讲座表
#
CREATE TABLE `lecture` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `sn` varchar(255) NOT NULL COMMENT '一个讲座对应一个sn',
  `name` varchar(255) DEFAULT NULL COMMENT '讲座名称',
  `username` varchar(255) DEFAULT NULL COMMENT '演讲人名称',
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '讲座时间',
  `address` varchar(1000) DEFAULT NULL COMMENT '讲座地点',
  `notice` text DEFAULT NULL COMMENT '报名须知',
  `fee` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '报名费用',
  `maxcount` int(11) unsigned NOT NULL COMMENT '最大报名人数',
  `registercount` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '已报名人数',
  `semester` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '学期（一期一个表）',
  `data` varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `file` varchar(255) DEFAULT NULL COMMENT '讲座文件',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(init 未开始报名 enable 可报名 end 报名结束 wait开讲时间确定)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='讲座表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `participate` table :报名表
#
CREATE TABLE `participate` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `useruid` varchar(36) DEFAULT NULL COMMENT '会员uid',
  `usersn` varchar(255) DEFAULT NULL COMMENT '会员号',
  `lectureuid` varchar(36) DEFAULT NULL COMMENT '讲座uid',
  `lecturename` varchar(255) DEFAULT NULL COMMENT '讲座名称',
  `semester`  int(11) unsigned DEFAULT '1' COMMENT '学期',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱(用此字段判断是否已是会员，提示用户已是会员，不会赠送会员)',
  `address` varchar(1000) DEFAULT NULL COMMENT '邮寄地址',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='报名表' ENGINE=InnoDB DEFAULT CHARSET=utf8;