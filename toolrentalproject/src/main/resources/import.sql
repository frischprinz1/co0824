
-- insert into rate (daily_charge,has_holiday_charge,has_weekday_charge,has_weekend_charge,id) values (1.99,false,true,true,"LAD");
-- insert into rate (daily_charge,has_holiday_charge,has_weekday_charge,has_weekend_charge,id) values (1.49,true,true,false,"CHN");
-- insert into rate (daily_charge,has_holiday_charge,has_weekday_charge,has_weekend_charge,id) values (1.99,false,true,false,"JAK");
-- insert into rate (daily_charge,has_holiday_charge,has_weekday_charge,has_weekend_charge,id) values (1.99,false,true,false,"JAKOO");

-- insert into brand (abbreviation, name) values ("S", "Stihl");
-- insert into brand (abbreviation, name) values ("D", "DeWalt");
-- insert into brand (abbreviation, name) values ("W", "Werner");
-- insert into brand (abbreviation, name) values ("R", "Ridgid");



CREATE TABLE `rate` (
  `id` varchar(255) NOT NULL,
  `daily_charge` double DEFAULT NULL,
  `has_holiday_charge` bit(1) DEFAULT NULL,
  `has_weekday_charge` bit(1) DEFAULT NULL,
  `has_weekend_charge` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `brand` (
  `id` int NOT NULL,
  `abbreviation` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrdxh7tq2xs66r485cc8dkxt77` (`name`)
)

CREATE TABLE `tool_type` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `rate_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe72p8puffe58e5q71jq63xjpr` (`rate_id`),
  CONSTRAINT `FK9f21h6xcrmepbehki6vvq58ng` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`id`)
)

CREATE TABLE `tool` (
  `id` int NOT NULL,
  `tool_code` varchar(255) DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `tool_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmwfkm90unvwa3sjl82hnqycl5` (`tool_code`),
  UNIQUE KEY `UKi2xjswjh6oxxlpoev02g2t515` (`brand_id`),
  KEY `FKf4c9j4hfc2cir0tl45q9jgxno` (`tool_type_id`),
  CONSTRAINT `FKe7s49q8gukyu2gge6u2xyk8w8` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `FKf4c9j4hfc2cir0tl45q9jgxno` FOREIGN KEY (`tool_type_id`) REFERENCES `tool_type` (`id`)
)