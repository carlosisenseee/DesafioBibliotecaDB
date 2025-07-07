CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `emprestimos_ativos` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES (1,'Carlos Alfredo Isensee','10928571920',3),(2,'Yan Victor Morais','12345',0),(3,'Lionel Messi','12345678901',0),(4,'Cristiano Ronaldo','23456789012',0),(5,'Michael Jackson','34567890123',0),(6,'Mike Tyson','45678901234',0),(7,'Will Smith','56789012345',0),(8,'Dwayne Johnson','67890123456',0),(9,'Leonardo DiCaprio','78901234567',0),(10,'Robert Downey Jr','89012345678',0),(11,'Tom Cruise','90123456789',0),(12,'Brad Pitt','10234567890',0),(13,'Angelina Jolie','21345678901',0),(14,'Jennifer Lawrence','32456789012',0),(15,'Scarlett Johansson','43567890123',0),(16,'Emma Stone','54678901234',0),(17,'Ryan Reynolds','65789012345',0),(18,'Chris Evans','76890123456',0),(19,'Chris Hemsworth','87901234567',0),(20,'Mark Wahlberg','98012345678',0),(21,'Vin Diesel','19123456789',0),(22,'Jason Statham','20234567890',0);
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-07 10:31:35
