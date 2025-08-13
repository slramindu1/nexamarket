mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: nexamarket
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `conatct_number` varchar(10) NOT NULL,
  `Address` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `City` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Main Branch','0776168954','561,Colombo04,Colombo','Colombo'),(2,'Kandy Branch','0776168955','511,Colombo04,Colombo','Kandy'),(3,'Jaffna Branch','0776168956','261,Colombo04,Colombo','Jaffna');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (5,'Anchor'),(3,'Elephant House'),(13,'Hemas'),(6,'Highland'),(4,'Keells'),(9,'Kist'),(10,'Lakspray'),(8,'Maliban'),(16,'Munchee'),(2,'Nestle'),(12,'Pelwatte'),(11,'Prima'),(7,'Raththi'),(15,'Sunlight'),(1,'Unilever'),(14,'Velona');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (11,'Baby Care'),(1,'Beverage'),(2,'Biscuits'),(7,'Canned Food'),(10,'Cleaning Supplies'),(12,'Dairy Products'),(5,'Flour & Bakery'),(13,'Frozen Food'),(8,'Instant Food'),(14,'Oils & Fats'),(9,'Personal Care'),(4,'Rice & Grains'),(3,'Snacks'),(6,'Spices'),(15,'Tea & Coffee');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goodrecievenotes`
--

DROP TABLE IF EXISTS `goodrecievenotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goodrecievenotes` (
  `id` int NOT NULL,
  `supplier_id` int NOT NULL,
  `supplier_contact` varchar(45) NOT NULL,
  `supplier_nic` varchar(45) NOT NULL,
  `supplier_email` varchar(45) NOT NULL,
  `RelesaseDate` date DEFAULT NULL,
  `GrnNumber` varchar(45) DEFAULT NULL,
  `ReferenceNumber` varchar(45) DEFAULT NULL,
  `paymentMethod_id` int NOT NULL,
  `ItemName` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `UnitPrice` varchar(45) DEFAULT NULL,
  `TotalAmount` varchar(45) DEFAULT NULL,
  `ItemDescription` varchar(105) DEFAULT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_GoodRecieveNotes_supplier_idx` (`supplier_id`),
  KEY `fk_GoodRecieveNotes_paymentMethod1_idx` (`paymentMethod_id`),
  KEY `fk_GoodRecieveNotes_category1_idx` (`category_id`),
  CONSTRAINT `fk_GoodRecieveNotes_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_GoodRecieveNotes_paymentMethod1` FOREIGN KEY (`paymentMethod_id`) REFERENCES `paymentmethod` (`id`),
  CONSTRAINT `fk_GoodRecieveNotes_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goodrecievenotes`
--

LOCK TABLES `goodrecievenotes` WRITE;
/*!40000 ALTER TABLE `goodrecievenotes` DISABLE KEYS */;
/*!40000 ALTER TABLE `goodrecievenotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_user_id` int NOT NULL,
  `to_user_id` int NOT NULL,
  `message_text` text NOT NULL,
  `sent_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `from_user_id` (`from_user_id`),
  KEY `to_user_id` (`to_user_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,1,1,'Hi','2025-07-07 23:24:28'),(2,1,1,'HI','2025-07-07 23:45:56');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentmethod`
--

DROP TABLE IF EXISTS `paymentmethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentmethod` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentmethod`
--

LOCK TABLES `paymentmethod` WRITE;
/*!40000 ALTER TABLE `paymentmethod` DISABLE KEYS */;
INSERT INTO `paymentmethod` VALUES (1,'Online Payment'),(2,'Bank Transfer');
/*!40000 ALTER TABLE `paymentmethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'Admin'),(2,'Cashier');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int DEFAULT '0',
  `description` text,
  `brand_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `branch_id` int DEFAULT NULL,
  `maximum_quantity` int DEFAULT '100',
  `MFD` date DEFAULT NULL,
  `EXD` date DEFAULT NULL,
  `Buying_Price` varchar(50) DEFAULT NULL,
  `Selling_Price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `barcode` (`barcode`),
  KEY `brand_id` (`brand_id`),
  KEY `category_id` (`category_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Munchee Marie Biscuits 100g','4791234567890',110.00,50,'Classic Sri Lankan tea biscuit',1,2,1,100,'2025-07-17',NULL,NULL,NULL),(2,'Munchee Lemon Puff 200g','4791234567891',180.00,60,'Lemon flavored cream biscuits',1,2,1,100,NULL,NULL,NULL,NULL),(3,'Anchor Full Cream Milk Powder 400g','4791234567892',2500.00,100,'Rich and creamy milk powder',5,12,1,100,NULL,NULL,NULL,NULL),(4,'Pelwatte Fresh Milk 1L','4791234567893',210.00,200,'Pure fresh cow\'s milk',12,12,1,100,NULL,NULL,NULL,NULL),(5,'Lakspray Natto Yogurt 500g','4791234567894',450.00,150,'Healthy probiotic yogurt',10,12,2,100,NULL,NULL,NULL,NULL),(6,'Elephant House Ice Cream Vanilla 1L','4791234567895',380.00,80,'Classic vanilla ice cream',3,13,2,100,NULL,NULL,NULL,NULL),(7,'Kotmale Curd 450g','4791234567896',120.00,120,'Thick and creamy curd',12,12,3,100,NULL,NULL,NULL,NULL),(8,'Maliban Marie Biscuits 100g','4791234567897',110.00,90,'Light tea-time biscuits',8,2,1,100,NULL,NULL,NULL,NULL),(9,'Maliban Hi-5 Cookies 120g','4791234567898',140.00,75,'Chocolate chip cookies',8,2,2,100,NULL,NULL,NULL,NULL),(10,'Munchee Peanut Biscuits 150g','4791234567899',130.00,60,'Crunchy peanut cookies',16,2,3,100,NULL,NULL,NULL,NULL),(11,'Raththi Cream Crackers 200g','4791234567900',115.00,110,'Creamy savory crackers',7,2,1,100,NULL,NULL,NULL,NULL),(12,'Kist Potato Chips Salt & Vinegar 100g','4791234567901',160.00,85,'Tangy salt & vinegar chips',9,3,2,100,NULL,NULL,NULL,NULL),(13,'Kist Cheese Rings 150g','4791234567902',155.00,95,'Cheesy corn rings',9,3,3,100,NULL,NULL,NULL,NULL),(14,'Prima Cheezels 70g','4791234567903',120.00,130,'Crunchy cheese snack',11,3,1,100,NULL,NULL,NULL,NULL),(15,'Elephant House Black Tea 200g','4791234567904',220.00,140,'Strong Ceylon black tea',3,1,2,100,NULL,NULL,NULL,NULL),(16,'Lipton Yellow Label Tea 100g','4791234567905',180.00,150,'Brisk yellow label tea',1,15,3,100,NULL,NULL,NULL,NULL),(17,'Lakspray Cocoa Powder 200g','4791234567906',340.00,70,'Rich cocoa powder',10,1,1,100,NULL,NULL,NULL,NULL),(18,'Hemas Mixed Fruit Juice 1L','4791234567907',260.00,95,'Refreshing mixed fruit juice',13,1,2,100,NULL,NULL,NULL,NULL),(19,'Highland Spring Water 500ml','4791234567908',80.00,200,'Pure spring water',6,1,3,100,NULL,NULL,NULL,NULL),(20,'Pelwatte Coconut Milk 400ml','4791234567909',145.00,130,'Thick coconut milk',12,7,1,100,NULL,NULL,NULL,NULL),(21,'Lakspray Sardines in Tomato Sauce 155g','4791234567910',185.00,75,'Tinned sardines',10,7,2,100,NULL,NULL,NULL,NULL),(22,'Del Monte Pineapple Slices 567g','4791234567911',450.00,60,'Canned pineapple slices',14,7,3,100,NULL,NULL,NULL,NULL),(23,'Keells Plain Flour 1kg','4791234567912',220.00,180,'All-purpose plain flour',4,5,1,100,NULL,NULL,NULL,NULL),(24,'Keells White Bread 400g','4791234567913',125.00,90,'Soft white sandwich bread',4,5,2,100,NULL,NULL,NULL,NULL),(25,'Velona Wheat Flour 2kg','4791234567914',480.00,70,'High-quality wheat flour',14,5,3,100,NULL,NULL,NULL,NULL),(26,'Keells Frozen Chicken 500g','4791234567915',650.00,100,'IQF chicken pieces',4,13,1,100,NULL,NULL,NULL,NULL),(27,'Keells Frozen Fish Fillet 400g','4791234567916',720.00,80,'Frozen white fish fillet',4,13,2,100,NULL,NULL,NULL,NULL),(28,'Maggi 2-Minute Noodles 75g','4791234567917',40.00,300,'Instant noodles (chicken)',2,8,3,100,NULL,NULL,NULL,NULL),(29,'Heinz Tomato Ketchup 300g','4791234567918',340.00,120,'Classic tomato ketchup',1,8,1,100,NULL,NULL,NULL,NULL),(30,'Lakspray Coconut Oil 500ml','4791234567919',620.00,85,'Pure cold-pressed coconut oil',10,14,2,100,NULL,NULL,NULL,NULL),(31,'Unilever Frytol Cooking Oil 1L','4791234567920',520.00,100,'Refined sunflower oil',1,14,3,100,NULL,NULL,NULL,NULL),(32,'Sunlight Dish Wash Liquid 250ml','4791234567921',145.00,110,'Lemon fresh dish wash',15,10,1,100,NULL,NULL,NULL,NULL),(33,'Sunlight Bar Soap 100g','4791234567922',55.00,200,'Gentle cleansing soap',15,10,2,100,NULL,NULL,NULL,NULL),(34,'Lifebuoy Antibacterial Soap 75g','4791234567923',65.00,180,'Kills germs soap',1,9,3,100,NULL,NULL,NULL,NULL),(35,'Hemas Herbal Face Cream 50g','4791234567924',360.00,80,'Nourishing herbal cream',13,9,1,100,NULL,NULL,NULL,NULL),(36,'Elephant House Samba Rice 5kg','4791234567925',750.00,50,'Premium samba rice',3,4,2,100,NULL,NULL,NULL,NULL),(37,'Raththi Nadu Rice 2kg','4791234567926',300.00,120,'Broken rice for everyday use',7,4,3,100,NULL,NULL,NULL,NULL),(38,'Munchee Black Pepper Powder 50g','4791234567927',150.00,140,'Spicy ground pepper',16,6,1,100,NULL,NULL,NULL,NULL),(39,'Hemas Curry Powder 100g','4791234567928',130.00,160,'Aromatic curry blend',13,6,2,100,NULL,NULL,NULL,NULL),(40,'Elephant House Coffee Powder 200g','4791234567929',520.00,95,'Rich ground coffee',3,15,3,100,NULL,NULL,NULL,NULL),(41,'Nestle Milo 200g','4791234567930',440.00,110,'Chocolate malt drink powder',2,1,1,100,NULL,NULL,NULL,NULL),(42,'Nestle Nido Fortified Milk Powder 500g','4791234567931',1100.00,80,'Fortified milk powder',2,12,2,100,NULL,NULL,NULL,NULL),(43,'Unilever OMO Laundry Powder 1kg','4791234567932',360.00,90,'Powerful stain remover',1,10,3,100,NULL,NULL,NULL,NULL),(44,'Hemas Spark Handwash 250ml','4791234567933',220.00,130,'Gentle handwash liquid',13,9,1,100,NULL,NULL,NULL,NULL),(45,'Keells Garlic Bread 150g','4791234567934',190.00,75,'Frozen garlic bread slices',4,5,2,100,NULL,NULL,NULL,NULL),(46,'Maliban Fudgee Barr 50g','4791234567935',85.00,140,'Chocolate sponge cake bar',8,2,3,100,NULL,NULL,NULL,NULL),(47,'Munchee Riders 100g','4791234567936',120.00,130,'Butter biscuits',16,2,1,100,NULL,NULL,NULL,NULL),(48,'Lakspray Peanut Butter 340g','4791234567937',1130.00,50,'Crunchy peanut butter',10,5,2,100,NULL,NULL,NULL,NULL),(49,'Elephant House Mint Toffee 200g','4791234567938',210.00,70,'Refreshing mint toffees',3,3,3,100,NULL,NULL,NULL,NULL),(50,'Pelwatte Ghee 200g','4791234567939',420.00,65,'Clarified butter (ghee)',12,14,1,100,NULL,NULL,NULL,NULL),(51,'Anchor Butter Salted 200g','4791234567940',480.00,80,'Creamy salted butter',5,12,2,100,NULL,NULL,NULL,NULL),(52,'Nestle KitKat 4-finger 45g','4791234567941',160.00,120,'Chocolate wafer bar',2,3,3,100,NULL,NULL,NULL,NULL),(53,'Prima Puffs Cheese 60g','4791234567942',115.00,150,'Cheesy corn puffs',11,3,1,100,NULL,NULL,NULL,NULL),(54,'Sunlight Lemon Dishwasher Tablets 20pc','4791234567943',890.00,60,'Lemon-scented tablets',15,10,2,100,NULL,NULL,NULL,NULL),(55,'Unilever Vaseline Petroleum Jelly 100g','4791234567944',250.00,100,'Multi-purpose jelly',1,9,3,100,NULL,NULL,NULL,NULL),(56,'Raththi Idli String 200g','4791234567945',150.00,70,'Pre-mixed idli batter',7,8,1,100,NULL,NULL,NULL,NULL),(57,'Keells Mixed Vegetable Curry 300g','4791234567946',230.00,85,'Ready-to-eat curry',4,8,2,100,NULL,NULL,NULL,NULL),(58,'Maliban Treat Cream 150g','4791234567947',125.00,110,'Cream-filled chocolate biscuits',8,2,3,100,NULL,NULL,NULL,NULL),(59,'Munchee Skippers 160g','4791234567948',135.00,90,'Butter sweet biscuits',16,2,1,100,NULL,NULL,NULL,NULL),(60,'Lakspray Evaporated Milk 410g','4791234567949',180.00,140,'Creamy evaporated milk',10,12,2,100,NULL,NULL,NULL,NULL),(61,'Hemas Tomato Ketchup 500g','4791234567950',380.00,75,'Thick tomato ketchup',13,8,3,100,NULL,NULL,NULL,NULL),(62,'Elephant House Dry Fish Sambal 200g','4791234567951',320.00,55,'Spicy dry fish condiment',3,6,1,100,NULL,NULL,NULL,NULL),(63,'Nestle Yogurt Strawberry 150g','4791234567952',160.00,80,'Fruit-flavored yogurt',2,12,2,100,NULL,NULL,NULL,NULL),(64,'Unilever Sunsilk Shampoo 340ml','4791234567953',350.00,120,'Nourishing hair shampoo',1,9,3,100,NULL,NULL,NULL,NULL),(65,'Keells Mixed Dal 500g','4791234567954',280.00,100,'Protein-rich lentil mix',4,4,1,100,NULL,NULL,NULL,NULL),(66,'Pelwatte UHT Milk 1L','4791234567955',220.00,150,'Long-life UHT milk',12,12,2,100,NULL,NULL,NULL,NULL),(67,'Hemas Ice Cream Chocolate 1L','4791234567956',380.00,90,'Premium chocolate ice cream',13,13,3,100,NULL,NULL,NULL,NULL),(68,'Anchor Cheddar Cheese 200g','4791234567957',1130.00,60,'Mature cheddar cheese',5,12,1,100,NULL,NULL,NULL,NULL),(69,'Raththi Papadam 100g','4791234567958',90.00,140,'Crispy rice papadams',7,8,2,100,NULL,NULL,NULL,NULL),(70,'Maliban Melting Moments 120g','4791234567959',160.00,85,'Buttery sandwich biscuits',8,2,3,100,NULL,NULL,NULL,NULL),(71,'Munchee Nice Time 150g','4791234567960',140.00,95,'Chocolate layered biscuits',16,3,1,100,NULL,NULL,NULL,NULL),(72,'Lakspray Evaporated Milk 170g','4791234567961',100.00,110,'Small tin evaporated milk',10,12,2,100,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `profit` decimal(10,2) NOT NULL,
  `sale_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,1,5,550.00,55.00,'2025-07-01 09:15:22'),(2,3,2,5000.00,500.00,'2025-07-01 10:30:45'),(3,8,3,330.00,33.00,'2025-07-01 11:45:10'),(4,23,1,220.00,22.00,'2025-07-01 14:20:33'),(5,29,2,680.00,68.00,'2025-07-01 16:55:18'),(6,5,4,1800.00,180.00,'2025-07-02 08:30:12'),(7,9,5,700.00,70.00,'2025-07-02 10:15:27'),(8,12,3,480.00,48.00,'2025-07-02 12:40:55'),(9,18,2,520.00,52.00,'2025-07-02 15:25:40'),(10,30,1,620.00,62.00,'2025-07-02 17:10:05'),(11,7,6,720.00,72.00,'2025-07-03 09:45:33'),(12,10,4,520.00,52.00,'2025-07-03 11:30:18'),(13,13,5,775.00,77.50,'2025-07-03 13:15:42'),(14,16,3,540.00,54.00,'2025-07-03 15:50:27'),(15,20,2,290.00,29.00,'2025-07-03 18:25:10'),(16,41,2,880.00,88.00,'2025-07-14 10:30:15'),(17,52,5,800.00,80.00,'2025-07-14 12:45:30'),(18,64,3,1050.00,105.00,'2025-07-14 15:20:45'),(19,68,1,1130.00,113.00,'2025-07-14 17:35:20'),(20,71,4,560.00,56.00,'2025-07-14 19:50:05');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Active'),(2,'Deactive');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `dob` date NOT NULL,
  `Nic` varchar(45) NOT NULL,
  `SupplyingCateogry_id` int NOT NULL,
  `status_id` int NOT NULL,
  `PaymentMethod_Id` int NOT NULL,
  `PaymentMethod_Method` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Supplier_SupplyingCateogry1_idx` (`SupplyingCateogry_id`),
  KEY `fk_Supplier_status1_idx` (`status_id`),
  KEY `fk_supplier_PaymentMethod1_idx` (`PaymentMethod_Id`,`PaymentMethod_Method`),
  CONSTRAINT `fk_supplier_PaymentMethod1` FOREIGN KEY (`PaymentMethod_Id`, `PaymentMethod_Method`) REFERENCES `mydb`.`paymentmethod` (`Id`, `Method`),
  CONSTRAINT `fk_Supplier_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_Supplier_SupplyingCateogry1` FOREIGN KEY (`SupplyingCateogry_id`) REFERENCES `supplyingcateogry` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (96,'supplier1@example.com','0771000001','Nimal','Perera','1990-01-01','902341234V',1,1,0,''),(97,'supplier2@example.com','0771000002','Sunil','Fernando','1985-05-11','852347812V',2,1,0,''),(98,'supplier3@example.com','0771000003','Kamal','Bandara','1992-03-15','921234981V',3,1,0,''),(99,'supplier4@example.com','0771000004','Amal','Jayasinghe','1993-04-17','932489174V',4,1,0,''),(100,'supplier5@example.com','0771000005','Ruwan','Senanayake','1988-12-05','882398479V',5,1,0,''),(101,'supplier6@example.com','0771000006','Chandana','Herath','1991-07-22','911238471V',6,1,0,''),(102,'supplier7@example.com','0771000007','Asela','Wijesinghe','1994-02-10','941232174V',7,1,0,''),(103,'supplier8@example.com','0771000008','Nadeesha','Lakmali','1995-06-19','952139478V',8,1,0,''),(104,'supplier9@example.com','0771000009','Tharindu','Dias','1990-09-09','902149875V',9,1,0,''),(105,'supplier10@example.com','0771000010','Isuru','Ranasinghe','1987-08-30','872319857V',10,1,0,''),(106,'supplier11@example.com','0771000011','Dinusha','Wijeratne','1989-01-23','891237589V',11,1,0,''),(107,'supplier12@example.com','0771000012','Suresh','Abeykoon','1993-02-14','931248127V',12,1,0,''),(108,'supplier13@example.com','0771000013','Pradeep','Silva','1991-10-28','911239125V',13,1,0,''),(109,'supplier14@example.com','0771000014','Lahiru','Madushanka','1994-04-09','942311478V',14,1,0,''),(110,'supplier15@example.com','0771000015','Sachini','Kumari','1992-11-11','922143871V',15,1,0,''),(111,'supplier16@example.com','0771000016','Anura','Gunasekara','1986-07-06','861245189V',16,1,0,''),(112,'supplier17@example.com','0771000017','Dulani','Rathnayake','1990-12-22','901247189V',17,1,0,''),(113,'supplier18@example.com','0771000018','Kasun','Rajapaksha','1995-03-03','951234571V',18,1,0,''),(114,'supplier19@example.com','0771000019','Indika','Samarakoon','1993-05-27','931258741V',19,1,0,''),(115,'supplier20@example.com','0771000020','Thilina','Abeywardena','1996-09-13','961247512V',20,1,0,''),(116,'supplier21@example.com','0771000021','Sameera','Jayalath','1991-01-20','911237845V',1,1,0,''),(117,'supplier22@example.com','0771000022','Kavindi','Perera','1992-02-15','921237895V',2,1,0,''),(118,'supplier23@example.com','0771000023','Nuwan','Rathnayake','1990-05-01','902147891V',3,1,0,''),(119,'supplier24@example.com','0771000024','Chamari','Athukorala','1994-08-12','941248951V',4,1,0,''),(120,'supplier25@example.com','0771000025','Bimal','Silva','1989-10-10','891239471V',5,1,0,''),(121,'supplier26@example.com','0771000026','Shehani','Fonseka','1993-03-16','931249875V',6,1,0,''),(122,'supplier27@example.com','0771000027','Ramesh','Dissanayake','1988-06-18','881247191V',7,1,0,''),(123,'supplier28@example.com','0771000028','Pasindu','Weerasinghe','1991-04-04','911234581V',8,1,0,''),(124,'supplier29@example.com','0771000029','Janitha','Karunaratne','1995-01-30','951248137V',9,1,0,''),(125,'supplier30@example.com','0771000030','Chamara','Nawaratne','1990-07-19','902314789V',10,1,0,''),(126,'supplier31@example.com','0771000031','Sanduni','Hettiarachchi','1992-09-25','922157814V',11,1,0,''),(127,'supplier32@example.com','0771000032','Hiran','Abeywickrama','1994-11-17','942148719V',12,1,0,''),(128,'supplier33@example.com','0771000033','Tharanga','Jayawardena','1987-02-22','872149875V',13,1,0,''),(129,'supplier34@example.com','0771000034','Dineth','Kariyawasam','1986-03-03','862314719V',14,1,0,''),(130,'supplier35@example.com','0771000035','Nimesha','Thushari','1995-05-15','952148951V',15,1,0,''),(131,'supplier36@example.com','0771000036','Roshan','Gunathilaka','1989-06-06','892148951V',16,1,0,''),(132,'supplier37@example.com','0771000037','Upeksha','Senarathne','1993-07-07','932148951V',17,1,0,''),(133,'supplier38@example.com','0771000038','Malith','Alwis','1991-08-08','912148951V',18,1,0,''),(134,'supplier39@example.com','0771000039','Chamika','Perera','1992-09-09','922148951V',19,1,0,''),(135,'supplier40@example.com','0771000040','Sahan','Fernando','1988-10-10','882148951V',20,1,0,'');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplyingcateogry`
--

DROP TABLE IF EXISTS `supplyingcateogry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplyingcateogry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplyingcateogry`
--

LOCK TABLES `supplyingcateogry` WRITE;
/*!40000 ALTER TABLE `supplyingcateogry` DISABLE KEYS */;
INSERT INTO `supplyingcateogry` VALUES (1,'Food'),(2,'Vegetables'),(3,'Fruits'),(4,'Dairy Products'),(5,'Meat'),(6,'Seafood'),(7,'Bakery Items'),(8,'Beverages'),(9,'Snacks'),(10,'Frozen Foods'),(11,'Spices'),(12,'Cleaning Supplies'),(13,'Stationery'),(14,'Electronics'),(15,'Plastic Goods'),(16,'Grains'),(17,'Canned Foods'),(18,'Household Items'),(19,'Health Products'),(20,'Beauty Products'),(21,'Toys');
/*!40000 ALTER TABLE `supplyingcateogry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `Position_id` int NOT NULL,
  `Status_id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `profile_image` longblob,
  PRIMARY KEY (`id`),
  KEY `fk_user_Position_idx` (`Position_id`),
  KEY `fk_user_Status1_idx` (`Status_id`),
  CONSTRAINT `fk_user_Position` FOREIGN KEY (`Position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `fk_user_Status1` FOREIGN KEY (`Status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ramindu.jiat@gmail.com','0776168954','*0776*Ramindu',1,1,'Ramindu2008','Ramindu','Ravihansa','2025-07-08',_binary 'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0[6\≈¯\0\0\0sRGB\0Æ\Œ\È\0\0\0gAMA\0\0±è¸a\0\0\0	pHYs\0\0\√\0\0\√\«o®d\0\0\0IDATWc\‰\„\„˚œÄò\–p	\0E\√/\Z\"í\œ\0\0\0\0IENDÆB`Ç'),(2,'Kamal@gmail.com','0776268954','*0776*Ramindu',2,1,'Kamal','Kamal','Weerasuriya','2025-07-08',NULL),(3,'Ravindu@gmail.com','0776167954','*0776*Ramindu',2,1,'Ravindu','Ravindu','Dinupa','2025-07-08',NULL),(58,'cashier01@example.com','0711111111','pass123',2,1,'cashier01','Nimal','Perera','1995-01-01',NULL),(59,'cashier02@example.com','0711111112','pass123',2,2,'cashier02','Sunil','Fernando','1994-02-10',NULL),(60,'cashier03@example.com','0711111113','pass123',2,1,'cashier03','Kamal','Silva','1993-03-15',NULL),(61,'cashier04@example.com','0711111114','pass123',2,1,'cashier04','Chamara','Rathnayake','1990-04-22',NULL),(62,'cashier05@example.com','0711111115','pass123',2,1,'cashier05','Pradeep','Wijesinghe','1989-05-30',NULL),(63,'cashier06@example.com','0711111116','pass123',2,1,'cashier06','Tharindu','Jayasinghe','1992-06-25',NULL),(64,'cashier07@example.com','0711111117','pass123',2,1,'cashier07','Ashan','Ekanayake','1991-07-11',NULL),(65,'cashier08@example.com','0711111118','pass123',2,1,'cashier08','Nuwan','Samarasinghe','1990-08-18',NULL),(66,'cashier09@example.com','0711111119','pass123',2,1,'cashier09','Ruwan','Dissanayake','1988-09-09',NULL),(67,'cashier10@example.com','0711111120','pass123',2,1,'cashier10','Sampath','Abeysekera','1987-10-14',NULL),(68,'cashier11@example.com','0711111121','pass123',2,1,'cashier11','Ramesh','Hettiarachchi','1996-11-20',NULL),(69,'cashier12@example.com','0711111122','pass123',2,1,'cashier12','Malith','Gunawardena','1997-12-12',NULL),(70,'cashier13@example.com','0711111123','pass123',2,1,'cashier13','Dilshan','Karunaratne','1995-05-05',NULL),(71,'cashier14@example.com','0711111124','pass123',2,1,'cashier14','Ravindu','Senanayake','1998-06-06',NULL),(72,'cashier15@example.com','0711111125','pass123',2,1,'cashier15','Isuru','Ranasinghe','1994-07-07',NULL),(73,'cashier16@example.com','0711111126','pass123',2,1,'cashier16','Gayan','Wickramasinghe','1991-08-08',NULL),(74,'cashier17@example.com','0711111127','pass123',2,1,'cashier17','Harsha','Weerakoon','1990-09-09',NULL),(75,'cashier18@example.com','0711111128','pass123',2,1,'cashier18','Roshan','Dias','1993-10-10',NULL),(76,'cashier19@example.com','0711111129','pass123',2,1,'cashier19','Kavindu','Liyanage','1992-11-11',NULL),(77,'cashier20@example.com','0711111130','pass123',2,1,'cashier20','Sasindu','Bandara','1996-12-12',NULL),(78,'cashier21@example.com','0711111131','pass123',2,1,'cashier21','Nadeesha','Perera','1995-01-13',NULL),(79,'cashier22@example.com','0711111132','pass123',2,1,'cashier22','Sameera','Ruwan','1994-02-14',NULL),(80,'cashier23@example.com','0711111133','pass123',2,1,'cashier23','Chamika','Lakmal','1993-03-15',NULL),(81,'cashier24@example.com','0711111134','pass123',2,1,'cashier24','Pasindu','Mendis','1992-04-16',NULL),(82,'cashier25@example.com','0711111135','pass123',2,1,'cashier25','Janith','Gunathilake','1991-05-17',NULL),(83,'cashier26@example.com','0711111136','pass123',2,1,'cashier26','Yohan','Peiris','1990-06-18',NULL),(84,'cashier27@example.com','0711111137','pass123',2,1,'cashier27','Lahiru','De Silva','1995-07-19',NULL),(85,'cashier28@example.com','0711111138','pass123',2,1,'cashier28','Kasun','Weerasinghe','1996-08-20',NULL),(86,'cashier29@example.com','0711111139','pass123',2,1,'cashier29','Sahan','Fonseka','1997-09-21',NULL),(87,'cashier30@example.com','0711111140','pass123',2,1,'cashier30','Ravishan','Hettiarachchi','1994-10-22',NULL),(88,'cashier31@example.com','0711111141','pass123',2,1,'cashier31','Nimendra','Samarakoon','1993-11-23',NULL),(89,'cashier32@example.com','0711111142','pass123',2,1,'cashier32','Avishka','Thilakarathne','1992-12-24',NULL),(90,'cashier33@example.com','0711111143','pass123',2,1,'cashier33','Manula','Jayawardena','1991-01-25',NULL),(91,'cashier34@example.com','0711111144','pass123',2,1,'cashier34','Rashmika','Kodikara','1990-02-26',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-27 22:21:38
