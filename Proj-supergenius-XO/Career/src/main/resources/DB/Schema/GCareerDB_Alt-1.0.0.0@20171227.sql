
/* 职场测试增加音乐功能 */
drop table if exists `music`;
/*==============================================================*/
/* Table:   tease  职场音乐表                                                   */
/*==============================================================*/
CREATE TABLE `music` (
  `uid` varchar(36) NOT NULL COMMENT 'uid',
  `name` varchar(12) DEFAULT NULL COMMENT '歌曲名字',
  `url` varchar(100) NOT NULL COMMENT '歌曲路径',
  `temp` tinyint(1) NOT NULL COMMENT '判断本地歌曲，还是网络歌曲  0 表示本地   1 表示网络',
  `order` int NOT NULL DEFAULT 0 COMMENT '显示顺序，数字越大越靠前,值相同，则按照时间倒排序显示',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
  `adminuid` varchar(36) DEFAULT NULL COMMENT '操作人uid',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
)COMMENT='背景音乐表' ENGINE=InnoDB DEFAULT CHARSET=utf8;
