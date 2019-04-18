/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:01 2013/7/14                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase:      GUserDB2
Author:        YongXueZhen
Description:   GUserDB2.0.0.0版本修改的表结构
ModifyDate:    2018年07月25日17:12:29
*/

/*==============================================================*/


drop table if exists `news`;
/*==============================================================*/
/* Table: news    消息表                                        */
/*==============================================================*/
CREATE TABLE `news` (
    `uid`        VARCHAR(36) NOT NULL COMMENT '唯一ID',
    `fromuid`    VARCHAR(36) NOT NULL COMMENT '发送者id',
    `touid`      VARCHAR(36) NOT NULL COMMENT '接收者id',
    `title`      VARCHAR(2000) DEFAULT NULL COMMENT '标题',
    `content`    VARCHAR(2000) DEFAULT NULL COMMENT '内容',
    `href`       varchar(255) DEFAULT NULL COMMENT '链接地址',
    `type`       TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '消息类型',
    `isread`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
    `status`     TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`uid`),
    UNIQUE INDEX `uid` (`uid`)
)COMMENT='消息表' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';




