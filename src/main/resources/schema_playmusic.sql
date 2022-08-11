CREATE DATABASE  IF NOT EXISTS `dbmusic` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbmusic`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: dbmusic
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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id` bigint NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `cover_big` varchar(255) DEFAULT NULL,
  `cover_medium` varchar(255) DEFAULT NULL,
  `cover_small` varchar(255) DEFAULT NULL,
  `cover_xl` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `fans` int DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nb_tracks` int DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tracklist` varchar(255) DEFAULT NULL,
  `genre_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmhihrmrx2f1mhqtrbagwru45g` (`genre_id`),
  CONSTRAINT `FKmhihrmrx2f1mhqtrbagwru45g` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `id` bigint NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nb_album` int DEFAULT NULL,
  `nb_fan` int DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `picture_big` varchar(255) DEFAULT NULL,
  `picture_medium` varchar(255) DEFAULT NULL,
  `picture_small` varchar(255) DEFAULT NULL,
  `picture_xl` varchar(255) DEFAULT NULL,
  `share` varchar(255) DEFAULT NULL,
  `track_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `dislikes` int NOT NULL,
  `likes` int NOT NULL,
  `track_id` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgvmrwo2ndvaej7ihhllxgemr2` (`track_id`),
  KEY `FK7f6bx0qgp8t4ehq2vqfps1xni` (`user_name`),
  CONSTRAINT `FK7f6bx0qgp8t4ehq2vqfps1xni` FOREIGN KEY (`user_name`) REFERENCES `users` (`username`),
  CONSTRAINT `FKgvmrwo2ndvaej7ihhllxgemr2` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment_replies`
--

DROP TABLE IF EXISTS `comment_replies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_replies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `dislikes` int NOT NULL,
  `likes` int NOT NULL,
  `comment_id` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9eka2pjxubgmpdx3qqfsvfack` (`comment_id`),
  KEY `FKgrc3kgtl33milbgcgdvaiakrm` (`user_name`),
  CONSTRAINT `FK9eka2pjxubgmpdx3qqfsvfack` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKgrc3kgtl33milbgcgdvaiakrm` FOREIGN KEY (`user_name`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `picture_big` varchar(255) DEFAULT NULL,
  `picture_medium` varchar(255) DEFAULT NULL,
  `picture_small` varchar(255) DEFAULT NULL,
  `picture_xl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `track_id` bigint NOT NULL,
  `username` varchar(255) NOT NULL,
  `is_like` bit(1) DEFAULT NULL,
  PRIMARY KEY (`track_id`,`username`),
  KEY `FK5tgmgqmu12rs6t9gv4i1laia3` (`username`),
  CONSTRAINT `FK5tgmgqmu12rs6t9gv4i1laia3` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FKhdv9wd57c92vreeswif03m604` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `likes_of_comment`
--

DROP TABLE IF EXISTS `likes_of_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes_of_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_disliked` bit(1) NOT NULL,
  `is_liked` bit(1) NOT NULL,
  `comment_id` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4gaqob48r5jy014p4vyiexnfy` (`comment_id`),
  KEY `FKrs024383yie57on2oj2hit7lo` (`user_name`),
  CONSTRAINT `FK4gaqob48r5jy014p4vyiexnfy` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKrs024383yie57on2oj2hit7lo` FOREIGN KEY (`user_name`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnorrq3ogghbs6odh0ki9esocl` (`username`),
  CONSTRAINT `FKnorrq3ogghbs6odh0ki9esocl` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track` (
  `id` bigint NOT NULL,
  `duration` int DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `preview` varchar(255) DEFAULT NULL,
  `ranks` int DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `album_id` bigint DEFAULT NULL,
  `artist_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaxm9pbgk7ptorfyk3o6911q05` (`album_id`),
  KEY `FKi28jadqiuqk1dlxtl0me7hqh2` (`artist_id`),
  CONSTRAINT `FKaxm9pbgk7ptorfyk3o6911q05` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`),
  CONSTRAINT `FKi28jadqiuqk1dlxtl0me7hqh2` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `track_has_playlist`
--

DROP TABLE IF EXISTS `track_has_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track_has_playlist` (
  `playlist_id` bigint NOT NULL,
  `track_id` bigint NOT NULL,
  PRIMARY KEY (`playlist_id`,`track_id`),
  KEY `FKp12kq61ohwcopor6nd5qdmb6b` (`track_id`),
  CONSTRAINT `FKp12kq61ohwcopor6nd5qdmb6b` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`),
  CONSTRAINT `FKr7s8v999omrw6nieuwydqckki` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_locked` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK4qu1gr772nnf6ve5af002rwya` (`role_id`),
  CONSTRAINT `FK4qu1gr772nnf6ve5af002rwya` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'dbmusic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-11  9:58:36
