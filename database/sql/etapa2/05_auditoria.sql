CREATE TABLE auditoria (
 id_evento BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 tabela_afetada VARCHAR(40) NOT NULL,
 operacao VARCHAR(10) NOT NULL,
 registro INT UNSIGNED NOT NULL,
 autor_aplicacao VARCHAR(80) NULL,
 autor_mysql VARCHAR(288) NOT NULL,
 data_hora DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
 antes JSON NOT NULL,
 depois JSON NOT NULL,
 INDEX idx_auditoria_data(data_hora)
) ENGINE=InnoDB;
DELIMITER $$
CREATE TRIGGER trg_auditoria_preco AFTER UPDATE ON jogo FOR EACH ROW
BEGIN
 IF NOT (OLD.preco <=> NEW.preco) THEN
 INSERT INTO auditoria(tabela_afetada,operacao,registro,autor_aplicacao,autor_mysql,antes,depois)
 VALUES('jogo','UPDATE',NEW.id_jogo,@app_usuario,USER(),JSON_OBJECT('preco',OLD.preco),JSON_OBJECT('preco',NEW.preco));
 END IF;
END$$
CREATE TRIGGER trg_auditoria_estoque AFTER UPDATE ON estoque FOR EACH ROW
BEGIN
 IF NOT (OLD.quantidade_disponivel <=> NEW.quantidade_disponivel) THEN
 INSERT INTO auditoria(tabela_afetada,operacao,registro,autor_aplicacao,autor_mysql,antes,depois)
 VALUES('estoque','UPDATE',NEW.id_jogo,@app_usuario,USER(),JSON_OBJECT('quantidade',OLD.quantidade_disponivel),JSON_OBJECT('quantidade',NEW.quantidade_disponivel));
 END IF;
END$$
DELIMITER ;
