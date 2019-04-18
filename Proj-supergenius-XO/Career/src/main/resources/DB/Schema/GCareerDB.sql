﻿# 天才职场表结构
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : GCareerDB

drop table if exists `question`;
#
# Structure for the `question` table :题库
#
CREATE TABLE `question` (
  `uid`        varchar(36) NOT NULL COMMENT '唯一uid',
  `name`       varchar(1000) NOT NULL COMMENT '题目',
  `options`    text DEFAULT NULL COMMENT '选项及选项内容，以json的方式记录{"A":"",...}',
  `optiontype` varchar(1000) NOT NULL COMMENT '选项及选项类型，以json的方式记录{"A":0,...}',
  `order`      int(11) NOT NULL DEFAULT 0 COMMENT '显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示',
  `adminuid`   varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `img`        varchar(100) DEFAULT NULL COMMENT 'question的题目插图，存储的图片路径',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:当前显示状态,13:旧的状态,2:删除状态)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='题库' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `statistics`;
#
# Structure for the `statistics` table :测试结果统计表
#
CREATE TABLE `statistics` (
  `uid`        varchar(36) NOT NULL COMMENT 'uid',
  `data`       text DEFAULT NULL COMMENT '记录被调查者回答的题目uid及选项，json格式存储{"questionuid":"A",...}',
  `servant`    float DEFAULT NULL COMMENT '公务员偏差',
  `profession` float DEFAULT NULL COMMENT '专业人士偏差',
  `art`        float DEFAULT NULL COMMENT '文化艺术偏差',
  `marketing`  float DEFAULT NULL COMMENT '营销偏差',
  `manager`    float DEFAULT NULL COMMENT '企业管理偏差',
  `artisan`    float DEFAULT NULL COMMENT '技术人员偏差',
  `ruleruid`   varchar(36) DEFAULT NULL COMMENT '测试结果',
  `loginip` varchar(50) DEFAULT '127.0.0.1' COMMENT '登录IP',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='测试结果统计表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `ruler`;
#
# Structure for the `ruler` table :规则表
#
CREATE TABLE `ruler` (
  `uid`        varchar(36) NOT NULL COMMENT 'uid',
  `name`       varchar(255) DEFAULT NULL COMMENT '职务名称',
  `cid`        smallint(6) DEFAULT NULL COMMENT '职业id',
  `research`   float DEFAULT 0.00 COMMENT '研究型参数',
  `convention` float DEFAULT 0.00 COMMENT '常规型参数',
  `art`        float DEFAULT 0.00 COMMENT '艺术型参数',
  `enterprise` float DEFAULT 0.00 COMMENT '企业型参数',
  `sociology`  float DEFAULT 0.00 COMMENT '社会型参数',
  `reality`    float DEFAULT 0.00 COMMENT '现实型参数',
  `adminuid`   varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='规则表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `article`;
/*==============================================================*/
/* Table: article   文章表                                      */
/*==============================================================*/
create table `article`(
   `oid`		   int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`         varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`         smallint(6) NOT NULL COMMENT '模块id',
   `adminuid`    varchar(36) COMMENT '操作管理员UID',
   `useruid`     varchar(36) COMMENT '用户uid',
   `author`      varchar(100) COMMENT '作者',
   `content`     mediumtext COMMENT '内容',
   `title`       varchar(500) COMMENT '标题', 
   `imglittle`   varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`   varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`      varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
   `origin`      varchar(100) COMMENT '来源',
   `summary`     varchar(1000) COMMENT '摘要内容',
   `keywords`    varchar(500) COMMENT '标签',
   `type`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发布',
   `kind`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
   `istop`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶(设为焦点文章)',
   `toptime`	  datetime DEFAULT NULL COMMENT '置顶时间',
   `status`      tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

drop table if exists `comments`;

/*==============================================================*/
/* Table: comments    评论表                                    */
/*==============================================================*/
CREATE TABLE `comments`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `fromuid`        varchar(36) NOT NULL COMMENT '所评论文章的UID',
   `touid`          varchar(36) COMMENT '回复comment的uid，对应此表的uid',
   `fromuseruid`    varchar(36) COMMENT '评论人uid',
   `fromuseroid`    int(11) COMMENT '评论人oid',
   `fromusername`   varchar(36) COMMENT '评论人name',
   `touseruid`      varchar(36) COMMENT '评论给谁的uid',
   `touseroid`      int(11) COMMENT '评论给谁的oid',
   `tousername`     varchar(36) COMMENT '评论给谁的name',
   `content`        varchar(1000) COMMENT '评论',
   `channel`        int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

drop table if exists `tease`;

/*==============================================================*/
/* Table:   tease  职场鬼话表（吐槽专区）                                                   */
/*==============================================================*/
CREATE TABLE `tease`(
   `uid`          varchar(36) NOT NULL COMMENT '唯一ID',
   `touid`        varchar(36) COMMENT '回复comment的uid，对应此表的uid',
   `fromuseruid`  varchar(36) COMMENT '评论人uid',
   `fromuseroid`  int(11) COMMENT '评论人oid',
   `fromusername` varchar(36) COMMENT '评论人name',
   `touseruid`    varchar(36) COMMENT '评论给谁的uid',
   `touseroid`    int(11) COMMENT '评论给谁的oid',
   `tousername`   varchar(36) COMMENT '评论给谁的name',
   `content`      varchar(1000) COMMENT '评论',
   `imglittle`    text DEFAULT NULL COMMENT '小图',
   `imgmedium`    text DEFAULT NULL COMMENT '中图',
   `imgbig`       text DEFAULT NULL COMMENT '大图',
   `imgoriginal`  text DEFAULT NULL COMMENT '原图',
   `data`         varchar(255) COMMENT '其他数据json格式',
   `channel`      int(4) UNSIGNED DEFAULT 0 COMMENT '吐槽或者鬼话',
   `type`         tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`     varchar(36) COMMENT '操作人UID',
   `status`       tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`   timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='吐槽表';

drop table if exists `catalogue`;

/*==============================================================*/
/* Table: catalogue    目录表                                   */
/*==============================================================*/
create table `catalogue`(
  `cid`        smallint(6) NOT NULL COMMENT '模块id',
  `name`       varchar(100) NOT NULL COMMENT '模块名称',
  `data`       varchar(1000) DEFAULT NULL COMMENT '相关的其他json数据',
  `isspecial`  tinyint(1) DEFAULT 0 COMMENT '是否属于应聘指南下的专题（0否1是）',
  `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`cid`),
   UNIQUE KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='目录表';

drop table if exists `content`;

/*==============================================================*/
/* Table: content    内容表                                     */
/*==============================================================*/
CREATE TABLE `content` (
  `uid`        varchar(36) NOT NULL COMMENT 'uid',
  `name`       varchar(1000) DEFAULT NULL COMMENT '名字',
  `type`       tinyint(1) NOT NULL COMMENT '类型(广告位|应聘指南|其他)',
  `data`       varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary`    varchar(1000) DEFAULT NULL COMMENT '简介',
  `content`    mediumtext DEFAULT NULL COMMENT '详情',
  `originurl`  varchar(255) DEFAULT NULL COMMENT '链接',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';

drop table if exists `collect`;

/*==============================================================*/
/* Table: collection    收藏表                                  */
/*==============================================================*/
create table `collect`(
   `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
   `useruid`    varchar(36) NOT NULL COMMENT '用户UID',
   `refuid`     varchar(36) NOT NULL COMMENT '相对应文章uid',
   `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' comment '创建时间',
   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`uid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

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
    `type`       TINYINT(1) UNSIGNED NOT NULL COMMENT '消息类型',
    `isread`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读1已读)',
    `status`     TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
    `createtime` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`uid`),
    UNIQUE INDEX `uid` (`uid`)
)COMMENT='消息表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

drop table if exists `problem`;

/*==============================================================*/
/* Table: problem   天才职场问题表                              */
/*==============================================================*/
create table `problem`(
   `oid`        int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
   `adminuid`   varchar(36) COMMENT '操作管理员UID',
   `useruid`    varchar(36) COMMENT '提问者uid',
   `author`     varchar(100) COMMENT '作者',
   `content`    mediumtext COMMENT '内容',
   `title`      varchar(500) COMMENT '标题', 
   `istop`      tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '推荐(设为推荐)',
   `toptime`    timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '置顶时间',
   `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `answer`;

/*==============================================================*/
/* Table: answer   天才职场回答表                               */
/*==============================================================*/
CREATE TABLE `answer`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `fromuid`        varchar(36) NOT NULL COMMENT '所回答问题的UID',
   `tittle`         varchar(500) COMMENT '所回答问题的标题',
   `touid`          varchar(36) COMMENT '回复回答的uid，对应此表的uid',
   `fromuseruid`    varchar(36) COMMENT '回答人uid',
   `fromuseroid`    int(11) COMMENT '回答人oid',
   `fromusername`   varchar(36) COMMENT '回答人name',
   `touseruid`      varchar(36) COMMENT '回答给谁的uid',
   `touseroid`      int(11) COMMENT '回答给谁的oid',
   `tousername`     varchar(36) COMMENT '回答给谁的name',
   `content`        varchar(1000) COMMENT '回答内容',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `channel`        int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，回答或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答表';
