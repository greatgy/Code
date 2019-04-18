﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                    */
/* Created on:     11:00 2015/7/20                              */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GAccountDB
Author:        liushaomin
Description:    创建数据库
CreatedDate:    11:00 2015/7/20
*/
/*==============================================================*/

drop table if exists account;

drop table if exists accountlog;

drop table if exists refund;

/*==============================================================*/
/* Table: account    充值表                          		                        */
/*==============================================================*/

CREATE TABLE `account` (
  `uid` 			varchar(36) NOT NULL COMMENT '唯一ID',
  `payuid` 			varchar(100) NOT NULL COMMENT 'payuid',
  `accountsn` 		varchar(36) NOT NULL COMMENT '流水号',
  `useruid` 		varchar(36) NOT NULL COMMENT '用户Uid',
  `username` 		varchar(100) NOT NULL COMMENT '用户姓名',
  `useremail` 		varchar(100) NOT NULL COMMENT '用户邮箱',
  `userip` 			varchar(100) NOT NULL COMMENT '用户ip',
  `resulturl` 		varchar(100) NOT NULL COMMENT '充值结果页',
  `notifyurl` 		varchar(100) NOT NULL COMMENT '回调接口url',
  `site` 			tinyint(1) NULL DEFAULT 0 COMMENT '平台',
  `type` 			tinyint(1) unsigned DEFAULT '0' COMMENT '类型,0:充值 1会员费',
  `money` 			decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  `bank`		 	tinyint(1) DEFAULT 0 COMMENT '金额来自哪个银行',
  `desc` 			varchar(1000) DEFAULT NULL COMMENT '描述信息',
  `successtime` 	timestamp NULL COMMENT '成功时间',
  `failedtime` 		timestamp NULL COMMENT '失败时间',
  `state` 			tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户状态(0:start初始 1:pay已支付 2:success成功 3notifyex:支付异常)',
  `status` 			tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `createtime` 		timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` 		timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `payuid` (`payuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值';

/*==============================================================*/
/* Table: accountlog    充值记录表                          		                    */
/*==============================================================*/

CREATE TABLE `accountlog` (
  `uid` 				varchar(36) NOT NULL COMMENT '唯一ID',
  `accountuid` 			varchar(36) NOT NULL COMMENT '账户记录的Uid',
  `accountsn` 			varchar(36) NOT NULL COMMENT '账户记录的流水号',
  `useruid` 			varchar(36) NOT NULL COMMENT '用户Uid',
  `username` 			varchar(36) NOT NULL COMMENT '用户姓名',
  `useremail` 			varchar(36) NOT NULL COMMENT '用户邮箱',
  `bank` 				tinyint(1) COMMENT '操作的银行',
  `accountstatefrom` 	tinyint(1) unsigned DEFAULT '0' COMMENT '账户状态(0:start初始 1:pay已支付 2:success成功 3notifyex:支付异常 )',
  `accountstateto` 		tinyint(1) unsigned DEFAULT '0' COMMENT '账户状态(0:start初始 1:pay已支付 2:success成功 3notifyex:支付异常 )',
  `money` 				decimal(10,2) DEFAULT '0.00' COMMENT '操作金额',
  `desc` 				varchar(1000) DEFAULT NULL COMMENT '备注',
  `status` 				tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` 			timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值日志';

/*==============================================================*/
/* Table: refund    退款表                          		                        */
/*==============================================================*/

CREATE TABLE `refund` (
  `uid` 			varchar(36) NOT NULL COMMENT '唯一ID',
  `accountuid` 		varchar(36) NOT NULL COMMENT '账户记录的Uid',
  `out_refund_no`	 varchar(64) DEFAULT NULL COMMENT '商户退款单号',
  `transaction_id`	 varchar(36) DEFAULT NULL COMMENT '微信、支付宝等订单号',
  `useruid` 		varchar(36) NOT NULL COMMENT '用户Uid',
  `username` 		varchar(100) NOT NULL COMMENT '用户姓名',
  `useremail` 		varchar(100) NOT NULL COMMENT '用户邮箱',
  `site` 			tinyint(1) NOT NULL DEFAULT 0 COMMENT '平台',
  `money` 			decimal(10,2) DEFAULT '0.00' COMMENT '订单金额',
  `refundmoney`     decimal(10,2) DEFAULT '0.00' COMMENT '退款金额',
  `bank`		 	tinyint(1) DEFAULT 0 COMMENT '金额来自哪个银行',
  `currency`	 varchar(36) DEFAULT NULL COMMENT '货币种类',
  `data`		    varchar(255) DEFAULT null COMMENT '存放其他信息',
  `adminuid`   varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `desc` 			varchar(1000) DEFAULT NULL COMMENT '描述信息',
  `state` 			tinyint(1) NOT NULL DEFAULT 0 COMMENT '退款状态',
  `status` 			tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `createtime` 		timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` 		timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款';
