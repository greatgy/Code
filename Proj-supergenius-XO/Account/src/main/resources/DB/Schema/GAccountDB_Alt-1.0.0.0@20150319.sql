/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:01 2013/7/14                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: WebDB
Author:        architect.bian
Description:    GWebDB1.0版本修改的表结构
CreatedDate:   19:01 2013/7/14
ModifyDate:    19:01 2013/7/14
*/

/*==============================================================*/
/*
ALTER TABLE table_name ADD column_name column-definition;
ALTER TABLE `workorder`
ADD `discount` tinyint DEFAULT NULL after saleorder_id,
ADD `note` varchar(254) DEFAULT NULL after discount;
*/

drop table if exists countdetail;

/*==============================================================*/
/* Table: countdetail    计数明细表                          		                */
/*==============================================================*/
create table countdetail(
   uid						char(32) not null comment '唯一ID',
   useruid         			char(32) comment '用户的UID',
   refuid  		            char(32) not null comment '对应的UID',
   channel      		    tinyint(1) unsigned DEFAULT '0' COMMENT '频道',
   `type`               	tinyint(1) unsigned DEFAULT '0' COMMENT '类型（点击/赞）',
   data               		varchar(255) comment '其他数据json格式',
   count            		int(11) unsigned DEFAULT '0' COMMENT '点击几次，默认为1',
   countcurr        		int(11) unsigned DEFAULT '0' COMMENT '当前点击数，点击后的计数',
   createtime      			timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
