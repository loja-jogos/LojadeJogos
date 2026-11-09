-- Executar SOMENTE no banco criado por scripts/preparar-banco.sh loja_jogos_perf.
-- Dados sintéticos coerentes com propriedade: um cliente novo por venda.
DELIMITER $$
CREATE PROCEDURE carga_perf()
BEGIN
 DECLARE i INT DEFAULT 1;
 DECLARE c INT UNSIGNED;
 DECLARE v INT UNSIGNED;
 START TRANSACTION;
 WHILE i<=20000 DO
 INSERT INTO cliente(nome,cpf,email) VALUES(CONCAT('Sintetico ',i),LPAD(80000000000+i,11,'0'),CONCAT('perf',i,'@example.test'));
 SET c=LAST_INSERT_ID();
 INSERT INTO venda(id_cliente,id_funcionario,id_forma_pagamento,status,data_hora)
 VALUES(c,1+MOD(i,8),1,'CONCLUIDA',TIMESTAMP('2025-01-01')+INTERVAL MOD(i,700) DAY);
 SET v=LAST_INSERT_ID();
 INSERT INTO item_venda VALUES(v,1+MOD(i,14),1,99.90);
 INSERT INTO biblioteca VALUES(c,1+MOD(i,14),CURRENT_TIMESTAMP,'LIBERADO');
 SET i=i+1;
 END WHILE;
 COMMIT;
END$$
DELIMITER ;
CALL carga_perf();
DROP PROCEDURE carga_perf;
ANALYZE TABLE cliente,venda,item_venda;
