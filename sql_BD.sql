DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS profissional;
DROP TABLE IF EXISTS administrador;


CREATE TABLE cliente (
	email VARCHAR(40) NOT NULL,
	senha VARCHAR(30) NOT NULL,
	cpf INT8,
	nome VARCHAR(50) NOT NULL,
	telefone INT8,
	sexo CHAR NOT NULL,
	data_nascimento DATE,
	CONSTRAINT PK_CLIENTE PRIMARY KEY (cpf),
	CONSTRAINT UNIQUE_email_senha UNIQUE (email, senha),
	CONSTRAINT sexo_check CHECK (sexo in ('M', 'F', 'N'))
);

CREATE TABLE profissional(
	email varchar(40) NOT NULL,
	senha varchar(30) NOT NULL,
	cpf int8,
	nome varchar(50) NOT NULL,
	areaConhecimento char NOT NULL,
	especialidade char NOT NULL,
	CONSTRAINT PK_PROFISSIONAL PRIMARY KEY (cpf)
);

CREATE TABLE consulta(
    num_consulta serial,
	dia DATE,
    horario TIME,
	profissional int NOT NULL,
	cancelada bool DEFAULT FALSE,
	cliente int NOT NULL,
	CONSTRAINT PK_CONSULTA PRIMARY KEY (num_consulta),
	CONSTRAINT FK_CONSULTA_PROFISSIONAL FOREIGN KEY (profissional)
		REFERENCES profissional(cpf),
	CONSTRAINT FK_CONSULTA_CLIENTE FOREIGN KEY (cliente)
		REFERENCES cliente(cpf)
);

create table IF NOT EXISTS administrador (
	email VARCHAR (60) NOT NULL,
    senha VARCHAR (24) NOT NULL, 
    nome VARCHAR (50) NOT NULL,
	CONSTRAINT PK_ADMIN PRIMARY KEY(nome)
);