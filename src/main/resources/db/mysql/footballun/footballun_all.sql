-- MySQL dump 10.13  Distrib 5.6.27, for Win64 (x86_64)
--
-- Host: localhost    Database: footballun
-- ------------------------------------------------------
-- Server version	5.6.27

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
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `year_from` year(4) DEFAULT NULL,
  `year_to` year(4) DEFAULT NULL,
  `start_at` timestamp NULL DEFAULT NULL,
  `end_at` timestamp NULL DEFAULT NULL,
  `host_country_id` smallint(6) DEFAULT NULL,
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_country_id_idx` (`host_country_id`),
  CONSTRAINT `fk_country_id` FOREIGN KEY (`host_country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (8,'Euro 2016',2016,2016,'2016-06-09 17:00:00','2016-07-09 17:00:00',NULL,'CUP'),(9,'English Premier League',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(10,'Bundesliga',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(11,'Laliga',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(12,'League 1',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(13,'WorldCup 2018',2018,2018,'2018-06-09 17:00:00','2018-07-09 17:00:00',NULL,'CUP'),(14,'V-League',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `continent` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (21,'France','Europe'),(22,'Albania','Europe'),(23,'Austria','Europe'),(24,'Belgium','Europe'),(25,'Croatia','Europe'),(26,'Czech Republic','Europe'),(27,'England','Europe'),(28,'Germany','Europe'),(29,'Iceland','Europe'),(30,'Italy','Europe'),(31,'Northern Ireland','Europe'),(32,'Poland','Europe'),(33,'Portugal','Europe'),(34,'Romania','Europe'),(35,'Russia','Europe'),(36,'Slovakia','Europe'),(37,'Spain','Europe'),(38,'Switzerland','Europe'),(39,'Turkey','Europe'),(40,'Wales','Europe');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formation`
--

DROP TABLE IF EXISTS `formation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formation`
--

LOCK TABLES `formation` WRITE;
/*!40000 ALTER TABLE `formation` DISABLE KEYS */;
/*!40000 ALTER TABLE `formation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (7,'A'),(8,'B'),(9,'C'),(10,'D'),(11,'E'),(12,'F');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hero`
--

DROP TABLE IF EXISTS `hero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `hight` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `alias` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status_id` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hero_hero_status1_idx` (`status_id`),
  CONSTRAINT `fk_hero_hero_status1` FOREIGN KEY (`status_id`) REFERENCES `hero_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hero`
--

LOCK TABLES `hero` WRITE;
/*!40000 ALTER TABLE `hero` DISABLE KEYS */;
INSERT INTO `hero` VALUES (1,'Willy','Caballero',NULL,NULL,NULL,NULL,NULL,1),(2,'Joe','Hart',NULL,NULL,NULL,NULL,NULL,1),(3,'Richard','Wright',NULL,NULL,NULL,NULL,NULL,1),(4,'Nicolas','Otamendi',NULL,NULL,NULL,NULL,NULL,1),(5,'Gael','Clichy',NULL,NULL,NULL,NULL,NULL,1),(6,'Martin','Demichelis',NULL,NULL,NULL,NULL,NULL,1),(7,'Jason','Denayer',NULL,NULL,NULL,NULL,NULL,1),(8,'Aleksandar','Kolarov',NULL,NULL,NULL,NULL,NULL,1),(9,'Vincent','Kompany',NULL,NULL,NULL,NULL,NULL,1),(10,'Eliaquim','Mangala',NULL,NULL,NULL,NULL,NULL,1),(11,'Bacary','Sagna',NULL,NULL,NULL,NULL,NULL,1),(12,'Pablo','Zabaleta',NULL,NULL,NULL,NULL,NULL,1),(13,'Fabian','Delph',NULL,NULL,NULL,NULL,NULL,1),(14,'Fernando','Francisco Reges',NULL,NULL,NULL,NULL,NULL,1),(15,'Fernandinho','Luiz Roza',NULL,NULL,NULL,NULL,NULL,1),(16,'Samir','Nasri',NULL,NULL,NULL,NULL,NULL,1),(17,'Jesus','Navas',NULL,NULL,NULL,NULL,NULL,1),(18,'David','Silva',NULL,NULL,NULL,NULL,NULL,1),(19,'Kevin','De Bruyne',NULL,NULL,NULL,NULL,NULL,1),(20,'Raheem','Sterling',NULL,NULL,NULL,NULL,NULL,1),(21,'Patrick','Roberts',NULL,NULL,NULL,NULL,NULL,1),(22,'Yaya','Toure',NULL,NULL,NULL,NULL,NULL,1),(23,'Bruno','Zuculini',NULL,NULL,NULL,NULL,NULL,1),(24,'Sergio','Aguero',NULL,NULL,NULL,NULL,NULL,1),(25,'Wilfried','Bony',NULL,NULL,NULL,NULL,NULL,1),(26,'Edin','Dzeko',NULL,NULL,NULL,NULL,NULL,1),(27,'Kelechi','Iheanacho',NULL,NULL,NULL,NULL,NULL,1),(28,'Stevan','Jovetic',NULL,NULL,NULL,NULL,NULL,1),(29,'Manuel','Pellegrini',NULL,NULL,NULL,NULL,NULL,1),(30,'Ruben','Cousillas Fuse',NULL,NULL,NULL,NULL,NULL,1),(31,'Xabier','Mancisidor',NULL,NULL,NULL,NULL,NULL,1),(32,'Jose','Cabello',NULL,NULL,NULL,NULL,NULL,1),(33,'Brian','Kidd',NULL,NULL,NULL,NULL,NULL,1),(34,'David','Ospina',NULL,NULL,NULL,NULL,NULL,1),(35,'Petr','Cech',NULL,NULL,NULL,NULL,NULL,1),(36,'Mathieu','Debuchy',NULL,NULL,NULL,NULL,NULL,1),(37,'Kieran','Gibbs',NULL,NULL,NULL,NULL,NULL,1),(38,'Per','Mertesacker',NULL,NULL,NULL,NULL,NULL,1),(39,'Gabriel','',NULL,NULL,NULL,NULL,NULL,1),(40,'Laurent','Koscielny',NULL,NULL,NULL,NULL,NULL,1),(41,'Nacho','Monreal',NULL,NULL,NULL,NULL,NULL,1),(42,'Calum','Chambers',NULL,NULL,NULL,NULL,NULL,1),(43,'Hector','Bellerin',NULL,NULL,NULL,NULL,NULL,1),(44,'Tomas','Rosicky',NULL,NULL,NULL,NULL,NULL,1),(45,'Mikel','Arteta',NULL,NULL,NULL,NULL,NULL,1),(46,'Jack','Wilshere',NULL,NULL,NULL,NULL,NULL,1),(47,'Mesut','Ozil',NULL,NULL,NULL,NULL,NULL,1),(48,'Alex','Oxlade-Chamberlain',NULL,NULL,NULL,NULL,NULL,1),(49,'Aaron','Ramsey',NULL,NULL,NULL,NULL,NULL,1),(50,'Santi','Cazorla',NULL,NULL,NULL,NULL,NULL,1),(51,'Mathieu','Flamini',NULL,NULL,NULL,NULL,NULL,1),(52,'Francis','Coquelin',NULL,NULL,NULL,NULL,NULL,1),(53,'Olivier','Giroud',NULL,NULL,NULL,NULL,NULL,1),(54,'Theo','Walcott',NULL,NULL,NULL,NULL,NULL,1),(55,'Alexis','Sanchez',NULL,NULL,NULL,NULL,NULL,1),(56,'Danny','Welbeck',NULL,NULL,NULL,NULL,NULL,1),(57,'Joel','Campbell',NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `hero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hero_role`
--

DROP TABLE IF EXISTS `hero_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hero_role` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hero_role`
--

LOCK TABLES `hero_role` WRITE;
/*!40000 ALTER TABLE `hero_role` DISABLE KEYS */;
INSERT INTO `hero_role` VALUES (1,'Player'),(2,'Manager'),(3,'President'),(4,'Referee'),(5,'Assistant Manager');
/*!40000 ALTER TABLE `hero_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hero_status`
--

DROP TABLE IF EXISTS `hero_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hero_status` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hero_status`
--

LOCK TABLES `hero_status` WRITE;
/*!40000 ALTER TABLE `hero_status` DISABLE KEYS */;
INSERT INTO `hero_status` VALUES (1,'Active'),(2,'Inactive'),(3,'Injury'),(4,'On Loan');
/*!40000 ALTER TABLE `hero_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchup`
--

DROP TABLE IF EXISTS `matchup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_at` timestamp NULL DEFAULT NULL,
  `end_at` timestamp NULL DEFAULT NULL,
  `result` tinyint(4) DEFAULT NULL,
  `matchday` tinyint(4) DEFAULT NULL,
  `point` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `stadium_id` int(11) DEFAULT NULL,
  `round` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `featured` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_matchup_stadium1_idx` (`stadium_id`),
  CONSTRAINT `fk_matchup_stadium1` FOREIGN KEY (`stadium_id`) REFERENCES `stadium` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup`
--

LOCK TABLES `matchup` WRITE;
/*!40000 ALTER TABLE `matchup` DISABLE KEYS */;
INSERT INTO `matchup` VALUES (3,NULL,'2015-10-03 11:45:00','2015-10-03 13:30:00',NULL,1,NULL,-1,3,'11',1),(4,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',NULL,1,NULL,-1,3,'11',1),(5,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',NULL,1,NULL,-1,3,'11',1),(6,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',NULL,1,NULL,-1,3,'11',1),(7,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',NULL,1,NULL,-1,3,'11',0),(8,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',NULL,1,NULL,-1,3,'11',0),(9,NULL,'2015-10-03 16:30:00','2015-10-03 18:15:00',NULL,1,NULL,-1,3,'11',NULL),(10,NULL,'2015-10-04 12:30:00','2015-10-04 14:15:00',NULL,1,NULL,-1,3,'11',NULL),(11,NULL,'2015-10-04 15:00:00','2015-10-04 16:45:00',NULL,1,NULL,-1,3,'11',NULL),(12,NULL,'2015-10-04 15:00:00','2015-10-04 16:45:00',NULL,1,NULL,-1,3,'11',NULL),(13,NULL,'2015-10-17 11:45:00','2015-10-17 13:30:00',NULL,1,NULL,-1,3,'11',NULL),(14,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,1,NULL,-1,3,'11',NULL),(15,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,1,NULL,-1,3,'11',NULL),(16,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,1,NULL,-1,3,'11',NULL),(17,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,1,NULL,-1,3,'11',NULL),(18,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,1,NULL,-1,3,'11',NULL),(19,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,1,NULL,-1,3,'11',NULL),(20,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(21,NULL,'2015-10-15 15:00:00','2015-10-15 16:45:00',NULL,2,NULL,-1,3,'12',NULL),(22,NULL,'2015-10-14 19:00:00','2015-10-14 20:45:00',NULL,2,NULL,-1,3,'12',NULL),(23,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(24,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(25,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(26,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(27,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(28,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(29,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(30,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(31,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(32,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(33,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(34,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(35,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(36,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(37,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',NULL,2,NULL,-1,3,'12',NULL),(38,NULL,'2015-10-31 14:00:00',NULL,NULL,2,NULL,-1,3,'12',NULL),(39,NULL,'2015-10-31 14:00:00',NULL,NULL,2,NULL,-1,3,'12',NULL);
/*!40000 ALTER TABLE `matchup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchup_live`
--

DROP TABLE IF EXISTS `matchup_live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchup_live` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matchup_id` int(11) NOT NULL,
  `event_id` smallint(6) NOT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `update_minute` tinyint(4) DEFAULT NULL,
  `matchup_register_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_matchup_detail_matchup1_idx` (`matchup_id`),
  KEY `fk_matchup_detail_event1_idx` (`event_id`),
  KEY `fk_matchup_detail_matchup_register1_idx` (`matchup_register_id`),
  CONSTRAINT `fk_matchup_detail_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_detail_matchup1` FOREIGN KEY (`matchup_id`) REFERENCES `matchup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_detail_matchup_register1` FOREIGN KEY (`matchup_register_id`) REFERENCES `matchup_register` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Information for team line-up, subtitues, formation, so on';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_live`
--

LOCK TABLES `matchup_live` WRITE;
/*!40000 ALTER TABLE `matchup_live` DISABLE KEYS */;
/*!40000 ALTER TABLE `matchup_live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchup_register`
--

DROP TABLE IF EXISTS `matchup_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchup_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_lineup` tinyint(1) DEFAULT NULL,
  `squad_member_id` int(11) NOT NULL,
  `position_id` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lineup_substitue_squad1_idx` (`squad_member_id`),
  KEY `fk_lineup_substitue_position1_idx` (`position_id`),
  CONSTRAINT `fk_lineup_substitue_position1` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lineup_substitue_squad1` FOREIGN KEY (`squad_member_id`) REFERENCES `squad_member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_register`
--

LOCK TABLES `matchup_register` WRITE;
/*!40000 ALTER TABLE `matchup_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `matchup_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchup_squad`
--

DROP TABLE IF EXISTS `matchup_squad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchup_squad` (
  `squad_id` int(11) NOT NULL,
  `matchup_id` int(11) NOT NULL,
  PRIMARY KEY (`squad_id`,`matchup_id`),
  KEY `fk_matchup_squad_squad1_idx` (`squad_id`),
  KEY `fk_matchup_squad_matchup1_idx` (`matchup_id`),
  CONSTRAINT `fk_matchup_squad_matchup1` FOREIGN KEY (`matchup_id`) REFERENCES `matchup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_squad_squad1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_squad`
--

LOCK TABLES `matchup_squad` WRITE;
/*!40000 ALTER TABLE `matchup_squad` DISABLE KEYS */;
INSERT INTO `matchup_squad` VALUES (21,6),(21,17),(21,31),(21,35),(22,11),(22,20),(22,28),(22,37),(23,8),(23,15),(23,27),(23,38),(24,11),(24,16),(24,31),(24,34),(25,7),(25,18),(25,24),(25,39),(26,12),(26,13),(26,30),(27,3),(27,15),(27,24),(27,34),(28,9),(28,18),(28,32),(29,10),(29,13),(29,32),(29,33),(30,3),(30,19),(30,25),(30,39),(31,10),(31,16),(31,28),(32,12),(32,22),(32,23),(32,37),(33,5),(33,20),(33,26),(33,38),(34,4),(34,22),(34,26),(34,36),(35,9),(35,14),(35,27),(35,33),(36,7),(36,21),(36,25),(36,35),(37,5),(37,17),(37,30),(38,8),(38,19),(38,29),(39,6),(39,21),(39,29),(39,36),(40,4),(40,14),(40,23);
/*!40000 ALTER TABLE `matchup_squad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (5,'Goalkeeper'),(6,'Defender'),(7,'Midfielder'),(8,'Forward');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `squad`
--

DROP TABLE IF EXISTS `squad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `squad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) DEFAULT NULL,
  `name` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `competition_id` int(11) DEFAULT NULL,
  `generation` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'First team, U21, U19, so on',
  PRIMARY KEY (`id`),
  KEY `fk_team_id_team_idx` (`team_id`),
  KEY `fk_squad_group1_idx` (`group_id`),
  KEY `fk_squad_competition1_idx` (`competition_id`),
  CONSTRAINT `fk_squad_competition1` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_group1` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_id_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squad`
--

LOCK TABLES `squad` WRITE;
/*!40000 ALTER TABLE `squad` DISABLE KEYS */;
INSERT INTO `squad` VALUES (21,42,NULL,NULL,9,'First Team'),(22,43,NULL,NULL,9,'First Team'),(23,44,NULL,NULL,9,'First Team'),(24,45,NULL,NULL,9,'First Team'),(25,46,NULL,NULL,9,'First Team'),(26,47,NULL,NULL,9,'First Team'),(27,48,NULL,NULL,9,'First Team'),(28,49,NULL,NULL,9,'First Team'),(29,50,NULL,NULL,9,'First Team'),(30,51,NULL,NULL,9,'First Team'),(31,52,NULL,NULL,9,'First Team'),(32,53,NULL,NULL,9,'First Team'),(33,54,NULL,NULL,9,'First Team'),(34,55,NULL,NULL,9,'First Team'),(35,56,NULL,NULL,9,'First Team'),(36,57,NULL,NULL,9,'First Team'),(37,58,NULL,NULL,9,'First Team'),(38,59,NULL,NULL,9,'First Team'),(39,60,NULL,NULL,9,'First Team'),(40,61,NULL,NULL,9,'First Team');
/*!40000 ALTER TABLE `squad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `squad_member`
--

DROP TABLE IF EXISTS `squad_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `squad_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hero_id` int(11) DEFAULT NULL,
  `position_id` int(2) DEFAULT NULL,
  `hero_role_id` tinyint(4) DEFAULT NULL,
  `squad_id` int(11) NOT NULL,
  `shirt_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_squad_hero1_idx` (`hero_id`),
  KEY `fk_squad_position1_idx` (`position_id`),
  KEY `fk_squad_hero_role1_idx` (`hero_role_id`),
  KEY `fk_squad_detail_squad1_idx` (`squad_id`),
  CONSTRAINT `fk_squad_member_hero1` FOREIGN KEY (`hero_id`) REFERENCES `hero` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_member_hero_role1` FOREIGN KEY (`hero_role_id`) REFERENCES `hero_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_member_position1` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_member_squad1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squad_member`
--

LOCK TABLES `squad_member` WRITE;
/*!40000 ALTER TABLE `squad_member` DISABLE KEYS */;
INSERT INTO `squad_member` VALUES (5,1,5,1,21,5),(6,2,5,1,21,6),(7,3,5,1,21,7),(8,4,6,1,21,8),(9,5,6,1,21,9),(10,6,6,1,21,10),(11,7,6,1,21,11),(12,8,6,1,21,12),(13,9,6,1,21,13),(14,10,6,1,21,14),(15,11,6,1,21,15),(16,12,6,1,21,16),(17,13,7,1,21,17),(18,14,7,1,21,18),(19,15,7,1,21,19),(20,16,8,1,21,20),(21,17,8,1,21,21),(22,18,8,1,21,22),(23,19,8,1,21,23),(24,20,8,1,21,24),(25,21,8,1,21,25),(26,22,7,1,21,26),(27,23,7,1,21,27),(28,24,7,1,21,28),(29,25,7,1,21,29),(30,26,7,1,21,30),(31,27,7,1,21,31),(32,28,7,1,21,32),(33,29,7,1,21,33),(34,30,8,1,21,34),(35,31,7,1,21,35),(36,32,8,1,21,36),(37,33,8,1,21,37),(68,34,5,1,22,68),(69,35,5,1,22,69),(70,36,5,1,22,70),(71,37,6,1,22,71),(72,38,6,1,22,72),(73,39,6,1,22,73),(74,40,6,1,22,74),(75,41,6,1,22,75),(76,42,6,1,22,76),(77,43,6,1,22,77),(78,44,6,1,22,78),(79,45,6,1,22,79),(80,46,6,1,22,80),(81,47,7,1,22,81),(82,48,7,1,22,82),(83,49,7,1,22,83),(84,50,7,1,22,84),(85,51,7,1,22,85),(86,52,7,1,22,86),(87,53,7,1,22,87),(88,54,8,1,22,88),(89,55,8,1,22,89),(90,56,8,1,22,90),(91,57,8,1,22,91);
/*!40000 ALTER TABLE `squad_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stadium`
--

DROP TABLE IF EXISTS `stadium`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stadium` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `built_since` date DEFAULT NULL,
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stadium_team1_idx` (`team_id`),
  CONSTRAINT `fk_stadium_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stadium`
--

LOCK TABLES `stadium` WRITE;
/*!40000 ALTER TABLE `stadium` DISABLE KEYS */;
INSERT INTO `stadium` VALUES (2,'Dean Court',NULL,NULL,11464,NULL,58),(3,'Emirates Stadium',NULL,NULL,60260,NULL,43),(4,'Villa Park',NULL,NULL,42660,NULL,61),(5,'Stamford Bridge',NULL,NULL,41798,NULL,56),(6,'Selhurst Park',NULL,NULL,25073,NULL,48),(7,'Goodison Park',NULL,NULL,39571,NULL,52),(8,'King Power Stadium',NULL,NULL,32312,NULL,46),(9,'Anfield',NULL,NULL,44742,NULL,50),(10,'Etihad Stadium',NULL,NULL,55097,NULL,42),(11,'Old Trafford',NULL,NULL,75653,NULL,45),(12,'St James\' Park',NULL,NULL,52338,NULL,60),(13,'Carrow Road',NULL,NULL,27010,NULL,57),(14,'St Mary\'s Stadium',NULL,NULL,32505,NULL,49),(15,'White Hart Lane',NULL,NULL,36284,NULL,47),(16,'Britannia Stadium',NULL,NULL,27740,NULL,55),(17,'Stadium of Light',NULL,NULL,48707,NULL,59),(18,'Liberty Stadium',NULL,NULL,20909,NULL,53),(19,'Vicarage Road',NULL,NULL,21500,NULL,54),(20,'The Hawthorns',NULL,NULL,26850,NULL,51),(21,'Boleyn Ground',NULL,NULL,35345,NULL,44);
/*!40000 ALTER TABLE `stadium` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `standing`
--

DROP TABLE IF EXISTS `standing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `standing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `point` int(11) DEFAULT NULL,
  `squad_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_standing_competing_team1_idx` (`squad_id`),
  CONSTRAINT `fk_standing_competing_team1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `standing`
--

LOCK TABLES `standing` WRITE;
/*!40000 ALTER TABLE `standing` DISABLE KEYS */;
/*!40000 ALTER TABLE `standing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats`
--

DROP TABLE IF EXISTS `stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stats_type_id` int(11) DEFAULT NULL,
  `player_id` int(11) DEFAULT NULL,
  `counted_for_team_id` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats`
--

LOCK TABLES `stats` WRITE;
/*!40000 ALTER TABLE `stats` DISABLE KEYS */;
/*!40000 ALTER TABLE `stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stats_type`
--

DROP TABLE IF EXISTS `stats_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stats_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stats_type`
--

LOCK TABLES `stats_type` WRITE;
/*!40000 ALTER TABLE `stats_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `stats_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `code` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `website` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alias` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_country1_idx` (`country_id`),
  CONSTRAINT `fk_team_country1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (21,'France','FRA',NULL,NULL,NULL,NULL,21),(22,'Albania','',NULL,NULL,NULL,NULL,22),(23,'Austria','',NULL,NULL,NULL,NULL,23),(24,'Belgium','',NULL,NULL,NULL,NULL,24),(25,'Croatia','',NULL,NULL,NULL,NULL,25),(26,'Czech Republic','',NULL,NULL,NULL,NULL,26),(27,'England','',NULL,NULL,NULL,NULL,27),(28,'Germany','',NULL,NULL,NULL,NULL,28),(29,'Iceland','',NULL,NULL,NULL,NULL,29),(30,'Italy','',NULL,NULL,NULL,NULL,30),(31,'Northern Ireland','',NULL,NULL,NULL,NULL,31),(32,'Poland','',NULL,NULL,NULL,NULL,32),(33,'Portugal','',NULL,NULL,NULL,NULL,33),(34,'Romania','',NULL,NULL,NULL,NULL,34),(35,'Russia','',NULL,NULL,NULL,NULL,35),(36,'Slovakia','',NULL,NULL,NULL,NULL,36),(37,'Spain','',NULL,NULL,NULL,NULL,37),(38,'Switzerland','',NULL,NULL,NULL,NULL,38),(39,'Turkey','',NULL,NULL,NULL,NULL,39),(40,'Wales','',NULL,NULL,NULL,NULL,40),(42,'Man City',NULL,NULL,NULL,NULL,NULL,NULL),(43,'Arsenal',NULL,NULL,NULL,NULL,NULL,NULL),(44,'West Ham',NULL,NULL,NULL,NULL,NULL,NULL),(45,'Man Utd',NULL,NULL,NULL,NULL,NULL,NULL),(46,'Leicester',NULL,NULL,NULL,NULL,NULL,NULL),(47,'Spurs',NULL,NULL,NULL,NULL,NULL,NULL),(48,'Crystal Palace',NULL,NULL,NULL,NULL,NULL,NULL),(49,'Southampton',NULL,NULL,NULL,NULL,NULL,NULL),(50,'Liverpool',NULL,NULL,NULL,NULL,NULL,NULL),(51,'West Brom',NULL,NULL,NULL,NULL,NULL,NULL),(52,'Everton',NULL,NULL,NULL,NULL,NULL,NULL),(53,'Swansea',NULL,NULL,NULL,NULL,NULL,NULL),(54,'Watford',NULL,NULL,NULL,NULL,NULL,NULL),(55,'Stoke',NULL,NULL,NULL,NULL,NULL,NULL),(56,'Chelsea',NULL,NULL,NULL,NULL,NULL,NULL),(57,'Norwich',NULL,NULL,NULL,NULL,NULL,NULL),(58,'Bournemouth',NULL,NULL,NULL,NULL,NULL,NULL),(59,'Sunderland',NULL,NULL,NULL,NULL,NULL,NULL),(60,'Newcastle',NULL,NULL,NULL,NULL,NULL,NULL),(61,'Aston Villa',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-06 18:30:45
