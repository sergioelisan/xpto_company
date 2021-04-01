--
-- soma a quantidade de movimentacoes feitas pelo cliente
-- para calcular a taxa a ser paga
--
CREATE OR REPLACE FUNCTION calcular_taxa(pessoa_id NUMBER)
RETURN NUMBER IS
    taxa NUMBER (10, 2);
    comeco_do_mes DATE; -- data do inicio da cobranca mensal do cliente
    movimentacoes NUMBER;
    dia NUMBER; -- vai armazenar o dia do mes que o cliente se cadastrou
    mes NUMBER; -- vai armazenar o mes corrente
    ano NUMBER; -- vai armazenar o ano corrente
    mes_char VARCHAR (2); -- vai armazenar o mes corrente
    dia_char VARCHAR (2); -- vai armazenar o ano corrente
BEGIN
    -- dia do mes que a pessoa se cadastrou
    SELECT EXTRACT(DAY FROM cadastrada_em) INTO dia FROM pessoa WHERE pessoa.id = pessoa_id;

    -- mes e ano corrente
    SELECT EXTRACT(MONTH FROM SYSDATE) INTO mes FROM dual;
    SELECT EXTRACT(YEAR FROM SYSDATE) INTO ano FROM dual;

    IF (mes < 10) THEN
        mes_char := '0' || TO_CHAR(mes);
    ELSE
        mes_char := TO_CHAR(mes);
    END IF; 

    IF (dia < 10) THEN
        dia_char := '0' || TO_CHAR(dia);
    ELSE
        dia_char := TO_CHAR(dia);
    END IF;

    -- monta a data que representa o inicio da cobranca mensal deste cliente
    SELECT TO_DATE(TO_CHAR(ano) || mes_char || dia_char, 'YYYYMMDD')
    INTO comeco_do_mes FROM dual;
    
    -- conta a quantidade de movimentacoes sobre o mes de cobranca corrente
    SELECT count(*) INTO movimentacoes FROM movimentacao 
    WHERE movimentacao.realizado_em >= comeco_do_mes;

    -- faz o calculo da taxa de movimentacao com base na quantidade de movimentacoes
    -- que o cliente fez durante o periodo do mes que ele eh cobrado
    IF (movimentacoes < 10) THEN 
        taxa := 1.00;
    ELSIF (movimentacoes >= 10 AND movimentacoes < 20) THEN  
        taxa := 0.75;
    ELSE
        taxa := 0.50;
    END IF;

    RETURN taxa;
END;
/