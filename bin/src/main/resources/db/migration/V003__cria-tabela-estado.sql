CREATE TABLE estado (
	id bigint not null auto_increment,
	nome varchar(80) not null, 
	
	primary key(id)
	
) engine=InnoDB default charset=utf8;

INSERT INTO estado (nome) SELECT DISTINCT c.nome_estado FROM cidade c;

ALTER TABLE cidade ADD COLUMN estado_id bigint not null;

UPDATE cidade c set estado_id = (SELECT e.id FROM estado e WHERE c.nome_estado = e.nome);

ALTER TABLE cidade ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado (id);

ALTER TABLE cidade CHANGE nome_cidade nome varchar(80) not null;

ALTER TABLE cidade DROP COLUMN nome_estado;