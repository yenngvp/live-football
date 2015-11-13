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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (8,'Euro 2016',2016,2016,'2016-06-09 17:00:00','2016-07-09 17:00:00',NULL,'CUP'),(9,'English Premier League',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(10,'Bundesliga',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(11,'Laliga',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(12,'League 1',2015,2016,'2015-08-07 17:00:00','2016-06-09 17:00:00',NULL,'LEAGUE'),(13,'WorldCup 2018',2018,2018,'2018-06-09 17:00:00','2018-07-09 17:00:00',NULL,'CUP'),(14,'V-League',NULL,NULL,NULL,NULL,NULL,NULL),(15,'Copa America',NULL,NULL,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Goal'),(2,'Penalty kick'),(3,'Corner kick');
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
INSERT INTO `hero` VALUES (1,'Willy','Caballero',NULL,0,0,0,'',1),(2,'Joe','Hart',NULL,NULL,NULL,NULL,NULL,1),(3,'Richard','Wright',NULL,NULL,NULL,NULL,NULL,1),(4,'Nicolas','Otamendi',NULL,NULL,NULL,NULL,NULL,1),(5,'Gael','Clichy',NULL,NULL,NULL,NULL,NULL,1),(6,'Martin','Demichelis',NULL,NULL,NULL,NULL,NULL,1),(7,'Jason','Denayer',NULL,NULL,NULL,NULL,NULL,1),(8,'Aleksandar','Kolarov',NULL,NULL,NULL,NULL,NULL,1),(9,'Vincent','Kompany',NULL,NULL,NULL,NULL,NULL,1),(10,'Eliaquim','Mangala',NULL,NULL,NULL,NULL,NULL,1),(11,'Bacary','Sagna',NULL,NULL,NULL,NULL,NULL,1),(12,'Pablo','Zabaleta',NULL,NULL,NULL,NULL,NULL,1),(13,'Fabian','Delph',NULL,NULL,NULL,NULL,NULL,1),(14,'Fernando','Francisco Reges',NULL,NULL,NULL,NULL,NULL,1),(15,'Fernandinho','Luiz Roza',NULL,NULL,NULL,NULL,NULL,1),(16,'Samir','Nasri',NULL,NULL,NULL,NULL,NULL,1),(17,'Jesus','Navas',NULL,NULL,NULL,NULL,NULL,1),(18,'David','Silva',NULL,NULL,NULL,NULL,NULL,1),(19,'Kevin','De Bruyne',NULL,NULL,NULL,NULL,NULL,1),(20,'Raheem','Sterling',NULL,NULL,NULL,NULL,NULL,1),(21,'Patrick','Roberts',NULL,NULL,NULL,NULL,NULL,1),(22,'Yaya','Toure',NULL,NULL,NULL,NULL,NULL,1),(23,'Bruno','Zuculini',NULL,NULL,NULL,NULL,NULL,1),(24,'Sergio','Aguero',NULL,NULL,NULL,NULL,NULL,1),(25,'Wilfried','Bony',NULL,NULL,NULL,NULL,NULL,1),(26,'Edin','Dzeko',NULL,NULL,NULL,NULL,NULL,1),(27,'Kelechi','Iheanacho',NULL,NULL,NULL,NULL,NULL,1),(28,'Stevan','Jovetic',NULL,NULL,NULL,NULL,NULL,1),(29,'Manuel','Pellegrini',NULL,NULL,NULL,NULL,NULL,1),(30,'Ruben','Cousillas Fuse',NULL,NULL,NULL,NULL,NULL,1),(31,'Xabier','Mancisidor',NULL,NULL,NULL,NULL,NULL,1),(32,'Jose','Cabello',NULL,NULL,NULL,NULL,NULL,1),(33,'Brian','Kidd',NULL,NULL,NULL,NULL,NULL,1),(34,'David','Ospina',NULL,NULL,NULL,NULL,NULL,1),(35,'Petr','Cech',NULL,NULL,NULL,NULL,NULL,1),(36,'Mathieu','Debuchy',NULL,NULL,NULL,NULL,NULL,1),(37,'Kieran','Gibbs',NULL,NULL,NULL,NULL,NULL,1),(38,'Per','Mertesacker',NULL,NULL,NULL,NULL,NULL,1),(39,'Gabriel','',NULL,NULL,NULL,NULL,NULL,1),(40,'Laurent','Koscielny',NULL,NULL,NULL,NULL,NULL,1),(41,'Nacho','Monreal',NULL,NULL,NULL,NULL,NULL,1),(42,'Calum','Chambers',NULL,NULL,NULL,NULL,NULL,1),(43,'Hector','Bellerin',NULL,NULL,NULL,NULL,NULL,1),(44,'Tomas','Rosicky',NULL,NULL,NULL,NULL,NULL,1),(45,'Mikel','Arteta',NULL,NULL,NULL,NULL,NULL,1),(46,'Jack','Wilshere',NULL,NULL,NULL,NULL,NULL,1),(47,'Mesut','Ozil',NULL,NULL,NULL,NULL,NULL,1),(48,'Alex','Oxlade-Chamberlain',NULL,NULL,NULL,NULL,NULL,1),(49,'Aaron','Ramsey',NULL,NULL,NULL,NULL,NULL,1),(50,'Santi','Cazorla',NULL,NULL,NULL,NULL,NULL,1),(51,'Mathieu','Flamini',NULL,NULL,NULL,NULL,NULL,1),(52,'Francis','Coquelin',NULL,NULL,NULL,NULL,NULL,1),(53,'Olivier','Giroud',NULL,NULL,NULL,NULL,NULL,1),(54,'Theo','Walcott',NULL,NULL,NULL,NULL,NULL,1),(55,'Alexis','Sanchez',NULL,NULL,NULL,NULL,NULL,1),(56,'Danny','Welbeck',NULL,NULL,NULL,NULL,NULL,1),(57,'Joel','Campbell',NULL,NULL,NULL,NULL,NULL,1);
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
  `result` tinyint(4) DEFAULT '-1',
  `matchday` tinyint(4) DEFAULT '0',
  `point` tinyint(4) DEFAULT '0',
  `status` tinyint(4) DEFAULT '-1',
  `stadium_id` int(11) DEFAULT NULL,
  `round` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `featured` tinyint(1) DEFAULT NULL,
  `manual_mode` tinyint(1) DEFAULT '0',
  `competition_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_matchup_stadium1_idx` (`stadium_id`),
  KEY `fk_matchup_competition1_idx` (`competition_id`),
  KEY `fk_matchup_matchup_status1_idx` (`status`),
  CONSTRAINT `fk_matchup_competition1` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_matchup_status1` FOREIGN KEY (`status`) REFERENCES `matchup_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_stadium1` FOREIGN KEY (`stadium_id`) REFERENCES `stadium` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup`
--

LOCK TABLES `matchup` WRITE;
/*!40000 ALTER TABLE `matchup` DISABLE KEYS */;
INSERT INTO `matchup` VALUES (3,'Crystal Palace vs West Brom',NULL,NULL,0,1,NULL,1,3,'11',1,0,9),(4,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',0,1,NULL,1,3,'11',1,0,9),(5,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',0,1,NULL,1,3,'11',1,0,9),(6,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',0,1,NULL,1,3,'11',1,0,9),(7,NULL,'2015-10-03 14:00:00','2015-10-03 15:45:00',0,1,NULL,1,3,'11',0,0,9),(8,'Sunderland vs West Ham',NULL,NULL,0,1,NULL,1,3,'11',0,0,9),(9,NULL,'2015-10-03 16:30:00','2015-10-03 18:15:00',0,1,NULL,1,3,'11',NULL,0,9),(10,NULL,'2015-10-04 12:30:00','2015-10-04 14:15:00',0,1,NULL,1,3,'11',NULL,0,9),(11,NULL,'2015-10-04 15:00:00','2015-10-04 16:45:00',1,1,NULL,1,3,'11',NULL,0,9),(12,NULL,'2015-10-04 15:00:00','2015-10-04 16:45:00',0,1,NULL,1,3,'11',NULL,0,9),(13,NULL,'2015-10-17 11:45:00','2015-10-17 13:30:00',0,1,NULL,1,3,'11',NULL,0,9),(14,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,1,NULL,1,3,'11',NULL,0,9),(15,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,1,NULL,1,3,'11',NULL,0,9),(16,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,1,NULL,1,3,'11',NULL,0,9),(17,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,1,NULL,1,3,'11',NULL,0,9),(18,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,1,NULL,1,3,'11',NULL,0,9),(19,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,1,NULL,1,3,'11',NULL,0,9),(20,NULL,'2015-10-17 14:00:00','2015-10-17 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(21,NULL,'2015-10-15 15:00:00','2015-10-15 16:45:00',0,2,NULL,1,3,'12',NULL,0,9),(22,NULL,'2015-10-14 19:00:00','2015-10-14 20:45:00',0,2,NULL,1,3,'12',NULL,0,9),(23,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(24,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(25,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(26,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(27,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(28,'Everton vs Arsenal',NULL,NULL,1,2,NULL,1,3,'12',0,0,9),(29,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(30,NULL,'2015-10-24 14:00:00','2015-10-24 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(32,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(33,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(34,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(35,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(36,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(37,NULL,'2015-10-31 14:00:00','2015-10-31 15:45:00',0,2,NULL,1,3,'12',NULL,0,9),(38,NULL,'2015-10-31 14:00:00',NULL,0,2,NULL,1,3,'12',NULL,0,9),(39,NULL,'2015-10-31 14:00:00',NULL,0,2,NULL,1,3,'12',NULL,0,9),(40,'',NULL,NULL,0,11,0,1,18,'11',1,0,9);
/*!40000 ALTER TABLE `matchup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchup_detail`
--

DROP TABLE IF EXISTS `matchup_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchup_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `squad_id` int(11) NOT NULL,
  `matchup_id` int(11) NOT NULL,
  `is_home_squad` tinyint(1) DEFAULT '0',
  `is_first_squad` tinyint(1) DEFAULT '0',
  `goal` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_matchup_squad_squad1_idx` (`squad_id`),
  KEY `fk_matchup_squad_matchup1_idx` (`matchup_id`),
  CONSTRAINT `fk_matchup_squad_matchup1` FOREIGN KEY (`matchup_id`) REFERENCES `matchup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_squad_squad1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_detail`
--

LOCK TABLES `matchup_detail` WRITE;
/*!40000 ALTER TABLE `matchup_detail` DISABLE KEYS */;
INSERT INTO `matchup_detail` VALUES (1,21,6,0,0,0),(2,21,17,0,0,0),(3,21,35,0,0,0),(4,22,11,0,0,11),(5,22,20,0,0,0),(6,22,28,1,1,7),(7,22,37,0,0,0),(8,23,8,0,0,0),(9,23,15,0,0,0),(10,23,27,0,0,0),(11,23,38,0,0,0),(12,24,11,0,0,0),(13,24,16,0,0,0),(14,24,34,0,0,0),(15,25,7,0,0,0),(16,25,18,0,0,0),(17,25,24,0,0,0),(18,25,39,0,0,0),(19,26,12,0,0,0),(20,26,13,0,0,0),(21,26,30,0,0,0),(22,27,3,0,0,0),(23,27,15,0,0,0),(24,27,24,0,0,0),(25,27,34,0,0,0),(26,28,9,0,0,0),(27,28,18,0,0,0),(28,28,32,0,0,0),(29,29,10,0,0,0),(30,29,13,0,0,0),(31,29,32,0,0,0),(32,29,33,0,0,0),(33,30,3,0,0,0),(34,30,19,0,0,0),(35,30,25,0,0,0),(36,30,39,0,0,0),(37,31,10,0,0,0),(38,31,16,0,0,0),(39,31,28,0,0,5),(40,32,12,0,0,0),(41,32,22,0,0,0),(42,32,23,0,0,0),(43,32,37,0,0,0),(44,33,5,0,0,0),(45,33,20,0,0,0),(46,33,26,0,0,0),(47,33,38,0,0,0),(48,34,4,0,0,0),(49,34,22,0,0,0),(50,34,26,0,0,0),(51,34,36,0,0,0),(52,35,9,0,0,0),(53,35,14,0,0,0),(54,35,27,0,0,0),(55,35,33,0,0,0),(56,36,7,0,0,0),(57,36,21,0,0,0),(58,36,25,0,0,0),(59,36,35,0,0,0),(60,37,5,0,0,0),(61,37,17,0,0,0),(62,37,30,0,0,0),(63,38,8,0,0,0),(64,38,19,0,0,0),(65,38,29,0,0,0),(66,39,6,0,0,0),(67,39,21,0,0,0),(68,39,29,0,0,0),(69,39,36,0,0,0),(70,40,4,0,0,0),(71,40,14,0,0,0),(72,40,23,0,0,0),(73,21,40,1,1,0),(74,22,40,0,0,0);
/*!40000 ALTER TABLE `matchup_detail` ENABLE KEYS */;
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
  CONSTRAINT `fk_matchup_live_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_live_matchup1` FOREIGN KEY (`matchup_id`) REFERENCES `matchup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_live_matchup_register1` FOREIGN KEY (`matchup_register_id`) REFERENCES `matchup_register` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Information for team line-up, subtitues, formation, so on';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_live`
--

LOCK TABLES `matchup_live` WRITE;
/*!40000 ALTER TABLE `matchup_live` DISABLE KEYS */;
INSERT INTO `matchup_live` VALUES (34,11,1,'2015-11-12 16:46:11',5,11),(37,11,1,NULL,0,11),(38,11,1,NULL,0,11),(39,11,1,NULL,0,11),(40,11,1,NULL,0,11),(41,11,1,NULL,0,11),(42,11,1,NULL,0,11),(43,11,1,NULL,0,11),(44,11,1,NULL,0,11),(45,11,1,NULL,0,11),(46,11,1,NULL,0,11);
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
  `matchup_detail_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lineup_substitue_squad1_idx` (`squad_member_id`),
  KEY `fk_lineup_substitue_position1_idx` (`position_id`),
  KEY `fk_matchup_register_matchup1_idx` (`matchup_detail_id`),
  CONSTRAINT `fk_matchup_register_matchup_detail1` FOREIGN KEY (`matchup_detail_id`) REFERENCES `matchup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_register_position1` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_register_squad_member1` FOREIGN KEY (`squad_member_id`) REFERENCES `squad_member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_register`
--

LOCK TABLES `matchup_register` WRITE;
/*!40000 ALTER TABLE `matchup_register` DISABLE KEYS */;
INSERT INTO `matchup_register` VALUES (10,0,22,8,4),(11,1,89,8,4);
/*!40000 ALTER TABLE `matchup_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchup_status`
--

DROP TABLE IF EXISTS `matchup_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchup_status` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `short_name` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchup_status`
--

LOCK TABLES `matchup_status` WRITE;
/*!40000 ALTER TABLE `matchup_status` DISABLE KEYS */;
INSERT INTO `matchup_status` VALUES (1,'Not Begin','NA'),(2,'Just Begin',''),(3,'First Half','FH'),(4,'Half Time','HT'),(5,'Second Half','SH'),(6,'Full Time','FT'),(7,'Postposed',NULL),(8,'Cancelled',NULL),(9,'Rescheduled',NULL);
/*!40000 ALTER TABLE `matchup_status` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squad`
--

LOCK TABLES `squad` WRITE;
/*!40000 ALTER TABLE `squad` DISABLE KEYS */;
INSERT INTO `squad` VALUES (21,42,NULL,NULL,9,'First Team'),(22,43,NULL,NULL,9,'First Team'),(23,44,NULL,NULL,9,'First Team'),(24,45,NULL,NULL,9,'First Team'),(25,46,NULL,NULL,9,'First Team'),(26,47,NULL,NULL,9,'First Team'),(27,48,NULL,NULL,9,'First Team'),(28,49,NULL,NULL,9,'First Team'),(29,50,NULL,NULL,9,'First Team'),(30,51,NULL,NULL,9,'First Team'),(31,52,NULL,NULL,9,'First Team'),(32,53,NULL,NULL,9,'First Team'),(33,54,NULL,NULL,9,'First Team'),(34,55,NULL,NULL,9,'First Team'),(35,56,NULL,NULL,9,'First Team'),(36,57,NULL,NULL,9,'First Team'),(37,58,NULL,NULL,9,'First Team'),(38,59,NULL,NULL,9,'First Team'),(39,60,NULL,NULL,9,'First Team'),(40,61,NULL,NULL,9,'First Team'),(41,28,NULL,NULL,8,'First Team');
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
  `point` int(11) DEFAULT '0',
  `squad_id` int(11) NOT NULL,
  `played` int(11) DEFAULT '0',
  `won` int(11) DEFAULT '0',
  `lost` int(11) DEFAULT '0',
  `drawn` int(11) DEFAULT '0',
  `current_position` tinyint(4) DEFAULT '0',
  `previous_position` tinyint(4) DEFAULT '0',
  `timestamp` timestamp NULL DEFAULT NULL,
  `goals_scored` int(11) DEFAULT '0',
  `goals_against` int(11) DEFAULT '0',
  `entered_live` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `squad_id_UNIQUE` (`squad_id`),
  KEY `fk_standing_competing_team1_idx` (`squad_id`),
  CONSTRAINT `fk_standing_competing_team1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `standing`
--

LOCK TABLES `standing` WRITE;
/*!40000 ALTER TABLE `standing` DISABLE KEYS */;
INSERT INTO `standing` VALUES (3,7,35,4,0,0,4,2,2,NULL,0,0,1),(4,4,21,4,0,0,4,10,10,NULL,0,0,1),(5,9,22,5,2,0,3,1,1,NULL,9,5,1),(6,4,23,4,0,0,4,11,11,NULL,0,0,1),(7,2,24,3,0,1,2,20,20,NULL,0,2,1),(8,4,25,4,0,0,4,12,12,NULL,0,0,1),(9,3,26,3,0,0,3,16,16,NULL,0,0,1),(10,4,27,4,0,0,4,13,13,NULL,0,0,1),(11,3,28,3,0,0,3,17,17,NULL,0,0,1),(12,4,29,4,0,0,4,9,9,NULL,0,0,1),(13,4,30,4,0,0,4,8,8,NULL,0,0,1),(14,2,31,3,0,1,2,19,19,NULL,5,7,1),(15,4,32,4,0,0,4,3,3,NULL,0,0,1),(16,4,33,4,0,0,4,4,4,NULL,0,0,1),(17,4,34,4,0,0,4,5,5,NULL,0,0,1),(18,4,36,4,0,0,4,6,6,NULL,0,0,1),(19,3,37,3,0,0,3,14,14,NULL,0,0,1),(20,3,38,3,0,0,3,15,15,NULL,0,0,1),(21,4,39,4,0,0,4,7,7,NULL,0,0,1),(22,3,40,3,0,0,3,18,18,NULL,0,0,1);
/*!40000 ALTER TABLE `standing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `standing_live`
--

DROP TABLE IF EXISTS `standing_live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `standing_live` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `point` int(11) DEFAULT '0',
  `squad_id` int(11) NOT NULL,
  `played` int(11) DEFAULT '0',
  `won` int(11) DEFAULT '0',
  `lost` int(11) DEFAULT '0',
  `drawn` int(11) DEFAULT '0',
  `current_position` tinyint(4) DEFAULT '0',
  `previous_position` tinyint(4) DEFAULT '0',
  `timestamp` timestamp NULL DEFAULT NULL,
  `goals_scored` int(11) DEFAULT '0',
  `goals_against` int(11) DEFAULT '0',
  `standing_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_standing_live_standing1_idx` (`id`),
  KEY `fk_standing_live_standing_idx` (`standing_id`),
  CONSTRAINT `fk_standing_live_standing` FOREIGN KEY (`standing_id`) REFERENCES `standing` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `standing_live`
--

LOCK TABLES `standing_live` WRITE;
/*!40000 ALTER TABLE `standing_live` DISABLE KEYS */;
INSERT INTO `standing_live` VALUES (23,9,22,5,2,0,3,1,1,NULL,9,5,5),(24,7,35,4,0,0,4,2,2,NULL,0,0,3),(25,4,32,4,0,0,4,3,3,NULL,0,0,15),(26,4,33,4,0,0,4,4,4,NULL,0,0,16),(27,4,34,4,0,0,4,5,5,NULL,0,0,17),(28,4,36,4,0,0,4,6,6,NULL,0,0,18),(29,4,39,4,0,0,4,7,7,NULL,0,0,21),(30,4,30,4,0,0,4,8,8,NULL,0,0,13),(31,4,29,4,0,0,4,9,9,NULL,0,0,12),(32,4,21,4,0,0,4,10,10,NULL,0,0,4),(33,4,23,4,0,0,4,11,11,NULL,0,0,6),(34,4,25,4,0,0,4,12,12,NULL,0,0,8),(35,4,27,4,0,0,4,13,13,NULL,0,0,10),(36,3,37,3,0,0,3,14,14,NULL,0,0,19),(37,3,38,3,0,0,3,15,15,NULL,0,0,20),(38,3,26,3,0,0,3,16,16,NULL,0,0,9),(39,3,28,3,0,0,3,17,17,NULL,0,0,11),(40,3,40,3,0,0,3,18,18,NULL,0,0,22),(41,2,31,3,0,1,2,19,19,NULL,5,7,14),(42,2,24,3,0,1,2,20,20,NULL,0,2,7);
/*!40000 ALTER TABLE `standing_live` ENABLE KEYS */;
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

-- Dump completed on 2015-11-13 17:37:56
