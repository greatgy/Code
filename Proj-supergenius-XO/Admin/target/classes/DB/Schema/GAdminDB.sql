/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:00 2013/7/14                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GAdminDB
Author:        architect.bian
Description:    创建数据库
CreatedDate:    19:00 2013/7/14
ModifyDate:     19:00 2013/7/14
*/
/*==============================================================*/
/*
drop database if EXISTS `GAdminDB`;

CREATE DATABASE `GAdminDB`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

use `GAdminDB`;
*/

drop table if exists admin;

drop table if exists adminrole;

drop table if exists adminauthority;

drop table if exists adminlog;

drop table if exists workorder;

drop table if exists workflow;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   uid                  varchar(36) not null comment '唯一ID',
   adminid              varchar(60) not null comment '登录ID',
   `pwd`             	varchar(32) not null comment '密码',
   `dopwd`              varchar(32) not null comment '操作密码',
   name                 varchar(255) comment '名称',
   email                varchar(60) comment '邮箱',
   mobile               varchar(60) comment '手机',
   status               tinyint(1) default 1 comment '状态(0禁用1启用)',
   createtime           timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   updatetime           timestamp not null default current_timestamp on update current_timestamp comment '最后更新时间',
   primary key (uid),
   UNIQUE KEY `uid` (`uid`),
   UNIQUE KEY `adminid` (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: adminrole                                             */
/*==============================================================*/
create table adminrole
(
   uid                  varchar(36) not null comment '唯一ID',
   adminuid             varchar(36) comment '管理员UID',
   adminid              varchar(36) comment '管理员ID',
   roleid               varchar(255) not null comment '权限',
   rolename             varchar(255) comment '权限名',
   `desc`               varchar(255) comment '描述',
   status               tinyint(1) default 1 comment '状态(0禁用1启用)',
   createtime           timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   updatetime           timestamp not null default current_timestamp on update current_timestamp comment '最后更新时间', 
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: adminauthority                                           */
/*==============================================================*/
CREATE TABLE `adminauthority` (
  `uid`                  varchar(36) NOT NULL COMMENT '唯一ID',
  `roleuid`              varchar(36) NOT NULL COMMENT '权限ID',
  `roleid`               varchar(255) NOT NULL COMMENT '权限',
  `url`                  varchar(255) NOT NULL COMMENT 'URL,正则',
  `name`                 varchar(255) COMMENT 'URL名称',
  `desc`                 varchar(255)  COMMENT '描述',
  `status`               tinyint(1) default 1 comment '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: adminlog                                               */
/*==============================================================*/
create table adminlog
(
   uid               char(32) not null comment '唯一UID',
   adminuid          char(32) not null comment '管理员UID',
   dataid            char(32) not null comment '操作对象UID',
   channel           int(11) unsigned not null comment '操作频道',
   operation         varchar(255) not null comment '操作名称',
   data              varchar(1000)not null comment '操作参数',
   `desc`            varchar(1000) not null comment '操作描述',
   createtime        timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: workorder                                             */
/*==============================================================*/

CREATE TABLE `workorder` (
  `uid`             varchar(36) NOT NULL COMMENT '唯一ID',
  `sn`              varchar(255) DEFAULT NULL COMMENT '工单号',
  `adminuid`		varchar(36) DEFAULT NULL COMMENT '操作管理员uid',
  `adminname` 		varchar(36) DEFAULT NULL COMMENT '操作管理员姓名',
  `title` 			varchar(36) DEFAULT '' COMMENT '标题',
  `fromuid` 		varchar(36) COMMENT '来自哪个对象的uid',
  `name`			varchar(36) DEFAULT '' COMMENT '对象名称',
  `desc` 			varchar(1000) DEFAULT NULL COMMENT '操作原因',
  `remark` 			varchar(1000) DEFAULT NULL COMMENT '评论批复',
  `datafile` 		varchar(255) DEFAULT NULL COMMENT '数据文件',
  `type` 			tinyint(1) unsigned DEFAULT '0' COMMENT '工单类型',
  `channel` 		tinyint(1) unsigned DEFAULT '0' COMMENT '频道，备用',
  `stageform` 		tinyint(1)  DEFAULT '0' COMMENT '更改前阶段',
  `stageto` 		tinyint(1)  DEFAULT '0' COMMENT '更改后阶段',
  `status` 			tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `createtime` 		timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` 		timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table: workflow                                              */
/*==============================================================*/
CREATE TABLE `workflow` (
  `uid` 				varchar(36) NOT NULL COMMENT '唯一ID',
  `workorderuid` 		varchar(255) DEFAULT NULL COMMENT '工单UID',
  `adminuid`			varchar(36) DEFAULT NULL COMMENT '操作管理员uid',
  `adminname` 			varchar(36) DEFAULT NULL COMMENT '操作管理员姓名',
  `title` 				varchar(36) DEFAULT '' COMMENT '标题',
  `desc` 				varchar(1000) DEFAULT NULL COMMENT '操作原因',
  `remark` 				varchar(1000) DEFAULT NULL COMMENT '评论批复',
  `datafile` 			varchar(255) DEFAULT NULL COMMENT '数据文件，备用',
  `stageform` 			tinyint(1)  DEFAULT '0' COMMENT '更改前阶段',
  `stageto` 			tinyint(1)  DEFAULT '0' COMMENT '更改后阶段',
  `statusfrom` 			tinyint(1) NOT NULL DEFAULT '0' COMMENT '原状态',
  `statusto` 			tinyint(1) NOT NULL DEFAULT '0' COMMENT '现状态',
  `type` 			    tinyint(1) unsigned DEFAULT '0' COMMENT '工单类型',
  `status` 				tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `createtime` 			timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` 			timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
