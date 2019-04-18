﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     2017年9月12日14:22:51                                                         */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: 		GStartupDB
Author:         ChenQi
Description:    GStartupDB 1.0 版本修改的初始化数据
CreatedDate:    2017年9月12日14:22:32
*/
INSERT INTO `catalogue` VALUES (3, 0, '会员通道', '<!DOCTYPE html>\r\n<html>\r\n<head>\r\n	<meta charset=\"UTF-8\">\r\n	<title>会员通道</title>\r\n	<meta name=\"Keywords\" content=\"天才创业，会员通道\" />\r\n	<meta name=\"Description\" content=\"天才创业，会员通道\" />\r\n	<!--# include file=\"/include/headcommon.html\" -->\r\n	<link rel=\"stylesheet\" href=\"${basecss}/css/default/member.css\">\r\n	<script type=\"text/javascript\">\r\n		$(function(){\r\n			$(\"#${MainName}\").addClass(\"current\").siblings().removeClass(\"current\");\r\n		});\r\n	</script>\r\n</head>\r\n<body>\r\n	<!--# include file=\"/include/header.html\" -->\r\n	<div class=\"memberBox\">\r\n		<div class=\"memberitem memberitembg\">\r\n				<h1>导师团队</h1>\r\n				<p>天才创业以中国著名企业家、原格林柯尔集团创始人、顾氏循环理论发明人、2003CCTV中国经济年度人物顾雏军先生为灵魂人物，携手原格林柯尔集团、原科龙电器以及天才纵横集团三大集团高管及投资方组成了国内外独一无二的超强创业导师团。在他们的指导与带领下，你将犹如插上五彩斑斓且丰腴有力的翅膀，振翅于天地之间，遨游于云际之上，穿梭于高山之巅，俯冲于浩海之边，以全新高度、绝佳视角和玄妙体验，俯瞰这场商界的厮杀，经历这场胜者的狂欢。创业路上，伴你而行，定让你不虚此行！</p>\r\n		</div>\r\n		<div class=\"memberitem\">\r\n			<h1>天才创业辅导课程</h1>\r\n			<div class=\"courseBox\">\r\n				<img src=\"${baseimg}/imgs/default/course1.png\" alt=\"资源整合\" title=\"资源整合\" />\r\n				<img src=\"${baseimg}/imgs/default/course2.png\" alt=\"相关政策\" title=\"相关政策\" />\r\n				<img src=\"${baseimg}/imgs/default/course3.png\" alt=\"融资\" title=\"融资\" />\r\n			</div>\r\n			<p>天才创业不定期邀请创投界权威人物开设创业讲堂，结合创业全生命周期，推出创业计划书谋划、创业路演、融资谈判、上市并购等一系列创业实战课程，帮助创业者清障开路、答疑解惑。</p>\r\n			<div class=\"notice\">每期课程安排会在本栏目提前两周公布。所有超天才会员尊享7折优惠。敬请关注。</div>\r\n		</div>\r\n		<div class=\"memberitem memberitembg\">\r\n			<h1>天才创业主题活动</h1>\r\n			<p>天才创业与政府相关部门、投资人/机构、知名企业家以及创业服务机构多方主体合作，不定期推出创业沙龙、峰会、赛事、直播节目等创业主题活动，帮助创业者对接拓展、聚合资源。每期活动安排会在本栏目提前两周公布。</p>\r\n			<div class=\"notice\">所有超天才会员尊享7折优惠，活跃会员更有机会免费参与。敬请关注。</div>\r\n			<div class=\"courseBox\">\r\n				<img src=\"${baseimg}/imgs/default/activity1.png\" alt=\"\" title=\"\" />\r\n				<img src=\"${baseimg}/imgs/default/activity2.png\" alt=\"\" title=\"\" />\r\n				<img src=\"${baseimg}/imgs/default/activity3.png\" alt=\"\" title=\"\" />\r\n			</div>\r\n		</div>\r\n		<div class=\"memberitem\">\r\n			<h1>天才创业交流群</h1>\r\n			<div class=\"communicate\">\r\n				<img src=\"${baseimg}/imgs/default/qun.png\" alt=\"\" />\r\n				<p>天才创业特别创建“天才创业”订阅号和“天才创业-主群/分群”微信群，第一时间推送所有天才创业最新课程安排、活动动向、内部分享资料等，并与大家一起互动交流。欢迎加入。</p>\r\n			</div>\r\n		</div>\r\n		<a href=\"http://user.supergenius.cn/register\" class=\"join\">立即加入</a>\r\n	</div>\r\n	<div class=\"shelterBox\">\r\n		<span>天才创业实战训练营</span>\r\n		<div class=\"btns\">\r\n			<a href=\"tencent://message/?uin=2954769862&amp;Menu=yes\" class=\"online\">在线咨询</a>\r\n			<a href=\"#\" class=\"signin\">立即报名</a>\r\n		</div>\r\n		<a href=\"javascript:;\" class=\"closebtn\"><img src=\"${baseimg}/imgs/default/close.png\" alt=\"\"></a>\r\n	</div>\r\n	<script type=\"text/javascript\">\r\n		$(function () {\r\n			$(\".closebtn\").click(function () {\r\n				$(this).parent(\".shelterBox\").addClass(\"hd\");\r\n			});\r\n		});\r\n	</script>\r\n</body>\r\n</html>', 1, NULL, '2017-8-25 14:15:17', '2017-08-25 14:15:17');
