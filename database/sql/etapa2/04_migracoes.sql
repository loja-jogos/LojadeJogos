-- Incremental: executar UMA vez após revisar backup e banco selecionado.
-- Não contém DROP DATABASE, USE ou recriação das tabelas originais.
CREATE TABLE usuario_app (
 id_usuario INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 login VARCHAR(80) NOT NULL UNIQUE,
 senha_hash VARCHAR(100) NOT NULL,
 perfil ENUM('ADMIN','OPERADOR','AUDITOR') NOT NULL,
 id_funcionario INT UNSIGNED NULL,
 ativo BOOLEAN NOT NULL DEFAULT TRUE,
 CONSTRAINT fk_usuario_funcionario FOREIGN KEY(id_funcionario) REFERENCES funcionario(id_funcionario) ON DELETE RESTRICT
) ENGINE=InnoDB;
-- Perfis web são distintos dos usuários MySQL. Bootstrap usa BCrypt com custo 12.
