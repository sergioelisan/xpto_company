--
--
CREATE OR REPLACE FUNCTION relatorio_saldo_todos_clientes(inicio DATE, fim DATE)
RETURN VARCHAR2 IS
    CURSOR c_pessoas is SELECT id, nome, cadastrada_em FROM pessoa;
    c_id NUMBER; 
    c_nome VARCHAR2 (48); 
    c_cadastrada_em DATE; 
    c_saldo NUMBER(10,2);
    result VARCHAR2 (4000) := '';
BEGIN 
    OPEN c_pessoas; 
    LOOP 
    FETCH c_pessoas into c_id, c_nome, c_cadastrada_em; 
        EXIT WHEN c_pessoas%notfound; 
        c_saldo := saldo_atual_cliente(c_id, inicio, fim);
        result  := result || 'Cliente: ' || c_nome || ' - Cliente desde: ' || 
            TO_CHAR(c_cadastrada_em) || ' – Saldo em ' || TO_CHAR(fim) || ': ' ||
            TO_CHAR(c_saldo) || (chr(13)||chr(10)); 
    END LOOP;
    CLOSE c_pessoas;
    RETURN result;
END;
/