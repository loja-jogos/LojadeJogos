-- Candidatos, somente em base isolada. Inspecionar SHOW INDEX antes.
SHOW INDEX FROM venda;
SHOW INDEX FROM item_venda;
-- idx_venda_cliente_data e idx_venda_status_data já existem: NÃO recriar.
-- Índice A: cliente e igualdade de status antes do intervalo temporal.
CREATE INDEX idx_venda_cliente_status_data ON venda(id_cliente,status,data_hora);
-- Índice B: funcionário seguido de intervalo temporal (FK sozinha não cobre).
CREATE INDEX idx_venda_func_data ON venda(id_funcionario,data_hora);
-- Índice C: cobertura da agregação; PK implícita contém id_venda em InnoDB.
CREATE INDEX idx_item_jogo_cobertura ON item_venda(id_jogo,quantidade,preco_unitario);
-- scripts/desempenho.sh registra planos reais antes/depois e cronometra consultas.
