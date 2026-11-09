-- Somente base isolada; usa jogo existente e desfaz ao final.
START TRANSACTION;
SET @app_usuario='experimento-sql';
SELECT quantidade_disponivel INTO @antes FROM estoque WHERE id_jogo=1 FOR UPDATE;
UPDATE estoque SET quantidade_disponivel=quantidade_disponivel+1 WHERE id_jogo=1;
SELECT * FROM auditoria WHERE tabela_afetada='estoque' AND registro=1 ORDER BY id_evento DESC LIMIT 1;
ROLLBACK;
SET @app_usuario=NULL;
SELECT quantidade_disponivel=@antes AS rollback_estoque_ok FROM estoque WHERE id_jogo=1;
-- Cenários multi-tabela de COMMIT/ROLLBACK e falha após biblioteca: VendaIntegrationTest.
