/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     11:15 2017/9/19                                                     */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GAiDB
Author:        yangguang
Description:    GAiDB3.1.0版本添加表/修改表结构
CreatedDate:   11:15 2017/9/19
ModifyDate:    14:00 2017/9/19
*/

/*==============================================================*/

ALTER TABLE `article` CHANGE keywords tags varchar(500) DEFAULT '';
ALTER TABLE `article` ADD COLUMN `keywords` varchar(500) DEFAULT '' COMMENT 'seo的keywords' after tags;