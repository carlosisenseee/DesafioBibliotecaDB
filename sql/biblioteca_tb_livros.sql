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
-- Table structure for table `tb_livros`
--

DROP TABLE IF EXISTS `tb_livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_livros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) DEFAULT NULL,
  `autor` varchar(45) DEFAULT NULL,
  `anoPublicacao` int DEFAULT NULL,
  `disponivel` tinyint(1) DEFAULT '1',
  `isbn` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_livros`
--

LOCK TABLES `tb_livros` WRITE;
/*!40000 ALTER TABLE `tb_livros` DISABLE KEYS */;
INSERT INTO `tb_livros` VALUES (21,'Lucíola','José de Alencar',1862,0,'9788520932194'),(22,'Quincas Borba','Machado de Assis',1891,0,'9788535910665'),(23,'Casa Velha','Machado de Assis',1885,0,'9788580444095'),(24,'Helena','Machado de Assis',1876,1,'9788520929989'),(25,'A Mão e a Luva','Machado de Assis',1874,1,'9788535932032'),(26,'Ressurreição','Machado de Assis',1872,1,'9788594318603'),(27,'Til','José de Alencar',1872,1,'9788572328891'),(28,'Diva','José de Alencar',1864,1,'9788544002135'),(29,'Cinco Minutos','José de Alencar',1856,1,'9788525416422'),(30,'A Viuvinha','José de Alencar',1857,1,'9788542800016'),(31,'Sonhos d´Ouro','José de Alencar',1872,1,'9786586498230'),(32,'Ubirajara','José de Alencar',1874,1,'9788579023959');
/*!40000 ALTER TABLE `tb_livros` ENABLE KEYS */;
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
