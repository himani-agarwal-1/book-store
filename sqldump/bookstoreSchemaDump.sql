-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `isbn` varchar(50) NOT NULL,
  `author` varchar(255) NOT NULL,
  `binding_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `published_by` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('4028818a70c957270170c957575a0000','gonzalo pombo','paperback','quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto','Latin',720,'publisher diurna','sunt aut facere repellat provident occaecati excepturi optio reprehenderit',1),('4028818a70c957270170c9582ed80001','gonzalo pombo','paperback','quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto','Latin',720,'publisher diurna','frsunt aut facere repellat provident occaecati excepturi optio reprehenderit',1),('4028818a70c957270170c95934dc0002','gonzalo pombo','paperback','quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto','Latin',720,'publisher diurna','accsunt aut facere repellat provident occaecati excepturi optio reprehenderit',1),('4028818a70c957270170c95c27b50003','héctor garcía','hardcover','bring meaning and joy to all your days with this internationally bestselling guide to the Japanese concept of ikigai (pronounced ee-key-guy)—the happiness of always being busy—as revealed by the daily habits of the world’s longest-living people.','english',298,'hutchinson/penguin life/gaia','ikigai: the japanese secret to a long and happy life',1),('4028818a70c957270170c95d3f470004','gonzalo arango','paperback','est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla','latin',680,'publisher diurna','qui est esse',1),('4028818a70c957270170c961cebf0006','gonzalo arango','paperback','est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla','latin',680,'publisher diurna','qui est esseei',1),('4028818a70cc7a780170cc7b37b20000','gonzalon creze','hardcover','dolore placeat quibusdam ea quo vitae\nmagni quis enim qui quis quo nemo aut saepe\nquidem repellat excepturi ut quia\nsunt ut sequi eos ea sed quas','latin',280,'publisher penguin','magnam facilis autem',1),('4028818a70cc7a780170cc7e55160001','James Canon','hardcover','dignissimos aperiam dolorem qui eum\nfacilis quibusdam animi sint suscipit qui sint possimus cum\nquaerat magni maiores excepturi\nipsam ut commodi dolor voluptatum modi aut vitae','latin',390,'publisher penguin','magnam dolorem dolore est ipsam',1),('4028818a70cc7a780170cc832b760002','Tomás Carrasquilla','paperback','qui consequuntur ducimus possimus quisquam amet similique\nsuscipit porro ipsam amet\neos veritatis officiis exercitationem vel fugit aut necessitatibus totam\nomnis rerum consequatur expedita quidem cumque explicabo ','latin',476,'Editis','doloribus ad provident suscipit at',1),('4028818a70cc7a780170cc8401db0003','Tomás Carrasquilla','hardcover','repellat aliquid praesentium dolorem quo\nsed totam minus non itaque\nnihil labore molestiae sunt dolor eveniet hic recusandae veniam\ntempora et tenetur expedita sunt','latin',676,'Editis','asperiores ea ipsam voluptatibus modi minima quia sint',1),('4028818a70cc7a780170cc87c9d70004','james canon','hardcover','eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse','latin',760,'Editis','dolor sint quo a velit explicabo quia nam',1),('4028818a70cc7a780170cc88144f0005','james canon','hardcover','eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse','latin',760,'Editis','dolor sint quo a velit explicabo quia nam qui',1),('4028818a70cc7a780170cc8a18f10006','james canon','hardcover','eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse','latin',760,'editis','dolor sint quo a velit explicabo quia nam sint',1),('4028818a70cc7a780170cc8be5160007','james canon','paperback','cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut','latin',880,'editis','at nam consequatur ea labore ea harum sint',1),('4028818a70cc7a780170cc8d502a0008','james canon','paperback','quo deleniti praesentium dicta non quod\naut est molestias\nmolestias et officia quis nihil\nitaque dolorem quia','latin',960,'editis','temporibus sit alias delectus eligendi possimus magni sint',1),('4028818a70cc7a780170cc8eb26d0009','james canon','paperback','doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',600,'editis','laboriosam dolor voluptates sint',1),('4028818a70cc7a780170cc8f4b1e000a','Germán Castro Caycedo','paperback','doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',600,'editis','laboriosam dolor voluptates',1),('4028818a70cc7a780170cc8fd20d000b','Germán Castro Caycedo','paperback','doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',600,'editis','quas fugiat ut perspiciatis vero provident',1),('4028818a70cc7a780170cc903098000c','Germán Castro Caycedo','paperback','quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',600,'editis','quas fugiat ut perspiciatis vero provident sint',1),('4028818a70cc7a780170cc91c5df000d','Germán Castro Caycedo','paperback','quaerat velit veniam amet cupiditate aut numquam ut sequi quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',710,'editis','quaerat velit veniam amet cupiditate aut numquam ut sequi',1),('4028818a70cc7a780170cc9b46f30012','linda rode','paperback','linda rode was born in ladismith, western cape. She studied at stellenbosch University, where she obtained an Honours degree in German and a Teacher\'s Diploma. She taught school in Calvinia','latin',710,'editis','Up the Down Escalator',1),('4028818a70cc7a780170cc9ce1020013','Arja Salafranca','paperback','born in Spain to a Spanish father and a South African mother. She has lived in South Africa since the age of five. In 1993 she earned a degree in African Literature and Psychology from the University of the Witwatersrand.','latin',800,'editis','Glass Jars among Trees',1),('4028818a70ce5ac00170ce6475b80001','gonzalo arango','paperback','est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla','latin',680,'publisher diurna','qui est essfebrye',1),('4028818a70cf03d80170cf18fb080000','Germán Castro Caycedo','paperback','quaerat velit veniam amet cupiditate aut numquam ut sequi quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',710,'editis','quaerat velit veniam amet cupiditate aut numquam ut seddqui',1),('4028818a70e2f7aa0170e2f9f0370000','testing','testing','testing','testing',100,'testing','testing',1),('4028818a70e2f7aa0170e3557eba0001','gonzalo pombo','paperback','quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto','Latin',720,'publisher diurna','scstestfrsunt aut facere repellat provident occaecati excepturi optio reprehenderit',1),('4028818a70e2f7aa0170e356cb850002','gonzalo pombo','paperback','quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto','Latin',720,'publisher diurna','ssscstestfrsunt aut facere repellat provident occaecati excepturi optio reprehenderit',1),('4028818a70e68e7c0170e79deae20000','A Germán Castro Caycedo','paperback','tequaerat velit veniam amet cupiditate aut numquam ut sequi quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',710,'editis','glowheeaquaerat velit veniam amet aut numquam ut seddqui',2),('4028818a70e68e7c0170e79fd7f30001','Germán Castro Caycedo','paperback','quaerat velit veniam amet cupiditate aut numquam ut sequi quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',710,'editis','saquaerat velit veniam amet aut numquam ut seddqui',0),('4028818a70e68e7c0170e7a0a1500002','Germán Castro Caycedo','paperback','quaerat velit veniam amet cupiditate aut numquam ut sequi quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',710,'editis','tsaquaerat velit veniam amet aut numquam ut seddqui',0),('8a7f781f70d39df90170d39ff0900000','Germán Castro Caycedo','paperback','quaerat velit veniam amet cupiditate aut numquam ut sequi quas fugiat ut perspiciatis vero provident doloremque ex facilis sit sint culpa\nsoluta assumenda eligendi non ut eius\nsequi ducimus vel quasi\nveritatis est dolores','latin',710,'editis','quaerat velit veniam amet aut numquam ut seddqui',1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_quantity`
--

DROP TABLE IF EXISTS `book_quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_quantity` (
  `isbn` varchar(50) NOT NULL,
  `quantity` int NOT NULL,
  `version` int DEFAULT '1',
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_quantity`
--

LOCK TABLES `book_quantity` WRITE;
/*!40000 ALTER TABLE `book_quantity` DISABLE KEYS */;
INSERT INTO `book_quantity` VALUES ('4028818a70c957270170c957575a0000',1,1),('4028818a70c957270170c9582ed80001',5,1),('4028818a70c957270170c95934dc0002',5,1),('4028818a70c957270170c95c27b50003',3,1),('4028818a70c957270170c95d3f470004',0,1),('4028818a70c957270170c961cebf0006',6,1),('4028818a70cc7a780170cc7b37b20000',10,1),('4028818a70cc7a780170cc7e55160001',8,1),('4028818a70cc7a780170cc832b760002',7,1),('4028818a70cc7a780170cc8401db0003',8,1),('4028818a70cc7a780170cc87c9d70004',5,1),('4028818a70cc7a780170cc88144f0005',5,1),('4028818a70cc7a780170cc8a18f10006',5,1),('4028818a70cc7a780170cc8be5160007',10,1),('4028818a70cc7a780170cc8d502a0008',10,1),('4028818a70cc7a780170cc8eb26d0009',5,1),('4028818a70cc7a780170cc8f4b1e000a',5,1),('4028818a70cc7a780170cc8fd20d000b',5,1),('4028818a70cc7a780170cc903098000c',5,1),('4028818a70cc7a780170cc91c5df000d',5,1),('4028818a70cc7a780170cc9b46f30012',5,1),('4028818a70cc7a780170cc9ce1020013',5,1),('4028818a70ce5ac00170ce6475b80001',0,1),('4028818a70cf03d80170cf18fb080000',4,1),('4028818a70e2f7aa0170e2f9f0370000',1,1),('4028818a70e2f7aa0170e3557eba0001',1,1),('4028818a70e2f7aa0170e356cb850002',1,1),('4028818a70e68e7c0170e79deae20000',11,2),('4028818a70e68e7c0170e79fd7f30001',4,0),('4028818a70e68e7c0170e7a0a1500002',4,0),('8a7f781f70d39df90170d39ff0900000',4,1);
/*!40000 ALTER TABLE `book_quantity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bookstore'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-17 14:08:08
