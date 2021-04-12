-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: newstation
-- ------------------------------------------------------
-- Server version	8.0.21

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
  `Id` int NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `imagemPath` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `raridade` varchar(255) DEFAULT NULL,
  `estoque_id` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKlapnxmkuoq10hfskf2rlbjs9c` (`estoque_id`),
  CONSTRAINT `FKlapnxmkuoq10hfskf2rlbjs9c` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES (1,_binary '','Deck Inicial do Kaiba','cartas/blue-eyes.png','Dragão Branco de Olhos Azuis',80.00,'ultraRaro',2),(2,_binary '','Impacto do Caos','cartas/AromageMarjoram.webp','Aromágico Manjerona',1.89,'normal',3),(3,_binary '','O Poder do Duelo','cartas/AromageJasmine.webp','Aromágico Jasmim',51.00,'ultraRaro',4),(6,_binary '','Impacto do Caos ','cartas/Manjerona-Doce.webp','Aromági-serafim Manjerona-Doce',0.99,'superRaro',5),(7,_binary '','Legendary Collection 4 ','cartas/Jinzo.webp','Jinzo',9.00,'raro',6),(8,_binary '','O Poder do Duelo ','cartas/alecrim.jpg','Aromágico Alecrim',34.00,'ultraRaro',7),(9,_binary '','Decks Lendários II ','cartas/forca_espelho.webp','Força do Espelho',6.00,'normal',8),(10,_binary '','Rugido do Dragão ','cartas/chamado_dos_mortos_vivos.webp','Chamado dos Assombrados',2.00,'normal',9),(12,_binary '','Decks Lendários de Yugi','cartas/Lustronegro001.JPG','Soldado do Lustro Negro',20.00,'superRaro',11),(13,_binary '','INVASÃO: VINGANÇA','cartas/alecrim-sincro.png','Aromági-serafim Alecrim ',1.50,'raro',12);
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartaocredito`
--

DROP TABLE IF EXISTS `cartaocredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartaocredito` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `bandeira` varchar(255) DEFAULT NULL,
  `codigoSeguranca` varchar(255) DEFAULT NULL,
  `nomeImpresso` varchar(255) DEFAULT NULL,
  `numeroCartao` varchar(255) DEFAULT NULL,
  `validade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartaocredito`
--

LOCK TABLES `cartaocredito` WRITE;
/*!40000 ALTER TABLE `cartaocredito` DISABLE KEYS */;
INSERT INTO `cartaocredito` VALUES (1,'MASTERCARD','559','BRENO G R SILVA','5517048405832282','24/02/2023'),(2,'ELO','486','NN horse race','987654321','10/25'),(3,'ELO','486','nome','000000235','10/25'),(4,'ELO','486','nome','000000235','10/25'),(5,'VISA','486','Maruzensky horse race','000000235','10/25'),(6,'ELO','pop','nome','451384185','12/12'),(7,'ELO','486','nome','987654321','10/25'),(9,'MASTERCARD','862','nome','98765432102','22/03/2023'),(10,'VISA','786','nom 2','5167 3644 5771 4531','22/10/2022'),(11,'MASTERCARD','559','Rice Showwer','5517048405832282','24/02/2023'),(12,'ELO','852','breno','23514343245','01/01');
/*!40000 ALTER TABLE `cartaocredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartaopedido`
--

DROP TABLE IF EXISTS `cartaopedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartaopedido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `valor` decimal(19,2) DEFAULT NULL,
  `cartao_Id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnr2nptkpa3rrnl35xc6hxsawt` (`cartao_Id`),
  CONSTRAINT `FKnr2nptkpa3rrnl35xc6hxsawt` FOREIGN KEY (`cartao_Id`) REFERENCES `cartaocredito` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartaopedido`
--

LOCK TABLES `cartaopedido` WRITE;
/*!40000 ALTER TABLE `cartaopedido` DISABLE KEYS */;
INSERT INTO `cartaopedido` VALUES (1,85.00,9),(2,5.99,6),(3,85.00,9),(10,76.89,9),(11,15.97,9),(12,0.76,9),(14,-5.00,11),(16,4.01,10),(17,7.12,9),(18,51.00,1),(19,114.67,12);
/*!40000 ALTER TABLE `cartaopedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartapedido`
--

DROP TABLE IF EXISTS `cartapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartapedido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantidade` int DEFAULT NULL,
  `carta_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb8mm3avsiikh8blg31eweudnh` (`carta_id`),
  CONSTRAINT `FKb8mm3avsiikh8blg31eweudnh` FOREIGN KEY (`carta_id`) REFERENCES `carta` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartapedido`
--

LOCK TABLES `cartapedido` WRITE;
/*!40000 ALTER TABLE `cartapedido` DISABLE KEYS */;
INSERT INTO `cartapedido` VALUES (3,3,3),(4,5,6),(7,1,9),(10,1,2),(14,1,6),(15,1,1),(16,1,6),(18,1,1),(30,1,2),(31,1,1),(32,0,6),(33,2,7),(34,1,2),(35,1,6),(40,1,6),(41,1,2),(42,3,2),(43,2,1);
/*!40000 ALTER TABLE `cartapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
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
INSERT INTO `cliente` VALUES (1,_binary '','1997-12-04','RcS@uma.org','Rice Shower','47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=','Admin'),(2,_binary '','1999-05-19','brenogabriel19@gmail.com','Breno Vapo','47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=','Medio'),(5,_binary '','2001-05-03','NN@yahho.com','Nice Nature','W365eoW1pb9zKiOBhtQB08kro3RVK3BBJLECs4OHCZw=','Basico'),(10,_binary '','2012-12-09','rice@uma.org','Mihono Burbon','A401TeRcPpMV4KezC1qKCZRrtWTmPt89nVjhS0wSpbY=','Basico'),(11,_binary '\0','2001-04-30','mrk@uma.org','Maruzensky','gKTu1U3N2/qyuV1pJz8HFwd8xoTB6Fs3SwxfgJ5g3Y0=','Basico'),(13,_binary '','2014-12-13','email@gmail.com','audio visual','A401TeRcPpMV4KezC1qKCZRrtWTmPt89nVjhS0wSpbY=','Medio');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_cartaocredito`
--

DROP TABLE IF EXISTS `cliente_cartaocredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_cartaocredito` (
  `Cliente_id` int NOT NULL,
  `cartoes_Id` int NOT NULL,
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
INSERT INTO `cliente_cartaocredito` VALUES (2,1),(5,2),(10,4),(11,5),(11,6),(13,7),(1,9),(1,10),(1,11),(2,12);
/*!40000 ALTER TABLE `cliente_cartaocredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_documento`
--

DROP TABLE IF EXISTS `cliente_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_documento` (
  `Cliente_id` int NOT NULL,
  `documentos_id` int NOT NULL,
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
INSERT INTO `cliente_documento` VALUES (2,1),(5,2),(10,4),(13,6),(1,12),(1,13),(11,14);
/*!40000 ALTER TABLE `cliente_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_endereco`
--

DROP TABLE IF EXISTS `cliente_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_endereco` (
  `Cliente_id` int NOT NULL,
  `enderecos_Id` int NOT NULL,
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
INSERT INTO `cliente_endereco` VALUES (2,1),(10,11),(11,12),(1,18),(1,19),(13,22),(1,23);
/*!40000 ALTER TABLE `cliente_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom`
--

DROP TABLE IF EXISTS `cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `descricao` longtext,
  `preco` decimal(19,2) DEFAULT NULL,
  `tipoCupom` varchar(255) DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7kmen9yy8etamedc9ogsswt04` (`cliente_id`),
  CONSTRAINT `FK7kmen9yy8etamedc9ogsswt04` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom`
--

LOCK TABLES `cupom` WRITE;
/*!40000 ALTER TABLE `cupom` DISABLE KEYS */;
INSERT INTO `cupom` VALUES (1,'f453g1df4g','mo fita miminameru',10.00,'Desconto',NULL),(2,'3d85f2g1d51fg','olha o leitero',95.00,'Troca',1),(4,'bc0944b7',NULL,4.01,'Troca',2),(5,'422ff7f1',NULL,7.12,'Troca',NULL);
/*!40000 ALTER TABLE `cupom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  `validade` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
INSERT INTO `documento` VALUES (1,'45185318384834','RG','2150-12-09'),(2,'1231863438','RG','2019-12-12'),(3,'18311548','RG','2019-12-12'),(4,'49176193861','CPF','2012-12-12'),(6,'73426312999','CPF','2012-12-12'),(8,'06415721402','CPF','2015-12-05'),(12,'06415721402','RG','2001-11-30'),(13,'06415721402','CPF','2015-12-01'),(14,'45185318384834','RG','2150-12-09');
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `Id` int NOT NULL AUTO_INCREMENT,
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
INSERT INTO `endereco` VALUES (1,'Vila Nova Aparecida','08830600','Mogi das Cruzes','SP','BL 08 APT 32','Rua General Osório','355','ENTREGA'),(10,'falsa','15000','suzano','sao paulo','','rua','1','COBRANCA'),(11,'falsa','08000200','suzano','manaus','','rua','159','ENTREGA'),(12,'algum bairro','08795020','hamamatsu','singapura','','rua','1001','ENTREGA'),(18,'kinchi','0800000','nagoya','Seul','ali do lado','rua seul','36','ENTREGA'),(19,'kinchy','0800000','nagoya','kanto','ali do lado','rua busan','27','ENTREGA'),(22,'yokohama','0800030','hamamatsu','kanto','ali do lado','alameda 3','12','ENTREGA'),(23,'kinchi','15975312','hamamatsu','plasma','ali do lado','alameda 2','27','ENTREGA');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantidade` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (2,16),(3,2),(4,52),(5,66),(6,36),(7,29),(8,30),(9,30),(11,30),(12,30);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dataAtualizacao` date DEFAULT NULL,
  `statusPedido` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  `cupomDesconto_id` int DEFAULT NULL,
  `endereco_Id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jq79bpskcvo2krkbee2qdqpr` (`cliente_id`),
  KEY `FK4ho297ojdd7x5kxonqgqselnw` (`cupomDesconto_id`),
  KEY `FK7rube3v74f7bwc7e06ve9w3go` (`endereco_Id`),
  CONSTRAINT `FK1jq79bpskcvo2krkbee2qdqpr` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK4ho297ojdd7x5kxonqgqselnw` FOREIGN KEY (`cupomDesconto_id`) REFERENCES `cupom` (`id`),
  CONSTRAINT `FK7rube3v74f7bwc7e06ve9w3go` FOREIGN KEY (`endereco_Id`) REFERENCES `endereco` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2021-03-09','Trocado',264.90,'f6fb8956-b611-4b20-bdb7-15b829fb02be',1,2,19),(3,'2021-04-04','Troca',6.00,'7fd6f848-18ef-4593-853e-2a7f0affceff',1,2,18),(6,'2021-04-05','Pendente',85.00,'a73af487-ea56-40f0-a470-34fd264ddfcf',1,1,18),(7,'2021-04-06','Pendente',5.99,'030e9af0-284e-4cb8-96c0-34d78789dad5',11,1,12),(9,'2021-04-10','Pendente',85.00,'c88c92b4-5316-4392-b986-920a7c9f9728',1,1,18),(16,'2021-04-10','Pendente',86.89,'ce4a8914-a852-4ae9-a107-73a848feab87',1,1,19),(17,'2021-03-29','Trocado',25.97,'09ad8060-52f8-4a76-8b2c-40746ea83744',1,1,19),(18,'2021-04-07','Trocado',10.76,'19802902-4227-457a-93cd-c4455deb586b',1,1,18),(20,'2021-04-10','Pendente',5.00,'ac797a72-62c1-4eb2-84e7-225e6ba200b7',1,1,18),(22,'2021-04-10','Pendente',5.99,'abfb45b1-fa59-4518-b19a-9d046cbab6c8',1,1,19),(23,'2021-04-10','Pendente',6.89,'d8c66386-be39-4010-a5cb-ac83af45c709',1,1,18),(24,'2021-04-12','Entregue',165.67,'fb85e216-129b-4237-955a-d7b3f91744a3',2,1,1);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cartaopedido`
--

DROP TABLE IF EXISTS `pedido_cartaopedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cartaopedido` (
  `Pedido_id` int NOT NULL,
  `cartoes_id` int NOT NULL,
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
INSERT INTO `pedido_cartaopedido` VALUES (6,1),(7,2),(9,3),(16,10),(17,11),(18,12),(20,14),(22,16),(23,17),(24,18),(24,19);
/*!40000 ALTER TABLE `pedido_cartaopedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cartapedido`
--

DROP TABLE IF EXISTS `pedido_cartapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cartapedido` (
  `Pedido_id` int NOT NULL,
  `itens_id` int NOT NULL,
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
INSERT INTO `pedido_cartapedido` VALUES (1,3),(1,4),(3,7),(6,15),(7,16),(9,18),(16,30),(16,31),(17,32),(17,33),(18,34),(18,35),(22,40),(23,41),(24,42),(24,43);
/*!40000 ALTER TABLE `pedido_cartapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cupom`
--

DROP TABLE IF EXISTS `pedido_cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cupom` (
  `Pedido_id` int NOT NULL,
  `cupomTroca_id` int NOT NULL,
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
INSERT INTO `pedido_cupom` VALUES (9,2),(23,4),(24,5);
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

-- Dump completed on 2021-04-12 18:52:01
