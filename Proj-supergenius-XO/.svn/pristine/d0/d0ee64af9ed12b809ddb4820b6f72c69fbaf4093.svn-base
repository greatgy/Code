/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:00 2013/7/14                                                           */
/*==============================================================*/

drop table if exists message;
/*==============================================================*/
/* Table: message    消息表                          		                */
/*==============================================================*/
create table message(
   uid						char(32) not null comment '唯一ID',
   fromuseruid              char(32) comment '来自谁UID',
   fromuseroid              int(11) comment '来自谁OID',
   fromusername             varchar(100) comment '来自谁NAME',
   touseruid          	    char(32) comment '发给谁UID',
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
