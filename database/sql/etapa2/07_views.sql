-- A view original é preservada: status efetivos APROVADA e CONCLUIDA.
-- View restrita não expõe CPF, telefone ou e-mail. SQL SECURITY DEFINER permite
-- conceder somente SELECT na view, sem conceder nas tabelas subjacentes.
CREATE SQL SECURITY DEFINER VIEW vw_clientes_restrita AS
 SELECT cliente,quantidade_vendas,total_gasto,ultima_compra FROM vw_resumo_vendas_cliente;
