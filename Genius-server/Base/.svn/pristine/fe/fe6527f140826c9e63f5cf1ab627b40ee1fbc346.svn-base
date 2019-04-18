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

drop table if exists workorder;

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
