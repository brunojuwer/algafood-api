CREATE TABLE restaurante_usuario (
	restaurante_id bigint not null,
    usuario_id bigint not null,

    primary key(restaurante_id, usuario_id)
) engine=InnoDB default charset=utf8;

ALTER TABLE restaurante_usuario ADD CONSTRAINT fk_restaurante_id
FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante_usuario ADD CONSTRAINT fk_usuario_id
FOREIGN KEY (usuario_id) REFERENCES usuario (id);