XPTO
====

Aplicacao usada como teste técnico para avaliação. Consiste basicamente em um banco de dados Oracle 18c, scripts PL/SQL e um backend Java usado como camada de negócios e API REST.

## INSTALAÇÃO

Tenha o Docker, JDK 15 e Maven instalados em seu computador, em seguida:

```
$ ./xpto_db_build
```

Após o processo de instalacao do banco de dados, procure os scripts de create dentro da pasta

```
xpto_database/sql/create
```

e os execute na ordem

```
create_endereco.sql
create_pessoa.sql
create_conta_bancaria.sql
create_movimentacao.sql
create_taxa.sql
```

Instale as funcoes e o trigger que estao nas pastas ```function/``` e ```trigger/```, nao importando a ordem

Banco de dados devidamente instalado, execute o comando para construir a imagem docker e executar o container

```
$ ./xpto_run
```

## IMPRIMIR RELATORIOS

Pode imprimir rodando os testes, ou atraves da servidor funcionando pelo docker como descrito acima.
As URLs sao

```
http://localhost:8080/xpto/clientes
```

```
http://localhost:8080/xpto/clientes/todos
```

```
http://localhost:8080/xpto/receita
```

## XPTO DATABASE

- **xpto_database/**  

    - **docker/**
        Arquivos para construir a imagem docker do banco de dados Oracle 18c. Basta rodar o comando  
        
        ```
        docker build -t oracle/database:18.4.0-xe -f Dockerfile.xe .
        ```
    - **sql/**
        - **create/**  
            Scripts de CREATE TABLE que devem ser aplicados na base de dados assim que ela for iniciada pela primeira vez  

        - **function/**  
            Funcoes PL SQL para consulta e geracao de relatórios  

        - **trigger/**  
            Trigger chamada após o registro de uma movimentação financeira, para deducao e credito das partes envolvidas e a devida cobranca de taxa.

Por comodidade, para criar a base de dados e deixá-la  
rodando, basta executar o script
```
$ ./xpto_db_build
```

## XPTO BACKEND

Desenvolvido em Java, com estrutura de projeto  
construida pelo Maven, unidades de Testes com JUnit  
e integracao com base de dados atraves do JDBC.
API Rest baseada no Jetty.

Usados padroes como DAO, Heranca (Strategy), Factory (com o DAOFactory).

- **main/java/nonreal/xpto**  

    - **dao**  
        Classes que implementam o padrao DAO (Data
        Access Object) que abstrai o acesso a base
        de dados para entitidades.

    - **logic**  
        Algumas regras implementadas, como logica de relatorios

    - **model**  
        Entidades que correspondem as tabelas do banco

    - **rest**
        API REST  

    - **util**  
        Ferramentas uteis, como a Conexao com o banco de dados