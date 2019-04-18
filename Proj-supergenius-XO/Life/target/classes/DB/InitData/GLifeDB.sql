﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     19:04 2013/1/10                                                   */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GLifeDB
Author:		ChenQi
Description:	GLifeDB初始化数据
CreatedDate:	2018年5月7日10:49:45
ModifyDate:	2018年5月7日10:50:01
*/
 /**
 * 模块表初始化数据
 * @author : ChenQi
 * @date : 2018年5月11日17:12:00
 */
INSERT INTO `catalogue` (`cid`, `pcid`, `name`, `type`, `status`, `createtime`, `updatetime`) VALUES 
 (1, 0, '首页', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (2, 0, '人生定位', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (4, 0, '疯狂理想', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (8, 0, '梦之初心', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (16, 0, '放飞梦想', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (32, 0, '追梦时光', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (64, 0, '人生守望', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (128, 0, '人生问答', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (256, 0, '我的偶像', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (512, 0, '会员通道', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (1024, 2, '人生宗旨', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (2048, 2, '人生定位', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (4096, 2, '人生路径', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (8192, 8, '家长责任', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (16384, 8, '课程推荐', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (32768, 8, '效果检验', 3, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (65536, 8, '我的理想', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (131072, 8, '留学指南', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (262144, 8, '交流对话', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (524288, 16, '别出心裁', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (1048576, 16, '思维拓展', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (2097152, 16, '课程见解', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (4194304, 16, '资料交流', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (8388608, 16, '专业匹配', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (16777216, 16, '大学优选', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (33554432, 16, '留学风向', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (67108864, 32, '火眼金睛', 2, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (134217728, 32, '识别骗局', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (268435456, 32, '水煮历史', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (536870912, 32, '去伪存真', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (1073741824, 32, '人生设计', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (2147483648, 32, '我的舞台', 3, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (4294967296, 32, '留学天地', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (8589934592, 64, '梦想成真', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (17179869184, 64, '守望初心', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (34359738368, 64, '理想变迁', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (68719476736, 64, '携手前行', 1, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (137438953472, 64, '人生推荐', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (274877906944, 64, '走万里路', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (549755813888, 256, '我身边的偶像', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (1099511627776, 256, '我崇拜的偶像', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05'),
 (2199023255552, 256, '我幻想的偶像', 0, 1, '2018-5-9 19:15:59', '2018-5-9 19:16:05');
 /**
 * 内容表初始化数据
 * @author : ChenQi
 * @date : 2018年5月11日17:12:20
 */ 
/*初始化banner*/

insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000001','1','首页专题banner','1','{\"1\":{\"title\":\"首页专题banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000002','1','首页banner','1','{\"1\":{\"title\":\"首页banner1\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000003','1','首页banner','1','{\"1\":{\"title\":\"首页banner2\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114418406b25_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000004','1','首页banner','1','{\"1\":{\"title\":\"首页banner3\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114429689xxh_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000005','16384','课程推荐banner','1','{\"1\":{\"title\":\"课程推荐banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000006','16777216','大学优选banner','1','{\"1\":{\"title\":\"大学优选banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000007','32768','效果检验banner','1','{\"1\":{\"title\":\"效果检验banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000008','8388608','专业匹配banner','1','{\"1\":{\"title\":\"专业匹配banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000009','4194304','资料交流banner','1','{\"1\":{\"title\":\"资料交流banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000010','128','人生问答banner','1','{\"1\":{\"title\":\"人生问答banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
insert into `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidbanner00000000000000000000011','1073741824','人生设计banner','1','{\"1\":{\"title\":\"人生设计banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}',NULL,NULL,NULL,'1',NULL,'2018-01-30 12:19:30','2018-02-07 06:21:58');
INSERT INTO `content` (`uid`,`cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) VALUES('uidbanner00000000000000000000012','8192', '天才人生网敬告初中生(家长责任)banner', '1', '{\"1\":{\"title\":\"天才人生网敬告初中生(家长责任)banner\",\"url\":\"\",\"content\":\"/imgs/webdata/enterpriser/180207114401827gac_300_200.png\"}}', NULL, NULL, NULL, 1, NULL, '2018-1-30 12:19:30', '2018-2-5 19:08:39');
/*初始化内容*/
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000001','1024','人生宗旨','2','','','<h2 style="text-align: center;">顾雏军<span>“评说”</span>人生</h2>\r\n			<p>人们总认为人生是一个大题目，很难说透，甚至说清楚也不容易！超天才网新创建了一个天才人生网的子网站，邀请我为天才人生网确定一个宗旨，作为天才人生的办网方向。我认为所谓人生宗旨，或者说人生格局，也就是两点，一是立志，二是成才。立志要早，才能坚定成才的决心，而成才却是千辛万苦的历练和拼搏。成才也可以分为四个阶段:</p>\r\n			<ul class=\"aimList\">\r\n				<li>\r\n					<span class=\"labelicon\">1</span>\r\n					初中阶段，应培养自学能力；\r\n				</li>\r\n				<li>\r\n					<span class=\"labelicon\">2</span>\r\n					高中阶段，应培养思考能力；\r\n				</li>\r\n				<li>\r\n					<span class=\"labelicon\">3</span>\r\n					大学及研究生阶段，应培养洞察力；\r\n				</li>\r\n				<li>\r\n					<span class=\"labelicon\">4</span>\r\n					工作之后，要守望。\r\n				</li>\r\n			</ul>\r\n			<p>这四个阶段说起来简单，但做起来都不容易。孟子曰：天将降大任于斯人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。曹操云：对酒当歌，人生几何。可见从古到今，很多名人甚至伟人都说过人生，视角和论点也是千奇百态。超天才网显然是要办一个严肃的关于人生的话题网站，根据我多年的思考，我认为天才人生网的办网宗旨应确定为：培养尽早立志、且有自学能力、能独立思考、具备洞察力和守望特质的人才。</p>\r\n			<div class=\"aimItem aimItem1\">\r\n				<img src=\"${baseimg}/imgs/default/aim1.png\" class=\"right\" alt=\"\" />\r\n				<p>天才人生网认为在<span class=\"junior\">初中阶段</span>的人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术孟子曰：天将降大任于斯人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。曹操云：对酒当歌，人生几何。可见从古到今，很多名人甚至伟人都说过人生，视角和论点也是千奇百态。孟子曰：天将降大任于斯人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。曹操云：对酒当歌，人生几何。可见从古到今，很多名人甚至伟人都说过人生，视角和论点也是千奇百态。</p>\r\n			</div>\r\n			<div class=\"aimItem aimItem2\">\r\n				<img src=\"${baseimg}/imgs/default/aim2.png\" class=\"left\" alt=\"\" />\r\n				<p>天才人生网认为在<span class=\"senior\">高中阶段</span>的人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力</p>\r\n			</div>\r\n			<div class=\"aimItem aimItem1\">\r\n				<img src=\"${baseimg}/imgs/default/aim3.png\" class=\"right\" alt=\"\" />\r\n				<p>天才人生网认为在<span class=\"university\">大学</span>的人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力</p>\r\n				<p>如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您如果您在初中学会了自学，高中培养了强大的思考能力，那么大学何难呢?毫无疑问您的大学生活将没有实质性困难，注定您</p>\r\n			</div>\r\n			<div class=\"aimItem aimItem2\">\r\n				<img src=\"${baseimg}/imgs/default/aim4.png\" class=\"left\" alt=\"\" />\r\n				<p>天才人生网认为在<span class=\"work\">就业阶段</span>的人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力人生目标应确定为培养自学能力，能够拥有科学、技术、文化、艺术的自学能力</p>\r\n				<p>现在埃隆．马斯克又在用天文望远镜聚焦理想，但愿他也能成功。绝大多数人年长后能用望远镜聚焦理想就足够了。远望而不散、不忘初心叫守，聚焦理想叫望，把理想聚焦成由远及近的一条线（那怕不是直线）就叫做守望。</p>\r\n				<p>不忘初心叫守，聚焦理想叫望，把理想聚焦成由远及近的一条线（那怕不是直线）就叫做守望。</p>\r\n				<img src=\"${baseimg}/imgs/default/aim5.png\" class=\"right inlineimg\" alt=\"\" />\r\n				<p>所以，我们定义那些用天文望远镜聚焦理想且碰巧又实现了这个理想的人就叫做伟人 ，比如乔布斯、爱因斯坦和罗斯福总统。而我们定义那些用天文望远镜聚焦理想且碰巧又实现了这个理想的人就叫做伟人 ，比如乔布斯、爱因斯坦和罗斯福总统。而</p>\r\n				<p>还有一类人，他们取得一个成绩或成就之后，就把望远镜拿到前头来再向前聚焦下一 个理想，这样一步一步取得成功，这样的人还有一类人，他们取得一个成绩或成就之后，就把望远镜拿到前头来再向前聚焦下一 个理想，这样一步一步取得成功，这样的人他们取得一个成绩或成就之后，就把望远镜拿到前头来再向前聚焦下一 个理想，这样一步一步取得成功，这样的人还有一类人，他们取得一个成绩或成就之后，就把望远镜拿到前头来再向前聚焦下一 个理想，这样一步一步取得成功，这样的人</p>\r\n			</div>\r\n			<p>现在回过头来总结一下：人生最重要的是在初中学会自学，而且尽可能把自学能力发挥到极致，其标志就是您自认为对您自学的那现在回过头来总结一下：人生最重要的是在初中学会自学，而且尽可能把自学能力发挥到极致，其标志就是您自认为对您自学的现在回过头来总结一下：人生最重要的是在初中学会自学，而且尽可能把自学能力发挥到极致，其标志就是您自认为对您自学的现在回过头来总结一下：人生最重要的是在初中学会自学，而且尽可能把自学能力发挥到极致，其标志就是您自认为对您自学的</p>\r\n			<p>而守望是一种独立于能力之外的成功人士必须具有的特质，会自学会思考甚至具有较强洞察力，并不一定具有守望的特质。守而守望是一种独立于能力之外的成功人士必须具有的特质，会自学会思考甚至具有较强洞察力，并不一定具有守望的特质。守而守望是一种独立于能力之外的成功人士必须具有的特质，会自学会思考甚至具有较强洞察力，并不一定具有守望的特质。</p>\r\n			<div class=\"aimItem1\">\r\n				<img src=\"${baseimg}/imgs/default/aim6.png\" class=\"right\" alt=\"\" />\r\n				<p class=\"theorem\">最后我再说一说人生最重要宗旨——立志。立志直接关系到一个人的人生格局的大小，立志 要早，立志越早人生的格局就越大</p>\r\n				<p class=\"theorem\">最后我再说一说人生最重要宗旨——立志。立志直接关系到一个人的人生格局的大小，立志 要早，立志越早人生的格局就越大</p>\r\n				<p>该定理的注释：初中阶段是确立人生格局的最早阶段，也是最小的年龄，如果人一生的人生 格局能在初中阶段确立，或者说一个该定理的注释：初中阶段是确立人生格局的最早阶段，也是最小的年龄，如果人一生的人生 格局能在初中阶段确立，或者说一个该定理的注释：初中阶段是确立人生格局的最早阶段，也是最小的年龄，如果人一生的人生 格局能在初中阶段确立，或者说一个该定理的注释：初中阶段是确立人生格局的最早阶段，也是最小的年龄，如果人一生的人生 格局能在初中阶段确立，或者说一个</p>\r\n				<p>项梁当年带项羽观看秦始皇南巡车队时，项羽看到秦始皇宏伟的车队仪仗，冒出一句：“我 能代替他”，项梁马上捂住项羽的项梁当年带项羽观看秦始皇南巡车队时，项羽看到秦始皇宏伟的车队仪仗，冒出一句：“我 能代替他”，项梁马上捂住项羽项梁当年带项羽观看秦始皇南巡车队时，项羽看到秦始皇宏伟的车队仪仗，冒出一句：“我 能代替他”，项梁马上捂住项羽</p>\r\n				\r\n				<p>再看看张良的格局就比项羽小了不少，年青时代的张良做了一把大铁锥并且找了一个有傻大力气的傻大个一起砸秦始皇的南巡马</p>\r\n				<p>再看看少年项羽，项羽自从立志要代替秦始皇之后，少年项羽就不学武功了，要学万人敌，于是项梁就教他兵法。当然项羽是不</p>\r\n				<p>写《史记》的司马迁也是这么认为的，他在记录《史记》人物时，把项羽列在了最高级的“本纪”一等（《项羽本纪》），却把写《史记》的司马迁也是这么认为的，他在记录《史记》人物时，把项羽列在了最高级的“本纪”一等（《项羽本纪》），却把</p>\r\n			</div>\r\n			<p>顺便说一下韩信，此公少不立志，远不像项羽和张良从小就立志推翻秦王朝那样，韩信无勇无胆以至于受人袴下之辱，贫困交加顺便说一下韩信，此公少不立志，远不像项羽和张良从小就立志推翻秦王朝那样，韩信无勇无胆以至于受人袴下之辱，贫困交加顺便说一下韩信，此公少不立志，远不像项羽和张良从小就立志推翻秦王朝那样，韩信无勇无胆以至于受人袴下之辱，贫困交加顺便说一下韩信，此公少不立志，远不像项羽和张良从小就立志推翻秦王朝那样，韩信无勇无胆以至于受人袴下之辱，贫困交加顺便说一下韩信，此公少不立志，远不像项羽和张良从小就立志推翻秦朝那样，韩信无勇无胆以至于受人袴下之辱，贫困交加</p>\r\n			<p>这样的故事还有很多。总的说来，立志要早，而且还要大声喊出来，宣誓于江湖和朝野，不给自己留后路的人才能成为伟人，青这样的故事还有很多。总的说来，立志要早，而且还要大声喊出来，宣誓于江湖和朝野，不给自己留后路的人才能成为伟人这样的故事还有很多。总的说来，立志要早，而且还要大声喊出来，宣誓于江湖和朝野，不给自己留后路的人才能成为伟人这样的故事还有很多。总的说来，立志要早，而且还要大声喊出来，宣誓于江湖和朝野，不给自己留后路的人才能成为伟人这样的故事还有很多。总的说来，立志要早，而且还要大声喊出来，宣誓于江湖和朝野，不给自己留后路的人才能成为伟人</p>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-22 10:23:31');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000002','2048','人生定位','2','','','<h2 style="text-align: center;">人生定位</h2>\r\n			<p>一个人从嗷嗷待哺的婴儿阶段到少年的学习阶段，从步入社会的青年阶段到社会与家庭支柱的壮年阶段，最后过渡到中年以及退一个人从嗷嗷待哺的婴儿阶段到少年的学习阶段，从步入社会的青年阶段到社会与家庭支柱的壮年阶段，最后过渡到中年以及退</p>\r\n			<img src=\"${baseimg}/imgs/default/life1.png\" alt=\"\" />\r\n			<p>顾雏军先生提出了实现人生理想的方法，从立志到自学，从思考到洞察，最后到守望。但在理想的基础上，我们仍需清楚自己的从立志到自学，从思考到洞察，最后到守望。但在理想的基础上，我们仍需清楚自己的</p>\r\n			<img src=\"${baseimg}/imgs/default/life2.png\" alt=\"\" />\r\n			<p>立志并不是盲目的说出理想，而是在理解自身的性格和能力的基础上，明确自己能够在社会中所承担的角色，并明确自己未来想。。。。。。在这里我们独创性的将社会角色分为五种：<span class=\"special\">开创者、领导者、执行者、跟随者和自由者</span></p>\r\n			<p>用简单的目标和勤奋两个维度来刻画这五种人生角色。比较特别的是自由者，因其随性而为，所以可能有目标，也可能没 有；可</p>\r\n			<img src=\"${baseimg}/imgs/default/life3.png\" alt=\"\">\r\n			<p>每一种角色都具有自身独特的性格特征和能力要求，并且不是一尘不变的。一方面随着个人的努力，自我能力的提升和性 格的控</p>\r\n			<img src=\"${baseimg}/imgs/default/life4.png\" alt=\"\">\r\n			<h3 class=\"littleTag\"><span class=\"labelicon\">1</span>人生坐标（立志要早）</h3>\r\n			<p>朱熹曾言：“为学须先立志。志既立，则学问可次第着力。立志不定，终不济事。”这是说无论是学习还是做事都必须先确定志朱熹曾言：“为学须先立志。志既立，则学问可次第着力。立志不定，终不济事。”这是说无论是学习还是做事都必须先确定志</p>\r\n			<p>朱熹曾言：“为学须先立志。志既立，则学问可次第着力。立志不定，终不济事。”这是说无论是学习还是做事都必须先确定志朱熹曾言：“为学须先立志。志既立，则学问可次第着力。立志不定，终不济事。”这是说无论是学习还是做事都必须先确定志</p>\r\n			<img src=\"${baseimg}/imgs/default/life5.png\" alt=\"\" />\r\n			<p>18岁-24岁，即大学本科和研究生阶段。这一阶段是对技术能力、人际交往能力和领导能力的培养期，也就是进入社会前的准</p>\r\n			<h3 class=\"littleTag\"><span class=\"labelicon\">2</span>  人生追求（不同的心理需求）</h3>\r\n			<p class=\"special\">开创者追求思想境界，领导者追求成果，执行者追求被需要感，跟随者追求安逸，自由者追求自我。</p>\r\n			<p>“为学须先立志。志既立，则学问可次第着力。立志不定，终不济事。”这是说无论是学习还是做事都必须先确定志。</p>\r\n			<div class=\"middlebox\">\r\n				<img src=\"${baseimg}/imgs/default/life6.png\" alt=\"\" />\r\n				<span>开创者通过知识的积累和思考分析实现不断的进步，追求思想境界；</span>\r\n			</div>\r\n			<div class=\"middlebox\">\r\n				<img src=\"${baseimg}/imgs/default/life7.png\" alt=\"\" />\r\n				<span>领导者通过自身的实力实现对人和事的管理和引领，追求权力和成果；</span> \r\n			</div>\r\n			<div class=\"middlebox\">\r\n				<img src=\"${baseimg}/imgs/default/life8.png\" alt=\"\" />\r\n				<span>执行者通过尽职和严谨的作风实现他人的计划，追求被需要感；</span>\r\n			</div>\r\n			<div class=\"middlebox\">\r\n				<img src=\"${baseimg}/imgs/default/life9.png\" alt=\"\" />\r\n				<span>自由者通过天马行空的想法实现自我。</span> \r\n			</div>\r\n			<h3 class=\"littleTag\"><span class=\"labelicon\">3</span>   遇到问题（不同的处事方式）</h3>\r\n			<p><em>开创者发掘问题，领导者配置资源解决问题，执行者根据指出的方向自主努力解决问题，跟随者按照具体安排完成任务，自</em></p>\r\n			<p>当遇到问题时，五种角色的反应是不同的。</p>\r\n			<p>开创者善于思考和分析，他会发掘问题，并质疑和挑战现有的答案，但往往只想不做。</p>\r\n			<p>领导者对自己的能力非常有信心，喜欢挑战，遇到问题会迎难而上，即使自身能力不足，也会配置可用的资源来解决问题。</p>\r\n			<img src=\"${baseimg}/imgs/default/life10.png\" alt=\"\" />\r\n			<p>执行者遇到问题时会比较焦虑，不愿意面对问题，转而寻求他人的意见，乐意按照开创者和领导者指出的方向来努力解决 问题。</p>\r\n			<p>跟随者一般不会自己去思考问题，跟随大家的行动而行动，对于问题是否解决也并不感兴趣，会按照上级的安排来完成任 务。</p>\r\n			<p>自由者会对感兴趣的问题进行无边际的思考，解决或不解决随心情而定；对不感性趣的问题他们会选择置之不理。</p>\r\n			<h3 class=\"littleTag\"><span class=\"labelicon\">4</span>    人际关系（不同的交往方式）</h3>\r\n			<p>开创者喜欢与同样有思想的人相处，但无生死之交；领导者喜欢成为团队的领袖，能团结人为己所用，更注重利益的得失 而不会不</p>\r\n			<img src=\"${baseimg}/imgs/default/life11.png\" alt=\"\" />\r\n			<p>在人际交往中，五种角色因性格和习惯而选择与不同的人或不同的方式进行接触。</p>\r\n			<p>开创者由于对事对人的高标准和天马行空的新想法，喜欢能与他们产生思想共鸣的人相处，但相处中理性大于感性，难有 生死之</p>\r\n			<p>领导者喜欢成为团体的话语人，喜欢团结对己有用的人，更注重付出和回报，在交往中保持对己有利的原则。执行者对他 人的需</p>\r\n			<p>执行者对他人的需求很敏锐，愿意与人交往，喜欢为朋友付出，并视朋友为自己最大的资产。</p>\r\n			<p>跟随者习惯与人为善，不愿引起冲突得罪他人，并在交往中更为看重对己有益的人。</p>\r\n			<p>自由者很不喜欢被束缚、被控制，不善于妥协和迎合他人，在交往中更愿随心所欲，以感情为主导与人相处。</p>\r\n			<h3 class=\"littleTag\"><span class=\"labelicon\">5</span>必备技能</h3>\r\n			<img src=\"${baseimg}/imgs/default/life12.png\" alt=\"\" />\r\n			<p>在技能方面：</p>\r\n			<ul class=\"skillList\">\r\n				<li>\r\n					<span class=\"numicon\">1</span>\r\n					开创者必须有洞察力和突破能力；\r\n				</li>\r\n				<li>\r\n					<span class=\"numicon\">2</span>\r\n					领导者必须有决断力和统筹能力；\r\n				</li>\r\n				<li>\r\n					<span class=\"numicon\">3</span>\r\n					执行者必须有亲和力和执行能力；\r\n				</li>\r\n				<li>\r\n					<span class=\"numicon\">4</span>\r\n					跟随者必须有适应力和忍耐能力；\r\n				</li>\r\n			</ul>\r\n			<p>其中亲和力、适应力和忍耐能力、想象能力是性格方面和天赋的塑造，而洞察力、决断力和统筹能力、执行能力</p>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-21 10:39:58');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000003','4096','人生路径','2','','','<h2 style="text-align: center;">人生路径</h2>\r\n			<p>根据“人生定位”中的描述，就可以大致确定自己的社会角色。每一种角色都有其存在的必要性，我们不能说跟随者就不好，因根据“人生定位”中的描述，就可以大致确定自己的社会角色。每一种角色都有其存在的必要性，我们不能说跟随者就不好，根据“人生定位”中的描述，就可以大致确定自己的社会角色。每一种角色都有其存在的必要性，我们不能说跟随者就不好，因根据“人生定位”中的描述，就可以大致确定自己的社会角色。每一种角色都有其存在的必要性，我们不能说跟随者就不好</p>\r\n			<div class=\"roadItem roadItem1\">\r\n				<h3 class=\"littleTag\" style="margin-bottom: 10px;"><span class=\"labelicon\">1</span>跟随者与执行者</h3>\r\n				<div class=\"roadp\">\r\n					<p>跟随者的特点是安于现状，懒惰不知进取。他们与执行者的共通点是可以 较为融洽的与他人交往，是比较好的沟通者。所以当跟随跟随者的特点是安于现状，懒惰不知进取。他们与执行者的共通点是可以较为融洽的与他人交往，是比较好的沟通者。所以当跟随</p>\r\n					<p>跟随者的特点是安于现状，懒惰不知进取。他们与执行者的共通点是可以较为融洽的与他人交往，是比较好的沟通者。所以当跟随</p>\r\n				</div>\r\n				<img src=\"${baseimg}/imgs/default/road1.png\" alt=\"\" class=\"positionImg\" />\r\n			</div>\r\n			<div class=\"roadItem roadItem2\">\r\n				<h3 class=\"littleTag\"><span class=\"labelicon\">2</span>执行者与领导者</h3>\r\n				<img src=\"${baseimg}/imgs/default/road2.png\" alt=\"\" class=\"positionImg\" />\r\n				<div class=\"roadp\">\r\n					<p>执行者的特点是害怕做决策，优柔寡断，并惧怕权威，胆小谨慎。他们与领导者的共通点是都具有较强的行动力。所以当执行者执行者的特点是害怕做决策，优柔寡断，并惧怕权威，胆小谨慎。他们与领导者的共通点是都具有较强的行动力。所以当执行者</p>\r\n					<p>跟随者的特点是安于现状，懒惰不知进取。他们与执行者的共通点是可以 较为融洽的与他人交往，是比较好的沟通者。所以当跟随</p>\r\n				</div>\r\n			</div>\r\n			<div class=\"roadItem roadItem1\">\r\n				<h3 class=\"littleTag\"><span class=\"labelicon\">3</span>领导者和开创者</h3>\r\n				<div class=\"roadp\">\r\n					<p>开创者是需要在青少年时培养其大局观，洞察力和思考能力才可能成为的， 所以领导者不可能向开创者转换。但开创者与领导开创者是需要在青少年时培养其大局观，洞察力和思考能力才可能成为的，所以领导者不可能向开创者转换。但开创者与领导</p>\r\n				</div>\r\n				<img src=\"${baseimg}/imgs/default/road3.png\" alt=\"\" class=\"positionImg\" />\r\n			</div>\r\n			<div class=\"roadItem roadItem2\">\r\n				<h3 class=\"littleTag\"><span class=\"labelicon\">4</span>开创者和自由者</h3>\r\n				<img src=\"${baseimg}/imgs/default/road4.png\" alt=\"\" class=\"positionImg\" />\r\n				<div class=\"roadp\">\r\n					<p>自由者的特点是自我，这也造成他们对不感兴趣的事情漠不关心，并缺乏责 任心。他们与开创者的共通点是创造力。自由者有一所以当跟随     跟随者的特点是安于现状，懒惰不知进取。他们与执行者的共通点是可以 较为融洽的与他人交往，是比较好的沟通者。所以当跟随</p>\r\n					<p>跟随者的特点是安于现状，懒惰不知进取。他们与执行者的共通点是可以 较为融洽的与他人交往，是比较好的沟通者。所以当跟随</p>\r\n				</div>\r\n			</div>\r\n			<div class=\"roadItem roadItem1\">\r\n				<h3 class=\"littleTag\"><span class=\"labelicon\">5</span>开创者与执行者</h3>\r\n				<div class=\"roadp\">\r\n					<p>开创者的最突出特点是其优秀的洞察力和思考能力，当其对自己失去自信时， 其可能退化为执行者。</p>\r\n				</div>\r\n				<img src=\"${baseimg}/imgs/default/road5.png\" alt=\"\" class=\"positionImg\" />\r\n			</div>\r\n			<p>综上所述，每个人由于性格不同，为人处事的方式方法也不同，所以所担当的社会角色也不同，但不代表各角色之间不能进行转</p>\r\n			<img src=\"/imgs/default/road6.png\" alt=\"\" />\r\n			<p>培养执行者并不难，入职几年的优质员工都是很好的执行者，可以说企业这个大机器可以很好的规范员工的行为，培养出 好的执培养执行者并不难，入职几年的优质员工都是很好的执行者，可以说企业这个大机器可以很好的规范员工的行为，培养出 好的执培养执行者并不难，入职几年的优质员工都是很好的执行者，可以说企业这个大机器可以很好的规范员工的行为，培养出 好的执培养执行者并不难，入职几年的优质员工都是很好的执行者，可以说企业这个大机器可以很好的规范员工的行为，培养出 好的执</p>\r\n			<p>所以人生可能的发展路径有（以17岁为初期人生定位）： </p>\r\n			<img src=\"${baseimg}/imgs/default/road7.png\" alt=\"\" />\r\n			<p>根据我们给出的培养能力的方法，即立志—自学—思考—洞察—守望，我们可以获得实现人生不同角色的技能。在此基础 上，在</p>\r\n			<p>根据我们给出的培养能力的方法，即立志—自学—思考—洞察—守望，我们可以获得实现人生不同角色的技能。在此基础 上，在根据我们给出的培养能力的方法，即立志—自学—思考—洞察—守望，我们可以获得实现人生不同角色的技能。</p>\r\n			<p>找准人生定位目标，将是人生成功第一步。</p>\r\n			<p>除跟随者外，执行者、领导者、开创者和自由者都需要相应的培养，我们提供的最切实可行的方法是在初中阶段学会自学，在高</p>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-22 10:24:47');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000004','8192','家长责任','2','','','<div class=\"duty\">\r\n				<h3>家长责任:</h3>\r\n				<p>作为家长，除了为子女提供良好的生长环境，让他们拥有健康的生活态度外，最期待的就是他们可以有所成就。天才人生网认为家长的最重要责任就是让孩子学会自学，并且要尽早学会自学。</p>\r\n				<p>当您的儿子和女儿学会自学后，他（她）们今后所遇到的所有困难都可以尝试通过自学去解决。而解决困难的甜头会使他（她）们更加如饥似渴的学会更多知识，进而解决更多的困难，最终赢得人生的辉煌。即使有些困难无法通过当时的自学解决，但在尝试的过程中也会学到更多的东西。例如发现自己性格的缺陷、知识结构的偏向、能力与喜好的抵触、现实与理想的矛盾，这就是成长过程中的烦恼。挫折总是会帮助他（她）们成长，而他（她）们拥有的自学能力将会帮助其迅速从失败的阴影中走出来。天才人生网可以宽慰家长的是：拥有自学能力的子女是不会陷在失败之中不可自拔的，因为他（她）们会用自学新东西来疗伤，在自学新东西的过程中找到自身的不足和缺陷，同时找到新的方向，甚至找到克服困难的新方法。如此可见就再也没什么可以让家长们担忧了。在这个意义上，天才人生网认为帮助孩子掌握自学的本领是家长的最后一个应尽的责任。</p>\r\n				<p>小学是打基础的阶段，知识相对浅显，学生们可以通过背诵和老师的讲解来理解和消化知识，并学会阅读。到了高中，学生主要任务是应对高考，所以并没有相对自由的时间段来培养自学能力。所以我们认为家长应该帮助孩子在初中拥有自学能力。拥有这种能力的学生也更容易在高中甚至在高考中脱颖而出。</p>\r\n				<p>我们鼓励家长自行帮助孩子完成自学能力的培养过程，我们不提倡将此任务委托给他人，如培训机构或教师，家长的参与会让孩子拥有更为明确的目标和更为积极的动力。即使成为我们的会员，我们也仅是以辅助者的角色帮助家长和孩子完善自学的过程。</p>\r\n				<p>当然我们为家长提供了切实可行的学习方法和步骤，可以有效的帮助家长和孩子掌握自学的秘诀。家长可以按照本网站提供的方法指导孩子自学一门课程，并按照我们的方法检验自学的成果。我们相信，家长直接帮助孩子与交给第三方监管的结果是不同的。我们希望所有的家长可以亲自参与到孩子的自学过程中，尽量不依靠外部的力量，但当家长在时间或能力上无法胜任这一任务时，请您选择非培训类的外部机构帮助您完成这一任务。我们的宗旨是让孩子学会自学，而不是简单的学会某一些知识。自学是一种能力，一旦掌握就可以应用在任何领域，受益终生。</p>\r\n				<p>可以说，“授人以鱼不如授人以渔”，自学能力是家长送给学生的最珍贵的礼物，学会自学的孩子不再需要家长扶着走路。他们可以在更广阔的世界里遨游。孩子拥有了自学能力，家长也就尽到了教育责任。</p>\r\n				<p>超天才网的目标是：培养出十个诺贝尔奖的获得者和一千个亿万富豪。天才人生网如果能够帮助30%的家长培养出孩子们的自学能力，那么这30%掌握了自学能力的孩子就一定能走出十个诺贝尔奖的获得者和一千个亿万富豪。</p>\r\n			</div>\r\n			<a href=\"#\" class=\"learnSelf\">如何完成自学</a>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-21 15:12:54');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000005','8192','顾雏军自学五法则(家长责任)','2','','','<div class=\"learnBox\">\r\n				<h2>顾雏军自学五法则：</h2>\r\n				<ul class=\"learnRules\">\r\n					<li>1.选一本作者名气很大的，且习题很多的著作或教科书作为自学的教材。</li>\r\n					<li>2.学完一章紧接着将本章复习一遍。</li>\r\n					<li>3.每一章学习完要紧接着做后面的习题，第一遍做不出来的，第二遍复习后必须做出来。</li>\r\n					<li>4.必须独立证明所有的等号。</li>\r\n					<li>5.紧接着选一本该领域名气最大的书再学一遍，如有可能选一本难度最高的习题集做一遍。</li>\r\n				</ul>\r\n			</div>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-21 15:12:36');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000007','134217728','识别骗局','2','','','<div class=\"newtopimg\" style=\"background:url(${baseimg}/imgs/default/newtopbg.png) no-repeat center center;background-size: 100% 100%;\">\r\n	<div class=\"tipWord\">\r\n		<p>目前社会上骗局日益更新，大学生们屡屡上当。</p>\r\n		<p>上传社会上的各种骗局，让大家识别社会的风险。</p>\r\n	</div>\r\n</div>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-29 14:28:47');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000008','512','会员通道','2','','','<div class=\"member2\">\r\n			<h2>会员通道</h2>\r\n			<p>天才人生网是一个收费的小众网站，是与我们有共同理念人们的聚集地（有远大志向人的聚集地）。我们认为青 少年必须尽早立志，在初天才人生网是一个收费的小众网站，是与我们有共同理念人们的聚集地（有远大志向人的聚集地）。我们认为青 少年必须尽早立志天才人生网是一个收费的小众网站，是与我们有共同理念人们的聚集地（有远大志向人的聚集地）。我们认为青 少年必须尽早立志</p>\r\n			<p>天才人生网是一个收费的小众网站，是与我们有共同理念人们的聚集地（有远大志向人的聚集地）。我们认为青 少年必须尽早立志</p>\r\n		</div>\r\n		<div class=\"ownserve\">\r\n			<h2>会员专属服务</h2>\r\n			<p>天才人生致力于引导处于不同阶段的会员最大程度的提高能力，发挥潜能。本网站特邀不同领域的专家和学者提 供专业的辅导和点评，让会</p>\r\n			<img src=\"${baseimg}/imgs/default/member1.png\" />\r\n			<img src=\"${baseimg}/imgs/default/member2.png\" />\r\n			<img src=\"${baseimg}/imgs/default/member3.png\" />\r\n			<ul class=\"serveList\">\r\n				<li>\r\n					<i class=\"circle\"></i> 自学：针对所学内容得到专业的书籍推荐和自学成果点评。\r\n				</li>\r\n				<li>\r\n					<i class=\"circle\"></i> 问答：对于人生发展的困惑可以得到专业的回答，并有机会得到顾雏军先生的直接指导和点评。\r\n				</li>\r\n				<li>\r\n					<i class=\"circle\"></i>  互动：可以自行组建线上交流群，帮助会员进行头脑风暴和思维拓展。\r\n				</li>\r\n			</ul>\r\n		</div>\r\n		<div class=\"member2\">\r\n			<h2>积分应用与活动</h2>\r\n			<p> 会员在本网站参与评论、发布信息和原创文章、建立话题都可以获得相应积分，积分可用于参与特设课程、线下 沙龙和论坛等活动。</p>\r\n		</div>\r\n		<div class=\"ownserve\">\r\n			<h2>自我推广</h2>\r\n			<p>会员在所有栏目中的内容，可以在一周内享受置顶或推荐服务。会员发表的优秀个人作品/文章，本网站会向网 站使用用户进行推送服务，</p>\r\n			<img src=\"${baseimg}/imgs/default/tui1.png\" />\r\n			<img src=\"${baseimg}/imgs/default/tui2.png\" />\r\n			<img src=\"${baseimg}/imgs/default/tui3.png\" />\r\n		</div>\r\n		<div class=\"member2\">\r\n			<h2>其他特权</h2>\r\n			<p>所有超天才会员在享有天才人生全部特权的同时，还享有超天才网旗下所有其他子网站的专享特权和优惠服务，详情请进入各个子网站的会</p>\r\n		</div>\r\n		<a href=\"http://user.supergenius.cn/register\" class=\"go\">立即加入</a>',NULL,1,'ii683b04795f48d0a70b1f9004c1foii','2018-01-30 12:19:30','2018-06-21 20:00:36');
insert into `content` (`uid`, `cid`, `name`, `type`, `data`, `summary`, `content`, `originurl`, `status`, `adminuid`, `createtime`, `updatetime`) values('uidhtml0000000000000000000000009','524288','别出心裁','2','','','<div class=\"newtopimg\" style=\"background:url(${baseimg}/imgs/default/newtopbg.png) no-repeat center center;background-size: 100% 100%;\">\r\n	<div class=\"tipWord\">\r\n		<p>目前社会上骗局日益更新，大学生们屡屡上当。</p>\r\n		<p>上传社会上的各种骗局，让大家识别社会的风险。</p>\r\n	</div>\r\n</div>',NULL,'1','86883b04795f38d0a70b1f9004c1f989','2018-01-30 12:19:30','2018-05-29 14:28:54');
	
/*思维拓展初始化*/
insert into `topic` (`oid`, `uid`, `cid`, `adminuid`, `useruid`, `author`, `content`, `title`, `imglittle`, `imgmedium`, `imgbig`, `imgoriginal`, `origin`, `summary`, `keywords`, `type`, `kind`, `istop`, `isoriginal`, `toptime`, `examine`, `status`, `createtime`, `updatetime`) values('1','1111406041344677be82749df1926322','1048576',NULL,'ee397cd1cb93471f97b2d8df6563f111','呵呵呵呵呵呵','<p>这个不需要那么复杂的功能，可以参照引资购商论坛发表帖子的编辑器</p>\r\n','7777','/imgs/webdata/life/180529173953884022_160_120.jpg','/imgs/webdata/life/180529173953884022_480_360.jpg','/imgs/webdata/life/180529173953884022_o.jpg','/imgs/webdata/life//180529173953884022.jpg',NULL,NULL,NULL,'0','0','0','0','2018-05-29 17:40:16','1','1','2018-05-29 17:40:16','2018-05-29 17:40:57');
