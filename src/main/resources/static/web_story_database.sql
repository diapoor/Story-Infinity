-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: web_story_database
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--
DROP DATABASE IF EXIST web_story_database;
CREATE DATABASE web_story_database;
USE web_story_database;
DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `information` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (3,'Author3','author3@example.com','Information about Author3'),(4,'Author4','author4@example.com','Information about Author4'),(5,'Author5','author5@example.com','Information about Author5'),(6,'Author6','author6@example.com','Information about Author6'),(7,'Author7','author7@example.com','Information about Author7'),(8,'Author8','author8@example.com','Information about Author8'),(9,'Author9','author9@example.com','Information about Author9'),(10,'Author10','author10@example.com','Information about Author10'),(11,'Author11','author11@example.com','Information about Author11'),(12,'Author12','author12@example.com','Information about Author12'),(13,'Author13','author13@example.com','Information about Author13'),(14,'Author14','author14@example.com','Information about Author14'),(15,'Author15','author15@example.com','Information about Author15'),(16,'Author16','author16@example.com','Information about Author16'),(17,'Author17','author17@example.com','Information about Author17'),(18,'Author18','author18@example.com','Information about Author18'),(19,'Author19','author19@example.com','Information about Author19'),(20,'Author20','author20@example.com','Information about Author20'),(21,'Author21','author21@example.com','Information about Author21'),(22,'Author22','author22@example.com','Information about Author22'),(23,'Author23','author23@example.com','Information about Author23'),(24,'Author24','author24@example.com','Information about Author24'),(25,'Author25','author25@example.com','Information about Author25'),(26,'Author26','author26@example.com','Information about Author26'),(27,'Author27','author27@example.com','Information about Author27'),(28,'Author28','author28@example.com','Information about Author28'),(29,'Author29','author29@example.com','Information about Author29'),(30,'Author30','author30@example.com','Information about Author30'),(31,'Author31','author31@example.com','Information about Author31'),(32,'Author32','author32@example.com','Information about Author32'),(33,'Author33','author33@example.com','Information about Author33'),(34,'Author34','author34@example.com','Information about Author34'),(35,'Author35','author35@example.com','Information about Author35'),(36,'Author36','author36@example.com','Information about Author36'),(37,'Author37','author37@example.com','Information about Author37'),(38,'Author38','author38@example.com','Information about Author38'),(39,'Author39','author39@example.com','Information about Author39'),(40,'Author40','author40@example.com','Information about Author40'),(41,'Author41','author41@example.com','Information about Author41'),(42,'Author42','author42@example.com','Information about Author42'),(43,'Author43','author43@example.com','Information about Author43'),(44,'Author44','author44@example.com','Information about Author44'),(45,'Author45','author45@example.com','Information about Author45'),(46,'Author46','author46@example.com','Information about Author46'),(47,'Author47','author47@example.com','Information about Author47'),(48,'Author48','author48@example.com','Information about Author48'),(49,'Author49','author49@example.com','Information about Author49'),(50,'Author50','author50@example.com','Information about Author50'),(51,'Author51','author51@example.com','Information about Author51'),(52,'Author52','author52@example.com','Information about Author52'),(53,'Author53','author53@example.com','Information about Author53'),(54,'Author54','author54@example.com','Information about Author54'),(55,'Author55','author55@example.com','Information about Author55'),(56,'Author56','author56@example.com','Information about Author56'),(57,'Author57','author57@example.com','Information about Author57'),(58,'Author58','author58@example.com','Information about Author58'),(59,'Author59','author59@example.com','Information about Author59'),(60,'Author60','author60@example.com','Information about Author60'),(61,'Author61','author61@example.com','Information about Author61'),(62,'Author62','author62@example.com','Information about Author62'),(66,'hhh','oeoe9999999999999999ea@gil.com','oèo óe oe');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `story_id` int NOT NULL,
  `chapter_name` varchar(50) DEFAULT NULL,
  `content_chapter` text,
  `datepost` date DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  KEY `story_id` (`story_id`),
  CONSTRAINT `chapters_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=439 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
INSERT INTO `chapters` VALUES (254,3,'Chapter 1','Content of Chapter 1 for Story 3','2023-12-26'),(255,4,'Chapter 1','Content of Chapter 1 for Story 4','2023-12-26'),(256,5,'Chapter 1','Content of Chapter 1 for Story 5','2023-12-26'),(257,6,'Chapter 1','Content of Chapter 1 for Story 6','2023-12-26'),(258,7,'Chapter 1','Content of Chapter 1 for Story 7','2023-12-26'),(259,8,'Chapter 1','Content of Chapter 1 for Story 8','2023-12-26'),(260,9,'Chapter 1','Content of Chapter 1 for Story 9','2023-12-26'),(261,10,'Chapter 1','Content of Chapter 1 for Story 10','2023-12-26'),(262,11,'Chapter 1','Content of Chapter 1 for Story 11','2023-12-26'),(263,12,'Chapter 1','Content of Chapter 1 for Story 12','2023-12-26'),(264,13,'Chapter 1','Content of Chapter 1 for Story 13','2023-12-26'),(265,14,'Chapter 1','Content of Chapter 1 for Story 14','2023-12-26'),(266,15,'Chapter 1','Content of Chapter 1 for Story 15','2023-12-26'),(267,16,'Chapter 1','Content of Chapter 1 for Story 16','2023-12-26'),(268,17,'Chapter 1','Content of Chapter 1 for Story 17','2023-12-26'),(269,18,'Chapter 1','Content of Chapter 1 for Story 18','2023-12-26'),(270,19,'Chapter 1','Content of Chapter 1 for Story 19','2023-12-26'),(271,20,'Chapter 1','Content of Chapter 1 for Story 20','2023-12-26'),(272,21,'Chapter 1','Content of Chapter 1 for Story 21','2023-12-26'),(273,22,'Chapter 1','Content of Chapter 1 for Story 22','2023-12-26'),(274,23,'Chapter 1','Content of Chapter 1 for Story 23','2023-12-26'),(275,24,'Chapter 1','Content of Chapter 1 for Story 24','2023-12-26'),(276,25,'Chapter 1','Content of Chapter 1 for Story 25','2023-12-26'),(277,26,'Chapter 1','Content of Chapter 1 for Story 26','2023-12-26'),(278,27,'Chapter 1','Content of Chapter 1 for Story 27','2023-12-26'),(279,28,'Chapter 1','Content of Chapter 1 for Story 28','2023-12-26'),(280,29,'Chapter 1','Content of Chapter 1 for Story 29','2023-12-26'),(281,30,'Chapter 1','Content of Chapter 1 for Story 30','2023-12-26'),(282,31,'Chapter 1','Content of Chapter 1 for Story 31','2023-12-26'),(283,32,'Chapter 1','Content of Chapter 1 for Story 32','2023-12-26'),(284,33,'Chapter 1','Content of Chapter 1 for Story 33','2023-12-26'),(285,34,'Chapter 1','Content of Chapter 1 for Story 34','2023-12-26'),(286,35,'Chapter 1','Content of Chapter 1 for Story 35','2023-12-26'),(287,36,'Chapter 1','Content of Chapter 1 for Story 36','2023-12-26'),(288,37,'Chapter 1','Content of Chapter 1 for Story 37','2023-12-26'),(289,38,'Chapter 1','Content of Chapter 1 for Story 38','2023-12-26'),(290,39,'Chapter 1','Content of Chapter 1 for Story 39','2023-12-26'),(291,40,'Chapter 1','Content of Chapter 1 for Story 40','2023-12-26'),(292,41,'Chapter 1','Content of Chapter 1 for Story 41','2023-12-26'),(293,42,'Chapter 1','Content of Chapter 1 for Story 42','2023-12-26'),(294,43,'Chapter 1','Content of Chapter 1 for Story 43','2023-12-26'),(295,44,'Chapter 1','Content of Chapter 1 for Story 44','2023-12-26'),(296,45,'Chapter 1','Content of Chapter 1 for Story 45','2023-12-26'),(297,46,'Chapter 1','Content of Chapter 1 for Story 46','2023-12-26'),(298,47,'Chapter 1','Content of Chapter 1 for Story 47','2023-12-26'),(299,48,'Chapter 1','Content of Chapter 1 for Story 48','2023-12-26'),(300,49,'Chapter 1','Content of Chapter 1 for Story 49','2023-12-26'),(301,50,'Chapter 1','Content of Chapter 1 for Story 50','2023-12-26'),(302,51,'Chapter 1','Content of Chapter 1 for Story 51','2023-12-26'),(303,52,'Chapter 1','Content of Chapter 1 for Story 52','2023-12-26'),(304,53,'Chapter 1','Content of Chapter 1 for Story 53','2023-12-26'),(305,54,'Chapter 1','Content of Chapter 1 for Story 54','2023-12-26'),(306,55,'Chapter 1','Content of Chapter 1 for Story 55','2023-12-26'),(307,56,'Chapter 1','Content of Chapter 1 for Story 56','2023-12-26'),(308,57,'Chapter 1','Content of Chapter 1 for Story 57','2023-12-26'),(309,58,'Chapter 1','Content of Chapter 1 for Story 58','2023-12-26'),(310,59,'Chapter 1','Content of Chapter 1 for Story 59','2023-12-26'),(311,60,'Chapter 1','Content of Chapter 1 for Story 60','2023-12-26'),(312,61,'Chapter 1','Content of Chapter 1 for Story 61','2023-12-26'),(313,62,'Chapter 1','Content of Chapter 1 for Story 62','2023-12-26'),(314,3,'Chapter 1','Content of Chapter 1 for Story 3','2023-12-26'),(315,4,'Chapter 1','Content of Chapter 1 for Story 4','2023-12-26'),(316,5,'Chapter 1','Content of Chapter 1 for Story 5','2023-12-26'),(317,6,'Chapter 1','Content of Chapter 1 for Story 6','2023-12-26'),(318,7,'Chapter 1','Content of Chapter 1 for Story 7','2023-12-26'),(319,8,'Chapter 1','Content of Chapter 1 for Story 8','2023-12-26'),(320,9,'Chapter 1','Content of Chapter 1 for Story 9','2023-12-26'),(321,10,'Chapter 1','Content of Chapter 1 for Story 10','2023-12-26'),(322,11,'Chapter 1','Content of Chapter 1 for Story 11','2023-12-26'),(323,12,'Chapter 1','Content of Chapter 1 for Story 12','2023-12-26'),(324,13,'Chapter 1','Content of Chapter 1 for Story 13','2023-12-26'),(325,14,'Chapter 1','Content of Chapter 1 for Story 14','2023-12-26'),(326,15,'Chapter 1','Content of Chapter 1 for Story 15','2023-12-26'),(327,16,'Chapter 1','Content of Chapter 1 for Story 16','2023-12-26'),(328,17,'Chapter 1','Content of Chapter 1 for Story 17','2023-12-26'),(329,18,'Chapter 1','Content of Chapter 1 for Story 18','2023-12-26'),(330,19,'Chapter 1','Content of Chapter 1 for Story 19','2023-12-26'),(331,20,'Chapter 1','Content of Chapter 1 for Story 20','2023-12-26'),(332,21,'Chapter 1','Content of Chapter 1 for Story 21','2023-12-26'),(333,22,'Chapter 1','Content of Chapter 1 for Story 22','2023-12-26'),(334,23,'Chapter 1','Content of Chapter 1 for Story 23','2023-12-26'),(335,24,'Chapter 1','Content of Chapter 1 for Story 24','2023-12-26'),(336,25,'Chapter 1','Content of Chapter 1 for Story 25','2023-12-26'),(337,26,'Chapter 1','Content of Chapter 1 for Story 26','2023-12-26'),(338,27,'Chapter 1','Content of Chapter 1 for Story 27','2023-12-26'),(339,28,'Chapter 1','Content of Chapter 1 for Story 28','2023-12-26'),(340,29,'Chapter 1','Content of Chapter 1 for Story 29','2023-12-26'),(341,30,'Chapter 1','Content of Chapter 1 for Story 30','2023-12-26'),(342,31,'Chapter 1','Content of Chapter 1 for Story 31','2023-12-26'),(343,32,'Chapter 1','Content of Chapter 1 for Story 32','2023-12-26'),(344,33,'Chapter 1','Content of Chapter 1 for Story 33','2023-12-26'),(345,34,'Chapter 1','Content of Chapter 1 for Story 34','2023-12-26'),(346,35,'Chapter 1','Content of Chapter 1 for Story 35','2023-12-26'),(347,36,'Chapter 1','Content of Chapter 1 for Story 36','2023-12-26'),(348,37,'Chapter 1','Content of Chapter 1 for Story 37','2023-12-26'),(349,38,'Chapter 1','Content of Chapter 1 for Story 38','2023-12-26'),(350,39,'Chapter 1','Content of Chapter 1 for Story 39','2023-12-26'),(351,40,'Chapter 1','Content of Chapter 1 for Story 40','2023-12-26'),(352,41,'Chapter 1','Content of Chapter 1 for Story 41','2023-12-26'),(353,42,'Chapter 1','Content of Chapter 1 for Story 42','2023-12-26'),(354,43,'Chapter 1','Content of Chapter 1 for Story 43','2023-12-26'),(355,44,'Chapter 1','Content of Chapter 1 for Story 44','2023-12-26'),(356,45,'Chapter 1','Content of Chapter 1 for Story 45','2023-12-26'),(357,46,'Chapter 1','Content of Chapter 1 for Story 46','2023-12-26'),(358,47,'Chapter 1','Content of Chapter 1 for Story 47','2023-12-26'),(359,48,'Chapter 1','Content of Chapter 1 for Story 48','2023-12-26'),(360,49,'Chapter 1','Content of Chapter 1 for Story 49','2023-12-26'),(361,50,'Chapter 1','Content of Chapter 1 for Story 50','2023-12-26'),(362,51,'Chapter 1','Content of Chapter 1 for Story 51','2023-12-26'),(363,52,'Chapter 1','Content of Chapter 1 for Story 52','2023-12-26'),(364,53,'Chapter 1','Content of Chapter 1 for Story 53','2023-12-26'),(365,54,'Chapter 1','Content of Chapter 1 for Story 54','2023-12-26'),(366,55,'Chapter 1','Content of Chapter 1 for Story 55','2023-12-26'),(367,56,'Chapter 1','Content of Chapter 1 for Story 56','2023-12-26'),(368,57,'Chapter 1','Content of Chapter 1 for Story 57','2023-12-26'),(369,58,'Chapter 1','Content of Chapter 1 for Story 58','2023-12-26'),(370,59,'Chapter 1','Content of Chapter 1 for Story 59','2023-12-26'),(371,60,'Chapter 1','Content of Chapter 1 for Story 60','2023-12-26'),(372,61,'Chapter 1','Content of Chapter 1 for Story 61','2023-12-26'),(373,62,'Chapter 1','Content of Chapter 1 for Story 62','2023-12-26'),(374,3,'Chapter 1','Content of Chapter 1 for Story 3','2023-12-26'),(375,4,'Chapter 1','Content of Chapter 1 for Story 4','2023-12-26'),(376,5,'Chapter 1','Content of Chapter 1 for Story 5','2023-12-26'),(377,6,'Chapter 1','Content of Chapter 1 for Story 6','2023-12-26'),(378,7,'Chapter 1','Content of Chapter 1 for Story 7','2023-12-26'),(379,8,'Chapter 1','Content of Chapter 1 for Story 8','2023-12-26'),(380,9,'Chapter 1','Content of Chapter 1 for Story 9','2023-12-26'),(381,10,'Chapter 1','Content of Chapter 1 for Story 10','2023-12-26'),(382,11,'Chapter 1','Content of Chapter 1 for Story 11','2023-12-26'),(383,12,'Chapter 1','Content of Chapter 1 for Story 12','2023-12-26'),(384,13,'Chapter 1','Content of Chapter 1 for Story 13','2023-12-26'),(385,14,'Chapter 1','Content of Chapter 1 for Story 14','2023-12-26'),(386,15,'Chapter 1','Content of Chapter 1 for Story 15','2023-12-26'),(387,16,'Chapter 1','Content of Chapter 1 for Story 16','2023-12-26'),(388,17,'Chapter 1','Content of Chapter 1 for Story 17','2023-12-26'),(389,18,'Chapter 1','Content of Chapter 1 for Story 18','2023-12-26'),(390,19,'Chapter 1','Content of Chapter 1 for Story 19','2023-12-26'),(391,20,'Chapter 1','Content of Chapter 1 for Story 20','2023-12-26'),(392,21,'Chapter 1','Content of Chapter 1 for Story 21','2023-12-26'),(393,22,'Chapter 1','Content of Chapter 1 for Story 22','2023-12-26'),(394,23,'Chapter 1','Content of Chapter 1 for Story 23','2023-12-26'),(395,24,'Chapter 1','Content of Chapter 1 for Story 24','2023-12-26'),(396,25,'Chapter 1','Content of Chapter 1 for Story 25','2023-12-26'),(397,26,'Chapter 1','Content of Chapter 1 for Story 26','2023-12-26'),(398,27,'Chapter 1','Content of Chapter 1 for Story 27','2023-12-26'),(399,28,'Chapter 1','Content of Chapter 1 for Story 28','2023-12-26'),(400,29,'Chapter 1','Content of Chapter 1 for Story 29','2023-12-26'),(401,30,'Chapter 1','Content of Chapter 1 for Story 30','2023-12-26'),(402,31,'Chapter 1','Content of Chapter 1 for Story 31','2023-12-26'),(403,32,'Chapter 1','Content of Chapter 1 for Story 32','2023-12-26'),(404,33,'Chapter 1','Content of Chapter 1 for Story 33','2023-12-26'),(405,34,'Chapter 1','Content of Chapter 1 for Story 34','2023-12-26'),(406,35,'Chapter 1','Content of Chapter 1 for Story 35','2023-12-26'),(407,36,'Chapter 1','Content of Chapter 1 for Story 36','2023-12-26'),(408,37,'Chapter 1','Content of Chapter 1 for Story 37','2023-12-26'),(409,38,'Chapter 1','Content of Chapter 1 for Story 38','2023-12-26'),(410,39,'Chapter 1','Content of Chapter 1 for Story 39','2023-12-26'),(411,40,'Chapter 1','Content of Chapter 1 for Story 40','2023-12-26'),(412,41,'Chapter 1','Content of Chapter 1 for Story 41','2023-12-26'),(413,42,'Chapter 1','Content of Chapter 1 for Story 42','2023-12-26'),(414,43,'Chapter 1','Content of Chapter 1 for Story 43','2023-12-26'),(415,44,'Chapter 1','Content of Chapter 1 for Story 44','2023-12-26'),(416,45,'Chapter 1','Content of Chapter 1 for Story 45','2023-12-26'),(417,46,'Chapter 1','Content of Chapter 1 for Story 46','2023-12-26'),(418,47,'Chapter 1','Content of Chapter 1 for Story 47','2023-12-26'),(419,48,'Chapter 1','Content of Chapter 1 for Story 48','2023-12-26'),(420,49,'Chapter 1','Content of Chapter 1 for Story 49','2023-12-26'),(421,50,'Chapter 1','Content of Chapter 1 for Story 50','2023-12-26'),(422,51,'Chapter 1','Content of Chapter 1 for Story 51','2023-12-26'),(423,52,'Chapter 1','Content of Chapter 1 for Story 52','2023-12-26'),(424,53,'Chapter 1','Content of Chapter 1 for Story 53','2023-12-26'),(425,54,'Chapter 1','Content of Chapter 1 for Story 54','2023-12-26'),(426,55,'Chapter 1','Content of Chapter 1 for Story 55','2023-12-26'),(427,56,'Chapter 1','Content of Chapter 1 for Story 56','2023-12-26'),(428,57,'Chapter 1','Content of Chapter 1 for Story 57','2023-12-26'),(429,58,'Chapter 1','Content of Chapter 1 for Story 58','2023-12-26'),(430,59,'Chapter 1','Content of Chapter 1 for Story 59','2023-12-26'),(431,60,'Chapter 1','Content of Chapter 1 for Story 60','2023-12-26'),(432,61,'Chapter 1','Content of Chapter 1 for Story 61','2023-12-26'),(433,62,'Chapter 1','Content of Chapter 1 for Story 62','2023-12-26'),(438,51,'óóosiiiiiiô','jklfffffffffffffffffffdsfjlf',NULL);
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `story_id` int NOT NULL,
  `content` varchar(500) NOT NULL,
  `date_comment` timestamp NULL DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  KEY `story_id` (`story_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (3,2,6,'goodddddddddddddd','2023-12-29 10:00:00'),(4,2,3,'best contetn comment','2023-12-29 10:00:00'),(5,29,3,'wow so cute','2023-12-29 10:00:00'),(6,30,3,'ỳe hú','2023-12-29 10:00:00');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Le Trung Duc','Male','2001-07-19','Phương Mai,Kim Lien,Ha Noi'),(2,'Nguyen Huu Tung','Male','2000-08-08','Hanoi'),(3,'Nguyen Long Vu','male','2000-01-01','no bro pblem'),(5,'Vu Duc Manh','male','2000-01-01','hanoi'),(6,'Bui Van Manh','male','2007-09-09','hanoii');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `user_id` int NOT NULL,
  `story_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`story_id`),
  KEY `story_id` (`story_id`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (2,9),(2,13),(29,34),(2,60);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Mystery','Intriguing and puzzling narratives'),(2,'Thriller','Suspenseful and exciting plots'),(3,'Science Fiction','Imaginative and futuristic stories'),(4,'Fantasy','Magical worlds and mythical creatures'),(5,'Romance','Love and emotional connections'),(6,'Historical Fiction','Set in historical periods with fictional elements'),(7,'Adventure','Exciting journeys and exploration'),(8,'Horror','Scary and chilling tales'),(9,'Drama','Emotional and character-driven narratives'),(10,'Action','High-energy and intense plots'),(11,'Comedy','Humorous and entertaining stories'),(12,'Biography','Life stories of real people'),(13,'Autobiography','Life stories written by the subject'),(14,'Self-Help','Guidance for personal development'),(15,'Science','Exploration of scientific concepts'),(16,'Fantasy','Imaginary worlds and magical elements'),(17,'Crime','Involves criminal activities and investigations'),(18,'Romantic Comedy','Blend of romance and humor'),(19,'Historical Romance','Romantic stories set in the past'),(20,'Non-Fiction','Factual and informative narratives'),(21,'Classic','Timeless and enduring works'),(22,'Modern','Contemporary and current themes'),(23,'Children\'s','Targeted for young readers'),(24,'Young Adult','Geared towards teenage audiences'),(25,'Manga','Japanese comic books and graphic novels'),(26,'Graphic Novel','Storytelling through sequential art'),(27,'Science Fantasy','Blending elements of science fiction and fantasy'),(28,'Dystopian','Set in a bleak and oppressive future'),(29,'Historical Non-Fiction','Factual narratives set in the past'),(30,'Mystery','Intriguing and puzzling narratives'),(31,'Thriller','Suspenseful and exciting plots'),(32,'Science Fiction','Imaginative and futuristic stories'),(33,'Fantasy','Magical worlds and mythical creatures'),(34,'Romance','Love and emotional connections'),(35,'Historical Fiction','Set in historical periods with fictional elements'),(36,'Adventure','Exciting journeys and exploration'),(37,'Horror','Scary and chilling tales'),(38,'Drama','Emotional and character-driven narratives'),(39,'Action','High-energy and intense plots'),(40,'Comedy','Humorous and entertaining stories'),(41,'Biography','Life stories of real people'),(42,'Autobiography','Life stories written by the subject'),(43,'Self-Help','Guidance for personal development'),(44,'Science','Exploration of scientific concepts'),(45,'Fantasy','Imaginary worlds and magical elements'),(46,'Crime','Involves criminal activities and investigations'),(47,'Romantic Comedy','Blend of romance and humor'),(48,'Historical Romance','Romantic stories set in the past'),(49,'Non-Fiction','Factual and informative narratives'),(50,'Classic','Timeless and enduring works'),(51,'Modern','Contemporary and current themes'),(52,'Children\'s','Targeted for young readers'),(53,'Young Adult','Geared towards teenage audiences'),(54,'Manga','Japanese comic books and graphic novels'),(55,'Graphic Novel','Storytelling through sequential art'),(56,'Science Fantasy','Blending elements of science fiction and fantasy'),(57,'Dystopian','Set in a bleak and oppressive future'),(58,'Historical Non-Fiction','Factual narratives set in the past'),(59,'Mystery','Intriguing and puzzling narratives'),(60,'Thriller','Suspenseful and exciting plots'),(61,'Science Fiction','Imaginative and futuristic stories'),(62,'Fantasy','Magical worlds and mythical creatures'),(63,'Romance','Love and emotional connections'),(64,'Historical Fiction','Set in historical periods with fictional elements'),(65,'Adventure','Exciting journeys and exploration'),(66,'Horror','Scary and chilling tales'),(67,'Drama','Emotional and character-driven narratives'),(68,'Action','High-energy and intense plots'),(69,'Comedy','Humorous and entertaining stories'),(70,'Biography','Life stories of real people'),(71,'Autobiography','Life stories written by the subject'),(72,'Self-Help','Guidance for personal development'),(73,'Science','Exploration of scientific concepts'),(74,'Fantasy','Imaginary worlds and magical elements'),(75,'Crime','Involves criminal activities and investigations'),(76,'Romantic Comedy','Blend of romance and humor'),(77,'Historical Romance','Romantic stories set in the past'),(78,'Non-Fiction','Factual and informative narratives'),(79,'Classic','Timeless and enduring works'),(80,'Modern','Contemporary and current themes'),(81,'Children\'s','Targeted for young readers'),(82,'Young Adult','Geared towards teenage audiences'),(83,'Manga','Japanese comic books and graphic novels'),(84,'Graphic Novel','Storytelling through sequential art'),(85,'Science Fantasy','Blending elements of science fiction and fantasy'),(86,'Dystopian','Set in a bleak and oppressive future'),(87,'Historical Non-Fiction','Factual narratives set in the past');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `histories`
--

DROP TABLE IF EXISTS `histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `histories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `chapter_id` int NOT NULL,
  `dateread` date DEFAULT (curdate()),
  PRIMARY KEY (`id`),
  KEY `histories_ibfk_1` (`user_id`),
  KEY `histories_ibfk_3` (`chapter_id`),
  CONSTRAINT `histories_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `histories_ibfk_3` FOREIGN KEY (`chapter_id`) REFERENCES `chapters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `histories`
--

LOCK TABLES `histories` WRITE;
/*!40000 ALTER TABLE `histories` DISABLE KEYS */;
INSERT INTO `histories` VALUES (4,2,345,'2023-12-30'),(5,2,344,'2023-12-30'),(6,2,374,'2023-12-30'),(7,29,288,'2023-12-30'),(8,29,278,'2023-12-30');
/*!40000 ALTER TABLE `histories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `story_id` int NOT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `story_id` (`story_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`),
  CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (4,2,6,5),(5,2,8,5),(6,2,3,5),(7,2,5,5),(8,2,9,5),(9,2,17,5),(10,2,19,5),(11,2,54,5),(13,29,6,1);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER','This is a basic role for users who have registered and logged into the system. Typically grants basic access rights such as viewing personal information and performing common user activities.'),(2,'ROLE_ADMIN','Users with this role usually have access to and can manage the entire system, including user management, configurations, and other administrative features.'),(3,'ROLE_EDITOR',' this role is often granted to users who can edit and manage content, such as editors.'),(4,'ROLE_MODERATOR','Users with this role may have the ability to review, edit, or delete content contributed by other members.'),(5,'ROLE_API','Users with this role may have access to API endpoints and can perform operations related to the API.');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stories`
--

DROP TABLE IF EXISTS `stories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `user_id` int DEFAULT NULL,
  `process` tinyint(1) DEFAULT '0',
  `status` tinyint(1) DEFAULT '0',
  `datepost` date DEFAULT (curdate()),
  `description` varchar(500) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `stories_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stories`
--

LOCK TABLES `stories` WRITE;
/*!40000 ALTER TABLE `stories` DISABLE KEYS */;
INSERT INTO `stories` VALUES (3,'The Mysterious Case of the Stolen Diamonds',2,1,1,'2023-12-26','Detective Harris scanned the room, seeking clues to solve the mystery of the missing artifact. Rain poured as he followed footprints to a secluded mansion.','image3.jpg'),(4,'The Enigma of the Silent Witness',NULL,0,1,'2023-12-26','Detective Miller faced a labyrinth of deceit, unraveling the scandal\'s truth. Detective Nguyen confronted a silent witness holding the key to a case.','image4.jpg'),(5,'Shadows in the Dark Alley',NULL,1,1,'2023-12-26','Detective Turner decoded a message, revealing a sinister secret society. Detective King unveiled dark family secrets amid whispers of betrayal.','image5.jpg'),(6,'The Cryptic Codebreaker',2,1,1,'2023-12-26','Detective Carter pieced together shattered alibis, solving a complex murder. Detective Lee explored the paranormal in the haunted Hollow Manor.','image6.jpg'),(7,'A Trail of Deception',NULL,1,1,'2023-12-26','Detective Anderson deciphered the riddle of vanishing artifacts, racing against time. Detective Martinez pursued a killer striking only at midnight.','image7.jpg'),(8,'The Haunting of Hollow Manor',NULL,1,1,'2023-12-26','Detective Brown followed silent footsteps in a fog-shrouded city. Detective Reed tackled a cipher conspiracy, exposing a threatening plot.','image8.jpg'),(9,'Whispers in the Fog',NULL,1,1,'2023-12-26','Detective Taylor infiltrated a criminal organization, risking all. Detective Lewis faced echoes from the past while solving a cold case.','image9.jpg'),(10,'The Puzzle of the Vanishing Heiress',2,0,1,'2023-12-26','Detective Garcia navigated a labyrinth of lies, chasing down leads. Detective Patel sought the truth behind mysterious murders in a dark alley.','image10.jpg'),(11,'Midnight Murmurs',NULL,1,1,'2023-12-26','Detective White pursued deception\'s trail to catch a cunning criminal. Detective Harris solved the enigma of a vanishing heiress.','image11.jpg'),(12,'The Labyrinth of Lies',NULL,1,1,'2023-12-26','Detective Anderson confronted shadows in a dark alley, closer to an elusive killer. Detective Lee encountered a ghostly gambit in a haunted mansion.','image12.jpg'),(13,'Echoes from the Past',NULL,1,1,'2023-12-26','Detective Miller navigated whispers in the fog, exposing a conspiracy. Detective Nguyen chased the phantom thief with a talent for disappearing.','image13.jpg'),(14,'The Secret Society',NULL,1,1,'2023-12-26','Detective Turner unraveled the midnight murmurs, discovering a sinister plot. Detective King faced the puzzle of the cipher, decoding a dangerous message.','image14.jpg'),(15,'Crimson Roses and Broken Vows',2,0,1,'2023-12-26','Detective Carter confronted the riddle of vanishing artifacts, recovering treasures. Detective Lee followed silent footsteps to uncover a dark secret.','image15.jpg'),(16,'The Phantom Thief',NULL,1,1,'2023-12-26','Detective Brown explored echoes from the past, revealing buried secrets. Detective Reed exposed shattered alibis, solving a complicated case.','image16.jpg'),(17,'Whispers of Betrayal',NULL,0,1,'2023-12-26','Detective Taylor infiltrated the criminal underworld, gambling in a dangerous game. Detective Lewis faced the haunting of Hollow Manor.','image17.jpg'),(18,'The Riddle of the Vanishing Artifacts',NULL,1,1,'2023-12-26','Detective Garcia tackled the mysterious case of stolen diamonds, chasing the thief. Detective Patel deciphered the enigma of a silent witness.','image18.jpg'),(19,'Shattered Alibis',NULL,1,1,'2023-12-26','Detective White followed a trail of deception, uncovering hidden layers. Detective Harris tackled the cryptic codebreaker, solving a challenging case.','image19.jpg'),(20,'The Ghostly Gambit',29,1,1,'2023-12-26','Detective Anderson navigated whispers of betrayal, revealing a web of lies. Detective Miller confronted the labyrinth of lies, exposing the truth.','image20.jpg'),(21,'Silent Footsteps',NULL,1,1,'2023-12-26','Detective Nguyen pursued the phantom thief, mastering disguise. Detective Turner faced crimson roses and broken vows, love turned betrayal.','image21.jpg'),(22,'The Cipher Conspiracy',NULL,1,1,'2023-12-26','Detective King solved the riddle of a vanishing heiress, unraveling a tangled web. Detective Carter confronted midnight murmurs, revealing a dark plot.','image22.jpg'),(23,'The Mysterious Case of the Stolen Diamonds',NULL,0,1,'2023-12-26','Detective Lee unraveled the secret society, exposing hidden workings. Detective Brown navigated the ghostly gambit, where reality blurred.','image23.jpg'),(24,'The Enigma of the Silent Witness',NULL,0,1,'2023-12-26','Detective Harris faced the haunting of Hollow Manor, echoes of the past resounding. Detective Reed solved the mysterious case of stolen diamonds.','image24.jpg'),(25,'Shadows in the Dark Alley',30,1,1,'2023-12-26','Detective Taylor tackled the enigma of a silent witness, speaking without words. Detective Lewis chased a trail of deception, revealing a cunning criminal.','image25.jpg'),(26,'The Cryptic Codebreaker',NULL,0,1,'2023-12-26','Detective Garcia deciphered the cryptic codebreaker, facing a challenging task. Detective Patel confronted whispers of betrayal, weaving a web of lies.','image26.jpg'),(27,'A Trail of Deception',NULL,1,1,'2023-12-26','Detective White tackled the labyrinth of lies, exposing a complex network. Detective Harris navigated the puzzle of the vanishing heiress, solving the disappearance.','image27.jpg'),(28,'The Haunting of Hollow Manor',NULL,1,1,'2023-12-26','Detective Anderson followed shadows in a dark alley, uncovering an elusive killer. Detective Lee confronted a ghostly gambit, where the supernatural intertwined.','image28.jpg'),(29,'Whispers in the Fog',NULL,1,1,'2023-12-26','Detective Miller faced whispers in the fog, uncovering a hidden conspiracy. Detective Nguyen chased the phantom thief, mastering the art of disappearance.','image29.jpg'),(30,'The Puzzle of the Vanishing Heiress',31,1,1,'2023-12-26','Detective Turner unraveled the midnight murmurs, revealing a sinister plot. Detective King decoded the cipher, unraveling a dangerous message.','image30.jpg'),(31,'Midnight Murmurs',NULL,1,1,'2023-12-26','Detective Carter confronted the riddle of vanishing artifacts, recovering stolen treasures. Detective Lee followed silent footsteps, uncovering a dark secret.','image31.jpg'),(32,'The Labyrinth of Lies',NULL,0,1,'2023-12-26','Detective Brown explored echoes from the past, exposing long-buried secrets. Detective Reed pieced together shattered alibis, solving a complicated case.','image32.jpg'),(33,'Echoes from the Past',NULL,0,1,'2023-12-26','Detective Taylor infiltrated the criminal underworld, gambling in a dangerous game. Detective Lewis faced the haunting of Hollow Manor.','image33.jpg'),(34,'The Secret Society',32,1,1,'2023-12-26','Detective Garcia tackled the mysterious case of stolen diamonds, chasing the elusive thief. Detective Patel deciphered the enigma of a silent witness.','image34.jpg'),(35,'Crimson Roses and Broken Vows',NULL,1,1,'2023-12-26','Detective White followed a trail of deception, uncovering hidden layers. Detective Harris tackled the cryptic codebreaker, solving a challenging case.','image35.jpg'),(36,'The Phantom Thief',NULL,1,1,'2023-12-26','Detective Anderson navigated whispers of betrayal, revealing a web of lies. Detective Miller confronted the labyrinth of lies, exposing the truth.','image36.jpg'),(37,'Whispers of Betrayal',NULL,1,1,'2023-12-26','Detective Nguyen pursued the phantom thief, mastering disguise. Detective Turner faced crimson roses and broken vows, love turned betrayal.','image37.jpg'),(38,'The Riddle of the Vanishing Artifacts',NULL,1,1,'2023-12-26','Detective King solved the riddle of a vanishing heiress, unraveling a tangled web. Detective Carter confronted midnight murmurs, revealing a dark plot.','image38.jpg'),(39,'Shattered Alibis',29,0,1,'2023-12-26','Detective Lee unraveled the secret society, exposing hidden workings. Detective Brown navigated the ghostly gambit, where reality blurred.','image39.jpg'),(40,'The Ghostly Gambit',NULL,1,1,'2023-12-26','Detective Harris faced the haunting of Hollow Manor, echoes of the past resounding. Detective Reed solved the mysterious case of stolen diamonds.','image40.jpg'),(41,'Silent Footsteps',NULL,0,1,'2023-12-26','Detective Taylor tackled the enigma of a silent witness, speaking without words. Detective Lewis chased a trail of deception, revealing a cunning criminal.','image41.jpg'),(42,'The Cipher Conspiracy',NULL,1,1,'2023-12-26','Detective Garcia deciphered the cryptic codebreaker, facing a challenging task. Detective Patel confronted whispers of betrayal, weaving a web of lies.','image42.jpg'),(43,'The Mysterious Case of the Stolen Diamonds',2,0,1,'2023-12-26','Detective White tackled the labyrinth of lies, exposing a complex network. Detective Harris navigated the puzzle of the vanishing heiress, solving the disappearance.','image43.jpg'),(44,'The Enigma of the Silent Witness',NULL,0,1,'2023-12-26','Detective Anderson followed shadows in a dark alley, uncovering an elusive killer. Detective Lee confronted a ghostly gambit, where the supernatural intertwined.','image44.jpg'),(45,'Shadows in the Dark Alley',NULL,1,1,'2023-12-26','Detective Miller faced whispers in the fog, uncovering a hidden conspiracy. Detective Nguyen chased the phantom thief, mastering the art of disappearance.','image45.jpg'),(46,'The Cryptic Codebreaker',NULL,0,1,'2023-12-26','Detective Turner unraveled the midnight murmurs, revealing a sinister plot. Detective King decoded the cipher, unraveling a dangerous message.','image46.jpg'),(47,'A Trail of Deception',2,0,1,'2023-12-26','Detective Carter confronted the riddle of vanishing artifacts, recovering stolen treasures. Detective Lee followed silent footsteps, uncovering a dark secret.','image47.jpg'),(48,'The Haunting of Hollow Manor',NULL,1,1,'2023-12-26','Detective Brown explored echoes from the past, exposing long-buried secrets. Detective Reed pieced together shattered alibis, solving a complicated case.','image48.jpg'),(49,'Whispers in the Fog',NULL,0,1,'2023-12-26','Detective Taylor infiltrated the criminal underworld, gambling in a dangerous game. Detective Lewis faced the haunting of Hollow Manor.','image49.jpg'),(50,'The Puzzle of the Vanishing Heiress',NULL,0,1,'2023-12-26','Detective Garcia tackled the mysterious case of stolen diamonds, chasing the elusive thief. Detective Patel deciphered the enigma of a silent witness.','image50.jpg'),(51,'Midnight Murmurs',2,1,1,'2023-12-26','Detective White followed a trail of deception, uncovering hidden layers. Detective Harris tackled the cryptic codebreaker, solving a challenging case.','image51.jpg'),(52,'The Labyrinth of Lies',NULL,0,1,'2023-12-26','Detective Anderson navigated whispers of betrayal, revealing a web of lies. Detective Miller confronted the labyrinth of lies, exposing the truth.','image52.jpg'),(53,'Echoes from the Past',NULL,0,1,'2023-12-26','Detective Nguyen pursued the phantom thief, mastering disguise. Detective Turner faced crimson roses and broken vows, love turned betrayal.','image53.jpg'),(54,'The Secret Society',NULL,1,1,'2023-12-26','Detective King solved the riddle of a vanishing heiress, unraveling a tangled web. Detective Carter confronted midnight murmurs, revealing a dark plot.','image54.jpg'),(55,'Crimson Roses and Broken Vows',2,0,1,'2023-12-26','Detective Lee unraveled the secret society, exposing hidden workings. Detective Brown navigated the ghostly gambit, where reality blurred.','image55.jpg'),(56,'The Phantom Thief',NULL,0,1,'2023-12-26','Detective Harris faced the haunting of Hollow Manor, echoes of the past resounding. Detective Reed solved the mysterious case of stolen diamonds.','image56.jpg'),(57,'Whispers of Betrayal',NULL,1,1,'2023-12-26','Detective Taylor tackled the enigma of a silent witness, speaking without words. Detective Lewis chased a trail of deception, revealing a cunning criminal.','image57.jpg'),(58,'The Riddle of the Vanishing Artifacts',NULL,1,1,'2023-12-26','Detective Garcia deciphered the cryptic codebreaker, facing a challenging task. Detective Patel confronted whispers of betrayal, weaving a web of lies.','image58.jpg'),(59,'Shattered Alibis',NULL,1,1,'2023-12-26','Detective White tackled the labyrinth of lies, exposing a complex network. Detective Harris navigated the puzzle of the vanishing heiress, solving the disappearance.','image59.jpg'),(60,'The Ghostly Gambit',NULL,1,1,'2023-12-26','Detective Anderson followed shadows in a dark alley, uncovering an elusive killer. Detective Lee confronted a ghostly gambit, where the supernatural intertwined.','image60.jpg'),(61,'Silent Footsteps',NULL,0,1,'2023-12-26','Detective Miller faced whispers in the fog, uncovering a hidden conspiracy. Detective Nguyen chased the phantom thief, mastering the art of disappearance.','image61.jpg'),(62,'The Cipher Conspiracy',NULL,1,1,'2023-12-26','Detective Turner unraveled the midnight murmurs, revealing a sinister plot. Detective King decoded the cipher, unraveling a dangerous message.','image62.jpg');
/*!40000 ALTER TABLE `stories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story_author`
--

DROP TABLE IF EXISTS `story_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `story_author` (
  `story_id` int NOT NULL,
  `author_id` int NOT NULL,
  PRIMARY KEY (`author_id`,`story_id`),
  KEY `story_id` (`story_id`),
  CONSTRAINT `story_author_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`),
  CONSTRAINT `story_author_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story_author`
--

LOCK TABLES `story_author` WRITE;
/*!40000 ALTER TABLE `story_author` DISABLE KEYS */;
INSERT INTO `story_author` VALUES (3,41),(4,61),(5,60),(6,40),(7,58),(8,57),(9,39),(9,56),(10,38),(11,54),(12,37),(12,53),(13,36),(13,52),(14,35),(14,51),(15,33),(16,49),(17,32),(17,48),(18,31),(18,47),(19,30),(19,46),(20,45),(20,59),(21,44),(22,43),(23,42),(24,41),(25,40),(26,39),(27,38),(28,37),(29,36),(30,35),(31,34),(32,33),(33,32),(34,31),(35,30),(36,29),(37,28),(38,27),(39,26),(40,25),(41,24),(42,23),(44,21),(45,20),(46,19),(48,17),(49,16),(50,15),(50,43),(52,14),(52,42),(53,13),(53,52),(54,12),(54,58),(56,10),(56,54),(57,9),(57,55),(58,8),(58,56),(59,3),(59,7),(60,3),(60,6),(61,5),(61,34),(62,4),(62,22);
/*!40000 ALTER TABLE `story_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story_genres`
--

DROP TABLE IF EXISTS `story_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `story_genres` (
  `story_id` int NOT NULL,
  `genres_id` int NOT NULL,
  PRIMARY KEY (`story_id`,`genres_id`),
  KEY `genres_id` (`genres_id`),
  CONSTRAINT `story_genres_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`),
  CONSTRAINT `story_genres_ibfk_2` FOREIGN KEY (`genres_id`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story_genres`
--

LOCK TABLES `story_genres` WRITE;
/*!40000 ALTER TABLE `story_genres` DISABLE KEYS */;
INSERT INTO `story_genres` VALUES (5,1),(12,1),(24,1),(45,1),(56,1),(41,2),(52,2),(54,2),(58,2),(18,3),(20,3),(43,3),(31,4),(44,4),(13,5),(32,5),(60,5),(10,6),(62,6),(21,7),(46,7),(23,8),(29,8),(30,8),(17,9),(49,9),(53,9),(3,10),(47,10),(25,11),(33,11),(7,12),(48,12),(57,12),(61,12),(22,13),(35,13),(42,14),(59,14),(14,15),(19,15),(22,16),(37,16),(51,16);
/*!40000 ALTER TABLE `story_genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(2,29),(2,36),(3,29),(3,30),(3,31),(3,34),(3,35),(4,29),(4,31),(5,29);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `nick_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,NULL,'ducute','$2a$10$d0.P/7REb9EbXemtG5GBrOOZSe6qWtnqGgj5wdxYnFVvFQy9p4Fwm','dfdf@gam.com','2001-12-12','ducccc'),(29,1,'diaPoor','$2a$10$v30UClgkcqvlC8iehF3eout5LYQtVioe6Q28kPzWXb.anoFspn9YS','duc.lt.521@aptechlearning.edu.vn','2001-01-19','DiaExcute'),(30,3,'LongVux','$2a$10$lXDEs94T8IcozjqBGWn8RenosUFbJG3eFhn2.av3jgLl.7xlQKMB2','LongVux@gmail.ee','2002-01-18','LongVuxcute'),(31,2,'TungHuu','$2a$10$4QMfkWPs0XvkVXllT3G23ucUQ1YllRiQmpoRi09HqaOR330F5XqIq','TungHuu@gmail.oo','1990-01-13','TungHuExcute'),(32,5,'ManhVu','$2a$10$HksZ4mYHn3MlbXnYzshFRuspxuRSP8LVWrehh1S9zdeoaRlAmLfN.','ManhVu@gmail.yy','2007-01-10','ManhVuExcute'),(33,6,'ManhBu','$2a$10$rE2hMskOSayuR5lyUCgXPOnT.fjRm0Jub9vgxWIszv9QxyZktRNym','ManhBu@gmail.yy','2003-01-10','ManhBuExcute'),(34,2,'namnguyen','$2a$10$RsX1OtTrFYhyR3p6GO/5a.5GqDrrRHNrVGHtC7ewM3OuJqvPEmcki','nam@gmail.com','2000-01-01','namdz'),(35,6,'haidovan','$2a$10$1/pkRNuAJGTKZXaJFvxiouQIq8BC52gHu5FUkcYmqDqiJNVgjlPVC','haido@gmail.com','2000-01-14','haido'),(36,2,'tahuynhmai','$2a$10$LqHptghR2mZkaS35TINbNOl0K3B7lEPNgTUKnrvcjStsp2jeGHrLy','huynhmai@gmail.com','2005-07-27','maihuynh'),(37,NULL,'nguyenthan','$2a$10$OgjV3rutCkaVtOgmhD4X6u8F8VKQo5SDqHe5HRU0tsGwAyQGk0.Ym','thanh@gmail.com','2004-01-06','thannguyen'),(38,NULL,'phunglan','$2a$10$aQxaOUVpSypBc.Pr.OpWvecObWPgiGIe3KUbvnV4RcndCTaSlhbNW','lanphung@gmail.com','1996-03-23','lanphung'),(39,NULL,'phungtu','$2a$10$WK9XkmZXoEGfnJo9dlb31.ym2thaHE.uKKlbviP6zfnP3GSmnvHWK','tu@gail.com','1995-04-23','tuphung');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-02  8:12:20
