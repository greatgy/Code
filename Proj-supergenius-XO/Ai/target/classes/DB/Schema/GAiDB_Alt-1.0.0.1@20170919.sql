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

ALTER TABLE `article` ADD COLUMN `kind` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型(0 文章   1 视频   2 图片)' after title;
ALTER TABLE `article` ADD COLUMN `catagory` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型(0 游客投稿   1 会员投稿)' after kind;
ALTER TABLE `article` ADD COLUMN `isoriginal` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型(0 非原创   1 原创)' after catagory;