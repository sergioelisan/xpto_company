--
--
CREATE OR REPLACE FUNCTION saldo_atual_cliente(pessoa_id NUMBER, inicio DATE, fim DATE)
RETURN NUMBER IS
    r_movimentacoes_credito NUMBER (10, 2);
    r_movimentacoes_debito NUMBER (10, 2);
    r_movimentacoes NUMBER (10, 2);
    r_saldoinicial NUMBER (10, 2);
BEGIN 
    r_saldoinicial := saldo_inicial_cliente(pessoa_id);
    r_movimentacoes_credito := movimentacoes_credito_cliente(pessoa_id, inicio, fim);
    r_movimentacoes_debito  := movimentacoes_debito_cliente(pessoa_id, inicio, fim);
    RETURN r_saldoinicial + r_movimentacoes_credito - r_movimentacoes_debito;
END;
/