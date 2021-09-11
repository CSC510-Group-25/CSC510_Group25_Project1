CREATE DATABASE inventory_tracker;
USE inventory_tracker;

CREATE TABLE `users3` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `restaurantName` varchar(45) NOT NULL DEFAULT 'CSC510',
  `name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `inventory` (
  `restaurantName` varchar(255) NOT NULL,
  `restuarantID` varchar(45) NOT NULL,
  `itemID` varchar(45) NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `batchID` varchar(45) NOT NULL,
  `batchQty` int NOT NULL,
  `costPerItem` double NOT NULL,
  `dateBought` datetime NOT NULL,
  `dateExpired` datetime NOT NULL,
  PRIMARY KEY (`restuarantID`,`itemID`,`batchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
