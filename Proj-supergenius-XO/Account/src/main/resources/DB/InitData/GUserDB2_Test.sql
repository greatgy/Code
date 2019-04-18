﻿/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                 */
/* Created on:     19:04 2014/10/25                                                */
/*==============================================================*/

/*==============================================================*/
/*Author:		liushaomin
Description:	comments表的初始化测试数据
CodeFile:		
*/
INSERT INTO `comments` (`uid`, `fromuid`, `touid`, `fromuseruid`, `fromuseroid`, `fromusername`, `touseruid`, `touseroid`, `tousername`, `content`, `type`, `status`, `channel`, `config`, `createtime`) VALUES 
  ('uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊0', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000001', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊1', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000002', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊2', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000003', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊3', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000004', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊4', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000005', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊5', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000006', 'uid00000000000000000000000000002', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊6', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000007', 'uid00000000000000000000000000002', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊7', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000008', 'uid00000000000000000000000000002', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊8', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000009', 'uid00000000000000000000000000003', 'uid00000000000000000000000000000', 'uid00000000000000000000000000000', 0, '张三', 'uid00000000000000000000000000000', 1, NULL, '大师兄说得对啊9', 1, 1, 4, 1, '2014-10-17 15:46:24'),
  ('uid00000000000000000000000000010', 'uid00000000000000000000000000003', 'uid00000000000000000000000000000', 'uid00000000000000000000000000001', 1, '李四', 'uid00000000000000000000000000000', 1, NULL, '大师兄说的对啊10', 1, 1, 4, 1, '2014-10-17 15:46:24');
  

INSERT INTO `user` (`oid`, `uid`, `usersn`, `email`, `newemail`, `password`, `resetpwd`, `paypwd`, `name`, `nickname`, `showname`, `account`, `freezeaccount`, `type`, `channelfrom`, `avatarbig`, `avatar`, `avatarlittle`, `original`, `summary`, `specialty`, `job`, `department`, `company`, `identityid`, `domain`, `gender`, `birthday`, `college`, `address`, `qq`, `msn`, `phone`, `mobile`, `validcode`, `logincount`, `lastlogintime`, `lastloginip`, `status`, `createtime`, `updatetime`) VALUES 
  (1, 'uid00000000000000000000000000000', 'testusersn', 'aa@bb.com', NULL, '007d8aaba38d191ba3588e1dbed315db', NULL, NULL, '大旺', 'nick旺', '大旺show', 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, '2014-10-31', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2014-10-31 18:05:09', NULL, 1, '2014-10-31 18:05:09', '2014-11-04 16:56:10');



