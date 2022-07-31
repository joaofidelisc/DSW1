DROP TABLE IF EXISTS consulta; 
DROP TABLE IF EXISTS profissional; 
CREATE TABLE profissional(
	email varchar(40) NOT NULL,
	senha varchar(30) NOT NULL,
	cpf BIGINT,
	nome varchar(50) NOT NULL,
	areaConhecimento VARCHAR(20) NOT NULL,
	especialidade VARCHAR(20) NOT NULL,
    local_pdf VARCHAR(100) NOT NULL,
	CONSTRAINT PK_PROFISSIONAL PRIMARY KEY (cpf)
);

INSERT INTO PROFISSIONAL VALUES ('luiz@mail.com','123',255255255127, 'Luiz Filipe', 'Medicina', 'Cardiologista', 'curriculo.pdf');
INSERT INTO PROFISSIONAL VALUES ('italo@mail.com','123',127127127255, 'Italo Ribeiro', 'Psicologia','Psicologo Infantil','curriculo1.pdf');
INSERT INTO profissional (email,senha,cpf,nome,areaConhecimento,especialidade,local_pdf)
VALUES
  ('whilemina@mail.com','123',96113030461,'Grant','Medicina', 'Cardiologista','c.pdf'),
  ('ahmed@mail.com','123',21157824144,'Caleb','Medicina', 'Cardiologista','c.pdf'),
  ('joseph8171@mail.com','123',31054678931,'Martina','Medicina', 'Cardiologista','c.pdf'),
  ('mason66@mail.com','123',70113931911,'Travis','Medicina', 'Cardiologista','c.pdf'),
  ('basia@mail.com','123',16472122218,'Tiger','Advocacia', 'Advogado Criminal','c.pdf');
INSERT INTO profissional (email,senha,cpf,nome,areaConhecimento,especialidade,local_pdf)
VALUES
  ('charles9897@mail.com','123',76499772632,'Chaim','Advocacia', 'Advogado Criminal','c.pdf'),
  ('burke@mail.com','123',51482415680,'Grant','Medicina', 'Cardiologista','c.pdf'),
  ('ian@mail.com','123',53004088465,'Raphael','Medicina', 'Cardiologista','c.pdf'),
  ('amaya8958@mail.com','123',18923924925,'Yasir','Advocacia', 'Advogado Criminal','c.pdf'),
  ('riley@mail.com','123',85663021003,'Britanni','Medicina', 'Cardiologista','c.pdf'),
  ('jameson9461@mail.com','123',80595287698,'Patience','Medicina', 'Cardiologista','c.pdf');
INSERT INTO profissional (email,senha,cpf,nome,areaConhecimento,especialidade,local_pdf)
VALUES
  ('chancellor6230@mail.com','123',52677495837,'Nehru','Medicina', 'Cardiologista','c.pdf'),
  ('philip@mail.com','123',26848972314,'Timon','Psicologia','Psicologo Infantil','c.pdf'),
  ('sasha7440@mail.com','123',21966626758,'Colton','Medicina', 'Cardiologista','c.pdf'),
  ('allistair@mail.com','123',28150623172,'Quail','Advocacia', 'Advogado Criminal','c.pdf'),
  ('constance2333@mail.com','123',91018824956,'Karen','Psicologia','Psicologo Infantil','c.pdf');
INSERT INTO profissional (email,senha,cpf,nome,areaConhecimento,especialidade,local_pdf)
VALUES
  ('brenna@mail.com','123',85945877804,'Nora','Psicologia','Psicologo Infantil','c.pdf'),
  ('sigourney2548@mail.com','123',95019741217,'Conan','Medicina', 'Cardiologista','c.pdf'),
  ('arden5795@mail.com','123',73977901327,'Oleg','Advocacia','Advogado Criminal','c.pdf'),
  ('hamish@mail.com','123',70265255465,'Wanda','Advocacia', 'Advogado Criminal','c.pdf'),
  ('dane3909@mail.com','123',24224822500,'Madaline','Psicologia','Psicologo Infantil','c.pdf'),
  ('zelda1161@mail.com','123',18566785094,'Naida','Psicologia','Psicologo Infantil','c.pdf');
INSERT INTO profissional (email,senha,cpf,nome,areaConhecimento,especialidade,local_pdf)
VALUES
  ('cheyenne@mail.com','123',38425937185,'Hope','Advocacia', 'Advogado Criminal','c.pdf'),
  ('dominic@mail.com','123',61596129743,'Demetrius','Medicina', 'Cardiologista','c.pdf'),
  ('daryl@mail.com','123',43433452272,'Ariel','Medicina', 'Cardiologista','c.pdf'),
  ('david6613@mail.com','123',61385903802,'Odysseus','Psicologia','Psicologo Infantil','c.pdf'),
  ('burton@mail.com','123',72308536005,'Ria','Advocacia','Advogado Criminal','c.pdf'),
  ('felix8119@mail.com','123',28369710230,'Martena','Psicologia','Psicologo Infantil','c.pdf'),
  ('hedwig@mail.com','123',88716604930,'Mara','Psicologia','Psicologo Infantil','c.pdf'),
  ('conan4817@mail.com','123',80029196223,'Haley','Psicologia','Psicologo Infantil','c.pdf'),
  ('kalia3708@mail.com','123',45453916414,'McKenzie','Advocacia','Advogado Criminal','c.pdf');
SELECT email, COUNT(*) FROM profissional GROUP BY email HAVING COUNT(*) > 1;
SELECT cpf FROM profissional;