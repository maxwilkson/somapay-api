CREATE TABLE IF NOT EXISTS conta (
    id bigint NOT NULL,
    agencia bigint,
    banco varchar(100),
    digito_agencia int,
    digito_conta int,
    numero bigint,
    operacao bigint,
    saldo numeric(19,2)
);

CREATE SEQUENCE IF NOT EXISTS conta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS contato (
    id bigint NOT NULL,
    numero varchar(20),
    tipo varchar(10)
);

CREATE SEQUENCE IF NOT EXISTS contato_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS empresa (
    id bigint NOT NULL,
    cnpj varchar(20),
    data_abertura date,
    email varchar(100),
    inscricao_estadual varchar(50),
    nome varchar(255),
    site varchar(255),
    endereco_id bigint
);

CREATE TABLE IF NOT EXISTS empresa_conta (
    conta_id bigint,
    empresa_id bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS empresa_contato (
    empresa_id bigint NOT NULL,
    contato_id bigint NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS empresa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS endereco (
    id bigint NOT NULL,
    bairro varchar(100),
    cep varchar(20),
    cidade varchar(100),
    estado varchar(50),
    logradouro varchar(255),
    numero varchar(20)
);

CREATE SEQUENCE IF NOT EXISTS endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS folha_pagamento (
    id bigint NOT NULL,
    data_pagamento date,
    taxa numeric(19,2),
    empresa_id bigint
);


CREATE SEQUENCE IF NOT EXISTS folha_pagamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS folha_pagamento_item (
    id bigint NOT NULL,
    valor numeric(19,2),
    folha_pagamento_id bigint,
    funcionario_id bigint
);

CREATE TABLE IF NOT EXISTS funcionario (
    id bigint NOT NULL,
    cpf varchar(20),
    data_admissao date,
    data_demissao date,
    data_nascimento date,
    email varchar(100),
    funcao varchar(100),
    nome varchar(255),
    rg varchar(20),
    salario numeric(19,2),
    sexo varchar(20),
    empresa_id bigint,
    endereco_id bigint
);

CREATE TABLE IF NOT EXISTS funcionario_conta (
    conta_id bigint,
    funcionario_id bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionario_contato (
    funcionario_id bigint NOT NULL,
    contato_id bigint NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS funcionario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
