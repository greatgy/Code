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

drop table if exists adminlog;

/*==============================================================*/
/* Table: adminlog                                               */
/*==============================================================*/
create table adminlog
(
   uid               char(32) not null comment '唯一UID',
   adminuid          char(32) not null comment '管理员UID',
   adminname         varchar(100) not null comment '管理员name',
   dataid            varchar(32) not null comment '操作对象ID',
   data              varchar(1000)not null comment '操作参数',
   channel           int(11) unsigned not null comment '操作频道',
   operation         varchar(255) not null comment '操作名称',
   `desc`            varchar(1000) not null comment '操作描述',
   createtime        timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

