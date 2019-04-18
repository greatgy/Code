/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:28 2015/8/20                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: WebDB
Author:        YuYingJie
Description:    GUserDB2.0版本修改的表结构
CreatedDate:   19:01 2015/8/10
ModifyDate:    19:01 2013/8/20
*/

/*==============================================================*/
/*
ALTER TABLE table_name ADD column_name column-definition;
ALTER TABLE `workorder`
ADD `discount` tinyint DEFAULT NULL after saleorder_id,
ADD `note` varchar(254) DEFAULT NULL after discount;
*/

/*==============================================================*/
/* Table: message                          		            */
/*==============================================================*/

alter table message modify content varchar(4000) comment '内容';
