CREATE SCHEMA `usterapp` ;

USE usterapp;

DROP TABLE IF EXISTS `Vehicles`;

CREATE TABLE `Vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `plate` varchar(10) NOT NULL,
  `licenseRequired` enum('A', 'B', 'C', 'D', 'E') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Drivers`;

CREATE TABLE `Drivers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `license` enum('A', 'B', 'C', 'D', 'E') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Trips`;

CREATE TABLE `Trips` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idVehicle` int(11) NOT NULL,
  `idDriver` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Trips_Vehicles_idx` (`idVehicle`),
  CONSTRAINT `fk_Trips_Vehicles` FOREIGN KEY (`idVehicle`) REFERENCES `Vehicles` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  KEY `fk_Trips_Drivers_idx` (`idDriver`),
  CONSTRAINT `fk_Trips_Drivers` FOREIGN KEY (`idDriver`) REFERENCES `Drivers` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO vehicles VALUES ( 1, 'BMW', 'Serie 1', '4584NAH', 'A' );
INSERT INTO vehicles VALUES ( 2, 'Ford', 'B-MAX', '9416BDK', 'A' );
INSERT INTO vehicles VALUES ( 3, 'Hyundai', 'QX50', '8885DBD', 'A' );
INSERT INTO vehicles VALUES ( 4, 'Honda', 'Accord', '2653BDK', 'B' );
INSERT INTO vehicles VALUES ( 5, 'Isuzu', 'D-Max', '7610JBB', 'C' );
INSERT INTO vehicles VALUES ( 6, 'Jaguar', 'F-Type', '9256BBD', 'C' );
INSERT INTO vehicles VALUES ( 7, 'Jeep', 'Compass', '5088BLF', 'D' );
INSERT INTO vehicles VALUES ( 8, 'KIA', 'Carens', '1234BCD', 'D' );
INSERT INTO vehicles VALUES ( 9, 'Lada', '4x4', '2687JYB', 'E' );
INSERT INTO vehicles VALUES ( 10, 'Land Rover', 'Defender', '4824HYL', 'E' );

INSERT INTO drivers VALUES ( 1, 'Antonio', 'Garcia', 'A' );
INSERT INTO drivers VALUES ( 2, 'Jose', 'Martinez', 'A' );
INSERT INTO drivers VALUES ( 3, 'Francisco', 'Lopez', 'B' );
INSERT INTO drivers VALUES ( 4, 'Maria', 'Sanchez', 'B' );
INSERT INTO drivers VALUES ( 5, 'Carmen', 'Moreno', 'C' );
INSERT INTO drivers VALUES ( 6, 'Francisca', 'Perez', 'C' );
INSERT INTO drivers VALUES ( 7, 'Carlos', 'Ruiz', 'D' );
INSERT INTO drivers VALUES ( 8, 'Alejandro', 'Hernandez', 'D' );
INSERT INTO drivers VALUES ( 9, 'Laura', 'Alfaro', 'E' );
INSERT INTO drivers VALUES ( 10, 'Cristina', 'Romero', 'E' );