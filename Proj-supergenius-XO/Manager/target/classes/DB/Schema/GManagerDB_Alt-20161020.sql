/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:59 2016/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GManagerDB
Author:        liubin
Description:    GManagerDB 2.0 版本修改的初始化数据
CreatedDate:   10:59 2016/10/20
*/

ALTER TABLE `video` add COLUMN `isrecommend` tinyint(1) DEFAULT 0 COMMENT '推荐视频';
