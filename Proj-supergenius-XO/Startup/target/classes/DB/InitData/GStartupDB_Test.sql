﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     12:20 2017/6/20                                                */
/*==============================================================*/

/**
 * 规则表测试数据
 * @author : ChenQi
 * @date : 2017年6月20日12:30:50
 */
INSERT INTO `ruler` (`uid`, `type`, `name`, `rejectmincount`, `rejectmaxcount`, `minscore`, `maxscore`, `content`, `status`, `createtime`) VALUES 
 ('uid00000000000000000000000000000', 0, '适合创业', 0, 0, 256, 320, '适合创业', 1, '2017-06-20 14:14:55');

 /**
 * 题库表测试数据
 * @author : ChenQi
 * @date : 2017年6月20日12:40:39
 */
INSERT INTO `question` (`uid`, `type`, `name`, `options`, `optionscore`, `order`, `img`, `status`, `createtime`) VALUES 
 ('uid11111111111111111111111111111', 1, '当你一眼看中一件价格相对昂贵但你能支付得起的商品时，你通常会', '{"":{"A":"立刻购买", "B":"将同类商品浏览一遍，货比三家之后再购买", "C":"多争求几个人的意见，再做决定", "D":"很难决定，最好有人帮我下决心", "E":"等到降价再买", "F":"放弃购买"}}', '{"":{"A":7, "B":10, "C":3, "D":0, "E":5, "F":6}}', 1, 'c://img', 0, '2017-06-20 14:15:09');
 
 /**
 * 调查结果统计表测试数据
 * @author : ChenQi
 * @date : 2017年6月20日14:09:43
 */
INSERT INTO `statistics` (`uid`, `score`, `ruleruid`, `data`, `loginip`, `name`, `nickname`, `gender`, `email`, `phone`, `address`, `advice`, `status`, `createtime`) VALUES 
 ('uid22222222222222222222222222222', 270, 'uid00000000000000000000000000000', '{"uid11111111111111111111111111111":"A"}', '127.0.0.1',"zhangsan", "旺财", 0, "123456789@qq.com", "18338384380", "北京市海淀区", "这是一条测试的意见反馈", 1, '2017-06-20 14:15:17');

  /**
 * 背景音乐表测试数据
 * @author : 许志翔
 * @date : 2017年8月9日14:38:54
 */
INSERT INTO `music` (`uid`, `name`, `url`, `temp`, `order`, `status`, `createtime`) VALUES 
 ('uid11111111111111111111111111111', "测试音乐", "http://192.168.1.96:8102/files/default/music/music1.mp3", 0, 0, 0, '2017-08-20 14:15:17');

 /**
 * 文章测试数据
 * @author : 许志翔
 * @date : 2017年8月23日15:15:16
 */
insert into `article` (`oid`, `uid`, `cid`, `adminuid`, `authoruid`, `author`, `content`, `title`, `imglittle`, `imgmedium`, `imgbig`, `imgoriginal`, `origin`, `summary`, `istop`, `toptime`, `status`, `createtime`, `updatetime`) values('10','uid11111111111111111111111111111','1','uid11111111111111111111111111112','uid11111111111111111111111111113','张三','我是测试内容','我是测试标题','','','','',NULL,NULL,'0','0000-00-00 00:00:00','1','2017-08-20 14:15:17','2017-08-20 14:15:17');
  
/**
 * 模块目录测试数据
 * @author : 许志翔
 * @date : 2017年8月23日15:36:23
 */
INSERT INTO `catalogue` (`cid`, `pcid`, `name`, `status`, `createtime`, `updatetime`) VALUES 
 (0, 0, '创业花絮', 0, '2017-08-20 14:15:17', '2017-08-20 14:15:17');

 /**
 * 评论测试数据
 * @author : ChenQi
 * @date : 2017年8月23日14:58:41
 */
INSERT INTO `comments` (`uid`, `fromuid`, `touid`, `fromuseruid`, `fromuseroid`, `fromusername`, `touseruid`, `touseroid`, `tousername`, `content`, `channel`, `data`, `type`, `adminuid`, `status`, `createtime` ) VALUES 
 ('uid00000000000000000000000000000', "uid11111111111111111111111111111", "uid00000000000000000000000000001", "uid00000000000000000000000000000", 10, "张一", "55cb3166ac634cb7b4616c4392fc9bfe", 13, "张细汉", "我是一条评论", 1, "", 0, "86883b04795f38d0a70b1f9004c1f989", 0, '2017-8-23 15:16:54');

 /**
 * 内容测试数据
 * @author : ChenQi
 * @date : 2017年8月23日14:58:47
 */
INSERT INTO `content` (`uid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `adminuid`, `status`, `createtime`) VALUES 
 ('uid00000000000000000000000000000', "广告", 0, "", "广告简介", "我是一个萌萌哒广告", "http://www.supergenius.cn/", "86883b04795f38d0a70b1f9004c1f989", 0,'2017-8-23 15:17:02');

 /**
 * Author:yangguang
 * inbox表的测试数据
 */
  INSERT INTO `inbox` (`uid`, `useruid`, `newsuid`, `isread`, `type`, `status`, `createtime`, `updatetime`) VALUES 
  ('uid00000000000000000000000000000','uid11111111111111111111111111111','uid22222222222222222222222222222','0','1','1','2016-08-04 12:26:13','2016-08-04 12:26:13');
  
  /**
 * Author:yangguang
 * news表的测试数据
 */
  INSERT INTO `news` (`uid`, `fromuid`, `title`, `content`, `href`, `type`, `status`, `createtime`, `updatetime`) VALUES
  ('uid22222222222222222222222222222','uid00000000000000000000000000000','我觉得','你好呀','www','1','1','2016-08-04 12:26:13','2016-08-04 12:26:13');