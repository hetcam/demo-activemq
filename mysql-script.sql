CREATE DATABASE activemq;

CREATE USER 'activemq'@'%' IDENTIFIED BY 'activemq';  

GRANT ALL PRIVILEGES ON *.* TO 'activemq'@'%';

USE activemq;

CREATE TABLE `queues` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` TEXT NOT NULL,
	`state` TEXT NOT NULL,
	`JMSMessageID` TEXT NOT NULL,
	`JMSTimestamp` BIGINT(20) NOT NULL,
	`JMSCorrelationID` TEXT NOT NULL,
	`client_id` TEXT NOT NULL,
	`operation_type` TEXT NOT NULL,
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
COMMENT='Tables for persist queue messages'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;

INSERT INTO `queues` VALUES (1, 'queueone', 'sent', '', 0, '', '', '', '2016-01-21 00:46:11');
INSERT INTO `queues` VALUES (2, 'queueone', 'test', '', 0, '', '', '', '2016-01-21 00:46:11');
INSERT INTO `queues` VALUES (3, 'queueone', 'test', '', 0, '', '', '', '2016-01-21 00:46:11');
INSERT INTO `queues` VALUES (4, 'queueone', 'test', '', 0, '', '', '', '2016-01-21 00:46:11');
INSERT INTO `queues` VALUES (5, 'queueone', 'test', '', 0, '', '', '', '2016-01-21 00:46:11');
INSERT INTO `queues` VALUES (6, 'queueone', 'test', '', 0, '', '', '', '2016-01-21 00:46:11');
INSERT INTO `queues` VALUES (7, 'queueone', 'sent', '', 0, '', '', '', '2016-01-21 00:46:46');
INSERT INTO `queues` VALUES (8, 'queueone', 'sent', '', 0, '', '', '', '2016-01-21 00:56:27');

delete from queues;
