CREATE TABLE `user_type` (
	`user_type_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_type` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`created_by` varchar(255) NOT NULL,
	PRIMARY KEY (`user_type_id`)
);

CREATE TABLE `user` (
	`user_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_type_id` INT(11) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`created_by` DATETIME NOT NULL,
	`first_name` varchar(255) NOT NULL,
	`user_address` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`mobile_number` INT(10) NOT NULL,
	`status` INT(2) NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`modified_by` varchar NOT NULL,
	`modified_on` DATETIME NOT NULL,
	`last_name` varchar(255),
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `category` (
	`category_id` INT(11) NOT NULL AUTO_INCREMENT,
	`description` varchar(255) NOT NULL,
	`status` INT(2) NOT NULL,
	`created_by` varchar(4) NOT NULL,
	`created_on` DATETIME NOT NULL,
	PRIMARY KEY (`category_id`)
);

CREATE TABLE `user_category` (
	`user_category_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`category_id` INT(11) NOT NULL,
	`status` INT(2) NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`modified_on` DATETIME NOT NULL,
	`modified_by` varchar(255) NOT NULL,
	PRIMARY KEY (`user_category_id`)
);

CREATE TABLE `user_data_collection` (
	`user_data_collection_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`data_url` varchar(255) NOT NULL,
	`category_id` INT(11) NOT NULL,
	`status` INT(2) NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	PRIMARY KEY (`user_data_collection_id`)
);

CREATE TABLE `booking_history` (
	`booking_history_id` INT(11) NOT NULL AUTO_INCREMENT,
	`booked_user_id` INT(11) NOT NULL,
	`user_type` INT(11) NOT NULL,
	`status` INT(2) NOT NULL,
	`booking_start_date` DATETIME NOT NULL,
	`booking_end_date` DATETIME NOT NULL,
	`user_id` INT(11) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`modified_by` varchar(255) NOT NULL,
	`modified_on` DATETIME NOT NULL,
	`category_id` INT(11) NOT NULL,
	`price` varchar(255) NOT NULL,
	PRIMARY KEY (`booking_history_id`)
);

CREATE TABLE `pricing_details` (
	`pricing_details_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`hourly_rate` INT(11) NOT NULL,
	`daily_rate` INT(11) NOT NULL,
	`status` INT(2) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`modified_on` DATETIME NOT NULL,
	`user_locality_id` INT(11) NOT NULL,
	PRIMARY KEY (`pricing_details_id`)
);

CREATE TABLE `user_locality` (
	`user_locality_id` INT(11) NOT NULL AUTO_INCREMENT,
	`city_id` INT(11) NOT NULL,
	`user_id` INT(11) NOT NULL,
	`status` INT(2) NOT NULL,
	`zip_code` INT NOT NULL,
	`status` INT(2) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`modified_on` DATETIME NOT NULL,
	PRIMARY KEY (`user_locality_id`)
);

CREATE TABLE `state` (
	`state_id` INT(11) NOT NULL AUTO_INCREMENT,
	`state_code` varchar(5) NOT NULL,
	`description` varchar(255) NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	PRIMARY KEY (`state_id`)
);

CREATE TABLE `city` (
	`city_id` INT(11) NOT NULL AUTO_INCREMENT,
	`city_code` varchar(5) NOT NULL,
	`state_id` INT(11) NOT NULL,
	`description` varchar(255) NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	PRIMARY KEY (`city_id`)
);

CREATE TABLE `transaction` (
	`transaction_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`amount` DECIMAL NOT NULL,
	`created_on` DATETIME NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`status` INT(2) NOT NULL,
	PRIMARY KEY (`transaction_id`)
);

CREATE TABLE `pincode` (
	`zip_code` INT(11) NOT NULL AUTO_INCREMENT,
	`city_id` INT(11) NOT NULL,
	`state_id` INT(11) NOT NULL,
	`created_on` DATETIME NOT NULL,
	`created_by` varchar(255) NOT NULL,
	PRIMARY KEY (`zip_code`)
);

CREATE TABLE `review` (
	`review_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`booked_user_id` INT(11) NOT NULL,
	`description` varchar(255) NOT NULL,
	`rating` DECIMAL(2,1) NOT NULL,
	`suggestions` varchar(255) NOT NULL,
	`created_by` varchar(255) NOT NULL,
	`created_on` DATETIME NOT NULL,
	PRIMARY KEY (`review_id`)
);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`user_type_id`) REFERENCES `user_type`(`user_type_id`);

ALTER TABLE `user_category` ADD CONSTRAINT `user_category_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `user_category` ADD CONSTRAINT `user_category_fk1` FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`);

ALTER TABLE `user_data_collection` ADD CONSTRAINT `user_data_collection_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `user_data_collection` ADD CONSTRAINT `user_data_collection_fk1` FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`);

ALTER TABLE `booking_history` ADD CONSTRAINT `booking_history_fk0` FOREIGN KEY (`booked_user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `booking_history` ADD CONSTRAINT `booking_history_fk1` FOREIGN KEY (`user_type`) REFERENCES `user_type`(`user_type_id`);

ALTER TABLE `booking_history` ADD CONSTRAINT `booking_history_fk2` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `booking_history` ADD CONSTRAINT `booking_history_fk3` FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`);

ALTER TABLE `pricing_details` ADD CONSTRAINT `pricing_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `pricing_details` ADD CONSTRAINT `pricing_details_fk1` FOREIGN KEY (`user_locality_id`) REFERENCES `user_locality`(`user_locality_id`);

ALTER TABLE `user_locality` ADD CONSTRAINT `user_locality_fk0` FOREIGN KEY (`city_id`) REFERENCES `city`(`city_id`);

ALTER TABLE `user_locality` ADD CONSTRAINT `user_locality_fk1` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `user_locality` ADD CONSTRAINT `user_locality_fk2` FOREIGN KEY (`zip_code`) REFERENCES `pincode`(`zip_code`);

ALTER TABLE `city` ADD CONSTRAINT `city_fk0` FOREIGN KEY (`state_id`) REFERENCES `state`(`state_id`);

ALTER TABLE `transaction` ADD CONSTRAINT `transaction_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `pincode` ADD CONSTRAINT `pincode_fk0` FOREIGN KEY (`city_id`) REFERENCES `city`(`city_id`);

ALTER TABLE `pincode` ADD CONSTRAINT `pincode_fk1` FOREIGN KEY (`state_id`) REFERENCES `state`(`state_id`);

ALTER TABLE `review` ADD CONSTRAINT `review_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`);

ALTER TABLE `review` ADD CONSTRAINT `review_fk1` FOREIGN KEY (`booked_user_id`) REFERENCES `user`(`user_id`);
