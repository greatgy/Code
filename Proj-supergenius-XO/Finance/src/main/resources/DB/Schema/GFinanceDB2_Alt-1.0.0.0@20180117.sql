/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2017年12月29日10:15:01                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GFinanceDB2
Author:        ChenQi
Description:    GFinanceDB2 1.0版本修改的表结构
CreatedDate:   2018年1月17日16:42:46
ModifyDate:    2018年1月17日16:42:49
*/

/*==============================================================*/

ALTER TABLE `article` ADD COLUMN `isoriginal` tinyint(1) DEFAULT 0 COMMENT '类型(0 非原创   1 原创)' after kind;
ALTER TABLE `content` modify COLUMN `type` int(11);
ALTER TABLE `article` CHANGE keywords tags varchar(500) DEFAULT '';
ALTER TABLE `article` ADD COLUMN `keywords` varchar(500) DEFAULT '' COMMENT 'seo的keywords' after tags;
ALTER TABLE `article` ADD COLUMN `booktime` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT "预定时间" after publishtime;