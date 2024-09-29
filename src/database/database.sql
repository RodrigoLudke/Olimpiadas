-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 30/09/2024 às 00:39
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
(5, 'Atleta 5', 5);

-- --------------------------------------------------------

--
-- Estrutura para tabela `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `pais` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `equipe`
--

INSERT INTO `equipe` (`id`, `pais`) VALUES
(1, 'Brasil'),
(2, 'Estados Unidos'),
(3, 'China'),
(4, 'França'),
(5, 'Alemanha');

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
  `coletivo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `modalidade`
--

INSERT INTO `modalidade` (`id`, `modalidade`, `coletivo`) VALUES
(1, 'Vôlei', 1),
(2, 'Tênis de Mesa', 0),
(3, 'Esgrima', 0);

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
(1, 2, 3, 2),
(3, 4, 4, 1),
(5, 1, 0, 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `atleta`
--
ALTER TABLE `atleta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idEquipe` (`idEquipe`);

--
-- Índices de tabela `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `idEquipe2` (`idEquipe2`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `atleta`
--
ALTER TABLE `atleta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `modalidade`
--
ALTER TABLE `modalidade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `atleta`
--
ALTER TABLE `atleta`
  ADD CONSTRAINT `atleta_ibfk_1` FOREIGN KEY (`idEquipe`) REFERENCES `equipe` (`id`);

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
  ADD CONSTRAINT `placar_ibfk_1` FOREIGN KEY (`idEquipe1`) REFERENCES `equipe` (`id`),
  ADD CONSTRAINT `placar_ibfk_2` FOREIGN KEY (`idEquipe2`) REFERENCES `equipe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
