/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     19:04 2013/1/10                                                   */
/*==============================================================*/

/*==============================================================*/
/*
DataBase: GeniusTest
Author:		architect.bian
Description:	GeniusTest初始化正式数据
CreatedDate:	19:04 2013/1/10
ModifyDate:	19:04 2013/1/10
*/

/*==============================================================*/
/*
Author:		architect.bian
Description:	***表的初始化测试数据
CodeFile:		com.wbcom.web.admin.dao.***
*/
INSERT INTO `adminrole` (`uid`, `adminuid`, `adminid`, `roleid`, `rolename`, `desc`, `createtime`, `updatetime`) VALUES 
  ('re483b04795f48d0a70b1f9004c1foej', 'uid11111111111111111111111111111', 'admin', 'ROLE_ADMIN', '', '', '2013-11-25 11:15:10', '2013-11-25 13:25:40'),
  ('ee483b04795f48d0a70b1f9004c1fasw', null, null, 'ROLE_ADMIN', '超级管理员权限', '超级管理员权限描述', '2013-11-25 11:15:10', '2013-11-25 13:25:40');
