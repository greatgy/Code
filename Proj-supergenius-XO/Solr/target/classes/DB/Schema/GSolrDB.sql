drop table if exists `terms`;

/*==============================================================*/
/* Table: terms    solr项目关键词表                          		                    */
/*==============================================================*/
create table `terms`(
   `uid`                 varchar(36) NOT NULL COMMENT '标签UID',
   `content`             varchar(100) COMMENT '内容',
   `count`               int(11) NOT NULL DEFAULT 0 COMMENT '点击或搜索数量',
   `status`              tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';



drop table if exists `content`;

/*==============================================================*/
/* Table: content    solr项目内容表                          		                    */
/*==============================================================*/
CREATE TABLE `content` (
  `uid`                 varchar(36) NOT NULL COMMENT 'uid',
  `name`                varchar(1000) DEFAULT NULL COMMENT '名字',
  `type`                tinyint(1) NOT NULL COMMENT '类型(广告位|其他)',
  `data`                varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary`             varchar(1000) DEFAULT NULL COMMENT '简介',
  `content`             mediumtext DEFAULT NULL COMMENT '详情',
  `originurl`           varchar(255) DEFAULT NULL COMMENT '链接',
  `status`              tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid`            varchar(36) COMMENT '操作人UID',
  `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime`          timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';
