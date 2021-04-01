--
-- enderecos
--
CREATE TABLE endereco(
	id 				NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
	logradouro 		VARCHAR2 (64),
	cep		 		VARCHAR2 (9),
	complemento 	VARCHAR2 (64),
	numero 			NUMBER,
	distrito 		VARCHAR2 (32),
	cidade 			VARCHAR2 (32),
	estado 			VARCHAR2 (24),
	CONSTRAINT pk_endereco PRIMARY KEY(id)
);