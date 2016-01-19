CREATE DATABASE activemq;

CREATE USER 'activemq'@'%' IDENTIFIED BY 'activemq';  

GRANT ALL PRIVILEGES ON *.* TO 'activemq'@'%';

USE activemq;

CREATE TABLE `queue_messages` (
	`id` BIGINT NOT NULL,
	`queue_name` TEXT NOT NULL,
	`message` TEXT NOT NULL,
	`state` TEXT NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='Tables for persist queue messages'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

ALTER TABLE `queue_messages`
	CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST;
	
INSERT INTO `queue_messages` (`id`, `queue_name`, `message`, `state`) VALUES (1, 'queueone', 'success', 'sent');

