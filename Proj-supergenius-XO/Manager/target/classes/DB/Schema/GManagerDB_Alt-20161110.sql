/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     10:59 2016/9/19                                                          */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GManagerDB
Author:        陈敏昌
Description:    GManagerDB 版本修改的初始化数据
CreatedDate:   14:21 2016/11/10
*/

ALTER TABLE `conference` DROP COLUMN `ticketstatus`;
ALTER TABLE `conference` DROP COLUMN `ticketprice`;
ALTER TABLE `conference` DROP COLUMN `ticketcount`;
ALTER TABLE `conference` DROP COLUMN `ticketsaletimefrom`;
ALTER TABLE `conference` DROP COLUMN `ticketsaletimeto`;
ALTER TABLE `conference` DROP COLUMN `ticketsalecount`;
ALTER TABLE `conference` ADD `endtime` datetime DEFAULT NULL COMMENT '会议室结束时间' after `passwordadmin`;
ALTER TABLE `conference` ADD `begintime` datetime DEFAULT NULL COMMENT '会议室开始时间' after `passwordadmin`;

ALTER TABLE `conference` ADD `expectjoincount` int(11) DEFAULT '0' COMMENT '预期与会人数' after `maxcountspokesman`;
ALTER TABLE `conference` ADD `realjoincount` int(11) DEFAULT '0' COMMENT '实际与会人数' after `maxcountspokesman`;  