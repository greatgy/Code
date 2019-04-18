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

drop table if exists admin;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   uid                  varchar(36) not null comment '唯一ID',
   adminid              varchar(60) not null comment '登录ID',
   pwd             		varchar(32) not null comment '密码',
   dopwd                varchar(32) not null comment '操作密码',
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

