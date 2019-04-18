/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:59 2016/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GManagerDB
Author:        liubin
Description:    GManagerDB 2.0 版本修改的初始化数据
CreatedDate:   10:59 2016/10/21
*/

ALTER TABLE `pkschedule` add COLUMN `judgementchairuid` varchar(36) DEFAULT NULL COMMENT '主裁判useruid' after `isallowjudgement2`;
