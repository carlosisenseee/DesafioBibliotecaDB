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
  `isbn` varchar(45) DEFAULT NULL,
  `disponivel` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_livros`
--

LOCK TABLES `tb_livros` WRITE;
/*!40000 ALTER TABLE `tb_livros` DISABLE KEYS */;
INSERT INTO `tb_livros` VALUES (1,'Dom Casmurro','Machado de Assis',1899,'12345',1),(2,'O Cortiço','Aluísio Azevedo',1890,'23456',1),(3,'Iracema','José de Alencar',1865,'34567',1),(4,'O Guarani','José de Alencar',1857,'45678',1),(7,'A Moreninha','Joaquim Manuel de Macedo',1844,'78901',1),(8,'O Ateneu','Raul Pompéia',1888,'89012',1),(9,'Lucíola','José de Alencar',1862,'90123',1),(10,'Quincas Borba','Machado de Assis',1891,'10234',1),(11,'Casa Velha','Machado de Assis',1885,'21345',1),(12,'Helena','Machado de Assis',1876,'32456',1),(13,'A Mão e a Luva','Machado de Assis',1874,'43567',1),(14,'Ressurreição','Machado de Assis',1872,'54678',1),(15,'Til','José de Alencar',1872,'65789',1),(16,'Diva','José de Alencar',1864,'76890',1),(17,'Cinco Minutos','José de Alencar',1856,'87901',1),(18,'A Viuvinha','José de Alencar',1857,'98012',1),(19,'Sonhos d´Ouro','José de Alencar',1872,'19123',1),(20,'Ubirajara','José de Alencar',1874,'20234',1);
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

-- Dump completed on 2025-06-30  9:16:54
