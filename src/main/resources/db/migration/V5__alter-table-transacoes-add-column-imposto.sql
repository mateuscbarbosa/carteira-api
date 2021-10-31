ALTER TABLE transacoes ADD imposto DECIMAL (18,2);

UPDATE transacoes SET imposto = ((preco * quantidade) * 0.15) WHERE tipo = 'VENDA' AND ((preco * quantidade) > 20000);
UPDATE transacoes SET imposto = 0 WHERE imposto IS NULL;