/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:59 2016/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GManagerDB
Author:        liubin
Description:    GManagerDB 2.0 版本修改的初始化数据
CreatedDate:   10:59 2016/9/19
*/
/*
`major` int(11) NOT NULL COMMENT '专业';
*/

ALTER TABLE `appjudgement` MODIFY COLUMN `major` int(11) DEFAULT NULL COMMENT '专业';
