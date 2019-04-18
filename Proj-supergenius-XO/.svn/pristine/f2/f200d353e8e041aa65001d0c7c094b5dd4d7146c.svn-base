/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2018年12月7日10:48:23                                                        */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GUSWebDB
Author:        YongXueZhen
Description:    创建数据库
CreatedDate:    2018年12月7日10:48:23
ModifyDate:     2018年12月7日10:48:23
*/
/*==============================================================*/

drop table if exists `content`;

/*==============================================================*/
/* Table: content    内容表                                     */
/*==============================================================*/
CREATE TABLE `content` (
  `id`        varchar(36) NOT NULL COMMENT 'id',
  `name`       varchar(1000) DEFAULT NULL COMMENT '名称',
  `content`    mediumtext DEFAULT NULL COMMENT '详情',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';

/*==============================================================*/
/* Table: content    管理员用户表                                     */
/*==============================================================*/
DROP TABLE IF EXISTS `adminuser`;

CREATE TABLE `adminuser` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `usernumber` varchar(36) NOT NULL COMMENT '账号',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态[0禁用，1启用]',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';