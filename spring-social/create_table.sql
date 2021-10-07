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
  `restaurant_id` varchar(45) NOT NULL,
  `order_id` varchar(45) NOT NULL,
  `dish_id` varchar(45) NOT NULL,
  `dish_name` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `order_qty` int NOT NULL,
  PRIMARY KEY (`restaurant_id`,`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `inventory` (
                             `restaurant_name` varchar(255) NOT NULL,
                             `restaurant_id` varchar(255) NOT NULL,
                             `item_id` varchar(255) NOT NULL,
                             `item_name` varchar(255) NOT NULL,
                             `batch_id` varchar(255) NOT NULL,
                             `batch_qty` int NOT NULL,
                             `cost_per_item` double NOT NULL,
                             `date_bought` datetime NOT NULL,
                             `date_expired` datetime NOT NULL,
                             PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE menu (
                      `restaurant_id` varchar(255) NOT NULL,
                      `dish_id` varchar(255) NOT NULL,
                      `dish_name` varchar(255) NOT NULL,
                      `dish_pointer` varchar(255) NOT NULL,
                      `cost_per_dish` double NOT NULL,
                      PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `orders`
(`restaurant_id`, `order_id`, `dish_id`,`dish_name`,`date`,`order_qty`)
VALUES('r100', 'o199', 'd10', 'Pasta', '2021-10-20', 1);

INSERT INTO `orders`
(`restaurant_id`, `order_id`, `dish_id`,`dish_name`,`date`,`order_qty`)
VALUES('r101', 'o200', 'd20', 'Noodles', '2021-10-20', 2);

INSERT INTO `orders`
(`restaurant_id`, `order_id`, `dish_id`,`dish_name`,`date`,`order_qty`)
VALUES('r102', 'o201', 'd21', 'Borrito', '2021-10-21', 4);

INSERT INTO `orders`
(`restaurant_id`, `order_id`, `dish_id`,`dish_name`,`date`,`order_qty`)
VALUES('r101', 'o202', 'd20', 'Noodles', '2021-10-20', 2);

INSERT INTO `orders`
(`restaurant_id`, `order_id`, `dish_id`,`dish_name`,`date`,`order_qty`)
VALUES('r101', 'o203', 'd40', 'Chocolate Ice Cream', '2021-10-21', 10);

