-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: undf
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.22.04.3

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
-- Table structure for table `tb_aluno`
--

DROP TABLE IF EXISTS `tb_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_aluno` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `curso` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_aluno`
--

LOCK TABLES `tb_aluno` WRITE;
/*!40000 ALTER TABLE `tb_aluno` DISABLE KEYS */;
INSERT INTO `tb_aluno` VALUES (1,'Bilbo Baggins','12345678901','123','Sistemas de Informação'),(2,'Frodo Baggins','12345678902','123','Sistemas de Informação'),(3,'Gollum','12345678904','123','Sistemas de Informação'),(4,'Smeagol','12345678905','123','Sistemas de Informação'),(5,'Samwise Gamgee','12345678906','123','Sistemas de Informação'),(6,'Meriadoc Brandybuck','12345678907','123','Engenharia de Software'),(7,'Peregrin Took','12345678908','123','Engenharia de Software'),(8,'Legolas','12345678909','123','Engenharia de Software'),(9,'Gimli','12345678910','123','Engenharia de Software');
/*!40000 ALTER TABLE `tb_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_disciplina`
--

DROP TABLE IF EXISTS `tb_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_disciplina` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `carga_horaria` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_disciplina`
--

LOCK TABLES `tb_disciplina` WRITE;
/*!40000 ALTER TABLE `tb_disciplina` DISABLE KEYS */;
INSERT INTO `tb_disciplina` VALUES (1,'Programação I','60','Introdução a programação'),(2,'Programação II','60','Estrutura de dados'),(3,'Programação III','60','Programação orientada a objetos'),(4,'Banco de Dados I','60','Introdução a banco de dados'),(5,'Banco de Dados II','60','Modelagem de dados'),(6,'Banco de Dados III','60','SQL avançado');
/*!40000 ALTER TABLE `tb_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_professor`
--

DROP TABLE IF EXISTS `tb_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_professor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_professor`
--

LOCK TABLES `tb_professor` WRITE;
/*!40000 ALTER TABLE `tb_professor` DISABLE KEYS */;
INSERT INTO `tb_professor` VALUES (1,'Gandalf','12345678903','123'),(2,'Aragorn','12345678907','123456'),(3,'Arwin','12345678909','123456');
/*!40000 ALTER TABLE `tb_professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sala`
--

DROP TABLE IF EXISTS `tb_sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sala` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `capacidade` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sala`
--

LOCK TABLES `tb_sala` WRITE;
/*!40000 ALTER TABLE `tb_sala` DISABLE KEYS */;
INSERT INTO `tb_sala` VALUES (1,101,50),(2,102,50),(3,103,50),(4,104,50);
/*!40000 ALTER TABLE `tb_sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_turma`
--

DROP TABLE IF EXISTS `tb_turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_turma` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `discilpina_id` int NOT NULL,
  `professor_id` int NOT NULL,
  `sala_id` int NOT NULL,
  `disciplina_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `discilpina_id` (`discilpina_id`),
  KEY `professor_id` (`professor_id`),
  KEY `sala_id` (`sala_id`),
  CONSTRAINT `tb_turma_ibfk_1` FOREIGN KEY (`discilpina_id`) REFERENCES `tb_disciplina` (`id`),
  CONSTRAINT `tb_turma_ibfk_2` FOREIGN KEY (`professor_id`) REFERENCES `tb_professor` (`id`),
  CONSTRAINT `tb_turma_ibfk_3` FOREIGN KEY (`sala_id`) REFERENCES `tb_sala` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_turma`
--

LOCK TABLES `tb_turma` WRITE;
/*!40000 ALTER TABLE `tb_turma` DISABLE KEYS */;
INSERT INTO `tb_turma` VALUES (1,'Programação I - 2020.1',1,1,1,NULL),(2,'Programação II - 2020.1',2,1,2,NULL),(3,'Programação III - 2020.1',3,1,3,NULL);
/*!40000 ALTER TABLE `tb_turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_turma_aluno`
--

DROP TABLE IF EXISTS `tb_turma_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_turma_aluno` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `turma_id` int DEFAULT NULL,
  `aluno_id` int DEFAULT NULL,
  `nota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `turma_id` (`turma_id`),
  KEY `aluno_id` (`aluno_id`),
  CONSTRAINT `tb_turma_aluno_ibfk_1` FOREIGN KEY (`turma_id`) REFERENCES `tb_turma` (`id`),
  CONSTRAINT `tb_turma_aluno_ibfk_2` FOREIGN KEY (`aluno_id`) REFERENCES `tb_aluno` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_turma_aluno`
--

LOCK TABLES `tb_turma_aluno` WRITE;
/*!40000 ALTER TABLE `tb_turma_aluno` DISABLE KEYS */;
INSERT INTO `tb_turma_aluno` VALUES (1,1,1,'A'),(2,1,2,'B'),(3,1,3,'C'),(4,2,1,'A'),(5,2,2,'B'),(6,2,3,'C'),(7,3,1,'A'),(8,3,2,'B');
/*!40000 ALTER TABLE `tb_turma_aluno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-16 21:13:17
