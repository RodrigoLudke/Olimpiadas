-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 14/10/2024 às 02:31
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `olimpiadas`
--
CREATE DATABASE IF NOT EXISTS `olimpiadas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `olimpiadas`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `atleta`
--

CREATE TABLE `atleta` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `idEquipe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `atleta`
--

INSERT INTO `atleta` (`id`, `nome`, `idEquipe`) VALUES
(1, 'Atleta 1', 1),
(2, 'Atleta 2', 2),
(3, 'Atleta 3', 3),
(4, 'Atleta 4', 4),
(5, 'Atleta 5', 5),
(7, 'Atleta_DB', 5),
(38, 'Atleta_1', 6),
(39, 'Atleta_2', 6),
(40, 'Atleta_3', 6),
(41, 'Atleta_4', 6),
(42, 'Atleta_5', 6),
(43, 'Atleta_6', 6),
(44, 'Atleta_1', 7),
(45, 'Atleta_2', 7),
(46, 'Atleta_3', 7),
(47, 'Atleta_4', 7),
(48, 'Atleta_5', 7),
(49, 'Atleta_6', 7),
(50, 'Atleta_1', 8),
(51, 'Atleta_2', 8),
(52, 'Atleta_3', 8),
(53, 'Atleta_4', 8),
(54, 'Atleta_5', 8),
(55, 'Atleta_6', 8),
(56, 'Atleta_1', 9),
(57, 'Atleta_2', 9),
(58, 'Atleta_3', 9),
(59, 'Atleta_4', 9),
(60, 'Atleta_5', 9),
(61, 'Atleta_6', 9);

-- --------------------------------------------------------

--
-- Estrutura para tabela `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `idModalidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `equipe`
--

INSERT INTO `equipe` (`id`, `pais`, `idModalidade`) VALUES
(5, 'Alemanha', 2),
(1, 'Brasil', 1),
(3, 'China', 2),
(9, 'Estado Unidos', 1),
(2, 'Estados Unidos', 2),
(4, 'França', 3),
(6, 'Japão', 1),
(8, 'Rússia', 1),
(7, 'Rússia', 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `medalhas`
--

CREATE TABLE `medalhas` (
  `idModalidade` int(11) NOT NULL,
  `idEquipe` int(11) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `medalhas`
--

INSERT INTO `medalhas` (`idModalidade`, `idEquipe`, `tipo`) VALUES
(1, 1, 'Ouro'),
(2, 2, 'Prata'),
(3, 3, 'Bronze');

-- --------------------------------------------------------

--
-- Estrutura para tabela `modalidade`
--

CREATE TABLE `modalidade` (
  `id` int(11) NOT NULL,
  `modalidade` varchar(100) DEFAULT NULL,
  `numeroAtletas` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `modalidade`
--

INSERT INTO `modalidade` (`id`, `modalidade`, `numeroAtletas`) VALUES
(1, 'Vôlei', 6),
(2, 'Tênis de Mesa', 2),
(3, 'Esgrima', 1),
(4, 'Basquete', 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `placar`
--

CREATE TABLE `placar` (
  `idEquipe1` int(11) NOT NULL,
  `idEquipe2` int(11) NOT NULL,
  `placarEquipe1` int(11) NOT NULL,
  `placarEquipe2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `placar`
--

INSERT INTO `placar` (`idEquipe1`, `idEquipe2`, `placarEquipe1`, `placarEquipe2`) VALUES
(1, 4, 3, 0),
(1, 8, 3, 2),
(3, 4, 4, 1),
(5, 1, 0, 1),
(7, 4, 3, 0);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `atleta`
--
ALTER TABLE `atleta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `atleta_ibfk_1` (`idEquipe`);

--
-- Índices de tabela `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pais` (`pais`,`idModalidade`),
  ADD KEY `idModalidade` (`idModalidade`);

--
-- Índices de tabela `medalhas`
--
ALTER TABLE `medalhas`
  ADD PRIMARY KEY (`idModalidade`,`idEquipe`),
  ADD KEY `idEquipe` (`idEquipe`);

--
-- Índices de tabela `modalidade`
--
ALTER TABLE `modalidade`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `placar`
--
ALTER TABLE `placar`
  ADD PRIMARY KEY (`idEquipe1`,`idEquipe2`),
  ADD KEY `placar_ibfk_2` (`idEquipe2`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `atleta`
--
ALTER TABLE `atleta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de tabela `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `modalidade`
--
ALTER TABLE `modalidade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `atleta`
--
ALTER TABLE `atleta`
  ADD CONSTRAINT `atleta_ibfk_1` FOREIGN KEY (`idEquipe`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para tabelas `equipe`
--
ALTER TABLE `equipe`
  ADD CONSTRAINT `equipe_ibfk_1` FOREIGN KEY (`idModalidade`) REFERENCES `modalidade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para tabelas `medalhas`
--
ALTER TABLE `medalhas`
  ADD CONSTRAINT `medalhas_ibfk_1` FOREIGN KEY (`idModalidade`) REFERENCES `modalidade` (`id`),
  ADD CONSTRAINT `medalhas_ibfk_2` FOREIGN KEY (`idEquipe`) REFERENCES `equipe` (`id`);

--
-- Restrições para tabelas `placar`
--
ALTER TABLE `placar`
  ADD CONSTRAINT `placar_ibfk_1` FOREIGN KEY (`idEquipe1`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `placar_ibfk_2` FOREIGN KEY (`idEquipe2`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
