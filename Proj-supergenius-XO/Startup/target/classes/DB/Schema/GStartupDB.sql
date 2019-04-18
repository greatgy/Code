# 问卷调查表结构
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : GStartupDB

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `GStartupDB`;

CREATE DATABASE `GStartupDB`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `GStartupDB`;

drop table if exists `ruler`;
drop table if exists `question`;
drop table if exists `statistics`;
drop table if exists `music`;

#
# Structure for the `ruler` table :规则表
#
CREATE TABLE `ruler` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `type` tinyint(1) NOT NULL COMMENT '评价类型',
  `name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `rejectmincount` tinyint(1) unsigned DEFAULT '0' COMMENT '一票否决数量最小值(包含此值)',
  `rejectmaxcount` tinyint(1) unsigned DEFAULT '0' COMMENT '一票否决数量最大值(包含此值)',
  `minscore` int(11) COMMENT '分数区间最小值(包含此值)',
  `maxscore` int(11) COMMENT '分数区间最大值(不包含此值)',
  `content` mediumtext DEFAULT NULL COMMENT '得分区间反馈内容',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `isinit` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0不能删除1可以删除)',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='规则表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `question` table :题库
#
CREATE TABLE `question` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `type` tinyint(1) NOT NULL COMMENT '题目类型(一票否决|创业加分)',
  `name` varchar(1000) NOT NULL COMMENT '题目',
  `options` text DEFAULT NULL COMMENT '选项及选项内容，以json的方式记录{"A":"",...}',
  `optionscore` varchar(1000) NOT NULL COMMENT '选项及选项得分，以json的方式记录{"A":5,...}',
  `order` int(11) NOT NULL DEFAULT 0 COMMENT '显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `img` varchar(100) COMMENT 'question的题目插图，存储的图片路径',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:当前显示状态,13:旧的状态,2:删除状态)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='题库' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `statistics` table :调查结果统计表
#
CREATE TABLE `statistics` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `score` int(11) NOT NULL DEFAULT 0 COMMENT '总得分',
  `ruleruid` mediumtext DEFAULT NULL COMMENT '得分结果反馈内容',
  `data` text DEFAULT NULL COMMENT '记录被调查者回答的题目uid及选项，json格式存储{"questionuid":"A",...}',
  `loginip` varchar(50) DEFAULT '127.0.0.1' COMMENT '登录IP',
  `useruid` varchar(36) DEFAULT NULL COMMENT '用户uid',
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(12) DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别(0男1女)',
  `email` varchar(36) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(36) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(36) DEFAULT NULL COMMENT '地址',
  `advice` varchar(500) COMMENT '意见反馈',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='调查结果统计表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `music` table :背景音乐表
#
CREATE TABLE `music` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `name` varchar(12) DEFAULT NULL COMMENT '歌曲名字',
  `url` varchar(100) NOT NULL COMMENT '歌曲路径',
  `temp` tinyint(1) NOT NULL COMMENT '判断本地歌曲，还是网络歌曲  0 表示本地   1 表示网络',
  `order` int NOT NULL DEFAULT 0 COMMENT '显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='背景音乐表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

