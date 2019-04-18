/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:59 2016/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GManagerDB
Author:        liubin
Description:    GManagerDB 增加字段
CreatedDate:   14:21 2016/11/14
*/

ALTER TABLE `appreply` ADD `openaddress` varchar(255) DEFAULT NULL COMMENT '开题论证会地点' after `opentimeok`;
ALTER TABLE `appreply` ADD `replyaddress` varchar(255) DEFAULT NULL COMMENT '答辩地点' after `replytimeok`;

