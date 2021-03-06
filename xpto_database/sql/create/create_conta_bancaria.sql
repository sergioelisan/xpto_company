--
-- contas bancarias dos clientes
--
CREATE TABLE conta_bancaria(
	id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
	proprietario_id NUMBER REFERENCES pessoa(id),
	instituicao_financeira VARCHAR2 (24),
	agencia VARCHAR2 (16),
	saldo NUMBER (10,2),
	saldo_inicial NUMBER (10,2) NOT NULL,
	criada_em DATE NOT NULL,
	arquivada NUMBER (1),
	CONSTRAINT pk_conta_bancaria PRIMARY KEY(id)
);