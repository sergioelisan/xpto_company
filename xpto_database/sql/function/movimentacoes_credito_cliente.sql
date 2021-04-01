--
--
CREATE OR REPLACE FUNCTION movimentacoes_credito_cliente(pessoa_id NUMBER, inicio DATE, fim DATE)
RETURN NUMBER IS
    r_movimentacoes NUMBER (10, 2);
BEGIN 
    SELECT SUM(valor) INTO r_movimentacoes FROM movimentacao 
        WHERE movimentacao.pessoa_destino_id = pessoa_id
        AND movimentacao.realizado_em >= inicio
        AND movimentacao.realizado_em < fim;
    RETURN r_movimentacoes;
END;
/