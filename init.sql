create database ogrenci;


CREATE TABLE `student` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`surname` VARCHAR(50) NOT NULL,
	`address` VARCHAR(50) NOT NULL,
	`tel` VARCHAR(50) NOT NULL,
	`student_number` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
