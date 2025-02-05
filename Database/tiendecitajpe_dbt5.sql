CREATE DATABASE  IF NOT EXISTS `tiendecitajpe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tiendecitajpe`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: tiendecitajpe
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulos` (
  `idArticulo` int NOT NULL AUTO_INCREMENT,
  `descripcionArticulo` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `precioArticulo` decimal(6,2) NOT NULL,
  `cantidadArticulo` int NOT NULL,
  PRIMARY KEY (`idArticulo`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (1,'Pastel',9.95,10),(3,'Bollito',0.45,19),(4,'Refresco',1.00,30),(5,'Sándwich',0.95,10),(6,'Cebolla',0.15,200),(7,'Ajo',0.23,60),(8,'Fregona',2.30,100),(9,'Estropajo',0.20,500),(10,'Leche',0.65,300),(11,'Atún',0.30,56),(12,'Piña',1.50,60),(13,'Tomate',0.56,1000),(14,'Lechuga',0.56,100),(15,'Café',1.00,20),(16,'Patatas',0.50,300),(17,'Coliflor',0.20,40),(18,'Fresa',0.10,500);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pertenencias`
--

DROP TABLE IF EXISTS `pertenencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pertenencias` (
  `idPertenencia` int NOT NULL AUTO_INCREMENT,
  `idArticuloFK` int NOT NULL,
  `cantidadArticuloTicket` int NOT NULL,
  `idTicketFK` int NOT NULL,
  PRIMARY KEY (`idPertenencia`),
  KEY `idArticuloFK_idx` (`idArticuloFK`),
  KEY `idTicketFK_idx` (`idTicketFK`),
  CONSTRAINT `idArticuloFK` FOREIGN KEY (`idArticuloFK`) REFERENCES `articulos` (`idArticulo`) ON DELETE RESTRICT,
  CONSTRAINT `idTicketFK` FOREIGN KEY (`idTicketFK`) REFERENCES `tickets` (`idTicket`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pertenencias`
--

LOCK TABLES `pertenencias` WRITE;
/*!40000 ALTER TABLE `pertenencias` DISABLE KEYS */;
INSERT INTO `pertenencias` VALUES (7,1,1,3),(8,4,30,3),(9,5,30,3),(10,5,99,1),(11,1,99,1),(12,16,20,4),(13,17,3,4),(14,18,2,5),(15,15,45,5),(16,10,74,6),(17,12,4,7),(18,15,31,7),(19,17,5,8),(20,18,20,9);
/*!40000 ALTER TABLE `pertenencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `fechaTicket` date NOT NULL,
  `totalTicket` decimal(8,2) NOT NULL,
  PRIMARY KEY (`idTicket`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,'2024-11-28',1079.10),(3,'2024-11-27',68.45),(4,'2024-12-01',10.60),(5,'2024-12-05',45.20),(6,'2024-12-23',48.10),(7,'2025-01-01',37.00),(8,'2025-01-12',1.00),(9,'2025-01-20',2.00);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-05 23:15:05
