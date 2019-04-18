/* 将label表中的refuid字段属性由varchar(500)改为text */
alter table `label` MODIFY column `refuid` text;
ALTER TABLE `article` ADD COLUMN `isflash` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否快讯(0 非快讯   1 快讯)' after title;