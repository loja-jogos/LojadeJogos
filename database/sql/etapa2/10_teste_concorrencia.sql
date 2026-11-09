-- Instruções para duas conexões reais: NÃO executar sequencialmente como prova de concorrência.
-- Preparar banco isolado com estoque 1 para um jogo sem biblioteca para os dois clientes.
-- SESSÃO A:
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
START TRANSACTION;
SELECT * FROM estoque WHERE id_jogo=14 FOR UPDATE;
-- Manter transação aberta enquanto a sessão B executa o SELECT abaixo.
UPDATE estoque SET quantidade_disponivel=quantidade_disponivel-1 WHERE id_jogo=14 AND quantidade_disponivel>0;
-- SELECT ROW_COUNT(); esperado 1 apenas se a preparação foi realizada.
COMMIT;
-- SESSÃO B (em outro terminal, executar ENQUANTO A mantém o bloqueio):
-- SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
-- START TRANSACTION;
-- SELECT * FROM estoque WHERE id_jogo=14 FOR UPDATE;
-- Deve ler 0 após A liberar; aplicação recusa a compra e executa ROLLBACK.
-- UPDATE estoque SET quantidade_disponivel=quantidade_disponivel-1 WHERE id_jogo=14 AND quantidade_disponivel>0;
-- SELECT ROW_COUNT();
-- ROLLBACK;
-- VendaIntegrationTest concorre duas confirmações com clientes distintos e barreira.
