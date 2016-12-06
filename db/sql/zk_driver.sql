CREATE DATABASE  IF NOT EXISTS `zk_driver` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `zk_driver`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 123.57.38.196    Database: zk_driver
-- ------------------------------------------------------
-- Server version	5.1.73

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
-- Table structure for table `zk_server_info`
--

DROP TABLE IF EXISTS `zk_server_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zk_server_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `servers` varchar(255) DEFAULT NULL COMMENT 'ZK Servers,支持集群格式',
  `isUse` char(1) CHARACTER SET latin1 DEFAULT '0' COMMENT '使用状态:0-未启用 1-启用 2-删除',
  `retry_sleep_time` int(11) DEFAULT '0' COMMENT '重连时间间隔',
  `retry_times` int(11) DEFAULT '0' COMMENT '重连次数',
  `conn_timeout` int(11) DEFAULT '0' COMMENT '连接超时时间',
  `session_timeout` int(11) DEFAULT '0' COMMENT 'Session超时时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `server_desc` varchar(255) DEFAULT NULL COMMENT '服务描述',
  `client_key` varchar(45) DEFAULT NULL COMMENT '生成Client的key,在添加时自动创建',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='ZK服务器信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zk_server_info`
--

LOCK TABLES `zk_server_info` WRITE;
/*!40000 ALTER TABLE `zk_server_info` DISABLE KEYS */;
INSERT INTO `zk_server_info` VALUES (1,'zk.sagesource.com:2181,zk.sagesource.com:2182,zk.sagesource.com:2183','1',1000,3,10000,10000,'2016-12-01 17:26:18','2016-12-01 17:26:20','test','120102198765');
/*!40000 ALTER TABLE `zk_server_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-06 17:48:00
