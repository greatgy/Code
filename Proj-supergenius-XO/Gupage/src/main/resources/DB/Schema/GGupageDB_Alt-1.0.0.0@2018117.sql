/** 增加keywords字段    **/
alter table `article` ADD COLUMN `keywords` varchar(500) DEFAULT '' COMMENT 'seo关键字';
alter table `debate` ADD COLUMN `keywords` varchar(500) DEFAULT '' COMMENT 'seo关键字';
