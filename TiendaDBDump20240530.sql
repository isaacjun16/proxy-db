-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: tiendadb
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombre_archivo` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `activo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'TV y Video','LG OLED55B2 55\" Smart OLED TV 4K-Ultra HD con Procesador Inteligente de Gran Potencia 4K a7 Gen 5 con IA, compatible con el 100% de formatos HDR, HDR Dolby Vision y Dolby Atmos, Smart TV webOS22, el mejor TV para Gaming','2024-05-29 21:14:26.116000','LG','LG OLED55B3 55\" OLED TV Smart 4K ThinQ AI™','1717038866116_tv.png',9999,'2024-05-29 21:57:32.093000',_binary ''),(2,'Celulares','nada de nada','2024-05-29 22:09:16.779000','test','Prueba','1717042156779_20240209_233253.jpg',1,NULL,_binary '\0'),(3,'Celulares','Cámara: 108+8+2MP Memoria: 8+256GB','2024-05-29 22:11:53.709000','Xiami','Xiaomi Redmi Note 12S, 8GB+256GB, Dual SIM, Liberado (Azul)','1717042313709_telefono.png',2699,NULL,_binary ''),(4,'Hogar','Refrigeradora french door, 26\' cúbicos de capacidad, acabado en acero, dispensador de agua y hielo, iluminación interior LED.','2024-05-29 22:13:54.568000','Kitchen Aid','Refrigeradora french door, 26\' de capacidad, acabado en acero, Kitchen Aid KRFF577KPS.','1717042434568_refrigeradora.png',35999,NULL,_binary ''),(5,'Electronicos','Laptop Huawei Core i5, 13\", 16GB RAM, 512GB SSD','2024-05-29 22:14:56.587000','Huawei','Laptop Huawei Core i5, 13\", 16GB RAM, 512GB SSD','1717042496587_laptop.png',16999,NULL,_binary ''),(6,'Video Juegos','Juega como nunca antes. Consola PlayStation®5 diseño slim.\r\n\r\nLa consola PS5 libera nuevas posibilidades de juego que nunca anticipaste.\r\nExperimente una carga ultrarrápida con un SSD de velocidad ultraalta, una inmersión más profunda con soporte para retroalimentación háptica, disparadores adaptativos y audio 3D.','2024-05-29 22:15:53.633000','Sony','Consola PlayStation 5 Slim Standard Edition + Control DualSense','1717042553633_consola.png',6999,NULL,_binary ''),(7,'Audio','JBL JBL2GBAR21DBBLKAM Barra de Sonido\r\n\r\nSubwoofer inalámbrico 300W\r\n2.1 CH con Deep Bass','2024-05-29 22:16:50.509000','JBL','JBL JBL2GBAR21DBBLKAM','1717042610509_teatro.png',2299,NULL,_binary ''),(8,'Cuidado Personal','Seca tu cabello a la perfección estés donde estés con este compacto pero potente secador de viaje.','2024-05-29 22:17:35.929000','Remington','Remington, D2400, Secador de Cabello, de Viaje, Con Mango Plegable','1717042655929_secadora.png',167,NULL,_binary '');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-30 13:47:51
