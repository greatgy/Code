/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:01 2013/7/14                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: WebDB
Author:        architect.bian
Description:    GWebDB1.0版本修改的表结构
CreatedDate:   19:01 2013/7/14
ModifyDate:    19:01 2013/7/14
*/

/*==============================================================*/

drop table if exists `appcooperation`;

/*Author:		chenminchang
Description:	增加互助合作报名表
CodeFile:		
*/
#
# Structure for the `appcooperation` table :互助合作报名表
#

CREATE TABLE `appcooperation` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `participateuid` varchar(36) DEFAULT NULL COMMENT '专题讲座报名uid',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `semester`  int(11) DEFAULT NULL COMMENT '第几期讲座参与者',
  `address` varchar(1000) DEFAULT NULL COMMENT '邮寄地址',
  `company` varchar(255) DEFAULT NULL COMMENT '供职单位',
  `job` varchar(255) DEFAULT NULL COMMENT '职位',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='互助合作报名表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

