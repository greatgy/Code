/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     9:00 2017/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GAiDB
Author:        architect.bian
Description:    创建数据库
CreatedDate:    9:00 2017/9/19
ModifyDate:     9:00 2017/9/19
*/
/*==============================================================*/


drop table if exists `article`;

/*==============================================================*/
/* Table: article   AI项目文章表                          		                    */
/*==============================================================*/
create table `article`(
   `oid`		   int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`                   varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`                   smallint(6) NOT NULL COMMENT '模块id',
   `adminuid`              varchar(36) COMMENT '操作管理员UID',
   `useruid`               varchar(36) COMMENT '用户uid',
   `username`              varchar(100) COMMENT '投稿人',
   `author`                varchar(100) COMMENT '作者',
   `content`               mediumtext COMMENT '内容',
   `title`                 varchar(500) COMMENT '标题', 
   `imglittle`             varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`        	   varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`                varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal`           varchar(255) DEFAULT '' COMMENT '原图',
   `origin`                varchar(100) COMMENT '来源',
   `summary`           	   varchar(1000) COMMENT '摘要内容',
   `keywords`        	   varchar(500) COMMENT '关键字',
   `contact`               varchar(36) COMMENT '联系方式',
   `type`                  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发布',
   `istop`                 tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶(设为焦点文章)',
   `toptime`		       datetime DEFAULT NULL COMMENT '置顶时间',
   `status`                tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`            timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`            timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';



drop table if exists `comments`;

/*==============================================================*/
/* Table: comments    AI项目评论表                          		                    */
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
   `content`        varchar(1000) COMMENT '评论',
   `channel`   		tinyint(1) UNSIGNED DEFAULT 0 COMMENT '频道',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';



drop table if exists `collect`;

/*==============================================================*/
/* Table: collection    AI项目我的收藏表                          		                    */
/*==============================================================*/
create table `collect`(
   `uid`                 varchar(36) NOT NULL COMMENT '唯一ID',
   `useruid`             varchar(36) NOT NULL COMMENT '用户UID',
   `refuid`               varchar(36) NOT NULL COMMENT '相对应文章uid',
   `status`              tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' comment '创建时间',
   `updatetime`          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';



drop table if exists `label`;

/*==============================================================*/
/* Table: label    AI项目标签表                          		                    */
/*==============================================================*/
create table `label`(
   `uid`                 varchar(36) NOT NULL COMMENT '标签UID',
   `content`             varchar(100) COMMENT '内容',
   `count`               int(11) NOT NULL DEFAULT 0 COMMENT '点击或搜索数量',
   `refuid`        	 	 text COMMENT '关键字(字符串格式保存文章uid)',
   `adminuid`			 varchar(500) COMMENT '操作人uid',
   `status`              tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';



drop table if exists `content`;

/*==============================================================*/
/* Table: content    AI项目内容表                          		                    */
/*==============================================================*/
CREATE TABLE `content` (
  `uid`                 varchar(36) NOT NULL COMMENT 'uid',
  `name`                varchar(1000) DEFAULT NULL COMMENT '名字',
  `type`                tinyint(1) NOT NULL COMMENT '类型(广告位|其他)',
  `data`                varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary`             varchar(1000) DEFAULT NULL COMMENT '简介',
  `content`             mediumtext DEFAULT NULL COMMENT '详情',
  `originurl`           varchar(255) DEFAULT NULL COMMENT '链接',
  `status`              tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid`            varchar(36) COMMENT '操作人UID',
  `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime`          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';



drop table if exists `catalogue`;

/*==============================================================*/
/* Table: catalogue    AI项目目录模块表                          		                    */
/*==============================================================*/
create table `catalogue`(
  `pcid`				smallint(6) NOT NULL COMMENT '一级模块id',
  `cid`                 smallint(6) NOT NULL COMMENT '模块id',
  `name`                varchar(100) NOT NULL COMMENT '模块名称',
  `data`                text DEFAULT NULL COMMENT '模块的html代码',
  `status`              tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `adminuid`            varchar(36) COMMENT '操作人UID',
  `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime`          timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`cid`),
   UNIQUE KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='目录表';


drop table if exists `news`;
/*==============================================================*/
/* Table: news    AI项目消息表                		                    */
/*==============================================================*/
CREATE TABLE `news` (
    `uid` VARCHAR(36) NOT NULL COMMENT '唯一ID',
    `fromuid` VARCHAR(36) NOT NULL COMMENT '发送者id',
    `touid` VARCHAR(36) NOT NULL COMMENT '接收者id',
    `title` VARCHAR(2000) DEFAULT NULL COMMENT '标题',
    `content` VARCHAR(2000) DEFAULT NULL COMMENT '内容',
    `href` varchar(255) DEFAULT NULL COMMENT '链接地址',
    `type` TINYINT(1) UNSIGNED NOT NULL COMMENT '消息类型',
    `isread` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
    `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`uid`),
    UNIQUE INDEX `uid` (`uid`)
)COMMENT='消息表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';