/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:59 2016/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GManagerDB
Author:        chenminchang
Description:    GManagerDB 增加字段
CreatedDate:   14:21 2016/11/16
*/

ALTER TABLE `certificate` ADD `imgoriginal` varchar(255) DEFAULT NULL COMMENT '证书原图' after `imgbig`;