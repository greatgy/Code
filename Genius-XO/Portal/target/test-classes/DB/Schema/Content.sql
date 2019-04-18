/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:00 2013/7/14                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: TestDB
Author:        architect.bian
Description:    创建数据库
CreatedDate:    19:00 2013/7/14
ModifyDate:     19:00 2013/7/14
*/
/*==============================================================*/

drop table if exists content;

/*==============================================================*/
/* Table: content                                               */
/*==============================================================*/
create table content
(
   oid                  int(11) unsigned not null auto_increment comment '自增主键',
   type                 tinyint unsigned comment '类型',
   adminuid             varchar(36) not null comment '管理员ID',
   title                varchar(150) comment '标题',
   titleshort           varchar(150) comment '简短标题',
   titleimg            varchar(255) default '' comment '标题图',
   imglittle            varchar(255) default '' comment '小图',
   imgmedium            varchar(255) default '' comment '中图',
   imgbig               varchar(255) default '' comment '大图',
   imgoriginal          varchar(255) default '' comment '原图',
   isimgshow        	tinyint(1) unsigned not null default 1 comment '标题图,是否在内容中显示',
   author               varchar(100) comment '作者',
   origin               varchar(100) comment '来源',
   originurl            varchar(255) comment '来源链接',
   summary              varchar(1000) comment '摘要内容',
   content              mediumtext comment '内容',
   sortorder            int(11) unsigned not null default 0 comment '排列顺序',
   istop                tinyint(1) unsigned not null default 1 comment '置顶',
   keywords             varchar(100) comment '页面中的keywords',
   description          varchar(100) comment '页面中的description',
   status               tinyint(1) default 1 comment '状态(0禁用1启用)',
   createtime           timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   updatetime           timestamp not null default current_timestamp on update current_timestamp comment '最后更新时间', 
   primary key (oid),
   UNIQUE KEY `oid` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
