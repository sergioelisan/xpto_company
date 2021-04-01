--
--
CREATE OR REPLACE FUNCTION relatorio_cliente_periodo(pessoa_id NUMBER, inicio DATE, fim DATE)
RETURN VARCHAR2 IS
    r_cliente VARCHAR (48);
    r_cliente_desde DATE;
    r_endereco VARCHAR2 (128);
    r_movimentacoes_credito NUMBER (10, 2);
    r_movimentacoes_debito NUMBER (10, 2);
    r_movimentacoes NUMBER (10, 2);
    r_taxas NUMBER (10, 2);
    r_saldo_inicial NUMBER (10, 2);
    r_saldo_atual NUMBER (10, 2);
    r_endereco_id NUMBER;
    result VARCHAR (1000);
BEGIN 
    --
    SELECT nome, endereco_id, cadastrada_em INTO r_cliente, r_endereco_id, r_cliente_desde 
        FROM pessoa WHERE pessoa.id = pessoa_id;
    --
    r_movimentacoes_credito := movimentacoes_credito_cliente(pessoa_id, inicio, fim);
    r_movimentacoes_debito  := movimentacoes_debito_cliente(pessoa_id, inicio, fim);
    r_saldo_inicial         := saldo_inicial_cliente(pessoa_id);
    r_saldo_atual           := r_saldo_inicial + (r_movimentacoes_credito - r_movimentacoes_debito);
    r_endereco              := obter_endereco(r_endereco_id);
    -- 
    SELECT SUM(valor) INTO r_taxas FROM taxa 
        WHERE taxa.cobrado_id = pessoa_id
        AND taxa.cobrada_em >= inicio
        AND taxa.cobrada_em < fim;
    --
    RETURN (
        'Relatório de saldo do cliente '    || r_cliente || ':' || (chr(13)||chr(10)) ||
        'Período: '                         || TO_CHAR(inicio) || ' a ' || TO_CHAR(fim) || (chr(13)||chr(10)) ||
        'Cliente: '                         || r_cliente || (chr(13)||chr(10)) ||
        'Cliente desde: '                   || TO_CHAR(r_cliente_desde) || (chr(13)||chr(10)) ||
        'Endereço: '                        || r_endereco || (chr(13)||chr(10)) ||
        'Movimentações de crédito: '        || TO_CHAR(r_movimentacoes_credito) || (chr(13)||chr(10)) ||
        'Movimentações de débito: '         || TO_CHAR(r_movimentacoes_debito) || (chr(13)||chr(10)) ||
        'Total de movimentações: '          || TO_CHAR(r_movimentacoes) || (chr(13)||chr(10)) ||
        'Valor pago pelas movimentações: '  || TO_CHAR(r_taxas) || (chr(13)||chr(10)) ||
        'Saldo inicial: '                   || TO_CHAR(r_saldo_inicial) || (chr(13)||chr(10)) ||
        'Saldo atual: '                     || TO_CHAR(r_saldo_atual)
    );
END;
/

