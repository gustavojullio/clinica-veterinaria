CREATE DATABASE  IF NOT EXISTS `clinica_veterinaria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinica_veterinaria`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: clinica_veterinaria
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `raca` varchar(45) NOT NULL,
  `peso` float NOT NULL,
  `idade` int NOT NULL,
  `id_veterinario` int NOT NULL,
  `id_dono` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_animal_veterinario` (`id_veterinario`),
  KEY `fk_animal_dono` (`id_dono`),
  CONSTRAINT `fk_animal_dono` FOREIGN KEY (`id_dono`) REFERENCES `dono` (`id`),
  CONSTRAINT `fk_animal_veterinario` FOREIGN KEY (`id_veterinario`) REFERENCES `veterinario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (16,'Peixonauta','Peixe',15,2,26,13),(17,'Ted ','Cachorro',20,3,28,14),(18,'Bob','Cachorro',14,3,26,12),(19,'Rex','Labrador Retriever',30,3,31,15),(20,'Bella','Pastor Alemão',25,3,28,16),(21,'Simba','Leão',190,6,29,14),(22,'Daniel','Humano',86,17,29,14),(23,'Jubiscleisson','Vira lata',8,7,28,16);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dono`
--

DROP TABLE IF EXISTS `dono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dono` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `idade` int NOT NULL,
  `cpf` bigint NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dono`
--

LOCK TABLES `dono` WRITE;
/*!40000 ALTER TABLE `dono` DISABLE KEYS */;
INSERT INTO `dono` VALUES (12,'Joaquim Silva',33,4642288456,'Rua Alfredo Neves','33984521478'),(13,'Elves Vieira',41,2514699872,'Rua Esmeralda','33984365214'),(14,'Sara Alvernaz',22,12645510772,'Avenida Pio de Meira','33984632541'),(15,'Laura Martins',42,1567891234,'Rua da Amizade, 567, Bairro Novo, Cidade Serena','12345678901'),(16,'Pedro Alves',25,18765432101,'Avenida Central, 789, Bairro Ensolarado, Cidade Feliz','23678901234');
/*!40000 ALTER TABLE `dono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinario`
--

DROP TABLE IF EXISTS `veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `idade` int NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinario`
--

LOCK TABLES `veterinario` WRITE;
/*!40000 ALTER TABLE `veterinario` DISABLE KEYS */;
INSERT INTO `veterinario` VALUES (26,'Gustavo Júlio',21,'33984085689','gustavojulio','senha21'),(27,'Flávio Oliveira',37,'33984254178','flaviooliveira','flavio1501'),(28,'Fernanda Lopes',42,'35984523698','lopesfernanda','vet2356'),(29,'João da Silva',25,'11987654321','joaosilva123','senha123'),(30,'Maria Santos',34,'21123456789','mariasantos34','123456'),(31,'Carlos Pereira',42,'31555555555','carlospereira42','segur@2023');
/*!40000 ALTER TABLE `veterinario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-16 18:42:29
