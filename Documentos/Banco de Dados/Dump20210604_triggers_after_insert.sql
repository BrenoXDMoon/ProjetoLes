CREATE DATABASE  IF NOT EXISTS `newstation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `newstation`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: newstation
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `carta`
--

DROP TABLE IF EXISTS `carta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `imagemPath` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `raridade` varchar(255) DEFAULT NULL,
  `estoque_id` int(11) DEFAULT NULL,
  `dataEntrada` date DEFAULT NULL,
  `fornecedor` varchar(255) DEFAULT NULL,
  `valorCusto` decimal(19,2) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKlapnxmkuoq10hfskf2rlbjs9c` (`estoque_id`),
  CONSTRAINT `FKlapnxmkuoq10hfskf2rlbjs9c` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES (1,_binary '','Deck Inicial do Kaiba','cartas/blue-eyes.png','Dragão Branco de Olhos Azuis',80.00,'ultraRaro',2,'2021-01-11','Konami',10.00),(2,_binary '','Impacto do Caos','cartas/AromageMarjoram.webp','Aromágico Manjerona',1.89,'normal',3,'2021-01-11','Copag',0.50),(3,_binary '','O Poder do Duelo','cartas/AromageJasmine.webp','Aromágico Jasmim',51.00,'ultraRaro',4,NULL,'Copag',5.00),(6,_binary '','Impacto do Caos ','cartas/Manjerona-Doce.webp','Aromági-serafim Manjerona-Doce',0.99,'superRaro',5,NULL,'Copag',0.50),(7,_binary '','Legendary Collection 4 ','cartas/Jinzo.webp','Jinzo',9.00,'raro',6,NULL,'Konami',5.00),(8,_binary '','O Poder do Duelo ','cartas/alecrim.jpg','Aromágico Alecrim',32.00,'raro',7,NULL,NULL,0.00),(9,_binary '','Decks Lendários II ','cartas/forca_espelho.webp','Força do Espelho',6.00,'normal',8,NULL,NULL,0.00),(10,_binary '','Rugido do Dragão ','cartas/chamado_dos_mortos_vivos.webp','Chamado dos Assombrados',2.00,'normal',9,NULL,NULL,0.00),(12,_binary '','Decks Lendários de Yugi','cartas/Lustronegro001.JPG','Soldado do Lustro Negro',20.00,'superRaro',11,NULL,NULL,0.00),(13,_binary '','INVASÃO: VINGANÇA','cartas/alecrim-sincro.png','Aromági-serafim Alecrim ',1.50,'raro',12,NULL,NULL,0.00),(14,_binary '','Deck Pegasus','cartas/mundo_da_fantasia.webp','Mundo da Fantasia',2.00,'raro',13,NULL,'Industrias IlusÃ£o',1.00),(16,_binary '','Dragão 2','cartas/FelgrandDragon.webp','Felgrand',30.00,'raro',20,NULL,'sony',1.50),(17,_binary '','COLEÇÃO LENDÁRIA: KAIBA MEGA PACK','cartas/Choro_Prata.webp','Choro de Prata',2.00,'raro',21,'2021-06-04','kaiba korp',1.00);
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `carta_AFTER_INSERT` AFTER INSERT ON `carta` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`carta_log`
(
`Data`,
`Hora`,
`user`)
VALUES
(
date(now()),
CURRENT_TIME(),
'admin');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `carta_BEFORE_UPDATE` BEFORE UPDATE ON `carta` FOR EACH ROW BEGIN
	if(new.ativo <> old.ativo or 
       new.descricao <> old.descricao or 
       new.nome<> old.nome or 
       new.preco <> old.preco or
       new.raridade <> old.raridade or
       new.fornecedor <> old.fornecedor or 
       new.valorCusto <> old.valorCusto or
	   new.imagemPath <> old.imagemPath) then
INSERT INTO `newstation`.`carta_log`
(`Id`,
`ativo`,
`descricao`,
`imagemPath`,
`nome`,
`preco`,
`raridade`,
`estoque_id`,
`Data`,
`Hora`,
`user`,
`fornecedor`,
`valorCusto`,
`dataEntrada`)
values
(  old.Id,
   old.ativo,
   old.descricao,
   old.imagemPath,
   old.nome,
   old.preco,
   old.raridade,
   old.estoque_id,
   date(now()),
   time(now()),
   'admin',
   old.fornecedor,
   old.valorCusto,
   old.dataEntrada);
end if;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `carta_log`
--

DROP TABLE IF EXISTS `carta_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta_log` (
  `Id` int(11) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `imagemPath` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `raridade` varchar(255) DEFAULT NULL,
  `estoque_id` int(11) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `fornecedor` varchar(255) DEFAULT NULL,
  `valorCusto` decimal(19,2) DEFAULT NULL,
  `dataEntrada` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta_log`
--

LOCK TABLES `carta_log` WRITE;
/*!40000 ALTER TABLE `carta_log` DISABLE KEYS */;
INSERT INTO `carta_log` VALUES (8,_binary '','O Poder do Duelo ','cartas/alecrim.jpg','Aromágico Alecrim',30.00,'ultraRaro',7,'2021-05-12','2021-05-12','admin',NULL,NULL,NULL),(8,_binary '','O Poder do Duelo ','cartas/alecrim.jpg','Aromágico Alecrim',32.00,'ultraRaro',7,'2021-05-12','2021-05-12','admin',NULL,NULL,NULL),(1,_binary '','Deck Inicial do Kaiba','cartas/blue-eyes.png','Dragão Branco de Olhos Azuis',80.00,'ultraRaro',2,'2021-05-12','2021-05-12','admin',NULL,NULL,NULL),(1,_binary '\0','Deck Inicial do Kaiba','cartas/blue-eyes.png','Dragão Branco de Olhos Azuis',80.00,'ultraRaro',2,'2021-05-12','2021-05-12','admin',NULL,NULL,NULL),(2,_binary '','Impacto do Caos','cartas/AromageMarjoram.webp','Aromágico Manjerona',1.89,'normal',3,'2021-05-12','2021-05-12','admin',NULL,0.00,NULL),(2,_binary '\0','Impacto do Caos','cartas/AromageMarjoram.webp','Aromágico Manjerona',1.89,'normal',3,'2021-05-12','2021-05-12','admin','Copag',0.50,'2021-01-12'),(3,_binary '','O Poder do Duelo','cartas/AromageJasmine.webp','Aromágico Jasmim',51.00,'ultraRaro',4,'2021-05-12','2021-05-12','admin',NULL,0.00,NULL),(6,_binary '','Impacto do Caos ','cartas/Manjerona-Doce.webp','Aromági-serafim Manjerona-Doce',0.99,'superRaro',5,'2021-05-14','2021-05-14','admin',NULL,0.00,NULL),(NULL,_binary '','Legendary Collection 4 ','cartas/Jinzo.webp','Jinzo',9.00,'raro',6,'2021-05-14','19:24:16','admin',NULL,0.00,NULL),(0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-14','19:45:47','admin',NULL,NULL,NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-14','19:47:06','admin',NULL,NULL,NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-14','19:48:42','admin',NULL,NULL,NULL),(-1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-14','19:50:13','admin',NULL,NULL,NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-14','20:21:29','admin',NULL,NULL,NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-14','20:55:31','admin',NULL,NULL,NULL),(16,_binary '','DragÃ£o 2','cartas/FelgrandDragon.webp','Felgrand',30.00,'raro',20,'2021-05-14','21:22:45','admin','sony',1.50,'2021-05-14'),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-04','19:49:43','admin',NULL,NULL,NULL);
/*!40000 ALTER TABLE `carta_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartaocredito`
--

DROP TABLE IF EXISTS `cartaocredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartaocredito` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `bandeira` varchar(255) DEFAULT NULL,
  `codigoSeguranca` varchar(255) DEFAULT NULL,
  `nomeImpresso` varchar(255) DEFAULT NULL,
  `numeroCartao` varchar(255) DEFAULT NULL,
  `validade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartaocredito`
--

LOCK TABLES `cartaocredito` WRITE;
/*!40000 ALTER TABLE `cartaocredito` DISABLE KEYS */;
INSERT INTO `cartaocredito` VALUES (2,'ELO','486','NN horse race','987654321','10/25'),(3,'ELO','486','nome','000000235','10/25'),(4,'ELO','486','nome','000000235','10/25'),(5,'VISA','486','Maruzensky horse race','000000235','10/25'),(6,'ELO','pop','nome','451384185','12/12'),(7,'ELO','486','nome','987654321','10/25'),(9,'MASTERCARD','862','nome','98765432102','22/03/2023'),(10,'VISA','786','nom 2','5167 3644 5771 4531','22/10/2022'),(11,'MASTERCARD','559','Rice Showwer','5517048405832282','24/02/2023'),(12,'MASTERCARD','701','rice','5578 1818 4937 5083','11/10/2022'),(13,'MASTERCARD','352','Cereza','987654321','10/25'),(14,'MASTERCARD','256','Kang Seulgi','5163 5880 6605 0275 ','04/23');
/*!40000 ALTER TABLE `cartaocredito` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cartaocredito_AFTER_INSERT` AFTER INSERT ON `cartaocredito` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`cartaocredito_log`
(`Id`,
`bandeira`,
`codigoSeguranca`,
`nomeImpresso`,
`numeroCartao`,
`validade`,
`Data`,
`Hora`,
`user`)
VALUES
(null,
null,
null,
null,
null,
null,
date(now()),
CURRENT_TIME(),
'admin');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cartaocredito_BEFORE_UPDATE` BEFORE UPDATE ON `cartaocredito` FOR EACH ROW BEGIN
	if(new.bandeira <> old.bandeira or 
       new.codigoSeguranca <> old.codigoSeguranca or 
       new.nomeImpresso<> old.nomeImpresso or 
       new.numeroCartao <> old.numeroCartao or
       new.validade <> old.validade) then
INSERT INTO `newstation`.`cartaocredito_log`
(`Id`,
`bandeira`,
`codigoSeguranca`,
`nomeImpresso`,
`numeroCartao`,
`validade`,
`Data`,
`Hora`,
`user`)
VALUES
(old.id,
old.bandeira,
old.codigoSeguranca,
old.nomeImpresso,
old.numeroCartao,
old.validade,
date(now()),
current_time(),
'admin');

end if;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cartaocredito_log`
--

DROP TABLE IF EXISTS `cartaocredito_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartaocredito_log` (
  `Id` int(11) DEFAULT NULL,
  `bandeira` varchar(255) DEFAULT NULL,
  `codigoSeguranca` varchar(255) DEFAULT NULL,
  `nomeImpresso` varchar(255) DEFAULT NULL,
  `numeroCartao` varchar(255) DEFAULT NULL,
  `validade` varchar(255) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartaocredito_log`
--

LOCK TABLES `cartaocredito_log` WRITE;
/*!40000 ALTER TABLE `cartaocredito_log` DISABLE KEYS */;
INSERT INTO `cartaocredito_log` VALUES (NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-01','19:55:55','admin'),(NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-04','20:05:38','admin');
/*!40000 ALTER TABLE `cartaocredito_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartaopedido`
--

DROP TABLE IF EXISTS `cartaopedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartaopedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` decimal(19,2) DEFAULT NULL,
  `cartao_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnr2nptkpa3rrnl35xc6hxsawt` (`cartao_Id`),
  CONSTRAINT `FKnr2nptkpa3rrnl35xc6hxsawt` FOREIGN KEY (`cartao_Id`) REFERENCES `cartaocredito` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartaopedido`
--

LOCK TABLES `cartaopedido` WRITE;
/*!40000 ALTER TABLE `cartaopedido` DISABLE KEYS */;
INSERT INTO `cartaopedido` VALUES (1,85.00,9),(2,5.99,6),(3,85.00,9),(10,76.89,9),(11,15.97,9),(12,0.76,9),(14,-5.00,11),(16,4.01,10),(17,7.12,9),(24,4.80,10),(26,6.50,10),(27,4.00,9),(28,-0.23,10),(32,5.39,10),(35,5.00,9),(36,NULL,10),(37,11.00,10),(38,600.00,10),(39,NULL,11),(40,85.00,10),(41,2349.12,10),(42,6.89,12),(43,800.00,10),(44,NULL,11),(45,89.50,14);
/*!40000 ALTER TABLE `cartaopedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartapedido`
--

DROP TABLE IF EXISTS `cartapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartapedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `carta_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb8mm3avsiikh8blg31eweudnh` (`carta_id`),
  CONSTRAINT `FKb8mm3avsiikh8blg31eweudnh` FOREIGN KEY (`carta_id`) REFERENCES `carta` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartapedido`
--

LOCK TABLES `cartapedido` WRITE;
/*!40000 ALTER TABLE `cartapedido` DISABLE KEYS */;
INSERT INTO `cartapedido` VALUES (3,5,3),(4,10,6),(7,1,9),(10,1,2),(14,1,6),(15,1,1),(16,1,6),(18,1,1),(30,1,2),(31,1,1),(32,3,6),(33,0,7),(34,3,2),(35,0,6),(40,1,6),(41,0,2),(48,2,13),(50,0,13),(51,1,2),(52,1,7),(54,1,2),(56,1,13),(57,1,10),(58,0,2),(59,4,3),(60,9,1),(61,1,1),(62,11,7),(63,9,1),(64,1,2),(65,7,2),(66,6,1),(67,1,17),(68,1,1);
/*!40000 ALTER TABLE `cartapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipoCliente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,_binary '','1997-12-04','RcS@uma.org','Rice Shower','47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=','Admin'),(5,_binary '','2001-05-03','NN@yahho.com','Nice Nature','W365eoW1pb9zKiOBhtQB08kro3RVK3BBJLECs4OHCZw=','Basico'),(10,_binary '','2012-12-09','rice@uma.org','Mihono Burbon','A401TeRcPpMV4KezC1qKCZRrtWTmPt89nVjhS0wSpbY=','Basico'),(11,_binary '\0','2001-04-30','mrk@uma.org','Maruzensky','gKTu1U3N2/qyuV1pJz8HFwd8xoTB6Fs3SwxfgJ5g3Y0=','Basico'),(13,_binary '','2014-12-13','email@gmail.com','audio visual','A401TeRcPpMV4KezC1qKCZRrtWTmPt89nVjhS0wSpbY=','Medio'),(17,_binary '','1992-09-30','byt@umbra.com','Bayonetta','XQAtsR4+LXKWURb232bJYF9ol4Edvoybd5uQTy5ZNSg=','Basico'),(18,_binary '','1990-12-16','seulgi@sm.com','Seulgi','re+QBkj+RBkubkZwuMbx3SX1R8ds6K+xcqywQ+hAhXU=','Basico');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cliente_AFTER_INSERT` AFTER INSERT ON `cliente` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`cliente_log`
(
`Data`,
`Hora`,
`user`)
VALUES
(
date(now()),
CURRENT_TIME(),
'admin');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cliente_BEFORE_UPDATE` BEFORE UPDATE ON `cliente` FOR EACH ROW BEGIN
	if(new.ativo <> old.ativo or 
       new.dataNascimento <> old.dataNascimento or 
       new.email<> old.email or 
       new.senha <> old.senha or
       new.tipoCliente <> old.tipoCliente) then
INSERT INTO `newstation`.`cliente_log`
(`id`, 
`ativo`,
`dataNascimento`,
`email`,
`nome`,
`senha`,
`tipoCliente`,
`Data`,
`Hora`,
`user`)
VALUES
(old.id,
old.ativo,
old.dataNascimento,
old.email,
old.nome,
old.senha,
old.tipoCliente,
date(now()),
current_time(),
'admin');
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cliente_cartaocredito`
--

DROP TABLE IF EXISTS `cliente_cartaocredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_cartaocredito` (
  `Cliente_id` int(11) NOT NULL,
  `cartoes_Id` int(11) NOT NULL,
  PRIMARY KEY (`Cliente_id`,`cartoes_Id`),
  UNIQUE KEY `UK_52d86kce7t3pvgkvstmklfadc` (`cartoes_Id`),
  CONSTRAINT `FK9lajajv2yjmqrgqnjwfq1rgem` FOREIGN KEY (`cartoes_Id`) REFERENCES `cartaocredito` (`Id`),
  CONSTRAINT `FKhhyhe9f77jc6vn35swvwuel5y` FOREIGN KEY (`Cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_cartaocredito`
--

LOCK TABLES `cliente_cartaocredito` WRITE;
/*!40000 ALTER TABLE `cliente_cartaocredito` DISABLE KEYS */;
INSERT INTO `cliente_cartaocredito` VALUES (5,2),(10,4),(11,5),(11,6),(13,7),(1,9),(1,10),(1,11),(1,12),(17,13),(18,14);
/*!40000 ALTER TABLE `cliente_cartaocredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_documento`
--

DROP TABLE IF EXISTS `cliente_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_documento` (
  `Cliente_id` int(11) NOT NULL,
  `documentos_id` int(11) NOT NULL,
  PRIMARY KEY (`Cliente_id`,`documentos_id`),
  UNIQUE KEY `UK_t58fbxd9sun2kfv8tag6dvg5g` (`documentos_id`),
  CONSTRAINT `FKewlndy93d1rvvx2r4y5vcavt9` FOREIGN KEY (`Cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKreyoilx733tvmrtvbmc7myv54` FOREIGN KEY (`documentos_id`) REFERENCES `documento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_documento`
--

LOCK TABLES `cliente_documento` WRITE;
/*!40000 ALTER TABLE `cliente_documento` DISABLE KEYS */;
INSERT INTO `cliente_documento` VALUES (5,2),(10,4),(13,6),(1,12),(1,13),(11,14),(17,15),(18,16);
/*!40000 ALTER TABLE `cliente_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_endereco`
--

DROP TABLE IF EXISTS `cliente_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_endereco` (
  `Cliente_id` int(11) NOT NULL,
  `enderecos_Id` int(11) NOT NULL,
  PRIMARY KEY (`Cliente_id`,`enderecos_Id`),
  UNIQUE KEY `UK_9lgl2mnqbcpafb8gj9v5lo4at` (`enderecos_Id`),
  CONSTRAINT `FK234330pjmnk8qavta476g7yv2` FOREIGN KEY (`enderecos_Id`) REFERENCES `endereco` (`Id`),
  CONSTRAINT `FKpvx6blbyg0julyys89r9ns6rb` FOREIGN KEY (`Cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_endereco`
--

LOCK TABLES `cliente_endereco` WRITE;
/*!40000 ALTER TABLE `cliente_endereco` DISABLE KEYS */;
INSERT INTO `cliente_endereco` VALUES (10,11),(11,12),(1,18),(1,19),(13,22),(1,23),(1,24),(1,25),(17,26),(18,27);
/*!40000 ALTER TABLE `cliente_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_log`
--

DROP TABLE IF EXISTS `cliente_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_log` (
  `id` int(11) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `tipoCliente` varchar(255) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_log`
--

LOCK TABLES `cliente_log` WRITE;
/*!40000 ALTER TABLE `cliente_log` DISABLE KEYS */;
INSERT INTO `cliente_log` VALUES (NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-04','20:05:37','admin');
/*!40000 ALTER TABLE `cliente_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom`
--

DROP TABLE IF EXISTS `cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `descricao` longtext,
  `preco` decimal(19,2) DEFAULT NULL,
  `tipoCupom` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7kmen9yy8etamedc9ogsswt04` (`cliente_id`),
  CONSTRAINT `FK7kmen9yy8etamedc9ogsswt04` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom`
--

LOCK TABLES `cupom` WRITE;
/*!40000 ALTER TABLE `cupom` DISABLE KEYS */;
INSERT INTO `cupom` VALUES (1,'f453g1df4g','mo fita miminameru',10.00,'Desconto',NULL,_binary ''),(2,'3d85f2g1d51fg','olha o leitero',95.00,'Troca',1,_binary '\0'),(4,'bc0944b7',NULL,4.01,'Troca',1,_binary '\0'),(5,'422ff7f1',NULL,7.12,'Troca',1,_binary '\0'),(7,'4d2b546a','null',1.20,'Troca',1,_binary '\0'),(8,'Sem Cupom','',0.00,'Desconto',NULL,_binary ''),(11,'ac219cd8','',1.50,'Troca',1,_binary '\0'),(12,'c7322ef1','',4.01,'Troca',1,_binary '\0'),(13,'31bf7e54','',1.00,'Troca',1,_binary '\0'),(14,'bf0cc5d7','',3.00,'Troca',1,_binary '\0'),(15,'d6d87756','',1500.00,'Troca',1,_binary '\0'),(16,'9fd50258',NULL,0.00,'Troca',1,_binary '\0'),(17,'a5ec84ce',NULL,1056.88,'Troca',1,_binary '\0'),(18,'2673b17d',NULL,771.00,'Troca',1,_binary '\0'),(19,'3edf39af',NULL,2587.00,'Troca',1,_binary ''),(20,'a54ed687',NULL,809.45,'Troca',1,_binary ''),(22,'eb846584','',2.50,'Desconto',NULL,_binary '');
/*!40000 ALTER TABLE `cupom` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cupom_AFTER_INSERT` AFTER INSERT ON `cupom` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`cupom_log`
(`id`,
`codigo`,
`descricao`,
`preco`,
`tipoCupom`,
`cliente_id`,
`ativo`,
`Data`,
`Hora`,
`user`)
VALUES
(null,
null,
null,
null,
null,
null,
null,
date(now()),
current_time(),
'admin');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `cupom_BEFORE_UPDATE` BEFORE UPDATE ON `cupom` FOR EACH ROW BEGIN
	if(new.codigo <> old.codigo or 
       new.descricao <> old.descricao or 
       new.preco<> old.preco or 
       new.ativo <> old.ativo or
       new.cliente_id <> old.cliente_id) then
INSERT INTO `newstation`.`cupom_log`
(`id`,
`codigo`,
`descricao`,
`preco`,
`tipoCupom`,
`cliente_id`,
`ativo`,
`Data`,
`Hora`,
`user`)
VALUES
(old.id,
old.codigo,
old.descricao,
old.preco,
old.tipoCupom,
old.cliente_id,
old.ativo,
date(now()),
current_time(),
'admin');

end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `cupom_log`
--

DROP TABLE IF EXISTS `cupom_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupom_log` (
  `id` int(11) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `descricao` longtext,
  `preco` decimal(19,2) DEFAULT NULL,
  `tipoCupom` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `ativo` bit(1) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom_log`
--

LOCK TABLES `cupom_log` WRITE;
/*!40000 ALTER TABLE `cupom_log` DISABLE KEYS */;
INSERT INTO `cupom_log` VALUES (NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-04','20:55:53','admin');
/*!40000 ALTER TABLE `cupom_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  `validade` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
INSERT INTO `documento` VALUES (2,'1231863438','RG','2019-12-12'),(3,'18311548','RG','2019-12-12'),(4,'49176193861','CPF','2012-12-12'),(6,'73426312999','CPF','2012-12-12'),(8,'06415721402','CPF','2015-12-05'),(12,'06415721402','RG','2001-11-30'),(13,'06415721402','CPF','2015-12-01'),(14,'45185318384834','RG','2150-12-09'),(15,'14.733.714-8','RG','2023-03-13'),(16,'54039552164','CPF','2025-08-09');
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `documento_AFTER_INSERT` AFTER INSERT ON `documento` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`documento_log`
(`id`,
`codigo`,
`tipoDocumento`,
`validade`,
`Data`,
`Hora`,
`user`)
VALUES
(null,
null,
null,
null,
date(now()),
CURRENT_TIME(),
'admin');

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `documento_BEFORE_UPDATE` BEFORE UPDATE ON `documento` FOR EACH ROW BEGIN
	if(new.codigo <> old.codigo or 
       new.tipoDocumento <> old.tipoDocumento or 
       new.validade<> old.validade) then
INSERT INTO `newstation`.`documento_log`
(`id`,
`codigo`,
`tipoDocumento`,
`validade`,
`Data`,
`Hora`,
`user`)
VALUES
(old.id,
old.codigo,
old.tipoDocumento,
old.validade,
date(now()),
current_time(),
'admin');
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `documento_log`
--

DROP TABLE IF EXISTS `documento_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento_log` (
  `id` int(11) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  `validade` date DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento_log`
--

LOCK TABLES `documento_log` WRITE;
/*!40000 ALTER TABLE `documento_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `tipoEndereco` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (10,'falsa','15000','suzano','sao paulo','','rua','1','COBRANCA'),(11,'falsa','08000200','suzano','manaus','','rua','159','ENTREGA'),(12,'algum bairro','08795020','hamamatsu','singapura','','rua','1001','ENTREGA'),(18,'kinchi','0800000','nagoya','Seul','ali do lado','rua seul','36','ENTREGA'),(19,'kinchy','0800000','nagoya','kanto','ali do lado','rua busan','27','ENTREGA'),(22,'yokohama','0800030','hamamatsu','kanto','ali do lado','alameda 3','12','ENTREGA'),(23,'kinchi','15975312','hamamatsu','plasma','ali do lado','alameda 2','27','ENTREGA'),(24,'Vila Palmeira','65045-430','São Luís','Maranhão','','Rua Eurico Ribeiro','837','ENTREGA'),(25,'Tocantins','85903-366','Toledo','parana','','Rua Ministro Marcos Freire','868','ENTREGA'),(26,'Jardim Jordão','54315-451','Jaboatão dos Guararapes','Pernambuco','','Travessa São Lourenço','398','ENTREGA'),(27,'seul','54315-451','sm towm','Rio Grande do Sul','','Viela Dois','27','ENTREGA');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `endereco_AFTER_INSERT` AFTER INSERT ON `endereco` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`endereco_log`
(`Id`,
`bairro`,
`cep`,
`cidade`,
`estado`,
`complemento`,
`logradouro`,
`numero`,
`tipoEndereco`,
`Data`,
`Hora`,
`user`)
VALUES
(null,
null,
null,
null,
null,
null,
null,
null,
null,
date(now()),
CURRENT_TIME(),
'admin');

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `endereco_BEFORE_UPDATE` BEFORE UPDATE ON `endereco` FOR EACH ROW BEGIN
if(new.bairro <> old.bairro or 
  new.cep <> old.cep or 
  new.cidade<> old.cidade or 
  new.estado <> old.estado or
  new.complemento <> old.complemento or
  new.logradouro <> old.logradouro or
  new.numero <> old.numero or
  new.tipoEndereco <> old.tipoEndereco ) then

INSERT INTO `newstation`.`endereco_log`
(`Id`,
`bairro`,
`cep`,
`cidade`,
`estado`,
`complemento`,
`logradouro`,
`numero`,
`tipoEndereco`,
`Data`,
`Hora`,
`user`)
VALUES
(old.Id,
old.bairro,
old.cep,
old.cidade,
old.estado,
old.complemento,
old.logradouro,
old.numero,
old.tipoEndereco,
date(now()),
current_time(),
'admin');
end if;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `endereco_log`
--

DROP TABLE IF EXISTS `endereco_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco_log` (
  `Id` int(11) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `tipoEndereco` varchar(255) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco_log`
--

LOCK TABLES `endereco_log` WRITE;
/*!40000 ALTER TABLE `endereco_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (2,67),(3,12),(4,112),(5,70),(6,48),(7,30),(8,30),(9,30),(11,30),(12,28),(13,1),(14,30),(15,6),(16,6),(17,6),(18,6),(20,10),(21,5);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `estoque_BEFORE_INSERT` BEFORE INSERT ON `estoque` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`estoque_log`
(`id`,
`quantidade`,
`Data`,
`Hora`,
`user`)
VALUES
(null,
null,
date(now()),
current_time(),
'admin');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `estoque_BEFORE_UPDATE` BEFORE UPDATE ON `estoque` FOR EACH ROW BEGIN
	if(new.quantidade <> old.quantidade) then
INSERT INTO `newstation`.`estoque_log`
(`id`,
`quantidade`,
`Data`,
`Hora`,
`user`)
VALUES
(old.id,
old.quantidade,
date(now()),
current_time(),
'admin');

end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `estoque_log`
--

DROP TABLE IF EXISTS `estoque_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque_log` (
  `id` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque_log`
--

LOCK TABLES `estoque_log` WRITE;
/*!40000 ALTER TABLE `estoque_log` DISABLE KEYS */;
INSERT INTO `estoque_log` VALUES (5,103,'2021-05-14','2021-05-14','admin'),(NULL,NULL,'2021-05-14','20:21:29','admin'),(NULL,NULL,'2021-05-14','20:30:19','admin'),(NULL,NULL,'2021-05-14','20:31:53','admin'),(NULL,NULL,'2021-05-14','20:37:13','admin'),(NULL,NULL,'2021-05-14','20:39:31','admin'),(NULL,NULL,'2021-05-14','20:50:24','admin'),(NULL,NULL,'2021-05-14','20:55:31','admin'),(NULL,NULL,'2021-06-04','19:49:43','admin');
/*!40000 ALTER TABLE `estoque_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataAtualizacao` date DEFAULT NULL,
  `statusPedido` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `cupomDesconto_id` int(11) DEFAULT NULL,
  `endereco_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jq79bpskcvo2krkbee2qdqpr` (`cliente_id`),
  KEY `FK4ho297ojdd7x5kxonqgqselnw` (`cupomDesconto_id`),
  KEY `FK7rube3v74f7bwc7e06ve9w3go` (`endereco_Id`),
  CONSTRAINT `FK1jq79bpskcvo2krkbee2qdqpr` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK4ho297ojdd7x5kxonqgqselnw` FOREIGN KEY (`cupomDesconto_id`) REFERENCES `cupom` (`id`),
  CONSTRAINT `FK7rube3v74f7bwc7e06ve9w3go` FOREIGN KEY (`endereco_Id`) REFERENCES `endereco` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2021-02-11','Troca_negada',264.90,'f6fb8956-b611-4b20-bdb7-15b829fb02be',1,2,19),(3,'2021-04-02','Troca_negada',6.00,'7fd6f848-18ef-4593-853e-2a7f0affceff',1,2,18),(6,'2021-04-05','Pendente',85.00,'a73af487-ea56-40f0-a470-34fd264ddfcf',1,1,18),(7,'2021-04-06','Pendente',5.99,'030e9af0-284e-4cb8-96c0-34d78789dad5',11,1,12),(9,'2021-04-10','Pendente',85.00,'c88c92b4-5316-4392-b986-920a7c9f9728',1,1,18),(16,'2021-04-10','Pendente',86.89,'ce4a8914-a852-4ae9-a107-73a848feab87',1,1,19),(17,'2021-03-08','Trocado',25.97,'09ad8060-52f8-4a76-8b2c-40746ea83744',1,1,19),(18,'2021-04-04','Trocado',10.76,'19802902-4227-457a-93cd-c4455deb586b',1,1,18),(20,'2021-04-10','Pendente',5.00,'ac797a72-62c1-4eb2-84e7-225e6ba200b7',1,1,18),(22,'2021-04-10','Pendente',5.99,'abfb45b1-fa59-4518-b19a-9d046cbab6c8',1,1,19),(23,'2021-04-07','Trocado',6.89,'d8c66386-be39-4010-a5cb-ac83af45c709',1,1,18),(30,'2021-04-06','Entregue',16.00,'f6dcb525-bf11-41f3-828e-fde9468c8ab1',1,1,19),(32,'2021-04-09','Trocado',6.50,'19e8e3e4-bc6a-4d77-a802-81441996ee06',1,8,19),(33,'2021-04-15','Pendente',10.89,'e0882dd7-f08b-4daa-bb2b-b91c0224460f',1,8,19),(36,'2021-04-15','Pendente',6.89,'06fe6b22-cf52-4b71-8074-058b5482f9ef',1,8,19),(38,'2021-04-15','Pendente',1.50,'f6853585-a04c-48ab-8a92-5e6b82fd858a',1,8,23),(39,'2021-04-15','Pendente',12.00,'68a71eba-20a4-45b3-80f9-869ddeca684c',1,8,19),(40,'2021-04-10','Trocado',2751.89,'7a61e812-1cd4-4802-931a-a91a50732cec',1,1,23),(41,'2021-05-04','Pendente',85.00,'63b95521-c691-48df-a528-ac3a3f123cbc',1,8,18),(42,'2021-05-08','Trocado',3416.00,'db5f9991-0ed1-4bb1-bb07-ffd3e758018d',1,1,23),(43,'2021-05-11','Pendente',6.89,'3f346c94-d71b-4e08-9235-47e3d7380440',1,8,24),(44,'2021-05-08','Trocado',1302.68,'67530c77-10df-4e3e-a361-f5c0e9ce904a',1,1,25),(45,'2021-06-04','Pendente',92.00,'10476877-304b-47cb-8afd-7731d3f704a8',18,22,27);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pedido_AFTER_INSERT` AFTER INSERT ON `pedido` FOR EACH ROW BEGIN
INSERT INTO `newstation`.`pedido_log`
(`id`,
`dataAtualizacao`,
`statusPedido`,
`total`,
`uuid`,
`cliente_id`,
`cupomDesconto_id`,
`endereco_Id`,
`Data`,
`Hora`,
`user`)
VALUES
(null,
null,
null,
null,
null,
null,
null,
null,
date(now()),
current_time(),
'admin');
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `pedido_BEFORE_UPDATE` BEFORE UPDATE ON `pedido` FOR EACH ROW BEGIN
	if(new.dataAtualizacao <> old.dataAtualizacao or 
       new.statusPedido <> old.statusPedido or 
       new.total<> old.total or 
       new.uuid <> old.uuid or
       new.cliente_id <> old.cliente_id or
       new.cupomDesconto_id <> old.cupomDesconto_id or
       new.endereco_Id <> old.endereco_Id) then
INSERT INTO `newstation`.`pedido_log`
(`id`,
`dataAtualizacao`,
`statusPedido`,
`total`,
`uuid`,
`cliente_id`,
`cupomDesconto_id`,
`endereco_Id`,
`Data`,
`Hora`,
`user`)
VALUES
(old.id,
old.dataAtualizacao,
old.statusPedido,
old.total,
old.uuid,
old.cliente_id,
old.cupomDesconto_id,
old.endereco_Id,
date(now()),
current_time(),
'admin');


end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `pedido_cartaopedido`
--

DROP TABLE IF EXISTS `pedido_cartaopedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cartaopedido` (
  `Pedido_id` int(11) NOT NULL,
  `cartoes_id` int(11) NOT NULL,
  PRIMARY KEY (`Pedido_id`,`cartoes_id`),
  UNIQUE KEY `UK_squ39imh3f3w4ea0knqqnhss5` (`cartoes_id`),
  CONSTRAINT `FKf2not5we9t5cnq34q9a33s2e1` FOREIGN KEY (`cartoes_id`) REFERENCES `cartaopedido` (`id`),
  CONSTRAINT `FKqd1pqddhvhadw03yx9rttqxqv` FOREIGN KEY (`Pedido_id`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cartaopedido`
--

LOCK TABLES `pedido_cartaopedido` WRITE;
/*!40000 ALTER TABLE `pedido_cartaopedido` DISABLE KEYS */;
INSERT INTO `pedido_cartaopedido` VALUES (6,1),(7,2),(9,3),(16,10),(17,11),(18,12),(20,14),(22,16),(23,17),(30,24),(32,26),(33,27),(33,28),(36,32),(38,35),(38,36),(39,37),(40,38),(40,39),(41,40),(42,41),(43,42),(44,43),(44,44),(45,45);
/*!40000 ALTER TABLE `pedido_cartaopedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cartapedido`
--

DROP TABLE IF EXISTS `pedido_cartapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cartapedido` (
  `Pedido_id` int(11) NOT NULL,
  `itens_id` int(11) NOT NULL,
  PRIMARY KEY (`Pedido_id`,`itens_id`),
  UNIQUE KEY `UK_h29ka6lqw6n6e0cdegb361nol` (`itens_id`),
  CONSTRAINT `FK8nnoayfefpswhm3x75q73jtt7` FOREIGN KEY (`Pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKd25d6ou0vonphoxy0ep4wl19` FOREIGN KEY (`itens_id`) REFERENCES `cartapedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cartapedido`
--

LOCK TABLES `pedido_cartapedido` WRITE;
/*!40000 ALTER TABLE `pedido_cartapedido` DISABLE KEYS */;
INSERT INTO `pedido_cartapedido` VALUES (1,3),(1,4),(3,7),(6,15),(7,16),(9,18),(16,30),(16,31),(17,32),(17,33),(18,34),(18,35),(22,40),(23,41),(30,48),(32,50),(33,51),(33,52),(36,54),(38,56),(39,57),(40,58),(40,59),(40,60),(41,61),(42,62),(42,63),(43,64),(44,65),(44,66),(45,67),(45,68);
/*!40000 ALTER TABLE `pedido_cartapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cupom`
--

DROP TABLE IF EXISTS `pedido_cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cupom` (
  `Pedido_id` int(11) NOT NULL,
  `cupomTroca_id` int(11) NOT NULL,
  PRIMARY KEY (`Pedido_id`,`cupomTroca_id`),
  UNIQUE KEY `UK_c3om7lflmv9sn19l8hx1s9y1s` (`cupomTroca_id`),
  CONSTRAINT `FKijlgo1ssy8tncnwg8gjw2nj67` FOREIGN KEY (`cupomTroca_id`) REFERENCES `cupom` (`id`),
  CONSTRAINT `FKtfo4avu7r6ynxla175w5oumag` FOREIGN KEY (`Pedido_id`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cupom`
--

LOCK TABLES `pedido_cupom` WRITE;
/*!40000 ALTER TABLE `pedido_cupom` DISABLE KEYS */;
INSERT INTO `pedido_cupom` VALUES (9,2),(23,4),(33,5),(30,7),(36,11),(38,12),(39,13),(40,14),(40,15),(41,16),(42,17),(44,18);
/*!40000 ALTER TABLE `pedido_cupom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_log`
--

DROP TABLE IF EXISTS `pedido_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_log` (
  `id` int(11) DEFAULT NULL,
  `dataAtualizacao` date DEFAULT NULL,
  `statusPedido` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `cupomDesconto_id` int(11) DEFAULT NULL,
  `endereco_Id` int(11) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Hora` varchar(20) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_log`
--

LOCK TABLES `pedido_log` WRITE;
/*!40000 ALTER TABLE `pedido_log` DISABLE KEYS */;
INSERT INTO `pedido_log` VALUES (NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-04','20:57:13','admin');
/*!40000 ALTER TABLE `pedido_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'newstation'
--

--
-- Dumping routines for database 'newstation'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-04 17:59:37
