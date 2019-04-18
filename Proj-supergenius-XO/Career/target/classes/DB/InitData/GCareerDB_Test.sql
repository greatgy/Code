﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     2017年11月13日11:45:17                                               */
/*==============================================================*/

 /**
 * 内容测试数据
 * @author : ChenQi
 * @date : 2017年11月13日17:18:36
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
 * 问题表测试数据
 * @author : ChenQi
 * @date : 2017年11月13日17:09:19
 */
INSERT INTO `problem` (`uid`, `adminuid`, `useruid`, `author`, `content`, `title`, `istop`, `toptime`, `status`, `createtime`) VALUES 
 ('uid00000000000000000000000000011', '86883b04795f38d0a70b1f9004c1f989', 'uid11111111111111111111111111111', '旺财', "现在是2017年11月13日17:13:22", "这是一条问题测试吗？", 1, '2017-11-13 17:14:13', 1,'2017-11-13 17:14:22');

 /**
 * 回答测试数据
 * @author : ChenQi
 * @date : 2017年11月13日17:40:36
 */
INSERT INTO `answer` (`uid`, `fromuid`, `touid`, `fromuseruid`, `fromuseroid`, `fromusername`, `touseruid`, `touseroid`, `tousername`, `content`, `channel`, `data`, `type`, `adminuid`, `status`, `createtime` ) VALUES 
 ('uid00000000000000000000000000000', "uid11111111111111111111111111111", "uid00000000000000000000000000001", "uid00000000000000000000000000000", 10, "张一", "55cb3166ac634cb7b4616c4392fc9bfe", 13, "张细汉", "我是一条评论", 1, "", 0, "86883b04795f38d0a70b1f9004c1f989", 0, '2017-8-23 15:16:54');
