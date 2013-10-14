-- MySQL dump 10.13  Distrib 5.5.32, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: CMS
-- ------------------------------------------------------
-- Server version	5.5.32-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `language_name` varchar(45) NOT NULL,
  `description` varchar(256) NOT NULL DEFAULT '',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'English','english','2013-10-11 22:32:08','2013-10-11 17:02:08');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,'test2','test1','test','2013-10-12 19:00:35','2013-10-12 13:30:35',2,1,2,'2013-10-09 00:00:00','2013-10-17 00:00:00',1),(2,'test2','test1','test','2013-10-12 19:07:17','2013-10-12 13:37:17',2,1,2,'2013-10-09 00:00:00','2013-10-18 00:00:00',1);
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_content_purposes`
--

DROP TABLE IF EXISTS `media_content_purposes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_content_purposes` (
  `media_content_purpose_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_content_purpose` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_content_purpose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_content_purposes`
--

LOCK TABLES `media_content_purposes` WRITE;
/*!40000 ALTER TABLE `media_content_purposes` DISABLE KEYS */;
INSERT INTO `media_content_purposes` VALUES (1,'preview','2013-10-12 12:35:38','2013-10-12 07:05:38'),(2,'media','2013-10-12 12:35:38','2013-10-12 07:06:09');
/*!40000 ALTER TABLE `media_content_purposes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_contents`
--

DROP TABLE IF EXISTS `media_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_contents`
--

LOCK TABLES `media_contents` WRITE;
/*!40000 ALTER TABLE `media_contents` DISABLE KEYS */;
/*!40000 ALTER TABLE `media_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_cycles`
--

DROP TABLE IF EXISTS `media_cycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_cycles` (
  `media_cycle_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_cycle_state` varchar(45) NOT NULL COMMENT 'stores info about media cycle state',
  `created_time` datetime NOT NULL COMMENT 'stores  created time',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'stores last modified time',
  PRIMARY KEY (`media_cycle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='stores info about media cycle';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_cycles`
--

LOCK TABLES `media_cycles` WRITE;
/*!40000 ALTER TABLE `media_cycles` DISABLE KEYS */;
INSERT INTO `media_cycles` VALUES (1,'Initial','2013-10-11 22:33:52','2013-10-11 17:03:52'),(2,'Transcoded','2013-10-11 22:34:37','2013-10-11 17:07:20'),(3,'Published','2013-10-11 22:35:35','2013-10-11 17:05:35'),(4,'Removed','2013-10-11 22:36:11','2013-10-11 17:06:11');
/*!40000 ALTER TABLE `media_cycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_group_media`
--

DROP TABLE IF EXISTS `media_group_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table for media for media groups';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_group_media`
--

LOCK TABLES `media_group_media` WRITE;
/*!40000 ALTER TABLE `media_group_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `media_group_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_groups`
--

DROP TABLE IF EXISTS `media_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_groups` (
  `media_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_group_name` varchar(45) NOT NULL,
  `media_group_title` varchar(20) NOT NULL,
  `media_group_description` varchar(256) NOT NULL DEFAULT '',
  `media_group_preview_id` int(11) NOT NULL DEFAULT '-1',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='This table stores information about media grouping ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_groups`
--

LOCK TABLES `media_groups` WRITE;
/*!40000 ALTER TABLE `media_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `media_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_process_states`
--

DROP TABLE IF EXISTS `media_process_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_process_states` (
  `media_process_state_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_process_state_name` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL DEFAULT '',
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_process_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='This table stores info about media process states';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_process_states`
--

LOCK TABLES `media_process_states` WRITE;
/*!40000 ALTER TABLE `media_process_states` DISABLE KEYS */;
INSERT INTO `media_process_states` VALUES (1,'not started','Transcode not started','2013-10-12 15:17:47','2013-10-12 09:47:47'),(2,'started','Transcode started','2013-10-12 15:18:11','2013-10-12 09:48:11'),(3,'completed','Transcode completed','2013-10-12 15:21:07','2013-10-12 09:51:07');
/*!40000 ALTER TABLE `media_process_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_specifications`
--

DROP TABLE IF EXISTS `media_specifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_specifications`
--

LOCK TABLES `media_specifications` WRITE;
/*!40000 ALTER TABLE `media_specifications` DISABLE KEYS */;
INSERT INTO `media_specifications` VALUES (1,'Videos',1,2,1,100,100,0,0,0,1,-1),(2,'Videos',1,5,2,720,480,320,0,0,1,-1);
/*!40000 ALTER TABLE `media_specifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_tags`
--

DROP TABLE IF EXISTS `media_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_tags`
--

LOCK TABLES `media_tags` WRITE;
/*!40000 ALTER TABLE `media_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `media_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_types`
--

DROP TABLE IF EXISTS `media_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media_types` (
  `media_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_type_name` varchar(45) NOT NULL COMMENT 'stores information about media types for internal purpose',
  `media_type_title` varchar(45) NOT NULL COMMENT 'this column used for title which will be displayed external',
  `created_time` datetime NOT NULL COMMENT 'stores created time',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'stores last modified time',
  PRIMARY KEY (`media_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='This table stores information about media types';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_types`
--

LOCK TABLES `media_types` WRITE;
/*!40000 ALTER TABLE `media_types` DISABLE KEYS */;
INSERT INTO `media_types` VALUES (1,'Videos','Videos','2013-10-10 23:21:41','2013-10-10 17:51:41');
/*!40000 ALTER TABLE `media_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mime_types`
--

DROP TABLE IF EXISTS `mime_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mime_types` (
  `mime_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `mime_type` varchar(45) NOT NULL,
  `media_extension` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mime_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mime_types`
--

LOCK TABLES `mime_types` WRITE;
/*!40000 ALTER TABLE `mime_types` DISABLE KEYS */;
INSERT INTO `mime_types` VALUES (1,'img/jpeg','jpg','2013-10-10 23:21:41','2013-10-12 07:04:42'),(2,'img/gif','gif','2013-10-10 23:21:41','2013-10-12 07:04:42'),(3,'img/png','png','2013-10-10 23:21:41','2013-10-12 07:04:42'),(4,'video/3gpp','3gp','2013-10-10 23:21:41','2013-10-12 07:04:42'),(5,'video/mp4','mp4','2013-10-10 23:21:41','2013-10-12 07:04:42');
/*!40000 ALTER TABLE `mime_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(45) NOT NULL,
  `created_time` datetime NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-12 19:54:30
