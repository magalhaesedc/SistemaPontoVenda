-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 23/08/2020 às 16:49
-- Versão do servidor: 5.7.31-0ubuntu0.18.04.1
-- Versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pontovenda`
--
CREATE DATABASE IF NOT EXISTS `pontovenda` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pontovenda`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes`
--

CREATE TABLE `clientes` (
  `codigo_cl` varchar(10) NOT NULL,
  `nome_cl` varchar(30) NOT NULL,
  `sexo_cl` varchar(10) NOT NULL,
  `celular_cl` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `clientes`
--

INSERT INTO `clientes` (`codigo_cl`, `nome_cl`, `sexo_cl`, `celular_cl`) VALUES
('CLI0001', 'PATRICIA', 'FEMININO', '92044898'),
('CLI0002', 'EDSON COSTA', 'MASCULINO', '92044898'),
('CLI0003', 'COSMA MARIA', 'FEMININO', '99999999'),
('CLI0004', 'EDUADO SILVA', 'MASCULINO', '91935599'),
('CLI0005', 'FERNANDES OLIVEIRA', 'MASCULINO', '93523532'),
('CLI0006', 'CRISTINA GOMES', 'FEMININO', '94733287'),
('CLI0007', 'AMANDA COSTA', 'MASCULINO', '56332587'),
('CLI0008', 'SOCORRO LIMA', 'FEMININO', '432689543'),
('CLI0009', 'BRUNA GONÇALVES', 'MASCULINO', '45772457');

-- --------------------------------------------------------

--
-- Estrutura para tabela `deposito`
--

CREATE TABLE `deposito` (
  `id` int(11) NOT NULL,
  `codigo_produto` varchar(10) NOT NULL,
  `codigo_venda` varchar(15) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `deposito`
--

INSERT INTO `deposito` (`id`, `codigo_produto`, `codigo_venda`, `quantidade`) VALUES
(8, 'PRO0003', '00000000000021', 3),
(9, 'PRO0004', '00000000000021', 4),
(10, 'PRO0003', '00000000000022', 2),
(11, 'PRO0003', '00000000000023', 2),
(12, 'PRO0001', '00000000000024', 5),
(13, 'PRO0004', '00000000000025', 5),
(14, 'PRO0003', '00000000000025', 2),
(15, 'PRO0002', '00000000000025', 4),
(16, 'PRO0004', '00000000000026', 2),
(17, 'PRO0003', '00000000000027', 4),
(18, 'PRO0003', '00000000000027', 2),
(19, 'PRO0002', '00000000000027', 6),
(20, 'PRO0004', '00000000000027', 8),
(21, 'PRO0001', '00000000000028', 9),
(22, 'PRO0004', '00000000000028', 7),
(23, 'PRO0003', '00000000000029', 5),
(24, 'PRO0001', '00000000000029', 6),
(25, 'PRO0002', '00000000000030', 5),
(26, 'PRO0003', '00000000000030', 10),
(27, 'PRO0001', '00000000000031', 2),
(28, 'PRO0002', '00000000000031', 5),
(29, 'PRO0002', '00000000000032', 8),
(30, 'PRO0003', '00000000000032', 5),
(31, 'PRO0002', '00000000000033', 4),
(32, 'PRO0002', '00000000000034', 2),
(33, 'PRO0004', '00000000000034', 8),
(34, 'PRO0004', '00000000000035', 7),
(35, 'PRO0001', '00000000000035', 13),
(36, 'PRO0003', '00000000000035', 8),
(37, 'PRO0002', '00000000000036', 10),
(38, 'PRO0001', '00000000000036', 10),
(39, 'PRO0004', '00000000000036', 10),
(40, 'PRO0003', '00000000000036', 10);

-- --------------------------------------------------------

--
-- Estrutura para tabela `parcelas`
--

CREATE TABLE `parcelas` (
  `id` int(11) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `numero` int(11) NOT NULL,
  `data_vencimento` varchar(10) NOT NULL,
  `data_pagamento` varchar(10) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `codigo_venda` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `parcelas`
--

INSERT INTO `parcelas` (`id`, `valor`, `numero`, `data_vencimento`, `data_pagamento`, `status`, `codigo_venda`) VALUES
(1, '24.00', 2, '18/10/2020', NULL, 1, '00000000000031'),
(2, '24.00', 1, '18/09/2020', NULL, 1, '00000000000031'),
(3, '25.00', 4, '18/12/2020', NULL, 0, '00000000000032'),
(4, '25.00', 3, '18/11/2020', NULL, 0, '00000000000032'),
(5, '25.00', 2, '18/10/2020', NULL, 0, '00000000000032'),
(6, '25.00', 1, '18/09/2020', NULL, 0, '00000000000032'),
(7, '7.75', 4, '20/12/2020', NULL, 0, '00000000000034'),
(8, '7.75', 3, '20/11/2020', NULL, 0, '00000000000034'),
(9, '7.75', 2, '20/10/2020', NULL, 0, '00000000000034'),
(10, '7.75', 1, '20/09/2020', NULL, 0, '00000000000034'),
(11, '18.33', 12, '21/08/2021', NULL, 0, '00000000000036'),
(12, '18.33', 11, '21/07/2021', NULL, 0, '00000000000036'),
(13, '18.33', 10, '21/06/2021', NULL, 0, '00000000000036'),
(14, '18.33', 9, '21/05/2021', NULL, 0, '00000000000036'),
(15, '18.33', 8, '21/04/2021', NULL, 0, '00000000000036'),
(16, '18.33', 7, '21/03/2021', NULL, 0, '00000000000036'),
(17, '18.33', 6, '21/02/2021', NULL, 0, '00000000000036'),
(18, '18.33', 5, '21/01/2021', NULL, 0, '00000000000036'),
(19, '18.33', 4, '21/12/2020', NULL, 0, '00000000000036'),
(20, '18.33', 3, '21/11/2020', NULL, 0, '00000000000036'),
(21, '18.33', 2, '21/10/2020', NULL, 0, '00000000000036'),
(22, '18.33', 1, '21/09/2020', NULL, 1, '00000000000036');

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `codigo_pro` varchar(100) NOT NULL,
  `tipo_pro` varchar(100) NOT NULL,
  `nome_pro` varchar(100) NOT NULL,
  `valor_pro` decimal(10,2) NOT NULL,
  `quantidade_pro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`codigo_pro`, `tipo_pro`, `nome_pro`, `valor_pro`, `quantidade_pro`) VALUES
('PRO0001', 'BEBIDAS', 'COCA COLA', '4.00', 990),
('PRO0002', 'CARNES', 'FRANGO CONGELADO', '10.00', 990),
('PRO0003', 'LACTINEOS', 'IOGURTE', '6.00', 990),
('PRO0004', 'VERDURAS', 'ALFACE', '2.00', 990);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `codigo_us` varchar(50) NOT NULL,
  `nome_us` varchar(100) NOT NULL,
  `login_us` varchar(20) NOT NULL,
  `sexo_us` varchar(20) NOT NULL,
  `tipo_us` varchar(30) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`codigo_us`, `nome_us`, `login_us`, `sexo_us`, `tipo_us`, `senha`) VALUES
('USU0001', 'EDSON MAGALHÃES', 'EDSON', 'MASCULINO', 'ADMINISTRADOR', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('USU0002', 'PATRÍCIA', 'PATRICIA', 'FEMININO', 'PADRÃO', '123'),
('USU0003', 'EDUARDO', 'EDUARDO', 'MASCULINO', 'PADRÃO', '123'),
('USU0004', 'COSMA MARIA', 'COSMA', 'FEMININO', 'PADRÃO', '123'),
('USU0005', 'PEDRO', 'PEDRO', 'MASCULINO', 'PADRÃO', '123'),
('USU0006', 'LETÍCIA', 'LETICIA', 'FEMININO', 'ADMINISTRADOR', '123'),
('USU0007', 'MARCOS', 'MARCOS', 'MASCULINO', 'ADMINISTRADOR', '123'),
('USU0008', 'BEATRIZ', 'BEATRIZ', 'FEMININO', 'PADRÃO', '123'),
('USU0009', 'PAULA', 'PAULA', 'FEMININO', 'PADRÃO', '123'),
('USU0010', 'JOÃO', 'JOAO', 'MASCULINO', 'ADMINISTRADOR', '123'),
('USU0011', 'Administrador', 'ROOT', 'MASCULINO', 'ADMINISTRADOR', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2'),
('USU0012', 'TESTE', 'TESTE', 'MASCULINO', 'PADRÃO', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

-- --------------------------------------------------------

--
-- Estrutura para tabela `vendas`
--

CREATE TABLE `vendas` (
  `numero_ven` varchar(30) NOT NULL,
  `total_ven` decimal(10,2) NOT NULL,
  `data_ven` varchar(100) NOT NULL,
  `forma_pagamento_ven` varchar(20) NOT NULL,
  `entrada` decimal(10,2) NOT NULL,
  `cod_cliente_ven` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `vendas`
--

INSERT INTO `vendas` (`numero_ven`, `total_ven`, `data_ven`, `forma_pagamento_ven`, `entrada`, `cod_cliente_ven`) VALUES
('00000000000031', '58.00', '19/08/2020', 'Dinheiro', '10.00', 'CLI0005'),
('00000000000032', '110.00', '19/08/2020', 'Cartão de Crédito', '10.00', 'CLI0009'),
('00000000000033', '40.00', '19/08/2020', 'Cartão de Débito', '40.00', 'CLI0002'),
('00000000000034', '36.00', '20/08/2020', 'Dinheiro', '5.00', 'CLI0002'),
('00000000000035', '114.00', '21/08/2020', 'Cartão de Crédito', '5.00', 'CLI0008'),
('00000000000036', '220.00', '21/08/2020', 'Cartão de Crédito', '0.00', 'CLI0005');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `deposito`
--
ALTER TABLE `deposito`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `parcelas`
--
ALTER TABLE `parcelas`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`codigo_pro`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`codigo_us`);

--
-- Índices de tabela `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`numero_ven`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `deposito`
--
ALTER TABLE `deposito`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de tabela `parcelas`
--
ALTER TABLE `parcelas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
