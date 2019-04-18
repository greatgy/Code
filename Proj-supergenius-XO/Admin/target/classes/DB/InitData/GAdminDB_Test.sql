/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     19:04 2013/1/10                                                */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: WebDB
Author:		architect.bian
Description:	GWebDB初始化测试数据
CreatedDate:	19:04 2013/1/10
ModifyDate:	19:04 2013/1/10
*/

/*==============================================================*/
/*
Author:		architect.bian
Description:	***表的初始化测试数据
CodeFile:		com.wbcom.web.admin.dao.***
*/

/*==============================================================*/
/*
Author:		architect.bian
Description:	***表的初始化测试数据
*/
INSERT INTO `adminrole` (`uid`, `adminid`, `roleid`, `rolename`, `desc`, `createtime`, `updatetime`) VALUES 
  ('uid11111111111111111111111111111', 'user', 'ROLE_USER', '普通权限', '普通权限描述', '2013-11-23 10:10:10', '2013-11-24 13:25:40');

/*==============================================================*/
/*
Author:		LiuYongjian
Description:	adminurl表的初始化测试数据
*/
INSERT INTO `adminauthority` (`uid`, `roleuid`, `roleid`, `url`, `name`, `desc`, `createtime`, `updatetime`) VALUES 
  ('uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'ROLE_USER','.*','普通用户权限','权限描述','2013-11-23 17:05:12', '2013-11-23 17:05:12');

/*==============================================================*/
/*
Author:		liushaomin
Description:	adminlog表的初始化测试数据
*/
INSERT INTO `adminlog` (`uid`, `dataid`, `adminuid`, `channel`, `operation`, `data`, `desc`, `createtime`) VALUES 
  ('uid11111111111111111111111111111', 'uid11111111111111111111111111111', 'uid11111111111111111111111111111', 0,'测试', '测试', '测试', '2013-10-29 14:14:30');

/*==============================================================*/
/*
Author:		LiuShaoMin
Description:	workorder表的初始化测试数据
*/
INSERT INTO `workorder` (`uid`, `sn`, `adminuid`, `adminname`, `title`, `fromuid`, `name`, `desc`, `remark`, `datafile`, `type`, `channel`, `stageform`, `stageto`, `status`, `createtime`, `updatetime`) VALUES 
  ('93483b04795f48d0a70b1f9004c1f98f', '0001', '93483b04795f48d0a70b1f9004c1f98f', 'admin', '标题', '93483b04795f48d0a70b1f9004c1f98f', 'user', '22', '00', '0', 0, 0, 0, 0, 0, '2013-11-23 16:00:21', '2013-11-23 16:00:21');
  
/*==============================================================*/
/*
Author:		LiuShaoMin
Description:	workflow表的初始化测试数据
*/
INSERT INTO `workflow` (`uid`, `workorderuid`, `adminuid`, `adminname`, `title`, `desc`, `remark`, `datafile`, `stageform`, `stageto`, `statusfrom`, `statusto`, `type`, `status`, `createtime`, `updatetime`) VALUES 
  ('93483b04795f48d0a70b1f9004c1f98f', '93483b04795f48d0a70b1f9004c1f98f', '93483b04795f48d0a70b1f9004c1f98f', 'admin', '标题', '详细', '12', '12', 0, 0, 0, 0, 0, 0, '2013-11-23 17:05:12', '2013-11-23 17:05:12');

/*==============================================================*/
/*
Author:     XieMing
Description:    emaillog表的初始化测试数据
*/
INSERT INTO `emaillog` (`uid`, `adminid`, `title`, `content`, `receiver`, `sender`, `createtime`, `updatetime`) VALUES 
  ('93483b04795f48d0a70b1f9004c1f98f', '93483b04795f48d0a70b1f9004c1f98f', '吃饭', '坏人', '{"uid11111111111111111111111111111":"好人"}', '好人', '2016-4-28 16:57:01', '2016-5-23 17:00:20');