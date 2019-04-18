/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:00 2013/7/14                                                           */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GAdminDB
Author:        architect.bian
Description:    创建数据库
CreatedDate:    19:00 2013/7/14
ModifyDate:     19:00 2013/7/14
*/
/*==============================================================*/

drop table if exists comments;
drop table if exists `user`;
drop table if exists `message`;
/*==============================================================*/
/* Table: comments    天财评论文章表                          		                */
/*==============================================================*/
create table comments(
   uid                      char(32) not null comment '唯一ID',
   fromuid                	char(32) not null comment '所评论文章的UID',
   touid                    char(32) comment '回复comment的uid，对应此表的uid',
   fromuseruid          	char(32) comment '评论人uid',
   fromuseroid          	int(11) comment '评论人oid',
   fromusername      		varchar(36) comment '评论人name',
   touseruid             	char(32) comment '评论给谁的uid',
   touseroid              	int(11) comment '评论给谁的oid',
   tousername          		varchar(36) comment '评论给谁的name',
   content                 	varchar(1000) comment '评论',
   type                     tinyint(1) unsigned default 0 comment '类型，评论或者赞',
   status              		tinyint(1) default 1 comment '状态(0禁用1启用)',
   channel                	tinyint(1) unsigned DEFAULT 0 COMMENT '频道',
   data                     varchar(255) comment '其他数据json格式',
   config                 	int(11) unsigned not null  comment '采用配置属性的二进制数据',
   createtime           	timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `oid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` char(32) NOT NULL COMMENT '唯一uid',
  `usersn` varchar(255) DEFAULT NULL COMMENT '会员号',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `newemail` varchar(255) DEFAULT NULL COMMENT '新邮箱',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `resetpwd` varchar(10) DEFAULT NULL COMMENT '重置密码VALUE',
  `paypwd` varchar(36) DEFAULT NULL COMMENT '支付密码',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名,2-4个汉字',
  `nickname` varchar(100) DEFAULT NULL COMMENT '用户英文名',
  `showname` varchar(100) DEFAULT NULL COMMENT '会员名',
  `account` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '账户余额',
  `freezeaccount` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '冻结金额',
  `type` int(11) DEFAULT NULL,
  `channelfrom` tinyint(1) unsigned DEFAULT NULL COMMENT '来自渠道',
  `avatarbig` varchar(100) DEFAULT NULL COMMENT '个人头像180x180',
  `avatar` varchar(255) DEFAULT NULL COMMENT '中头像90x90',
  `avatarlittle` varchar(255) DEFAULT NULL COMMENT '小头像36x36',
  `original` varchar(255) DEFAULT NULL COMMENT '原图',
  `summary` varchar(1000) DEFAULT NULL COMMENT '自我简介最多150字',
  `specialty` int(11) DEFAULT NULL COMMENT '专业',
  `job` varchar(255) DEFAULT NULL COMMENT '职位',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `company` varchar(255) DEFAULT NULL COMMENT '供职单位',
  `identityid` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `domain` varchar(255) DEFAULT NULL COMMENT '二级域名',
  `gender` tinyint(1) unsigned DEFAULT NULL COMMENT '性别(1:男2:女)',
  `birthday` date DEFAULT NULL COMMENT '出生年月日',
  `college` varchar(255) DEFAULT NULL COMMENT '毕业院校',
  `address` varchar(255) DEFAULT NULL COMMENT '现居地址',
  `qq` varchar(100) DEFAULT NULL COMMENT 'QQ',
  `msn` varchar(100) DEFAULT NULL COMMENT 'MSN',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `validcode` varchar(50) DEFAULT NULL COMMENT '邮箱验证码',
  `logincount` int(11) unsigned DEFAULT '0' COMMENT '登录次数',
  `lastlogintime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `lastloginip` varchar(50) DEFAULT '127.0.0.1' COMMENT '最后登录IP',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`oid`,`uid`),
  UNIQUE KEY `oid` (`oid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*==============================================================*/
/* Table: message    消息表                          		                */
/*==============================================================*/
create table message(
   uid						char(32) not null comment '唯一ID',
   fromuseruid              char(32) comment '来自谁UID',
   fromuseroid              int(11) comment '来自谁OID',
   fromusername             varchar(100) comment '来自谁NAME',
   touseruid          	    char(32) not null comment '发给谁UID',
   touseroid          	    int(11) comment '发给谁OID',
   tousername      		    varchar(100) comment '发给谁NAME',
   useravatar               varchar(200) comment '会员头像or网站头像',
   title              		varchar(200) comment '标题',
   content          		varchar(1000) comment '内容',
   href 		        	varchar(100) comment '链接',				
   state        		 	tinyint(1) comment '未读、已读、已删除',
   type        		 		tinyint(1) comment '消息类型',
   site        		 		tinyint(1) comment '来自哪个项目',
   data        				varchar(255) comment '其他数据json格式',
   status              		tinyint(1) default 1 comment '状态(0禁用1启用)',
   createtime           	timestamp not null default '0000-00-00 00:00:00.000' comment '创建时间',
   updatetime 				timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   primary key (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
