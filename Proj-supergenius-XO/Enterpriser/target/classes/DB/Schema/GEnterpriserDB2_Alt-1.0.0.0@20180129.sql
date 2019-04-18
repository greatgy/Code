﻿﻿# 新引资购商表结构
# 备注：原enterprise项目表保留，content表使用新项目的content表
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : GEnterpriseDB2


drop table if exists `article`;
/*==============================================================*/
/* Table: article   文章表                                      */
/*==============================================================*/
create table `article`(
   `oid`	 int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`         varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`         smallint(6) NOT NULL COMMENT '模块id',
   `adminuid`    varchar(36) COMMENT '操作管理员UID',
   `authoruid`   varchar(36) COMMENT '用户uid',
   `author`      varchar(100) COMMENT '作者',
   `content`     mediumtext COMMENT '内容',
   `title`       varchar(500) COMMENT '标题', 
   `imglittle`   varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`   varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`      varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal` varchar(255) DEFAULT '' COMMENT '原图',
   `origin`      varchar(100) COMMENT '来源',
   `summary`     varchar(1000) COMMENT '摘要内容',
   `keywords`    varchar(500) COMMENT 'SEO关键词',
   `contact`     varchar(36) COMMENT '联系方式',
   `type`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发布',
   `kind`        tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
   `isoriginal`  tinyint(1) DEFAULT 0 COMMENT '类型(0 非原创   1 原创)',
   `istop`       tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶(设为焦点文章)',
   `isrecommend` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐为首页显示',
   `toptime`	 datetime DEFAULT NULL COMMENT '置顶时间',
   `status`      tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';



drop table if exists `forum`;
/*==============================================================*/
/* Table: forum   论坛表                                      */
/*==============================================================*/
create table `forum`(
   `oid`	 int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `uid`         varchar(36) NOT NULL COMMENT '唯一ID',
   `cid`         smallint(6) NOT NULL COMMENT '模块id',
   `cataloguename`      varchar(100) COMMENT '模块名',
   `adminuid`    varchar(36) COMMENT '操作管理员UID',
   `authoruid`   varchar(36) COMMENT '用户uid',
   `author`      varchar(100) COMMENT '作者',
   `content`     mediumtext COMMENT '内容',
   `title`       varchar(500) COMMENT '标题', 
   `isrecommend` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐为首页显示',
   `toptime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '置顶时间',
   `summary`     varchar(1000) COMMENT 'SEO描述',
   `keywords`    varchar(500) COMMENT 'SEO关键词',
   `status`      tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `activetime`	 datetime DEFAULT NULL COMMENT '最后活跃时间(当帖子发生点赞、评论、收藏时更新)',
   `createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (`oid`,`uid`),
   UNIQUE KEY `oid` (`oid`),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛表';


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
   `cataloguename`  varchar(36) COMMENT '评论模块名称',
   `cid`            varchar(36) COMMENT '评论模块cid',
   `content`        text COMMENT '评论',
   `channel`        int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `type`           tinyint(1) UNSIGNED DEFAULT 0 COMMENT '类型，评论或者赞',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

drop table if exists `catalogue`;

/*==============================================================*/
/* Table: catalogue    目录模块表                          		                    */
/*==============================================================*/
create table `catalogue`(
  `cid`                 smallint(6) NOT NULL COMMENT '模块id',
  `pcid`                smallint(6) NOT NULL COMMENT '父模块id',
  `name`                varchar(100) NOT NULL COMMENT '模块名称',
  `content`            text DEFAULT NULL COMMENT '相关的其他json数据',
  `status`              tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
  `adminuid`            varchar(36) COMMENT '操作人UID',
  `createtime`          timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
  `updatetime`          timestamp NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最后更新时间',
   PRIMARY KEY (`cid`),
   UNIQUE KEY `cid` (`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='目录表';

drop table if exists `video`;

/*==============================================================*/
/* Table: video    视频表                                    */
/*==============================================================*/
CREATE TABLE `video`(
   `uid`            varchar(36) NOT NULL COMMENT '唯一ID',
   `title`          varchar(500) COMMENT '标题',
   `cid`            smallint(6) DEFAULT 0 COMMENT '编号',
   `content`        text COMMENT '视频代码',
   `keywords`       varchar(500) COMMENT '视频标签',
   `summary`        varchar(255) DEFAULT '' COMMENT '视频描述',
   `imglittle`      varchar(255) DEFAULT '' COMMENT '小图',
   `imgmedium`      varchar(255) DEFAULT '' COMMENT '中图',
   `imgbig`         varchar(255) DEFAULT '' COMMENT '大图',
   `imgoriginal`    varchar(255) DEFAULT '' COMMENT '原图',
   `data`           varchar(255) COMMENT '其他数据json格式',
   `istop`          tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶',
   `adminuid`       varchar(36) COMMENT '操作人UID',
   `status`         tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `createtime`     timestamp NOT NULL DEFAULT '0000-00-00 00:00:00.000' COMMENT '创建时间',
   `updatetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
   PRIMARY KEY (uid),
   UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频表';

drop table if exists `collect`;

/*==============================================================*/
/* Table: collection    收藏表                                  */
/*==============================================================*/
create table `collect`(
   `uid`        varchar(36) NOT NULL COMMENT '唯一ID',
   `useruid`    varchar(36) NOT NULL COMMENT '用户UID',
   `refuid`     varchar(36) NOT NULL COMMENT '相对应文章uid',
   `status`     tinyint(1) DEFAULT 1 COMMENT '状态(0禁用1启用)',
   `channel`    int(4) UNSIGNED DEFAULT 0 COMMENT '频道',
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


drop table if exists `content`;

/*==============================================================*/
/* Table: content    内容表                                     */
/*==============================================================*/
CREATE TABLE `content` (
  `uid`        varchar(36) NOT NULL COMMENT 'uid',
  `cid`        smallint(6) NOT NULL COMMENT '模块id',
  `name`       varchar(1000) DEFAULT NULL COMMENT '名字',
  `type`       tinyint(1) NOT NULL COMMENT '类型(广告位|其他)',
  `data`       varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `summary`    varchar(1000) DEFAULT NULL COMMENT '简介',
  `content`    text DEFAULT NULL COMMENT '详情',
  `originurl`  varchar(255) DEFAULT NULL COMMENT '链接',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `adminuid`   varchar(36) COMMENT '操作人UID',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';

drop table if exists `appcooperation`;

/*==============================================================*/
/* Table: appcooperation   互助合作报名表                       */
/*==============================================================*/
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

drop table if exists `lecture`;

/*==============================================================*/
/* Table: lecture   讲座表                                      */
/*==============================================================*/
CREATE TABLE `lecture` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `sn` varchar(255) NOT NULL COMMENT '一个讲座对应一个sn',
  `name` varchar(255) DEFAULT NULL COMMENT '讲座名称',
  `username` varchar(255) DEFAULT NULL COMMENT '演讲人名称',
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '讲座时间',
  `address` varchar(1000) DEFAULT NULL COMMENT '讲座地点',
  `notice` text DEFAULT NULL COMMENT '报名须知',
  `fee` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '报名费用',
  `maxcount` int(11) unsigned NOT NULL COMMENT '最大报名人数',
  `registercount` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '已报名人数',
  `semester` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '学期（一期一个表）',
  `data` varchar(1000) DEFAULT NULL COMMENT '以json的方式记录',
  `file` varchar(255) DEFAULT NULL COMMENT '讲座文件',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(init 未开始报名 enable 可报名 end 报名结束 wait开讲时间确定)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='讲座表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `participate`;

/*==============================================================*/
/* Table: participate   报名表                                  */
/*==============================================================*/
CREATE TABLE `participate` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `useruid` varchar(36) DEFAULT NULL COMMENT '会员uid',
  `usersn` varchar(255) DEFAULT NULL COMMENT '会员号',
  `lectureuid` varchar(36) DEFAULT NULL COMMENT '讲座uid',
  `lecturename` varchar(255) DEFAULT NULL COMMENT '讲座名称',
  `semester`  int(11) unsigned DEFAULT '1' COMMENT '学期',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱(用此字段判断是否已是会员，提示用户已是会员，不会赠送会员)',
  `address` varchar(1000) DEFAULT NULL COMMENT '邮寄地址',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='报名表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `announcement`;
/*==============================================================*/
/* Table: announcement   公告表                                  */
/*==============================================================*/
CREATE TABLE `announcement` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `sn` varchar(255) NOT NULL COMMENT '公告编号',
  `cid`         smallint(6) NOT NULL COMMENT '模块id',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `content` text DEFAULT NULL COMMENT '内容',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  `endtime` datetime DEFAULT NULL COMMENT '到期时间',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `istop`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否置顶',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='公告表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*表：article forum(陈奇)  catalogue  comments（娄）  news collect video（杨）

后台：  中国制造提升  并购产业基金  跨境并购  国际并购（陈奇）   论坛管理  引资购商图片  首页视频 静态页面（已经有）（杨光）  模块管理    广告位管理  评论管理 （娄） 
前台：首页 其他文章页（杨光） 个人中心  文章详情页  商城页 投稿  静态页面（陈奇）  论坛详情页 发表论坛  论坛列表页（娄） */
