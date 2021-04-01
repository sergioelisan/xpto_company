--
--
CREATE OR REPLACE FUNCTION relatorio_receita_periodo(inicio DATE, fim DATE)
RETURN VARCHAR2 IS
    CURSOR c_pessoas is SELECT id, nome FROM pessoa;
    c_id NUMBER; 
    c_nome VARCHAR2 (48); 
    c_movimentacoes NUMBER; 
    c_saldo NUMBER(10,2);
    c_taxas NUMBER(10,2);
    result VARCHAR2 (4000) := 'Período: '|| TO_CHAR(inicio) || ' a ' || TO_CHAR(fim) || (chr(13)||chr(10));
BEGIN 
    OPEN c_pessoas; 
    LOOP 
    FETCH c_pessoas into c_id, c_nome; 
        EXIT WHEN c_pessoas%notfound; 
        -- soma todas as movimentacoes que o cliente esteve envolvido
        SELECT count(*) INTO c_movimentacoes FROM movimentacao 
            WHERE movimentacao.pessoa_origem_id = c_id OR movimentacao.pessoa_destino_id = c_id;
        -- soma os valores de todas as movimentacoes que o cliente esteve envolvido
        SELECT sum(valor) INTO c_saldo FROM movimentacao 
            WHERE movimentacao.pessoa_origem_id = c_id;
        --
        result := result || 'Cliente: ' || c_nome || ' - Quantidade de movimentações:  ' || 
            TO_CHAR(c_movimentacoes) || ' - Valor das movimentações: R$ ' || TO_CHAR(c_saldo)|| (chr(13)||chr(10)); 
    END LOOP;
    CLOSE c_pessoas;
    --
    SELECT SUM(valor) INTO c_taxas FROM taxa 
        WHERE taxa.cobrada_em >= inicio
        AND taxa.cobrada_em < fim;
    result := result || 'Total de receitas com taxas: R$ ' || TO_CHAR(c_taxas)  || ' ';
    --
    RETURN result;
END;
/