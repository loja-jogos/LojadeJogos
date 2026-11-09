-- Consultas de verificação da loja de jogos
USE loja_jogos;

-- Q01 - Quais jogos possuem a palavra 'Ring' no nome?
SELECT id_jogo, nome, preco
FROM jogo
WHERE nome LIKE '%Ring%'
ORDER BY nome;

-- Q02 - Quais jogos custam entre R$ 40,00 e R$ 150,00?
SELECT nome, preco
FROM jogo
WHERE preco BETWEEN 40.00 AND 150.00
ORDER BY preco;

-- Q03 - Quais vendas estão pendentes ou aprovadas?
SELECT id_venda, data_hora, status
FROM venda
WHERE status IN ('PENDENTE', 'APROVADA')
ORDER BY data_hora;

-- Q04 - Quais clientes não informaram telefone?
SELECT c.id_cliente, c.nome, c.email, c.telefone
FROM cliente c
WHERE c.telefone IS NULL
ORDER BY c.nome;

-- Q05 - Quais desenvolvedoras estão ativas no catálogo?
SELECT nome, pais
FROM desenvolvedora
ORDER BY nome;

-- Q06 - Quais clientes e seus respectivos pedidos possuem jogos vendidos?
SELECT c.nome AS cliente, v.id_venda, v.status, v.data_hora, j.nome AS jogo
FROM cliente c
JOIN venda v ON v.id_cliente = c.id_cliente
JOIN item_venda iv ON iv.id_venda = v.id_venda
JOIN jogo j ON j.id_jogo = iv.id_jogo
ORDER BY v.id_venda, j.nome;

-- Q07 - Quais clientes nunca fizeram uma venda aprovada ou concluída?
SELECT c.id_cliente, c.nome
FROM cliente c
LEFT JOIN venda v ON v.id_cliente = c.id_cliente
    AND v.status IN ('APROVADA', 'CONCLUIDA')
WHERE v.id_venda IS NULL
ORDER BY c.nome;

-- Q08 - Quantas unidades de cada jogo aparecem nos itens de venda?
SELECT j.nome, SUM(iv.quantidade) AS unidades_vendidas
FROM jogo j
JOIN item_venda iv ON iv.id_jogo = j.id_jogo
GROUP BY j.id_jogo, j.nome
ORDER BY unidades_vendidas DESC;

-- Q09 - Quais jogos foram vendidos em mais de 5 unidades?
SELECT j.nome, SUM(iv.quantidade) AS unidades_vendidas
FROM jogo j
JOIN item_venda iv ON iv.id_jogo = j.id_jogo
GROUP BY j.id_jogo, j.nome
HAVING SUM(iv.quantidade) > 5
ORDER BY unidades_vendidas DESC;

-- Q10 - Qual foi o valor total aprovado/concluído por cliente?
SELECT c.nome AS cliente,
       COUNT(DISTINCT v.id_venda) AS compras,
       SUM(iv.quantidade * iv.preco_unitario) AS total_gasto
FROM cliente c
JOIN venda v ON v.id_cliente = c.id_cliente
JOIN item_venda iv ON iv.id_venda = v.id_venda
WHERE v.status IN ('APROVADA', 'CONCLUIDA')
GROUP BY c.id_cliente, c.nome
ORDER BY total_gasto DESC;

-- Q11 - Quais jogos estão acima do preço médio da própria desenvolvedora? (subconsulta correlacionada)
SELECT j.nome, j.preco, d.nome AS desenvolvedora
FROM jogo j
JOIN desenvolvedora d ON d.id_desenvolvedora = j.id_desenvolvedora
WHERE j.preco > (
    SELECT AVG(j2.preco)
    FROM jogo j2
    WHERE j2.id_desenvolvedora = j.id_desenvolvedora
)
ORDER BY j.preco DESC;

-- Q12 - Quais desenvolvedoras possuem pelo menos um jogo do catálogo? (EXISTS)
SELECT d.nome
FROM desenvolvedora d
WHERE EXISTS (
    SELECT 1
    FROM jogo j
    WHERE j.id_desenvolvedora = d.id_desenvolvedora
)
ORDER BY d.nome;

-- Q13 - Quais clientes já possuem pelo menos uma DLC adquirida?
SELECT DISTINCT c.nome AS cliente, j.nome AS jogo_principal, d.nome AS dlc
FROM aquisicao_dlc a
JOIN cliente c ON c.id_cliente = a.id_cliente
JOIN jogo j ON j.id_jogo = a.id_jogo
JOIN dlc d ON d.id_jogo = a.id_jogo AND d.id_dlc = a.id_dlc
ORDER BY c.nome, j.nome;

-- Q14 - Quais jogos ainda possuem unidades disponíveis em estoque?
SELECT j.nome, e.quantidade_disponivel, e.ultima_atualizacao
FROM estoque e
JOIN jogo j ON j.id_jogo = e.id_jogo
WHERE e.quantidade_disponivel > 0
ORDER BY e.quantidade_disponivel DESC, j.nome;

-- Q15 - Relatório consolidado da view criada no DDL.
SELECT id_cliente, cliente, quantidade_vendas, total_gasto, ultima_compra
FROM vw_resumo_vendas_cliente
ORDER BY total_gasto DESC, cliente;
