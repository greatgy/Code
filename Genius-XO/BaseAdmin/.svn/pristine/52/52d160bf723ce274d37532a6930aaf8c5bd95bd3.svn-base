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

drop table if exists adminauthority;

/*==============================================================*/
/* Table: adminauthority                                           */
/*==============================================================*/
CREATE TABLE `adminauthority` (
  `uid`                  varchar(36) NOT NULL COMMENT '唯一ID',
  `roleuid`              varchar(36) NOT NULL COMMENT '权限ID',
  `roleid`               varchar(255) NOT NULL COMMENT '权限',
  `url`                  varchar(255) NOT NULL COMMENT 'URL,正则',
  `name`                 varchar(255) COMMENT 'URL名称',
  `desc`                varchar(255)  COMMENT '描述',
  `status`               tinyint(1) default 1 comment '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

