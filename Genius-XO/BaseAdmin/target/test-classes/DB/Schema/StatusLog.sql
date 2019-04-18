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

drop table if exists statuslog;

/*==============================================================*/
/* Table: statuslog                                               */
/*==============================================================*/
CREATE TABLE `statuslog` (
  `uid`                  varchar(36) NOT NULL COMMENT '唯一ID',
  `fromuid` 		        varchar(36) NOT NULL COMMENT '来自那个对象的uid',
  `adminuid` 		        varchar(36) NOT NULL COMMENT '操作管理员uid',
  `name` 		            varchar(255) COMMENT '操作名称',
  `desc` 			    varchar(36) DEFAULT NULL COMMENT '操作原因',
  `channel` 		        tinyint(1) unsigned DEFAULT '0' COMMENT '频道',
  `statusfrom` 		    tinyint(1) NOT NULL DEFAULT '0' COMMENT '原状态',
  `statusto` 		        tinyint(1) NOT NULL DEFAULT '0' COMMENT '现状态',
  `createtime` 		    timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
