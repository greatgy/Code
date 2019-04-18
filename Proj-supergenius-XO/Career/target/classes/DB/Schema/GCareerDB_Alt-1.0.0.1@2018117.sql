/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2018年1月17日11:46:43                                                           */
/*==============================================================*/

ALTER TABLE `article` CHANGE `keywords` `tags` VARCHAR(500) DEFAULT '';

ALTER TABLE `article` ADD COLUMN `keywords` VARCHAR(500) DEFAULT '' after tags;