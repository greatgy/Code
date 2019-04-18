/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:00 2013/7/14                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GeniusTest
Author:        architect.bian
Description:    创建数据库
CreatedDate:    19:00 2013/7/14
ModifyDate:     19:00 2013/7/14
*/
/*==============================================================*/

drop table if exists workflow;

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
