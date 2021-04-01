--
--
CREATE OR REPLACE FUNCTION saldo_inicial_cliente(pessoa_id NUMBER)
RETURN NUMBER IS
    r_saldo_inicial NUMBER (10, 2);
BEGIN 
    SELECT saldo_inicial INTO r_saldo_inicial FROM conta_bancaria 
        WHERE criada_em IN (SELECT MIN(criada_em) FROM conta_bancaria 
                WHERE conta_bancaria.proprietario_id = pessoa_id);
    RETURN r_saldo_inicial;
END;
/