CREATE DATABASE inventory_tracker;
USE inventory_tracker;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `restaurantName` varchar(45) NOT NULL DEFAULT 'CSC510',
  `name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `restaurantId` varchar(45) NOT NULL,
  `orderID` varchar(45) NOT NULL,
  `dishID` varchar(45) NOT NULL,
  `dishName` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `orderQuantity` int NOT NULL,
  PRIMARY KEY (`restaurantId`,`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


