﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2017年12月29日10:12:23                                                        */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GFinanceDB2
Author:        ChenQi
Description:    创建数据库
CreatedDate:    2017年12月29日10:12:16
ModifyDate:     2017年12月29日10:12:19
*/
/*==============================================================*/

drop table if exists `article`;
/*==============================================================*/
/* Table: article   文章表                                      */
/*==============================================================*/
create table `article`(
   `oid`		     int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`         varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`         int(11) NOT NULL COMMENT '模块id',
   `contributeuid`    varchar(36) COMMENT '投稿表id',
   `adminuid`    varchar(36) COMMENT '操作管理员UID',
   `authoruid`    varchar(36) COMMENT '用户uid',
   `author`      varchar(100) COMMENT '作者',
   `content`     mediumtext COMMENT '内容',
   `title`       varchar(500) COMMENT '标题', 
   `shorttitle`   varchar(500) COMMENT '标题', 
   `grade`        tinyint(1)  DEFAULT 0 COMMENT '文章等级(备用)',
   `imglittle`   varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`   varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`      varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
   `origin`      varchar(100) COMMENT '来源',
   `originurl`      varchar(100) COMMENT '来源',
   `summary`     varchar(1000) COMMENT '摘要内容',
   `keywords`    varchar(500) COMMENT '关键字',
   `sortorder`   smallint(6) DEFAULT 0 COMMENT '排序',
   `config`      smallint(6) DEFAULT 0 COMMENT '备用',
   `data`     varchar(1000) COMMENT '备用',
   `type`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发布',
   `kind`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
   `istop`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶(设为焦点文章)',
   `isrecommend`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐为首页显示',
   `ispublic`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否公开(备用)',
   `toptime`	  datetime DEFAULT NULL COMMENT '置顶时间',
   `status`      tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `publishtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '文章发布时间',
   `createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

drop table if exists `comments`;

/*==============================================================*/
/* Table: comments    评论表                                    */
/*==============================================================*/
CREATE TABLE `comments`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `fromuid`        varchar(36) NOT NULL COMMENT '所评论文章的UID',
   `touid`          varchar(36) COMMENT '回复comment的uid，对应此表的uid',
   `fromuseruid`    varchar(36) COMMENT '评论人uid',
   `fromuseroid`    int(11) COMMENT '评论人oid',
   `fromusername`   varchar(36) COMMENT '评论人name',
   `touseruid`      varchar(36) COMMENT '评论给谁的uid',
   `touseroid`      int(11) COMMENT '评论给谁的oid',
   `tousername`     varchar(36) COMMENT '评论给谁的name',
   `cataloguename`  varchar(36) COMMENT '评论模块名称',
   `cid`            varchar(36) COMMENT '评论模块cid',
   `content`        text COMMENT '评论',
   `channel`        int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';


drop table if exists `catalogue`;

/*==============================================================*/
/* Table: catalogue    目录表                                   */
/*==============================================================*/
create table `catalogue`(
  `cid`        int(11) NOT NULL COMMENT '模块id',
  `name`       varchar(100) NOT NULL COMMENT '模块名称',
  `data`       text DEFAULT NULL COMMENT '相关的其他json数据',
  `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`cid`),
   UNIQUE KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='目录表';

drop table if exists `content`;

/*==============================================================*/
/* Table: content    内容表                                     */
/*==============================================================*/
CREATE TABLE `content` (
  `uid`        varchar(36) NOT NULL COMMENT 'uid',
  `name`       varchar(1000) DEFAULT NULL COMMENT '名字',
  `type`       tinyint(1) NOT NULL COMMENT '类型(广告位|其他)',
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
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';

drop table if exists `label`;

/*==============================================================*/
/* Table: label    标签表                                                  */
/*==============================================================*/
create table `label`(
   `uid`                 varchar(36) NOT NULL COMMENT '标签UID',
   `content`             varchar(100) COMMENT '内容',
   `count`               int(11) NOT NULL DEFAULT 0 COMMENT '点击或搜索数量',
   `refuid`              text COMMENT '关键字(字符串格式保存文章uid)',
   `adminuid`            varchar(500) COMMENT '操作人uid',
   `status`              tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

drop table if exists `collect`;

/*==============================================================*/
/* Table: collection    收藏表                                  */
/*==============================================================*/
create table `collect`(
   `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
   `useruid`    varchar(36) NOT NULL COMMENT '用户UID',
   `cid`		int(11) 	COMMENT '模块cid',
   `refuid`     varchar(36) NOT NULL COMMENT '相对应文章uid',
   `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' comment '创建时间',
   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

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
    `type`       TINYINT(1) UNSIGNED NOT NULL COMMENT '消息类型',
    `isread`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
    `status`     TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`uid`),
    UNIQUE INDEX `uid` (`uid`)
)COMMENT='消息表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

drop table if exists `subscribe`;

/*==============================================================*/
/* Table: subscribe    订阅表								*/
/*==============================================================*/
CREATE TABLE IF NOT EXISTS `subscribe` (
  `uid` 				char(32) NOT NULL COMMENT '唯一ID',
  `useruid` 				varchar(32) NOT NULL COMMENT '订阅者uid',
  `refuseruid` 				varchar(32) NOT NULL COMMENT '被订阅者uid',
  `follow` 				tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '订阅状态枚举（1订阅，2互相订阅）',
  `status` 				tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` 				timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` 				timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订阅表，记录订阅人和被订阅人之间的关系。';


