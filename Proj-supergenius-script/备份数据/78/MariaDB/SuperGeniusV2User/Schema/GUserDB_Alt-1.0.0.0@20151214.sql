/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:15 2015/12/14                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GUserDB
Author:        YuYingJie
Description:   GUserDB3.1.0版本添加表/修改表结构
CreatedDate:   11:15 2015/12/14
ModifyDate:    11:15 2015/12/14
*/

/*==============================================================*/

ALTER TABLE `comments` ADD COLUMN `data` varchar(255) COMMENT '其他数据json格式' AFTER `channel`;
ALTER TABLE `comments` ADD COLUMN `config` int(11) unsigned not null  COMMENT '采用配置属性的二进制数据' AFTER `data`;
