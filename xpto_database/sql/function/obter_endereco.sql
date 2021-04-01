---
---
CREATE OR REPLACE FUNCTION obter_endereco(endereco_id NUMBER)
RETURN VARCHAR2 IS
    e_logradouro VARCHAR2 (64);
    e_complemento VARCHAR2 (64);
    e_cep VARCHAR2 (9);
    e_numero NUMBER;
    e_distrito VARCHAR2 (32);
    e_cidade VARCHAR2 (32);
    e_estado VARCHAR2 (24);
BEGIN 
    SELECT logradouro, cep, complemento, numero, distrito, cidade, estado 
    INTO e_logradouro, e_cep, e_complemento, e_numero, e_distrito, e_cidade, e_estado
    FROM endereco WHERE endereco.id = endereco_id;

    RETURN (e_logradouro || ', ' || e_complemento || ', ' || TO_CHAR(e_numero) || ' - ' 
                || e_cep || ', ' || e_distrito || ', ' || e_cidade || ' - ' || e_estado);
END;
/