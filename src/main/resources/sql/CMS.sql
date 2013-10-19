/*
SQLyog Trial v11.24 (64 bit)
MySQL - 5.5.27 : Database - cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cms` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `cms`;

/*Table structure for table `languages` */

DROP TABLE IF EXISTS `languages`;

CREATE TABLE `languages` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(45) NOT NULL,
  `description` varchar(256) NOT NULL DEFAULT '',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `languages` */

insert  into `languages`(`language_id`,`language_name`,`description`,`created_time`,`modified_time`) values (1,'English','english','2013-10-11 22:32:08','2013-10-11 22:32:08');

/*Table structure for table `media` */

DROP TABLE IF EXISTS `media`;

CREATE TABLE `media` (
  `media_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'media id',
  `media_name` varchar(100) NOT NULL COMMENT 'media name for internal purpose',
  `media_title` varchar(75) NOT NULL COMMENT 'media title for service purpose',
  `description` varchar(256) NOT NULL DEFAULT '',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `media_cycle_id` int(11) NOT NULL,
  `media_type_id` int(11) NOT NULL,
  `media_process_state_id` int(11) NOT NULL,
  `media_start_time` datetime NOT NULL,
  `media_end_time` datetime NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`media_id`),
  KEY `fk_media_media_cycles_idx` (`media_cycle_id`),
  KEY `fk_media_media_process_states_idx` (`media_process_state_id`),
  KEY `fk_media_media_types_idx` (`media_type_id`),
  KEY `fk_media_language_idx` (`language_id`),
  CONSTRAINT `fk_media_language` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_cycles` FOREIGN KEY (`media_cycle_id`) REFERENCES `media_cycles` (`media_cycle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_process_states` FOREIGN KEY (`media_process_state_id`) REFERENCES `media_process_states` (`media_process_state_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_types` FOREIGN KEY (`media_type_id`) REFERENCES `media_types` (`media_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media';

/*Data for the table `media` */

insert  into `media`(`media_id`,`media_name`,`media_title`,`description`,`created_time`,`modified_time`,`media_cycle_id`,`media_type_id`,`media_process_state_id`,`media_start_time`,`media_end_time`,`language_id`) values (1,'test1','test1','test','2013-10-12 19:00:35','2013-10-14 15:11:50',2,1,2,'2013-10-09 00:00:00','2013-10-17 00:00:00',1),(2,'test2','test2','test','2013-10-12 19:07:17','2013-10-14 15:11:58',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1);

/*Table structure for table `media_content_purposes` */

DROP TABLE IF EXISTS `media_content_purposes`;

CREATE TABLE `media_content_purposes` (
  `media_content_purpose_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_content_purpose` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_content_purpose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `media_content_purposes` */

insert  into `media_content_purposes`(`media_content_purpose_id`,`media_content_purpose`,`created_time`,`modified_time`) values (1,'preview','2013-10-12 12:35:38','2013-10-12 12:35:38'),(2,'media','2013-10-12 12:35:38','2013-10-12 12:36:09');

/*Table structure for table `media_contents` */

DROP TABLE IF EXISTS `media_contents`;

CREATE TABLE `media_contents` (
  `media_content_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_id` int(11) NOT NULL,
  `media_specification_id` int(11) NOT NULL,
  `storage_path` varchar(1024) NOT NULL,
  `md5` varchar(45) NOT NULL,
  PRIMARY KEY (`media_content_id`),
  KEY `fk_media_contents_media_idx` (`media_id`),
  KEY `fk_media_contents_spec_idx` (`media_specification_id`),
  CONSTRAINT `fk_media_contents_media` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_contents_spec` FOREIGN KEY (`media_specification_id`) REFERENCES `media_specifications` (`media_specification_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='this table stores media contents files';

/*Data for the table `media_contents` */

/*Table structure for table `media_cycles` */

DROP TABLE IF EXISTS `media_cycles`;

CREATE TABLE `media_cycles` (
  `media_cycle_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_cycle_state` varchar(45) NOT NULL COMMENT 'stores info about media cycle state',
  `created_time` datetime NOT NULL COMMENT 'stores  created time',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'stores last modified time',
  PRIMARY KEY (`media_cycle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='stores info about media cycle';

/*Data for the table `media_cycles` */

insert  into `media_cycles`(`media_cycle_id`,`media_cycle_state`,`created_time`,`modified_time`) values (1,'Initial','2013-10-11 22:33:52','2013-10-11 22:33:52'),(2,'Transcoded','2013-10-11 22:34:37','2013-10-11 22:37:20'),(3,'Published','2013-10-11 22:35:35','2013-10-11 22:35:35'),(4,'Removed','2013-10-11 22:36:11','2013-10-11 22:36:11');

/*Table structure for table `media_group_media` */

DROP TABLE IF EXISTS `media_group_media`;

CREATE TABLE `media_group_media` (
  `media_group_media_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_id` int(11) NOT NULL,
  `media_group_id` int(11) NOT NULL,
  `media_order` int(11) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_group_media_id`),
  KEY `fk_media_group_media_media_idx` (`media_id`),
  KEY `fk_media_group_media_media_groups_idx` (`media_group_id`),
  CONSTRAINT `fk_media_group_media_media` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_group_media_media_groups` FOREIGN KEY (`media_group_id`) REFERENCES `media_groups` (`media_group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='This table for media for media groups';

/*Data for the table `media_group_media` */

insert  into `media_group_media`(`media_group_media_id`,`media_id`,`media_group_id`,`media_order`,`created_time`,`modified_time`) values (6,2,1,0,'2013-10-14 11:27:25','2013-10-14 11:27:25'),(7,1,2,0,'2013-10-14 13:08:13','2013-10-14 13:08:13'),(8,2,2,1,'2013-10-14 13:08:16','2013-10-14 13:08:16'),(9,1,6,0,'2013-10-14 15:06:05','2013-10-14 15:06:05'),(10,2,6,1,'2013-10-14 15:06:08','2013-10-14 15:06:08'),(13,1,7,0,'2013-10-19 14:00:57','2013-10-19 14:00:57');

/*Table structure for table `media_groups` */

DROP TABLE IF EXISTS `media_groups`;

CREATE TABLE `media_groups` (
  `media_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_group_name` varchar(45) NOT NULL,
  `media_group_title` varchar(20) NOT NULL,
  `media_group_description` varchar(256) NOT NULL DEFAULT '',
  `media_group_preview_id` int(11) NOT NULL DEFAULT '-1',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media grouping ';

/*Data for the table `media_groups` */

insert  into `media_groups`(`media_group_id`,`media_group_name`,`media_group_title`,`media_group_description`,`media_group_preview_id`,`created_time`,`modified_time`) values (1,'test category','test category','test desc',1,'2013-10-12 23:03:26','2013-10-12 23:03:26'),(2,'test category1','test category1','test category1',1,'2013-10-12 23:36:00','2013-10-12 23:36:00'),(3,'test category1','test category1','test category1',1,'2013-10-12 23:39:02','2013-10-12 23:39:02'),(4,'test category4','test category4','category4',1,'2013-10-12 23:45:59','2013-10-12 23:45:59'),(5,'test category4','test category4','category4',1,'2013-10-12 23:48:18','2013-10-12 23:48:18'),(6,'test category4','test category4','category4',1,'2013-10-12 23:53:33','2013-10-12 23:53:33'),(7,'New Group','New Group','',1,'2013-10-14 16:16:47','2013-10-14 16:16:47');

/*Table structure for table `media_process_states` */

DROP TABLE IF EXISTS `media_process_states`;

CREATE TABLE `media_process_states` (
  `media_process_state_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_process_state_name` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL DEFAULT '',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_process_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='This table stores info about media process states';

/*Data for the table `media_process_states` */

insert  into `media_process_states`(`media_process_state_id`,`media_process_state_name`,`description`,`created_time`,`modified_time`) values (1,'not started','Transcode not started','2013-10-12 15:17:47','2013-10-12 15:17:47'),(2,'started','Transcode started','2013-10-12 15:18:11','2013-10-12 15:18:11'),(3,'completed','Transcode completed','2013-10-12 15:21:07','2013-10-12 15:21:07');

/*Table structure for table `media_specifications` */

DROP TABLE IF EXISTS `media_specifications`;

CREATE TABLE `media_specifications` (
  `media_specification_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_specifications_name` varchar(45) NOT NULL,
  `media_type_id` int(11) NOT NULL,
  `mime_type_id` int(11) NOT NULL,
  `media_content_purpose_id` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `bitrate` int(11) NOT NULL DEFAULT '0',
  `duration` int(11) NOT NULL,
  `media_location` tinyint(1) NOT NULL COMMENT '1 for remote and 0 for local',
  `is_source` tinyint(1) NOT NULL,
  `parent_specification_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`media_specification_id`),
  KEY `fk_media_specifications_types_idx` (`media_type_id`),
  KEY `fk_media_specifications_mime_types_idx` (`mime_type_id`),
  KEY `fk_media_specifications_content_purpose_idx` (`media_content_purpose_id`),
  CONSTRAINT `fk_media_specifications_content_purpose` FOREIGN KEY (`media_content_purpose_id`) REFERENCES `media_content_purposes` (`media_content_purpose_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_specifications_mime_types` FOREIGN KEY (`mime_type_id`) REFERENCES `mime_types` (`mime_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_specifications_types` FOREIGN KEY (`media_type_id`) REFERENCES `media_types` (`media_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='this table for media specification';

/*Data for the table `media_specifications` */

insert  into `media_specifications`(`media_specification_id`,`media_specifications_name`,`media_type_id`,`mime_type_id`,`media_content_purpose_id`,`width`,`height`,`bitrate`,`duration`,`media_location`,`is_source`,`parent_specification_id`) values (1,'Videos',1,2,1,100,100,0,0,0,1,-1),(2,'Videos',1,5,2,720,480,320,0,0,1,-1);

/*Table structure for table `media_subgroups` */

DROP TABLE IF EXISTS `media_subgroups`;

CREATE TABLE `media_subgroups` (
  `subgroup_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_group_id` int(11) NOT NULL,
  `child_group_id` int(11) NOT NULL,
  `group_order` int(11) NOT NULL DEFAULT '0',
  `created_time` datetime DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`subgroup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `media_subgroups` */

/*Table structure for table `media_tags` */

DROP TABLE IF EXISTS `media_tags`;

CREATE TABLE `media_tags` (
  `media_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_tag_id`),
  KEY `fk_media_tags_media_idx` (`media_id`),
  KEY `fk_media_tags_tag_idx` (`tag_id`),
  CONSTRAINT `fk_media_tags_media` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_tags_tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `media_tags` */

/*Table structure for table `media_types` */

DROP TABLE IF EXISTS `media_types`;

CREATE TABLE `media_types` (
  `media_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_type_name` varchar(45) NOT NULL COMMENT 'stores information about media types for internal purpose',
  `media_type_title` varchar(45) NOT NULL COMMENT 'this column used for title which will be displayed external',
  `created_time` datetime NOT NULL COMMENT 'stores created time',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'stores last modified time',
  PRIMARY KEY (`media_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media types';

/*Data for the table `media_types` */

insert  into `media_types`(`media_type_id`,`media_type_name`,`media_type_title`,`created_time`,`modified_time`) values (1,'Videos','Videos','2013-10-10 23:21:41','2013-10-10 23:21:41');

/*Table structure for table `mime_types` */

DROP TABLE IF EXISTS `mime_types`;

CREATE TABLE `mime_types` (
  `mime_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `mime_type` varchar(45) NOT NULL,
  `media_extension` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mime_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `mime_types` */

insert  into `mime_types`(`mime_type_id`,`mime_type`,`media_extension`,`created_time`,`modified_time`) values (1,'img/jpeg','jpg','2013-10-10 23:21:41','2013-10-12 12:34:42'),(2,'img/gif','gif','2013-10-10 23:21:41','2013-10-12 12:34:42'),(3,'img/png','png','2013-10-10 23:21:41','2013-10-12 12:34:42'),(4,'video/3gpp','3gp','2013-10-10 23:21:41','2013-10-12 12:34:42'),(5,'video/mp4','mp4','2013-10-10 23:21:41','2013-10-12 12:34:42');

/*Table structure for table `tags` */

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tags` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
