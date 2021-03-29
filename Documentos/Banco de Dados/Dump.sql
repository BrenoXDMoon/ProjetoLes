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
  PRIMARY KEY (`Id`),
  KEY `FKlapnxmkuoq10hfskf2rlbjs9c` (`estoque_id`),
  CONSTRAINT `FKlapnxmkuoq10hfskf2rlbjs9c` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES (1,_binary '','Deck Inicial do Kaiba','cartas/blue-eyes.png','Dragão Branco de Olhos Azuis',80.00,'ultraRaro',2),(2,_binary '','Impacto do Caos','cartas/AromageMarjoram.webp','Aromágico Manjerona',1.89,'normal',3),(3,_binary '','O Poder do Duelo','cartas/AromageJasmine.webp','Aromágico Jasmim',51.00,'ultraRaro',4),(6,_binary '','Impacto do Caos ','cartas/Manjerona-Doce.webp','Aromági-serafim Manjerona-Doce',0.99,'superRaro',5),(7,_binary '','Legendary Collection 4 ','cartas/Jinzo.webp','Jinzo',9.00,'raro',6),(8,_binary '','O Poder do Duelo ','cartas/alecrim.jpg','Aromágico Alecrim',34.00,'ultraRaro',7),(9,_binary '','Decks Lendários II ','cartas/forca_espelho.webp','Força do Espelho',6.00,'normal',8),(10,_binary '','Rugido do Dragão ','cartas/chamado_dos_mortos_vivos.webp','Chamado dos Assombrados',2.00,'normal',9),(12,_binary '','Decks Lendários de Yugi','cartas/Lustronegro001.JPG','Soldado do Lustro Negro',20.00,'superRaro',11);
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartaocredito`
--

LOCK TABLES `cartaocredito` WRITE;
/*!40000 ALTER TABLE `cartaocredito` DISABLE KEYS */;
INSERT INTO `cartaocredito` VALUES (2,'ELO','486','NN horse race','987654321','10/25'),(3,'ELO','486','nome','000000235','10/25'),(4,'ELO','486','nome','000000235','10/25'),(5,'VISA','486','Maruzensky horse race','000000235','10/25'),(6,'ELO','pop','nome','451384185','12/12'),(7,'ELO','486','nome','987654321','10/25'),(9,'MASTERCARD','862','nome','98765432102','22/03/2023'),(10,'VISA','786','nom 2','5167 3644 5771 4531','22/10/2022'),(11,'MASTERCARD','559','Rice Showwer','5517048405832282','24/02/2023');
/*!40000 ALTER TABLE `cartaocredito` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,_binary '','1997-12-06','RcS@uma.org','Rice Shower','47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=','Admin'),(5,_binary '','2001-05-03','NN@yahho.com','Nice Nature','W365eoW1pb9zKiOBhtQB08kro3RVK3BBJLECs4OHCZw=','Basico'),(10,_binary '','2012-12-11','rice@uma.org','Mihono Burbon','A401TeRcPpMV4KezC1qKCZRrtWTmPt89nVjhS0wSpbY=','Basico'),(11,_binary '','2001-05-02','mrk@uma.org','Maruzensky','gKTu1U3N2/qyuV1pJz8HFwd8xoTB6Fs3SwxfgJ5g3Y0=','Basico'),(13,_binary '\0','2014-12-19','email@gmail.com','audio visual','47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=','Medio');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `cliente_cartaocredito` VALUES (5,2),(10,4),(11,5),(11,6),(13,7),(1,9),(1,10),(1,11);
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
INSERT INTO `cliente_documento` VALUES (5,2),(10,4),(13,6),(1,12),(1,13);
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
INSERT INTO `cliente_endereco` VALUES (10,11),(11,12),(1,18),(1,19),(13,22),(1,23);
/*!40000 ALTER TABLE `cliente_endereco` ENABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom`
--

LOCK TABLES `cupom` WRITE;
/*!40000 ALTER TABLE `cupom` DISABLE KEYS */;
INSERT INTO `cupom` VALUES (1,'f453g1df4g','mo fita miminameru',10.00,'Desconto'),(2,'3d85f2g1d51fg','olha o leitero',7.50,'Troca');
/*!40000 ALTER TABLE `cupom` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
INSERT INTO `documento` VALUES (2,'1231863438','RG','2019-12-12'),(3,'18311548','RG','2019-12-12'),(4,'49176193861','CPF','2012-12-12'),(6,'73426312999','CPF','2012-12-12'),(8,'06415721402','CPF','2015-12-05'),(12,'06415721402','RG','2001-11-30'),(13,'06415721402','CPF','2015-12-01');
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (10,'falsa','15000','suzano','sao paulo','','rua','1','COBRANCA'),(11,'falsa','08000200','suzano','manaus','','rua','159','ENTREGA'),(12,'algum bairro','08795020','hamamatsu','singapura','','rua','1001','ENTREGA'),(18,'kinchi','0800000','nagoya','Seul','ali do lado','rua seul','36','ENTREGA'),(19,'kinchy','0800000','nagoya','kanto','ali do lado','rua busan','27','ENTREGA'),(22,'yokohama','0800030','hamamatsu','kanto','ali do lado','alameda 3','12','ENTREGA'),(23,'kinchi','15975312','hamamatsu','plasma','ali do lado','alameda 2','27','ENTREGA');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (2,3),(3,2),(4,3),(5,1),(6,6),(7,1),(8,6),(9,6),(11,5);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2021-03-28','Pendente',3.78,'09dcb6c0-3b7f-4908-8a39-c03a11b6077f',1,1,18),(3,'2021-03-28','Pendente',1.89,'8047723d-0f1d-477c-90ea-e591e187c7b8',1,1,18);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_carta`
--

DROP TABLE IF EXISTS `pedido_carta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_carta` (
  `Pedido_id` int(11) NOT NULL,
  `itens_id` int(11) NOT NULL,
  PRIMARY KEY (`Pedido_id`,`itens_id`),
  KEY `idx_pedido_carta_itens_id` (`itens_id`),
  CONSTRAINT `FK6p1hb5vocyrvwsipmid0nx93l` FOREIGN KEY (`Pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKkba4du4gwe5rqf2bluxfv4bem` FOREIGN KEY (`itens_id`) REFERENCES `carta` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_carta`
--

LOCK TABLES `pedido_carta` WRITE;
/*!40000 ALTER TABLE `pedido_carta` DISABLE KEYS */;
INSERT INTO `pedido_carta` VALUES (1,2),(3,2);
/*!40000 ALTER TABLE `pedido_carta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cartaocredito`
--

DROP TABLE IF EXISTS `pedido_cartaocredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cartaocredito` (
  `Pedido_id` int(11) NOT NULL,
  `cartoes_Id` int(11) NOT NULL,
  PRIMARY KEY (`Pedido_id`,`cartoes_Id`),
  KEY `idx_pedido_cartaocredito_cartoes_Id` (`cartoes_Id`),
  CONSTRAINT `FK6lqdnv0ug79vyx1cxyl5bfkn3` FOREIGN KEY (`Pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKqrco0marr2gvidc8skj1w9dpb` FOREIGN KEY (`cartoes_Id`) REFERENCES `cartaocredito` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cartaocredito`
--

LOCK TABLES `pedido_cartaocredito` WRITE;
/*!40000 ALTER TABLE `pedido_cartaocredito` DISABLE KEYS */;
INSERT INTO `pedido_cartaocredito` VALUES (1,11),(3,11);
/*!40000 ALTER TABLE `pedido_cartaocredito` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `pedido_cupom` ENABLE KEYS */;
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

-- Dump completed on 2021-03-28 21:01:19
