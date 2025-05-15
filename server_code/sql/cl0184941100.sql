-- MySQL dump 10.13  Distrib 5.7.44, for Linux (x86_64)
--
-- Host: localhost    Database: cl0184941100
-- ------------------------------------------------------
-- Server version	5.7.44

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
-- Current Database: `cl0184941100`
--

/*!40000 DROP DATABASE IF EXISTS `cl0184941100`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cl0184941100` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cl0184941100`;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COLLATE utf8mb4_unicode_ci COMMENT '提问内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客服聊天';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2025-05-08 12:32:33',1,'提问内容1','回复内容1',1,1),(2,'2025-05-08 12:32:33',2,'提问内容2','回复内容2',2,2),(3,'2025-05-08 12:32:33',3,'提问内容3','回复内容3',3,3),(4,'2025-05-08 12:32:33',4,'提问内容4','回复内容4',4,4),(5,'2025-05-08 12:32:33',5,'提问内容5','回复内容5',5,5),(6,'2025-05-08 12:32:33',6,'提问内容6','回复内容6',6,6),(7,'2025-05-08 12:32:33',7,'提问内容7','回复内容7',7,7),(8,'2025-05-08 12:32:33',8,'提问内容8','回复内容8',8,8),(9,'2025-05-08 12:32:33',9,'提问内容9','回复内容9',9,9),(10,'2025-05-08 12:32:33',10,'提问内容10','回复内容10',10,10);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_friend`
--

DROP TABLE IF EXISTS `chat_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `fid` bigint(20) NOT NULL COMMENT '好友id',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `alias` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '别名',
  `type` int(11) DEFAULT '0' COMMENT '类型(0:好友申请，1:好友，2:消息)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='好友表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_friend`
--

LOCK TABLES `chat_friend` WRITE;
/*!40000 ALTER TABLE `chat_friend` DISABLE KEYS */;
INSERT INTO `chat_friend` VALUES (1,'2025-05-08 12:32:33',1,1,'名称1','file/chat_friendPicture1.jpg,file/chat_friendPicture2.jpg,file/chat_friendPicture3.jpg','角色1','表名1','别名1',1),(2,'2025-05-08 12:32:33',2,2,'名称2','file/chat_friendPicture2.jpg,file/chat_friendPicture3.jpg,file/chat_friendPicture4.jpg','角色2','表名2','别名2',2),(3,'2025-05-08 12:32:33',3,3,'名称3','file/chat_friendPicture3.jpg,file/chat_friendPicture4.jpg,file/chat_friendPicture5.jpg','角色3','表名3','别名3',3),(4,'2025-05-08 12:32:33',4,4,'名称4','file/chat_friendPicture4.jpg,file/chat_friendPicture5.jpg,file/chat_friendPicture6.jpg','角色4','表名4','别名4',4),(5,'2025-05-08 12:32:33',5,5,'名称5','file/chat_friendPicture5.jpg,file/chat_friendPicture6.jpg,file/chat_friendPicture7.jpg','角色5','表名5','别名5',5),(6,'2025-05-08 12:32:33',6,6,'名称6','file/chat_friendPicture6.jpg,file/chat_friendPicture7.jpg,file/chat_friendPicture8.jpg','角色6','表名6','别名6',6),(7,'2025-05-08 12:32:33',7,7,'名称7','file/chat_friendPicture7.jpg,file/chat_friendPicture8.jpg,file/chat_friendPicture9.jpg','角色7','表名7','别名7',7),(8,'2025-05-08 12:32:33',8,8,'名称8','file/chat_friendPicture8.jpg,file/chat_friendPicture9.jpg,file/chat_friendPicture10.jpg','角色8','表名8','别名8',8),(9,'2025-05-08 12:32:33',9,9,'名称9','file/chat_friendPicture9.jpg,file/chat_friendPicture10.jpg,file/chat_friendPicture11.jpg','角色9','表名9','别名9',9),(10,'2025-05-08 12:32:33',10,10,'名称10','file/chat_friendPicture10.jpg,file/chat_friendPicture11.jpg,file/chat_friendPicture12.jpg','角色10','表名10','别名10',10);
/*!40000 ALTER TABLE `chat_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `fid` bigint(20) NOT NULL COMMENT '好友id',
  `content` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `format` int(11) DEFAULT NULL COMMENT '格式(1:文字，2:图片)',
  `is_read` int(11) DEFAULT '0' COMMENT '消息已读(0:未读，1:已读)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,'2025-05-08 12:32:33',1,1,'内容1',1,1),(2,'2025-05-08 12:32:33',2,2,'内容2',2,2),(3,'2025-05-08 12:32:33',3,3,'内容3',3,3),(4,'2025-05-08 12:32:33',4,4,'内容4',4,4),(5,'2025-05-08 12:32:33',5,5,'内容5',5,5),(6,'2025-05-08 12:32:33',6,6,'内容6',6,6),(7,'2025-05-08 12:32:33',7,7,'内容7',7,7),(8,'2025-05-08 12:32:33',8,8,'内容8',8,8),(9,'2025-05-08 12:32:33',9,9,'内容9',9,9),(10,'2025-05-08 12:32:33',10,10,'内容10',10,10);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `value` longtext COLLATE utf8mb4_unicode_ci COMMENT '值',
  `url` longtext COLLATE utf8mb4_unicode_ci COMMENT '链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='轮播图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'2025-05-08 12:32:34','swiper1','file/swiperPicture1.jpg',NULL),(2,'2025-05-08 12:32:34','swiper2','file/swiperPicture2.jpg',NULL),(3,'2025-05-08 12:32:34','swiper3','file/swiperPicture3.jpg',NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussjiankangshipu`
--

DROP TABLE IF EXISTS `discussjiankangshipu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussjiankangshipu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康食谱评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussjiankangshipu`
--

LOCK TABLES `discussjiankangshipu` WRITE;
/*!40000 ALTER TABLE `discussjiankangshipu` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussjiankangshipu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussjiankangzhishi`
--

DROP TABLE IF EXISTS `discussjiankangzhishi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussjiankangzhishi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康知识评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussjiankangzhishi`
--

LOCK TABLES `discussjiankangzhishi` WRITE;
/*!40000 ALTER TABLE `discussjiankangzhishi` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussjiankangzhishi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiankangdangan`
--

DROP TABLE IF EXISTS `jiankangdangan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiankangdangan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '性别',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `chushengriqi` date DEFAULT NULL COMMENT '出生日期',
  `nianling` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '年龄',
  `shengao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '身高',
  `tizhong` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '体重',
  `xuexing` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '血型',
  `jiwangbingshi` longtext COLLATE utf8mb4_unicode_ci COMMENT '既往病史',
  `jiazubingshi` longtext COLLATE utf8mb4_unicode_ci COMMENT '家族病史',
  `guominyaowu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '过敏药物',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康档案';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiankangdangan`
--

LOCK TABLES `jiankangdangan` WRITE;
/*!40000 ALTER TABLE `jiankangdangan` DISABLE KEYS */;
INSERT INTO `jiankangdangan` VALUES (1,'2025-05-08 12:32:33','用户账号1','用户姓名1','性别1','file/jiankangdanganTouxiang1.jpg,file/jiankangdanganTouxiang2.jpg,file/jiankangdanganTouxiang3.jpg','2025-05-08','年龄1','身高1','体重1','血型1','既往病史1','家族病史1','过敏药物1'),(2,'2025-05-08 12:32:33','用户账号2','用户姓名2','性别2','file/jiankangdanganTouxiang2.jpg,file/jiankangdanganTouxiang3.jpg,file/jiankangdanganTouxiang4.jpg','2025-05-08','年龄2','身高2','体重2','血型2','既往病史2','家族病史2','过敏药物2'),(3,'2025-05-08 12:32:33','用户账号3','用户姓名3','性别3','file/jiankangdanganTouxiang3.jpg,file/jiankangdanganTouxiang4.jpg,file/jiankangdanganTouxiang5.jpg','2025-05-08','年龄3','身高3','体重3','血型3','既往病史3','家族病史3','过敏药物3'),(4,'2025-05-08 12:32:33','用户账号4','用户姓名4','性别4','file/jiankangdanganTouxiang4.jpg,file/jiankangdanganTouxiang5.jpg,file/jiankangdanganTouxiang6.jpg','2025-05-08','年龄4','身高4','体重4','血型4','既往病史4','家族病史4','过敏药物4'),(5,'2025-05-08 12:32:33','用户账号5','用户姓名5','性别5','file/jiankangdanganTouxiang5.jpg,file/jiankangdanganTouxiang6.jpg,file/jiankangdanganTouxiang7.jpg','2025-05-08','年龄5','身高5','体重5','血型5','既往病史5','家族病史5','过敏药物5'),(6,'2025-05-08 12:32:33','用户账号6','用户姓名6','性别6','file/jiankangdanganTouxiang6.jpg,file/jiankangdanganTouxiang7.jpg,file/jiankangdanganTouxiang8.jpg','2025-05-08','年龄6','身高6','体重6','血型6','既往病史6','家族病史6','过敏药物6'),(7,'2025-05-08 12:32:33','用户账号7','用户姓名7','性别7','file/jiankangdanganTouxiang7.jpg,file/jiankangdanganTouxiang8.jpg,file/jiankangdanganTouxiang9.jpg','2025-05-08','年龄7','身高7','体重7','血型7','既往病史7','家族病史7','过敏药物7'),(8,'2025-05-08 12:32:33','用户账号8','用户姓名8','性别8','file/jiankangdanganTouxiang8.jpg,file/jiankangdanganTouxiang9.jpg,file/jiankangdanganTouxiang10.jpg','2025-05-08','年龄8','身高8','体重8','血型8','既往病史8','家族病史8','过敏药物8'),(9,'2025-05-08 12:32:33','用户账号9','用户姓名9','性别9','file/jiankangdanganTouxiang9.jpg,file/jiankangdanganTouxiang10.jpg,file/jiankangdanganTouxiang11.jpg','2025-05-08','年龄9','身高9','体重9','血型9','既往病史9','家族病史9','过敏药物9'),(10,'2025-05-08 12:32:33','用户账号10','用户姓名10','性别10','file/jiankangdanganTouxiang10.jpg,file/jiankangdanganTouxiang11.jpg,file/jiankangdanganTouxiang12.jpg','2025-05-08','年龄10','身高10','体重10','血型10','既往病史10','家族病史10','过敏药物10');
/*!40000 ALTER TABLE `jiankangdangan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiankangmubiao`
--

DROP TABLE IF EXISTS `jiankangmubiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiankangmubiao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mubiaotizhong` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标体重',
  `mubiaoxinlv` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标心率',
  `mubiaoxueya` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目标血压',
  `jihua` longtext COLLATE utf8mb4_unicode_ci COMMENT '计划',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康目标';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiankangmubiao`
--

LOCK TABLES `jiankangmubiao` WRITE;
/*!40000 ALTER TABLE `jiankangmubiao` DISABLE KEYS */;
INSERT INTO `jiankangmubiao` VALUES (1,'2025-05-08 12:32:33','目标体重1','目标心率1','目标血压1','计划1','用户账号1','用户姓名1'),(2,'2025-05-08 12:32:33','目标体重2','目标心率2','目标血压2','计划2','用户账号2','用户姓名2'),(3,'2025-05-08 12:32:33','目标体重3','目标心率3','目标血压3','计划3','用户账号3','用户姓名3'),(4,'2025-05-08 12:32:33','目标体重4','目标心率4','目标血压4','计划4','用户账号4','用户姓名4'),(5,'2025-05-08 12:32:33','目标体重5','目标心率5','目标血压5','计划5','用户账号5','用户姓名5'),(6,'2025-05-08 12:32:33','目标体重6','目标心率6','目标血压6','计划6','用户账号6','用户姓名6'),(7,'2025-05-08 12:32:33','目标体重7','目标心率7','目标血压7','计划7','用户账号7','用户姓名7'),(8,'2025-05-08 12:32:33','目标体重8','目标心率8','目标血压8','计划8','用户账号8','用户姓名8'),(9,'2025-05-08 12:32:33','目标体重9','目标心率9','目标血压9','计划9','用户账号9','用户姓名9'),(10,'2025-05-08 12:32:33','目标体重10','目标心率10','目标血压10','计划10','用户账号10','用户姓名10');
/*!40000 ALTER TABLE `jiankangmubiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiankangshipu`
--

DROP TABLE IF EXISTS `jiankangshipu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiankangshipu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shipumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '食谱名称',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `suoxushicai` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所需食材',
  `shipugongxiao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '食谱功效',
  `shiherenqun` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '适合人群',
  `zhizuofangfa` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '制作方法',
  `fabushijian` datetime DEFAULT NULL COMMENT '发布时间',
  `xiangqingjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '详情简介',
  `thumbsup_number` int(11) DEFAULT NULL COMMENT '赞',
  `crazily_number` int(11) DEFAULT NULL COMMENT '踩',
  `storeup_number` int(11) DEFAULT NULL COMMENT '收藏数',
  `discuss_number` int(11) DEFAULT NULL COMMENT '评论数',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康食谱';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiankangshipu`
--

LOCK TABLES `jiankangshipu` WRITE;
/*!40000 ALTER TABLE `jiankangshipu` DISABLE KEYS */;
INSERT INTO `jiankangshipu` VALUES (1,'2025-05-08 12:32:33','食谱名称1','file/jiankangshipuFengmian1.jpg,file/jiankangshipuFengmian2.jpg,file/jiankangshipuFengmian3.jpg','所需食材1','食谱功效1','适合人群1','制作方法1','2025-05-08 20:32:33','详情简介1',1,1,1,1,'2025-05-08 20:32:33'),(2,'2025-05-08 12:32:33','食谱名称2','file/jiankangshipuFengmian2.jpg,file/jiankangshipuFengmian3.jpg,file/jiankangshipuFengmian4.jpg','所需食材2','食谱功效2','适合人群2','制作方法2','2025-05-08 20:32:33','详情简介2',2,2,2,2,'2025-05-08 20:32:33'),(3,'2025-05-08 12:32:33','食谱名称3','file/jiankangshipuFengmian3.jpg,file/jiankangshipuFengmian4.jpg,file/jiankangshipuFengmian5.jpg','所需食材3','食谱功效3','适合人群3','制作方法3','2025-05-08 20:32:33','详情简介3',3,3,3,3,'2025-05-08 20:32:33'),(4,'2025-05-08 12:32:33','食谱名称4','file/jiankangshipuFengmian4.jpg,file/jiankangshipuFengmian5.jpg,file/jiankangshipuFengmian6.jpg','所需食材4','食谱功效4','适合人群4','制作方法4','2025-05-08 20:32:33','详情简介4',4,4,4,4,'2025-05-08 20:32:33'),(5,'2025-05-08 12:32:33','食谱名称5','file/jiankangshipuFengmian5.jpg,file/jiankangshipuFengmian6.jpg,file/jiankangshipuFengmian7.jpg','所需食材5','食谱功效5','适合人群5','制作方法5','2025-05-08 20:32:33','详情简介5',5,5,5,5,'2025-05-08 20:32:33'),(6,'2025-05-08 12:32:33','食谱名称6','file/jiankangshipuFengmian6.jpg,file/jiankangshipuFengmian7.jpg,file/jiankangshipuFengmian8.jpg','所需食材6','食谱功效6','适合人群6','制作方法6','2025-05-08 20:32:33','详情简介6',6,6,6,6,'2025-05-08 20:32:33'),(7,'2025-05-08 12:32:33','食谱名称7','file/jiankangshipuFengmian7.jpg,file/jiankangshipuFengmian8.jpg,file/jiankangshipuFengmian9.jpg','所需食材7','食谱功效7','适合人群7','制作方法7','2025-05-08 20:32:33','详情简介7',7,7,7,7,'2025-05-08 20:32:33'),(8,'2025-05-08 12:32:33','食谱名称8','file/jiankangshipuFengmian8.jpg,file/jiankangshipuFengmian9.jpg,file/jiankangshipuFengmian10.jpg','所需食材8','食谱功效8','适合人群8','制作方法8','2025-05-08 20:32:33','详情简介8',8,8,8,8,'2025-05-08 20:32:33'),(9,'2025-05-08 12:32:33','食谱名称9','file/jiankangshipuFengmian9.jpg,file/jiankangshipuFengmian10.jpg,file/jiankangshipuFengmian11.jpg','所需食材9','食谱功效9','适合人群9','制作方法9','2025-05-08 20:32:33','详情简介9',9,9,9,9,'2025-05-08 20:32:33'),(10,'2025-05-08 12:32:33','食谱名称10','file/jiankangshipuFengmian10.jpg,file/jiankangshipuFengmian11.jpg,file/jiankangshipuFengmian12.jpg','所需食材10','食谱功效10','适合人群10','制作方法10','2025-05-08 20:32:33','详情简介10',10,10,10,10,'2025-05-08 20:32:33');
/*!40000 ALTER TABLE `jiankangshipu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiankangshuju`
--

DROP TABLE IF EXISTS `jiankangshuju`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiankangshuju` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiluriqi` date DEFAULT NULL COMMENT '记录日期',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `tizhong` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '体重',
  `xueya` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '血压',
  `xuetang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '血糖',
  `xinlv` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '心率',
  `danguchun` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '胆固醇',
  `shuimianzhiliang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '睡眠质量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiankangshuju`
--

LOCK TABLES `jiankangshuju` WRITE;
/*!40000 ALTER TABLE `jiankangshuju` DISABLE KEYS */;
INSERT INTO `jiankangshuju` VALUES (1,'2025-05-08 12:32:33','2025-05-08','用户账号1','用户姓名1','file/jiankangshujuTouxiang1.jpg,file/jiankangshujuTouxiang2.jpg,file/jiankangshujuTouxiang3.jpg','性别1','体重1','血压1','血糖1','心率1','胆固醇1','睡眠质量1'),(2,'2025-05-08 12:32:33','2025-05-08','用户账号2','用户姓名2','file/jiankangshujuTouxiang2.jpg,file/jiankangshujuTouxiang3.jpg,file/jiankangshujuTouxiang4.jpg','性别2','体重2','血压2','血糖2','心率2','胆固醇2','睡眠质量2'),(3,'2025-05-08 12:32:33','2025-05-08','用户账号3','用户姓名3','file/jiankangshujuTouxiang3.jpg,file/jiankangshujuTouxiang4.jpg,file/jiankangshujuTouxiang5.jpg','性别3','体重3','血压3','血糖3','心率3','胆固醇3','睡眠质量3'),(4,'2025-05-08 12:32:33','2025-05-08','用户账号4','用户姓名4','file/jiankangshujuTouxiang4.jpg,file/jiankangshujuTouxiang5.jpg,file/jiankangshujuTouxiang6.jpg','性别4','体重4','血压4','血糖4','心率4','胆固醇4','睡眠质量4'),(5,'2025-05-08 12:32:33','2025-05-08','用户账号5','用户姓名5','file/jiankangshujuTouxiang5.jpg,file/jiankangshujuTouxiang6.jpg,file/jiankangshujuTouxiang7.jpg','性别5','体重5','血压5','血糖5','心率5','胆固醇5','睡眠质量5'),(6,'2025-05-08 12:32:33','2025-05-08','用户账号6','用户姓名6','file/jiankangshujuTouxiang6.jpg,file/jiankangshujuTouxiang7.jpg,file/jiankangshujuTouxiang8.jpg','性别6','体重6','血压6','血糖6','心率6','胆固醇6','睡眠质量6'),(7,'2025-05-08 12:32:33','2025-05-08','用户账号7','用户姓名7','file/jiankangshujuTouxiang7.jpg,file/jiankangshujuTouxiang8.jpg,file/jiankangshujuTouxiang9.jpg','性别7','体重7','血压7','血糖7','心率7','胆固醇7','睡眠质量7'),(8,'2025-05-08 12:32:33','2025-05-08','用户账号8','用户姓名8','file/jiankangshujuTouxiang8.jpg,file/jiankangshujuTouxiang9.jpg,file/jiankangshujuTouxiang10.jpg','性别8','体重8','血压8','血糖8','心率8','胆固醇8','睡眠质量8'),(9,'2025-05-08 12:32:33','2025-05-08','用户账号9','用户姓名9','file/jiankangshujuTouxiang9.jpg,file/jiankangshujuTouxiang10.jpg,file/jiankangshujuTouxiang11.jpg','性别9','体重9','血压9','血糖9','心率9','胆固醇9','睡眠质量9'),(10,'2025-05-08 12:32:33','2025-05-08','用户账号10','用户姓名10','file/jiankangshujuTouxiang10.jpg,file/jiankangshujuTouxiang11.jpg,file/jiankangshujuTouxiang12.jpg','性别10','体重10','血压10','血糖10','心率10','胆固醇10','睡眠质量10');
/*!40000 ALTER TABLE `jiankangshuju` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiankangzhishi`
--

DROP TABLE IF EXISTS `jiankangzhishi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiankangzhishi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `jianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `neirong` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `fabushijian` date DEFAULT NULL COMMENT '发布时间',
  `discuss_number` int(11) DEFAULT '0' COMMENT '评论数',
  `storeup_number` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康知识';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiankangzhishi`
--

LOCK TABLES `jiankangzhishi` WRITE;
/*!40000 ALTER TABLE `jiankangzhishi` DISABLE KEYS */;
INSERT INTO `jiankangzhishi` VALUES (1,'2025-05-08 12:32:33','标题1','简介1','file/jiankangzhishiTupian1.jpg,file/jiankangzhishiTupian2.jpg,file/jiankangzhishiTupian3.jpg','内容1','2025-05-08',1,1),(2,'2025-05-08 12:32:33','标题2','简介2','file/jiankangzhishiTupian2.jpg,file/jiankangzhishiTupian3.jpg,file/jiankangzhishiTupian4.jpg','内容2','2025-05-08',2,2),(3,'2025-05-08 12:32:33','标题3','简介3','file/jiankangzhishiTupian3.jpg,file/jiankangzhishiTupian4.jpg,file/jiankangzhishiTupian5.jpg','内容3','2025-05-08',3,3),(4,'2025-05-08 12:32:33','标题4','简介4','file/jiankangzhishiTupian4.jpg,file/jiankangzhishiTupian5.jpg,file/jiankangzhishiTupian6.jpg','内容4','2025-05-08',4,4),(5,'2025-05-08 12:32:33','标题5','简介5','file/jiankangzhishiTupian5.jpg,file/jiankangzhishiTupian6.jpg,file/jiankangzhishiTupian7.jpg','内容5','2025-05-08',5,5),(6,'2025-05-08 12:32:33','标题6','简介6','file/jiankangzhishiTupian6.jpg,file/jiankangzhishiTupian7.jpg,file/jiankangzhishiTupian8.jpg','内容6','2025-05-08',6,6),(7,'2025-05-08 12:32:33','标题7','简介7','file/jiankangzhishiTupian7.jpg,file/jiankangzhishiTupian8.jpg,file/jiankangzhishiTupian9.jpg','内容7','2025-05-08',7,7),(8,'2025-05-08 12:32:33','标题8','简介8','file/jiankangzhishiTupian8.jpg,file/jiankangzhishiTupian9.jpg,file/jiankangzhishiTupian10.jpg','内容8','2025-05-08',8,8),(9,'2025-05-08 12:32:33','标题9','简介9','file/jiankangzhishiTupian9.jpg,file/jiankangzhishiTupian10.jpg,file/jiankangzhishiTupian11.jpg','内容9','2025-05-08',9,9),(10,'2025-05-08 12:32:33','标题10','简介10','file/jiankangzhishiTupian10.jpg,file/jiankangzhishiTupian11.jpg,file/jiankangzhishiTupian12.jpg','内容10','2025-05-08',10,10);
/*!40000 ALTER TABLE `jiankangzhishi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiankangzhuanjia`
--

DROP TABLE IF EXISTS `jiankangzhuanjia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiankangzhuanjia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhuanjiazhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专家账号',
  `zhuanjiamima` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专家密码',
  `zhuanjiaxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专家姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `nianling` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '年龄',
  `shanzhanglingyu` longtext COLLATE utf8mb4_unicode_ci COMMENT '擅长领域',
  `gerenjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '个人简介',
  PRIMARY KEY (`id`),
  UNIQUE KEY `zhuanjiazhanghao` (`zhuanjiazhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康专家';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiankangzhuanjia`
--

LOCK TABLES `jiankangzhuanjia` WRITE;
/*!40000 ALTER TABLE `jiankangzhuanjia` DISABLE KEYS */;
INSERT INTO `jiankangzhuanjia` VALUES (191,'2025-05-08 12:32:33','专家账号1','e10adc3949ba59abbe56e057f20f883e','专家姓名1','file/jiankangzhuanjiaTouxiang1.jpg','男','年龄1','擅长领域1','个人简介1'),(192,'2025-05-08 12:32:33','专家账号2','e10adc3949ba59abbe56e057f20f883e','专家姓名2','file/jiankangzhuanjiaTouxiang2.jpg','男','年龄2','擅长领域2','个人简介2'),(193,'2025-05-08 12:32:33','专家账号3','e10adc3949ba59abbe56e057f20f883e','专家姓名3','file/jiankangzhuanjiaTouxiang3.jpg','男','年龄3','擅长领域3','个人简介3'),(194,'2025-05-08 12:32:33','专家账号4','e10adc3949ba59abbe56e057f20f883e','专家姓名4','file/jiankangzhuanjiaTouxiang4.jpg','男','年龄4','擅长领域4','个人简介4'),(195,'2025-05-08 12:32:33','专家账号5','e10adc3949ba59abbe56e057f20f883e','专家姓名5','file/jiankangzhuanjiaTouxiang5.jpg','男','年龄5','擅长领域5','个人简介5'),(196,'2025-05-08 12:32:33','专家账号6','e10adc3949ba59abbe56e057f20f883e','专家姓名6','file/jiankangzhuanjiaTouxiang6.jpg','男','年龄6','擅长领域6','个人简介6'),(197,'2025-05-08 12:32:33','专家账号7','e10adc3949ba59abbe56e057f20f883e','专家姓名7','file/jiankangzhuanjiaTouxiang7.jpg','男','年龄7','擅长领域7','个人简介7'),(198,'2025-05-08 12:32:33','专家账号8','e10adc3949ba59abbe56e057f20f883e','专家姓名8','file/jiankangzhuanjiaTouxiang8.jpg','男','年龄8','擅长领域8','个人简介8'),(199,'2025-05-08 12:32:33','专家账号9','e10adc3949ba59abbe56e057f20f883e','专家姓名9','file/jiankangzhuanjiaTouxiang9.jpg','男','年龄9','擅长领域9','个人简介9'),(200,'2025-05-08 12:32:33','专家账号10','e10adc3949ba59abbe56e057f20f883e','专家姓名10','file/jiankangzhuanjiaTouxiang10.jpg','男','年龄10','擅长领域10','个人简介10');
/*!40000 ALTER TABLE `jiankangzhuanjia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jianshenjihua`
--

DROP TABLE IF EXISTS `jianshenjihua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jianshenjihua` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `jihuabiaoti` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '计划标题',
  `kaishiriqi` date NOT NULL COMMENT '开始日期',
  `jieshuriqi` date NOT NULL COMMENT '结束日期',
  `jihuaneirong` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '计划内容',
  `jihuashijian` datetime DEFAULT NULL COMMENT '计划时间',
  `beizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健身计划';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jianshenjihua`
--

LOCK TABLES `jianshenjihua` WRITE;
/*!40000 ALTER TABLE `jianshenjihua` DISABLE KEYS */;
INSERT INTO `jianshenjihua` VALUES (1,'2025-05-08 12:32:33','用户账号1','用户姓名1','计划标题1','2025-05-08','2025-05-08','计划内容1','2025-05-08 20:32:33','备注1'),(2,'2025-05-08 12:32:33','用户账号2','用户姓名2','计划标题2','2025-05-08','2025-05-08','计划内容2','2025-05-08 20:32:33','备注2'),(3,'2025-05-08 12:32:33','用户账号3','用户姓名3','计划标题3','2025-05-08','2025-05-08','计划内容3','2025-05-08 20:32:33','备注3'),(4,'2025-05-08 12:32:33','用户账号4','用户姓名4','计划标题4','2025-05-08','2025-05-08','计划内容4','2025-05-08 20:32:33','备注4'),(5,'2025-05-08 12:32:33','用户账号5','用户姓名5','计划标题5','2025-05-08','2025-05-08','计划内容5','2025-05-08 20:32:33','备注5'),(6,'2025-05-08 12:32:33','用户账号6','用户姓名6','计划标题6','2025-05-08','2025-05-08','计划内容6','2025-05-08 20:32:33','备注6'),(7,'2025-05-08 12:32:33','用户账号7','用户姓名7','计划标题7','2025-05-08','2025-05-08','计划内容7','2025-05-08 20:32:33','备注7'),(8,'2025-05-08 12:32:33','用户账号8','用户姓名8','计划标题8','2025-05-08','2025-05-08','计划内容8','2025-05-08 20:32:33','备注8'),(9,'2025-05-08 12:32:33','用户账号9','用户姓名9','计划标题9','2025-05-08','2025-05-08','计划内容9','2025-05-08 20:32:33','备注9'),(10,'2025-05-08 12:32:33','用户账号10','用户姓名10','计划标题10','2025-05-08','2025-05-08','计划内容10','2025-05-08 20:32:33','备注10');
/*!40000 ALTER TABLE `jianshenjihua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menujson` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'2025-05-08 12:32:34','[{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-discover\",\"buttons\":[\"查看\",\"修改\"],\"classname\":\"config\",\"menu\":\"轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"news\",\"menu\":\"新闻资讯\",\"menuJump\":\"列表\",\"tableName\":\"news\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goodsnew\",\"buttons\":[\"查看\",\"修改\"],\"classname\":\"systemintro\",\"menu\":\"网站介绍\",\"menuJump\":\"列表\",\"tableName\":\"systemintro\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-link\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"syslog\",\"menu\":\"操作日志\",\"menuJump\":\"列表\",\"tableName\":\"syslog\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"onlineMessage\",\"menu\":\"在线留言\",\"menuJump\":\"列表\",\"tableName\":\"online_message\"}],\"fontClass\":\"icon-common3\",\"menu\":\"系统管理\",\"unicode\":\"&#xeda5;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vipcard\",\"buttons\":[\"查看\"],\"classname\":\"chat\",\"menu\":\"客服聊天\",\"menuJump\":\"列表\",\"tableName\":\"chat\"}],\"fontClass\":\"icon-common22\",\"menu\":\"客服聊天管理\",\"unicode\":\"&#xee04;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-goods\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"users\",\"menu\":\"管理员\",\"menuJump\":\"列表\",\"tableName\":\"users\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"用户统计\",\"首页总数\",\"首页统计\",\"私信\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"私信\",\"用户统计\",\"首页总数\",\"首页统计\"],\"classname\":\"yonghu\",\"menu\":\"用户\",\"menuJump\":\"列表\",\"tableName\":\"yonghu\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"私信\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"私信\"],\"classname\":\"jiankangzhuanjia\",\"menu\":\"健康专家\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhuanjia\"}],\"fontClass\":\"icon-user1\",\"menu\":\"用户管理\",\"unicode\":\"&#xef97;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"热门健康知识统计\",\"查看评论\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\",\"首页总数\",\"首页统计\",\"热门健康知识统计\"],\"classname\":\"jiankangzhishi\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhishi\"}],\"fontClass\":\"icon-common17\",\"menu\":\"健康知识管理\",\"unicode\":\"&#xedfe;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"classname\":\"jiankangshipu\",\"menu\":\"健康食谱\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshipu\"}],\"fontClass\":\"icon-common40\",\"menu\":\"健康食谱管理\",\"unicode\":\"&#xeebb;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiankangdangan\",\"menu\":\"健康档案\",\"menuJump\":\"列表\",\"tableName\":\"jiankangdangan\"}],\"fontClass\":\"icon-common36\",\"menu\":\"健康档案管理\",\"unicode\":\"&#xee9f;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiankangshuju\",\"menu\":\"健康数据\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshuju\"}],\"fontClass\":\"icon-common21\",\"menu\":\"健康数据管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"yongyaoguanli\",\"menu\":\"用药管理\",\"menuJump\":\"列表\",\"tableName\":\"yongyaoguanli\"}],\"fontClass\":\"icon-common21\",\"menu\":\"用药管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiankangmubiao\",\"menu\":\"健康目标\",\"menuJump\":\"列表\",\"tableName\":\"jiankangmubiao\"}],\"fontClass\":\"icon-common35\",\"menu\":\"健康目标管理\",\"unicode\":\"&#xee8c;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jianshenjihua\",\"menu\":\"健身计划\",\"menuJump\":\"列表\",\"tableName\":\"jianshenjihua\"}],\"fontClass\":\"icon-common42\",\"menu\":\"健身计划管理\",\"unicode\":\"&#xef24;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"yinshijilu\",\"menu\":\"饮食记录\",\"menuJump\":\"列表\",\"tableName\":\"yinshijilu\"}],\"fontClass\":\"icon-common7\",\"menu\":\"饮食记录管理\",\"unicode\":\"&#xedb4;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"新闻资讯\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"查看评论\"],\"classname\":\"jiankangshipu\",\"menu\":\"健康食谱\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshipu\"}],\"menu\":\"健康食谱管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"查看\",\"查看评论\"],\"classname\":\"jiankangzhishi\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhishi\"}],\"menu\":\"健康知识管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"jiankangzhuanjia\",\"menu\":\"健康专家\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhuanjia\"}],\"menu\":\"健康专家\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"pathName\":\"users\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\",\"新增\",\"修改\",\"删除\"],\"classname\":\"jiankangdangan\",\"menu\":\"健康档案\",\"menuJump\":\"列表\",\"tableName\":\"jiankangdangan\"}],\"fontClass\":\"icon-common36\",\"menu\":\"健康档案管理\",\"unicode\":\"&#xee9f;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiankangshuju\",\"menu\":\"健康数据\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshuju\"}],\"fontClass\":\"icon-common21\",\"menu\":\"健康数据管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"yongyaoguanli\",\"menu\":\"用药管理\",\"menuJump\":\"列表\",\"tableName\":\"yongyaoguanli\"}],\"fontClass\":\"icon-common21\",\"menu\":\"用药管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiankangmubiao\",\"menu\":\"健康目标\",\"menuJump\":\"列表\",\"tableName\":\"jiankangmubiao\"}],\"fontClass\":\"icon-common35\",\"menu\":\"健康目标管理\",\"unicode\":\"&#xee8c;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-pic\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jianshenjihua\",\"menu\":\"健身计划\",\"menuJump\":\"列表\",\"tableName\":\"jianshenjihua\"}],\"fontClass\":\"icon-common42\",\"menu\":\"健身计划管理\",\"unicode\":\"&#xef24;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"yinshijilu\",\"menu\":\"饮食记录\",\"menuJump\":\"列表\",\"tableName\":\"yinshijilu\"}],\"fontClass\":\"icon-common7\",\"menu\":\"饮食记录管理\",\"unicode\":\"&#xedb4;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"popupRemind\",\"menu\":\"吃药提醒\",\"menuJump\":\"列表\",\"tableName\":\"popup_remind\"}],\"fontClass\":\"icon-common10\",\"menu\":\"弹窗提醒管理\",\"unicode\":\"&#xedd1;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\"],\"classname\":\"storeup\",\"menu\":\"我的收藏\",\"menuJump\":\"1\",\"tableName\":\"storeup\"}],\"fontClass\":\"icon-common21\",\"menu\":\"我的收藏管理\",\"unicode\":\"&#xee03;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"新闻资讯\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"查看评论\"],\"classname\":\"jiankangshipu\",\"menu\":\"健康食谱\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshipu\"}],\"menu\":\"健康食谱管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"查看\",\"查看评论\"],\"classname\":\"jiankangzhishi\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhishi\"}],\"menu\":\"健康知识管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"jiankangzhuanjia\",\"menu\":\"健康专家\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhuanjia\"}],\"menu\":\"健康专家\"}],\"hasBackLogin\":\"否\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"pathName\":\"yonghu\",\"roleName\":\"用户\",\"tableName\":\"yonghu\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"热门健康知识统计\",\"查看评论\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-flashlightopen\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"classname\":\"jiankangzhishi\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhishi\"}],\"fontClass\":\"icon-common17\",\"menu\":\"健康知识管理\",\"unicode\":\"&#xedfe;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"classname\":\"jiankangshipu\",\"menu\":\"健康食谱\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshipu\"}],\"fontClass\":\"icon-common40\",\"menu\":\"健康食谱管理\",\"unicode\":\"&#xeebb;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"新闻资讯\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"查看评论\"],\"classname\":\"jiankangshipu\",\"menu\":\"健康食谱\",\"menuJump\":\"列表\",\"tableName\":\"jiankangshipu\"}],\"menu\":\"健康食谱管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"查看\",\"查看评论\"],\"classname\":\"jiankangzhishi\",\"menu\":\"健康知识\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhishi\"}],\"menu\":\"健康知识管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"私信\"],\"classname\":\"jiankangzhuanjia\",\"menu\":\"健康专家\",\"menuJump\":\"列表\",\"tableName\":\"jiankangzhuanjia\"}],\"menu\":\"健康专家\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"是\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"pathName\":\"jiankangzhuanjia\",\"roleName\":\"健康专家\",\"tableName\":\"jiankangzhuanjia\"}]');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='新闻资讯';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2025-05-08 12:32:33','余生，愿你活出自己的精彩','曾几何时，为了让自己显得合群，选择去做自己不喜欢的事;为了讨好喜欢的人，一次次地委屈自己;甚至，为了陌生人的一个眼神，就陷入了深深的自我怀疑。','file/newsPicture1.jpg','可是，这样小心翼翼的你，一定很累吧。你以为照顾了所有人的想法，就能得到他们的喜欢;你以为逼着自己合群，就能真的合群。直到后来才发现，在日复一日对自己的为难中，你逐渐弄丢了真实的自己.\n殊不知，人生在世，最不值得的事情，就是在别人的眼光中，迷失自己。其实，正如世界上没有十全十美的人，你也同样无法做到让每个人满意。\n世上没有不快乐的人，只有不肯让自己快乐的心。有一句话说:\n如果一段关系让你变得卑微，你可以选择抽身离去;如果一个人总是让你悲伤哭泣，你要学会放手。\n始终相信，每个人最初的样子，都是最美好的。真正喜欢你的人，会喜欢你原来的样子:真正爱你的从来不需要你的委曲求全。\n后半生，请把身上的枷锁，全部解除。去做喜欢的事，去听自己内心的声音。你会发现，这世上没有什么事情比活出自己更加幸福。\n要相信，真实的自己亦是一道风景线。你若盛开，清风自来。愿你此生尽兴，不负韶华，'),(2,'2025-05-08 12:32:33','岁月无常，好好珍惜','常听到这样的话，如果一切可以重新开始，我一定改掉任性，学会坚持，如果有下辈子，我一定好好珍惜，不会再弄丢原本属于自己的幸福。','file/newsPicture2.jpg','然而，世上有很多事情没有再来一次的机会，一旦错失就意味着无法弥补，正如人与人之间，有的只是一世的缘分，一旦转身，就意味着再无可能。\n这一生只活一次，有些人也只爱一回。\n所以，在拥有时就好好把握，对于来世，可以适当憧憬，但不要一昧去幻想，活着，唯有珍惜身边做好眼前事，才能让明天少一些遗憾。人，\n既然有幸来到这个世上，那就要努力把这辈子活好。岁月其实远没有想象中的那么漫长，若是能爱就好好爱，别让说好相伴一生的人，中途散走;若是当下能相知相守，就别去等待虚无缥缈的未来。\n就像一位网友留言说的:我从来不去期待下辈子，我要的只有今生，世事无常，来世是怎样的光景，谁都无法预知。对我来说，在有限的时光里，善待爱自己及自己爱的人，用心珍惜相聚的每一时刻，努力过好当下就足够了。\n时光匆匆，韶华似水，每个人都只有这短暂的一生。\n有些事，如果喜欢就坚定努力的去做，有些人，如果爱就真诚用心地去爱，别幻想着重来，别总是寄希望于下辈子。\n下辈子，彼此也许再也不会遇见，就算遇见了，谁还会记得谁，今生相互承诺的两个人，来生也许相见不相识。\n想起之前看过的一句话:人生没有假如，我们没有来世，下辈子，你我也许只是大千世界里互不相识的两个陌生人。\n的确，生命只有一次，今生有幸相遇的人，下辈子不一定能遇见，就算能遇见，谁能把谁陪伴，谁又是谁的谁?\n余生不长，趁着岁月未老，好好珍惜这辈子的缘分吧，别等到失去了才悔不该当初。'),(3,'2025-05-08 12:32:33','与其背着烦恼活，不如带着美好过','一直觉得，感情里，无疾而终并不可怕，可怕的是结束后，你还抱着回忆不肯放;岁月中，失去并不可怕，可怕的是失去后，你还揪着曾经不肯忘。\n有些事，明知道不应该继续，却始终没有转身的勇气;有些人，明知道已经成为过去，却还是舍不得放手;有时候，明知道纠缠于往事旧人只会徒增伤悲，却仍然执着的去回忆，去留恋。','file/newsPicture3.jpg','人生短短几十年，为什么要蹉跎浪费在无可挽回的失去上，为什么不酒脱一点，看开一点，活得轻松快乐一点。\n这世间，没有谁的人生是十全十美的，总要面对一些离别，总要经历一些失去，如果总对身边的一切执念太深，只会越活越累。\n时间不停向前，人也不可避免的要向前看。面对一些不如意、不顺心的事，该放的就放，该忘的就忘，别总把自己困在失败的悲伤中。\n生而为人，放不是无能，而是一种洒脱，忘更不是懦弱，而是一种智慧。\n在来去匆匆，聚散不定的现实里，放下不属于自己的感情，才能有新的开始，忘记心中的烦恼，才能收获愉快的心情。\n所以，有些离开的人，该放就放下吧，一直念念不忘，伤害的是自己，有些烦心事，该忘就忘了吧一直耿耿于怀，痛苦的是自己。\n-辈子本就不长，何必让自己活得那么累，过去的情就让它过去，别再留恋，想不通的事就算了，别再纠结。\n人生的旅途，总有些事让人烦，总有些情让人伤。只有学会忘记过往的不如意，忘记一些不必要的烦恼，以轻盈的身心重新出发，才不会被旧人旧事所累。\n记得，每个人心灵的空间都是有限的，当装多了纠结与伤感，就装不下自在与欢快了。要想日子过得简单轻松一点，就要懂得放下执念，忘掉痛苦，清除伤。'),(4,'2025-05-08 12:32:33','理想很丰满，现实很骨感','游戏里的你帅气无比，乘风御剑，通关杀敌无所不能，你满足于那其中的成就感，你满足于通关的畅快淋漓。','file/newsPicture4.jpg','年轻人，可那毕竟不是现实，理想很丰满，现实很骨感这不是玩笑，你不走出来，怎么会知\n你真的想等到某天喜欢上一个姑娘时，才去纠结咖啡钱电影钱你要如何与爸妈张口，才去苦恼为她买礼物你要怎样省吃俭用节衣缩食?\n你没有挥金如土的.魄力，没有殷实的家境，没有既定的光芒锦绣的前程，二十出头的年纪，你不努力，你想拿什么报答那个在你身边用最好的青春陪伴你的姑娘?你又拿什么报答含辛茹苦养育你这么多年的父母?\n生活不是游戏，不会给你那么多反复再来的机会，青春年少风华正茂，年轻人，你不努力，你想指望什么?\n年轻人，走出来奋斗吧!累也好苦也罢。如果你现在对自己各种放纵，你指望以后你用什么条件来放松?\n年轻人，你要努力赚钱，为了自己，也为了你爸妈。为了他们在以后逛商场的时候，可以给自己买东西像给你买东西一样毫不犹豫。为了他们可以在同老友谈起你时可以一脸安详，而不是想着，这个月，儿子交了房租还有钱吃饭么。我们这个年龄的人，已经像吸血鬼一样巴着父母活了二十多年了，还真的要继续吸着他们的血活下去么?\n年轻人，停止期望凡事简单容易。如果总是挑简单的事情做，那你永远成不了大器，生活总会在始料不及时考验你，所以你必须挑战困难，保持坚强，世上任何值得拥有的东西都需要付诸努力，勤奋与自律，倘若自甘平庸，那你就会变得庸碌无为。\n年轻人，与我们一起，挑战一下自己，拥抱一下梦想。你想要的生活，跳起来，一定够得到!'),(5,'2025-05-08 12:32:33','理想是一种精神上的追求','如果前方是一片黑暗，理想是否会黯淡?黑暗终究不能限制理想的步伐，只是需要时间。','file/newsPicture5.jpg','是否会撕裂黑暗的结界?给理想一点时间，是否会完成理想的目标?太多时候，黑给黑暗一缕曙光，暗限制了理想的脚步，时间成了撕裂黑暗的曙光。社会的压抑，未知的迷茫，甚至是事实的打击，都成了所谓的黑暗。\n理想应该是一种精神上的追求，一种我们内心深处的渴望。也许理想的种类太多，不一样的人也有不一样的理想。但唯一的共同点就是我们在追求，追求自己精神上亦或是心中的目标。但总是不尽人意，一来存在太多不确定因素，二来时间还不够。理想并不是一而就的空话。\n想过放弃，因为理想与现实的差距。但又不想放弃，因为理想是精神上的追求心中的渴望。纠结了太多，却是否想过在纠结的时候，已经选择了错过。被刻意选择的错过是什么?是路过。我们在不经意间路过了多少理想，只是因为纠结。如果坚定自己的理想，用时间去行动，是否会少几次刻意的错过:-次，与一位朋友谈到如何乐观，然后追求理想。朋友想了一会儿，给我的回答是正能量与时间。他说，想要乐观就要不断给自己正能量。事物有阴阳两面，如果阳代表乐观，那在我们看待一件事时要看阳面千万别看阴面。这样自己不会消极，某种程度上来说就是一种正能量。想要追求理想，务必要花费时间。正如没有风平浪静的大海，想要追求理想，要时间。给理想一点时间，让自己去追求，不顾切的追求。\n点点滴滴的小事构成了我们的生活，点点滴滴的努力给理想铺平了路。每一次小成功都为理想迈进了一步。每一次失败，都告诉我们一些教训，反方向来说为下一次成功提供了经验。不要觉得失败是可怕的，长远来看，正是失败才有了成功。\n时间在流逝。如果安于现状，就会乐不思。理想需要时间不代表我们可以挥霍，我们能做的是珍惜时间，去为理想拼搏。\n间接意义上讲，理想的时间是我们的努力。\n如果正在路上，一直任性向前走，忘记时间不回头。'),(6,'2025-05-08 12:32:33','人生如梦，梦想是帆','人生如梦，梦想是帆，每个人都有一个只属于自己的梦，但我们同属一个国家，所以每个人的梦又与国家的兴衰荣辱紧密相连。先哲顾炎武曾说:天下兴亡，匹夫有责。只有国家好，大家才能好。','file/newsPicture6.jpg','我依然清楚的记得:\n当甲午战争战败，日寇无礼踏破中国的门户;当八国联军侵入北京，无情掠夺中国的财产:当七七事变发生，中国的老人、妇孺被残忍杀害的时候，我在想那时中国的梦是怎样的!\n我虽不曾亲眼看到，但那却是铁一般的事实。因为从老人们那深邃的眼神中可以感到无尽的愤懑;从他们干瘪的脸颊可以看到深情的泪水，从他们嘹亮的军歌中可以想到那奋勇杀敌时的豪迈;从他们激昂话语中听到那誓要捍卫家园振兴中华的誓言。作为新一代青年的我们难道不应该树立远大的理想，付之以踏实的行动，去继承先辈们的使命。去实现中华民族的伟大崛起和复兴吗?\n有梦才能使中国繁荣!\n在改革开放以来中国取得了一系列的可以载入中国史册的成就。香港、澳门的回归，经济特区的建立，使中国成为发展国家中的经济大国，科技先进国和军事强国。当中国成功举办奥运的时候，当神九飞天的时候，当蛟龙入海的时候，当航母下水的时候，当莫言荣获诺贝尔文学奖的时候。我相信每个人都感觉到了无比的自豪。但是现在的中国与其他发达国家还有很大差距。作为新一代的我们，难道不应该志存高远吗?\n我想有的人会说，我们的力量是有限的。的确个人的力量很渺小，但是中国梦就是因一个个微不足道的个人的梦一直汇集、汇集，然后凝聚成的一个巨大的梦。冯至在《十四行诗》中写道，我们准备着，深深领受，那些意想不到的奇迹，在漫长的岁月里，忽然有彗星的出现，狂风乍起。\n梦想是美丽的，它是美的期望;梦想是阳光的，它使人由浮躁走向踏实;梦想是充满力量的，它可以激发人身体里无限的潜能。我们期盼的是国泰民安、经济发展、政治清明、文化繁荣、社会和谐、生态良\n好、公平正义。这才是中国人伟大的梦'),(7,'2025-05-08 12:32:33','若月亮没来-王宇宙Leto，乔浚丞','《若月亮没来(若是月亮还没来)》是王宇宙Leto，乔浚丞演唱的歌曲，于2024年1月22日上线发行。','file/newsPicture7.jpg','风吹过山 船靠了岸  风光呀 一点点看  我走向北 你去往南\n故事呀 一篇篇翻  好烦 又加班到很晚  你搭上空荡的 地铁已是末班\n好烦 很爱却要分开  恋爱谈不明白  好烦 接近理想好难 却又还很不甘\n如何拥抱平淡  如果 最难得到圆满  不如选择坦然 若是月亮还没来\n路灯也可照窗台  照着白色的山茶花微微开  若是晨风还没来\n晚风也可吹入怀  吹着那一地树影温柔摇摆  你总以为你不够好\n不够苗条和美貌  可是完美谁能达到  做你自己就很好\n烦恼烦恼拥有太少  没有房车没有钞票  可爱你的人永远会 \n把你当做心尖珍宝  我也懂大多数的时候  你只想逃离这世界\n我也懂太多的情绪在  一个人失眠的深夜 你可以是悲伤或者埋怨\n但请不要放弃明天  这一直灰暗的世界  我想看见你的笑脸\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n李哲今年1月份创作了《若月亮没来》这首歌。随着歌曲的热度不断上升，约他写歌的人越来越多，他的工作变得更加繁忙。\n近年来，李哲凭借对音乐的热爱和不懈的努力，创作了几百首音乐作品，其中不乏广为人知的佳作，例如王栎鑫的《一个人》、何赛飞与叶炫清在央视演唱的《木兰诗》等歌曲，都是由李哲作曲。\n如今，李哲的音乐才华和努力得到了业界的广泛认可，他的音乐作品多次登上各大音乐平台的热歌榜，成为备受瞩目的新生代音乐人。现在，走在街头、坐在车里，经常会听到自己的作品。看到自己的音乐作品受到如此广泛的喜爱和认可，我感到非常有成就感。李哲笑着说，他会努力创作更多优秀音乐作品，为家乡争光。'),(8,'2025-05-08 12:32:33','-米阳光传递一份温暖','黑暗中跌倒，坚强里苦笑。只是因为青春还在，所以梦想一直在路上。当年华未曾老去，青春没有散场，留给幽暗的岁月，还有几分值得珍藏?孤独无助时、伤心难过时总爱恣意挥霍愤怒。伤了、痛了原本以为就都结束了;可是心里还不甘愿放弃，就只为一个理由，有梦想的人永不孤单。','file/newsPicture8.jpg','放进去了的青春，走不出来的悲伤却总是给年少时的我们留下太多无法割舍的情怀。记录岁月的足迹，聆听时光的故事。然轻吟浅唱出，青春嘉年华。\n故事里总会有，那些太多感慨于时间的无情，太多对往事的追忆，太多的太多，而今刻在了心里也停泻在了流年里。一段无法抹去的回忆，也只不过是一场烟火燃放的瞬间，却也一辈子在脑海里兜兜转转。很是怀念那些相遇的日子，邂逅了幸福也留在了时间轨迹里。时而想起，那些曾肩并肩奋斗的日子，那些写满感动与快乐的岁月。\n如今，我们似乎渐行渐远，彼此奋斗着青春的轮回。渐渐的，我们都长大了，时间改变了我们时刻分享彼此快乐与不快乐的习惯，青春的渐逝，岁月的更迭让我们变得都不再联系，身边的朋友也越来越少;那些欢笑，那些幸福的流年也随着我们的陌生而变得更加奢侈。也许多年以后，我们都有了自己的朋友，有了自己关心的人。那时，我们再细数那些癫狂在你生命里一辈子的记忆故事，蓦然发现一切都停留在了回忆里。'),(9,'2025-05-08 12:32:33','自己的路自己走，自己的心自己懂','岛上书店》中有句话:每个人的生命里，都有最艰难的那一年，将人生变得美好而辽阔。\n你有没有经历过那么一年，在走过的岁月中跋涉得异常艰难困苦，却仍无法灭前行的勇气。哪怕所有人都极力劝阻，也要不甘心地试一试;哪怕前方已没有道路，却还在坚定前行;哪怕再看不到任何希望，也要竭尽全力地为自己争取一个机会。','file/newsPicture9.jpg','你开始不动声色的扮演着一个大人，经历着生活日复一日的打。\n每天醒在天还没亮的五六点，挤过早高峰的地下铁，面对成山的工作，加班到深夜倒下就睡更成为了日常，还要应付各色的人际关系，逐渐开始接受努力也不一定有回报的道理。\n这段日子也许很长，但你没有放弃对生活的信心，始终相信人生总会变得一帆风顺，更坚信每件事的最后都会有一个好的结局，如果不是，说明还没到最后。\n于是你树立新的目标，让这一年的奔波更有方向。\n虽然永远无法预料明天是晴是雨，也无法预知你在乎的人是否还在身边，你一直以来的坚持究竟能换来什么。\n但你能决定的是，今天有没有备好雨伞，有没有好好爱人以及是否为自己追求的理想拼尽全力。\n永远不要只看见前方路途遥远而忘了从前的自己坚持了多久才走到了这里，\n今天拼尽全力虽然艰辛万分，在未来都将成为落在你身上的礼物。'),(10,'2025-05-08 12:32:33','愿你有明天可奔赴，有过往可回头','此去经年，用一张离家的车票，换一段有故事可说的人生。\n大概就是那时候怀揣着梦想，背上了行囊，一腔热血决定远行的你。独自拎着一只行李箱，背着双肩包在深夜抵达一个陌生的城市，站在人潮涌动的车站出口，望着宽敞公路上车水马龙的喧嚣，城市里亮如白昼的繁华。','file/newsPicture10.jpg','这世界上的人们都活的匆匆忙忙，每个人看起来都很焦虑，每个人都有自己的苦楚，却往往找不到人可以诉说。也许你的倾诉，对别人来说是一种打扰，而且别人没经历过你的经历，所以无法体会你的心情。我们总是在心情不好的时候拿着手机，翻翻通讯录，然后再默默放下手机，压住心中那股想要找人说说话的想法。\n等到低落的情绪慢慢有些好转，才开始明白，人这一生，无论是什么样的路，都是自己的选择，冲动也好，深思熟虑也罢，自己选的路，不管多难，都要自己走下去。心中的感受，不管是复杂还是简单，都只能自己来懂，别人帮不了你，也安慰不了你，顶多给你一些无济于事的劝告，最后还得靠自己想开。你选的幸福，你自己享受;你选的眼泪，你自己来流，生活就是如此，自己的路，自己走;自己的心，自己懂。');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online_message`
--

DROP TABLE IF EXISTS `online_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `online_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `phone` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '留言内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='在线留言';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_message`
--

LOCK TABLES `online_message` WRITE;
/*!40000 ALTER TABLE `online_message` DISABLE KEYS */;
INSERT INTO `online_message` VALUES (1,'2025-05-08 12:32:33','姓名1','电话1','留言内容1'),(2,'2025-05-08 12:32:33','姓名2','电话2','留言内容2'),(3,'2025-05-08 12:32:33','姓名3','电话3','留言内容3'),(4,'2025-05-08 12:32:33','姓名4','电话4','留言内容4'),(5,'2025-05-08 12:32:33','姓名5','电话5','留言内容5'),(6,'2025-05-08 12:32:33','姓名6','电话6','留言内容6'),(7,'2025-05-08 12:32:33','姓名7','电话7','留言内容7'),(8,'2025-05-08 12:32:33','姓名8','电话8','留言内容8'),(9,'2025-05-08 12:32:33','姓名9','电话9','留言内容9'),(10,'2025-05-08 12:32:33','姓名10','电话10','留言内容10');
/*!40000 ALTER TABLE `online_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `popup_remind`
--

DROP TABLE IF EXISTS `popup_remind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `popup_remind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '发布人用户ID',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '私人' COMMENT '类型',
  `brief` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `remind_time` datetime DEFAULT NULL COMMENT '提醒时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='吃药提醒';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `popup_remind`
--

LOCK TABLES `popup_remind` WRITE;
/*!40000 ALTER TABLE `popup_remind` DISABLE KEYS */;
INSERT INTO `popup_remind` VALUES (1,'2025-05-08 12:32:33',1,'标题1','私人','简介1','内容1','2025-05-08 20:32:33'),(2,'2025-05-08 12:32:33',2,'标题2','私人','简介2','内容2','2025-05-08 20:32:33'),(3,'2025-05-08 12:32:33',3,'标题3','私人','简介3','内容3','2025-05-08 20:32:33'),(4,'2025-05-08 12:32:33',4,'标题4','私人','简介4','内容4','2025-05-08 20:32:33'),(5,'2025-05-08 12:32:33',5,'标题5','私人','简介5','内容5','2025-05-08 20:32:33'),(6,'2025-05-08 12:32:33',6,'标题6','私人','简介6','内容6','2025-05-08 20:32:33'),(7,'2025-05-08 12:32:33',7,'标题7','私人','简介7','内容7','2025-05-08 20:32:33'),(8,'2025-05-08 12:32:33',8,'标题8','私人','简介8','内容8','2025-05-08 20:32:33'),(9,'2025-05-08 12:32:33',9,'标题9','私人','简介9','内容9','2025-05-08 20:32:33'),(10,'2025-05-08 12:32:33',10,'标题10','私人','简介10','内容10','2025-05-08 20:32:33');
/*!40000 ALTER TABLE `popup_remind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) DEFAULT NULL COMMENT 'refid',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='我的收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syslog`
--

DROP TABLE IF EXISTS `syslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `operation` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户操作',
  `method` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
  `params` longtext COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
  `time` bigint(20) DEFAULT NULL COMMENT '请求时长(毫秒)',
  `ip` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syslog`
--

LOCK TABLES `syslog` WRITE;
/*!40000 ALTER TABLE `syslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `syslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemintro`
--

DROP TABLE IF EXISTS `systemintro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemintro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '副标题',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `picture1` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片1',
  `picture2` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片2',
  `picture3` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='网站介绍';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemintro`
--

LOCK TABLES `systemintro` WRITE;
/*!40000 ALTER TABLE `systemintro` DISABLE KEYS */;
INSERT INTO `systemintro` VALUES (1,'2025-05-08 12:32:33','网站介绍','WEBSITE INTRODUCTION','山川湖海，风雨雷电，花鸟虫鱼，这些都是大自然的奇妙造物，也是我们生命中最美好的遇见。在万籁俱寂的雪夜里，深邃>的星空映入眼帘，让我感受到生命的无限广阔与深沉；在烈日炎炎的夏日里，那炽热的阳光照射在身上，让我感受到生命的多彩与活力。','file/systemintroPicture1.jpg','file/systemintroPicture2.jpg','file/systemintroPicture3.jpg');
/*!40000 ALTER TABLE `systemintro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,71,'用户账号1','yonghu','用户','udbq5vbhb4mtq1cjrpsiucwfunnxd7qe','2025-05-08 12:34:48','2025-05-08 13:34:49');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2025-05-08 12:32:34','admin','admin','管理员');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yinshijilu`
--

DROP TABLE IF EXISTS `yinshijilu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yinshijilu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiluriqi` date DEFAULT NULL COMMENT '记录日期',
  `yinshixiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '饮食详情',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='饮食记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yinshijilu`
--

LOCK TABLES `yinshijilu` WRITE;
/*!40000 ALTER TABLE `yinshijilu` DISABLE KEYS */;
INSERT INTO `yinshijilu` VALUES (1,'2025-05-08 12:32:33','2025-05-08','饮食详情1','用户账号1','用户姓名1'),(2,'2025-05-08 12:32:33','2025-05-08','饮食详情2','用户账号2','用户姓名2'),(3,'2025-05-08 12:32:33','2025-05-08','饮食详情3','用户账号3','用户姓名3'),(4,'2025-05-08 12:32:33','2025-05-08','饮食详情4','用户账号4','用户姓名4'),(5,'2025-05-08 12:32:33','2025-05-08','饮食详情5','用户账号5','用户姓名5'),(6,'2025-05-08 12:32:33','2025-05-08','饮食详情6','用户账号6','用户姓名6'),(7,'2025-05-08 12:32:33','2025-05-08','饮食详情7','用户账号7','用户姓名7'),(8,'2025-05-08 12:32:33','2025-05-08','饮食详情8','用户账号8','用户姓名8'),(9,'2025-05-08 12:32:33','2025-05-08','饮食详情9','用户账号9','用户姓名9'),(10,'2025-05-08 12:32:33','2025-05-08','饮食详情10','用户账号10','用户姓名10');
/*!40000 ALTER TABLE `yinshijilu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonghu`
--

DROP TABLE IF EXISTS `yonghu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `yonghumima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `shoujihaoma` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yonghuzhanghao` (`yonghuzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonghu`
--

LOCK TABLES `yonghu` WRITE;
/*!40000 ALTER TABLE `yonghu` DISABLE KEYS */;
INSERT INTO `yonghu` VALUES (71,'2025-05-08 12:32:33','用户账号1','e10adc3949ba59abbe56e057f20f883e','用户姓名1','file/yonghuTouxiang1.jpg','男','19819881111'),(72,'2025-05-08 12:32:33','用户账号2','e10adc3949ba59abbe56e057f20f883e','用户姓名2','file/yonghuTouxiang2.jpg','男','19819881112'),(73,'2025-05-08 12:32:33','用户账号3','e10adc3949ba59abbe56e057f20f883e','用户姓名3','file/yonghuTouxiang3.jpg','男','19819881113'),(74,'2025-05-08 12:32:33','用户账号4','e10adc3949ba59abbe56e057f20f883e','用户姓名4','file/yonghuTouxiang4.jpg','男','19819881114'),(75,'2025-05-08 12:32:33','用户账号5','e10adc3949ba59abbe56e057f20f883e','用户姓名5','file/yonghuTouxiang5.jpg','男','19819881115'),(76,'2025-05-08 12:32:33','用户账号6','e10adc3949ba59abbe56e057f20f883e','用户姓名6','file/yonghuTouxiang6.jpg','男','19819881116'),(77,'2025-05-08 12:32:33','用户账号7','e10adc3949ba59abbe56e057f20f883e','用户姓名7','file/yonghuTouxiang7.jpg','男','19819881117'),(78,'2025-05-08 12:32:33','用户账号8','e10adc3949ba59abbe56e057f20f883e','用户姓名8','file/yonghuTouxiang8.jpg','男','19819881118'),(79,'2025-05-08 12:32:33','用户账号9','e10adc3949ba59abbe56e057f20f883e','用户姓名9','file/yonghuTouxiang9.jpg','男','19819881119'),(80,'2025-05-08 12:32:33','用户账号10','e10adc3949ba59abbe56e057f20f883e','用户姓名10','file/yonghuTouxiang10.jpg','男','198198811110');
/*!40000 ALTER TABLE `yonghu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yongyaoguanli`
--

DROP TABLE IF EXISTS `yongyaoguanli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yongyaoguanli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yaopinmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '药品名称',
  `guige` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格',
  `fuyongjiliang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服用剂量',
  `meirifuyongcishu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '每日服用次数',
  `qishifuyongshijian` date DEFAULT NULL COMMENT '起始服用时间',
  `yujijieshushijian` date DEFAULT NULL COMMENT '预计结束时间',
  `yonghuzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用药管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yongyaoguanli`
--

LOCK TABLES `yongyaoguanli` WRITE;
/*!40000 ALTER TABLE `yongyaoguanli` DISABLE KEYS */;
INSERT INTO `yongyaoguanli` VALUES (1,'2025-05-08 12:32:33','药品名称1','规格1','服用剂量1','每日服用次数1','2025-05-08','2025-05-08','用户账号1','用户姓名1'),(2,'2025-05-08 12:32:33','药品名称2','规格2','服用剂量2','每日服用次数2','2025-05-08','2025-05-08','用户账号2','用户姓名2'),(3,'2025-05-08 12:32:33','药品名称3','规格3','服用剂量3','每日服用次数3','2025-05-08','2025-05-08','用户账号3','用户姓名3'),(4,'2025-05-08 12:32:33','药品名称4','规格4','服用剂量4','每日服用次数4','2025-05-08','2025-05-08','用户账号4','用户姓名4'),(5,'2025-05-08 12:32:33','药品名称5','规格5','服用剂量5','每日服用次数5','2025-05-08','2025-05-08','用户账号5','用户姓名5'),(6,'2025-05-08 12:32:33','药品名称6','规格6','服用剂量6','每日服用次数6','2025-05-08','2025-05-08','用户账号6','用户姓名6'),(7,'2025-05-08 12:32:33','药品名称7','规格7','服用剂量7','每日服用次数7','2025-05-08','2025-05-08','用户账号7','用户姓名7'),(8,'2025-05-08 12:32:33','药品名称8','规格8','服用剂量8','每日服用次数8','2025-05-08','2025-05-08','用户账号8','用户姓名8'),(9,'2025-05-08 12:32:33','药品名称9','规格9','服用剂量9','每日服用次数9','2025-05-08','2025-05-08','用户账号9','用户姓名9'),(10,'2025-05-08 12:32:33','药品名称10','规格10','服用剂量10','每日服用次数10','2025-05-08','2025-05-08','用户账号10','用户姓名10');
/*!40000 ALTER TABLE `yongyaoguanli` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-09  1:01:55
