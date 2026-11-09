-- PROJETO FINAL - LOJA DE JOGOS PARA COMPUTADOR
-- SGBD: MySQL 8.0+
-- DDL alinhado ao documento da loja de jogos e ao MER.

DROP DATABASE IF EXISTS loja_jogos;
CREATE DATABASE loja_jogos CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE loja_jogos;

-- RN01/RN03: empresa desenvolvedora do jogo.
CREATE TABLE desenvolvedora (
    id_desenvolvedora INT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    pais VARCHAR(80),
    data_fundacao DATE,
    CONSTRAINT pk_desenvolvedora PRIMARY KEY (id_desenvolvedora),
    CONSTRAINT uq_desenvolvedora_nome UNIQUE (nome)
) ENGINE=InnoDB;

CREATE TABLE loja (
    id_loja INT UNSIGNED AUTO_INCREMENT,
    nome_loja VARCHAR(120) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(200) NOT NULL,
    CONSTRAINT pk_loja PRIMARY KEY (id_loja),
    CONSTRAINT uq_loja_nome UNIQUE (nome_loja)
) ENGINE=InnoDB;

-- Cliente conforme o MER; não há entidade Pessoa no escopo do projeto.
CREATE TABLE cliente (
    id_cliente INT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    cpf CHAR(11) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(160) NOT NULL,
    data_cadastro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
    CONSTRAINT uq_cliente_cpf UNIQUE (cpf),
    CONSTRAINT uq_cliente_email UNIQUE (email),
    CONSTRAINT ck_cliente_nome CHECK (CHAR_LENGTH(TRIM(nome)) >= 3),
    CONSTRAINT ck_cliente_cpf CHECK (cpf REGEXP '^[0-9]{11}$')
) ENGINE=InnoDB;

-- RN12/RN13/RN14: funcionário, loja e autorrelacionamento de supervisão.
CREATE TABLE funcionario (
    id_funcionario INT UNSIGNED AUTO_INCREMENT,
    id_supervisor INT UNSIGNED,
    id_loja INT UNSIGNED NOT NULL,
    nome VARCHAR(120) NOT NULL,
    cpf CHAR(11) NOT NULL,
    telefone VARCHAR(20),
    data_contratacao DATE NOT NULL,

    CONSTRAINT pk_funcionario
        PRIMARY KEY (id_funcionario),

    CONSTRAINT uq_funcionario_cpf
        UNIQUE (cpf),

    CONSTRAINT fk_funcionario_loja
        FOREIGN KEY (id_loja)
        REFERENCES loja(id_loja)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT fk_funcionario_supervisor
        FOREIGN KEY (id_supervisor)
        REFERENCES funcionario(id_funcionario)
        ON DELETE SET NULL
        ON UPDATE CASCADE,

    CONSTRAINT ck_funcionario_nome
        CHECK (CHAR_LENGTH(TRIM(nome)) >= 3),

    CONSTRAINT ck_funcionario_cpf
        CHECK (cpf REGEXP '^[0-9]{11}$')
) ENGINE=InnoDB;

-- RN01/RN03/RN16: catálogo de jogos; cada jogo possui uma desenvolvedora principal.
CREATE TABLE jogo (
    id_jogo INT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(160) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    id_desenvolvedora INT UNSIGNED NOT NULL,
    data_lancamento DATE NOT NULL,
    CONSTRAINT pk_jogo PRIMARY KEY (id_jogo),
    CONSTRAINT fk_jogo_desenvolvedora FOREIGN KEY (id_desenvolvedora)
        REFERENCES desenvolvedora(id_desenvolvedora) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ck_jogo_preco CHECK (preco > 0)
) ENGINE=InnoDB;

-- Relacionamento N:N Jogo-Gênero.
CREATE TABLE genero (
    id_genero INT UNSIGNED AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    CONSTRAINT pk_genero PRIMARY KEY (id_genero),
    CONSTRAINT uq_genero_nome UNIQUE (nome)
) ENGINE=InnoDB;

CREATE TABLE jogo_genero (
    id_jogo INT UNSIGNED,
    id_genero INT UNSIGNED,
    genero_principal BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT pk_jogo_genero PRIMARY KEY (id_jogo, id_genero),
    CONSTRAINT fk_jogo_genero_jogo FOREIGN KEY (id_jogo)
        REFERENCES jogo(id_jogo) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_jogo_genero_genero FOREIGN KEY (id_genero)
        REFERENCES genero(id_genero) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Forma de pagamento prevista no MER/documento: PIX, crédito e débito.
CREATE TABLE forma_pagamento (
    id_forma_pagamento INT UNSIGNED AUTO_INCREMENT,
    descricao VARCHAR(50) NOT NULL,
    CONSTRAINT pk_forma_pagamento PRIMARY KEY (id_forma_pagamento),
    CONSTRAINT uq_forma_pagamento_descricao UNIQUE (descricao)
) ENGINE=InnoDB;

-- Entidade fraca: (id_jogo, id_dlc) identifica a DLC.
CREATE TABLE dlc (
    id_jogo INT UNSIGNED,
    id_dlc INT UNSIGNED,
    nome VARCHAR(160) NOT NULL,
    conteudo VARCHAR(500),
    data_de_aquisicao DATE,
    valor DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_dlc PRIMARY KEY (id_jogo, id_dlc),
    CONSTRAINT fk_dlc_jogo FOREIGN KEY (id_jogo)
        REFERENCES jogo(id_jogo) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ck_dlc_id CHECK (id_dlc > 0),
    CONSTRAINT ck_dlc_valor CHECK (valor > 0)
) ENGINE=InnoDB;

CREATE TABLE estoque (
    id_estoque INT UNSIGNED AUTO_INCREMENT,
    id_jogo INT UNSIGNED NOT NULL,
    quantidade_disponivel INT UNSIGNED NOT NULL DEFAULT 0,
    ultima_atualizacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_estoque PRIMARY KEY (id_estoque),
    CONSTRAINT uq_estoque_jogo UNIQUE (id_jogo),
    CONSTRAINT fk_estoque_jogo FOREIGN KEY (id_jogo)
        REFERENCES jogo(id_jogo) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ck_estoque_quantidade CHECK (quantidade_disponivel >= 0)
) ENGINE=InnoDB;

-- RN05/RN08/RN10/RN12/RN18.
CREATE TABLE venda (
    id_venda INT UNSIGNED AUTO_INCREMENT,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDENTE','APROVADA','CONCLUIDA','CANCELADA') NOT NULL DEFAULT 'PENDENTE',
    id_cliente INT UNSIGNED NOT NULL,
    id_funcionario INT UNSIGNED NOT NULL,
    id_forma_pagamento INT UNSIGNED NOT NULL,
    CONSTRAINT pk_venda PRIMARY KEY (id_venda),
    CONSTRAINT fk_venda_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_venda_funcionario FOREIGN KEY (id_funcionario)
        REFERENCES funcionario(id_funcionario) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_venda_pagamento FOREIGN KEY (id_forma_pagamento)
        REFERENCES forma_pagamento(id_forma_pagamento) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- RN02/RN06/RN07: relacionamento N:N Venda-Jogo.
CREATE TABLE item_venda (
    id_venda INT UNSIGNED,
    id_jogo INT UNSIGNED,
    quantidade INT UNSIGNED NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_item_venda PRIMARY KEY (id_venda, id_jogo),
    CONSTRAINT fk_item_venda_venda FOREIGN KEY (id_venda)
        REFERENCES venda(id_venda) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_item_venda_jogo FOREIGN KEY (id_jogo)
        REFERENCES jogo(id_jogo) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT ck_item_venda_quantidade CHECK (quantidade > 0),
    CONSTRAINT ck_item_venda_preco CHECK (preco_unitario > 0)
) ENGINE=InnoDB;

-- RN09/RN11/RN17: biblioteca do cliente; a PK impede aquisição duplicada.
CREATE TABLE biblioteca (
    id_cliente INT UNSIGNED,
    id_jogo INT UNSIGNED,
    data_aquisicao DATETIME NOT NULL,
    status_acesso ENUM('LIBERADO','BLOQUEADO') NOT NULL DEFAULT 'LIBERADO',
    CONSTRAINT pk_biblioteca PRIMARY KEY (id_cliente, id_jogo),
    CONSTRAINT fk_biblioteca_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_biblioteca_jogo FOREIGN KEY (id_jogo)
        REFERENCES jogo(id_jogo) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- RN20: a FK composta exige que o jogo principal esteja na biblioteca do cliente.
CREATE TABLE aquisicao_dlc (
    id_cliente INT UNSIGNED,
    id_jogo INT UNSIGNED,
    id_dlc INT UNSIGNED,
    data_aquisicao DATETIME NOT NULL,
    CONSTRAINT pk_aquisicao_dlc PRIMARY KEY (id_cliente, id_jogo, id_dlc),
    CONSTRAINT fk_aquisicao_biblioteca FOREIGN KEY (id_cliente, id_jogo)
        REFERENCES biblioteca(id_cliente, id_jogo) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_aquisicao_dlc FOREIGN KEY (id_jogo, id_dlc)
        REFERENCES dlc(id_jogo, id_dlc) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

DELIMITER $$

CREATE TRIGGER trg_funcionario_autosupervisor_insert
BEFORE INSERT ON funcionario
FOR EACH ROW
BEGIN
    IF NEW.id_supervisor IS NOT NULL
       AND NEW.id_supervisor = NEW.id_funcionario THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Um funcionario nao pode ser seu proprio supervisor';

    END IF;
END$$


CREATE TRIGGER trg_funcionario_autosupervisor_update
BEFORE UPDATE ON funcionario
FOR EACH ROW
BEGIN
    IF NEW.id_supervisor IS NOT NULL
       AND NEW.id_supervisor = NEW.id_funcionario THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Um funcionario nao pode ser seu proprio supervisor';

    END IF;
END$$

DELIMITER ;

CREATE INDEX idx_jogo_nome ON jogo(nome);
CREATE INDEX idx_jogo_desenvolvedora ON jogo(id_desenvolvedora);
CREATE INDEX idx_venda_cliente_data ON venda(id_cliente, data_hora);
CREATE INDEX idx_venda_status_data ON venda(status, data_hora);
CREATE INDEX idx_item_venda_jogo ON item_venda(id_jogo);

-- Relatório consolidado por cliente.
CREATE OR REPLACE VIEW vw_resumo_vendas_cliente AS
SELECT
    c.id_cliente,
    c.nome AS cliente,
    COUNT(DISTINCT v.id_venda) AS quantidade_vendas,
    COALESCE(SUM(iv.quantidade * iv.preco_unitario), 0.00) AS total_gasto,
    MAX(v.data_hora) AS ultima_compra
FROM cliente c
LEFT JOIN venda v ON v.id_cliente = c.id_cliente
    AND v.status IN ('APROVADA', 'CONCLUIDA')
LEFT JOIN item_venda iv ON iv.id_venda = v.id_venda
GROUP BY c.id_cliente, c.nome;
