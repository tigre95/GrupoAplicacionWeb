CREATE DATABASE  IF NOT EXISTS `bd_sistemacompleto` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_sistemacompleto`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: bd_sistemacompleto
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `datosusuarios`
--

DROP TABLE IF EXISTS `datosusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datosusuarios` (
  `id_datousuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_datousuario`),
  KEY `fk_DatosUsuarios_Usuarios1_idx` (`id_usuario`),
  CONSTRAINT `dat_usu` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datosusuarios`
--

LOCK TABLES `datosusuarios` WRITE;
/*!40000 ALTER TABLE `datosusuarios` DISABLE KEYS */;
INSERT INTO `datosusuarios` VALUES (1,22,'edu','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(2,23,'jeffrey','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(3,24,'mayra','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(4,25,'edugt','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(5,26,'javier','da39a3ee5e6b4b0d3255bfef95601890afd80709','I'),(6,27,'iker','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(7,28,'lukas','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(8,29,'Carlos','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(9,30,'mabel','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(10,31,'jaim','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(11,32,'may','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(12,33,'sad','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(13,34,'sd','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(14,35,'adm','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(15,36,'emp','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(16,37,'eduardo123','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(17,38,'eduthj','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(18,39,'bdhad','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(19,40,'bdhad','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(20,41,'hhhhhh','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(21,42,'hhhhhh','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(22,43,'edukloi','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(23,44,'edu5566','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(24,45,'eduaaa','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(25,46,'lornzo','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(26,47,'riofrio','da39a3ee5e6b4b0d3255bfef95601890afd80709','A'),(27,48,'eduhjk','da39a3ee5e6b4b0d3255bfef95601890afd80709','A');
/*!40000 ALTER TABLE `datosusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `idTipoDepartamento` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`idTipoDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'Departamento de Sistemas','A'),(2,'Departamento de Finanzas','A'),(3,'Departamento de Contabilidad','A'),(4,'Departamento de Administracion','A'),(5,'Departamento de Ventas','A');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveltareas`
--

DROP TABLE IF EXISTS `niveltareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveltareas` (
  `id_tipotarea` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_tipotarea`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveltareas`
--

LOCK TABLES `niveltareas` WRITE;
/*!40000 ALTER TABLE `niveltareas` DISABLE KEYS */;
INSERT INTO `niveltareas` VALUES (1,'Alto','A'),(2,'Medio','A'),(3,'Bajo','A');
/*!40000 ALTER TABLE `niveltareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `id_permisos` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_tipousuario` int(3) NOT NULL,
  `descripcion` varchar(20) DEFAULT NULL,
  `crear` int(3) DEFAULT NULL,
  `buscar` int(3) DEFAULT NULL,
  `editar` int(3) DEFAULT NULL,
  `eliminar` int(3) DEFAULT NULL,
  `estado` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`id_permisos`),
  KEY `tipo_permisos_idx` (`id_tipousuario`),
  CONSTRAINT `tipo_permisos` FOREIGN KEY (`id_tipousuario`) REFERENCES `tiposusuarios` (`id_tipousuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
INSERT INTO `permisos` VALUES (0000000001,14,'personas',1,1,1,1,'A'),(0000000002,14,'empleadostareas',1,1,1,1,'A'),(0000000004,14,'permisos',1,1,1,1,'A'),(0000000005,14,'consultas',1,1,1,1,'A'),(0000000007,14,'reportes',1,1,1,1,'A'),(0000000008,14,'configuracion',1,1,1,1,'A'),(0000000009,14,'tareasjefe',1,1,1,1,'A'),(0000000010,15,'personas',0,0,0,0,'A'),(0000000011,15,'empleadostareas',1,1,1,1,'A'),(0000000013,15,'permisos',0,0,0,0,'A'),(0000000014,15,'consultas',0,1,0,0,'A'),(0000000015,15,'reportes',0,0,0,0,'A'),(0000000016,15,'configuracion',0,0,0,0,'A'),(0000000017,15,'tareasjefe',0,0,0,0,'A'),(0000000018,16,'personas',0,0,0,0,'A'),(0000000019,16,'empleadostareas',0,0,0,0,'A'),(0000000021,16,'permisos',0,0,0,0,'A'),(0000000022,16,'consultas',0,1,0,0,'A'),(0000000023,16,'reportes',0,1,0,0,'A'),(0000000024,16,'configuracion',0,0,0,0,'A'),(0000000025,16,'tareasjefe',1,1,1,1,'A');
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_persona`),
  KEY `dep_tipo_idx` (`id_departamento`),
  CONSTRAINT `dep_tipo` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`idTipoDepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (26,'Eduardo Ignacio',1,'Tigrero Bacilio','0992341234','Mueyyy','edut@hotmail.com','A'),(27,'Jeffrey Jaime',2,'Reyes Figueroa','918028754','jeffrey@hotmail.com','Atahualpa','A'),(28,'Mayra Mabel',2,'Ramirez de la O','900000036','may@hotmail.com','Libertad','A'),(29,'Eduardo Xavier',2,'Gonzalez Tumbaco','900205546','edugt@hotmail.com','Ancon','A'),(30,'Jefferson Paul',2,'Gonzalez Tumbaco','922561586','jef@hotmail.com','Salinas','A'),(31,'Iker Matias',2,'Gonzalez Ramirez','966341258','iker@hotmail.com','La libertad','A'),(32,'Lucas',2,'Podolsky','955641326','lukas@hotmail.com','Salinas','A'),(33,'Carlos Alberto',3,'Torres Ronquillo','952214563','carlos@hotmail.com','La libertad','A'),(34,'de',3,'grg','54646546','cdvfd','sgrg','A'),(35,'jknjkgdf',3,'gnrejgnrekj','4866515','dvfdv','vrevbhrej','A'),(36,'fefe',3,'frgtr','6515123','dewd','fwefew','A'),(37,'asd',2,'asd','123','213','das','A'),(38,'asd',2,'asd','12312','asd','ad','A'),(39,'jykyuk',5,'khkhj','84653132','gfdgfd','vfbgf','A'),(40,'fsdf',4,'lklñk','465465','vdd','knlknj','A'),(41,'asd',1,'asd','23','asd','asd','A'),(42,'EduardJavier',5,'GonzaTumb','45698322','lmlknn','fefefwefd','A'),(43,'fwef',4,'yhfh','8846452','hfeweye','fgkehw','A'),(44,'fwef',4,'yhfh','8846452','hfeweye','fgkehw','A'),(45,'fwef',4,'yhfh','8846452','hfeweye','fgkehw','A'),(46,'fwef',4,'yhfh','8846452','hfeweye','fgkehw','A'),(47,'ffe',3,'gggf','698854','fggopiu','tgrers','A'),(48,'fgswrg',4,'ggwrwg','964444444','ff5wwrr','gfaaaa','A'),(49,'gtdews',5,'nhjmkl','2578964581','junhlom','fewjh','A'),(50,'bbfffff',5,'sasasasasasasa','1245963254','nmhhgg','vvvvvv','A'),(51,'jfjdjlsl',5,'jdhdhd','2145873265','ff','d','A'),(52,'dsd',4,'vbfb','1234567890','bbvvf','ddddd','A');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarearealizada`
--

DROP TABLE IF EXISTS `tarearealizada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarearealizada` (
  `idTarea_realizada` int(11) NOT NULL,
  `id_tarea` int(11) NOT NULL,
  `descipcion` varchar(250) NOT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `archivo_env` varchar(45) DEFAULT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`idTarea_realizada`),
  KEY `tarea_idx` (`id_tarea`),
  CONSTRAINT `tarea` FOREIGN KEY (`id_tarea`) REFERENCES `tareas` (`id_tarea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarearealizada`
--

LOCK TABLES `tarearealizada` WRITE;
/*!40000 ALTER TABLE `tarearealizada` DISABLE KEYS */;
INSERT INTO `tarearealizada` VALUES (1,1,'realizada','2010-10-02 00:00:00','2010-10-02 00:00:00','A'),(2,2,'realizada','2010-10-02 00:00:00','2010-10-02 00:00:00','A'),(3,3,'realizada','2010-10-02 00:00:00','2010-10-02 00:00:00','A'),(4,4,'realizada','2010-10-02 00:00:00','2010-10-02 00:00:00','A'),(5,5,'realizada','2010-10-02 00:00:00','2010-10-02 00:00:00','A'),(6,6,'realizada','2010-10-02 00:00:00','2010-10-02 00:00:00','A'),(7,7,'realizada','2011-11-15 00:00:00','2011-11-18','A'),(8,8,'realizada','2011-11-25 00:00:00','2011-12-30','A'),(9,9,'realizada','2012-12-10 00:00:00','2012-12-20','A'),(10,10,'realizada','2012-12-24 00:00:00','2012-12-30','A');
/*!40000 ALTER TABLE `tarearealizada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tareas` (
  `id_tarea` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipotarea` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_persotarea` int(11) DEFAULT NULL,
  `descripcion` varchar(45) NOT NULL,
  `archivo` varchar(45) DEFAULT NULL,
  `comentario` varchar(250) DEFAULT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  `estado_tarea` int(11) NOT NULL,
  PRIMARY KEY (`id_tarea`),
  KEY `fk_Tareas_TiposTareas1_idx` (`id_tipotarea`),
  KEY `p_tatrea_idx` (`id_persona`),
  CONSTRAINT `p_tatrea` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tar_tipo` FOREIGN KEY (`id_tipotarea`) REFERENCES `niveltareas` (`id_tipotarea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES (1,1,26,26,'s',NULL,'s','2011-01-01 00:00:00','2016-05-01 00:00:00','A',1),(2,1,27,27,'s',NULL,'s','2011-02-02 00:00:00','2012-02-02 00:00:00','A',2),(3,1,28,28,'s',NULL,'s','2011-02-02 00:00:00','2013-02-02 00:00:00','A',2),(4,1,29,29,'s',NULL,'s','2011-02-02 00:00:00','2014-02-02 00:00:00','A',2),(5,1,30,30,'s',NULL,'s','2011-02-02 00:00:00','2015-02-02 00:00:00','A',5),(6,1,31,31,'s',NULL,'s','2011-02-02 00:00:00','2016-02-02 00:00:00','A',6),(7,2,32,32,'fggh',NULL,'err','2015-01-01 00:00:00','2015-01-10 00:00:00','A',1),(8,2,33,33,'rertt',NULL,'resw','2015-01-13 00:00:00','2015-01-20 00:00:00','A',1),(9,3,34,34,'ggg',NULL,'hhhh','2015-01-25 00:00:00','2015-01-30 00:00:00','A',1),(10,3,35,35,'gffff',NULL,'fggg','2015-05-20 00:00:00','2015-05-30 00:00:00','A',1);
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposusuarios`
--

DROP TABLE IF EXISTS `tiposusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposusuarios` (
  `id_tipousuario` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_tipousuario`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposusuarios`
--

LOCK TABLES `tiposusuarios` WRITE;
/*!40000 ALTER TABLE `tiposusuarios` DISABLE KEYS */;
INSERT INTO `tiposusuarios` VALUES (14,'administrador','A'),(15,'empleado','A'),(16,'jefe','A');
/*!40000 ALTER TABLE `tiposusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_tipousuario` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `estado` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id_usuario`),
  KEY `fk_Usuarios_TiposUsuarios_idx` (`id_tipousuario`),
  KEY `fk_Usuarios_Personas1_idx` (`id_persona`),
  CONSTRAINT `per_usu` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_usu` FOREIGN KEY (`id_tipousuario`) REFERENCES `tiposusuarios` (`id_tipousuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (22,14,26,'A'),(23,16,27,'A'),(24,15,28,'A'),(25,15,29,'A'),(26,15,30,'I'),(27,15,31,'A'),(28,15,32,'A'),(29,16,33,'A'),(30,15,34,'A'),(31,15,35,'A'),(32,15,36,'A'),(33,14,37,'A'),(34,14,38,'A'),(35,14,39,'A'),(36,15,40,'A'),(37,15,41,'A'),(38,15,42,'A'),(39,15,43,'A'),(40,15,44,'A'),(41,15,45,'A'),(42,15,46,'A'),(43,15,47,'A'),(44,15,48,'A'),(45,14,49,'A'),(46,16,50,'A'),(47,15,51,'A'),(48,15,52,'A');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_sistemacompleto'
--

--
-- Dumping routines for database 'bd_sistemacompleto'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_ingresar_usuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ingresar_usuarios`(vnombres varchar(100),v_id_departamento int,
vapellidos varchar(100),vcedula varchar(10),
vemail varchar(100),
vdireccion varchar(200),vid_tipousuarios int,
vusuarios varchar(100), vcontraseña varchar(100))
BEGIN
declare vid_persona int;
	declare vid_tipousuario int;
	declare vid_usuario int;
	Insert into personas(nombres,id_departamento,apellidos,cedula,direccion,email,estado)
				values(vnombres,v_id_departamento,vapellidos,vcedula,vemail,vdireccion,'A');
	set vid_persona=(select max(id_persona) from personas where estado='A');
	
Insert into usuarios(id_tipousuario,id_persona,estado)
				values(vid_tipousuarios,vid_persona,'A');

set vid_usuario=(select max(id_usuario) from usuarios where estado='A');
	Insert into datosusuarios(id_usuario,usuario,contraseña,estado)
				values(vid_usuario,vusuarios,vcontraseña,'A');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_modificar_datos_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_datos_usuario`(
v_id_persona int,
v_alias varchar(100),
v_dpassword varchar(100))
BEGIN
	Update datosusuarios set usuario=v_alias,
			                contraseña=v_dpassword
                   where id_usuario=(select id_usuario from usuarios where id_persona=v_id_persona and estado='A') and estado='A';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_modulo_usuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modulo_usuarios`(v_id_persona int,
v_nombres varchar(100),
v_apellidos varchar(100),
v_cedula varchar(10),
v_email varchar(100),
v_direccion varchar(100),
v_id_departamento int,
v_id_tipo_usuario int,

accion int)
BEGIN
	IF accion=1 THEN
		Update personas set id_persona=v_id_persona,
				    nombres=v_nombres,
						id_departamento=v_id_departamento,
			            apellidos=v_apellidos,
    				    cedula=v_cedula,
direccion=v_direccion,
 				    email=v_email
				    
							where id_persona=v_id_persona and estado='A';
		Update usuarios set id_tipousuario=v_id_tipo_usuario where estado='A' and id_persona=v_id_persona;
	ELSE
		Update personas set estado='I' where id_persona=v_id_persona;
		Update usuarios set estado='I' where id_persona=v_id_persona;
		update datosusuarios set estado='I' where id_usuario=(select id_usuario from usuarios where id_persona=v_id_persona);
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-18  7:56:07
