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

drop table if exists adminrole;

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
   `desc`                varchar(255) comment '描述',
   status               tinyint(1) default 1 comment '状态(0禁用1启用)',
   createtime           timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   updatetime           timestamp not null default current_timestamp on update current_timestamp comment '最后更新时间', 
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

