USE `testdatabase`;


CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userNameEntry` varchar(45) DEFAULT NULL,
  `friendName` varchar(45) DEFAULT NULL,



  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
