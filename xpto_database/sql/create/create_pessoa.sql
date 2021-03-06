--
-- clientes fisicos e juridicos
--
CREATE TABLE pessoa(
	id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
	nome VARCHAR2 (48),
	endereco_id NUMBER REFERENCES endereco(id),
	cnp VARCHAR2 (18),
	pessoa_juridica NUMBER (1),
	cadastrada_em DATE NOT NULL,
	arquivada NUMBER (1),
	CONSTRAINT pk_pessoa PRIMARY KEY(id)
);