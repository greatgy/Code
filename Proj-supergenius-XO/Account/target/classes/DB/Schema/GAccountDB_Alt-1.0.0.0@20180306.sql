﻿/*==============================================================*/
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

ALTER TABLE `account` ADD COLUMN `available` decimal(10,2) DEFAULT '0.00' after money;
