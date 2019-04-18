﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     20:23 2014/1/2                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GCareerDB
Author:		ChenQi
Description:	GCareerDB初始化数据
CreatedDate:	2017年11月13日11:44:34
ModifyDate:	2017年11月13日11:44:43
*/
INSERT INTO `catalogue` VALUES (18, '职场测试内容', '<!DOCTYPE html>\r\n<html>\r\n<head>\r\n	<meta charset=\"UTF-8\">\r\n	<title>职场测试</title>\r\n	<meta name=\"Keywords\" content=\"职场测试\" />\r\n	<meta name=\"Description\" content=\"职场测试\" />\r\n	<!--# include file=\"/include/headcommon.html\" -->\r\n	<link rel=\"stylesheet\" href=\"${basecss}/css/default/index.css\">\r\n</head>\r\n<body>\r\n	<h2 class=\"testTitle\">解剖自己  成就远方</h2>\r\n	<div class=\"testBoxmiddle\">\r\n		<p>1111修改的内容也许你还是一名胸怀大志的在校大学生，亦或许你已经在纷繁复杂的社会中跌跌撞撞，牛马风尘。你能否还记得心中当初的坚守和志向？不忘初心，方得始终。了解自己，才能不断成长和进步，找到自己职业发展的正确方向！天才职场通过专业的性格和能力等维度的深度挖掘，帮助每个人寻找到自己职业生涯的理性方向！</p>\r\n		<img src=\"${baseimg}/imgs/default/testimg2.png\" class=\"img-responsive\" alt=\"\" title=\"\" />\r\n		<p>本测试一共30道单选题，每题共设六个选项，行你觉得两个选项都是自己匹配的，或者选项中没有自己觉得匹配的，那么就选择一个更贴切的，单个选项并不会对测试结果造成很大偏差。本测试基于个人性格特征，领导能力，沟通能力，社交能力，研究能力，创造力等维度对测试者进行深度挖掘。选项没有优劣之分，任何一种倾向都有着其背后的逻辑，从而反映出更适合的职业类型。请遵循最真实的内心世界来答题！</p>\r\n		<a href=\"${base}/question\" class=\"startbtn\">开始测试</a>\r\n	</div>\r\n</body>\r\n</html>', 2, 1, 'ii683b04795f48d0a70b1f9004c1foii', '2017-11-13 16:07:20', '2018-1-3 03:18:18');

insert into `content` (`uid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('ce920b36bd3b4c6faab56a74d6fa6047','天才职场banner','4','{\"1\":{\"title\":\"banner1\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"},\"2\":{\"title\":\"banner2\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114418406b25_300_200.png\"},\"3\":{\"title\":\"banner3\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114429689xxh_300_200.png\"},\"4\":{\"title\":\"banner4\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/1802071145150531vw_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');