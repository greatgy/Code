/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.28 : Database - gmoralnewsdb
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


USE `gMoralNewsDB`;


/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `oid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `cid` int(11) NOT NULL COMMENT '模块id',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作管理员UID',
  `authoruid` varchar(36) DEFAULT NULL COMMENT '用户uid',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `content` mediumtext COMMENT '内容',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `imglittle` varchar(255) DEFAULT '' COMMENT '小图',
  `imgmedium` varchar(255) DEFAULT '' COMMENT '中图',
  `imgbig` varchar(255) DEFAULT '' COMMENT '大图',
  `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
  `origin` varchar(100) DEFAULT NULL COMMENT '来源',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要内容',
  `keywords` varchar(500) DEFAULT '' COMMENT 'seo的keywords',
  `data` varchar(1000) DEFAULT NULL COMMENT '备用',
  `type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否发布',
  `kind` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '类型（帖子或文章）',
  `isnick` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否匿名（0非匿名	1匿名）',
  `isoriginal` tinyint(1) DEFAULT '0' COMMENT '类型(0 非原创   1 原创)',
  `istop` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '置顶(设为焦点文章)',
  `toptime` datetime DEFAULT NULL COMMENT '置顶时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`oid`,`uid`),
  UNIQUE KEY `oid` (`oid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';


/*Table structure for table `catalogue`*/

DROP TABLE IF EXISTS `catalogue`;

CREATE TABLE `catalogue` (
  `cid` int(11) NOT NULL COMMENT '模块id',
  `name` varchar(100) NOT NULL COMMENT '模块名称',
  `data` text COMMENT '相关的其他json数据',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='目录表';
/*Table structure for table `collect` */

DROP TABLE IF EXISTS `collect`;

CREATE TABLE `collect` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户UID',
  `cid` int(11) DEFAULT NULL COMMENT '模块cid',
  `refuid` varchar(36) NOT NULL COMMENT '相对应文章uid',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';


/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `fromuid` varchar(36) NOT NULL COMMENT '所评论文章的UID',
  `touid` varchar(36) DEFAULT NULL COMMENT '回复comment的uid，对应此表的uid',
  `fromuseruid` varchar(36) DEFAULT NULL COMMENT '评论人uid',
  `fromuseroid` int(11) DEFAULT NULL COMMENT '评论人oid',
  `fromusername` varchar(36) DEFAULT NULL COMMENT '评论人name',
  `touseruid` varchar(36) DEFAULT NULL COMMENT '评论给谁的uid',
  `touseroid` int(11) DEFAULT NULL COMMENT '评论给谁的oid',
  `tousername` varchar(36) DEFAULT NULL COMMENT '评论给谁的name',
  `cid` int(11) DEFAULT NULL COMMENT '评论模块cid',
  `content` text COMMENT '评论',
  `channel` int(4) unsigned DEFAULT '0' COMMENT '频道',
  `data` varchar(255) DEFAULT NULL COMMENT '其他数据json格式',
  `type` tinyint(1) unsigned DEFAULT '0' COMMENT '类型，评论或者赞',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人UID',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';


/*Table structure for table `content` */

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `cid` int(11) DEFAULT NULL COMMENT '模块cid',
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `name` varchar(1000) DEFAULT NULL COMMENT '名字',
  `type` int(11) DEFAULT NULL COMMENT '广告位或其他内容',
  `data` varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary` varchar(1000) DEFAULT NULL COMMENT '简介',
  `content` mediumtext COMMENT '详情',
  `originurl` varchar(255) DEFAULT NULL COMMENT '链接',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';


/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `fromuid` varchar(36) NOT NULL COMMENT '发送者id',
  `touid` varchar(36) NOT NULL COMMENT '接收者id',
  `title` varchar(2000) DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `href` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `type` tinyint(1) unsigned NOT NULL COMMENT '消息类型',
  `isread` tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';


/*Table structure for table `announcement` */

DROP TABLE IF EXISTS `announcement`;

CREATE TABLE `announcement` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `sn` varchar(255) NOT NULL COMMENT '公告编号',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `content` text DEFAULT NULL COMMENT '内容',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `endtime` datetime DEFAULT NULL COMMENT '到期时间',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `istop`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否置顶',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='公告表' ENGINE=InnoDB DEFAULT CHARSET=utf8;