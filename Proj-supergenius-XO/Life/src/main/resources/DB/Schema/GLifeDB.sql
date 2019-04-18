﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     19:04 2013/1/10                                                   */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GLifeDB
Author:		ChenQi
Description:	GLifeDB初始化数据
CreatedDate:	2018年5月7日10:49:45
ModifyDate:	2018年5月7日10:50:01
*/

drop table if exists `article`;
/*==============================================================*/
/* Table: article   文章表                                      */
/*==============================================================*/
create table `article`(
   `oid`		   int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`         varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`         bigint(20) NOT NULL COMMENT '模块id',
   `adminuid`    varchar(36) COMMENT '操作管理员UID',
   `useruid`     varchar(36) COMMENT '用户uid',
   `author`      varchar(100) COMMENT '作者',
   `content`     mediumtext COMMENT '内容',
   `title`       varchar(500) COMMENT '标题', 
   `imglittle`   varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`   varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`      varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
   `origin`      varchar(100) COMMENT '来源',
   `summary`     varchar(1000) COMMENT '摘要内容',
   `keywords`    varchar(500) COMMENT 'seo关键字',
   `type`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发布',
   `kind`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
   `istop`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶(设为焦点文章)',
   `isoriginal` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型(0 非原创   1 原创)',
   `toptime`	 datetime DEFAULT NULL COMMENT '置顶时间',
   `status`      tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

drop table if exists `comments`;

/*==============================================================*/
/* Table: comments    评论表                                    */
/*==============================================================*/
CREATE TABLE `comments`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`            bigint(20) NOT NULL COMMENT '模块id',
   `cataloguename`  varchar(36) COMMENT '评论模块名称',
   `fromuid`        varchar(36) NOT NULL COMMENT '所评论文章的UID',
   `touid`          varchar(36) COMMENT '回复comment的uid，对应此表的uid',
   `fromuseruid`    varchar(36) COMMENT '评论人uid',
   `fromuseroid`    int(11) COMMENT '评论人oid',
   `fromusername`   varchar(36) COMMENT '评论人name',
   `touseruid`      varchar(36) COMMENT '评论给谁的uid',
   `touseroid`      int(11) COMMENT '评论给谁的oid',
   `tousername`     varchar(36) COMMENT '评论给谁的name',
   `content`        longtext COMMENT '评论',
   `channel`        int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

drop table if exists `topic`;
/*==============================================================*/
/* Table: topic   话题表                                      */
/*==============================================================*/
create table `topic`(
   `oid`	 int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`         varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`         bigint(20)  NOT NULL COMMENT '模块id',
   `adminuid`    varchar(36) COMMENT '操作管理员UID',
   `useruid`     varchar(36) COMMENT '用户uid',
   `author`      varchar(100) COMMENT '作者',
   `content`     mediumtext COMMENT '内容',
   `title`       varchar(500) COMMENT '标题', 
   `imglittle`   varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`   varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`      varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
   `origin`      varchar(100) COMMENT '来源',
   `summary`     varchar(1000) COMMENT '摘要内容',
   `keywords`    varchar(500) COMMENT 'seo关键字',
   `type`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发布',
   `kind`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
   `istop`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶(设为焦点文章)',
   `isoriginal` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型(0 非原创   1 原创)',
   `toptime`	 datetime DEFAULT NULL COMMENT '置顶时间',
   `examine`    tinyint(1) DEFAULT 0 COMMENT '是否通过审核',
   `status`      tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='话题表';


drop table if exists `essay`;

/*==============================================================*/
/* Table:   essay  天才人生动态表                                                   */
/*==============================================================*/
CREATE TABLE `essay`(
   `uid`          varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`        bigint(20) NOT NULL COMMENT '模块id',
   `touid`        varchar(36) COMMENT '回复comment的uid，对应此表的uid',
   `fromuseruid`  varchar(36) COMMENT '评论人uid',
   `fromuseroid`  int(11) COMMENT '评论人oid',
   `fromusername` varchar(36) COMMENT '评论人name',
   `touseruid`    varchar(36) COMMENT '评论给谁的uid',
   `touseroid`    int(11) COMMENT '评论给谁的oid',
   `tousername`   varchar(36) COMMENT '评论给谁的name',
   `content`      longtext COMMENT '评论',
   `imglittle`    text DEFAULT NULL COMMENT '小图',
   `imgmedium`    text DEFAULT NULL COMMENT '中图',
   `imgbig`       text DEFAULT NULL COMMENT '大图',
   `imgoriginal`  text DEFAULT NULL COMMENT '原图',
   `channel`      int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `data`         varchar(255) COMMENT '其他数据json格式',
   `type`         tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`     varchar(36) COMMENT '操作人UID',
   `status`       tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`   timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动态表';

drop table if exists `catalogue`;

/*==============================================================*/
/* Table: catalogue    目录表                                   */
/*==============================================================*/
create table `catalogue`(
  `cid`        bigint(20) NOT NULL COMMENT '模块id',
  `pcid`       bigint(20) NOT NULL COMMENT '父模块id',
  `name`       varchar(100) NOT NULL COMMENT '模块名称',
  `data`       LONGTEXT DEFAULT NULL COMMENT '相关的其他json数据',
  `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `type`       tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型(0文章1话题2视频)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`cid`),
   UNIQUE KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='目录表';

drop table if exists `subject`;

/*==============================================================*/
/* Table: subject    科目表                                   */
/*==============================================================*/
create table `subject`(
  `sid`        smallint(6) NOT NULL AUTO_INCREMENT COMMENT '科目id',
  `grade`       smallint(6) NOT NULL COMMENT '所属年级',
  `name`       varchar(100) NOT NULL COMMENT '科目名称',
  `data`       LONGTEXT DEFAULT NULL COMMENT '相关的其他json数据',
  `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`sid`),
   UNIQUE KEY `sid` (`sid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='科目表';

drop table if exists `course`;

/*==============================================================*/
/* Table: course    课程表                                   */
/*==============================================================*/
create table `course`(
  `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
  `grade`       smallint(6) NOT NULL COMMENT '所属年级',
  `sid`        smallint(6) NOT NULL COMMENT '所属科目id',
  `name`       varchar(100) NOT NULL COMMENT '课程名称',
  `imglittle`   varchar(255) DEFAULT '' COMMENT '小图',
  `imgmedium`   varchar(255) DEFAULT '' COMMENT '中图',
  `imgbig`      varchar(255) DEFAULT '' COMMENT '大图',
  `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
  `data`       LONGTEXT DEFAULT NULL COMMENT '相关的其他json数据',
  `press`      varchar(36) DEFAULT '' COMMENT '出版社',
  `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

drop table if exists `video`;

/*==============================================================*/
/* Table: video    视频表                                    */
/*==============================================================*/
CREATE TABLE `video`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`            bigint(20) NOT NULL COMMENT '模块id',
   `grade`          smallint(6) DEFAULT 0 COMMENT '所属年级',
   `sid`            smallint(6) NOT NULL COMMENT '所属科目id',
   `title`          varchar(500) COMMENT '标题',
   `content`        text COMMENT '视频代码',
   `keywords`       smallint(6)  DEFAULT 20 COMMENT '视频标签',
   `summary`        varchar(255) DEFAULT '' COMMENT '视频描述',
   `imglittle`      varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`      varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`         varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal`    varchar(255) DEFAULT '' COMMENT '原图',
   `data`           text COMMENT '其他数据json格式(保存点评与用户回复，list中嵌套map)',
   `istop`          tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶',
   `ismember`     tinyint(1) DEFAULT 0 COMMENT '是否会员(0用户1会员)',
   `useruid`     varchar(36) COMMENT '用户uid',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `type`           tinyint(1) DEFAULT 0 COMMENT '状态(0视频1音频)',
   `state`         tinyint(1) DEFAULT 0 COMMENT '状态(保存待点评、待回复等状态)',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频表';

drop table if exists `content`;

/*==============================================================*/
/* Table: content    内容表                                     */
/*==============================================================*/
CREATE TABLE `content` (
  `uid`        varchar(36) NOT NULL COMMENT 'uid',
  `cid`        bigint(20) NOT NULL COMMENT '模块id',
  `name`       varchar(1000) DEFAULT NULL COMMENT '名字',
  `type`       tinyint(1) DEFAULT 0 COMMENT '类型(广告位|应聘指南|其他)',
  `data`       varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary`    varchar(1000) DEFAULT NULL COMMENT '简介',
  `content`    mediumtext DEFAULT NULL COMMENT '详情',
  `originurl`  varchar(255) DEFAULT NULL COMMENT '链接',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';

drop table if exists `collect`;

/*==============================================================*/
/* Table: collection    收藏表                                  */
/*==============================================================*/
create table `collect`(
   `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
   `useruid`    varchar(36) NOT NULL COMMENT '用户UID',
   `refuid`     varchar(36) NOT NULL COMMENT '相对应uid',
   `type`       tinyint(1) DEFAULT 0 COMMENT '类型（0文章、1话题、2视频）',
   `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' comment '创建时间',
   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

drop table if exists `news`;
/*==============================================================*/
/* Table: news    消息表                                        */
/*==============================================================*/
CREATE TABLE `news` (
    `uid`        VARCHAR(36) NOT NULL COMMENT '唯一ID',
    `fromuid`    VARCHAR(36) NOT NULL COMMENT '发送者id',
    `touid`      VARCHAR(36) NOT NULL COMMENT '接收者id',
    `title`      VARCHAR(2000) DEFAULT NULL COMMENT '标题',
    `content`    VARCHAR(2000) DEFAULT NULL COMMENT '内容',
    `href`       varchar(255) DEFAULT NULL COMMENT '链接地址',
    `type`       TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '消息类型',
    `isread`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
    `status`     TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`uid`),
    UNIQUE INDEX `uid` (`uid`)
)COMMENT='消息表' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

drop table if exists `problem`;

/*==============================================================*/
/* Table: problem   天才人生问题表                              */
/*==============================================================*/
create table `problem`(
   `oid`        int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `cid`            bigint(20) NOT NULL COMMENT '模块id',
   `cataloguename`  varchar(36) COMMENT '评论模块名称',
   `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
   `adminuid`   varchar(36) COMMENT '操作管理员UID',
   `useruid`    varchar(36) COMMENT '提问者uid',
   `username`     varchar(100) COMMENT '提问者姓名',
   `content`    mediumtext COMMENT '内容',
   `title`      varchar(500) COMMENT '标题',
   `place`      varchar(500) COMMENT '行万里路模块目的地',
   `data`       text COMMENT '其他数据json格式(保存点评与用户回复，list中嵌套map)',
   `state`         tinyint(1) DEFAULT 0 COMMENT '状态(保存待点评、待回复等状态)',
   `istop`      tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '推荐(设为推荐)',
   `toptime`    timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '置顶时间',
   `ismember` tinyint(1) DEFAULT 0 COMMENT '是否会员(0用户1会员)',
   `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists `answer`;

/*==============================================================*/
/* Table: answer   天才人生回答表                               */
/*==============================================================*/
CREATE TABLE `answer`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`            bigint(20) NOT NULL COMMENT '模块id',
   `cataloguename`  varchar(36) COMMENT '评论模块名称',
   `fromuid`        varchar(36) NOT NULL COMMENT '所回答问题的UID',
   `title`         varchar(500) COMMENT '所回答问题的标题',
   `touid`          varchar(36) COMMENT '回复回答的uid，对应此表的uid',
   `fromuseruid`    varchar(36) COMMENT '回答人uid',
   `fromuseroid`    int(11) COMMENT '回答人oid',
   `fromusername`   varchar(36) COMMENT '回答人name',
   `touseruid`      varchar(36) COMMENT '回答给谁的uid',
   `touseroid`      int(11) COMMENT '回答给谁的oid',
   `tousername`     varchar(36) COMMENT '回答给谁的name',
   `content`        longtext COMMENT '回答内容',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `channel`        int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，回答或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回答表';

drop table if exists `complaint`;
#
# Structure for the `complaint` table : 投诉举报
#
CREATE TABLE `complaint` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `kind`  smallint(6) DEFAULT 0 COMMENT '文章，视频，话题',
  `fromuseruid` varchar(36) DEFAULT NULL COMMENT '举报人',
  `fromuid` varchar(36) DEFAULT NULL ,
  `refurl` varchar(255) DEFAULT NULL COMMENT '被举报的链接',
  `refname` varchar(255) DEFAULT NULL COMMENT '被举报标题',
  `type` tinyint(1) DEFAULT '0' COMMENT '类型（受贿、偏袒、徇私舞弊）',
  `desc` varchar(1000) DEFAULT NULL COMMENT '详细说明',
  `file` varchar(255) DEFAULT NULL COMMENT '上传凭证',
  `result` varchar(1000) DEFAULT NULL COMMENT '处理结果',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='举报' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#任务：
#     搭框架：
#	     前台 杨
#	     后台 + xo + server 陈
#     xo: 前五 陈
#		  中五 杨
#	  	  后四 贾
#	
#     后台：
#		文章管理 问题管理 回答管理 话题管理 贾
#		课程管理 科目管理 举报管理 视频管理 广告位管理 杨
#		动态管理 内容管理 评论管理 模块管理 banner管理  陈
#
#     前台：
#		文章 话题 文章详情 个人中心 内容 广告位 banner 杨
#		问题 回答 动态 视频 课程 课程详情页 陈
	
	