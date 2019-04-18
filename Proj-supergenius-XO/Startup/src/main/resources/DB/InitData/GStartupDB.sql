﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     17:10 2017/6/20                                                   */
/*==============================================================*/

/**
 * 规则表初始化数据
 * @author : ChenQi
 * @date : 2017年6月20日17:17:17
 */
INSERT INTO `ruler` (`uid`, `type`, `name`, `rejectmincount`, `rejectmaxcount`, `minscore`, `maxscore`, `content`, `isinit`, `status`, `createtime`) VALUES 
 ('10000000000000000000000000000001', 0, '不适合创业', 2, 4, 0, 360, '您的一票否决数量超过了2，您不适合创业', 0, 1, '2017-06-23 15:28:12'),
 ('10000000000000000000000000000003', 0, '不适合创业', 0, 1, 0, 200, '您的得分低于200分，您不适合创业', 0, 1, '2017-06-23 15:28:21'),
 ('10000000000000000000000000000002', 0, '适合创业', 0, 0, 256, 360, '您的一票否决数量为0，并且得分超过了256分，您适合创业', 0, 1, '2017-06-23 15:28:17');
 
 INSERT INTO `ruler` (`uid`, `type`, `name`, `content`, `isinit`, `status`, `createtime`) VALUES 
 ('10000000000000000000000000000000', 0, '不能确定', '对不起，无法确定', 0, 1, '2017-07-05 16:07:33');
/*================================================================================================*/
 /**
 * 题库表初始化数据
 * @author : ChenQi
 * @date : 2017-06-21 11:41:52
 */
  INSERT INTO `question` (`uid`, `type`, `name`, `options`, `optionscore`, `order`, `status`, `createtime`) VALUES 
 ('20000000000000000000000000000001', 0, '当你一眼看中一件价格相对昂贵但你能支付得起的商品时，你通常会', '{"":{"A":"立刻购买", "B":"将同类商品浏览一遍，货比三家之后再购买", "C":"多争求几个人的意见，再做决定", "D":"很难决定，最好有人帮我下决心", "E":"等到降价再买", "F":"放弃购买"}}', '{"":{"A":7, "B":10, "C":3, "D":0, "E":5, "F":6}}', 1, 1, '2017-06-23 16:53:00'),
 ('20000000000000000000000000000002', 0, '你总会从父母、或者老师、或者朋友那里听到这样的批评', '{"":{"A":"你总是缺少常性，不喜欢花很长时间去做一件事情", "B":"你总是缺乏果敢，遇到困难就裹足不前", "C":"你总是缺少耐心，容易冲动和草率行事", "D":"你总是缺少紧迫感，经常把事情拖到最后一刻完成", "E":"你有某种强迫症，甚至一点瑕疵都不能容忍", "F":"你总是自以为是，而且偏执"}}', '{"":{"A":1, "B":0, "C":4, "D":7, "E":5, "F":10}}', 2, 1, '2017-06-23 16:53:17'), 
 ('20000000000000000000000000000003', 0, '如果你是一名在大学学过数学的学生，学习过程中老师布置了一道数学难题，没有参考答案，为了解出答案你已经花了1个多小时时间，但还是毫无头绪，此时你会', '{"":{"A":"果断放弃", "B":"自己再研究几天，如果仍然解不开答案，只好放弃", "C":"一直研究，不论多久，直到解出答案为止", "D":"找人请教和讨论，如果仍然得不到答案，再放弃", "E":"自己研究几天得不到答案，再去请教别人，直到得到答案", "F":"不确定，大学期间没有学过数学"}}', '{"":{"A":0, "B":4, "C":10, "D":6, "E":8, "F":6}}', 3, 1, '2017-06-23 16:53:28'),
 ('20000000000000000000000000000004', 0, '对于下定决心的事情，你总是', '{"":{"A":"一旦下定决心，即使周围人都反对，我也会坚持去做，并且坚持到底", "B":"我会想方设法争取支持，但如果最后支持的人还是寥寥无几，我仍然会全力坚持去做", "C":"我会听从大多数人都意见", "D":"任何的反对意见可能都会让我改变主意", "E":"决定了就去做，我根本不会征求别人的意见", "F":"其他"}}', '{"":{"A":8, "B":10, "C":3, "D":0, "E":5, "F":5}}', 4, 1, '2017-06-23 16:53:36'),
 ('20000000000000000000000000000005', 1, '下列人物中，你觉得自己更像谁', '{"0":{"A":"刘秀", "B":"足智多谋的诸葛亮", "C":"忠肝义胆的关羽", "D":"知足常乐的猪八戒", "E":"任劳任怨的沙僧", "F":"执着木讷的郭靖", "G":"至情至性的杨过", "H":"机灵讲义气的韦小宝", "I":"其他"}, "1":{"A":"八面玲珑的王熙凤", "B":"圆滑世故的薛宝钗", "C":"多愁善感的林黛玉", "D":"武则天", "E":"古灵精怪的黄蓉", "F":"善解人意的双儿", "G":"侠骨柔肠的郭襄", "H":"狠辣决绝的李莫愁", "I":"其他"}}', '{"0":{"A":10, "B":7, "C":5, "D":1, "E":2, "F":5, "G":6, "H":6, "I":5}, "1":{"A":7, "B":4, "C":3, "D":10, "E":7, "F":2, "G":5, "H":7, "I":5}}', 5, 1, '2017-06-23 16:53:47'),
 ('20000000000000000000000000000006', 1, '你对自己最为之骄傲的是', '{"":{"A":"颜值", "B":"气质", "C":"智商", "D":"情商", "E":"口才", "F":"胆量", "G":"酒量", "H":"激情", "I":"目前还没有什么可骄傲的", "J":"其他"}}', '{"":{"A":3, "B":5, "C":7, "D":6, "E":9, "F":10, "G":6, "H":8, "I":1, "J":5}}', 6, 1, '2017-06-23 16:53:55'), 
 ('20000000000000000000000000000007', 1, '下列书籍或影视作品中，你最喜欢的题材类型贴近于', '{"":{"A":"《福尔摩斯》", "B":"《三国演义》", "C":"《射雕英雄传》", "D":"《鹿鼎记》", "E":"《乔家大院》", "F":"《三生三世十里桃花》", "G":"《中国式关系》", "H":"《人民的名义》", "I":"其他"}}', '{"":{"A":8, "B":10, "C":5, "D":7, "E":7, "F":3, "G":5, "H":7, "I":5}}', 7, 1, '2017-06-23 16:54:04'),
 ('20000000000000000000000000000008', 1, '工作之余，你花时间最多的是', '{"":{"A":"看书", "B":"旅行", "C":"广泛社交", "D":"谈恋爱", "E":"购物", "F":"极限运动", "G":"养生", "H":"看恐怖电影", "I":"看肥皂剧", "J":"其他"}}', '{"":{"A":8, "B":6, "C":10, "D":5, "E":4, "F":7, "G":3, "H":7, "I":1, "J":5}}', 8, 1, '2017-06-23 16:54:12'), 
 ('20000000000000000000000000000009', 1, '在人生道路上，更多时候你通过什么获得动力', '{"":{"A":"自己追求的目标", "B":"长辈的期望", "C":"他人的激励", "D":"竞争的压力", "E":"生活的压力", "F":"榜样的牵引"}}', '{"":{"A":10, "B":4, "C":6, "D":7, "E":1, "F":5}}', 9, 1, '2017-06-23 16:54:24'), 
 ('20000000000000000000000000000010', 1, '在保证精神状态良好的情况下，你每天需要多少睡眠时间', '{"":{"A":"最多4个小时就够了", "B":"5-6个小时", "C":" 7-8个小时", "D":"9-10个小时", "E":"11个小时以上"}}', '{"":{"A":6, "B":10, "C":6, "D":4, "E":1}}', 10, 1, '2017-06-23 16:54:34'), 
 ('20000000000000000000000000000011', 1, '对于做自己感兴趣的事情，你常常', '{"":{"A":"感到时间不知不觉就溜走了，经常忘记吃饭和睡觉", "B":"时间久了会感到疲惫，但我会坚持每天花同样多的时间在上面", "C":" 一开始会忘我的投入，但慢慢热情就减退了，花费时间也就越来越少", "D":"再怎么感兴趣，我都将保持我一贯的作息时间规律", "E":"至今为止我好像没什么事情是感兴趣的", "F":"其他"}}', '{"":{"A":10, "B":8, "C":3, "D":5, "E":1, "F":5}}', 11, 1, '2017-06-23 16:54:47'),
 ('20000000000000000000000000000012', 1, '你真正确立一个此生将为之奋斗的理想（并且确立以后真的在为之努力）是在什么时候', '{"":{"A":"10岁之前", "B":"上中学（包括初中和高中）的时候", "C":"上大学的时候", "D":"工作以后", "E":"现在还没有真正确立这样的理想"}}', '{"":{"A":10, "B":8, "C":6, "D":4, "E":1}}', 12, 1, '2017-06-23 16:54:55'),
 ('20000000000000000000000000000013', 1, '你对金钱的态度，更接近哪一种', '{"":{"A":"做一个像比尔•盖茨那样的超级富豪，是我毕生的追求", "B":"我喜欢各种奢华的东西，我会尽可能地想办法拥有它们", "C":" 我享受赚钱的快感，但并不在乎自己是不是有钱人", "D":"我只希望付出能有等价的回报", "E":"我对金钱没有太多奢求，赚多就多花，赚少就少花", "F":"相比金钱，我更需要诗和远方", "G":"其他"}}', '{"":{"A":10, "B":8, "C":5, "D":3, "E":3, "F":1, "G":5}}', 13, 1, '2017-06-23 16:55:04'),
 ('20000000000000000000000000000014', 1, '赚钱对你有多重要', '{"":{"A":"为了赚钱，我可以舍弃娱乐和休息", "B":"为了赚钱，我可以舍弃自尊", "C":"为了赚钱，我可以舍弃家庭", "D":"为了赚钱，我可以舍弃友情", "E":"为了赚钱，我可以舍弃爱情", "F":"为了赚钱，上面几项我都可以舍弃", "G":"我不会为了赚钱舍弃上面任何一项"}}', '{"":{"A":3, "B":9, "C":8, "D":5, "E":4, "F":10, "G":1}}', 14, 1, '2017-06-23 16:55:13'),
 ('20000000000000000000000000000015', 1, '下列选项中，得到什么最使你有满足感', '{"":{"A":"实实在在的金钱", "B":"他人的崇拜和追棒", "C":"他人的赞赏和尊重", "D":"他人的帮助和照顾", "E":"帮助别人的那种心灵上的满足", "F":"在乎的人的真挚情感", "G":"其他"}}', '{"":{"A":10, "B":8, "C":6, "D":3, "E":3, "F":1, "G":5}}', 15, 1, '2017-06-23 16:55:20'),
 ('20000000000000000000000000000016', 1, '在聚会上，如果只有你一个人穿的不正式，你会', '{"":{"A":"感到很不自在，想办法早点离开", "B":"感到有些窘迫，但庆幸自己不是主角，全程保持低调就好了", "C":"没关系，这对我的心情和行为将没有丝毫影响", "D":"我就喜欢与众不同，甚至有点兴奋能有这样的事情发生", "E":"一旦发现立即离开"}}', '{"":{"A":2, "B":3, "C":10, "D":8, "E":5}}', 16, 1, '2017-06-23 16:55:30'),
 ('20000000000000000000000000000017', 1, '如果因为你做了一件错事而有人当面批评你，通常这时', '{"":{"A":"我会反过来批评他", "B":"我会为自己辩解，没理也能辩三分", "C":"我会想办法回避起来", "D":" 我会耐心倾听，尤其对中肯的批评，然后给自己做个总结分析", "E":"听是听了，但是我基本左耳听右耳就冒了", "F":"表面我比较无所谓，但是心里很在意，甚至感到有一点难过", "G":"我会感到很不好意思，想找个地缝钻进去"}}', '{"":{"A":10, "B":6, "C":4, "D":7, "E":5, "F":3, "G":2}}', 17, 1, '2017-06-23 16:55:41'),
 ('20000000000000000000000000000018', 1, '对于每天出现的新变化，你总是', '{"":{"A":"第一时间就能发现", "B":"较晚时间才能发现", "C":"需要别人提醒或告知时才能发现"}}', '{"":{"A":10, "B":6, "C":1}}', 18, 1, '2017-06-23 16:56:02'),
 ('20000000000000000000000000000019', 1, '少年时期，哪项描述与你更贴切', '{"0":{"A":"我喜欢和男孩子们一起玩，我总是领头的那一个", "B":"我喜欢和男孩子们一起玩，我不是领头的那一个", "C":"我喜欢和女孩子们一起玩，我总是领头的那一个", "D":"我喜欢和女孩子们一起玩，我不是领头的那一个", "E":"我喜欢自己玩", "F":"其他"}, "1":{"A":"我喜欢和男孩子们一起玩，我总是领头的那一个", "B":"我喜欢和男孩子们一起玩，我不是领头的那一个", "C":"我喜欢和女孩子们一起玩，而且总是领头的那一个", "D":"我喜欢和女孩子们一起玩，我不是领头的那一个", "E":"我喜欢自己玩", "F":"其他"}}', '{"0":{"A":10, "B":5, "C":7, "D":1, "E":5, "F":5}, "1":{"A":10, "B":5, "C":8, "D":2, "E":5, "F":5}}', 19, 1, '2017-06-23 16:56:14'),
 ('20000000000000000000000000000020', 1, '下列对未来自己的描述，你认为最贴切的是', '{"":{"A":"我认为我能改变世界", "B":"我认为我能改变与我接触过的所有人", "C":"我认为我能改变与我长期相处的人，包括朋友和家人", "D":"我认为我能改变我最亲密的人", "E":"我认为我能改变我自己", "F":"我认为我谁都改变不了，包括我自己"}}', '{"":{"A":10, "B":8, "C":6, "D":4, "E":5, "F":0}}', 20, 1, '2017-06-23 16:56:22'),
 ('20000000000000000000000000000021', 1, '对待一件事情，当只有你与周围人想法不一致的时候，哪项描述与你最贴近', '{"":{"A":"我总是要想办法让他们欣然接受我的想法", "B":"我喜欢与他们展开辩论，“真理”往往越辩越明", "C":"我不在意，不可能要求所有人的想法都一样", "D":"为了“和谐”，表面上我会迁就他们的想法，但内心还是觉得自己是对的", "E":"可能会对自己的想法产生怀疑", "F":"我往往很快就会被大家说服"}}', '{"":{"A":10, "B":9, "C":6, "D":4, "E":2, "F":1}}', 21, 1, '2017-06-23 16:56:33'),
 ('20000000000000000000000000000022', 1, '遇到能力比你强的人，你内心的真实感受是', '{"":{"A":"我一定要让他为我所用", "B":"取长补短，希望能够和他成为黄金搭档", "C":"羡慕嫉妒恨，和这样的人一起共事要格外小心", "D":"我得想办法压制他", "E":"最好是你走你的阳关道，我走我的独木桥", "F":"能跟在这样的人后面，我将心悦诚服", "G":"其他"}}', '{"":{"A":10, "B":8, "C":5, "D":4, "E":2, "F":1, "G":5}}', 22, 1, '2017-06-23 16:56:41'),
 ('20000000000000000000000000000023', 1, '如果你和朋友一起去购物，遇到一件商品有两个款式，你的朋友都比较喜欢，对于到底要买哪一个你的朋友已经纠结快半个小时了，这时你通常会', '{"":{"A":"建议他把两件商品都买下来，甚至借钱给他把两件商品都买下来", "B":"劝他把你认为更适合他的那件商品买下来", "C":"给他对比分析一下两件商品的优缺点，但不给明确建议", "D":" 让他继续纠结着，自己先去别处逛逛，等他纠结完再回来", "E":"让他继续纠结，自己先回家", "F":"在旁边默默等待", "G":"让他自己抛硬币决定"}}', '{"":{"A":10, "B":9, "C":6, "D":4, "E":7, "F":1, "G":6}}', 23, 1, '2017-06-23 16:56:50'),
 ('20000000000000000000000000000024', 1, '假设你正与一位客户谈你的创业计划，如果你用一种方法去说服他，他不信你，你会', '{"":{"A":"不在乎，我只找信我的客户", "B":"试图换一个方法再去说服这位客户", "C":"找和这位客户关系好的人，让这个人去说服这位客户", "D":"找和这位客户有关的所有人，尽可能多的让这些人去影响这位客户"}}', '{"":{"A":10, "B":6, "C":5, "D":2}}', 24, 1, '2017-06-23 16:56:58'),
 ('20000000000000000000000000000025', 1, '假设创业过程中，你需要20万元人民币的启动资金，目前你已经有了10万，在无法向父母和亲属再去借的情况下，剩下的10万你能在一星期之内向朋友借齐或用别的办法筹集成功吗', '{"":{"A":"能", "B":"不能", "C":"不确定"}}', '{"":{"A":10, "B":0, "C":5}}', 25, 1, '2017-06-23 16:57:05'),
 ('20000000000000000000000000000026', 1, '关于减肥，哪项描述与你更相近', '{"":{"A":"我愿意做一个快乐的胖子，从不减肥", "B":"我尝试过了各种减肥计划，但效果不佳", "C":"我总说要减肥，但就是管不住嘴、迈不开腿", "D":"我已经减肥成功", "E":"我一直都是个瘦子，不考虑减肥", "F":"其他"}}', '{"":{"A":7, "B":5, "C":1, "D":10, "E":7, "F":5}}', 26, 1, '2017-06-23 16:57:15'),
 ('20000000000000000000000000000027', 1, '以下工作状态，如果你必须选择一种，你倾向于', '{"":{"A":"没有假期，常常超负荷工作", "B":"长期出差，总是飞来飞去", "C":"人际关系复杂，整天勾心斗角", "D":"工作简单重复，枯燥乏味", "E":"时刻准备，专门应对紧急和棘手问题", "F":"陌生领域，需要面对各种未知问题和全新问题", "G":"其他"}}', '{"":{"A":6, "B":8, "C":5, "D":2, "E":7, "F":10, "G":5}}', 27, 1, '2017-06-23 16:57:24'),
 ('20000000000000000000000000000028', 1, '假设在创业过程中，你的客户、或者你的合作伙伴、或者是你的家庭，让你遭受了巨大的心理压力，你会', '{"":{"A":"虽然感到很痛苦，但对做事没有太大影响", "B":"感到很受伤，但是能够很快想通并从消极情绪中平复过来", "C":"想到此事就感到很痛苦，而且还会常常想到此事", "D":"不确定"}}', '{"":{"A":10, "B":7, "C":1, "D":5}}', 28, 1, '2017-06-23 16:57:31'),
 ('20000000000000000000000000000029', 1, '平时的工作和生活中，哪项描述与你最贴近', '{"":{"A":"我经常尝试一些自己没做过的事情，做不好也没关系", "B":"我有时间就钻研自己较熟悉的事情，试图找到新方法把它做得更好", "C":"我只喜欢做一些与众不同的事情，即使总被嘲笑", "D":"我可以不厌其烦地重复做一件简单的事情，这能让我消去浮躁", "E":"我总会花很多时间把事情做的很完美，我对有瑕疵的事物不能容忍"}}', '{"":{"A":10, "B":8, "C":8, "D":2, "E":5}}', 29, 1, '2017-06-23 16:57:38'),
 ('20000000000000000000000000000030', 1, '对于那些对你的事业和家庭有重大影响的事，你需要有多大把握才会采取行动', '{"":{"A":"100%", "B":"80%-90%", "C":"60%-70%", "D":" 40%-50%", "E":" 20%-30%", "F":"10%-20%", "G":"0"}}', '{"":{"A":1, "B":5, "C":7, "D":10, "E":8, "F":5, "G":1}}', 30, 1, '2017-06-23 16:57:47'),
 ('20000000000000000000000000000031', 1, '对于各类聚会活动，你的态度接近于', '{"":{"A":"多多参加，争取成为会王，混个脸熟，万一能认识哪位大咖呢", "B":"只选对自己可能有帮助的参加", "C":"创造条件也要参加对自己可能有帮助的聚会活动", "D":"只选自己有认识人的参加", "E":"只选自己没有认识人的参加", "F":"只选自己感兴趣的参加", "G":"参加一两次见过世面足矣，其他的就不参加", "H":"一概不参加"}}', '{"":{"A":5, "B":7, "C":10, "D":3, "E":5, "F":5, "G":5, "H":1}}', 31, 1, '2017-06-23 16:57:54'),
 ('20000000000000000000000000000032', 1, '如果有好友邀请你去参加他/她的生日派对，但是任何一位来宾你都不认识，这时你会', '{"":{"A":"找个合适的理由拒绝参加，也不会送礼", "B":"找个合适的理由拒绝参加，但是会邮寄一份礼物", "C":"愿意早去一会儿帮他/她筹备，等派对快开始的时候找理由离开", "D":"按时参加，但如果感觉没意思会提前离开", "E":"按时参加，很乐意借此认识新朋友", "F":"一定会早去晚走，成为朋友生日派对的好帮手"}}', '{"":{"A":0, "B":3, "C":5, "D":7, "E":10, "F":8}}', 32, 1, '2017-06-23 16:58:05'),
 ('20000000000000000000000000000033', 1, '当你耗费很大心力但很成功的完成一件事情之后，你喜欢', '{"":{"A":"立刻开始一件新的艰巨事情", "B":"做一些简单的事情缓冲几天", "C":"放一个长假好好休整一下", "D":"相当长一段时间都不再做同样耗费心力的事情了"}}', '{"":{"A":6, "B":10, "C":5, "D":2}}', 33, 1, '2017-06-23 16:58:13'),
 ('20000000000000000000000000000034', 1, '下列工作，你更喜欢的是', '{"":{"A":"分析并预测一件事情的可能进展", "B":"分析一件事情的深层原因", "C":"发现一件事情存在的缺陷和问题，让别人去干", "D":"消除一件事情存在的缺陷或问题", "E":"用语言去改变别人的行为和决策", "F":"完整构想一件别人没有想过的事情", "G":"按照设定的目标和思路实现完美执行", "H":"都不感兴趣"}}', '{"":{"A":6, "B":9, "C":7, "D":8, "E":10, "F":9, "G":3, "H":0}}', 34, 1, '2017-06-23 16:58:26'),
 ('20000000000000000000000000000035', 1, '工作过程中，一段时间内你通常', '{"":{"A":"能够同时应对好几件事，并且感到游刃有余", "B":"可以同时应对好几件事，但会有些手忙脚乱", "C":"最多可以同时应对三件事", "D":"最多可以同时应对两件事"}}', '{"":{"A":10, "B":7, "C":5, "D":3}}', 35, 1, '2017-06-23 16:58:35'),
 ('20000000000000000000000000000036', 1, '如果在工作中遇到了新知识，但并不是你马上就要用的，哪项描述与你更接近', '{"":{"A":"自己先看看要点，用较短时间学个大概齐，以后别人提到时能听懂就行", "B":"找相关方面的专业人士请教一下，再找一本经典书快速看一遍，达到半专业水平是我一贯的Level ", "C":"我会花一段较长的时间，按照从入门到高级的顺序，一步一步系统扎实的学习，掌握完整的知识结构才能让我踏实", "D":"现用现学就来得及，有需要时候再说吧", "E":"新知识和我无关，我的老本差不多就够用了"}}', '{"":{"A":8, "B":10, "C":6, "D":7, "E":3}}', 36, 1, '2017-06-23 16:58:43');
 
 /**
 * 模块表初始化数据
 * @author : 许志翔
 * @date : 2017年8月25日12:32:52
 */
INSERT INTO `catalogue` (`cid`, `pcid`, `name`, `status`, `createtime`, `updatetime`) VALUES 
 (1, 0, '创业资讯', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (2, 0, '创业花絮', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (20, 1, '热点专题', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (21, 1, '创业公司', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (22, 1, '创业资本', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (23, 1, '行业动态', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (24, 1, '政策法规', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (25, 2, '创业轶事', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (26, 2, '创业者说', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (27, 2, '投资者说', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (28, 2, '创业八卦', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17'),
 (29, 2, '快讯', 1, '2017-08-25 14:15:17', '2017-08-25 14:15:17');
 