/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:00 2013/7/14                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: TestDB
Author:        architect.bian
Description:    创建数据库
CreatedDate:    19:00 2013/7/14
ModifyDate:     19:00 2013/7/14
*/
/*==============================================================*/

drop table if exists region;

/*==============================================================*/
/* Table: region                                               */
/*==============================================================*/
create table `region` (
  `id`                   char(6) not null,
  `name`                 varchar(60) default null,
  `name_english`         varchar(254) default '',
  `name_short`           varchar(60) default null,
  `postcode`             varchar(6) default null,
  `areacode`             varchar(8) default null,
  `parentid`             varchar(6) default '',
  `note`                 varchar(254) default '',
  PRIMARY KEY (`id`)
);

