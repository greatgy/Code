drop table if exists tradedetail;
#
# Structure for the `tradedetail` table : 交易明细
#

CREATE TABLE `tradedetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户uid',
  `tradeeventuid` varchar(36) DEFAULT NULL COMMENT '交易事件uid(pk|申请答辩)',
  `money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额',
  `accountcurr` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '余额',
  `sn` varchar(36) NOT NULL COMMENT '流水号',
  `orderuid` varchar(36) DEFAULT NULL COMMENT '订单Uid',
  `type` tinyint(1) NOT NULL COMMENT '交易类型(充值、视频收入、扣除挑战费、作为裁判奖励、购买视频、购买门票、购买道具、取消门票、管理员退票、申请答辩、冻结挑战费用、解冻挑战费用、挑战获胜奖励、特批增加金额、特批减少金额)',
  `name` varchar(100) NOT NULL COMMENT '操作名称(扣除挑战费、作为裁判奖励、购买视频、购买门票、购买道具、取消门票、管理员退票、申请答辩、冻结挑战费用、解冻挑战费用、挑战获胜奖励、特批增加金额、特批减少金额)',
  `site` tinyint(1) DEFAULT NULL COMMENT '交易来源的平台',
  `successtime` datetime DEFAULT NULL COMMENT '成功时间',
  `failedtime` datetime DEFAULT NULL COMMENT '失败时间',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='交易明细表' ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易明细';


drop table if exists `order`;
#
# Structure for the `order` table : 订单
#

CREATE TABLE `order` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) DEFAULT NULL COMMENT '用户唯一ID',
  `sn` varchar(60) DEFAULT NULL COMMENT '订单编号',
  `name` varchar(36) NOT NULL COMMENT '订单名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '备忘录',
  `money` decimal(10,2) DEFAULT NULL COMMENT '订单总金额', 
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态（初始化|失败|成功|未付款|已付款|取消订单|已过期|管理员退票|已删除）',
  `status` tinyint(1) DEFAULT '1' COMMENT '订单状态(0已删除1未删除)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下单时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `ordersn` (`sn`)
)COMMENT='订单' ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists orderlog;
#
# Structure for the `orderlog` table : 订单日志
#

CREATE TABLE `orderlog` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `logsn` varchar(36) NOT NULL COMMENT '流水号',
  `orderuid` varchar(36) DEFAULT '0' COMMENT '订单的uid',
  `ordersn` varchar(60) DEFAULT NULL COMMENT '订单编号',
  `useruid` varchar(36) DEFAULT NULL COMMENT '用户唯一ID',
  `name` varchar(36) NOT NULL COMMENT '订单日志名称',
  `statefrom` tinyint(1) NOT NULL DEFAULT '0' COMMENT '原状态',
  `stateto` tinyint(1) NOT NULL DEFAULT '0' COMMENT '现状态',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `logsn` (`logsn`)
)COMMENT='订单日志' ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists ordergoods;
#
# Structure for the `ordergoods` table : 订单商品
#

CREATE TABLE `ordergoods` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `orderuid` varchar(36) NOT NULL COMMENT '订单ID',
  `useruid` varchar(36) DEFAULT NULL COMMENT '用户唯一ID',
  `refuid` varchar(36) DEFAULT NULL COMMENT '商品的uid',
  `sn` varchar(36) DEFAULT NULL COMMENT '商品编号(挑战编号|视频编号)',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '商品类型(视频|门票|挑战)',
  `count` smallint(5)  DEFAULT '1' COMMENT '商品个数',
  `unitprice` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `totalprice` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '购买时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='订单商品' ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists content;
#
# Structure for the `content` table :内容表
#

CREATE TABLE `content` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `titleshort` varchar(255) DEFAULT NULL COMMENT '短标题',
  `summary` varchar(1000) DEFAULT NULL COMMENT '简介',
  `content` mediumtext DEFAULT NULL COMMENT '内容',
  `data` varchar(1000) default NULL COMMENT '以json的方式记录',
  `imgtitle` varchar(255) DEFAULT NULL COMMENT '标题图片',
  `imglittle` varchar(255) DEFAULT NULL COMMENT '小图',
  `imgmedium` varchar(255) DEFAULT NULL COMMENT '中图',
  `imgbig` varchar(255) DEFAULT NULL COMMENT '大图',
  `imgoriginal` varchar(255) DEFAULT NULL COMMENT '原图',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
 )COMMENT='内容表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists visitor;
#
# Structure for the `visitor` table : 游客
#

CREATE TABLE `visitor` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `loginip` varchar(36) COMMENT '登陆IP',
  `nickname` varchar(100) DEFAULT NULL COMMENT '游客昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '游客头像',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='游客' ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists `score`;
#
# Structure for the `score` table : 积分表
#
CREATE TABLE IF NOT EXISTS `score` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户uid',
  `total` int(11) DEFAULT NULL COMMENT '总积分(某个类型的总积分)',
  `type` tinyint(1) NOT NULL COMMENT '分数类型（写文章获得，转载文章获得，积分晋级） 取用 in',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
 PRIMARY KEY (`uid`),
 UNIQUE KEY `uid` (`uid`)
)COMMENT='动态表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists `scoredetail`;
#
# Structure for the `scoredetail` table : 积分明细
#
CREATE TABLE IF NOT EXISTS `scoredetail` (
  `uid` varchar(36) NOT NULL COMMENT '唯一ID',
  `useruid` varchar(36) NOT NULL COMMENT '用户uid',
  `score` int(11) NOT NULL COMMENT '变动积分',
  `site` tinyint(1) NOT NULL COMMENT '变动的站点，对应ESite枚举',
  `refuid` varchar(255) DEFAULT NULL COMMENT '获得积分与消费积分事件uid',
  `type` tinyint(1) DEFAULT NULL COMMENT '分数类型（写文章获得，转载文章获得，积分晋级）',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0原创1非原创)',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
 PRIMARY KEY (`uid`),
 UNIQUE KEY `uid` (`uid`)
)COMMENT='动态表' ENGINE=InnoDB DEFAULT CHARSET=utf8;



drop table if exists `user`;
#
# Structure for the `user` table :用户表
#

CREATE TABLE `user` (
  `oid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `usersn` varchar(255) DEFAULT NULL COMMENT '会员号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `newemail` varchar(255) DEFAULT NULL COMMENT '新邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
  `validcode` varchar(50) DEFAULT NULL COMMENT '邮箱验证码',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `paypwd` varchar(36) DEFAULT NULL COMMENT '支付密码',
  `resetpwd` varchar(36) DEFAULT NULL COMMENT '重置密码VALUE',
  `account` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '账户余额',
  `freezeaccount` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '冻结金额',
  `totalpay` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '累计支出',
  `totalincome` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '累计收入',
  `type` int(11) DEFAULT NULL COMMENT '用户类别，如会员、学员、裁判、专家等',
  `channelfrom` tinyint(1) unsigned DEFAULT NULL COMMENT '来自渠道(缴费|特批|邀请)',
  `logincount` int(11) unsigned DEFAULT '0' COMMENT '登录次数',
  `lastlogintime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `lastloginip` varchar(50) DEFAULT '127.0.0.1' COMMENT '最后登录IP',
  `data` varchar(255) default null COMMENT '存放其他状态信息',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开户时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`oid`,`uid`),
  UNIQUE KEY `oid` (`oid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='用户表' ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户表';



drop table if exists `userinfo`;
#
# Structure for the `userinfo` table :用户信息
#

CREATE TABLE `userinfo` (
  `uid` varchar(36) NOT NULL COMMENT '唯一uid',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名,2-4个汉字',
  `nickname` varchar(100) DEFAULT NULL COMMENT '用户英文名',
  `showname` varchar(100) DEFAULT NULL COMMENT '会员名',
  `identityid` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `gender` tinyint(1) unsigned DEFAULT NULL COMMENT '性别(1:男2:女)',
  `birthday` date DEFAULT NULL COMMENT '出生年月日',
  `college` varchar(255) DEFAULT NULL COMMENT '毕业院校',
  `address` varchar(255) DEFAULT NULL COMMENT '现居地址',
  `qq` varchar(100) DEFAULT NULL COMMENT 'QQ',
  `msn` varchar(100) DEFAULT NULL COMMENT 'MSN',
  `avatarbig` varchar(100) DEFAULT NULL COMMENT '个人头像180x180',
  `avatar` varchar(255) DEFAULT NULL COMMENT '中头像90x90',
  `avatarlittle` varchar(255) DEFAULT NULL COMMENT '小头像36x36',
  `original` varchar(255) DEFAULT NULL COMMENT '原图',
  `summary` varchar(1000) DEFAULT NULL COMMENT '自我简介最多150字',
  `job` varchar(255) DEFAULT NULL COMMENT '职位',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `company` varchar(255) DEFAULT NULL COMMENT '供职单位',
  `domain` varchar(255) DEFAULT NULL COMMENT '二级域名',
  `honor` varchar(1000) DEFAULT NULL COMMENT '个人荣誉使用 ，以json格式存储(list(map))',
  `education` varchar(1000) DEFAULT NULL COMMENT '教育经历使用，以json格式存储(list(map))',
  `work` varchar(1000) DEFAULT NULL COMMENT '工作经历使用，以json格式存储(list(map))',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='用户信息表' ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
