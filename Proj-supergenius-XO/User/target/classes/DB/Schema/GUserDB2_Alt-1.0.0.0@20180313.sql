/*==============================================================*/
/* DBMS name:      MySQL 5.5                                                                  */
/* Created on:     19:01 2013/7/14                                                            */
/*==============================================================*/

/*==============================================================*/
/*
DataBase:      GUserDB2
Author:        YongXueZhen
Description:   GUserDB2.0.0.0版本修改的表结构
ModifyDate:    2018年03月13日17:42:39 
*/

/*==============================================================*/
ALTER TABLE userinfo ADD wx VARCHAR(100)  DEFAULT NULL  COMMENT '微信' AFTER qq; 
ALTER TABLE userinfo ADD sina VARCHAR(100)  DEFAULT NULL  COMMENT '新浪微博' AFTER wx;
ALTER TABLE userinfo ADD othernicks VARCHAR(100)  DEFAULT NULL  COMMENT '第三方登录昵称' AFTER sina; 
ALTER TABLE userinfo ADD otheravatar VARCHAR(100)  DEFAULT NULL  COMMENT '第三方登录头像' AFTER othernicks; 
ALTER TABLE userinfo ADD officer VARCHAR(100)  DEFAULT NULL  COMMENT '军官证' AFTER otheravatar; 
ALTER TABLE userinfo ADD soldier VARCHAR(100)  DEFAULT NULL  COMMENT '士兵证' AFTER officer; 
ALTER TABLE userinfo ADD passport VARCHAR(100)  DEFAULT NULL  COMMENT '护照' AFTER soldier; 
ALTER TABLE userinfo ADD aisle int  DEFAULT NULL  COMMENT '注册通道' AFTER sina; 
ALTER TABLE user ADD openid VARCHAR(100)  DEFAULT NULL  COMMENT '第三方身份id' AFTER phone; 
ALTER TABLE userinfo ADD freeze int  DEFAULT 0  COMMENT '用户冻结状态' AFTER msn; 
ALTER TABLE user MODIFY email VARCHAR(255) DEFAULT NULL;
ALTER TABLE user MODIFY password VARCHAR(36) DEFAULT NULL;


/**
 * 修改军官证officer 为护照国家 country
 */

ALTER TABLE userinfo CHANGE officer country VARCHAR(100);





