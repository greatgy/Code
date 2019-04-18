/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2017年11月13日11:46:43                                                           */
/*==============================================================*/

ALTER TABLE `article` ADD COLUMN `isoriginal` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型(0 非原创   1 原创)' after kind;