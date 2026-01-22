-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: projeto_financiamentos
-- ------------------------------------------------------
-- Server version	8.0.43
--- Schema projeto_financiamentos, rode esse arquivo para criar as tabelas necessárias

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
-- Table structure for table `apartamentos`
--

DROP TABLE IF EXISTS `apartamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartamentos` (
  `id_apartamento` int NOT NULL AUTO_INCREMENT,
  `valorImovel` double NOT NULL,
  `prazoFinanciamento` int NOT NULL,
  `taxaJurosAnual` double NOT NULL,
  `vagasGaragem` int NOT NULL,
  `numeroAndar` int NOT NULL,
  PRIMARY KEY (`id_apartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `casas`
--

DROP TABLE IF EXISTS `casas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `casas` (
  `id_casa` int NOT NULL AUTO_INCREMENT,
  `valorImovel` double NOT NULL,
  `prazoFinanciamento` int NOT NULL,
  `taxaJurosAnual` double NOT NULL,
  `valorFixo` int NOT NULL,
  `tamanhoAreaConstruida` double NOT NULL,
  `tamanhoAreaTerreno` double NOT NULL,
  PRIMARY KEY (`id_casa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `terrenos`
--

DROP TABLE IF EXISTS `terrenos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terrenos` (
  `id_terreno` int NOT NULL AUTO_INCREMENT,
  `valorImovel` double NOT NULL,
  `prazoFinanciamento` int NOT NULL,
  `taxaJurosAnual` double NOT NULL,
  `zonaLocalizada` varchar(100) NOT NULL,
  PRIMARY KEY (`id_terreno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Estrutura de tabela para usuários
CREATE TABLE users (
user_id INT AUTO_INCREMENT PRIMARY KEY,
login_cpf VARCHAR(11) UNIQUE NOT NULL,
senha_hash VARCHAR(255) NOT NULL
)

-- Dump completed on 2026-01-13  9:03:23
