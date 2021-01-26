CREATE DATABASE  IF NOT EXISTS `loja_manga` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `loja_manga`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: loja_manga
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
-- Table structure for table `cartao_de_credito`
--

DROP TABLE IF EXISTS `cartao_de_credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartao_de_credito` (
  `ctcrd_id` int NOT NULL AUTO_INCREMENT,
  `ctcrd_bandeira` varchar(45) NOT NULL,
  `FK_cli_id_ctcrd` int NOT NULL,
  `ctcrd_numero_cartao` varchar(25) NOT NULL,
  `ctcrd_nome_impresso` varchar(50) NOT NULL,
  `ctcrd_validade` varchar(5) NOT NULL,
  `ctcrd_codigo_seguranca` varchar(4) NOT NULL,
  PRIMARY KEY (`ctcrd_id`),
  UNIQUE KEY `ctcrd_id_UNIQUE` (`ctcrd_id`),
  KEY `fk_Cartao_de_credito_Cliente_idx` (`FK_cli_id_ctcrd`),
  CONSTRAINT `FK_CARTAO_DE_CREDITO_CLIENTE` FOREIGN KEY (`FK_cli_id_ctcrd`) REFERENCES `cliente` (`cli_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartao_de_credito`
--

LOCK TABLES `cartao_de_credito` WRITE;
/*!40000 ALTER TABLE `cartao_de_credito` DISABLE KEYS */;
INSERT INTO `cartao_de_credito` VALUES (2,'Visa',13,'4539 8430 3778 1734','Pedrinho','09/21','755'),(3,'MasterCard',13,'5406 3403 6171 1020','Pedrinho','05/21','653'),(4,'American Express',7,'3475 368189 99117','Matheus','08/22','8041'),(5,'Diners club',7,'3841 345022 6197','Matheus','06/22','536'),(6,'Discover',8,'6011 4640 5385 1954','Emerson','04/22','5105'),(7,'enRoute',8,'2014 2253995 0843','Emerson','09/22','758'),(8,'JCB',9,'3550 3590 1901 9189','Breno','06/22','932'),(9,'Voyager',9,'86992 9033 68091 9','Breno','07/22','269'),(10,'HiperCard',10,'6062 8236 6645 4217','Breno Gabriel','04/21','168'),(11,'Aura',10,'5005 0708 4458 0198','Breno Gabriel','10/20','294');
/*!40000 ALTER TABLE `cartao_de_credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `cat_id` int NOT NULL AUTO_INCREMENT,
  `cat_nome` varchar(45) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_id_UNIQUE` (`cat_id`),
  UNIQUE KEY `cat_nome_UNIQUE` (`cat_nome`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `cli_id` int NOT NULL AUTO_INCREMENT,
  `cli_nome` varchar(256) NOT NULL,
  `cli_email` varchar(256) NOT NULL,
  `cli_admin` int NOT NULL,
  `cli_data_nascimento` varchar(10) NOT NULL,
  `ativo` varchar(20) NOT NULL DEFAULT 'ATIVO',
  `cli_ativo` tinyint NOT NULL,
  `cli_carteira` double NOT NULL,
  `cli_senha` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `cli_cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`cli_id`),
  UNIQUE KEY `cli_id_UNIQUE` (`cli_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2,'Administrador','admin@admin.com',1,'12/12/12','ATIVO',1,0,'qwer1234','00000000000'),(7,'Matheus','wgfdsagfs@asdfasdf.mok',0,'2020-12-31','ATIVO',0,0,'qwer1234','00000000000'),(8,'Emerson Santana','wgfdsagfs@asdfasdf.mok',0,'2002-01-01','INATIVO',0,0,'qwer1234','47519735842'),(9,'Breno Gabriel Rodrigues da Silva','brenogabriel19@gmail.com',0,'1999-05-19','ATIVO',0,0,'qwer1234','47519735842'),(10,'Breno Gabriel Rodrigues da Silva','wgfdsagfs@asdfasdf.mok',0,'2020-01-01','INATIVO',0,0,'qwer1234','47519735842'),(13,'Pedro Henrique','pedrinho@pedrao.com',0,'2003-12-20','ATIVO',0,0,'qwer1234','47519735842');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupom`
--

DROP TABLE IF EXISTS `cupom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupom` (
  `cup_id` int NOT NULL AUTO_INCREMENT,
  `cup_tipo` varchar(15) NOT NULL,
  `cup_valor` int NOT NULL,
  `FK_cli_id_cup` int NOT NULL,
  PRIMARY KEY (`cup_id`),
  UNIQUE KEY `cup_id_UNIQUE` (`cup_id`),
  KEY `fk_Cupom_Cliente1_idx` (`FK_cli_id_cup`),
  CONSTRAINT `FK_CUPOM_CLIENTE` FOREIGN KEY (`FK_cli_id_cup`) REFERENCES `cliente` (`cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupom`
--

LOCK TABLES `cupom` WRITE;
/*!40000 ALTER TABLE `cupom` DISABLE KEYS */;
/*!40000 ALTER TABLE `cupom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `end_id` int NOT NULL AUTO_INCREMENT,
  `end_tipo` varchar(15) NOT NULL,
  `end_logradouro` varchar(45) NOT NULL,
  `end_complemento` varchar(256) DEFAULT NULL,
  `end_numero` varchar(10) NOT NULL,
  `end_bairro` varchar(45) NOT NULL,
  `end_cep` varchar(50) NOT NULL,
  `end_cidade` varchar(45) NOT NULL,
  `end_estado` varchar(45) NOT NULL,
  `FK_cli_id_end` int NOT NULL,
  `ativo` varchar(45) NOT NULL,
  PRIMARY KEY (`end_id`),
  UNIQUE KEY `end_id_UNIQUE` (`end_id`),
  KEY `FK_ENDERECO_CLIENTE_idx` (`FK_cli_id_end`),
  CONSTRAINT `FK_ENDERECO_CLIENTE` FOREIGN KEY (`FK_cli_id_end`) REFERENCES `cliente` (`cli_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (5,'RESIDENCIA','Rua Álvaro Mathias','Nenhum','361','Sabaúna','08850-060','Mogi das Cruzes','SP',13,'ATIVO'),(6,'ENTREGA','Rua do Pão','Nenhum','420','Bairro','98765412','Mogi das Cruzes','SP',13,'ATIVO'),(7,'RESIDENCIA','Estrada Iriri','Apto 13 Bloco A','123','Cantagalo','28899-862','Rio das Ostras','RJ',7,'ATIVO'),(8,'ENTREGA','Rua Interna Cinco','Nenhum','125','Viverde I','28894-741','Rio das Ostras','RJ',7,'ATIVO'),(9,'RESIDENCIA','Rua Acre 2','Nenhum','456','Aeroporto Velho','69911-087','Rio Branco','AC',8,'ATIVO'),(10,'ENTREGA','Rua Rondônia','Qd B lote 05','458','Bosque','69900-457','Rio Branco','AC',8,'ATIVO'),(11,'RESIDENCIA','Rua Deputado Lidovino Fanton','Nenhum','789','Faxinal Menino Deus','96840-592','Santa Cruz do Sul','RS',9,'ATIVO'),(12,'ENTREGA','Rua Papa João XXIII','Nenhum','782','Faxinal Menino Deus','96840-520','Santa Cruz do Sul','RS',9,'ATIVO'),(13,'COBRANCA','Rua Frei Paulo','Apto 02 Bloco C','159','São José','49015-260','Aracaju','SE',10,'ATIVO'),(14,'ENTREGA','Vila Sabino','Qd D lote 22','153','Alto do Peru','40349-670','Salvador','BA',10,'ATIVO');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `etq_id` int NOT NULL,
  `etq_mg_id` int NOT NULL,
  `etq_quantidade` int NOT NULL,
  PRIMARY KEY (`etq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,7,122),(2,8,300);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manga`
--

DROP TABLE IF EXISTS `manga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manga` (
  `mg_id` int NOT NULL AUTO_INCREMENT,
  `mg_autor` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_ano` int NOT NULL,
  `mg_titulo` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_editora` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_edicao` int NOT NULL,
  `mg_isbn` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_paginas` int NOT NULL,
  `mg_sinopse` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_altura` float NOT NULL,
  `mg_largura` float NOT NULL,
  `mg_profundidade` float NOT NULL,
  `mg_peso` float NOT NULL,
  `mg_cod_barras` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_grupo_precificacao` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `ativo` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'ATIVO',
  `mg_quantidade` int NOT NULL,
  `mg_preco` float NOT NULL,
  `mg_motivo_inativ` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_motivo_ativ` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mg_imagem` text CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  PRIMARY KEY (`mg_id`),
  UNIQUE KEY `mg_id_UNIQUE` (`mg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manga`
--

LOCK TABLES `manga` WRITE;
/*!40000 ALTER TABLE `manga` DISABLE KEYS */;
INSERT INTO `manga` VALUES (5,'Fulano',1900,'Manga generico','Editora',0,'0',1,'Sinopse',1,1,1,1,'0','Grupo','ATIVO',1,10,'','','generico.jpg'),(7,'Eiichiro Oda',1997,'One Piece','Panini',95,'9786555121018',200,'Rei dos piratas',20,13.6,1,150,'9788542620535','Grupo A','ATIVO',123,19.5,'','','One-Piece-volume-95-cover-capa.jpg'),(8,'Chu-Gong',2018,'Solo leveling','New Pop',1,'9786586799194',200,'Farmando sozinho',21,14.8,1,150,'9786586799200','Grupo B','INATIVO',456,19.5,'','','solo-leveling-volume-1.jpg'),(9,'Eiichiro Oda',1997,'One Piece','Panini',31,'9786555121018',200,'Rei dos piratas',20,13.6,1,150,'9788542620535','Grupo','ATIVO',125,19.5,'','','one-piece-vol-31.jpg'),(10,'Eiichiro Oda',1997,'One Piece','Panini',1,'9786555121018',200,'Rei dos piratas',20,13.6,1,150,'9788542620535','Grupo A','INATIVO',124,19.5,'','','one-piece-vol-1.jpg'),(11,'Chu-Gong',2018,'Solo leveling','New Pop',2,'9786555121018',200,'Farmando sozinho',21,14.8,1,150,'9788542620535','Grupo B','ATIVO',456,19.5,'','','solo-leveling-volume-2.jpg'),(12,'Fulano',1900,'Manga generico','Panini',2,'9786555121018',1,'Algo',20,13.6,1,150,'9788542620535','Grupo A','ATIVO',1,10,'','','generico.jpg');
/*!40000 ALTER TABLE `manga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manga_categoria`
--

DROP TABLE IF EXISTS `manga_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manga_categoria` (
  `mg_cat_id` int NOT NULL AUTO_INCREMENT,
  `FK_mg_id_mg_cat` int NOT NULL,
  `FK_cat_id_mg_cat` int NOT NULL,
  PRIMARY KEY (`mg_cat_id`),
  UNIQUE KEY `mg_cat_id_UNIQUE` (`mg_cat_id`),
  KEY `fk_Manga_Categoria_Mangá_idx` (`FK_mg_id_mg_cat`),
  KEY `fk_Manga_Categoria_Categoria1_idx` (`FK_cat_id_mg_cat`),
  CONSTRAINT `FK_MANGA_CATEGORIA_CATEGORIA` FOREIGN KEY (`FK_cat_id_mg_cat`) REFERENCES `categoria` (`cat_id`),
  CONSTRAINT `FK_MANGA_CATEGORIA_MANGA` FOREIGN KEY (`FK_mg_id_mg_cat`) REFERENCES `manga` (`mg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manga_categoria`
--

LOCK TABLES `manga_categoria` WRITE;
/*!40000 ALTER TABLE `manga_categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `manga_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_entrega`
--

DROP TABLE IF EXISTS `metodo_entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_entrega` (
  `mte_id` int NOT NULL AUTO_INCREMENT,
  `mte_nome` varchar(45) NOT NULL,
  `mte_valor` double NOT NULL,
  PRIMARY KEY (`mte_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_entrega`
--

LOCK TABLES `metodo_entrega` WRITE;
/*!40000 ALTER TABLE `metodo_entrega` DISABLE KEYS */;
INSERT INTO `metodo_entrega` VALUES (1,'grátis',0);
/*!40000 ALTER TABLE `metodo_entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `ped_id` int NOT NULL,
  `ped_cartao` varchar(45) NOT NULL,
  `ped_valor_total` double NOT NULL,
  `ped_status` varchar(45) NOT NULL,
  `ped_data_pagamento` date NOT NULL,
  `ped_data_pedido` date NOT NULL,
  `ped_cli_id` int NOT NULL,
  `ped_end_id` int NOT NULL,
  `ativo` varchar(45) NOT NULL,
  `ped_metodo_entrega` int NOT NULL,
  PRIMARY KEY (`ped_id`),
  KEY `FK_cli_id_idx` (`ped_cli_id`),
  KEY `FK_end_id_idx` (`ped_end_id`),
  KEY `FK_mte_id_idx` (`ped_metodo_entrega`),
  CONSTRAINT `FK_cli_id` FOREIGN KEY (`ped_cli_id`) REFERENCES `cliente` (`cli_id`),
  CONSTRAINT `FK_end_id` FOREIGN KEY (`ped_end_id`) REFERENCES `endereco` (`end_id`),
  CONSTRAINT `FK_mte_id` FOREIGN KEY (`ped_metodo_entrega`) REFERENCES `metodo_entrega` (`mte_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'Pedrinho',19.5,'EM PROCESSAMENTO','0000-00-00','0000-00-00',13,5,'ATIVO',1),(2,'Pedrinho',19.5,'EM TROCA','0000-00-00','0000-00-00',13,6,'ATIVO',1),(3,'Matheus',19.5,'TROCA AUTORIZADA','0000-00-00','0000-00-00',7,7,'ATIVO',1),(4,'Matheus',19.5,'APROVADA','0000-00-00','0000-00-00',7,8,'ATIVO',1),(5,'Emerson',19.5,'REPROVADA','0000-00-00','0000-00-00',8,9,'ATIVO',1),(6,'Emerson',19.5,'EM TRANSITO','0000-00-00','0000-00-00',8,10,'ATIVO',1),(7,'Breno',19.5,'ENTREGUE','0000-00-00','0000-00-00',9,11,'ATIVO',1),(8,'Breno',19.5,'REPROVADA','0000-00-00','0000-00-00',9,12,'ATIVO',1),(9,'Breno Gabriel',19.5,'ENTREGUE','0000-00-00','0000-00-00',10,13,'ATIVO',1),(10,'Breno Gabriel',19.5,'ENTREGUE','0000-00-00','0000-00-00',10,14,'ATIVO',1);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos_mangas`
--

DROP TABLE IF EXISTS `pedidos_mangas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos_mangas` (
  `ped_mg_id` int NOT NULL,
  `ped_mg_mg_id` int NOT NULL,
  `ped_mg_valor` double NOT NULL,
  `ped_mg_qtde` int NOT NULL,
  `ped_mg_status` varchar(45) NOT NULL,
  `ativo` varchar(45) NOT NULL,
  PRIMARY KEY (`ped_mg_id`),
  KEY `FK_mg_id_idx` (`ped_mg_mg_id`),
  CONSTRAINT `FK_mg_id` FOREIGN KEY (`ped_mg_mg_id`) REFERENCES `manga` (`mg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos_mangas`
--

LOCK TABLES `pedidos_mangas` WRITE;
/*!40000 ALTER TABLE `pedidos_mangas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos_mangas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefone` (
  `FK_cli_id_tel` int NOT NULL,
  `tel_id` int NOT NULL AUTO_INCREMENT,
  `tel_tipo` varchar(15) NOT NULL,
  `tel_ddd` int NOT NULL,
  `tel_numero` int NOT NULL,
  PRIMARY KEY (`tel_id`),
  UNIQUE KEY `tel_id_UNIQUE` (`tel_id`),
  UNIQUE KEY `tel_numero_UNIQUE` (`tel_numero`),
  KEY `fk_Telefone_Cliente_idx` (`FK_cli_id_tel`),
  CONSTRAINT `FK_TELEFONE_CLIENTE` FOREIGN KEY (`FK_cli_id_tel`) REFERENCES `cliente` (`cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'loja_manga'
--

--
-- Dumping routines for database 'loja_manga'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-10 22:53:07
