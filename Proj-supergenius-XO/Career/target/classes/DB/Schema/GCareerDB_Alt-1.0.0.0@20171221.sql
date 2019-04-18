/* ruler表添加content字段，存储规则内容 */
ALTER TABLE `ruler` ADD COLUMN `content` mediumtext COMMENT '内容';
alter table `tease` MODIFY column `content` longtext;
alter table `answer` MODIFY column `content` longtext;
alter table `comments` MODIFY column `content` longtext;
ALTER TABLE `catalogue` MODIFY COLUMN `data` LONGTEXT;