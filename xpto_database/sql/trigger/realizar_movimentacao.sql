-- faz a cobranca da taxa de movimentacao de
-- um cliente
CREATE OR REPLACE TRIGGER realizar_movimentacao 
BEFORE INSERT ON movimentacao
FOR EACH ROW
DECLARE 
   -- retorna o valor da taxa com base nas movimentacoes 
   -- deste cliente durante o mes
   taxa NUMBER := calcular_taxa(:NEW.pessoa_origem_id);
   saldo_origem NUMBER (10, 2);
   saldo_destino NUMBER (10, 2);
BEGIN 
   DBMS_OUTPUT.PUT_LINE(:NEW.id);
   -- faz a movimentacao entre as contas bancarias, deduz da origem e deposita no destino
   -- e ja cobra a taxa de movimentacoes
   SELECT saldo INTO saldo_origem FROM conta_bancaria WHERE id = :NEW.conta_origem_id;
   SELECT saldo INTO saldo_destino FROM conta_bancaria WHERE id = :NEW.conta_destino_id;

   saldo_origem := saldo_origem - (:NEW.valor + taxa);
   saldo_destino := saldo_destino + :NEW.valor;

   UPDATE conta_bancaria c SET c.saldo = saldo_origem WHERE c.id = :NEW.conta_origem_id;
   UPDATE conta_bancaria c SET c.saldo = saldo_destino WHERE c.id = :NEW.conta_destino_id;

   -- faz o registro da cobranca
   INSERT INTO taxa (cobrado_id, valor, cobrada_em) 
      VALUES (:NEW.pessoa_origem_id, taxa, SYSDATE);
END; 
/ 