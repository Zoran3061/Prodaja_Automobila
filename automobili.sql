-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2019 at 10:12 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cs102-projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `automobili`
--

CREATE TABLE `automobili` (
  `id` int(11) NOT NULL,
  `marka` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `godiste` int(11) NOT NULL,
  `kubikaza` int(11) NOT NULL,
  `boja` varchar(20) NOT NULL,
  `cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `automobili`
--

INSERT INTO `automobili` (`id`, `marka`, `model`, `godiste`, `kubikaza`, `boja`, `cena`) VALUES
(14, 'Ford', 'Mustang', 1969, 4000, 'Crvena', 30000),
(15, 'Ford', 'Fusion', 2008, 2000, 'Plava', 5000),
(16, 'Audi', 'A6', 2014, 2200, 'Siva', 20000),
(17, 'Audi', 'TT', 2004, 3000, 'Roze', 6500),
(18, 'Volkswagen', 'Polo', 2016, 1000, 'Siva', 10000),
(19, 'Volkswagen', 'Passat', 2010, 2000, 'Crna', 9999),
(21, 'Fiat', 'Punto', 2006, 1400, 'Crvena', 4000),
(22, 'Fiat', '500', 2010, 1000, 'Bela', 7000),
(23, 'Fiat', 'Bravo', 2004, 1300, 'Plava', 2400),
(24, 'BMW', 'X5', 2008, 2200, 'Crna', 9000),
(25, 'Fiat', 'Punto', 2002, 1248, 'Zelena', 1250);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `automobili`
--
ALTER TABLE `automobili`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `automobili`
--
ALTER TABLE `automobili`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
