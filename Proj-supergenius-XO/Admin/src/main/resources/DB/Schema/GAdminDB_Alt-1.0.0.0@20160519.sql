/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:22 2016/5/19                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: WebDB
Author:        XieMing
Description:    GWebDB1.0版本修改的表结构
CreatedDate:   19:01 2013/7/14
ModifyDate:    19:01 2013/7/14
*/

/*==============================================================*/
/*
ALTER TABLE adminlog modify operation,data DEFAULT NULL; 
*/

ALTER TABLE `adminlog` MODIFY operation varchar(255) default null comment '操作名称';
ALTER TABLE `adminlog` MODIFY data varchar(1000) default null comment '操作参数';

drop table if exists emaillog;

/*==============================================================*/
/* Table: emaillog                                                 */
/*==============================================================*/
create table emaillog
(
   uid                  varchar(36) not null comment '编号',
   adminid              varchar(36) not null comment '管理员ID',
   title                varchar(255) default null COMMENT '标题',
   content              mediumtext default null comment '内容',
   receiver             varchar(1000) not null comment '接收人（json存储）',
   sender               varchar(1000) not null comment '发送人', 
   createtime           timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   updatetime           timestamp not null default current_timestamp on update current_timestamp comment '最后更新时间',
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;