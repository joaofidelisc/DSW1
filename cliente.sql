DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
	email VARCHAR(40) NOT NULL,
	senha VARCHAR(30) NOT NULL,
	cpf BIGINT NOT NULL,
	nome VARCHAR(50) NOT NULL,
	telefone BIGINT,
	sexo CHAR NOT NULL,
	data_nascimento DATE,
	CONSTRAINT PK_CLIENTE PRIMARY KEY (cpf),
	CONSTRAINT UNIQUE_email_senha UNIQUE (email, senha),
	CONSTRAINT sexo_check CHECK (sexo in ('M', 'F', 'N'))
);

INSERT INTO CLIENTE VALUES ('joao@mail.com','123',11122233390,'João Fidelis',16955664433, 'M', '10/10/2001');
INSERT INTO CLIENTE VALUES ('matteus@mail.com','123',11111111190,'Matteus Souza',22922222222, 'M', '02/12/1999');
INSERT INTO cliente (email,senha,cpf,nome,telefone,sexo,data_nascimento)
VALUES
  ('wayne@mail.com','123',15101282816,'Wayne','16912704934','F','2009-06-03'),
  ('samson@mail.com','123',74551156427,'Samson','16912582118','M','1979-04-04'),
  ('melyssa9910@mail.com','123',15932871075,'Melyssa','16975894854','F','2001-02-21'),
  ('buffy@mail.com','123',44026018406,'Buffy','16960937097','F','2001-06-01'),
  ('colin1118@mail.com','123',62044080519,'Colin','16918257522','F','1994-10-23'),
  ('macaulay@mail.com','123',28813665860,'Macaulay','16965041782','F','2011-05-21'),
  ('petra9439@mail.com','123',92689953598,'Petra','16988818356','M','1977-07-30'),
  ('byron9572@mail.com','123',28229128536,'Byron','16985851225','M','1991-11-14'),
  ('doris@mail.com','123',66614549500,'Doris','16932057154','M','1985-01-14'),
  ('bryar@mail.com','123',79525629354,'Bryar','16948986377','M','1972-03-24');
INSERT INTO cliente (email,senha,cpf,nome,telefone,sexo,data_nascimento)
VALUES
  ('fulton8537@mail.com','123',65426014386,'Fulton','16902564463','M','1973-11-19'),
  ('isabelle5900@mail.com','123',55870267949,'Isabelle','16938424380','F','1999-09-16'),
  ('tashya@mail.com','123',24373960426,'TaShya','16966614768','F','2020-01-08'),
  ('lucius@mail.com','123',16455462570,'Lucius','16984414246','F','1985-06-22'),
  ('joshua@mail.com','123',10189087447,'Joshua','16945761720','F','2003-04-23'),
  ('ethan@mail.com','123',22753584288,'Ethan','16938353319','M','2012-07-29'),
  ('august@mail.com','123',99935432318,'August','16973443597','M','2016-05-31'),
  ('ingrid9933@mail.com','123',48051809617,'Ingrid','16973375130','F','2013-05-18'),
  ('eve@mail.com','123',54877089628,'Eve','16946664409','F','2004-05-23');
INSERT INTO cliente (email,senha,cpf,nome,telefone,sexo,data_nascimento)
VALUES
  ('susan4331@mail.com','123',30700810822,'Susan','16971713547','M','2000-11-08'),
  ('petra577@mail.com','123',96696676002,'Petra','16976694827','F','1972-03-08'),
  ('dennis7486@mail.com','123',25488253702,'Dennis','16954356350','F','1982-10-21'),
  ('jared1550@mail.com','123',13355130461,'Jared','16924930178','M','1973-12-20'),
  ('mark@mail.com','123',73155557248,'Mark','16935508263','F','2014-10-17'),
  ('marsden@mail.com','123',46343072391,'Marsden','16929157848','F','1985-05-13'),
  ('cole@mail.com','123',76013513687,'Cole','16967364261','F','1985-08-12'),
  ('gannon@mail.com','123',49533933492,'Gannon','16905664542','M','2006-07-22'),
  ('raya@mail.com','123',51426940790,'Raya','16952417138','F','1976-12-30'),
  ('rae1501@mail.com','123',33823479980,'Rae','16918981686','M','2003-11-23');
INSERT INTO cliente (email,senha,cpf,nome,telefone,sexo,data_nascimento)
VALUES
  ('nina@mail.com','123',10573769758,'Nina','16952924852','M','1983-06-15'),
  ('rajah@mail.com','123',43938421282,'Rajah','16950130692','M','2008-09-28'),
  ('karly9196@mail.com','123',21276299111,'Karly','16913224681','M','2014-06-05'),
  ('mckenzie6715@mail.com','123',91450551602,'McKenzie','16980548772','F','2016-02-04'),
  ('graham2270@mail.com','123',99905406199,'Graham','16984368547','M','1977-01-29'),
  ('brynne5596@mail.com','123',85411709859,'Brynne','16954438241','M','1970-03-04'),
  ('abra8765@mail.com','123',34872654582,'Abra','16995173886','F','2022-02-13'),
  ('hoyt5694@mail.com','123',33035152403,'Hoyt','16970143774','F','2018-06-03'),
  ('indigo6338@mail.com','123',20276670354,'Indigo','16984829730','M','2006-07-15'),
  ('charde9690@mail.com','123',66864204281,'Charde','16984535611','F','2013-07-26');
INSERT INTO cliente (email,senha,cpf,nome,telefone,sexo,data_nascimento)
VALUES
  ('hanna@mail.com','123',44130346524,'Hanna','16971603407','F','2020-02-07'),
  ('scarlett@mail.com','123',20566260609,'Scarlett','16928666678','F','1992-07-10'),
  ('zahir628@mail.com','123',23200988990,'Zahir','16999656181','F','2005-02-13'),
  ('valentine9358@mail.com','123',77116192209,'Valentine','16914435988','M','1974-01-14'),
  ('kirk3687@mail.com','123',93353147702,'Kirk','16944193392','M','2002-11-26'),
  ('george1177@mail.com','123',17305675153,'George','16925202192','F','2018-07-20'),
  ('christine7507@mail.com','123',29375439012,'Christine','16922368635','M','2011-09-04'),
  ('ignatius@mail.com','123',80670852967,'Ignatius','16919971445','F','2014-08-10'),
  ('ignatius649@mail.com','123',58615161286,'Ignatius','16946230673','F','1993-03-28'),
  ('grady@mail.com','123',85338461684,'Grady','16985212817','M','1974-02-10');
