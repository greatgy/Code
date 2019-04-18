/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2018年5月7日10:54:51                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GLifeDB
Author:        architect.bian
Description:    GLifeDB1.0版本修改的表结构
CreatedDate:   2018年5月7日10:54:44
ModifyDate:    2018年5月7日10:54:47
*/

/*
 课程表添加，出版时间publishtime， 应用学校useadress
*/
ALTER TABLE course ADD publishtime timestamp  DEFAULT '0000-00-00 00:00:00.000' COMMENT '出版时间' AFTER press; 
ALTER TABLE course ADD useadress text DEFAULT NULL COMMENT '应用学校' AFTER publishtime; 
/**
 * 举报表添加举报的所属板块
 */
ALTER TABLE complaint ADD cid  bigint(20) DEFAULT 0 COMMENT '模块id' AFTER uid; 
ALTER TABLE complaint ADD cataloguename  varchar(36) DEFAULT NULL COMMENT '模块名称' AFTER cid; 

