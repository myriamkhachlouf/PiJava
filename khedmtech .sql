-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 26 avr. 2021 à 23:20
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `khedmtech`
--

-- --------------------------------------------------------

--
-- Structure de la table `candidature`
--

DROP TABLE IF EXISTS `candidature`;
CREATE TABLE IF NOT EXISTS `candidature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `candidat_id` int(11) NOT NULL,
  `offre_id` int(11) NOT NULL,
  `date_candidature` date NOT NULL,
  `pdf` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_E33BD3B88D0EB82` (`candidat_id`),
  KEY `IDX_E33BD3B84CC8505A` (`offre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `candidature`
--

INSERT INTO `candidature` (`id`, `candidat_id`, `offre_id`, `date_candidature`, `pdf`) VALUES
(1, 1, 1, '2021-03-10', 'estrdtfhjghkjn,');

-- --------------------------------------------------------

--
-- Structure de la table `entretien`
--

DROP TABLE IF EXISTS `entretien`;
CREATE TABLE IF NOT EXISTS `entretien` (
  `Id` int(255) NOT NULL AUTO_INCREMENT,
  `Idc` int(255) NOT NULL,
  `Idr` int(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `lieu` varchar(5000) NOT NULL,
  `confirmation` int(11) NOT NULL,
  `etat` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `entretien`
--

INSERT INTO `entretien` (`Id`, `Idc`, `Idr`, `date`, `lieu`, `confirmation`, `etat`) VALUES
(1, 1, 4, '2021-04-30', 'ariana', 0, 1),
(12, 1, 1, '2021-04-30', 'ariana', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `grille`
--

DROP TABLE IF EXISTS `grille`;
CREATE TABLE IF NOT EXISTS `grille` (
  `Idg` int(11) NOT NULL AUTO_INCREMENT,
  `ide` int(11) NOT NULL,
  `commentaire` varchar(250) NOT NULL,
  `etat` varchar(250) NOT NULL,
  PRIMARY KEY (`Idg`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `grille`
--

INSERT INTO `grille` (`Idg`, `ide`, `commentaire`, `etat`) VALUES
(39, 7, 'bon', 'refusé'),
(38, 4, 'li theb', 'oui'),
(32, 1, 'bon', 'acceptee'),
(33, 1, 'acceptee', 'bo'),
(25, 7, 'ghjk', 'non'),
(37, 5, 'exellant', 'acceptee'),
(35, 12, 'azert', 'non'),
(34, 2, 'olala', 'oui');

-- --------------------------------------------------------

--
-- Structure de la table `recruteur`
--

DROP TABLE IF EXISTS `recruteur`;
CREATE TABLE IF NOT EXISTS `recruteur` (
  `Id` int(255) NOT NULL AUTO_INCREMENT,
  `nom` varchar(1000) NOT NULL,
  `prenom` varchar(1000) NOT NULL,
  `domaine` varchar(1000) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `recruteur`
--

INSERT INTO `recruteur` (`Id`, `nom`, `prenom`, `domaine`) VALUES
(23, 'khachlouuuuu', 'Myriam', 'Rh'),
(41, 'olfa', 'niz', 'Rh'),
(28, 'nizar', 'benooo', 'Rh'),
(32, 'gotham', 'ben', 'Rh'),
(35, 'nizar', 'ferchichi', 'code'),
(33, 'Salma', 'o', 'Rh'),
(44, 'iii', 'amira', 'pi'),
(47, ' i', 'ferchichi', 'code'),
(43, 'Chaabane', 'Haroun', 'Technique'),
(42, 'Chaabane', 'Youssef', 'sahbi'),
(45, 'arbi', 'boubou', 'aaa');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `domaine` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `activation_token` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reset_token` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_1483A5E9450FF010` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `email`, `nom`, `telephone`, `adresse`, `domaine`, `roles`, `password`, `activation_token`, `reset_token`, `enabled`, `score`) VALUES
(1, 'myriam.khachlouf@esprit.tn', 'aziz shaba', '7894651', 'nasr', 'fazefzaef', '[\"ROLE_CANDIDAT\"]', '$argon2id$v=19$m=65536,t=4,p=1$RzhLcUIwQ2dhcGhpdFdHWg$ulGiKiwrYCSxIYgtWodplbsXQFxzRfySQXFDuPDUhFo', 'ddf6308f9bf358726a2de25ec7149244', NULL, NULL, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
