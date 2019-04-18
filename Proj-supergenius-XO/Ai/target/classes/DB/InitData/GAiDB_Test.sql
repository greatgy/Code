 ﻿﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     2017年9月19日10:03:11                                               */
/*==============================================================*/

 /**
 * 评论测试数据
 * @author : xuzhixiang
 * @date : 2017年9月19日10:02:37
 */
INSERT INTO `comments` (`uid`, `fromuid`, `touid`, `fromuseruid`, `fromuseroid`, `fromusername`, `touseruid`, `touseroid`, `tousername`, `content`, `channel`, `data`, `type`, `adminuid`, `status`, `createtime` ) VALUES 
 ('uid00000000000000000000000000000', "uid11111111111111111111111111111", "uid00000000000000000000000000001", "uid00000000000000000000000000000", 10, "张一", "55cb3166ac634cb7b4616c4392fc9bfe", 13, "张细汉", "我是一条评论", 1, "", 0, "86883b04795f38d0a70b1f9004c1f989", 0, '2017-8-23 15:16:54');

 /**
 * 内容测试数据
 * @author : xuzhxiang
 * @date : 2017年9月19日10:02:44
 */
INSERT INTO `content` (`uid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `adminuid`, `status`, `createtime`) VALUES 
 ('uid00000000000000000000000000000', "广告", 0, "", "广告简介", "我是一个萌萌哒广告", "http://www.supergenius.cn/", "86883b04795f38d0a70b1f9004c1f989", 0,'2017-8-23 15:17:02');

 /**
 * Author:ChenQi
 * news表的测试数据
 */
  INSERT INTO `news` (`uid`, `fromuid`, `touid`, `title`, `content`, `href`, `type`, `isread`, `status`, `createtime`, `updatetime`) VALUES
  ('uid22222222222222222222222222222', 'uid11111111111111111111111111111', 'uid00000000000000000000000000000', '我觉得', '你好呀', 'www', '2', '1', '1', '2017-9-19 10:23:25', '2017-9-19 10:23:33');
  
  /**
 * 文章表测试数据
 * @author : 杨光
 * @date : 2017年9月19日16:21:19
 */
insert into `article` (`oid`, `uid`, `cid`, `adminuid`, `useruid`, `username`, `author`, `content`, `title`, `origin`, `summary` ,`keywords`, `contact`, `status`, `createtime`, `updatetime`) values
('10','uid11111111111111111111111111111','11', '', '' ,'莉莉','匿名', '测试内容', '测试', '超天才', '没有摘要', '关键字', '18519744454', '1','2017-08-20 14:15:17','2017-08-20 14:15:17');
  
  