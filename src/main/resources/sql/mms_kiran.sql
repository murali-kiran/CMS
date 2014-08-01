/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.19 : Database - MMS
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`MMS` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `MMS`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  KEY `username` (`username`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `authorities` */

/*Table structure for table `banner_content` */

DROP TABLE IF EXISTS `banner_content`;

CREATE TABLE `banner_content` (
  `banner_m_id` int(11) NOT NULL,
  `banner_id` int(11) NOT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `path` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`banner_m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `banner_content` */

/*Table structure for table `banners` */

DROP TABLE IF EXISTS `banners`;

CREATE TABLE `banners` (
  `banner_id` int(11) NOT NULL,
  `media_provider_id` int(11) NOT NULL,
  `banner_title` varchar(75) DEFAULT NULL,
  `banner_description` varchar(256) DEFAULT NULL,
  `banner_start_time` datetime DEFAULT NULL,
  `banner_end_time` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `banners` */

/*Table structure for table `banners_spects` */

DROP TABLE IF EXISTS `banners_spects`;

CREATE TABLE `banners_spects` (
  `banner_s_id` int(11) NOT NULL,
  `media_provider_id` int(11) NOT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`banner_s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `banners_spects` */

/*Table structure for table `capture_wap_details` */

DROP TABLE IF EXISTS `capture_wap_details`;

CREATE TABLE `capture_wap_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msisdn` bigint(20) DEFAULT NULL,
  `host` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `user_agent` varchar(800) COLLATE latin1_general_ci DEFAULT NULL,
  `referal_url` varchar(1000) COLLATE latin1_general_ci DEFAULT NULL,
  `request_url` varchar(1000) COLLATE latin1_general_ci DEFAULT NULL,
  `query_string` varchar(800) COLLATE latin1_general_ci DEFAULT NULL,
  `channel` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `other_details` varchar(4100) COLLATE latin1_general_ci DEFAULT NULL,
  `session_id` varchar(45) COLLATE latin1_general_ci DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` varchar(45) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `msisdn` (`msisdn`),
  KEY `createda_time` (`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

/*Data for the table `capture_wap_details` */

/*Table structure for table `circles` */

DROP TABLE IF EXISTS `circles`;

CREATE TABLE `circles` (
  `circle_id` int(11) NOT NULL AUTO_INCREMENT,
  `circles_name` varchar(45) NOT NULL,
  `circle_code` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`circle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `circles` */

/*Table structure for table `group_authorities` */

DROP TABLE IF EXISTS `group_authorities`;

CREATE TABLE `group_authorities` (
  `group_id` int(12) NOT NULL,
  `authority` varchar(200) NOT NULL,
  KEY `group_id` (`group_id`),
  CONSTRAINT `group_authorities_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `group_authorities` */

insert  into `group_authorities`(`group_id`,`authority`) values (3,'ROLE_USER'),(4,'ROLE_ADMIN');

/*Table structure for table `group_members` */

DROP TABLE IF EXISTS `group_members`;

CREATE TABLE `group_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group_members_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `group_members` */

insert  into `group_members`(`id`,`username`,`group_id`) values (3,'kiranuser@sumadga.com',3),(4,'kiranadmin@sumadga.com',4),(5,'murliuser@sumadga.com',3),(6,'murliadmin@sumadga.com',4);

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `groups` */

insert  into `groups`(`id`,`group_name`) values (3,'Users'),(4,'Administrators');

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

insert  into `languages`(`language_id`,`language_name`,`description`,`created_time`,`modified_time`) values (1,'English','english','2013-10-11 22:32:08','2013-10-12 08:02:08');

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
  `media_provider_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`media_id`),
  KEY `fk_media_media_cycles_idx` (`media_cycle_id`),
  KEY `fk_media_media_process_states_idx` (`media_process_state_id`),
  KEY `fk_media_media_types_idx` (`media_type_id`),
  KEY `fk_media_language_idx` (`language_id`),
  KEY `fk_media_media_provider_id` (`media_provider_id`),
  CONSTRAINT `fk_media_language` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_cycles` FOREIGN KEY (`media_cycle_id`) REFERENCES `media_cycles` (`media_cycle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_process_states` FOREIGN KEY (`media_process_state_id`) REFERENCES `media_process_states` (`media_process_state_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_provider_id` FOREIGN KEY (`media_provider_id`) REFERENCES `media_providers` (`media_provider_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_media_types` FOREIGN KEY (`media_type_id`) REFERENCES `media_types` (`media_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media';

/*Data for the table `media` */

insert  into `media`(`media_id`,`media_name`,`media_title`,`description`,`created_time`,`modified_time`,`media_cycle_id`,`media_type_id`,`media_process_state_id`,`media_start_time`,`media_end_time`,`language_id`,`media_provider_id`) values (1,'test1','test1','test','2013-10-12 19:00:35','2013-10-25 14:58:58',3,1,2,'2013-10-09 00:00:00','2013-10-09 00:00:00',1,1),(2,'test2','test2','test','2013-10-12 19:07:17','2013-10-15 00:41:58',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1,1),(3,'test3','test3','test test','2013-10-12 19:00:35','2013-10-27 21:00:37',2,1,2,'2013-10-09 00:00:00','2013-10-09 00:00:00',1,1),(4,'test4','test4','test','2013-10-12 19:07:17','2013-10-14 19:11:58',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1,1),(5,'test5','test5','test','2013-10-12 19:00:35','2013-10-14 19:11:50',2,1,2,'2013-10-09 00:00:00','2013-10-17 00:00:00',1,1),(6,'test6','test6','test','2013-10-12 19:07:17','2013-10-14 19:11:58',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1,1),(7,'test7','test7','test','2013-10-12 19:00:35','2013-10-14 19:11:50',2,1,2,'2013-10-09 00:00:00','2013-10-17 00:00:00',1,1),(8,'test8','test8','test','2013-10-12 19:07:17','2013-10-14 19:11:58',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1,1),(9,'test9','test9','test','2013-10-12 19:00:35','2013-10-14 19:11:50',2,1,2,'2013-10-09 00:00:00','2013-10-17 00:00:00',1,1),(10,'test10','test10','test','2013-10-12 19:07:17','2013-10-14 19:11:58',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1,1),(11,'tst1','test1','test','2013-10-21 00:55:01','2013-10-21 00:55:01',2,1,2,'2013-10-15 00:00:00','2013-10-29 00:00:00',1,1),(12,'tst1','test1','test','2013-10-21 01:04:05','2013-10-21 01:04:05',2,1,2,'2013-10-07 00:00:00','2013-10-31 00:00:00',1,1),(13,'test','kiran test','test','2013-10-21 21:37:53','2013-10-21 21:37:54',2,1,3,'2013-10-14 00:00:00','2013-10-30 00:00:00',1,1),(14,'murali','test-murali','test','2013-10-21 21:46:53','2013-10-21 21:46:54',2,1,3,'2013-10-22 00:00:00','2013-10-31 00:00:00',1,1),(15,'kiran','kiran','test','2013-10-21 22:19:26','2013-10-21 22:19:26',2,1,3,'2013-10-15 00:00:00','2013-10-31 00:00:00',1,1),(16,'test','test','test','2013-10-25 11:53:46','2013-10-25 11:53:46',2,1,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(17,'test','test','test','2013-10-25 12:57:39','2013-10-25 12:57:39',2,1,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(18,'test','tst','test','2013-10-25 13:09:43','2013-10-25 13:09:43',2,1,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(19,'test','test','test','2013-10-25 13:16:47','2013-10-25 13:16:50',2,1,3,'2013-10-01 00:00:00','2013-10-26 00:00:00',1,1),(20,'test-wallpaper','wal-test','test test','2013-10-26 22:47:41','2013-10-27 21:00:15',2,2,3,'2013-10-09 00:00:00','2013-10-09 00:00:00',1,1),(21,'wal','test-wal','test','2013-10-26 22:53:53','2013-10-26 22:54:02',2,2,3,'2013-10-09 00:00:00','2013-10-31 00:00:00',1,1),(22,'test animation','test animation','desc ','2013-10-27 19:27:41','2013-10-27 19:27:42',2,3,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(23,'test wallpaper','test wallpaper','test wallpaper','2013-10-27 20:24:11','2013-10-27 20:24:14',2,2,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(24,'test animation1','test animation1','test animation1','2013-10-27 21:02:55','2013-10-27 21:02:55',2,3,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(25,'test wallpaper12','test wallpaper12','test wallpaper12','2013-10-27 21:04:39','2013-10-27 21:04:42',2,2,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(26,'test video','test video','test video','2013-10-27 21:14:48','2013-10-27 21:14:52',2,1,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(27,'test wallpaper123','test wallpaper123','test wallpaper123','2013-10-27 21:16:04','2013-10-27 21:16:07',2,2,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(28,'test','testkiran','test','2013-10-27 21:25:14','2013-10-27 21:25:22',2,3,3,'2013-10-15 00:00:00','2013-10-24 00:00:00',1,1),(29,'kiran','kiran','test','2013-10-27 21:30:31','2013-10-27 21:30:39',2,2,3,'2013-10-09 00:00:00','2013-10-31 00:00:00',1,1),(30,'test animation','test animation','test animation','2013-10-27 22:17:29','2013-10-27 22:17:32',2,3,3,'2013-10-01 00:00:00','2013-10-31 00:00:00',1,1),(31,'Aamir_khan','Aamir_khan','Test Wallpaper','2013-10-28 13:19:01','2013-10-28 13:23:19',2,2,3,'2013-10-28 00:00:00','2013-10-28 00:00:00',1,1),(32,'Ranbir','Ranbir','Test Animation','2013-10-28 13:20:49','2013-10-28 13:22:54',2,3,3,'2013-10-28 00:00:00','2013-10-28 00:00:00',1,1),(33,'AUM_BB_H3_whendy_sexycar ','AUM_BB_H3_whendy_sexycar ','Test_Video','2013-10-28 13:44:25','2013-10-28 13:44:26',2,1,3,'2013-10-28 00:00:00','2013-10-31 00:00:00',1,1),(34,'srikanth','srikanth','test','2013-11-02 12:41:05','2013-11-02 12:41:13',2,2,3,'2013-11-04 00:00:00','2013-11-30 00:00:00',1,1),(35,'murali','murali','test','2013-11-10 12:22:27','2013-11-10 12:22:31',2,1,3,'2013-11-07 00:00:00','2013-11-30 00:00:00',1,1),(36,'text','test text','','2014-07-09 06:49:48','2014-07-09 06:49:50',2,4,3,'2014-07-09 00:00:00','2014-07-23 00:00:00',1,1),(37,'test','sanju test','test','2014-07-09 07:19:32','2014-07-09 07:19:32',2,4,3,'2014-07-09 00:00:00','2014-07-09 00:00:00',1,1);

/*Table structure for table `media_app_contents` */

DROP TABLE IF EXISTS `media_app_contents`;

CREATE TABLE `media_app_contents` (
  `media_app_content_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_id` int(11) NOT NULL,
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `os_id` int(11) NOT NULL,
  `mime_type_id` int(11) NOT NULL,
  `storage_path` varchar(1024) NOT NULL,
  `purpose` varchar(20) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`media_app_content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `media_app_contents` */

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

insert  into `media_content_purposes`(`media_content_purpose_id`,`media_content_purpose`,`created_time`,`modified_time`) values (1,'preview','2013-10-12 12:35:38','2013-10-12 22:05:38'),(2,'media','2013-10-12 12:35:38','2013-10-12 22:06:09');

/*Table structure for table `media_contents` */

DROP TABLE IF EXISTS `media_contents`;

CREATE TABLE `media_contents` (
  `media_content_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_id` int(11) NOT NULL,
  `media_specification_id` int(11) NOT NULL,
  `storage_path` varchar(1024) NOT NULL,
  `md5` varchar(45) NOT NULL,
  `text_message` varchar(7000) DEFAULT NULL,
  PRIMARY KEY (`media_content_id`),
  KEY `fk_media_contents_media_idx` (`media_id`),
  KEY `fk_media_contents_spec_idx` (`media_specification_id`),
  CONSTRAINT `fk_media_contents_media` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_contents_spec` FOREIGN KEY (`media_specification_id`) REFERENCES `media_specifications` (`media_specification_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT='this table stores media contents files';

/*Data for the table `media_contents` */

insert  into `media_contents`(`media_content_id`,`media_id`,`media_specification_id`,`storage_path`,`md5`,`text_message`) values (1,12,1,'0/12/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(2,12,2,'0/12/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(3,13,1,'0/13/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(4,13,2,'0/13/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(5,14,1,'0/14/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(6,14,2,'0/14/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(7,15,1,'0/15/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(8,15,2,'0/15/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(9,16,1,'0/16/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(10,16,2,'0/16/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(11,17,1,'0/17/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(12,17,2,'0/17/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(13,18,1,'0/18/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(14,18,2,'0/18/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(15,19,1,'0/19/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(16,19,2,'0/19/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(17,19,3,'0/19/19_preview_50_50_0k.gif','bfafdb1b250019caefa0fdd0c17324b2',NULL),(18,19,4,'0/19/19_media_176_144_180k.mp4','61bc946676b4b5f4fd38201c5d785fa3',NULL),(19,20,5,'0/20/sample_500_preview_500_500_0k.gif','93fb56066def6c88911509645613ec50',NULL),(20,20,6,'0/20/20_preview_400_400_0k.gif','dfcfe231a3bf3c817717fb2e22cc362c',NULL),(21,20,7,'0/20/20_preview_300_300_0k.gif','2b9dd7dbf886d188133bda14b25ac198',NULL),(22,20,8,'0/20/20_preview_200_200_0k.gif','8e71802d28c556828253d1137e0d7a44',NULL),(23,20,9,'0/20/20_preview_100_100_0k.gif','05b7c0f5248ace996fbc75bf25f90257',NULL),(24,20,10,'0/20/20_preview_50_50_0k.gif','20bb8d06c4862cd4b29ee08ba2ece1f5',NULL),(25,20,11,'0/20/20_preview_400_400_0k.png','',NULL),(26,20,12,'0/20/20_preview_300_300_0k.png','',NULL),(27,20,13,'0/20/20_preview_200_200_0k.png','',NULL),(28,20,14,'0/20/20_preview_100_100_0k.png','',NULL),(29,20,15,'0/20/20_preview_50_50_0k.png','',NULL),(30,21,5,'0/21/sample_500_preview_500_500_0k.gif','93fb56066def6c88911509645613ec50',NULL),(31,21,6,'0/21/21_preview_400_400_0k.gif','dfcfe231a3bf3c817717fb2e22cc362c',NULL),(32,21,7,'0/21/21_preview_300_300_0k.gif','2b9dd7dbf886d188133bda14b25ac198',NULL),(33,21,8,'0/21/21_preview_200_200_0k.gif','8e71802d28c556828253d1137e0d7a44',NULL),(34,21,9,'0/21/21_preview_100_100_0k.gif','05b7c0f5248ace996fbc75bf25f90257',NULL),(35,21,10,'0/21/21_preview_50_50_0k.gif','20bb8d06c4862cd4b29ee08ba2ece1f5',NULL),(36,21,11,'0/21/21_preview_400_400_0k.png','cb827b4700c2da220f2915055546c9cd',NULL),(37,21,12,'0/21/21_preview_300_300_0k.png','6d3b28f295a1a68c081bf03b6cf84e5d',NULL),(38,21,13,'0/21/21_preview_200_200_0k.png','650592202cca09590fb6a19a805b4fe2',NULL),(39,21,14,'0/21/21_preview_100_100_0k.png','0db585d54dbe26b262dc36b506d9dca4',NULL),(40,21,15,'0/21/21_preview_50_50_0k.png','7a07a6d7029a805f85e701e35dcbc479',NULL),(41,22,16,'0/22/image_preview_500_500_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(42,23,5,'0/23/image_preview_500_500_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(43,23,6,'0/23/23_preview_400_400_0k.gif','2c6f2f67eeecd998a9a2ddd33f6c2008',NULL),(44,23,7,'0/23/23_preview_300_300_0k.gif','5d0ff05c058053cd8e2546394e25e1a4',NULL),(45,23,8,'0/23/23_preview_200_200_0k.gif','cbc43b6f020f228f45ae5197f55edb01',NULL),(46,23,9,'0/23/23_preview_100_100_0k.gif','08ac951d88526346be8b006858748d63',NULL),(47,23,10,'0/23/23_preview_50_50_0k.gif','e0cfeef4ccc6094fdda22618e22f603a',NULL),(48,23,11,'0/23/23_preview_400_400_0k.png','2f8de6e08bdfd59c91957a4b9ee5b1c7',NULL),(49,23,12,'0/23/23_preview_300_300_0k.png','c9de3ba2ca6c1cb1c2d763d95f0f15f4',NULL),(50,23,13,'0/23/23_preview_200_200_0k.png','fa38988ffe5b997fd2a06b2a874b92db',NULL),(51,23,14,'0/23/23_preview_100_100_0k.png','b92be359727099bfe245ab0621ea81f1',NULL),(52,23,15,'0/23/23_preview_50_50_0k.png','7c6cefeeaceb74b957cfa95476b8c593',NULL),(53,24,16,'0/24/image_preview_500_500_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(54,25,5,'0/25/image_preview_500_500_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(55,25,6,'0/25/25_preview_400_400_0k.gif','2c6f2f67eeecd998a9a2ddd33f6c2008',NULL),(56,25,7,'0/25/25_preview_300_300_0k.gif','5d0ff05c058053cd8e2546394e25e1a4',NULL),(57,25,8,'0/25/25_preview_200_200_0k.gif','cbc43b6f020f228f45ae5197f55edb01',NULL),(58,25,9,'0/25/25_preview_100_100_0k.gif','08ac951d88526346be8b006858748d63',NULL),(59,25,10,'0/25/25_preview_50_50_0k.gif','e0cfeef4ccc6094fdda22618e22f603a',NULL),(60,25,11,'0/25/25_preview_400_400_0k.png','42f4e86838df55d68dda1adccb112c6f',NULL),(61,25,12,'0/25/25_preview_300_300_0k.png','d65bed05f04698c667cce2bdd023d0d9',NULL),(62,25,13,'0/25/25_preview_200_200_0k.png','16dd98faa2e4032515da2164acd4032f',NULL),(63,25,14,'0/25/25_preview_100_100_0k.png','d56444c2d930eb5e1700e14f85f240b3',NULL),(64,25,15,'0/25/25_preview_50_50_0k.png','9565c9cdb5b55fb5b91c64f3291528a7',NULL),(65,26,1,'0/26/image_preview_100_100_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(66,26,2,'0/26/video_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(67,26,3,'0/26/26_preview_50_50_0k.gif','e0cfeef4ccc6094fdda22618e22f603a',NULL),(68,26,4,'0/26/26_media_176_144_180k.mp4','61bc946676b4b5f4fd38201c5d785fa3',NULL),(69,27,5,'0/27/image_preview_500_500_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(70,27,6,'0/27/27_preview_400_400_0k.gif','2c6f2f67eeecd998a9a2ddd33f6c2008',NULL),(71,27,7,'0/27/27_preview_300_300_0k.gif','5d0ff05c058053cd8e2546394e25e1a4',NULL),(72,27,8,'0/27/27_preview_200_200_0k.gif','cbc43b6f020f228f45ae5197f55edb01',NULL),(73,27,9,'0/27/27_preview_100_100_0k.gif','08ac951d88526346be8b006858748d63',NULL),(74,27,10,'0/27/27_preview_50_50_0k.gif','e0cfeef4ccc6094fdda22618e22f603a',NULL),(75,27,11,'0/27/27_preview_400_400_0k.png','0ea48aa2583a20ffea102ba9b35ac68f',NULL),(76,27,12,'0/27/27_preview_300_300_0k.png','b547b06ab5895d3a01ca8f67bde9f816',NULL),(77,27,13,'0/27/27_preview_200_200_0k.png','63b2aef7ba7a328430e525a72413e6ed',NULL),(78,27,14,'0/27/27_preview_100_100_0k.png','89ae0b925864908e4b490b1d356b0221',NULL),(79,27,15,'0/27/27_preview_50_50_0k.png','846200bca6b3458cb5e42e621644b32c',NULL),(80,28,16,'0/28/sample_500_preview_500_500_0k.gif','93fb56066def6c88911509645613ec50',NULL),(81,28,17,'0/28/28_preview_400_400_0k.gif','dfcfe231a3bf3c817717fb2e22cc362c',NULL),(82,28,18,'0/28/28_preview_300_300_0k.gif','2b9dd7dbf886d188133bda14b25ac198',NULL),(83,28,19,'0/28/28_preview_200_200_0k.gif','8e71802d28c556828253d1137e0d7a44',NULL),(84,28,20,'0/28/28_preview_100_100_0k.gif','05b7c0f5248ace996fbc75bf25f90257',NULL),(85,28,21,'0/28/28_preview_50_50_0k.gif','20bb8d06c4862cd4b29ee08ba2ece1f5',NULL),(86,29,5,'0/29/sample_500_preview_500_500_0k.gif','93fb56066def6c88911509645613ec50',NULL),(87,29,6,'0/29/29_preview_400_400_0k.gif','dfcfe231a3bf3c817717fb2e22cc362c',NULL),(88,29,7,'0/29/29_preview_300_300_0k.gif','2b9dd7dbf886d188133bda14b25ac198',NULL),(89,29,8,'0/29/29_preview_200_200_0k.gif','8e71802d28c556828253d1137e0d7a44',NULL),(90,29,9,'0/29/29_preview_100_100_0k.gif','05b7c0f5248ace996fbc75bf25f90257',NULL),(91,29,10,'0/29/29_preview_50_50_0k.gif','20bb8d06c4862cd4b29ee08ba2ece1f5',NULL),(92,29,11,'0/29/29_preview_400_400_0k.png','a98dd6ba590f9cb0322b9e8464ad3556',NULL),(93,29,12,'0/29/29_preview_300_300_0k.png','891d64ac4ac5ee4b0b5e9ecc24d828cd',NULL),(94,29,13,'0/29/29_preview_200_200_0k.png','2b8ad3e0b0080159fa2150419857856f',NULL),(95,29,14,'0/29/29_preview_100_100_0k.png','ce27c1a70f85d1a96c32df2dd488a2b6',NULL),(96,29,15,'0/29/29_preview_50_50_0k.png','5d632f9c2651110b789ad58fcc826bd3',NULL),(97,30,16,'0/30/image_preview_500_500_0k.jpg','6f04d2fbd83b303bca39ef0d459c055e',NULL),(98,30,17,'0/30/30_preview_400_400_0k.gif','2c6f2f67eeecd998a9a2ddd33f6c2008',NULL),(99,30,18,'0/30/30_preview_300_300_0k.gif','5d0ff05c058053cd8e2546394e25e1a4',NULL),(100,30,19,'0/30/30_preview_200_200_0k.gif','cbc43b6f020f228f45ae5197f55edb01',NULL),(101,30,20,'0/30/30_preview_100_100_0k.gif','08ac951d88526346be8b006858748d63',NULL),(102,30,21,'0/30/30_preview_50_50_0k.gif','e0cfeef4ccc6094fdda22618e22f603a',NULL),(103,31,5,'0/31/Aamir_khan_preview_500_500_0k.jpg','bae43f2bf81291acc815550379eb49d2',NULL),(104,31,6,'0/31/31_preview_400_400_0k.gif','53b557e7841185d3df5f841273a2f10b',NULL),(105,31,7,'0/31/31_preview_300_300_0k.gif','4414e92143c77b1544eddf2d829ed24c',NULL),(106,31,8,'0/31/31_preview_200_200_0k.gif','c18e572f679f9513ff261a5529f1efc0',NULL),(107,31,9,'0/31/31_preview_100_100_0k.gif','46850cebfbd85e9e055ddb011b678108',NULL),(108,31,10,'0/31/31_preview_50_50_0k.gif','9ac6ce0511a318ccf32e1c413718db17',NULL),(109,31,11,'0/31/31_preview_400_400_0k.png','0657b7134ddeae1ca6902406e7b4f31e',NULL),(110,31,12,'0/31/31_preview_300_300_0k.png','96713f75d9487a00acc25ec3f8eaf763',NULL),(111,31,13,'0/31/31_preview_200_200_0k.png','603cccae8ba0b6fece06f7077d395300',NULL),(112,31,14,'0/31/31_preview_100_100_0k.png','d968e30e37f6c1f834ff6320c10e49ad',NULL),(113,31,15,'0/31/31_preview_50_50_0k.png','ed89107823f462bb6377ea1aa5115f14',NULL),(114,32,16,'0/32/_preview_500_500_0k','d41d8cd98f00b204e9800998ecf8427e',NULL),(115,33,1,'0/33/AUM_BB_H3_whendy_sexycar_preview_100_100_0k.gif','70203a34824b59c9f789eb004fff7fdc',NULL),(116,33,2,'0/33/AUM_BB_H3_whendy_sexycar _media_720_480_320k.mp4','6d0e60b345024b3b968005151ccab8ed',NULL),(117,33,3,'0/33/33_preview_50_50_0k.gif','f8c10ff40e2c9b0dbad5fb4e34bfd7ac',NULL),(118,34,5,'0/34/sample_500_preview_500_500_0k.gif','93fb56066def6c88911509645613ec50',NULL),(119,34,6,'0/34/34_preview_400_400_0k.gif','dfcfe231a3bf3c817717fb2e22cc362c',NULL),(120,34,7,'0/34/34_preview_300_300_0k.gif','2b9dd7dbf886d188133bda14b25ac198',NULL),(121,34,8,'0/34/34_preview_200_200_0k.gif','8e71802d28c556828253d1137e0d7a44',NULL),(122,34,9,'0/34/34_preview_100_100_0k.gif','05b7c0f5248ace996fbc75bf25f90257',NULL),(123,34,10,'0/34/34_preview_50_50_0k.gif','20bb8d06c4862cd4b29ee08ba2ece1f5',NULL),(124,34,11,'0/34/34_preview_400_400_0k.png','adec02d0f5cee6d73d9263f55ea3f7a8',NULL),(125,34,12,'0/34/34_preview_300_300_0k.png','3a2f1d54441c7c8119eb9b1638c1c36d',NULL),(126,34,13,'0/34/34_preview_200_200_0k.png','31b19da6d21806f2f511584220b32b78',NULL),(127,34,14,'0/34/34_preview_100_100_0k.png','1ce4908a5a13bc573bb52661c02f6f23',NULL),(128,34,15,'0/34/34_preview_50_50_0k.png','19538e64b193ce49b50a3b75217151f8',NULL),(129,35,1,'0/35/100x100_preview_100_100_0k_preview_100_100_0k.gif','129decfa4ac89c15dbd7e4a8d5b14aed',NULL),(130,35,2,'0/35/video_media_720_480_320k_media_720_480_320k.3gp','e4cb7e0f4e0b35860c3c6a37268c76e0',NULL),(131,35,3,'0/35/35_preview_50_50_0k.gif','bfafdb1b250019caefa0fdd0c17324b2',NULL),(132,35,4,'0/35/35_media_176_144_180k.mp4','61bc946676b4b5f4fd38201c5d785fa3',NULL),(133,36,22,'','','kiran text'),(134,37,22,'','','test file for testing');

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

insert  into `media_cycles`(`media_cycle_id`,`media_cycle_state`,`created_time`,`modified_time`) values (1,'Initial','2013-10-11 22:33:52','2013-10-12 08:03:52'),(2,'Transcoded','2013-10-11 22:34:37','2013-10-12 08:07:20'),(3,'Published','2013-10-11 22:35:35','2013-10-12 08:05:35'),(4,'Removed','2013-10-11 22:36:11','2013-10-12 08:06:11');

/*Table structure for table `media_downloads` */

DROP TABLE IF EXISTS `media_downloads`;

CREATE TABLE `media_downloads` (
  `media_download_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_id` int(11) NOT NULL,
  `msisdn` bigint(20) NOT NULL,
  `media_id` int(11) NOT NULL,
  `media_content_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `is_download_success` tinyint(1) NOT NULL COMMENT '0 for fail, 1 for success',
  `download_time` datetime NOT NULL,
  `channel` varchar(100) NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_agent` varchar(800) NOT NULL,
  `sessionId` varchar(800) NOT NULL,
  `remarks` varchar(800) NOT NULL,
  PRIMARY KEY (`media_download_id`),
  KEY `fk_media_downloads_purchase_idx` (`purchase_id`),
  KEY `fk_media_downloads_service_idx` (`service_id`),
  KEY `fk_media_downloads_media_idx` (`media_id`),
  KEY `fk_media_downloads_media_content_idx` (`media_content_id`),
  CONSTRAINT `fk_media_downloads_media` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_downloads_media_content` FOREIGN KEY (`media_content_id`) REFERENCES `media_contents` (`media_content_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_downloads_purchase` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`purchase_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_downloads_service` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `media_downloads` */

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='This table for media for media groups';

/*Data for the table `media_group_media` */

insert  into `media_group_media`(`media_group_media_id`,`media_id`,`media_group_id`,`media_order`,`created_time`,`modified_time`) values (6,2,1,0,'2013-10-14 11:27:25','2013-10-14 20:57:25'),(7,1,2,0,'2013-10-14 13:08:13','2013-10-14 22:38:13'),(8,2,2,1,'2013-10-14 13:08:16','2013-10-14 22:38:16'),(9,1,6,3,'2013-10-14 15:06:05','2013-10-22 20:56:14'),(10,2,6,2,'2013-10-14 15:06:08','2013-10-22 20:56:14'),(13,1,7,6,'2013-10-19 14:00:57','2013-10-20 19:57:29'),(14,2,7,2,'2013-10-20 19:50:54','2013-10-20 19:57:28'),(15,4,7,1,'2013-10-20 19:56:55','2013-10-20 19:57:28'),(16,5,7,4,'2013-10-20 19:56:55','2013-10-20 19:57:28'),(17,6,7,3,'2013-10-20 19:56:55','2013-10-20 19:57:28'),(18,7,7,5,'2013-10-20 19:56:56','2013-10-20 19:57:28'),(19,3,1,0,'2013-10-20 23:30:35','2013-10-20 23:30:35'),(20,11,6,5,'2013-10-22 20:55:29','2013-10-22 20:56:14'),(22,13,6,4,'2013-10-22 20:55:29','2013-10-22 20:56:14'),(23,14,6,6,'2013-10-22 20:55:29','2013-10-22 20:56:14'),(24,15,6,1,'2013-10-22 20:55:29','2013-10-22 20:56:14'),(25,10,8,1,'2013-10-22 20:57:44','2013-10-27 20:23:26'),(26,11,8,6,'2013-10-22 20:57:44','2013-10-27 20:23:26'),(27,12,8,5,'2013-10-22 20:57:44','2013-10-27 20:23:26'),(28,13,8,4,'2013-10-22 20:57:44','2013-10-27 20:23:26'),(29,14,8,3,'2013-10-22 20:57:44','2013-10-27 20:23:26'),(30,15,8,2,'2013-10-22 20:57:44','2013-10-27 20:23:26');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media grouping ';

/*Data for the table `media_groups` */

insert  into `media_groups`(`media_group_id`,`media_group_name`,`media_group_title`,`media_group_description`,`media_group_preview_id`,`created_time`,`modified_time`) values (1,'test category','test category','test desc',1,'2013-10-12 23:03:26','2013-10-13 08:33:26'),(2,'test category1','test category1','test category1',1,'2013-10-12 23:36:00','2013-10-13 09:06:00'),(3,'test category1','test category1','test category1',1,'2013-10-12 23:39:02','2013-10-13 09:09:02'),(4,'test category4','test category4','category4',1,'2013-10-12 23:45:59','2013-10-13 09:15:59'),(5,'test category4','test category4','category4',1,'2013-10-12 23:48:18','2013-10-13 09:18:18'),(6,'test category4','test category4','category4',1,'2013-10-12 23:53:33','2013-10-13 09:23:33'),(7,'New Group','New Group','',1,'2013-10-14 16:16:47','2013-10-15 01:46:47'),(8,'Kiran Group','Kiran Group','desc',15,'2013-10-22 20:57:17','2013-10-22 20:57:17');

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

insert  into `media_process_states`(`media_process_state_id`,`media_process_state_name`,`description`,`created_time`,`modified_time`) values (1,'not started','Transcode not started','2013-10-12 15:17:47','2013-10-13 00:47:47'),(2,'started','Transcode started','2013-10-12 15:18:11','2013-10-13 00:48:11'),(3,'completed','Transcode completed','2013-10-12 15:21:07','2013-10-13 00:51:07');

/*Table structure for table `media_providers` */

DROP TABLE IF EXISTS `media_providers`;

CREATE TABLE `media_providers` (
  `media_provider_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_provider_name` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_provider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `media_providers` */

insert  into `media_providers`(`media_provider_id`,`media_provider_name`,`created_time`,`modified_time`) values (1,'peppermint','2013-11-18 00:00:00','2013-11-10 12:18:02'),(2,'sample','2013-11-10 00:00:00','2013-11-10 12:39:40');

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
  `transcodingCommand` varchar(900) DEFAULT NULL,
  `is_file` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`media_specification_id`),
  KEY `fk_media_specifications_types_idx` (`media_type_id`),
  KEY `fk_media_specifications_mime_types_idx` (`mime_type_id`),
  KEY `fk_media_specifications_content_purpose_idx` (`media_content_purpose_id`),
  CONSTRAINT `fk_media_specifications_content_purpose` FOREIGN KEY (`media_content_purpose_id`) REFERENCES `media_content_purposes` (`media_content_purpose_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_specifications_mime_types` FOREIGN KEY (`mime_type_id`) REFERENCES `mime_types` (`mime_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_media_specifications_types` FOREIGN KEY (`media_type_id`) REFERENCES `media_types` (`media_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='this table for media specification';

/*Data for the table `media_specifications` */

insert  into `media_specifications`(`media_specification_id`,`media_specifications_name`,`media_type_id`,`mime_type_id`,`media_content_purpose_id`,`width`,`height`,`bitrate`,`duration`,`media_location`,`is_source`,`parent_specification_id`,`transcodingCommand`,`is_file`) values (1,'preview 100x100',1,2,1,100,100,0,0,0,1,-1,NULL,1),(2,'video media file',1,5,2,720,480,320,0,0,1,-1,NULL,1),(3,'preview 50x50',1,2,1,50,50,0,0,0,0,1,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##50##@DESTINATION@',1),(4,'Video',1,5,2,176,144,180,0,0,0,2,'/usr/local/bin/video_conversion.sh##-y##-i##@SOURCE@##-vlevel##10##-g##25##-b##180k##-r##12##-s##176x144##-vcodec##mpeg4##-acodec##libfaac##-ac##1##-ab##12.2k##-ar##8000##@DESTINATION@',1),(5,'content 500x500',2,2,1,500,500,0,0,0,1,-1,NULL,1),(6,'content 400x400',2,2,1,400,400,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##400x400##@DESTINATION@',1),(7,'content 300x300',2,2,1,300,300,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##300x300##@DESTINATION@',1),(8,'content 200x200',2,2,1,200,200,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##200x200##@DESTINATION@',1),(9,'content 100x100',2,2,1,100,100,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##100x100##@DESTINATION@',1),(10,'content 50x50',2,2,1,50,50,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##50x50##@DESTINATION@',1),(11,'content 400x400',2,3,1,400,400,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##400x400##@DESTINATION@',1),(12,'content 300x300',2,3,1,300,300,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##300x300##@DESTINATION@',1),(13,'content 200x200',2,3,1,200,200,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##200x200##@DESTINATION@',1),(14,'content 100x100',2,3,1,100,100,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##100x100##@DESTINATION@',1),(15,'content 50x50',2,3,1,50,50,0,0,0,0,5,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##50x50##@DESTINATION@',1),(16,'content 500x500',3,2,1,500,500,0,0,0,1,-1,NULL,1),(17,'content 400x400',3,2,1,400,400,0,0,0,0,16,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##400x400##@DESTINATION@',1),(18,'content 300x300',3,2,1,300,300,0,0,0,0,16,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##300x300##@DESTINATION@',1),(19,'content 200x200',3,2,1,200,200,0,0,0,0,16,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##200x200##@DESTINATION@',1),(20,'content 100x100',3,2,1,100,100,0,0,0,0,16,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##100x100##@DESTINATION@',1),(21,'content 50x50',3,2,1,50,50,0,0,0,0,16,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##50x50##@DESTINATION@',1),(22,'Text',4,6,1,1500,1500,0,0,0,1,-1,NULL,0);

/*Table structure for table `media_specifications_temp` */

DROP TABLE IF EXISTS `media_specifications_temp`;

CREATE TABLE `media_specifications_temp` (
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
  `transcodingCommand` varchar(900) DEFAULT NULL,
  PRIMARY KEY (`media_specification_id`),
  KEY `fk_media_specifications_types_idx` (`media_type_id`),
  KEY `fk_media_specifications_mime_types_idx` (`mime_type_id`),
  KEY `fk_media_specifications_content_purpose_idx` (`media_content_purpose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='this table for media specification';

/*Data for the table `media_specifications_temp` */

insert  into `media_specifications_temp`(`media_specification_id`,`media_specifications_name`,`media_type_id`,`mime_type_id`,`media_content_purpose_id`,`width`,`height`,`bitrate`,`duration`,`media_location`,`is_source`,`parent_specification_id`,`transcodingCommand`) values (1,'200x200 Gif File',1,2,1,200,200,0,0,0,1,-1,NULL),(2,'150x150 Gif File',1,2,1,150,150,0,0,0,0,1,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##150x150##@DESTINATION@'),(3,'100x100 Gif File',1,2,1,100,100,0,0,0,0,1,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##100x100##@DESTINATION@'),(4,'50x50 Gif File',1,2,1,50,50,0,0,0,0,1,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##50x50##@DESTINATION@'),(5,'720x480 size 320 bitrate mp4 file',1,5,2,720,480,320,0,0,1,-1,NULL),(6,'320x240 size 180 bitrate 3gp file',1,5,2,320,240,180,0,0,0,5,'/usr/local/bin/video_conversion.sh##-y##-i##@SOURCE@##-vlevel##10##-g##25##-b##180k##-r##12##-s##176x144##-vcodec##mpeg4##-acodec##libfaac##-ac##1##-ab##12.2k##-ar##8000##@DESTINATION@'),(7,'176x144 size 70 bitrate 3gp file',1,5,2,176,144,70,0,0,0,5,'/usr/local/bin/video_conversion.sh##-y##-i##@SOURCE@##-vlevel##10##-g##25##-b##180k##-r##12##-s##176x144##-vcodec##mpeg4##-acodec##libfaac##-ac##1##-ab##12.2k##-ar##8000##@DESTINATION@'),(8,'176x144 size 70 bitrate 3gp file',1,5,2,176,144,70,0,0,0,5,'/usr/local/bin/video_conversion.sh##-y##-i##@SOURCE@##-vlevel##10##-g##25##-b##180k##-r##12##-s##176x144##-vcodec##mpeg4##-acodec##libfaac##-ac##1##-ab##12.2k##-ar##8000##@DESTINATION@'),(9,'176x144 size 70 bitrate 3gp h263 file',1,5,2,176,144,70,0,0,0,5,'/usr/local/bin/video_conversion.sh##-y##-i##@SOURCE@##-vlevel##10##-g##25##-b##180k##-r##12##-s##176x144##-vcodec##mpeg4##-acodec##libfaac##-ac##1##-ab##12.2k##-ar##8000##@DESTINATION@'),(10,'1500x1500 Gif File',2,2,2,1500,1500,0,0,0,1,-1,NULL),(11,'320x320 Gif File',2,2,2,320,320,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##320x320##@DESTINATION@'),(12,'120x120 Gif File',2,2,2,120,120,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##120x120##@DESTINATION@'),(13,'128x128 Gif File',2,2,2,128,128,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##128x128##@DESTINATION@'),(14,'130x130 Gif File',2,2,2,130,130,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##130x130##@DESTINATION@'),(15,'240x240 Gif File',2,2,2,240,240,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##240x240##@DESTINATION@'),(16,'150x150 Gif File',2,2,2,150,150,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##150x150##@DESTINATION@'),(17,'100x100 Gif File',2,2,1,100,100,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##100x100##@DESTINATION@'),(18,'75x75 Gif File',2,2,1,75,75,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##75x75##@DESTINATION@'),(19,'50x50 Gif File',2,2,1,50,50,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##50x50##@DESTINATION@'),(20,'960x1280 Gif File',2,2,2,960,1280,0,0,0,1,-1,NULL),(21,'720x960 Gif File',2,2,2,720,960,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##720x960##@DESTINATION@'),(22,'768x1024 Gif File',2,2,2,768,1024,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##768x1024##@DESTINATION@'),(23,'120x160 Gif File',2,2,2,120,160,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##120x160##@DESTINATION@'),(24,'132x176 Gif File',2,2,2,132,176,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##132x176##@DESTINATION@'),(25,'240x320 Gif File',2,2,2,240,320,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##240x320##@DESTINATION@'),(26,'480x640 Gif File',2,2,2,480,640,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##480x640##@DESTINATION@'),(27,'600x800 Gif File',2,2,2,600,800,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##600x800##@DESTINATION@'),(28,'960x1600 Gif File',2,2,2,960,1600,0,0,0,1,-1,NULL),(29,'480x800 Gif File',2,2,2,480,800,0,0,0,0,28,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##480x800##@DESTINATION@'),(30,'240x400 Gif File',2,2,2,240,400,0,0,0,0,28,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##240x400##@DESTINATION@'),(31,'960x720 Gif File',2,2,2,960,720,0,0,0,1,-1,NULL),(32,'640x480 Gif File',2,2,2,640,480,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##640x480##@DESTINATION@'),(33,'320x240 Gif File',2,2,2,320,240,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##320x240##@DESTINATION@'),(34,'480x360 Gif File',2,2,2,480,360,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##480x360##@DESTINATION@'),(35,'128x96 Gif File',2,2,2,128,96,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##128x96##@DESTINATION@'),(36,'96x72 Gif File',2,2,2,96,72,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##96x72##@DESTINATION@'),(37,'320x320 Png File',2,3,2,320,320,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##320x320##@DESTINATION@'),(38,'120x120 Png File',2,3,2,120,120,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##120x120##@DESTINATION@'),(39,'128x128 Png File',2,3,2,128,128,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##128x128##@DESTINATION@'),(40,'130x130 Png File',2,3,2,130,130,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##130x130##@DESTINATION@'),(41,'240x240 Png File',2,3,2,240,240,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##240x240##@DESTINATION@'),(42,'150x150 Png File',2,3,2,150,150,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##150x150##@DESTINATION@'),(43,'100x100 Png File',2,3,1,100,100,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##100x100##@DESTINATION@'),(44,'75x75 Png File',2,3,1,75,75,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##75x75##@DESTINATION@'),(45,'50x50 Png File',2,3,1,50,50,0,0,0,0,10,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##50x50##@DESTINATION@'),(46,'720x960 Png File',2,3,2,720,960,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##720x960##@DESTINATION@'),(47,'768x1024 Png File',2,3,2,768,1024,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##768x1024##@DESTINATION@'),(48,'120x160 Png File',2,3,2,120,160,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##120x160##@DESTINATION@'),(49,'132x176 Png File',2,3,2,132,176,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##132x176##@DESTINATION@'),(50,'240x320 Png File',2,3,2,240,320,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##240x320##@DESTINATION@'),(51,'480x640 Png File',2,3,2,480,640,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##480x640##@DESTINATION@'),(52,'600x800 Png File',2,3,2,600,800,0,0,0,0,20,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##600x800##@DESTINATION@'),(53,'480x800 Png File',2,3,2,480,800,0,0,0,0,28,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##480x800##@DESTINATION@'),(54,'240x400 Png File',2,3,2,240,400,0,0,0,0,28,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##240x400##@DESTINATION@'),(55,'640x480 Png File',2,3,2,640,480,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##640x480##@DESTINATION@'),(56,'320x240 Png File',2,3,2,320,240,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##320x240##@DESTINATION@'),(57,'480x360 Png File',2,3,2,480,360,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##480x360##@DESTINATION@'),(58,'128x96 Png File',2,3,2,128,96,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##128x96##@DESTINATION@'),(59,'96x72 Png File',2,3,2,96,72,0,0,0,0,31,'/usr/local/bin/image_conversion.sh##@SOURCE@[0]##-resize##96x72##@DESTINATION@'),(60,'1500x1500 Gif File',3,2,2,1500,1500,0,0,0,1,-1,NULL),(61,'320x320 Gif File',3,2,2,320,320,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##320x320##@DESTINATION@'),(62,'120x120 Gif File',3,2,2,120,120,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##120x120##@DESTINATION@'),(63,'128x128 Gif File',3,2,2,128,128,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##128x128##@DESTINATION@'),(64,'130x130 Gif File',3,2,2,130,130,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##130x130##@DESTINATION@'),(65,'240x240 Gif File',3,2,2,240,240,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##240x240##@DESTINATION@'),(66,'150x150 Gif File',3,2,2,150,150,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##150x150##@DESTINATION@'),(67,'100x100 Gif File',3,2,1,100,100,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##100x100##@DESTINATION@'),(68,'75x75 Gif File',3,2,1,75,75,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##75x75##@DESTINATION@'),(69,'50x50 Gif File',3,2,1,50,50,0,0,0,0,60,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##50x50##@DESTINATION@'),(70,'960x1280 Gif File',3,2,2,960,1280,0,0,0,1,-1,NULL),(71,'720x960 Gif File',3,2,2,720,960,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##720x960##@DESTINATION@'),(72,'768x1024 Gif File',3,2,2,768,1024,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##768x1024##@DESTINATION@'),(73,'120x160 Gif File',3,2,2,120,160,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##120x160##@DESTINATION@'),(74,'132x176 Gif File',3,2,2,132,176,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##132x176##@DESTINATION@'),(75,'240x320 Gif File',3,2,2,240,320,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##240x320##@DESTINATION@'),(76,'480x640 Gif File',3,2,2,480,640,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##480x640##@DESTINATION@'),(77,'600x800 Gif File',3,2,2,600,800,0,0,0,0,70,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##600x800##@DESTINATION@'),(78,'960x1600 Gif File',3,2,2,960,1600,0,0,0,1,-1,NULL),(79,'480x800 Gif File',3,2,2,480,800,0,0,0,0,78,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##480x800##@DESTINATION@'),(80,'240x400 Gif File',3,2,2,240,400,0,0,0,0,78,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##240x400##@DESTINATION@'),(81,'960x720 Gif File',3,2,2,960,720,0,0,0,1,-1,NULL),(82,'640x480 Gif File',3,2,2,640,480,0,0,0,0,81,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##640x480##@DESTINATION@'),(83,'320x240 Gif File',3,2,2,320,240,0,0,0,0,81,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##320x240##@DESTINATION@'),(84,'480x360 Gif File',3,2,2,480,360,0,0,0,0,81,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##480x360##@DESTINATION@'),(85,'128x96 Gif File',3,2,2,128,96,0,0,0,0,81,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##128x96##@DESTINATION@'),(86,'96x72 Gif File',3,2,2,96,72,0,0,0,0,81,'/usr/local/bin/image_conversion.sh##@SOURCE@##-resize##96x72##@DESTINATION@');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `media_subgroups` */

insert  into `media_subgroups`(`subgroup_id`,`parent_group_id`,`child_group_id`,`group_order`,`created_time`,`modified_time`) values (1,1,8,0,'2013-10-27 21:31:36','2013-10-27 21:31:36');

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `media_tags` */

insert  into `media_tags`(`media_tag_id`,`media_id`,`tag_id`,`created_time`,`modified_time`) values (1,12,1,'2013-10-21 01:04:05','2013-10-21 01:04:05'),(2,13,1,'2013-10-21 21:37:53','2013-10-21 21:37:53'),(3,14,1,'2013-10-21 21:46:53','2013-10-21 21:46:53'),(4,15,1,'2013-10-21 22:19:26','2013-10-21 22:19:26'),(5,16,1,'2013-10-25 11:53:46','2013-10-25 11:53:46'),(6,17,1,'2013-10-25 12:57:39','2013-10-25 12:57:39'),(7,18,1,'2013-10-25 13:09:43','2013-10-25 13:09:43'),(8,19,1,'2013-10-25 13:16:47','2013-10-25 13:16:47'),(9,1,2,'2013-10-25 14:58:58','2013-10-25 14:58:58'),(10,20,1,'2013-10-26 22:47:41','2013-10-26 22:47:41'),(11,21,1,'2013-10-26 22:53:54','2013-10-26 22:53:54'),(12,22,3,'2013-10-27 19:27:41','2013-10-27 19:27:41'),(13,23,4,'2013-10-27 20:24:11','2013-10-27 20:24:11'),(14,20,1,'2013-10-27 20:48:05','2013-10-27 20:48:05'),(15,20,1,'2013-10-27 21:00:15','2013-10-27 21:00:15'),(16,20,1,'2013-10-27 21:00:15','2013-10-27 21:00:15'),(17,3,2,'2013-10-27 21:00:37','2013-10-27 21:00:37'),(18,24,1,'2013-10-27 21:02:55','2013-10-27 21:02:55'),(19,25,5,'2013-10-27 21:04:39','2013-10-27 21:04:39'),(20,26,1,'2013-10-27 21:14:48','2013-10-27 21:14:48'),(21,27,1,'2013-10-27 21:16:04','2013-10-27 21:16:04'),(22,28,1,'2013-10-27 21:25:15','2013-10-27 21:25:15'),(23,29,1,'2013-10-27 21:30:31','2013-10-27 21:30:31'),(24,30,1,'2013-10-27 22:17:29','2013-10-27 22:17:29'),(25,31,2,'2013-10-28 13:19:01','2013-10-28 13:19:01'),(26,32,2,'2013-10-28 13:20:49','2013-10-28 13:20:49'),(27,32,2,'2013-10-28 13:22:28','2013-10-28 13:22:28'),(28,32,1,'2013-10-28 13:22:54','2013-10-28 13:22:54'),(29,31,1,'2013-10-28 13:23:19','2013-10-28 13:23:19'),(30,33,1,'2013-10-28 13:44:25','2013-10-28 13:44:25'),(31,34,1,'2013-11-02 12:41:05','2013-11-02 12:41:05'),(32,35,1,'2013-11-10 12:22:27','2013-11-10 12:22:27'),(33,36,2,'2014-07-09 06:49:49','2014-07-09 06:49:49'),(34,37,1,'2014-07-09 07:19:32','2014-07-09 07:19:32');

/*Table structure for table `media_types` */

DROP TABLE IF EXISTS `media_types`;

CREATE TABLE `media_types` (
  `media_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_type_name` varchar(45) NOT NULL COMMENT 'stores information about media types for internal purpose',
  `media_type_title` varchar(45) NOT NULL COMMENT 'this column used for title which will be displayed external',
  `created_time` datetime NOT NULL COMMENT 'stores created time',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'stores last modified time',
  PRIMARY KEY (`media_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media types';

/*Data for the table `media_types` */

insert  into `media_types`(`media_type_id`,`media_type_name`,`media_type_title`,`created_time`,`modified_time`) values (1,'Videos','Videos','2013-10-10 23:21:41','2013-10-11 08:51:41'),(2,'Wallpapers','Wallpapers','2013-10-26 19:27:22','2013-10-26 19:31:10'),(3,'Animations','Animations','2013-10-26 19:28:59','2013-10-26 19:28:59'),(4,'Text','Text','2014-07-07 07:55:21','2014-07-07 07:55:33');

/*Table structure for table `mime_types` */

DROP TABLE IF EXISTS `mime_types`;

CREATE TABLE `mime_types` (
  `mime_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `mime_type` varchar(45) NOT NULL,
  `media_extension` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mime_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `mime_types` */

insert  into `mime_types`(`mime_type_id`,`mime_type`,`media_extension`,`created_time`,`modified_time`) values (1,'image/jpeg','jpg','2013-10-10 23:21:41','2013-11-12 17:36:05'),(2,'image/gif','gif','2013-10-10 23:21:41','2013-11-12 17:36:12'),(3,'image/png','png','2013-10-10 23:21:41','2013-11-12 17:36:17'),(4,'video/3gpp','3gp','2013-10-10 23:21:41','2013-10-12 22:04:42'),(5,'video/mp4','mp4','2013-10-10 23:21:41','2013-10-12 22:04:42'),(6,'text','text','2014-07-07 07:58:48','2014-07-07 07:58:54');

/*Table structure for table `network_circles` */

DROP TABLE IF EXISTS `network_circles`;

CREATE TABLE `network_circles` (
  `network_circle_id` int(11) NOT NULL AUTO_INCREMENT,
  `network_id` int(11) NOT NULL,
  `circle_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`network_circle_id`),
  KEY `fk_network_circles_networks_idx` (`network_id`),
  KEY `fk_network_circles_circles_idx` (`circle_id`),
  CONSTRAINT `fk_network_circles_circles` FOREIGN KEY (`circle_id`) REFERENCES `circles` (`circle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_network_circles_networks` FOREIGN KEY (`network_id`) REFERENCES `networks` (`network_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `network_circles` */

/*Table structure for table `networks` */

DROP TABLE IF EXISTS `networks`;

CREATE TABLE `networks` (
  `network_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`network_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='network  means operator';

/*Data for the table `networks` */

/*Table structure for table `os` */

DROP TABLE IF EXISTS `os`;

CREATE TABLE `os` (
  `os_id` int(11) NOT NULL AUTO_INCREMENT,
  `os_name` varchar(50) DEFAULT NULL,
  `os_title` varchar(50) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`os_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `os` */

/*Table structure for table `purchase_details` */

DROP TABLE IF EXISTS `purchase_details`;

CREATE TABLE `purchase_details` (
  `purchase_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_id` int(11) NOT NULL,
  `service_key_price_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0 for fail, 1 for success and 5 for  pending.',
  `purchase_time` datetime NOT NULL,
  `remarks` varchar(800) NOT NULL,
  `channel` varchar(45) NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`purchase_detail_id`),
  KEY `fk_purchase_details_purchase_idx` (`purchase_id`),
  KEY `fk_purchase_details_prices_idx` (`service_key_price_id`),
  CONSTRAINT `fk_purchase_details_prices` FOREIGN KEY (`service_key_price_id`) REFERENCES `service_key_prices` (`service_key_price_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_purchase_details_purchase` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`purchase_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase_details` */

/*Table structure for table `purchases` */

DROP TABLE IF EXISTS `purchases`;

CREATE TABLE `purchases` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `msisdn` bigint(20) NOT NULL,
  `service_key_id` int(11) NOT NULL,
  `network_id` int(11) NOT NULL,
  `circle_id` int(11) NOT NULL,
  `purchase_type` tinyint(1) NOT NULL COMMENT '0 for one time and 1 for multiple times',
  `first_purchase_time` datetime NOT NULL,
  `last_purchase_time` datetime NOT NULL,
  `expiry_time` datetime NOT NULL,
  `stoped_time` datetime DEFAULT NULL,
  `purchase_status` tinyint(4) NOT NULL COMMENT '1 for active, -1 for inactive, -2 for suspended',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`purchase_id`),
  KEY `fk_purchases_service_key_idx` (`service_key_id`),
  KEY `fk_purchases_network_idx` (`network_id`),
  KEY `fk_purchases_circle_idx` (`circle_id`),
  CONSTRAINT `fk_purchases_circle` FOREIGN KEY (`circle_id`) REFERENCES `circles` (`circle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_purchases_network` FOREIGN KEY (`network_id`) REFERENCES `networks` (`network_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_purchases_service_key` FOREIGN KEY (`service_key_id`) REFERENCES `service_keys` (`service_key_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchases` */

/*Table structure for table `requests` */

DROP TABLE IF EXISTS `requests`;

CREATE TABLE `requests` (
  `request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msisdn` bigint(20) DEFAULT NULL,
  `requested_url` varchar(1000) NOT NULL,
  `redirect_url` varchar(1000) DEFAULT NULL,
  `createdTime` datetime NOT NULL,
  `modifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`request_id`),
  KEY `msisdn` (`msisdn`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `requests` */

insert  into `requests`(`request_id`,`msisdn`,`requested_url`,`redirect_url`,`createdTime`,`modifiedTime`) values (1,NULL,'http://49.50.68.139:8080/Wap/service/1?t1=t&channel=smd','http://49.50.68.139:8080/Wap/service/1?t1=t&channel=smd','2013-11-22 02:41:18','2013-11-22 02:41:18'),(2,9966,'http://49.50.68.139:8080/Wap/service2/dwl/1/1/1?t1=t','http://49.50.68.139:8080/Wap/service2/dwl/1/1/1?t1=t','2013-11-22 02:43:26','2013-11-22 02:43:26'),(3,NULL,'http://49.50.68.139:8080/Wap/service/2?t1=t&channel=smd','http://49.50.68.139:8080/Wap/service/2?t1=t&channel=smd','2013-11-22 02:43:27','2013-11-22 02:43:27'),(4,9966,'http://49.50.68.139:8080/Wap/service2/dwl/1/1/1?t1=t','http://49.50.68.139:8080/Wap/service2/dwl/1/1/1?t1=t','2013-11-22 02:56:29','2013-11-22 02:56:29');

/*Table structure for table `responses` */

DROP TABLE IF EXISTS `responses`;

CREATE TABLE `responses` (
  `response_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_id` bigint(20) NOT NULL,
  `msisdn` bigint(20) DEFAULT NULL,
  `query_string` varchar(1000) DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`response_id`),
  KEY `request_id` (`request_id`,`msisdn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `responses` */

/*Table structure for table `service_key_prices` */

DROP TABLE IF EXISTS `service_key_prices`;

CREATE TABLE `service_key_prices` (
  `service_key_price_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_key_price_key` varchar(45) DEFAULT NULL,
  `service_key_price_type` tinyint(1) NOT NULL COMMENT '0 for main and 1 for fallback',
  `service_key_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `duration` int(11) NOT NULL,
  `tokens` int(11) NOT NULL,
  PRIMARY KEY (`service_key_price_id`),
  KEY `fk_service_key_prices_serviceKeys_idx` (`service_key_id`),
  CONSTRAINT `fk_service_key_prices_serviceKeys` FOREIGN KEY (`service_key_id`) REFERENCES `service_keys` (`service_key_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service_key_prices` */

/*Table structure for table `service_keys` */

DROP TABLE IF EXISTS `service_keys`;

CREATE TABLE `service_keys` (
  `service_key_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `title` varchar(45) NOT NULL,
  `service_key` varchar(45) NOT NULL,
  `desciption` varchar(256) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`service_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service_keys` */

/*Table structure for table `service_media_groups` */

DROP TABLE IF EXISTS `service_media_groups`;

CREATE TABLE `service_media_groups` (
  `service_media_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `media_group_id` int(11) NOT NULL,
  `is_one_time_charge` tinyint(1) NOT NULL COMMENT '1 for no and 0 for yes',
  `service_key_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`service_media_group_id`),
  KEY `fk_service_media_groups_service_idx` (`service_id`),
  KEY `fk_service_media_groups_media_group_id_idx` (`service_key_id`),
  KEY `fk_service_media_groups_groups_idx` (`media_group_id`),
  CONSTRAINT `fk_service_media_groups_groups` FOREIGN KEY (`media_group_id`) REFERENCES `media_groups` (`media_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_service_media_groups_service` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_service_media_groups_service_keys` FOREIGN KEY (`service_key_id`) REFERENCES `service_keys` (`service_key_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service_media_groups` */

/*Table structure for table `services` */

DROP TABLE IF EXISTS `services`;

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(45) NOT NULL,
  `service_title` varchar(45) NOT NULL,
  `service_description` varchar(256) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `services` */

/*Table structure for table `tags` */

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tags` */

insert  into `tags`(`tag_id`,`tag_name`,`created_time`,`modified_time`) values (1,'TEST','2013-10-21 01:04:05','2013-10-21 01:04:05'),(2,'','2013-10-25 14:58:58','2013-10-25 14:58:58'),(3,'desc','2013-10-27 19:27:41','2013-10-27 19:27:41'),(4,'test wallpaper','2013-10-27 20:24:11','2013-10-27 20:24:11'),(5,'test wallpaper12','2013-10-27 21:04:39','2013-10-27 21:04:39');

/*Table structure for table `user_media_providers` */

DROP TABLE IF EXISTS `user_media_providers`;

CREATE TABLE `user_media_providers` (
  `user_media_provider_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `media_provider_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_media_provider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_media_providers` */

insert  into `user_media_providers`(`user_media_provider_id`,`user_name`,`media_provider_id`,`created_time`,`modified_time`) values (1,'kiranadmin@sumadga.com',1,'2013-11-17 19:00:07','2013-11-17 19:00:07');

/*Table structure for table `user_permissions` */

DROP TABLE IF EXISTS `user_permissions`;

CREATE TABLE `user_permissions` (
  `user` varchar(150) NOT NULL,
  `media_upload` tinyint(1) DEFAULT '1',
  `game_upload` tinyint(1) DEFAULT '0',
  `media_search` tinyint(1) DEFAULT '1',
  `media_group` tinyint(1) DEFAULT '0',
  `media_group_list` tinyint(1) DEFAULT '0',
  `media_service` tinyint(1) DEFAULT '0',
  `mis` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_permissions` */

insert  into `user_permissions`(`user`,`media_upload`,`game_upload`,`media_search`,`media_group`,`media_group_list`,`media_service`,`mis`) values ('kiranadmin@sumadga.com',1,0,1,0,0,0,0);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(256) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('kiranadmin@sumadga.com','kiranadmin',1),('kiranuser@sumadga.com','kiranuser',1),('murliadmin@sumadga.com','murliadmin',0),('murliuser@sumadga.com','murliuser',1);

/*Table structure for table `revenueview` */

DROP TABLE IF EXISTS `revenueview`;

/*!50001 DROP VIEW IF EXISTS `revenueview` */;
/*!50001 DROP TABLE IF EXISTS `revenueview` */;

/*!50001 CREATE TABLE  `revenueview`(
 `purchaseTime` date ,
 `operator` varchar(45) ,
 `content` varchar(100) ,
 `mediaProviderId` int(11) ,
 `downloads` bigint(21) ,
 `revenue` double 
)*/;

/*View structure for view revenueview */

/*!50001 DROP TABLE IF EXISTS `revenueview` */;
/*!50001 DROP VIEW IF EXISTS `revenueview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `revenueview` AS select cast(`pd`.`purchase_time` as date) AS `purchaseTime`,`n`.`title` AS `operator`,`m`.`media_name` AS `content`,`m`.`media_provider_id` AS `mediaProviderId`,count(distinct `md`.`media_download_id`) AS `downloads`,sum(`pd`.`amount`) AS `revenue` from ((((`purchases` `p` join `networks` `n` on((`n`.`network_id` = `p`.`network_id`))) join `purchase_details` `pd` on((`p`.`purchase_id` = `pd`.`purchase_id`))) left join `media_downloads` `md` on((`md`.`purchase_id` = `p`.`purchase_id`))) left join `media` `m` on((`md`.`media_id` = `m`.`media_id`))) group by 1,2,3 */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
